package com.edu.dao;

import java.util.List;

import com.edu.domain.Bmpjxx;
import com.edu.vo.SignUpVo;

public interface BmpjxxMapper {
    int deleteByPrimaryKey(String id);

    int insert(Bmpjxx record);

    int insertSelective(Bmpjxx record);

    Bmpjxx selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Bmpjxx record);

    int updateByPrimaryKey(Bmpjxx record);

	List<Bmpjxx> getAllBmpjxx(SignUpVo signUpVo);
}