package com.expect.custom.service.vo.component;

import com.expect.custom.utils.JacksonJsonUtil;

public class BaseVo {

	@Override
	public String toString() {
		String result = JacksonJsonUtil.getInstance().write(this);
		return result;
	}

}
