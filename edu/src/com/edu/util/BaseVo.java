package com.edu.util;

import com.google.gson.annotations.Expose;

/**
 * 返回json 视图基类
 * @author viva
 */ 
public class BaseVo {

	@Expose
	private int code;
	@Expose
	private String error;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}	
}
