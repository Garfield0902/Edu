package com.edu.component.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.edu.component.entity.SystemLog;
import com.edu.domain.Datadictionary;
import com.edu.vo.SystemLogVo;

@Component
public interface SystemLogMapper {
    int deleteByPrimaryKey(String id);

    int insert(SystemLog record);

    int insertSelective(SystemLog record);

    SystemLog selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SystemLog record);

    int updateByPrimaryKey(SystemLog record);
    
    public int getAllSystemLogCount(SystemLogVo systemLogVo);
    
	public List<SystemLog> getSystemLog(SystemLogVo systemLogVo);
}
