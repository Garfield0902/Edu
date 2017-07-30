package com.edu.service;

import java.util.List;

import com.edu.domain.Bmpjxx;
import com.edu.domain.Pxhd;
import com.edu.vo.PxhdVo;
import com.edu.vo.SignUpVo;

public interface TrainingInfoServiceI {
	public int getAllPxhdCount(PxhdVo pxhdVo);
	public List<Pxhd> getAllPxhd(PxhdVo pxhdVo);
	public int signUp(Bmpjxx record);
	public List<Bmpjxx> getAllBm(PxhdVo pxhdVo);
	public int cancelSignUp(String id);
	public int delete(String id);
	public int insert(Pxhd pxhd);
	public int updateByPrimaryKeySelective(Pxhd pxhd);
	public Pxhd selectByPrimaryKey(String hdid);
}
