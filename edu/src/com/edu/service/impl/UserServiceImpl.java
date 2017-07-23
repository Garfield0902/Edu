package com.edu.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.edu.dao.PermissionMapper;
import com.edu.dao.UserMapper;
import com.edu.domain.User;
import com.edu.service.UserServiceI;
/**
 * @author 11016
 * ʹ��@Service ע�⽫UserServiceImpl���עΪһ�� service
 * ���service��id�� userService
 */
@Service("userService")
public class UserServiceImpl implements UserServiceI{
	Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	/**
	 * ʹ�� @Autowired ע���עuserMapper������
	 */
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private PermissionMapper permissionMapper;
	
	@Override
	public void addUser(User user) {
		userMapper.insert(user);
	}

	@Override
	public User getUserById(String userId) {
		return userMapper.selectByPrimaryKey(userId);
	}

	public List<User> getAllUser(){
		return userMapper.getAllUser();
	}

	@Override
	public User getUserByName(String name) {
		return userMapper.getUserByName(name);
	}

	@Override
	public Set<String> findRoles(String userName) {
		return userMapper.findRolesName(userName);
	}

	@Override
	public Set<String> findPermissions(String userName) {
		if(StringUtils.isEmpty(userName)){
			logger.warn("用户名为空");
			return null;
		}
		//通过用户名查找角色
		Set<String> roles = userMapper.findRolesId(userName);
		if(roles==null||roles.size()==0){
			logger.warn(userName+ "对应角色为空！");
			return null;
		}
		
		Set<String> permissions = new HashSet<String>();
		for (String roleId : roles) {
			Set<String> tp = permissionMapper.findPermissionsByRoleId(roleId);
			permissions.addAll(tp);
		}
		return permissions;
	}
}
