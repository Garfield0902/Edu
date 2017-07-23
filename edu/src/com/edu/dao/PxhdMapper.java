package com.edu.dao;

import java.util.List;

import com.edu.domain.Pxhd;
import com.edu.vo.TrainingInfoAnnouncementVo;

public interface PxhdMapper {
    int deleteByPrimaryKey(String hdid);

    int insert(Pxhd record);

    int insertSelective(Pxhd record);

    Pxhd selectByPrimaryKey(String hdid);

    int updateByPrimaryKeySelective(Pxhd record);

    int updateByPrimaryKeyWithBLOBs(Pxhd record);

    int updateByPrimaryKey(Pxhd record);

	Integer getAllPxhdCount();

	List<Pxhd> getAllPxhd(TrainingInfoAnnouncementVo trainingInfoAnnouncementVo);
}