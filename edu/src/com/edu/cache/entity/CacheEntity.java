package com.edu.cache.entity;

import java.util.ArrayList;
import java.util.List;

public class CacheEntity{
	private String dbName;
	private String dbo;
	private String table;
	private String entity;
	private String key;
	private List list = new ArrayList();
	
	public String getDbName() {
		return dbName;
	}
	public void setDbName(String dbName) {
		this.dbName = dbName;
	}
	public String getDbo() {
		return dbo;
	}
	public void setDbo(String dbo) {
		this.dbo = dbo;
	}
	public String getTable() {
		return table;
	}
	public void setTable(String table) {
		this.table = table;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getEntity() {
		return entity;
	}
	public void setEntity(String entity) {
		this.entity = entity;
	}
	
	public List getList() {
		return list;
	}
	public void setList(List list) {
		this.list = list;
	}
	@Override
	public String toString() {
		return "CacheEntity [dbName=" + dbName + ", dbo=" + dbo + ", table="
				+ table + ", key=" + key + "]";
	}
}
