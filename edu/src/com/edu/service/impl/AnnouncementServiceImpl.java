package com.edu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.dao.TzggMapper;
import com.edu.domain.Tzgg;
import com.edu.service.AnnouncementServiceI;
import com.edu.vo.AnnouncementVo;
import com.edu.vo.Pagination;

@Service("AnnouncementService")
public class AnnouncementServiceImpl implements AnnouncementServiceI{

	@Autowired
	private TzggMapper tzggMapper;
	
	public List<Tzgg> getAllTzgg(AnnouncementVo announcementVo){  
        if (announcementVo.getPage() == null) {  
        	announcementVo.setPage(new Pagination());  
        }  
        Integer rows = tzggMapper.getAllTzggCount();  
        announcementVo.getPage().setTotalCount(rows);  
        Pagination page = announcementVo.getPage();  
        List<Tzgg> list = tzggMapper.getAllTzgg(announcementVo);
        return list;  
    } 
	public int deleteByPrimaryKey(String tzggh){
		return tzggMapper.deleteByPrimaryKey(tzggh);
	}
	public int insert(Tzgg tzgg){
		return tzggMapper.insert(tzgg);
	}
	public int updateByPrimaryKeySelective(Tzgg tzgg){
		return tzggMapper.updateByPrimaryKeySelective(tzgg);
	}
	@Override
	public Tzgg selectByPrimaryKey(String id) {
		return tzggMapper.selectByPrimaryKey(id);
	}
}
