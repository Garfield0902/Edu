package com.edu.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.edu.domain.Tzgg;
import com.edu.service.AnnouncementServiceI;
import com.edu.vo.AnnouncementVo;
import com.edu.vo.Pagination;

@RequestMapping("/analysis")
@Controller
public class AnalysisController {
	
	@Autowired
	AnnouncementServiceI announcement;
	
	
	@RequestMapping("/analysisPage.do")
	private String analysisPage(){
		return "analysis";
	}
	@ResponseBody
	@RequestMapping(value="/getAllTzgg.do",method = RequestMethod.POST,consumes="application/json")
	private AnnouncementVo<Tzgg> getAllTzgg(@RequestBody Pagination pagination){
		AnnouncementVo<Tzgg> announcementVo=new AnnouncementVo<Tzgg>();
		announcementVo.setPage(pagination);
		List<Tzgg> list = announcement.getAllTzgg(announcementVo);
		announcementVo.setList(list);
		return announcementVo;
	}
}

