package com.edu.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edu.domain.Department;
import com.edu.domain.Jsjbxx;
import com.edu.service.DepartmentService;
import com.edu.vo.DepartmentVo;
import com.edu.vo.GenePageVo;
import com.edu.vo.Pagination;

@Controller
@RequestMapping("/department")
public class DepartmentController {
	Logger logger = LoggerFactory.getLogger(DepartmentController.class);
	@Autowired
	private DepartmentService service;
	
	@RequestMapping("/info.do")
	public String departmentPage(HttpServletRequest req){
		return "department";
	}
	
	
	@ResponseBody
	@RequestMapping(value="/getDeptByPID.do",method ={RequestMethod.POST,RequestMethod.GET},consumes="application/json")
	public GenePageVo getDeptByPID(@RequestBody DepartmentVo dvo){
		logger.debug("查询父部门下所有子部门！");
		final GenePageVo<Department> gv = new GenePageVo<Department>();
		List<Department> list= service.getDeptByPID(dvo);
		gv.setList(list);
		return gv;
	}
	
	@ResponseBody
	@RequestMapping(value="/getDeptList.do",method ={RequestMethod.POST,RequestMethod.GET})
	public GenePageVo getDeptList(DepartmentVo dvo){
		logger.debug("查询所有部门！");
		final GenePageVo<Department> gv = new GenePageVo<Department>();
		List<Department> list= service.getDeptList(dvo);
		gv.setList(list);
		return gv;
	}
	
	@RequestMapping(value="/addDept.do",method = RequestMethod.POST,consumes="application/json")
	public String addDept(DepartmentVo dvo){
		logger.debug("添加部门！");
		Department d = new Department();
		BeanUtils.copyProperties(dvo,d);
		service.addDept(d);
		return "department";
	}
	
	@RequestMapping(value="/updateDept.do",method = RequestMethod.POST)
	public String updateDept(DepartmentVo dvo){
		logger.debug("修改部门！");
		Department d = new Department();
		BeanUtils.copyProperties(dvo,d);
		service.updateDept(d);
		return "department";
	}
	
	@RequestMapping(value="/delDept.do",method ={RequestMethod.POST,RequestMethod.GET},consumes="application/json")
	@ResponseBody
	public String delDept(DepartmentVo dvo){
		logger.debug("删除部门！");
		if(StringUtils.isEmpty(dvo.getIds())){
			logger.debug("请选择要删除的数据！");
			return "请选择要删除的数据！";
		}
		service.delDepts(dvo);
		return "ok";
	}
	
}
