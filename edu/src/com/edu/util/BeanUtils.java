package com.edu.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.Date;
/**
 * bean 工具类；
 * @Description: TODO
 * @author zhangwc
 * @date 2017-7-13 下午6:44:31
 */
public class BeanUtils {
	/**
	 * 实现 bean 之间的 属性值的拷贝；不区分属性大小写；
	 * @author zhangwc
	 * @Description: TODO
	 * @param @param src
	 * @param @param des
	 * @param @throws NoSuchMethodException
	 * @param @throws SecurityException
	 * @param @throws IllegalAccessException
	 * @param @throws IllegalArgumentException
	 * @param @throws InvocationTargetException   
	 * @return void  
	 * @date 2017-7-13
	 */
	public static void copyBeanIgnor(Object src,Object des) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Class srcClazz = src.getClass();
		Class desClazz = des.getClass();
		Field [] srcF = srcClazz.getDeclaredFields();
		Field[] desF = desClazz.getDeclaredFields();
		for (Field sf : srcF) {
			String sfName = sf.getName();
			for(Field df : desF){
				String dfName = df.getName();
				if(sfName.equalsIgnoreCase(dfName)){
					Method srcM = srcClazz.getMethod("get"+Astr(sfName), null);
					Object o = srcM.invoke(src, null);
					if(o==null){
						break;
					}
					//将 src的属性注入到 对应的des的属性；
					inJectData(des,dfName,o);
					break;
				}
			}
		}
	}

	/**
	 * 数据注入一个对象的属性
	 * @param o	对象o
	 * @param fN 注入名称
	 * @param v	注入值
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 */
	private static void inJectData(Object o, String fN, Object v) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
		if(v==null){
			return;
		}
		
		Class clazz = o.getClass();
		Field [] fs = clazz.getDeclaredFields();
		for(Field f:fs){
			String fName = f.getName();
			if(fName.equals(fN)){
				Class<?> c = f.getType();
				String typeName = c.getName();
				if(typeName.equals("java.util.Date")||typeName.equals("java.sql.Date")){
					v = DateUtils.formatDatetime((Date)v);
				}
				String mName = "set"+Astr(fName);
				Method m = clazz.getMethod(mName,c);
				m.invoke(o, typeOpe(c.getName(),v.toString()));
				break;
			}
		}
	}

	
	public static Object typeOpe(String typeName,String val){
		if(typeName.equalsIgnoreCase("int")||typeName.equals("java.lang.Integer")){
			return Integer.parseInt(val);
		}
		if(typeName.equals("java.lang.String")){
			return val;
		}
		if(typeName.equals("java.util.Date")||typeName.equals("java.sql.Date")){
			try {
				if(val.contains("T")){
					return DateUtils.parseDatetime(val,"yyyy-MM-dd'T'hh:mm:ss");
				}else{
					if(val.length()==5){
						return DateUtils.parseDatetime(val, "hh:mm");
					}
					return DateUtils.parseDatetime(val);
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if(typeName.equalsIgnoreCase("double")||typeName.equals("java.lang.Double")){
			return Double.parseDouble(val);
		}
		if(typeName.equalsIgnoreCase("long")||typeName.equals("java.lang.Long")){
			return Long.parseLong(val);
		}
		if(typeName.equalsIgnoreCase("float")||typeName.equals("java.lang.Float")){
			return Float.parseFloat(val);
		}
		if(typeName.equalsIgnoreCase("byte")||typeName.equals("java.lang.Byte")){
			return Byte.parseByte(val);
		}
		return null;
	}
	
	/**
	 * 字符串首字母变大写
	 * @return 返回首字母变大写的字符串
	 */
	public static String Astr(String s){
		char[] cs=s.toCharArray();
		if(Character.isLowerCase(cs[0])){
			cs[0]-=32;
		}
        return String.valueOf(cs);
	}
	
	
	
	//首字母转小写
    public static String toLowerCaseFirstOne(String s)
    {
        if(Character.isLowerCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
    }
    
	public static void main(String[] args) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		String s="userid";
		System.out.println(Astr(s));
		//		TestVo vo = new TestVo();
//		vo.setId(1000);
//		vo.setRealName("zhangwenchao");
//		vo.setTelPhone("123456");
//		vo.setNowDate(new Date());
//		Test t = new Test();
//		copyBeanIgnor(vo,t);
//		System.out.println(t);
	}
}
