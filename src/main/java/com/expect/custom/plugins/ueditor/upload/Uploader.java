package com.expect.custom.plugins.ueditor.upload;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.expect.custom.plugins.ueditor.define.State;

@Component
public class Uploader {

	@Autowired
	private BinaryUploader bu;

	private HttpServletRequest request = null;
	private Map<String, Object> conf = null;
	private String actionType;

	public Uploader() {
	}

	public Uploader(HttpServletRequest request, Map<String, Object> conf, String actionType) {
		this.request = request;
		this.conf = conf;
		this.actionType = actionType;
	}

	public final State doExec(MultipartFile file) {
		String filedName = (String) this.conf.get("fieldName");
		State state = null;

		if ("true".equals(this.conf.get("isBase64"))) {
			state = Base64Uploader.save(this.request.getParameter(filedName), this.conf);
		} else {
			state = bu.save(this.request, this.conf, actionType, file);
		}
		return state;
	}

	public void setConf(Map<String, Object> conf) {
		this.conf = conf;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}
}
