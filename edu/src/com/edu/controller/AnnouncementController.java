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

@RequestMapping("/announcement")
@Controller
public class AnnouncementController {
	
	@Autowired
	AnnouncementServiceI announcement;
	
	
	@RequestMapping("/announcementPage.do")
	private String announcementPage(){
		return "announcement";
	}
	@RequestMapping("/announcementManagePage.do")
	private String announcementManagePage(){
		return "announcementManage";
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
	@ResponseBody
	@RequestMapping(value="/delete.do",method = RequestMethod.POST,consumes="application/json")
	private int cancelSignUp(@RequestBody String tzggh){
		return announcement.deleteByPrimaryKey(tzggh);
	}
	@RequestMapping(value="/addTzgg.do",method = RequestMethod.POST)
	private ModelAndView addTzgg(Tzgg tzgg){
		ModelAndView mav = new ModelAndView();
		String tzggh = tzgg.getTzggh();
		int result = 0;
		if(tzggh == ""){
			tzgg.setTzggh(UUID.randomUUID().toString().replaceAll("-", ""));
			tzgg.setTzggsj(new Date());
			tzgg.setCreateAt(new Date());
			tzgg.setCreateBy("");
			result = announcement.insert(tzgg);
		}else{
			tzgg.setUpdateAt(new Date());
			result = announcement.updateByPrimaryKeySelective(tzgg);
		}
		if(result==1){
			mav.addObject("msg", "操作成功！");
		}else{
			mav.addObject("msg", "操作失败！");
		}
		 mav.setViewName("redirect:/announcement/announcementManagePage.do");
		return mav;
	}
	@ResponseBody
	@RequestMapping(value="/selectAnnouncementById.do",method = RequestMethod.POST)
	private Tzgg selectAnnouncementById(@RequestBody String tzggh){
		return announcement.selectByPrimaryKey(tzggh);
	}
}

