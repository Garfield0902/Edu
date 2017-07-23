package com.edu.vo;

import java.util.Date;

public class UserVo {
	private String id;

    private String name;

    private String password;

    private Integer status;

    private Date createdate;
    
    private String checknum;

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	public String getChecknum() {
		return checknum;
	}

	public void setChecknum(String checkNum) {
		this.checknum = checkNum;
	}

	@Override
	public String toString() {
		return "UserVo [id=" + id + ", name=" + name + ", password=" + password
				+ ", status=" + status + ", createdate=" + createdate + "]";
	}
}
