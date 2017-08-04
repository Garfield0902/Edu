package com.edu.util;

/*
 *  Copyright  sunflower
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

/**
 * 日期时间工具籿
 * 
 * @author 张佳
 * 
 */
public class DateUtils {
	private static final SimpleDateFormat datetimeFormat = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	private static final SimpleDateFormat datetimestampFormat = new SimpleDateFormat(
	        "yyyy-MM-dd HH:mm:ss.SSS");
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy-MM-dd");
	private static final SimpleDateFormat timeFormat = new SimpleDateFormat(
			"HH:mm:ss");
	private static final String[] dayOfWeek = new String[]{"星期日","星期一","星期二","星期三","星期四","星期五","星期六"};	
	private static final SimpleDateFormat dateFormatNoSep = new SimpleDateFormat("yyyyMMdd");
	private static final SimpleDateFormat datetimeFormatNoSep = new SimpleDateFormat("yyyyMMdd HHmmss");
	
	private static final SimpleDateFormat solrFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
	
	/**
	 * 获得当前日期时间
	 * <p>
	 * 日期时间格式yyyy-MM-dd HH:mm:ss
	 * 
	 * @return
	 */
	public static String currentDatetime() {
		return datetimeFormat.format(now());
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
		return dateFormatNoSep.format(date);
	}

	/**
	 * 格式化日期时闿
	 * 
	 * 日期时间格式yyyy-MM-ddTHH:mm:ssZ
	 * @author zhanghan
	 * @date	2016-8-26
	 * @param date
	 * @return
	 */
	public static String formatSolrDatetime(Date date){
		return solrFormat.format(date);
	}
	
	/**
	 * 格式化日期时闿
	 * <p>
	 * 日期时间格式yyyy-MM-dd HH:mm:ss
	 * 
	 * @return
	 */
	public static String formatDatetime(Date date) {
		return datetimeFormat.format(date);
	}

	/**
	 * 获取日期时闿
	 * <p>
	 * 日期时间格式yyyy-MM-dd HH:mm:ss
	 * 
	 * @return
	 */
	public static String currentDateformatDatetime() {
		return datetimeFormat.format(now());
	}
	
	/**
	 * 格式化日期时闿
	 * 
	 * @param date
	 * @param pattern
	 *            格式化模式，详覺{@link SimpleDateFormat}构ꀥٿ
	 *            <code>SimpleDateFormat(String pattern)</code>
	 * @return
	 */
	public static String formatDatetime(Date date, String pattern) {
		SimpleDateFormat customFormat = (SimpleDateFormat) datetimeFormat
				.clone();
		customFormat.applyPattern(pattern);
		return customFormat.format(date);
	}

	/**
	 * 获得当前日期
	 * <p>
	 * 日期格式yyyy-MM-dd
	 * 
	 * @return
	 */
	public static String currentDate() {
		return dateFormat.format(now());
	}
	
	/**
	 * 获得当前日期
	 * <p>
	 * 日期格式yyyyMMdd
	 * @author zhanghan
	 * @return
	 */
	public static String currentDateNoSep() {
		return dateFormatNoSep.format(now());
	}
	
	/**
	 * 获得当前日期时间
	 * <p>
	 * 日期格式yyyyMMdd HHmmss
	 * @author zhanghan
	 * @return
	 */
	public static String currentDatetimeNoSep() {
		return datetimeFormatNoSep.format(now());
	}

	/**
	 * 格式化日朿
	 * <p>
	 * 日期格式yyyy-MM-dd
	 * 
	 * @return
	 */
	public static String formatDate(Date date) {
		return dateFormat.format(date);
	}

	/**
	 * 获得当前时间
	 * <p>
	 * 时间格式HH:mm:ss
	 * 
	 * @return
	 */
	public static String currentTime() {
		return timeFormat.format(now());
	}

	/**
	 * 格式化时闿
	 * <p>
	 * 时间格式HH:mm:ss
	 * 
	 * @return
	 */
	public static String formatTime(Date date) {
		return timeFormat.format(date);
	}

	/**
	 * 获得当前时间皿code>java.util.Date</code>对象
	 * 
	 * @return
	 */
	public static Date now() {
		return new Date();
	}

