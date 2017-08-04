package com.edu.util.excel;

import java.io.OutputStream;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * 封装Excel导出的设置信息
 * 
 * @author xieyang
 * 
 */
public class ExportExcelDataInfo {
	
	/**
	 *key为sheet名称，value为单个sheet所有行的数据
	 */
	private LinkedHashMap<SheetDesc, List<?>> objsMap;

	/**
	 *isMap 是objsMap中的List<Object>的对象是否是MAP
	 */
	private boolean isMap = false;
	
	private OutputStream out;
	
	public LinkedHashMap<SheetDesc, List<?>> getObjsMap() {
		return objsMap;
	}

	/**
	 * 图片传输格式
	 */
	private byte[] b;
	/**
	 *开始cell列 
	 */
	private Integer col1;
	/**
	 * 开始cell列行
	 */
	private Integer row1;
	/**
	 * 结束cell列
	 */
	private Integer col2;
	/**
	 * 结束cell行
	 */
	private Integer row2;
	/**
	 * @param objMap
	 *            导出数据
	 * 
	 *            泛型 String : 代表sheet名称 List : 代表单个sheet里的所有行数据
	 */
	public void setObjsMap(LinkedHashMap<SheetDesc, List<?>> objsMap) {
		this.objsMap = objsMap;
	}

	
	public OutputStream getOut() {
		return out;
	}
	/**
	 * 控制那个sheet显示图片
	 */
    private Integer sheetNum;;
	/**
	 * @param out
	 *            Excel数据将输出到该输出流
	 */
	public void setOut(OutputStream out) {
		this.out = out;
	}

	public boolean isMap() {
		return isMap;
	}

	public void setMap(boolean isMap) {
		this.isMap = isMap;
	}


	public byte[] getB() {
		return b;
	}


	public void setB(byte[] b) {
		this.b = b;
	}


	public Integer getCol1() {
		return col1;
	}


	public void setCol1(Integer col1) {
		this.col1 = col1;
	}


	public Integer getRow1() {
		return row1;
	}


	public void setRow1(Integer row1) {
		this.row1 = row1;
	}


	public Integer getCol2() {
		return col2;
	}


	public void setCol2(Integer col2) {
		this.col2 = col2;
	}


	public Integer getRow2() {
		return row2;
	}


	public void setRow2(Integer row2) {
		this.row2 = row2;
	}


	public Integer getSheetNum() {
		return sheetNum;
	}


	public void setSheetNum(Integer sheetNum) {
		this.sheetNum = sheetNum;
	}
	
	
}