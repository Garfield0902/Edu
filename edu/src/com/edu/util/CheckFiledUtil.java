package com.edu.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

public class CheckFiledUtil {
	private static final Log log = LogFactory.getLog(CheckFiledUtil.class);
	
	public static final String I_TYPE="I";
	public static final String U_TYPE="U";
	//不检测的属性集合（属性字段名字集合），和 I_TYPE 搭配使用
	public Set<String> ignoreFieldSet = new HashSet<String>();
	//只检测的属性集合（属性字段名字集合），和U_TYPE 搭配使用
	public Set<String> onlyCheckField = new HashSet<String>();
	/**
	 * 检测 传过来的 数据的准确性工具方法
	 * @author zhangwc
	 * @Description: TODO
	 * @param @param br  BindingResult实体，封装了整个检测结果
	 * @param @param type 检测类型；I 可以和 ignoreField搭配，过滤掉某些不检测的属性； U 可以和onlyCheckFiled搭配，检测只需要检测的属性
	 * @param @return   
	 * @return GeneralVO  
	 * @date 2017-7-25
	 */
	public GeneralVO validateFieldVo(BindingResult br,String type){
		return validateFieldVo(br,type,ignoreFieldSet,onlyCheckField);
	}
	
    /**
     * 检测 传过来的 数据的准确性工具方法
     * @author zhangwc
     * @Description: TODO
     * @param @param br	BindingResult实体，封装了整个检测结果
     * @param @param type	检测类型；I 可以和 ignoreField搭配，过滤掉某些不检测的属性； U 可以和onlyCheckFiled搭配，检测只需要检测的属性
     * @param @param ignoreField 不检测的属性集合
     * @param @param onlyCheckField	只检测的属性集合
     * @param @return   返回为空的话说明检测通过，否则 返回检测错误的结果对象
     * @return GeneralVO  
     * @date 2017-7-25
     */
    public GeneralVO validateFieldVo(BindingResult br,String type,Set<String> ignoreField,Set<String> onlyCheckField) {
    	if(br.hasErrors()){
    		boolean flag =false;
    		String errorStr="";
    		List<Map<String,String>> lm= new ArrayList<Map<String,String>>();
            List<FieldError> list = br.getFieldErrors();  
            for (FieldError fieldError2 : list) {
            	String fName = fieldError2.getField();
            	String err = fieldError2.getDefaultMessage();
            	if(type.equalsIgnoreCase(I_TYPE)){
            		if(ignoreField!=null && ignoreField.contains(fName)){
            			continue;
            		}
            		Map tm = new HashMap<String,String>();
                	tm.put(fName, err);
                	lm.add(tm);
                	errorStr += fName+":"+err+";";
                	log.debug("fName: "+fName+"  "+fieldError2.getDefaultMessage());  
                	flag = true;
            	}
            	if(type.equalsIgnoreCase(U_TYPE)){
            		if(onlyCheckField!=null && onlyCheckField.contains(fName)){
            			Map tm = new HashMap<String,String>();
                    	tm.put(fName, err);
                    	lm.add(tm);
                    	errorStr += fName+":"+err+";";
                    	log.debug("fName: "+fName+"  "+fieldError2.getDefaultMessage());  
            			flag = true;
            		}
            	}
            }
            if(flag){
	            GeneralVO gv = new GeneralVO(ErrorListEnum.E30001.ordinal(),errorStr,lm);
	            return gv;
            }
    	}
    	return null;
	}
}
