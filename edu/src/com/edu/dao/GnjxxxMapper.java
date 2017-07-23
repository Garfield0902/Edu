package com.edu.dao;

import com.edu.domain.Gnjxxx;

public interface GnjxxxMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Gnjxxx record);

    int insertSelective(Gnjxxx record);

    Gnjxxx selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Gnjxxx record);

    int updateByPrimaryKey(Gnjxxx record);
}