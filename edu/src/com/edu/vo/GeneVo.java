
package com.edu.vo;

public class GeneVo<T> {
	private String code;
	private String msg;
	private T body;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public T getBody() {
		return body;
	}
	public void setBody(T body) {
		this.body = body;
	}
	@Override
	public String toString() {
		return "GeneVo [code=" + code + ", msg=" + msg + ", bogy=" + body + "]";
	}
}
