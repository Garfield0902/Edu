package com.edu.service;

import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;

import com.edu.domain.Bmpjxx;
import com.edu.domain.Pxhd;
import com.edu.vo.PxhdVo;
import com.edu.vo.SignUpVo;

public interface TrainingInfoServiceI {
	public int getAllPxhdCount(PxhdVo pxhdVo);
	public List<Pxhd> getAllPxhd(PxhdVo pxhdVo,String zgh);
	public List<Pxhd> getAllPxhdNoPage(PxhdVo pxhdVo);
	public int signUp(Bmpjxx record);
	/*public List<Bmpjxx> getAllBm(PxhdVo pxhdVo);*/
	public int cancelSignUp(String id);
	public int delete(String id);
	public int insert(Pxhd pxhd);
	public int updateByPrimaryKeySelective(Pxhd pxhd);
	public Pxhd selectByPrimaryKey(String hdid);
	/**
	 * 拼装 ex'ce'l 结构
	 * @param list
	 * @return
	 */
	public Workbook getexcelData(List<Pxhd> list) throws Exception ;
	/**
	 * 批量插入培训活动
	 * @param vals
	 */
	public void insertTrainingInfoBach(List<Object> vals);
	
}
