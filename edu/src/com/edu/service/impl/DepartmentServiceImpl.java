package com.edu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.dao.DepartmentMapper;
import com.edu.domain.Department;
import com.edu.service.DepartmentService;
import com.edu.vo.DepartmentVo;
@Service
public class DepartmentServiceImpl implements DepartmentService{
	@Autowired
	private DepartmentMapper mmper;
	@Override
	public List<Department> getDeptByPID(DepartmentVo dvo) {
		Integer pid = dvo.getPid();
		if(pid==null){
			pid =0;
		}
		return mmper.getDeptByPID(pid);
	}
	
	@Override
	public void addDept(Department d) {
		mmper.insertSelective(d);
	}

	@Override
	public void updateDept(Department d) {
		mmper.updateByPrimaryKeySelective(d);
	}

	@Override
	public void delDept(DepartmentVo d) {
		Integer id = d.getId();
		mmper.deleteByPrimaryKey(id);
	}
	
	@Override
	public void delDepts(DepartmentVo dvo) {
		mmper.delDepts(dvo);
	}
	
	@Override
	public List<Department> getDeptList(DepartmentVo dvo) {
		return mmper.getDeptList();
	}

}
