package com.edu.service;

import java.util.List;

import com.edu.domain.Bmpjxx;
import com.edu.domain.Role;
import com.edu.vo.BmpjxxVo;
import com.edu.vo.RoleVo;

public interface RoleService {
	
	public int getAllRoleCount(RoleVo roleVo);
	List<Role> getAllRole(RoleVo roleVo);
	public int insert(Role role);
	public int updateByPrimaryKeySelective(Role role);
	public int deleteByPrimaryKey(String id);
	public Role selectByPrimaryKey(String id);
}
