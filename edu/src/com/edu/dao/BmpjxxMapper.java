package com.edu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.edu.domain.Bmpjxx;
import com.edu.vo.BmpjxxVo;
import com.edu.vo.EvaluationManagementVo;
import com.edu.vo.PxhdVo;
import com.edu.vo.PxhddaVo;

public interface BmpjxxMapper {
    int deleteByPrimaryKey(String id);

    int insert(Bmpjxx record);

    int insertSelective(Bmpjxx record);

    Bmpjxx selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Bmpjxx record);

    int updateByPrimaryKey(Bmpjxx record);

	List<PxhddaVo> getAllJsjbxx(BmpjxxVo bv);
	
	List<Bmpjxx> getAllBmpjxx(BmpjxxVo bv);

	int getAllBmpjxxCount(BmpjxxVo bv);
	
	int getAllJsjbxxCount(BmpjxxVo bv);
	
	public List<EvaluationManagementVo> getAllEvaluationManagement(PxhdVo pxhdVo);
	public int getAllEvaluationManagementCount(PxhdVo pxhdVo);
	/**
	 * @param zgh
	 * @return
	 */
	List<BmpjxxVo> getPxhdByZgh(@Param("zgh") String zgh);
}