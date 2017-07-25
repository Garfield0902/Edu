package com.edu.dao;

import java.util.List;

import com.edu.domain.Bmpjxx;
import com.edu.vo.BmpjxxVo;
import com.edu.vo.PxhddaVo;
import com.edu.vo.SignUpVo;

public interface BmpjxxMapper {
    int deleteByPrimaryKey(String id);

    int insert(Bmpjxx record);

    int insertSelective(Bmpjxx record);

    Bmpjxx selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Bmpjxx record);

    int updateByPrimaryKey(Bmpjxx record);

	List<Bmpjxx> getAllBmpjxx(SignUpVo signUpVo);

	//查询教师档案
	List<PxhddaVo> getAllJsjbxx(BmpjxxVo bv);

	int getAllJsjbxxCount(BmpjxxVo bv);
}