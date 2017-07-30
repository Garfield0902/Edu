package com.edu.dao;

import java.util.List;

import com.edu.domain.Datadictionary;
import com.edu.vo.DataDictionaryVo;

public interface DatadictionaryMapper {
    int deleteByPrimaryKey(String id);

    int insert(Datadictionary record);

    int insertSelective(Datadictionary record);

    Datadictionary selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Datadictionary record);

    int updateByPrimaryKey(Datadictionary record);
    
    int getAllDataDictionaryCount(DataDictionaryVo dataDictionaryVo);
    
    List<Datadictionary> getAllDataDictionary(DataDictionaryVo dataDictionaryVo);
}