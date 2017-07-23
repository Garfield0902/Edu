package com.edu.domain;

import java.util.Date;

public class User {
    private String id;

    private String name;

    private String password;

    private Integer status;

    private Date createdate;
    
    private Integer jsjbxxid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
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

	public Integer getJsjbxxid() {
		return jsjbxxid;
	}

	public void setJsjbxxid(Integer jsjbxxid) {
		this.jsjbxxid = jsjbxxid;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password
				+ ", status=" + status + ", createdate=" + createdate
				+ ", jsjbxxid=" + jsjbxxid + "]";
	}
}