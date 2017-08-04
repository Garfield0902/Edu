package com.edu.util.excel;

import java.util.List;

/**
 * sheet描述，包含表头、sheet名称和列名称与实体属性的关系
 * @author xieyang
 *
 */
public class SheetDesc {
	
	/**
	 * sheet名称
	 */
	private String sheetName;

	/**
	 * sheet 表头名称描述
	 */
	private String title;

	/**
	 * 列标头显示值与过滤值关系集合
	 */
	private List<FiledDescription> excelFiledDescriptions;

	
	
	public String getSheetName() {
		return sheetName;
	}

	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<FiledDescription> getExcelFiledDescriptions() {
		return excelFiledDescriptions;
	}

	public void setExcelFiledDescriptions(
			List<FiledDescription> excelFiledDescriptions) {
		this.excelFiledDescriptions = excelFiledDescriptions;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((excelFiledDescriptions == null) ? 0
						: excelFiledDescriptions.hashCode());
		result = prime * result
				+ ((sheetName == null) ? 0 : sheetName.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SheetDesc other = (SheetDesc) obj;
		if (excelFiledDescriptions == null) {
			if (other.excelFiledDescriptions != null)
				return false;
		} else if (!excelFiledDescriptions.equals(other.excelFiledDescriptions))
			return false;
		if (sheetName == null) {
			if (other.sheetName != null)
				return false;
		} else if (!sheetName.equals(other.sheetName))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	
	

}
