package com.edu.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
/**
 * 由于SimpleDateFormat这个类不是线程安全的，所以每次新new 一个来操作
 * @author zhangwc
 */
public class DateUtils {
	/**
	 * 获得当前时间皿code>java.util.Date</code>对象
	 * 
	 * @return
	 */
	public static Date now() {
		return new Date();
	}
	/**
	 * 获得当前日期时间
	 * <p>
	 * 日期时间格式yyyy-MM-dd HH:mm:ss
	 * 
	 * @return
	 */
	public static String currentDatetime() {
		SimpleDateFormat datetimeFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		return datetimeFormat.format(now());
	}
	
	/**
	 * 格式化日期时闿
	 * <p>
	 * 日期时间格式yyyy-MM-dd HH:mm:ss
	 * 
	 * @return
	 */
	public static String formatDatetime(Date date) {
		SimpleDateFormat datetimeFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		return datetimeFormat.format(date);
	}
	
	/**
	 * 获取日期时闿
	 * <p>
	 * 日期时间格式yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String currentDateformatDatetime() {
		SimpleDateFormat datetimeFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		return datetimeFormat.format(now());
	}
	/**
     * 方法名：getCurrentDateAlterHour
     * 描述：获取当前时间的前几个小时
     * @参数：   day  int   加减小时数
     * @返回值类型： String   
     * @创建时间：  2016-6-31
     * @创建者：陈金熠
     * @变更记录：2016-6-31上午11:02:15 by
     */
    public static String getCurrentDateTimeAlterHour(int hour) {
    	SimpleDateFormat datetimeFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
    	Calendar calendar = Calendar.getInstance();//此时打印它获取的是系统当前时间
        calendar.add(Calendar.HOUR, hour);    //得到前一个小时
        String  yestedayDate = datetimeFormat.format(calendar.getTime());
        return yestedayDate;
    }
    
    /**
	 * 将字符串日期时间转换成java.util.Date类型
	 * <p>
	 * 日期时间格式yyyy-MM-dd HH:mm:ss
	 * 
	 * @param datetime
	 * @return
	 */
	public static Date parseDatetime(String datetime) throws ParseException {
		SimpleDateFormat datetimeFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		return datetimeFormat.parse(datetime);
	}
	
	/**
	 * 根据自定义pattern将字符串日期转换成java.util.Date类型
	 * 
	 * @param datetime
	 * @param pattern
	 * @return
	 * @throws ParseException
	 */
	public static Date parseDatetime(String datetime, String pattern)
			throws ParseException {
		SimpleDateFormat datetimeFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat format = (SimpleDateFormat) datetimeFormat.clone();
		format.applyPattern(pattern);
		return format.parse(datetime);
	}
	/**
	 * TODO 获取前后半小时
	 * @author viva
	 * @date	2016-9-18
	 * @param min 分钟 ，正数为之后多少分钟，负数为之前多少分钟	
	 */
	public static String getDateMinuteAlter(int min) {
		SimpleDateFormat datetimeFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		Calendar nowTime = Calendar.getInstance();
		nowTime.add(Calendar.MINUTE, min);//30分钟后的时间
		return datetimeFormat.format(nowTime.getTime());
	}
	
	/**
	 * 日期格式化 yyyyMM
	 * @author zhangwc
	 * @date 2017-03-18
	 * @param date
	 * @return
	 */
	public static String yearDateFormatNoSep(Date date){
		SimpleDateFormat yearDayFormatNoSep = new SimpleDateFormat("yyyyMM");
		return yearDayFormatNoSep.format(date);
	}
	/**
	 * 格式化日期时闿
	 * 
	 * 日期时间格式yyyyMMdd
	 * @author zhanghan
	 * @date	2016-8-10
	 * @param date
	 * @return
	 */
	public static String formatNoSepDate(Date date) {
		SimpleDateFormat dateFormatNoSep = new SimpleDateFormat("yyyyMMdd");
		return dateFormatNoSep.format(date);
	}
	
	public static Date getCurrentDateBefore(int day) {
		Calendar calendar = Calendar.getInstance();//此时打印它获取的是系统当前时闿
        calendar.add(Calendar.DATE, day);    //得到前一夿
        return calendar.getTime();
	}
	
	public static String getCurrentDateAlter(String date,int day) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();//此时打印它获取的是系统当前时闿
		calendar.setTime(dateFormat.parse(date));
        calendar.add(Calendar.DATE, day);    //得到前一夿
        String  yestedayDate = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
		return yestedayDate;
	}
	
	/**
	 *判断原日期是否在目标日期之前
	 * 
	 * @param src
	 * @param dst
	 * @return
	 */
	public static boolean isBefore(Date src, Date dst) {
		return src.before(dst);
	}

	/**
	 *判断原日期是否在目标日期之后
	 * 
	 * @param src
	 * @param dst
	 * @return
	 */
	public static boolean isAfter(Date src, Date dst) {
		return src.after(dst);
	}
	
}
