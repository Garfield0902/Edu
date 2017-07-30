package com.edu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.dao.BmpjxxMapper;
import com.edu.dao.PxhdMapper;
import com.edu.domain.Bmpjxx;
import com.edu.domain.Pxhd;
import com.edu.service.TrainingInfoServiceI;
import com.edu.vo.Pagination;
import com.edu.vo.PxhdVo;
import com.edu.vo.SignUpVo;

@Service("TrainingInfoService")
public class TrainingInfoServiceImpl implements TrainingInfoServiceI{

	@Autowired
	private PxhdMapper pxhdMapper;
	@Autowired
	private BmpjxxMapper bmpjxxMapper;

	public int getAllPxhdCount(PxhdVo pxhdVo){  
		return pxhdMapper.getAllPxhdCount(pxhdVo);
    }
	public List<Pxhd> getAllPxhd(PxhdVo pxhdVo){  
		Integer count = pxhdMapper.getAllPxhdCount(pxhdVo);
		pxhdVo.setTotalCount(count);
        List<Pxhd> list = pxhdMapper.getAllPxhd(pxhdVo);
        return list;
    }

	public int signUp(Bmpjxx record) {
		return bmpjxxMapper.insert(record);
	}
	public List<Bmpjxx> getAllBm(PxhdVo pxhdVo){
        List<Bmpjxx> list = bmpjxxMapper.getAllBmpjxx(pxhdVo);
        return list;
	}

	@Override
	public int cancelSignUp(String id) {
		// TODO Auto-generated method stub
		return bmpjxxMapper.deleteByPrimaryKey(id);
	}
	@Override
	public int delete(String id) {
		// TODO Auto-generated method stub
		return pxhdMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Pxhd pxhd) {
		// TODO Auto-generated method stub
		return pxhdMapper.insert(pxhd);
	}

	@Override
	public int updateByPrimaryKeySelective(Pxhd pxhd) {
		// TODO Auto-generated method stub
		return pxhdMapper.updateByPrimaryKeySelective(pxhd);
	}
	
	@Override
	public Pxhd selectByPrimaryKey(String hdid) {
		// TODO Auto-generated method stub
		return pxhdMapper.selectByPrimaryKey(hdid);
	}
}
