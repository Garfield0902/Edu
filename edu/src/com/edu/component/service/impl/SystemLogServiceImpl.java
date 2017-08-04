package com.edu.component.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.component.dao.SystemLogMapper;
import com.edu.component.entity.SystemLog;
import com.edu.component.service.SystemLogService;
import com.edu.domain.Datadictionary;
import com.edu.vo.SystemLogVo;

@Service("systemLogService")
public class SystemLogServiceImpl implements SystemLogService{
	Logger logger = Logger.getLogger(SystemLogServiceImpl.class);
	
	@Autowired
	private SystemLogMapper slm;
	@Override
	public int deleteSystemLog(String id) {
		return slm.deleteByPrimaryKey(id);
	}
	@Override
	public int insert(SystemLog record) {
		return slm.insertSelective(record);
	}
	@Override
	public int insertTest(SystemLog record) {
		return slm.insert(record);
	}
	@Override
	public SystemLog selectSystemLog(String id) {
		return slm.selectByPrimaryKey(id);
	}
	@Override
	public int updateSystemLog(SystemLog record) {
		return slm.updateByPrimaryKeySelective(record);
	}
	public int getAllSystemLogCount(SystemLogVo systemLogVo){  
		return slm.getAllSystemLogCount(systemLogVo);
    }
	public List<SystemLog> getSystemLog(SystemLogVo systemLogVo){  
        List<SystemLog> list = slm.getSystemLog(systemLogVo);
        return list;
    } 
}
