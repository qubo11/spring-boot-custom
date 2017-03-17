package com.expect.custom.service.impl;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.expect.custom.config.Settings;
import com.expect.custom.data.dao.AttachmentRepository;
import com.expect.custom.data.dataobject.Attachment;
import com.expect.custom.service.vo.AttachmentVo;
import com.expect.custom.service.vo.component.FileResultVo;
import com.expect.custom.utils.IOUtil;

/**
 * 附件Service
 */
@Service
public class AttachmentService {

	@Autowired
	private AttachmentRepository attachmentRepository;
	@Autowired
	private Settings settings;

	/**
	 * 保存单张图片
	 */
	public FileResultVo save(MultipartFile file, String path) {
		FileResultVo frv = new FileResultVo(false, "上传失败");
		try {
			if (file == null || file.getBytes() == null) {
				return frv;
			}
		} catch (IOException e) {
			e.printStackTrace();
			return frv;
		}
		if (StringUtils.isBlank(path)) {
			path = settings.getAttachmentPath();
		}
		String fileName = file.getOriginalFilename();
		fileName = fileName + "-" + UUID.randomUUID();
		try {
			IOUtil.outputDataToFile(file.getBytes(), path, fileName);
		} catch (IOException e) {
			e.printStackTrace();
			return frv;
		}
		Attachment attachment = new Attachment();
		attachment.setName(fileName);
		attachment.setOriginalName(file.getOriginalFilename());
		attachment.setPath(path);
		attachment.setTime(new Date());
		Attachment resultAttachment = attachmentRepository.save(attachment);
		if (resultAttachment != null) {
			frv.setId(resultAttachment.getId());
			frv.setMessage("上传成功");
			frv.setResult(true);
		} else {
			frv.addErrorName(fileName);
		}
		return frv;
	}

	/**
	 * 保存多张图片
	 */
	public FileResultVo save(MultipartFile[] files, String path) {
		FileResultVo frv = new FileResultVo(false, "上传失败");
		if (files == null || files.length == 0) {
			return frv;
		}
		for (MultipartFile file : files) {
			FileResultVo resultFileRv = save(file, path);
			if (!resultFileRv.isResult()) {
				frv.addErrorName(resultFileRv.getName());
			} else {
				frv.addIds(resultFileRv.getId());
			}
		}
		frv.setMessage("上传成功");
		frv.setResult(true);
		return frv;
	}

	/**
	 * 根据id获取附件
	 */
	public AttachmentVo getAttachmentById(String id) {
		Attachment attachment = attachmentRepository.findOne(id);
		if (attachment != null) {
			AttachmentVo attachmentVo = new AttachmentVo();
			try {
				BeanUtils.copyProperties(attachmentVo, attachment);
			} catch (IllegalAccessException | InvocationTargetException e) {
				e.printStackTrace();
			}
			return attachmentVo;
		}
		return null;
	}

	/**
	 * 根据ids获取多个附件
	 */
	public List<AttachmentVo> getAttachmentsByIds(String[] ids) {
		List<Attachment> attachments = attachmentRepository.findByIdIn(ids);
		if (CollectionUtils.isEmpty(attachments)) {
			return null;
		}
		List<AttachmentVo> attachmentVos = new ArrayList<>();
		for (Attachment attachment : attachments) {
			AttachmentVo attachmentVo = new AttachmentVo();
			try {
				BeanUtils.copyProperties(attachmentVo, attachment);
			} catch (IllegalAccessException | InvocationTargetException e) {
				e.printStackTrace();
			}
			attachmentVos.add(attachmentVo);
		}
		return attachmentVos;
	}
}
