package com.edu.service;

import java.util.List;

import com.edu.domain.Department;
import com.edu.vo.DepartmentVo;

public interface DepartmentService {
	/**
	 * 通过pid查询子部门
	 * @param dvo
	 * @return
	 */
	List<Department> getDeptByPID(DepartmentVo dvo);
	/**
	 * 添加部门
	 * @param d
	 */
	void addDept(Department d);
	/**
	 * 修改部门
	 * @param d
	 */
	void updateDept(Department d);
	/**
	 * 删除部门
	 * @param d
	 */
	void delDept(DepartmentVo dvo);
	/**
	 * 删除多个部门
	 * @param dvo
	 */
	void delDepts(DepartmentVo dvo);
	/**
	 * 查询所有部门
	 * @param dvo
	 * @return
	 */
	List<Department> getDeptList(DepartmentVo dvo);

}
