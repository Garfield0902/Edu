package com.example.domain;

public class TUser {
	private int id;
	private String userName;
	private String passWord;
	private int roleId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	@Override
	public String toString() {
		return "TUser [id=" + id + ", userName=" + userName + ", passWord="
				+ passWord + ", roleId=" + roleId + "]";
	}
}
