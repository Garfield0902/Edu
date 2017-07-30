package com.edu.service;

import java.util.List;

import com.edu.domain.Datadictionary;
import com.edu.domain.Tzgg;
import com.edu.vo.DataDictionaryVo;

public interface DataDictionaryServiceI {
	public int getAllDataDictionaryCount(DataDictionaryVo dataDictionaryVo);
	public List<Datadictionary> getAllDataDictionary(DataDictionaryVo dataDictionaryVo);
	public int deleteByPrimaryKey(String id);
	public int insert(Datadictionary dataDictionary);
	public int updateByPrimaryKeySelective(Datadictionary dataDictionary);
	public Datadictionary selectByPrimaryKey(String id);
}
