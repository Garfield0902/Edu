package com.edu.dao;

import com.edu.domain.Xxjl;

public interface XxjlMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Xxjl record);

    int insertSelective(Xxjl record);

    Xxjl selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Xxjl record);

    int updateByPrimaryKey(Xxjl record);
}