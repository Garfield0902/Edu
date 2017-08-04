package com.edu.util.excel;

import java.util.List;
import java.util.Map;

/**
 * Sheet 内容组件
 * @author xieyang
 *
 */
public class SheetContent {
	
	/**
	 * sheet名称
	 */
	private String sheetName;

	/**
	 * excel内容,key为行号
	 */
	private Map<Object, List<Object>> sheetContentMap;
	
	public Map<Object, List<Object>> getSheetContentMap() {
		return sheetContentMap;
	}

	public void setSheetContentMap(Map<Object, List<Object>> sheetContentMap) {
		this.sheetContentMap = sheetContentMap;
	}

	public String getSheetName() {
		return sheetName;
	}

	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}
	
	
	
}
