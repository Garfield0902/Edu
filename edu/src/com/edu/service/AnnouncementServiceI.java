package com.edu.service;

import java.util.List;

import com.edu.domain.Tzgg;
import com.edu.vo.AnnouncementVo;

public interface AnnouncementServiceI {
	public List<Tzgg> getAllTzgg(AnnouncementVo announcementVo);
	public int deleteByPrimaryKey(String id);
	public int insert(Tzgg tzgg);
	public int updateByPrimaryKeySelective(Tzgg tzgg);
	public Tzgg selectByPrimaryKey(String id);
}
