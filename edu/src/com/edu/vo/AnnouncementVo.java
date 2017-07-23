package com.edu.vo;

import java.util.ArrayList;
import java.util.List;

public class AnnouncementVo<T> {
	private Pagination page;
	List<T> list = new ArrayList<T>();
	
	public Pagination getPage() {  
        return page;  
    }  
  
    public void setPage(Pagination page) {  
        this.page = page;  
    }

	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}  
	
}
