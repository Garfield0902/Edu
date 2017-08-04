package com.edu.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edu.component.Log;
import com.edu.component.entity.SystemLog;
import com.edu.component.service.SystemLogService;
import com.edu.vo.GenePageVo;
import com.edu.vo.Pagination;
import com.edu.vo.PxhddaVo;
import com.edu.vo.SystemLogVo;

@Controller
@RequestMapping("/systemLog")
public class SystemLogController {
	Logger logger = LoggerFactory.getLogger(SystemLogController.class);
	
	@Autowired
	private SystemLogService service;
	
	@RequestMapping(value = "/systemLogPage.do")
	public String systemLogPage(HttpServletRequest request){
		return "systemLog";
	}
	
	@RequestMapping(value = "/getAllSystemLog.do",method={RequestMethod.GET,RequestMethod.POST},consumes="application/json")
	@ResponseBody
	@Log(operationName="查看培训档案",operationType="archives操作")
	public GenePageVo getAllSystemLog(@RequestBody SystemLogVo systemLogVo){
		final GenePageVo<SystemLog> gv = new GenePageVo<SystemLog>();
		int count = service.getAllSystemLogCount(systemLogVo);
		systemLogVo.setTotalCount(count);
		
		Pagination p = new Pagination();
		BeanUtils.copyProperties(systemLogVo, p);
		List<SystemLog> list= service.getSystemLog(systemLogVo);
		gv.setList(list);
		gv.setPage(p);
		return gv;
	}
	
}

