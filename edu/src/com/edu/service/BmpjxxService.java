package com.edu.service;

import java.util.List;

import com.edu.domain.Bmpjxx;
import com.edu.vo.BmpjxxVo;
import com.edu.vo.EvaluationManagementVo;
import com.edu.vo.PxhdVo;
import com.edu.vo.PxhddaVo;

public interface BmpjxxService {
	List<PxhddaVo> getAllJsjbxx(BmpjxxVo bv);

	int getAllJsjbxxCount(BmpjxxVo bv);
	
	public List<Bmpjxx> getAllBmpjxx(BmpjxxVo bv);
	public int getAllBmByIdCount(BmpjxxVo bv);
	
	public List<EvaluationManagementVo> getAllEvaluationManagement(PxhdVo pxhdVo);
	public int getAllEvaluationManagementCount(PxhdVo pxhdVo);
	public int insertSelective(Bmpjxx bmpjxx);
	public int deleteByPrimaryKey(String id);
}
