package com.edu.util.excel;

public class ContentInfo {

	private String name;
	private String sex;
	private String age;
	private String address;
	
	
	public ContentInfo() {
		super();
	}
	public ContentInfo(String name, String sex, String age, String address) {
		super();
		this.name = name;
		this.sex = sex;
		this.age = age;
		this.address = address;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
}
