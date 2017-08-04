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
	public int cancelSignUp(String id);
	public int delete(String id);
	public int insert(Pxhd pxhd);
	public int updateByPrimaryKeySelective(Pxhd pxhd);
	public Pxhd selectByPrimaryKey(String hdid);
	/**
	 * ƴװ ex'ce'l �ṹ
	 * @param list
	 * @return
	 */
	public Workbook getexcelData(List<Pxhd> list) throws Exception ;
	/**
	 * ����������ѵ�
	 * @param vals
	 */
	public void insertTrainingInfoBach(List<Object> vals);
	
}
