package com.example.dao;

import java.util.List;

import com.example.domain.TUser;

public interface TUserDao {
	/**
	 * ����username��ѯ�û���֮��Shiro����ݲ�ѯ������User�����������ύ������������бȶԡ�
	 * @param userName
	 * @return
	 */
	public TUser findUserByName(String userName);
	/**
	 * ����username��ѯ���û������н�ɫ�����ڽ�ɫ��֤��
	 * @param userName
	 * @return
	 */
	public List<String> findRoles(String userName);
	/**
	 * ����username��ѯ����ӵ�е�Ȩ����Ϣ������Ȩ���жϡ�
	 * @param name
	 * @return
	 */
	public List<String> findPermissions(String name);
}
