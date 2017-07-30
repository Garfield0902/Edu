package com.edu.dao;

import java.util.List;

import com.edu.domain.Department;
import com.edu.vo.DepartmentVo;

public interface DepartmentMapper {
    int deleteByPrimaryKey(Integer id);
    
    void delDepts(DepartmentVo dvo);
    
    int insert(Department record);

    int insertSelective(Department record);

    Department selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Department record);

    int updateByPrimaryKey(Department record);
    
	List<Department> getDeptByPID(Integer pid);

	List<Department> getDeptList();

}