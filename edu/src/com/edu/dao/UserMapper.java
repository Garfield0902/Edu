package com.edu.dao;

import java.util.List;
import java.util.Set;

import com.edu.domain.User;
import com.edu.vo.UserVo;

public interface UserMapper {
    int deleteByPrimaryKey(String id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    List<User> getAllUser();
    
    User getUserByName(String userName);
    
	public Set<String> findRolesName(String userName);
	public Set<String> findRolesId(String userName);

	List<User> getAllUserPage(UserVo userVo);
	int getAllUserPageCount(UserVo userVo);
}