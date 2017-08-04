package com.edu.util;

import com.google.gson.annotations.Expose;

/**
 * 通用VO
 * @author viva
 *
 * @param <T>
 */
public class GeneralVO<T> extends BaseVo {

	@Expose
	private T body;	
	
	public GeneralVO() {
	}
	/**
	 * @param code	返回码数组
	 * @param error	错误信息数组
	 * @param body	返回正文内容
	 */
	public GeneralVO(int code,String error,T body) {
		this.setCode(code);
		this.setError(error);
		this.setBody(body);
	}
	public T getBody() {
		return body;
	}
	
	public void setBody(T t) {
		this.body = t;
	}
	
}
