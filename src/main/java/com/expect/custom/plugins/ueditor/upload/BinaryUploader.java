package com.expect.custom.plugins.ueditor.upload;

import java.io.File;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.expect.custom.config.Settings;
import com.expect.custom.plugins.ueditor.define.AppInfo;
import com.expect.custom.plugins.ueditor.define.BaseState;
import com.expect.custom.plugins.ueditor.define.State;
import com.expect.custom.service.impl.AttachmentService;
import com.expect.custom.service.vo.component.FileResultVo;
import com.expect.custom.utils.DateUtil;

@Component
public class BinaryUploader {

	@Autowired
	private AttachmentService attachmentService;
	@Autowired
	private Settings settings;

	public State save(HttpServletRequest request, Map<String, Object> conf, String actionType, MultipartFile upfile) {
		try {
			String path = settings.getUeditorPath();
			String url = null;
			String fileName = null;
			String fileExt = null;

			if (upfile != null) {
				fileName = upfile.getOriginalFilename();
				fileExt = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
				if (!validType(fileExt, (String[]) conf.get("allowFiles"))) {
					return new BaseState(false, 8);
				}
				String currentDate = DateUtil.format(new Date(System.currentTimeMillis()), DateUtil.newFormat);
				path = path + File.separator + currentDate;
				FileResultVo frv = attachmentService.save(upfile, path);
				if (!frv.isResult()) {
					return new BaseState(false, AppInfo.IO_ERROR);
				}
				String id = frv.getId();
				if (actionType != null) {
					String contextPath = request.getContextPath();
					System.out.println(contextPath);
					if (actionType.equals("uploadfile")) {
						url = contextPath + "/attachment/download?id=" + id;
					} else {
						url = contextPath + "/attachment/show?id=" + id;
					}
				}
			} else {
				return new BaseState(false, AppInfo.IO_ERROR);
			}

			State storageState = new BaseState(Boolean.TRUE);
			if (storageState.isSuccess()) {
				storageState.putInfo("url", url);
				storageState.putInfo("type", fileExt);
				storageState.putInfo("original", fileName);
			}
			return storageState;
		} catch (Exception e) {
			e.printStackTrace();
			return new BaseState(false, AppInfo.IO_ERROR);
		}
	}

	private static boolean validType(String type, String[] allowTypes) {
		List<String> list = Arrays.asList(allowTypes);

		return list.contains(type);
	}
}
