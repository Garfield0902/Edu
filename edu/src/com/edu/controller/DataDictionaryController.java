package com.edu.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.edu.cache.CacheManager;
import com.edu.common.SelectData;
import com.edu.common.entity.SelectEntity;
import com.edu.domain.Datadictionary;
import com.edu.service.DataDictionaryServiceI;
import com.edu.vo.DataDictionaryVo;
import com.edu.vo.GenePageVo;
import com.edu.vo.Pagination;
import com.edu.vo.SelectVo;

@RequestMapping("/dataDictionary")
@Controller
public class DataDictionaryController {
	
	@Autowired
    public CacheManager cacheManager;
	@Autowired
	DataDictionaryServiceI dataDictionaryService;
	@Autowired
	SelectData selectData;
	
	@RequestMapping("/dataDictionaryPage.do")
	public ModelAndView dataDictionaryPage(){
		ModelAndView mav = new ModelAndView();
		//从缓存中将下拉框数据类型拿出来；
		List<Map<String,Object>> list = cacheManager.getCacheData("edu_dbo_sys_datadictionary");
    	List<SelectEntity> sv = new ArrayList<SelectEntity>();
		for(Map<String,Object> m :list){
			String id = (String) m.get("id");
    		String key = (String) m.get("name");
    		String value = (String) m.get("value");
    		String type = (String) m.get("type");
    		Integer order = (Integer) m.get("orderData");
    		if("-1".equals(value)){
//    			SelectVo s = new SelectVo(name,value,type);
//    			sv.add(s);
    			SelectEntity se = new SelectEntity(id, key, value);
    			se.setType(type);
    			se.setOrder(order);
    			sv.add(se);
    		}
    	}
    	mav.addObject("dictype",sv);
		mav.setViewName("dataDictionary");
		return mav;
	}
	@ResponseBody
	@RequestMapping(value="/getAllDatadictionary.do",method = RequestMethod.POST,consumes="application/json")
	public GenePageVo getAllDatadictionary(@RequestBody DataDictionaryVo dataDictionaryVo){
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
	public ModelAndView addDataDictionary(Datadictionary datadictionary){
		ModelAndView mav = new ModelAndView();
		String id = datadictionary.getId();
		int result = 0;
		
		if(StringUtils.isEmpty(id)){
			datadictionary.setId(UUID.randomUUID().toString().replaceAll("-", ""));
			result = dataDictionaryService.insert(datadictionary);
		}else{
			result = dataDictionaryService.updateByPrimaryKeySelective(datadictionary);
		}
		if(result==1){
			mav.addObject("msg", "操作成功！");
			//刷新缓存
			selectData.reloadCache();
		}else{
			mav.addObject("msg", "操作失败！");
		}
		 mav.setViewName("redirect:/dataDictionary/dataDictionaryPage.do");
		return mav;
	}
	@ResponseBody
	@RequestMapping(value="/delete.do",method = RequestMethod.POST,consumes="application/json")
	public int delete(@RequestBody String ids){
		String[] array = ids.split(",");
		int result = 0;
		for(int i = 0;i < array.length; i++){
			result = dataDictionaryService.deleteByPrimaryKey(array[i]);
		}
		//刷新缓存
		selectData.reloadCache();
		return result;
	}
	@RequestMapping(value="/selectDataDictionaryById.do",method = RequestMethod.POST)
	@ResponseBody
	public Datadictionary selectDictionaryById(@RequestBody String id){
		return dataDictionaryService.selectByPrimaryKey(id);
	}
	/**
	 * 通过ajax请求 去获取下拉框的内容
	 * @author zhangwc
	 * @Description: TODO
	 * @param @param id
	 * @param @return   
	 * @return Datadictionary  
	 * @date 2017-8-4
	 */
	@RequestMapping(value="/selectDataDictionaryByType.do",method = RequestMethod.POST)
	@ResponseBody
	public List<SelectEntity> selectDataDictionaryByType(@RequestBody String type){
		//刷新缓存
		List<SelectEntity> lt = selectData.getSelectByType(type,false);
		return lt;
	}
	
	/**
	 * 通过ajax请求 去获取下拉框的内容
	 * @author zhangwc
	 * @Description: TODO
	 * @param @param id
	 * @param @return   
	 * @return Datadictionary  
	 * @date 2017-8-4
	 */
	@RequestMapping(value="/getSelectType.do",method = RequestMethod.POST)
	@ResponseBody
	public List<SelectEntity> getSelectType(@RequestBody String type){
		//刷新缓存
		List<SelectEntity> lt = selectData.getSelectType();
		return lt;
	}
}
