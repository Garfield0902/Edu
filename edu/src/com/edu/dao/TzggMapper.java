package com.edu.dao;

import java.util.List;

import com.edu.domain.Tzgg;
import com.edu.vo.AnnouncementVo;

public interface TzggMapper {
    int deleteByPrimaryKey(String tzggh);

    int insert(Tzgg record);

    int insertSelective(Tzgg record);

    Tzgg selectByPrimaryKey(String tzggh);

    int updateByPrimaryKeySelective(Tzgg record);

    int updateByPrimaryKeyWithBLOBs(Tzgg record);

    int updateByPrimaryKey(Tzgg record);

	Integer getAllTzggCount();

	List<Tzgg> getAllTzgg(AnnouncementVo announcementVo);
}