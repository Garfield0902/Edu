package com.edu.util.excel;

import java.util.ArrayList;
import java.util.List;

public class ImpEntity {
	//excel sheet表名
	private String tableName;
	//导出时间
	private String expTime;
	
	private Class clazz;
	//字段名封装
	private List<String> fields = new ArrayList<String>();
	//字段值封装
	private List<Object> vals = new ArrayList<Object>();
	
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getExpTime() {
		return expTime;
	}
	public void setExpTime(String expTime) {
		this.expTime = expTime;
	}
	public List<String> getFields() {
		return fields;
	}
	public void setFields(List<String> fields) {
		this.fields = fields;
	}
	public List<Object> getVals() {
		return vals;
	}
	public void setVals(List<Object> vals) {
		this.vals = vals;
	}
	public Class getClazz() {
		return clazz;
	}
	public void setClazz(Class clazz) {
		this.clazz = clazz;
	}
	
}
