package com.expect.custom.service.vo;

import com.expect.custom.service.vo.component.BaseVo;
import com.expect.custom.utils.JacksonJsonUtil;

public class AttachmentVo extends BaseVo {

	private String id;
	private String name;
	private String originalName;
	private String path;
	private String timeStr;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOriginalName() {
		return originalName;
	}

	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getTimeStr() {
		return timeStr;
	}

	public void setTimeStr(String timeStr) {
		this.timeStr = timeStr;
	}

	@Override
	public String toString() {
		String result = JacksonJsonUtil.getInstance().write(this);
		return result;
	}

}
