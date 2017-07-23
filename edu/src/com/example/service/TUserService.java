package com.example.service;

import java.util.Set;

import com.example.domain.TUser;

public interface TUserService {
	
	public TUser findUserByUserName(String userName);
	
	public Set<String> findRoles(String userName);
	
	public Set<String> findPermissions(String userName);
}
