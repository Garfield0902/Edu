package com.edu.controller;

import java.util.Calendar;
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

import com.edu.component.Log;
import com.edu.service.BmpjxxService;
import com.edu.vo.BmpjxxVo;
import com.edu.vo.GenePageVo;
import com.edu.vo.Pagination;
import com.edu.vo.PxhddaVo;

@Controller
@RequestMapping("/bmpjxx")
public class BmpjxxController {
	Logger logger = LoggerFactory.getLogger(BmpjxxController.class);
	
	@Autowired
	private BmpjxxService service;
	
	@RequestMapping(value = "/archives.do")
	@Log(operationName="跳转培训档案界面",operationType="界面跳转操作")
	public String archives(HttpServletRequest request){
		return "trainarchives";
	}
	
	@RequestMapping(value = "/allArchives.do",method={RequestMethod.GET,RequestMethod.POST},consumes="application/json")
	@ResponseBody
	@Log(operationName="查看培训档案",operationType="archives操作")
	public GenePageVo allArchives(@RequestBody BmpjxxVo bv){
		logger.debug("查询所有用户！");
		String searchType = bv.getSearchType();
		if(StringUtils.isEmpty(searchType)){
			logger.error("档案搜索条件类型为空，无法搜索！");
			return null;
		}
		if(bv.getPjnf()!=null&&bv.getPjnf().intValue()==-1){
			bv.setPjnf(null);
		}
		final GenePageVo<PxhddaVo> gv = new GenePageVo<PxhddaVo>();
		int count = service.getAllJsjbxxCount(bv);
		bv.setTotalCount(count);
		
		Pagination p = new Pagination();
		BeanUtils.copyProperties(bv, p);
		List<PxhddaVo> list= service.getAllJsjbxx(bv);
		gv.setList(list);
		gv.setPage(p);
		return gv;
	}
	
}
