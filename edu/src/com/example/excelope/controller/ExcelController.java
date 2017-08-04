package com.example.excelope.controller;

import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.edu.util.DateUtils;
import com.edu.util.excel.ExcelUtil;
import com.edu.util.excel.SheetContent;
import com.example.excelope.entity.UserEntity;
import com.example.excelope.entity.UserVo;
import com.example.excelope.service.UserService;

@Controller
@RequestMapping(value="/excel")
public class ExcelController {
	@Autowired
	private UserService service;
	
	@ResponseBody
	@RequestMapping(value = "/export.do", method = { RequestMethod.GET,RequestMethod.POST },produces = "text/html;charset=UTF-8")
	public String exportUserInfo(HttpServletRequest request, HttpServletResponse response,UserVo userVo){
		String msg = "";
		try {
			//创建表文件名
		    String fileName="用户信息导出"+DateUtils.currentDate();
			List<UserEntity> list = service.searchUserInfo(userVo);
			
			//此对象用户各个封装并返回，单个sheet内容默认不能超过65533,多处部分,导出时候程序自行截断
	    	Workbook hssfWorkbook2=service.getexcelData(list);
	    	ExcelUtil.writeExcelToResponse(request, response, fileName, hssfWorkbook2);
	 		msg = "导出成功！";
		} catch (Exception e) {
			e.printStackTrace();
			msg="ERROR:"+e.getMessage();
		}
	 	return msg;
	}
	
	@RequestMapping(value = "/import.do", method = { RequestMethod.GET,RequestMethod.POST },produces = "text/html;charset=UTF-8")
	public String importUserInfo(HttpServletRequest request, HttpServletResponse response,UserVo userVo){
		String msg = "";
		try {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;    
	        System.out.println("通过传统方式form表单提交方式导入excel文件！");  
	          
	        InputStream in =null;  
	        List<List<Object>> listob = null;  
	        MultipartFile file = multipartRequest.getFile("upfile");  
	        if(file.isEmpty()){
	            throw new Exception("文件不存在！");  
	        }
	        in = file.getInputStream();
	        List<SheetContent> wk = ExcelUtil.getExcelInfo(in, file.getOriginalFilename());
	        
	        for(SheetContent sc : wk){
	        	String sheetName = sc.getSheetName();
	        	Map<Object, List<Object>> map = sc.getSheetContentMap();
	        	Set<Object> keys = map.keySet();
	        	Iterator<Object> it = keys.iterator();
	        	while(it.hasNext()){
	        		Object key = it.next();
	        		List<Object> list = map.get(key);
	        		System.out.println(list);
	        	}
	        }
		} catch (Exception e) {
			e.printStackTrace();
			msg="ERROR:"+e.getMessage();
		}
	 	return msg;
	}
	
}
