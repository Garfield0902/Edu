package com.edu.service;

import java.util.List;
import java.util.Set;

import com.edu.domain.User;
import com.edu.vo.UserVo;

public interface UserServiceI {
	/**
	 * ����û�
	 * @param user
	 */
	int addUser(User user);
	/**
	 * ����û�Id��ȡ�û�
	 * @param userId
	 * @return
	 */
	User getUserById(String userId);
	/**
	 * ��ȡ����User
	 * @return
	 */
	public List<User> getAllUser();
	/**
	 * ͨ���û����ȡ�û�
	 * @return
	 */
	public User getUserByName(String user);
	/**
	 * ͨ��userName��ȡ�û���ɫ
	 * @param userName
	 * @return
	 */
	public Set<String> findRoles(String userName);
	/**
	 * ͨ���û����ȡȨ��
	 * @param userName
	 * @return
	 */
	public Set<String> findPermissions(String userName);
	
	List<User> getAllUser(UserVo userVo);
	int getAllUserCount(UserVo userVo);
	
	int update(User u);
	
	int deleteByPrimaryKey(String string);
}
