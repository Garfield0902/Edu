package com.edu.vo;

public class DepartmentVo {
    private Integer id;

    private Integer pid;

    private String dname;

    private String dcode;

    private Integer dtype;

    private Integer dorder;
    
    private String ids;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname == null ? null : dname.trim();
    }

    public String getDcode() {
        return dcode;
    }

    public void setDcode(String dcode) {
        this.dcode = dcode == null ? null : dcode.trim();
    }

    public Integer getDtype() {
        return dtype;
    }

    public void setDtype(Integer dtype) {
        this.dtype = dtype;
    }

    public Integer getDorder() {
        return dorder;
    }

    public void setDorder(Integer dorder) {
        this.dorder = dorder;
    }

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}
}