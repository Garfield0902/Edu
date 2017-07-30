package com.edu.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.dao.BmpjxxMapper;
import com.edu.domain.Bmpjxx;
import com.edu.service.BmpjxxService;
import com.edu.vo.BmpjxxVo;
import com.edu.vo.PxhddaVo;
@Service
public class BmpjxxServiceImpl implements BmpjxxService{
	Logger logger = LoggerFactory.getLogger(BmpjxxServiceImpl.class);
	
	@Autowired
	private BmpjxxMapper bmmper;
	@Override
	public List<PxhddaVo> getAllJsjbxx(BmpjxxVo bv) {
		return bmmper.getAllJsjbxx(bv);
	}
	@Override
	public int getAllJsjbxxCount(BmpjxxVo bv) {
		return bmmper.getAllJsjbxxCount(bv);
	}
	public int deleteByPrimaryKey(String id){
		return bmmper.deleteByPrimaryKey(id);
	}
	@Override
	public List<Bmpjxx> getAllBmById(BmpjxxVo bv) {
		return bmmper.getAllBmById(bv);
	}
}
