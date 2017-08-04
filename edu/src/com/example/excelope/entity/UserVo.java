package com.example.excelope.entity;

import java.util.Date;

public class UserVo {
	private String name;
	private Integer age;
	private Date birthdate;
	private String remark;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Date getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Override
	public String toString() {
		return "UserVo [name=" + name + ", age=" + age + ", birthdate="
				+ birthdate + ", remark=" + remark + "]";
	}
}
