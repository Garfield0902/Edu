package com.example.domain;

public class TPermission {
	private int id;
	private String permissionName;
	private int roleId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPermissionName() {
		return permissionName;
	}
	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	@Override
	public String toString() {
		return "TPermission [id=" + id + ", permissionName=" + permissionName
				+ ", roleId=" + roleId + "]";
	}
}