	public static Calendar calendar() {
		Calendar cal = GregorianCalendar.getInstance(Locale.CHINESE);
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		return cal;
	}

	/**
	 * 获得当前时间的毫秒数
	 * <p>
	 * 详覺{@link System#currentTimeMillis()}
	 * 
	 * @return
	 */
	public static long millis() {
		return System.currentTimeMillis();
	}

	/**
	 * 
	 * 获得当前Chinese月份
	 * 
	 * @return
	 */
	public static int month() {
		return calendar().get(Calendar.MONTH) + 1;
	}

	/**
	 * 获得月份中的第几夿
	 * 
	 * @return
	 */
	public static int dayOfMonth() {
		return calendar().get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 今天是星期的第几夿
	 * 
	 * @return
	 */
	public static int dayOfWeek() {
		return calendar().get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * 今天是年中的第几夿
	 * 
	 * @return
	 */
	public static int dayOfYear() {
		return calendar().get(Calendar.DAY_OF_YEAR);
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

	/**
	 *判断两日期是否相吿
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean isEqual(Date date1, Date date2) {
		return date1.compareTo(date2) == 0;
	}

	/**
	 * 判断某个日期是否在某个日期范囿
	 * 
	 * @param beginDate
	 *            日期范围弿ƾ
	 * @param endDate
	 *            日期范围结束
	 * @param src
	 *            霿Ơ判断的日朿
	 * @return
	 */
	public static boolean between(Date beginDate, Date endDate, Date src) {
		return beginDate.before(src) && endDate.after(src);
	}

	/**
	 * 获得当前月的朿Ў丿ĩ
	 * <p>
	 * HH:mm:ss丿，毫秒为999
	 * 
	 * @return
	 */
	public static Date lastDayOfMonth() {
		Calendar cal = calendar();
		cal.set(Calendar.DAY_OF_MONTH, 0); // M月置雿
		cal.set(Calendar.HOUR_OF_DAY, 0);// H置零
		cal.set(Calendar.MINUTE, 0);// m置零
		cal.set(Calendar.SECOND, 0);// s置零
		cal.set(Calendar.MILLISECOND, 0);// S置零
		cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + 1);// 月份+1
		cal.set(Calendar.MILLISECOND, -1);// 毫秝-1
		return cal.getTime();
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
        Calendar calendar = Calendar.getInstance();//此时打印它获取的是系统当前时间
        calendar.add(Calendar.HOUR, hour);    //得到前一个小时
        String  yestedayDate = datetimeFormat.format(calendar.getTime());
        return yestedayDate;
    }

	/**
	 * 获得当前月的第一夿
	 * <p>
	 * HH:mm:ss SS为零
	 * 
	 * @return
	 */
	public static Date firstDayOfMonth() {
		Calendar cal = calendar();
		cal.set(Calendar.DAY_OF_MONTH, 1); // M月置1
		cal.set(Calendar.HOUR_OF_DAY, 0);// H置零
		cal.set(Calendar.MINUTE, 0);// m置零
		cal.set(Calendar.SECOND, 0);// s置零
		cal.set(Calendar.MILLISECOND, 0);// S置零
		return cal.getTime();
	}

	private static Date weekDay(int week) {
		Calendar cal = calendar();
		cal.set(Calendar.DAY_OF_WEEK, week);
		return cal.getTime();
	}

	/**
	 * 获得周五日期
	 * <p>
	 * 注：日历工厂方法{@link #calendar()}设置类每个星期的第一天为Monday，US等每星期第一天为sunday
	 * 
	 * @return
	 */
	public static Date friday() {
		return weekDay(Calendar.FRIDAY);
	}

	/**
	 * 获得周六日期
	 * <p>
	 * 注：日历工厂方法{@link #calendar()}设置类每个星期的第一天为Monday，US等每星期第一天为sunday
	 * 
	 * @return
	 */
	public static Date saturday() {
		return weekDay(Calendar.SATURDAY);
	}

	/**
	 * 获得周日日期
	 * <p>
	 * 注：日历工厂方法{@link #calendar()}设置类每个星期的第一天为Monday，US等每星期第一天为sunday
	 * 
	 * @return
	 */
	public static Date sunday() {
		return weekDay(Calendar.SUNDAY);
	}
	
	public static String getFirstWorkDate() {
		return dateFormat.format(weekDay(Calendar.MONDAY));
	}
	
	public static String getLastWorkDate() {
		return dateFormat.format(weekDay(Calendar.FRIDAY));
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
		return datetimeFormat.parse(datetime);
	}

	/**
	 * 将字符串日期转换成java.util.Date类型
	 *<p>
	 * 日期时间格式yyyy-MM-dd
	 * 
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static Date parseDate(String date) throws ParseException {
		return dateFormat.parse(date);
	}

	/**
	 * 将字符串日期转换成java.util.Date类型
	 *<p>
	 * 时间格式 HH:mm:ss
	 * 
	 * @param time
	 * @return
	 * @throws ParseException
	 */
	public static Date parseTime(String time) throws ParseException {
		return timeFormat.parse(time);
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
		SimpleDateFormat format = (SimpleDateFormat) datetimeFormat.clone();
		format.applyPattern(pattern);
		return format.parse(datetime);
	}
	
	/**
	 * 方法名：getCurrentDateAlter
	 * 描述：获取当前日期的前几天䀥Ў几夿
	 * @参数＿  day  int   加减天数
	 * @返回值类型： String   
	 * @创建时间＿ 2014-7-23 
	 * @创建者：陈威桿
	 * @变更记录＿014-7-23上午11:02:15 by
	 */
	public static String getCurrentDateAlter(int day) {
		Calendar calendar = Calendar.getInstance();//此时打印它获取的是系统当前时闿
        calendar.add(Calendar.DATE, day);    //得到前一夿
        String  yestedayDate = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
		return yestedayDate;
	}
	
	/**
	 * TODO 获取当前日期的前几天后几天
	 * @author zhanghan
	 * @date	2016-10-12
	 * @param day 加减天数
	 * @return 日期时间格式yyyy-MM-dd HH:mm:ss
	 */
	public static String getCurrentDateTimeAlter(int day) {
		Calendar calendar = Calendar.getInstance();//此时打印它获取的是系统当前时闿
        calendar.add(Calendar.DATE, day);    //得到前一夿
        String  yestedayDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime());
		return yestedayDate;
	}

	
	/**
	 * 获取当前日期的前几天、后几天
	 * @author zhanghan
	 * @date	2016-8-10
	 * @param day  加减天数
	 * @return
	 */
	public static String getCurrentDateAlterNoSep(int day) {
		Calendar calendar = Calendar.getInstance();//此时打印它获取的是系统当前时闿
		calendar.add(Calendar.DATE, day);    //得到前一夿
		return dateFormatNoSep.format(calendar.getTime());
	}
	
	public static Date getCurrentDateBefore(int day) {
		Calendar calendar = Calendar.getInstance();//此时打印它获取的是系统当前时闿
        calendar.add(Calendar.DATE, day);    //得到前一夿
        return calendar.getTime();
	}
	
	public static String getCurrentDateAlter(String date,int day) throws ParseException {
		Calendar calendar = Calendar.getInstance();//此时打印它获取的是系统当前时闿
		calendar.setTime(dateFormat.parse(date));
        calendar.add(Calendar.DATE, day);    //得到前一夿
        String  yestedayDate = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
		return yestedayDate;
	}
	
	public static String dayOfWeekInChines(){
		int dayOfWeek = DateUtils.dayOfWeek();
		return DateUtils.dayOfWeek[dayOfWeek-1];
	}
	
	public static String getWeek(String date) throws ParseException{
		Calendar cal = Calendar.getInstance();
		cal.setTime(dateFormat.parse(date));
		int week_index = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if(week_index<0){
			week_index = 0;
		}
		return dayOfWeek[week_index];
	}
	
	public static List<String> getFiveWorkDayFromTody(String date1,String date2) throws ParseException{
		List<String> resList = new ArrayList<String>();
		if(date1==null){
			date1 = DateUtils.getCurrentDateAlter(-7);
		}
		
		Date startDate = dateFormat.parse(date1);
		if(date2==null){
			date2 = DateUtils.currentDate();
		}
		Date today = dateFormat.parse(date2);
		while (!startDate.after(today)) {
			String week = getWeek(dateFormat.format(startDate));
			if (!(dayOfWeek[0].equals(week)||dayOfWeek[6].equals(week))) {
				resList.add(week);
			}
			Calendar cal = Calendar.getInstance();
			cal.setTime(startDate);
			cal.add(Calendar.DAY_OF_MONTH, 1);
			startDate = cal.getTime();
		}
		return resList;
	}
	
	public static List<String> getFiveWorkDayStrFromTody(String date1,String date2) throws ParseException{
		List<String> resList = new ArrayList<String>();
		if(date1==null){
			date1 = DateUtils.getCurrentDateAlter(-7);
		}
		Date startDate = dateFormat.parse(date1);
		if(date2==null){
			date2 = DateUtils.currentDate();
		}
		Date today = dateFormat.parse(date2);
		while (!startDate.after(today)) {
			String week = getWeek(dateFormat.format(startDate));
			if (!(dayOfWeek[0].equals(week)||dayOfWeek[6].equals(week))) {
				resList.add(dateFormat.format(startDate));
			}
			Calendar cal = Calendar.getInstance();
			cal.setTime(startDate);
			cal.add(Calendar.DAY_OF_MONTH, 1);
			startDate = cal.getTime();
		}
		return resList;
	}
	
	/**  
     * 计算两个日期之间相差的天敿 
     * @param smdate 较小的时闿
     * @param bdate  较大的时闿
     * @return 相差天数 
     * @throws ParseException  
     */    
    public static int daysBetween(String startDateStr,String endDateStr) throws ParseException    
    {    
    	Date startDate = dateFormat.parse(startDateStr);
    	Date endDate = dateFormat.parse(endDateStr);
            
       return daysBetween(startDate,endDate);           
    } 
    
	/**  
     * 计算两个日期之间相差的天敿 
     * @param smdate 较小的时闿
     * @param bdate  较大的时闿
     * @return 相差天数 
     * @throws ParseException  
     */    
    public static int daysBetween(Date startDate,Date endDate) throws ParseException    
    {    
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
        startDate=sdf.parse(sdf.format(startDate));  
        endDate=sdf.parse(sdf.format(endDate));  
        Calendar cal = Calendar.getInstance();    
        cal.setTime(startDate);    
        long time1 = cal.getTimeInMillis();                 
        cal.setTime(endDate);    
        long time2 = cal.getTimeInMillis();         
        long between_days=(time2-time1)/(1000*3600*24);  
            
       return Integer.parseInt(String.valueOf(between_days));           
    } 
    
    public static String getWeekOfNum(String date) throws ParseException{
		Calendar cal = Calendar.getInstance();
		cal.setTime(dateFormat.parse(date));
		int week_index = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if(week_index<0){
			week_index = 0;
		}
		return ""+ week_index;
	}
    
    /**
     * 老外习惯,获取上周第一天(周日)
     * @author zhanghan
     * @date	2016-8-10
     * @param date 传入当前日期
     * @return	返回类型yyyy-MM-dd
     * @throws ParseException
     */
    public static String getLastSunday(String date) throws ParseException{
   	 Calendar calendar = Calendar.getInstance();//此时打印它获取的是系统当前时闿
   	 calendar.setTime(dateFormat.parse(date));
        calendar.add(Calendar.DAY_OF_WEEK_IN_MONTH, -1);    //得到前一夿
        calendar.set(Calendar.DAY_OF_WEEK,Calendar.SUNDAY);
        return formatDate(calendar.getTime());
   }

    /**
     * 老外习惯,获取上周第一天(周日)
     * @author zhanghan
     * @date	2016-8-10
     * @param date 传入当前日期
     * @return	返回类型yyyyMMdd
     * @throws ParseException
     */
    public static String getLastSundayNoSepDate(String date) throws ParseException{
    	Calendar calendar = Calendar.getInstance();//此时打印它获取的是系统当前时闿
    	calendar.setTime(dateFormat.parse(date));
    	calendar.add(Calendar.DAY_OF_WEEK_IN_MONTH, -1);    //得到前一夿
    	calendar.set(Calendar.DAY_OF_WEEK,Calendar.SUNDAY);
    	return formatNoSepDate(calendar.getTime());
    }
    
    /**
     * 老外习惯,获取上周第一天(周日)
     * @author zhanghan
     * @date	2016-8-10
     * @param date 传入当前日期
     * @return	返回类型yyyy-MM-dd
     * @throws ParseException
     */
    public static String getLastSunDay(String date) throws ParseException {
    	Calendar calendar = Calendar.getInstance();//此时打印它获取的是系统当前时闿
   	 	calendar.setTime(dateFormat.parse(date));	
        calendar.add(Calendar.DAY_OF_WEEK_IN_MONTH, -1);
        calendar.set(Calendar.DAY_OF_WEEK,Calendar.SUNDAY);
        return formatDate(calendar.getTime());
    }
    
    /**
     * TODO 老外习惯,获取上几周第一天(周日)
     * @author zhanghan
     * @date	2016-9-9
     * @param date  
     * @param day  上几周
     * @return  返回类型yyyy-MM-dd
     * @throws ParseException
     */
    public static String getLastSunDay(String date,int day) throws ParseException {
    	Calendar calendar = Calendar.getInstance();//此时打印它获取的是系统当前时闿
   	 	calendar.setTime(dateFormat.parse(date));	
        calendar.add(Calendar.DAY_OF_WEEK_IN_MONTH, day);
        calendar.set(Calendar.DAY_OF_WEEK,Calendar.SUNDAY);
        return formatDate(calendar.getTime());
    }
    
    /**
     * TODO 老外习惯,获取上周最后一天(周六)
     * @author zhanghan
     * @date	2016-9-9
     * @param date
     * @return  返回类型yyyy-MM-dd
     * @throws ParseException
     */
    public static String getLastSaturday(String date) throws ParseException {
    	Calendar calendar = Calendar.getInstance();//此时打印它获取的是系统当前时闿
   	 	calendar.setTime(dateFormat.parse(date));	
        calendar.add(Calendar.DAY_OF_WEEK_IN_MONTH, -1);
        calendar.set(Calendar.DAY_OF_WEEK,Calendar.SATURDAY);
        return formatDate(calendar.getTime());
    }
    
    /**
     * TODO 老外习惯,获取上几周最后一天(周六)
     * @author zhanghan
     * @date	2016-9-9
     * @param date  
     * @param day  上几周
     * @return  返回类型yyyy-MM-dd
     * @throws ParseException
     */
    public static String getLastSaturday(String date,int day) throws ParseException {
    	Calendar calendar = Calendar.getInstance();//此时打印它获取的是系统当前时闿
   	 	calendar.setTime(dateFormat.parse(date));	
        calendar.add(Calendar.DAY_OF_WEEK_IN_MONTH, day);
        calendar.set(Calendar.DAY_OF_WEEK,Calendar.SATURDAY);
        return formatDate(calendar.getTime());
    }
    
    /**
     * 老外习惯,获取上周最后一天(周六)
     * @author zhanghan
     * @date	2016-8-10
     * @param date 传入当前日期
     * @return	返回类型yyyyMMdd
     * @throws ParseException
     */
    public static String getLastSaturdayNoSepDate(String date) throws ParseException {
    	Calendar calendar = Calendar.getInstance();//此时打印它获取的是系统当前时闿
    	calendar.setTime(dateFormat.parse(date));	
    	calendar.add(Calendar.DAY_OF_WEEK_IN_MONTH, -1);
    	calendar.set(Calendar.DAY_OF_WEEK,Calendar.SATURDAY);
    	return formatNoSepDate(calendar.getTime());
    }
    
    /**
     * 获取前后day月最后一夿      正数为未来{ay朿负数为过去day朿
     * @author zhanghan
     * @date	2016-8-10
     * @param date 传入当前日期
     * @return	返回类型yyyy-MM-dd
     * @throws ParseException
     */
    public static String getMonthLastDay(String date,int day) throws ParseException {
    	Calendar cal = calendar();
    	cal.setTime(dateFormat.parse(date));
    	cal.add(Calendar.MONTH, day+1);
		cal.set(Calendar.DAY_OF_MONTH, 0); // M月置1
    	return formatDate(cal.getTime());
    }
    
    /**
     * 获取前后day月最后一夿      正数为未来{ay朿负数为过去day朿
     * @author zhanghan
     * @date	2016-8-10
     * @param date 传入当前日期
     * @return	返回类型yyyyMMdd
     * @throws ParseException
     */
    public static String getMonthLastDayNoSepDate(String date,int day) throws ParseException {
    	Calendar cal = calendar();
    	cal.setTime(dateFormat.parse(date));
    	cal.add(Calendar.MONTH, day+1);
    	cal.set(Calendar.DAY_OF_MONTH, 0); // M月置1
    	return formatNoSepDate(cal.getTime());
    }
    
    /**
     * 获取前后day月第丿ĩ		正数为未来{ay朿负数为过去day朿
     * @author zhanghan
     * @date	2016-8-10
     * @param date
     * @return	返回类型yyyy-MM-dd
     * @throws ParseException
     */
    public static String getMonthFirstDay(String date, int day) throws ParseException {
    	Calendar cal = calendar();
    	cal.setTime(dateFormat.parse(date));
    	cal.add(Calendar.MONTH, day);
		cal.set(Calendar.DAY_OF_MONTH, 1); // M月置1
    	return formatDate(cal.getTime());
    }
    
    /**
     * 获取前后day月第丿ĩ		正数为未来{ay朿负数为过去day朿
     * @author zhanghan
     * @date	2016-8-10
     * @param date
     * @return	返回类型yyyyMMdd
     * @throws ParseException
     */
    public static String getMonthFirstDayNoSepDate(String date, int day) throws ParseException {
    	Calendar cal = calendar();
    	cal.setTime(dateFormat.parse(date));
    	cal.add(Calendar.MONTH, day);
    	cal.set(Calendar.DAY_OF_MONTH, 1); // M月置1
    	return formatNoSepDate(cal.getTime());
    }
    
	/**
	 * TODO 获取某天的前后几天    返回类型 yyyyMMdd
	 * @author zhanghan
	 * @date	2016-9-5
	 * @param date	
	 * @param day	正数后几天,负数前几天
	 */
	public static Date getDateAlter(Date date, int day) {
		Calendar calendar = Calendar.getInstance();//此时打印它获取的是系统当前时间
		calendar.setTime(date);
		calendar.add(Calendar.DATE, day);    //得到前几天
		Date yestedayDate = calendar.getTime();
		return yestedayDate;
	}
	
	/**
	 * TODO 中国习惯上一周第一天(上周一)
	 * @author zhanghan
	 * @date	2016-9-14
	 * @param date
	 * @param day
	 * @return
	 * @throws ParseException
	 */
	public static String getMonday(String date,int day) throws ParseException{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(parseDate(date));
		System.out.println(calendar.get(Calendar.DAY_OF_WEEK));
		if (calendar.get(Calendar.DAY_OF_WEEK) == 1) {
			--day;
		}
		calendar.add(Calendar.DAY_OF_WEEK_IN_MONTH, day);
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return formatDate(calendar.getTime());
	}
	
	/**
	 * TODO 中国习惯上一周最后一天(上周日)
	 * @author zhanghan
	 * @date	2016-9-14
	 * @param date
	 * @param day
	 * @return
	 * @throws ParseException
	 */
	public static String getSun(String date,int day) throws ParseException{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(parseDate(date));
		System.out.println(calendar.get(Calendar.DAY_OF_WEEK));
		if (calendar.get(Calendar.DAY_OF_WEEK) != 1) {
			++day;
		}
		calendar.add(Calendar.DAY_OF_WEEK_IN_MONTH, day);
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		return formatDate(calendar.getTime());
	}
	
	/**
	 * TODO 获取前后半小时
	 * @author viva
	 * @date	2016-9-18
	 * @param min 分钟 ，正数为之后多少分钟，负数为之前多少分钟	
	 */
	public static String getDateMinuteAlter(int min) {
		Calendar nowTime = Calendar.getInstance();
		nowTime.add(Calendar.MINUTE, min);//30分钟后的时间
		return datetimeFormat.format(nowTime.getTime());
	}
	
	/**
	 * TODO 获取某个日期的前后几天
	 * @author zhanghan
	 * @date	2017-1-19
	 * @return
	 */
	public static Date getCurrentDateAlter(Date date,int day) {
		Calendar calendar = Calendar.getInstance();//获取的系统当前时间
		calendar.setTime(date);
        calendar.add(Calendar.DATE, day);    //得到前一天
		return calendar.getTime();
	}
}