package com.edu.dao;

import java.util.Set;

import com.edu.domain.Permission;

public interface PermissionMapper {
    int deleteByPrimaryKey(String id);

    int insert(Permission record);

    int insertSelective(Permission record);

    Permission selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);
    
    Set<String> findPermissionsByRoleId(String id);
}