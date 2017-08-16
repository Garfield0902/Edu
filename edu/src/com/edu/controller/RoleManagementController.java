package com.edu.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.edu.domain.Role;
import com.edu.service.RoleService;
import com.edu.vo.GenePageVo;
import com.edu.vo.Pagination;
import com.edu.vo.RoleVo;

@RequestMapping("/roleManagement")
@Controller
public class RoleManagementController {
	
	@Autowired
	RoleService roleService;
	
	
	@RequestMapping("/roleManagementPage.do")
	private String roleManagementPage(){
		return "roleManagement";
	}
	@ResponseBody
	@RequestMapping(value="/getAllRoleManagement.do",method = RequestMethod.POST,consumes="application/json")
	private GenePageVo getAllRoleManagement(@RequestBody RoleVo roleVo){
		final GenePageVo<Role> gv = new GenePageVo<Role>();
		int count = roleService.getAllRoleCount(roleVo);
		roleVo.setTotalCount(count);
		Pagination p = new Pagination();
		BeanUtils.copyProperties(roleVo, p);
		List<Role> list= roleService.getAllRole(roleVo);
		gv.setList(list);
		gv.setPage(p);
		return gv;
	}
	
	@RequestMapping(value="/addRoleManagement.do",method = RequestMethod.POST)
	private ModelAndView addRole(Role role){
		ModelAndView mav = new ModelAndView();
		String id = role.getId();
		int result = 0;
		if(id == ""){
			role.setId(UUID.randomUUID().toString().replaceAll("-", ""));
			result = roleService.insert(role);
		}else{
			result = roleService.updateByPrimaryKeySelective(role);
		}
		if(result==1){
			mav.addObject("msg", "操作成功！");
		}else{
			mav.addObject("msg", "操作失败！");
		}
		 mav.setViewName("redirect:/roleManagement/roleManagementPage.do");
		return mav;
	}
	@RequestMapping(value="/delete.do",method = RequestMethod.POST,consumes="application/json")
	private int delete(@RequestBody String ids){
		String[] array = ids.split(",");
		int result = 0;
		for(int i = 0;i < array.length; i++){
			result = roleService.deleteByPrimaryKey(array[i]);
		}
		return result;
	}
	@RequestMapping(value="/selectRoleById.do",method = RequestMethod.POST)
	@ResponseBody
	private Role selectRoleById(@RequestBody String id){
		return roleService.selectByPrimaryKey(id);
	}
}
