package com.edu.component.service;

import java.util.List;

import com.edu.component.entity.SystemLog;
import com.edu.domain.Datadictionary;
import com.edu.vo.SystemLogVo;

public interface SystemLogService {
    int deleteSystemLog(String id);
    int insert(SystemLog record);
    int insertTest(SystemLog record);
    SystemLog selectSystemLog(String id);
    int updateSystemLog(SystemLog record);
    public int getAllSystemLogCount(SystemLogVo systemLogVo);
	public List<SystemLog> getSystemLog(SystemLogVo systemLogVo);
}
