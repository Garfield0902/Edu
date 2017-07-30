package com.edu.service;

import java.util.List;

import com.edu.domain.Bmpjxx;
import com.edu.vo.BmpjxxVo;
import com.edu.vo.PxhddaVo;

public interface BmpjxxService {
	List<PxhddaVo> getAllJsjbxx(BmpjxxVo bv);

	int getAllJsjbxxCount(BmpjxxVo bv);
	
	public List<Bmpjxx> getAllBmById(BmpjxxVo bv);
}
