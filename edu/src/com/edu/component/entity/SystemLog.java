package com.edu.component.entity;

import java.util.Date;
/**
 * 日志类实体
 * @Description: TODO
 * @author zhangwc
 * @date 2017-7-11 下午4:00:10
 */
public class SystemLog {
	private String id;

    private String description;

    private String method;

    private Integer logtype;

    private String requestip;

    private String exceptioncode;

    private String exceptiondetail;

    private String params;

    private String createby;

    private Date createdate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method == null ? null : method.trim();
    }

    public Integer getLogtype() {
        return logtype;
    }

    public void setLogtype(Integer logtype) {
        this.logtype = logtype;
    }

    public String getRequestip() {
        return requestip;
    }

    public void setRequestip(String requestip) {
        this.requestip = requestip == null ? null : requestip.trim();
    }

    public String getExceptioncode() {
        return exceptioncode;
    }

    public void setExceptioncode(String exceptioncode) {
        this.exceptioncode = exceptioncode == null ? null : exceptioncode.trim();
    }

    public String getExceptiondetail() {
        return exceptiondetail;
    }

    public void setExceptiondetail(String exceptiondetail) {
        this.exceptiondetail = exceptiondetail == null ? null : exceptiondetail.trim();
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params == null ? null : params.trim();
    }

    public String getCreateby() {
        return createby;
    }

    public void setCreateby(String createby) {
        this.createby = createby == null ? null : createby.trim();
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

	@Override
	public String toString() {
		return "SystemLog [id=" + id + ", description=" + description
				+ ", method=" + method + ", logtype=" + logtype
				+ ", requestip=" + requestip + ", exceptioncode="
				+ exceptioncode + ", exceptiondetail=" + exceptiondetail
				+ ", params=" + params + ", createby=" + createby
				+ ", createdate=" + createdate + "]";
	}
}