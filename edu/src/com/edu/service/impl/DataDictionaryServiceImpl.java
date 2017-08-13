package com.edu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.dao.DatadictionaryMapper;
import com.edu.dao.TzggMapper;
import com.edu.domain.Datadictionary;
import com.edu.domain.Pxhd;
import com.edu.domain.Tzgg;
import com.edu.service.DataDictionaryServiceI;
import com.edu.vo.AnnouncementVo;
import com.edu.vo.DataDictionaryVo;
import com.edu.vo.Pagination;
import com.edu.vo.PxhdVo;

@Service("dataDictionaryService")
public class DataDictionaryServiceImpl implements DataDictionaryServiceI{

	@Autowired
	private DatadictionaryMapper datadictionaryMapper;
	
	public int getAllDataDictionaryCount(DataDictionaryVo dataDictionaryVo){  
		return datadictionaryMapper.getAllDataDictionaryCount(dataDictionaryVo);
    }
	public List<Datadictionary> getAllDataDictionary(DataDictionaryVo dataDictionaryVo){  
        List<Datadictionary> list = datadictionaryMapper.getAllDataDictionary(dataDictionaryVo);
        return list;
    } 
	@Override
	public int deleteByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return datadictionaryMapper.deleteByPrimaryKey(id);
	}
	@Override
	public int insert(Datadictionary datadictionary) {
		// TODO Auto-generated method stub
		return datadictionaryMapper.insertSelective(datadictionary);
	}
	@Override
	public Datadictionary selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return datadictionaryMapper.selectByPrimaryKey(id);
	}
	@Override
	public int updateByPrimaryKeySelective(Datadictionary datadictionary) {
		return datadictionaryMapper.updateByPrimaryKeySelective(datadictionary);
	}
}
