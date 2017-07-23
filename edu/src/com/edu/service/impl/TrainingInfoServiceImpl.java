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
import com.edu.vo.SignUpVo;
import com.edu.vo.TrainingInfoAnnouncementVo;

@Service("TrainingInfoService")
public class TrainingInfoServiceImpl implements TrainingInfoServiceI{

	@Autowired
	private PxhdMapper pxhdMapper;
	@Autowired
	private BmpjxxMapper bmpjxxMapper;

	public List<Pxhd> getAllPxhd(TrainingInfoAnnouncementVo trainingInfoAnnouncementVo){  
        if (trainingInfoAnnouncementVo.getPage() == null) {  
        	trainingInfoAnnouncementVo.setPage(new Pagination());  
        }  
        Integer rows = pxhdMapper.getAllPxhdCount(); 
        trainingInfoAnnouncementVo.getPage().setTotalCount(rows);  
        Pagination page = trainingInfoAnnouncementVo.getPage();  
        List<Pxhd> list = pxhdMapper.getAllPxhd(trainingInfoAnnouncementVo);
        return list;
    }

	public int signUp(Bmpjxx record) {
		return bmpjxxMapper.insert(record);
	}
	public List<Bmpjxx> getAllBm(SignUpVo signUpVo){
		if (signUpVo.getPage() == null) {
			signUpVo.setPage(new Pagination());  
        }  
//        Integer rows = pxhdMapper.getAllPxhdCount(); 
//        signUpVo.getPage().setTotalCount(rows);  
        Pagination page = signUpVo.getPage();  
        List<Bmpjxx> list = bmpjxxMapper.getAllBmpjxx(signUpVo);
        return list;
	}

	@Override
	public int cancelSignUp(String id) {
		// TODO Auto-generated method stub
		return bmpjxxMapper.deleteByPrimaryKey(id);
	}
}
