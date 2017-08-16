package com.edu.dao;

import java.util.List;
import java.util.Set;

import com.edu.domain.Permission;
import com.edu.vo.PermissionVo;

public interface PermissionMapper {
    int deleteByPrimaryKey(String id);

    int insert(Permission record);

    int insertSelective(Permission record);

    Permission selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);
    
    Set<String> findPermissionsByRoleId(String id);

    
	int getAllPermissionCount(PermissionVo permissionVo);

	List<Permission> getAllPermission(PermissionVo permissionVo);
}