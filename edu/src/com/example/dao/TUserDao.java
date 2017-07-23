package com.example.dao;

import java.util.List;

import com.example.domain.TUser;

public interface TUserDao {
	/**
	 * 根据username查询用户，之后Shiro会根据查询出来的User的密码来和提交上来的密码进行比对。
	 * @param userName
	 * @return
	 */
	public TUser findUserByName(String userName);
	/**
	 * 根据username查询该用户的所有角色，用于角色验证。
	 * @param userName
	 * @return
	 */
	public List<String> findRoles(String userName);
	/**
	 * 根据username查询他所拥有的权限信息，用于权限判断。
	 * @param name
	 * @return
	 */
	public List<String> findPermissions(String name);
}
