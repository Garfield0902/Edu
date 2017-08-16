package com.edu.service;

import java.util.List;
import java.util.Set;

import com.edu.domain.Permission;
import com.edu.vo.PermissionVo;

public interface PermissionServiceI {
	public Set<String> findPermissionsByRoleId(String roleId);

	public int getAllPermissionCount(PermissionVo permissionVo);

	public List<Permission> getAllPermission(PermissionVo permissionVo);

	public int addPermission(Permission p);

	public int update(Permission p);

	public int deleteByPrimaryKey(String string);
}
