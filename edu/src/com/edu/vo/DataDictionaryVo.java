package com.edu.vo;


public class DataDictionaryVo  extends Pagination {
	private String id;
	private String name;
    private String type;

    private String value;

    private Integer orderData;

    private String des;
    
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Integer getOrderData() {
		return orderData;
	}

	public void setOrderData(Integer orderData) {
		this.orderData = orderData;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "DataDictionaryVo [id=" + id + ", type=" + type + ", value="
				+ value + ", orderData=" + orderData + ", des=" + des + "]";
	}
}
