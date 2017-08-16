package com.edu.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

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

import com.edu.component.Log;
import com.edu.domain.Permission;
import com.edu.domain.User;
import com.edu.service.PermissionServiceI;
import com.edu.vo.GenePageVo;
import com.edu.vo.Pagination;
import com.edu.vo.PermissionVo;
import com.edu.vo.UserVo;


@Controller
@RequestMapping("/permission")
public class PermissionController {
Logger logger = LoggerFactory.getLogger(PermissionController.class);
	
	@Autowired
	public PermissionServiceI permissionService;
	
	@RequestMapping("/getAllPermissionPage.do")
	public String turn2Page(HttpServletRequest req){
		return "permission";
	}
	
	@RequestMapping(value = "/getAllPermission.do",method={RequestMethod.GET,RequestMethod.POST},consumes="application/json")
	@ResponseBody
	@Log(operationName="查看权限列表",operationType="getAllPermission操作")
	public GenePageVo getAllPermission(@RequestBody PermissionVo permissionVo){
		final GenePageVo<Permission> gv = new GenePageVo<Permission>();
		int count = permissionService.getAllPermissionCount(permissionVo);
		permissionVo.setTotalCount(count);
		Pagination p = new Pagination();
		BeanUtils.copyProperties(permissionVo, p);
		List<Permission> list= permissionService.getAllPermission(permissionVo);
		gv.setList(list);
		gv.setPage(p);
		return gv;
	}
	
	@RequestMapping(value="/addPermission.do",method = RequestMethod.POST)
	public ModelAndView addPermission(PermissionVo permission){
		ModelAndView mav = new ModelAndView();
		Permission p = new Permission();
		BeanUtils.copyProperties(permission,p);
		String id = permission.getId();
		int result = 0;
		if(StringUtils.isEmpty(id)){
			p.setId(UUID.randomUUID().toString().replaceAll("-", ""));
			result = permissionService.addPermission(p);
		}else{
			result = permissionService.update(p);
		}
		if(result==1){
			mav.addObject("msg", "操作成功！");
		}else{
			mav.addObject("msg", "操作失败！");
		}
		 mav.setViewName("redirect:/permission/getAllPermissionPage.do");
		return mav;
	}
	
	@ResponseBody
	@RequestMapping(value="/delete.do",method = RequestMethod.POST,consumes="application/json")
	public int delete(@RequestBody String ids){
		String[] array = ids.split(",");
		int result = 0;
		for(int i = 0;i < array.length; i++){
			result = permissionService.deleteByPrimaryKey(array[i]);
		}
		return result;
	}
	
}
