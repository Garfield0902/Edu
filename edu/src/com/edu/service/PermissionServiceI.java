package com.edu.service;

import java.util.Set;

public interface PermissionServiceI {
	/**
	 * ͨ����ɫid��ȡȨ��
	 * @param roleId
	 * @return
	 */
	public Set<String> findPermissionsByRoleId(String roleId);
}
