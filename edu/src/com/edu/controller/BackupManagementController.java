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
@RequestMapping("/backupManagement")
public class BackupManagementController {
	Logger logger = LoggerFactory.getLogger(BackupManagementController.class);
	
	
	@RequestMapping(value = "/backupManagementPage.do")
	public String backupManagementPage(HttpServletRequest request){
		return "backupManagement";
	}
}

