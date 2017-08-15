package com.edu.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.servlet.ModelAndView;

import com.edu.cache.CacheManager;
import com.edu.common.SelectData;
import com.edu.common.entity.SelectEntity;
import com.edu.common.entity.TreeEntity;
import com.edu.domain.Department;
import com.edu.service.DepartmentService;
import com.edu.vo.DepartmentVo;
import com.edu.vo.GenePageVo;

@Controller
@RequestMapping("/department")
public class DepartmentController {
	Logger logger = LoggerFactory.getLogger(DepartmentController.class);
	@Autowired
    public CacheManager cacheManager;
	@Autowired
	public SelectData sd;
	@Autowired
	private DepartmentService service;
	
	@RequestMapping("/info.do")
	public ModelAndView departmentPage(HttpServletRequest req){
		ModelAndView mav = new ModelAndView();
		
		//从缓存中将下拉框数据类型拿出来；
		List<Map<String,Object>> list = cacheManager.getCacheData("edu_dbo_app_department");
		List<SelectEntity> se = sd.getSelectByType("JGLX",false);
		
		List<SelectEntity> sv = new ArrayList<SelectEntity>();
		List<TreeEntity> tel = new ArrayList<TreeEntity>();
		for(Map<String,Object> m :list){
			Integer id = (Integer) m.get("id");
			Integer pid = (Integer) m.get("pid");
    		String name = (String) m.get("dname");
    		String code = (String) m.get("dcode");
    		Integer type = (Integer) m.get("dtype");
    		Integer order = (Integer) m.get("dorder");
			TreeEntity te = new TreeEntity(id, pid, name,code,type);
			te.setDtype(type);
			te.setDorder(order);
			tel.add(te);
    	}
		mav.addObject("departs",tel);
		mav.addObject("dtypes", se);
		mav.setViewName("department");
		return mav;
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
	
	@RequestMapping(value="/addDept.do",method = RequestMethod.POST)
	public ModelAndView addDept(DepartmentVo dvo){
		ModelAndView mav = new ModelAndView();
		logger.debug("添加部门！");
		Department d = new Department();
		BeanUtils.copyProperties(dvo,d);
		service.addDept(d);
		//刷新缓存
		sd.reloadCache();
		mav.setViewName("redirect:/department/info.do");
		return mav;
	}
	
	@RequestMapping(value="/updateDept.do",method = RequestMethod.POST)
	public String updateDept(DepartmentVo dvo){
		logger.debug("修改部门！");
		Department d = new Department();
		BeanUtils.copyProperties(dvo,d);
		service.updateDept(d);
		//刷新缓存
		sd.reloadCache();
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
		//刷新缓存
		sd.reloadCache();
		return "ok";
	}
	
}
