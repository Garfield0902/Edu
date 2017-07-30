package com.edu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.dao.RoleMapper;
import com.edu.domain.Pxhd;
import com.edu.domain.Role;
import com.edu.domain.Tzgg;
import com.edu.service.RoleService;
import com.edu.vo.Pagination;
import com.edu.vo.PxhdVo;
import com.edu.vo.RoleVo;

@Service("RoleService")
public class RoleServiceImpl implements RoleService{

	@Autowired
	private RoleMapper roleMapper;
	
	public int getAllRoleCount(RoleVo roleVo){  
		return roleMapper.getAllRoleCount(roleVo);
    }
	public List<Role> getAllRole(RoleVo roleVo){  
        Integer rows = roleMapper.getAllRoleCount(roleVo);
        roleVo.setTotalCount(rows);  
        List<Role> list = roleMapper.getAllRole(roleVo);
        return list;  
    }
	public int insert(Role role){  
		return roleMapper.insert(role);
    }
	public int updateByPrimaryKeySelective(Role role){  
		return roleMapper.updateByPrimaryKeySelective(role);
    }
	public int deleteByPrimaryKey(String id){  
		return roleMapper.deleteByPrimaryKey(id);
    }
	public Role selectByPrimaryKey(String id){  
		return roleMapper.selectByPrimaryKey(id);
    }
}
