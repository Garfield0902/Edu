package com.edu.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.dao.PermissionMapper;
import com.edu.domain.Permission;
import com.edu.domain.User;
import com.edu.service.PermissionServiceI;
import com.edu.vo.PermissionVo;
@Service
public class PermissionServiceImpl implements PermissionServiceI{
	@Autowired
	private PermissionMapper permissionMapper;
	
	@Override
	public Set<String> findPermissionsByRoleId(String roleId) {
		return permissionMapper.findPermissionsByRoleId(roleId);
	}

	@Override
	public int getAllPermissionCount(PermissionVo permissionVo) {
		return permissionMapper.getAllPermissionCount(permissionVo);
	}

	@Override
	public List<Permission> getAllPermission(PermissionVo permissionVo) {
		List<Permission> list = permissionMapper.getAllPermission(permissionVo);
		return list;
	}

	@Override
	public int addPermission(Permission p) {
		return permissionMapper.insertSelective(p);
	}

	@Override
	public int update(Permission p) {
		return permissionMapper.updateByPrimaryKeySelective(p);
	}

	@Override
	public int deleteByPrimaryKey(String id) {
		return permissionMapper.deleteByPrimaryKey(id);
	}
}
