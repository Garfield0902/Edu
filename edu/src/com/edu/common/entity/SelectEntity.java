package com.edu.common.entity;

public class SelectEntity {
	private String id;
	private String key;
	private String type;
	private Object value;
	private Integer order;
	
	public SelectEntity(String id, String key, String value) {
		this.id = id;
		this.key = key;
		this.value = value;
	}
	public SelectEntity() {
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	public Integer getOrder() {
		return order;
	}
	public void setOrder(Integer order) {
		this.order = order;
	}
	@Override
	public String toString() {
		return "SelectEntity [id=" + id + ", key=" + key + ", value=" + value
				+ ", order=" + order + "]";
	}
}
