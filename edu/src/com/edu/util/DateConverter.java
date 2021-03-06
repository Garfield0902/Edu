package com.edu.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

/**
 * 全局日期处理类
 * Convert<T,S>
 *         泛型T:代表客户端提交的参数 String
 *         泛型S:通过convert转换的类型
   
 */
public class DateConverter implements Converter<String, Date> {

    @Override
    public Date convert(String stringDate) {
    	if(StringUtils.isEmpty(stringDate)){
    		return null;
    	}
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            return simpleDateFormat.parse(stringDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

}