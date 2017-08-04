package com.edu.util.excel;

/**
 * Excel 列标头显示值与模型过滤值对应关系
 * @author xieyang
 *
 */
public class FiledDescription {
	/**
	 * excel 列显示字段
	 */
	private String fieldDesc;

	/**
	 * excel 列显示时对应的过滤字段
	 */
	private String fieldName;
	
	

	public FiledDescription() {
		super();
	}

	public FiledDescription(String fieldDesc, String fieldName) {
		super();
		this.fieldDesc = fieldDesc;
		this.fieldName = fieldName;
	}

	public String getFieldDesc() {
		return fieldDesc;
	}

	public void setFieldDesc(String fieldDesc) {
		this.fieldDesc = fieldDesc;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieleName(String fieldName) {
		this.fieldName = fieldName;
	}
	
	
}
