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
import com.edu.domain.Role;
import com.edu.domain.User;
import com.edu.service.UserServiceI;
import com.edu.vo.GenePageVo;
import com.edu.vo.Pagination;
import com.edu.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {
	Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	public UserServiceI userService;
	
	@RequestMapping("/getAllUserPage.do")
	public String turn2Page(HttpServletRequest req){
		return "user";
	}
	
	@RequestMapping(value = "/getAllUser.do",method={RequestMethod.GET,RequestMethod.POST},consumes="application/json")
	@ResponseBody
	@Log(operationName="查看所有用户列表",operationType="archives操作")
	public GenePageVo getAllUser(@RequestBody UserVo userVo){
		final GenePageVo<User> gv = new GenePageVo<User>();
		int count = userService.getAllUserCount(userVo);
		userVo.setTotalCount(count);
		Pagination p = new Pagination();
		BeanUtils.copyProperties(userVo, p);
		List<User> list= userService.getAllUser(userVo);
		gv.setList(list);
		gv.setPage(p);
		return gv;
	}
	
	@RequestMapping(value="/addUser.do",method = RequestMethod.POST)
	public ModelAndView addUser(UserVo user){
		ModelAndView mav = new ModelAndView();
		User u = new User();
		BeanUtils.copyProperties(user,u);
		String id = user.getId();
		int result = 0;
		if(StringUtils.isEmpty(id)){
			u.setId(UUID.randomUUID().toString().replaceAll("-", ""));
			u.setCreatedate(new Date());
			result = userService.addUser(u);
		}else{
			result = userService.update(u);
		}
		if(result==1){
			mav.addObject("msg", "操作成功！");
		}else{
			mav.addObject("msg", "操作失败！");
		}
		 mav.setViewName("redirect:/user/getAllUserPage.do");
		return mav;
	}
	
	@ResponseBody
	@RequestMapping(value="/delete.do",method = RequestMethod.POST,consumes="application/json")
	public int delete(@RequestBody String ids){
		String[] array = ids.split(",");
		int result = 0;
		for(int i = 0;i < array.length; i++){
			result = userService.deleteByPrimaryKey(array[i]);
		}
		return result;
	}
	
	public String getUserByName(HttpServletRequest req){
		return "";
	}
}
