package com.edu.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.dao.PermissionMapper;
import com.edu.service.PermissionServiceI;
@Service
public class PermissionServiceImpl implements PermissionServiceI{
	@Autowired
	private PermissionMapper permissionMapper;
	
	@Override
	public Set<String> findPermissionsByRoleId(String roleId) {
		return permissionMapper.findPermissionsByRoleId(roleId);
	}
}
