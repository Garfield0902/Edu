package com.edu.common.entity;

public class TreeEntity {
	private Integer id;
	private Integer pid;
	private String dname;
	private String dcode;
	private Integer dtype;
	private Integer dorder;
	
	public TreeEntity() {
	}
	public TreeEntity(Integer id, Integer pid, String dname, String dcode,
			Integer dtype) {
		this.id = id;
		this.pid = pid;
		this.dname = dname;
		this.dcode = dcode;
		this.dtype = dtype;
	}

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
		this.dname = dname;
	}
	public String getDcode() {
		return dcode;
	}
	public void setDcode(String dcode) {
		this.dcode = dcode;
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
	@Override
	public String toString() {
		return "TreeEntity [id=" + id + ", pid=" + pid + ", dname=" + dname
				+ ", dcode=" + dcode + ", dtype=" + dtype + ", dorder="
				+ dorder + "]";
	}
}
