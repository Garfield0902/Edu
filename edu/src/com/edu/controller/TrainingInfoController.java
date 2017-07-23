package com.edu.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edu.domain.Bmpjxx;
import com.edu.domain.Jsjbxx;
import com.edu.domain.Pxhd;
import com.edu.domain.Tzgg;
import com.edu.service.TrainingInfoServiceI;
import com.edu.service.impl.JsjbxxServiceImpl;
import com.edu.vo.AnnouncementVo;
import com.edu.vo.JsjbxxVo;
import com.edu.vo.Pagination;
import com.edu.vo.SignUpVo;
import com.edu.vo.TrainingInfoAnnouncementVo;

@Controller
@RequestMapping("/trainingInfo")
public class TrainingInfoController {
	
	@Autowired
	TrainingInfoServiceI trainingInfo;
	@Autowired
	JsjbxxServiceImpl jsjbxxServiceImp; 
	@RequestMapping("/trainingInfoPage")
	private String trainingInfoPage(){
		return "trainingInfo";
	}
	@ResponseBody
	@RequestMapping(value="/getAllPxhd.do",method = RequestMethod.POST,consumes="application/json")
	private TrainingInfoAnnouncementVo<Pxhd> getAllPxhd(@RequestBody Pagination pagination){

		TrainingInfoAnnouncementVo<Pxhd> trainingInfoAnnouncementVo=new TrainingInfoAnnouncementVo<Pxhd>();
		trainingInfoAnnouncementVo.setPage(pagination);
		List<Pxhd> list = trainingInfo.getAllPxhd(trainingInfoAnnouncementVo);
		trainingInfoAnnouncementVo.setList(list);
		return trainingInfoAnnouncementVo;
	}
	@ResponseBody
	@RequestMapping(value="/signUp.do",method = RequestMethod.POST,consumes="application/json")
	private int signUp(@RequestBody Pxhd record){
		record.setRecordStatus(1);
		Bmpjxx bmp = new Bmpjxx();
		Jsjbxx jsj = new Jsjbxx();
		JsjbxxVo jsjbxxVo = new JsjbxxVo(); 
		jsjbxxVo.setZgh("11");
		jsj = jsjbxxServiceImp.getJsjbxxInfo(jsjbxxVo);
		bmp.setHdid(record.getHdid());
		bmp.setXymc(jsj.getXy());
		bmp.setZgh(jsj.getZgh());
		bmp.setXm(jsj.getXm());
		bmp.setBmbz(0);
		bmp.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		return trainingInfo.signUp(bmp);
	}
	@ResponseBody
	@RequestMapping(value="/getAllBm.do",method = RequestMethod.POST,consumes="application/json")
	private SignUpVo<Bmpjxx> getAllBm(@RequestBody Pagination pagination){
		SignUpVo<Bmpjxx> signUpVo=new SignUpVo<Bmpjxx>();
		signUpVo.setPage(pagination);
		List<Bmpjxx> list = trainingInfo.getAllBm(signUpVo);
		signUpVo.setList(list);
		return signUpVo;
	}
	@ResponseBody
	@RequestMapping(value="/cancelSignUp.do",method = RequestMethod.POST,consumes="application/json")
	private int cancelSignUp(@RequestBody String id){
		return trainingInfo.cancelSignUp(id);
	}
}
