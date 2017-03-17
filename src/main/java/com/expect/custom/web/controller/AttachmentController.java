package com.expect.custom.web.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.expect.custom.service.impl.AttachmentService;
import com.expect.custom.service.vo.AttachmentVo;
import com.expect.custom.utils.IOUtil;
import com.expect.custom.utils.RequestUtil;

@Controller
@RequestMapping("/attachment")
public class AttachmentController {

	@Autowired
	private AttachmentService attachmentService;

	/**
	 * 附件下载
	 */
	@RequestMapping(value = "/download", method = RequestMethod.GET)
	public void download(String id, HttpServletResponse response) {
		if (StringUtils.isBlank(id)) {
			return;
		}
		AttachmentVo attachment = attachmentService.getAttachmentById(id);
		if (attachment != null) {
			String path = attachment.getPath() + File.separator + attachment.getName();
			byte[] buffer = IOUtil.inputDataFromFile(path);
			try {
				RequestUtil.downloadFile(buffer, attachment.getOriginalName(), response);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 显示图片/视频
	 */
	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public void show(String id, HttpServletResponse response) {
		if (StringUtils.isBlank(id)) {
			return;
		}
		AttachmentVo attachment = attachmentService.getAttachmentById(id);
		if (attachment != null) {
			String path = attachment.getPath() + File.separator + attachment.getName();
			byte[] buffer = IOUtil.inputDataFromFile(path);
			try {
				response.getOutputStream().write(buffer);
				response.getOutputStream().flush();
				response.getOutputStream().close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
