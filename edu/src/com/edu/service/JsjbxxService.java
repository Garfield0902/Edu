package com.edu.service;

import java.util.List;

import com.edu.domain.Jsjbxx;
import com.edu.vo.JsjbxxVo;
import com.edu.vo.TjxfVo;

public interface JsjbxxService {
	/**
	 * 通过职工号拿到职工id
	 * @param js
	 * @return
	 */
	Jsjbxx getJsjbxxInfo(JsjbxxVo js);
	/**
	 * 查询所有教师信息
	 * @param js
	 * @return
	 */
	List<Jsjbxx> getAllJsjbxx(JsjbxxVo js);
	/**
	 * 教师添加学分
	 * @param js
	 */
	void addJsxf(TjxfVo js);

}
