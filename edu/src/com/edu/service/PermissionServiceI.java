package com.edu.service;

import java.util.Set;

public interface PermissionServiceI {
	/**
	 * 通过角色id获取权限
	 * @param roleId
	 * @return
	 */
	public Set<String> findPermissionsByRoleId(String roleId);
}
