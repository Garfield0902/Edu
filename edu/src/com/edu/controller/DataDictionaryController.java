package com.edu.controller;

import java.util.Date;
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

import com.edu.domain.Datadictionary;
import com.edu.domain.Pxhd;
import com.edu.service.DataDictionaryServiceI;
import com.edu.vo.DataDictionaryVo;
import com.edu.vo.GenePageVo;
import com.edu.vo.Pagination;

@RequestMapping("/dataDictionary")
@Controller
public class DataDictionaryController {
	
	@Autowired
	DataDictionaryServiceI dataDictionaryService;
	
	
	@RequestMapping("/dataDictionaryPage.do")
	private String dataDictionaryPage(){
		return "dataDictionary";
	}
	@ResponseBody
	@RequestMapping(value="/getAllDatadictionary.do",method = RequestMethod.POST,consumes="application/json")
	private GenePageVo getAllDatadictionary(@RequestBody DataDictionaryVo dataDictionaryVo){
		final GenePageVo<Datadictionary> gv = new GenePageVo<Datadictionary>();
		int count = dataDictionaryService.getAllDataDictionaryCount(dataDictionaryVo);
		dataDictionaryVo.setTotalCount(count);
		Pagination p = new Pagination();
		BeanUtils.copyProperties(dataDictionaryVo, p);
		List<Datadictionary> list= dataDictionaryService.getAllDataDictionary(dataDictionaryVo);
		gv.setList(list);
		gv.setPage(p);
		return gv;
	}
	@ResponseBody
	@RequestMapping(value="/addDataDictionary.do",method = RequestMethod.POST)
	private ModelAndView addDataDictionary(Datadictionary datadictionary){
		ModelAndView mav = new ModelAndView();
		String id = datadictionary.getId();
		int result = 0;
		if(id == ""){
			datadictionary.setId(UUID.randomUUID().toString().replaceAll("-", ""));
			result = dataDictionaryService.insert(datadictionary);
		}else{
			result = dataDictionaryService.updateByPrimaryKeySelective(datadictionary);
		}
		if(result==1){
			mav.addObject("msg", "操作成功！");
		}else{
			mav.addObject("msg", "操作失败！");
		}
		 mav.setViewName("redirect:/dataDictionary/dataDictionaryPage.do");
		return mav;
	}
	@RequestMapping(value="/delete.do",method = RequestMethod.POST,consumes="application/json")
	private int delete(@RequestBody String ids){
		String[] array = ids.split(",");
		int result = 0;
		for(int i = 0;i < array.length; i++){
			result = dataDictionaryService.deleteByPrimaryKey(array[i]);
		}
		return result;
	}
	@RequestMapping(value="/selectDataDictionaryById.do",method = RequestMethod.POST)
	@ResponseBody
	private Datadictionary selectDictionaryById(@RequestBody String id){
		return dataDictionaryService.selectByPrimaryKey(id);
	}
}
