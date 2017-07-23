package com.edu.component.dao;

import org.springframework.stereotype.Component;

import com.edu.component.entity.SystemLog;

@Component
public interface SystemLogMapper {
    int deleteByPrimaryKey(String id);

    int insert(SystemLog record);

    int insertSelective(SystemLog record);

    SystemLog selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SystemLog record);

    int updateByPrimaryKey(SystemLog record);
}
