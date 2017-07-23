package com.edu.component.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.component.dao.SystemLogMapper;
import com.edu.component.entity.SystemLog;
import com.edu.component.service.SystemLogService;

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
}
