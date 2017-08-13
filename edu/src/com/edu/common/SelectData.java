package com.edu.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.edu.cache.CacheManager;
import com.edu.common.entity.SelectEntity;

@Component
public class SelectData{
	
	@Autowired
    public CacheManager cacheManager;
	
	//下拉框类型
	public static Map<String,String> selectType= new HashMap<String,String>();
	/**
	 * 查询有几种下拉框类型
	 * @author zhangwc
	 * @Description: TODO
	 * @param @return   
	 * @return List<SelectEntity>  
	 * @date 2017-8-4
	 */
	public List<SelectEntity> getSelectType(){
		List<SelectEntity> list = new ArrayList<SelectEntity>();
		List<Map<String, Object>> lm =  cacheManager.getCacheData("edu_dbo_sys_datadictionary");
		for(Map<String,Object> m:lm){
			String id = (String) m.get("id");
			String key = (String) m.get("type");
			String value = (String) m.get("value");
			if(!hasExsit(key, list)){
				SelectEntity se = new SelectEntity(id,key,value);
				list.add(se);
			}
		}
		return list;
	}
	/**
	 * 根据下拉框类型查询 这种类型的所有下拉框数据
	 * @author zhangwc
	 * @Description: TODO
	 * @param @param type
	 * @param @return   
	 * @return List<SelectEntity>  
	 * @date 2017-8-4
	 */
	public List<SelectEntity> getSelectByType(String type){
		List<SelectEntity> list = new ArrayList<SelectEntity>();
		List<Map<String, Object>> lm =  cacheManager.getCacheData("edu_dbo_sys_datadictionary");
		for(Map<String,Object> m:lm){
			String id = (String) m.get("id");
			String key = (String) m.get("type");
			String value = (String) m.get("value");
			if(key.equals(type)){
				SelectEntity se = new SelectEntity(id,key,value);
				list.add(se);
			}
		}
		return list;
	}
	/**
	 * 刷新缓存，刷新 下拉框内容
	 * @author zhangwc
	 * @Description: TODO
	 * @param    
	 * @return void  
	 * @date 2017-8-4
	 */
	public void reloadCache(){
		cacheManager.reload();
	}
	private boolean hasExsit(String type,List<SelectEntity> list){
		boolean flag = false;
		if(list.size()==0){
			return flag;
		}
		for(SelectEntity se:list){
			String t = se.getKey();
			if(type.equals(t)){
				return true;
			}
		}
		return flag;
	}
}
