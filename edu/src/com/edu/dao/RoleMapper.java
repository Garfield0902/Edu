package com.edu.dao;

import java.util.List;

import com.edu.domain.Role;
import com.edu.vo.RoleVo;

public interface RoleMapper {
    int deleteByPrimaryKey(String id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
    
    Integer getAllRoleCount(RoleVo roleVo);

	List<Role> getAllRole(RoleVo roleVo);
}