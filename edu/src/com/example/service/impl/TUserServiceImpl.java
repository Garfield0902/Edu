package com.example.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.TUserDao;
import com.example.domain.TUser;
import com.example.service.TUserService;
@Service
public class TUserServiceImpl implements TUserService{
	@Autowired
	private TUserDao tUserDao;

	@Override
	public TUser findUserByUserName(String userName) {
		return tUserDao.findUserByName(userName);
	}

	@Override
	public Set<String> findRoles(String userName) {
		return new HashSet<String>(tUserDao.findRoles(userName));
	}

	@Override
	public Set<String> findPermissions(String userName) {
		return new HashSet<String>(tUserDao.findPermissions(userName));
	}
}
