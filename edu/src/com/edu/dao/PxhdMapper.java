package com.edu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.edu.domain.Pxhd;
import com.edu.vo.BmpjxxVo;
import com.edu.vo.PxhdVo;

public interface PxhdMapper {
    int deleteByPrimaryKey(String hdid);

    int insert(Pxhd record);

    int insertSelective(Pxhd record);

    Pxhd selectByPrimaryKey(String hdid);

    int updateByPrimaryKeySelective(Pxhd record);

    int updateByPrimaryKeyWithBLOBs(Pxhd record);

    int updateByPrimaryKey(Pxhd record);

	Integer getAllPxhdCount(PxhdVo pxhdVo);

	List<Pxhd> getAllPxhd(PxhdVo pxhdVo);

	List<Pxhd> getAllPxhdNoPage(PxhdVo pxhdVo);

	Object insertTrainingInfoBach(List<Object> vals);
	
}