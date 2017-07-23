package com.edu.service;

import java.util.List;
import java.util.Set;

import com.edu.domain.User;

public interface UserServiceI {
	/**
	 * 添加用户
	 * @param user
	 */
	void addUser(User user);
	/**
	 * 根据用户Id获取用户
	 * @param userId
	 * @return
	 */
	User getUserById(String userId);
	/**
	 * 获取所有User
	 * @return
	 */
	public List<User> getAllUser();
	/**
	 * 通过用户名获取用户
	 * @return
	 */
	public User getUserByName(String user);
	/**
	 * 通过userName获取用户角色
	 * @param userName
	 * @return
	 */
	public Set<String> findRoles(String userName);
	/**
	 * 通过用户名获取权限
	 * @param userName
	 * @return
	 */
	public Set<String> findPermissions(String userName);
}
