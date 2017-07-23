package com.edu.dao;

import com.edu.domain.Cgjxx;

public interface CgjxxMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Cgjxx record);

    int insertSelective(Cgjxx record);

    Cgjxx selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Cgjxx record);

    int updateByPrimaryKey(Cgjxx record);
}