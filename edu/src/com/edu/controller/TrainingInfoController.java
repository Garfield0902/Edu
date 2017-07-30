package com.edu.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.edu.domain.Bmpjxx;
import com.edu.domain.Jsjbxx;
import com.edu.domain.Pxhd;
import com.edu.service.TrainingInfoServiceI;
import com.edu.service.impl.BmpjxxServiceImpl;
import com.edu.service.impl.JsjbxxServiceImpl;
import com.edu.vo.BmpjxxVo;
import com.edu.vo.GenePageVo;
import com.edu.vo.JsjbxxVo;
import com.edu.vo.Pagination;
import com.edu.vo.PxhdVo;
import com.edu.vo.SignUpVo;

@Controller
@RequestMapping("/trainingInfo")
public class TrainingInfoController {
	
	@Autowired
	TrainingInfoServiceI trainingInfo;
	@Autowired
	JsjbxxServiceImpl jsjbxxServiceImp; 
	@Autowired
	BmpjxxServiceImpl bmpjxxServiceImp;
	@RequestMapping("/trainingInfoPage")
	private String trainingInfoPage(){
		return "trainingInfo";
	}
	@RequestMapping("/activityManagementPage.do")
	private String activityManagementPage(){
		return "activityManagement";
	}
	@ResponseBody
	@RequestMapping(value="/getAllPxhd.do",method = RequestMethod.POST,consumes="application/json")
	private GenePageVo getAllPxhd(@RequestBody PxhdVo pxhdVo){
		final GenePageVo<Pxhd> gv = new GenePageVo<Pxhd>();
		int count = trainingInfo.getAllPxhdCount(pxhdVo);
		pxhdVo.setTotalCount(count);
		Pagination p = new Pagination();
		BeanUtils.copyProperties(pxhdVo, p);
		List<Pxhd> list= trainingInfo.getAllPxhd(pxhdVo);
		gv.setList(list);
		gv.setPage(p);
		return gv;
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
	private GenePageVo getAllBm(@RequestBody PxhdVo pxhdVo){
		final GenePageVo<Bmpjxx> gv = new GenePageVo<Bmpjxx>();
		int count = trainingInfo.getAllPxhdCount(pxhdVo);
		pxhdVo.setTotalCount(count);
		Pagination p = new Pagination();
		BeanUtils.copyProperties(pxhdVo, p);
		List<Bmpjxx> list= trainingInfo.getAllBm(pxhdVo);
		gv.setList(list);
		gv.setPage(p);
		return gv;
	}
	@ResponseBody
	@RequestMapping(value="/cancelSignUp.do",method = RequestMethod.POST,consumes="application/json")
	private int cancelSignUp(@RequestBody String id){
		return trainingInfo.cancelSignUp(id);
	}
	@RequestMapping(value="/delete.do",method = RequestMethod.POST,consumes="application/json")
	private int delete(@RequestBody String ids){
		String[] array = ids.split(",");
		int result = 0;
		for(int i = 0;i < array.length; i++){
			result = trainingInfo.delete(array[i]);
		}
		return result;
	}
	@RequestMapping(value="/addPxhd.do",method = RequestMethod.POST)
	private ModelAndView addPxhd(Pxhd pxhd){
		ModelAndView mav = new ModelAndView();
		String hdid = pxhd.getHdid();
		int result = 0;
		if(hdid == ""){
			pxhd.setHdid(UUID.randomUUID().toString().replaceAll("-", ""));
			pxhd.setDqcyrs(0);
			result = trainingInfo.insert(pxhd);
		}else{
			result = trainingInfo.updateByPrimaryKeySelective(pxhd);
		}
		if(result==1){
			mav.addObject("msg", "操作成功！");
		}else{
			mav.addObject("msg", "操作失败！");
		}
		 mav.setViewName("redirect:/trainingInfo/activityManagementPage.do");
		return mav;
	}
	@RequestMapping(value="/selectTrainingInfoById.do",method = RequestMethod.POST)
	@ResponseBody
	private Pxhd selectTrainingInfoById(@RequestBody String hdid){
		return trainingInfo.selectByPrimaryKey(hdid);
	}
	@ResponseBody
	@RequestMapping(value="/getBmById.do",method = RequestMethod.POST,consumes="application/json")
	private GenePageVo getBmById(@RequestBody BmpjxxVo bmpjxxVo){

		final GenePageVo<Bmpjxx> gv = new GenePageVo<Bmpjxx>();
		int count = bmpjxxServiceImp.getAllJsjbxxCount(bmpjxxVo);
		bmpjxxVo.setTotalCount(count);
		Pagination p = new Pagination();
		BeanUtils.copyProperties(bmpjxxVo, p);
		List<Bmpjxx> list= bmpjxxServiceImp.getAllBmById(bmpjxxVo);
		gv.setList(list);
		gv.setPage(p);
		return gv;
	}
	@RequestMapping(value="/bmDelete.do",method = RequestMethod.POST,consumes="application/json")
	private int bmDelete(@RequestBody String ids){
		String[] array = ids.split(",");
		int result = 0;
		for(int i = 0;i < array.length; i++){
			result = bmpjxxServiceImp.deleteByPrimaryKey(array[i]);
		}
		return result;
	}
	
}
