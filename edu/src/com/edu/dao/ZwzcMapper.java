package com.edu.dao;

import com.edu.domain.Zwzc;

public interface ZwzcMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Zwzc record);

    int insertSelective(Zwzc record);

    Zwzc selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Zwzc record);

    int updateByPrimaryKey(Zwzc record);
}