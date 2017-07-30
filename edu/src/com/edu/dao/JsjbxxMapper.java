package com.edu.dao;

import java.util.List;

import com.edu.domain.Jsjbxx;
import com.edu.vo.JsjbxxVo;

public interface JsjbxxMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Jsjbxx record);

    int insertSelective(Jsjbxx record);

    Jsjbxx selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Jsjbxx record);

    int updateByPrimaryKey(Jsjbxx record);
    /**
     * 通过 职工号查询职工信息
     * @param js
     * @return
     */
	Jsjbxx getJsjbxxInfo(String zgh);
	/**
	 * 可以通过职工号或者姓名查询职工
	 * @param js
	 * @return
	 */
	List<Jsjbxx> getAllJsjbxx(JsjbxxVo js);

	int getAllJsjbxxCount(JsjbxxVo js);
}