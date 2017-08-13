package com.edu.controller;

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

import com.edu.domain.Jsjbxx;
import com.edu.service.JsjbxxService;
import com.edu.vo.GenePageVo;
import com.edu.vo.GeneVo;
import com.edu.vo.JsjbxxVo;
import com.edu.vo.Pagination;
import com.edu.vo.TjxfVo;

@Controller
@RequestMapping("/jsjbxx")
public class JsjbxxController {
	Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private JsjbxxService service;
	
	@ResponseBody
	@RequestMapping(value="/getJsjbxxInfo.do",method = RequestMethod.POST,consumes="application/json")
	public GeneVo getJsjbxxInfo(JsjbxxVo js,HttpServletRequest req){
		logger.debug("当前用户信息");

		final GeneVo<Jsjbxx> gv = new GeneVo<Jsjbxx>();
		String zgh = "11";//(String)req.getSession().getAttribute("zgh");
		
		if(StringUtils.isEmpty(zgh)){
			gv.setMsg("当前用户职工号为空，查看失败！");
			gv.setCode("400");
			return gv;
		}
		js.setZgh(zgh);
		boolean flag =getInfoValidateVo(gv,js);
		if(!flag){
			return gv;
		}
		Jsjbxx jsInfo= service.getJsjbxxInfo(js);
		gv.setBody(jsInfo);
		return gv;
	}
	
	@RequestMapping(value="/getAllJsjbxxPage.do")
	public String getAllJsjbxxPage(){
		return "teachermanage";
	}
	@RequestMapping(value="/myInfo.do")
	public String myinfo(){
		return "myinfo";
	}
	
	@ResponseBody
	@RequestMapping(value="/getAllJsjbxx.do",method = RequestMethod.POST,consumes="application/json")
	public GenePageVo getAllJsjbxx(@RequestBody  JsjbxxVo js){
		logger.debug("查询所有用户！");
		final GenePageVo<Jsjbxx> gv = new GenePageVo<Jsjbxx>();
		int count = service.getAlljsjbxxCount(js);
		js.setTotalCount(count);
		Pagination p = new Pagination();
		BeanUtils.copyProperties(js, p);
		
		List<Jsjbxx> list= service.getAllJsjbxx(js);
		gv.setList(list);
		gv.setPage(p);
		return gv;
	}
	
	@RequestMapping(value="/addJsxf.do",method = RequestMethod.POST)
	public String addJsxf(TjxfVo js){
		logger.debug("添加学分！");
		service.addJsxf(js);
		return "teachermanage";
	}
	
	//参数校验
	private boolean getInfoValidateVo(GeneVo<Jsjbxx> gv, JsjbxxVo js) {
		if(js==null){
			gv.setCode("400");
			gv.setMsg("参数为空！");
			return false;
		}
		if(StringUtils.isEmpty(js.getZgh())){
			gv.setCode("400");
			gv.setMsg("职工号参数为空，无法查询！");
			return false;
		}
		return true;
	}
}
