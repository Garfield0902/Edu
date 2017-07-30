package com.edu.dao;

import java.util.List;

import com.edu.domain.Bmpjxx;
import com.edu.vo.BmpjxxVo;
import com.edu.vo.PxhdVo;
import com.edu.vo.PxhddaVo;

public interface BmpjxxMapper {
    int deleteByPrimaryKey(String id);

    int insert(Bmpjxx record);

    int insertSelective(Bmpjxx record);

    Bmpjxx selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Bmpjxx record);

    int updateByPrimaryKey(Bmpjxx record);

	List<Bmpjxx> getAllBmpjxx(PxhdVo pxhdVo);

	//��ѯ��ʦ����
	List<PxhddaVo> getAllJsjbxx(BmpjxxVo bv);
	
	List<Bmpjxx> getAllBmById(BmpjxxVo bv);

	int getAllJsjbxxCount(BmpjxxVo bv);
}