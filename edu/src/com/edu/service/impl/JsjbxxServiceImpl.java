package com.edu.service.impl;

import java.text.ParseException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.edu.dao.BmpjxxMapper;
import com.edu.dao.JsjbxxMapper;
import com.edu.dao.PxhdMapper;
import com.edu.domain.Bmpjxx;
import com.edu.domain.Jsjbxx;
import com.edu.domain.Pxhd;
import com.edu.service.JsjbxxService;
import com.edu.util.DateUtils;
import com.edu.vo.JsjbxxVo;
import com.edu.vo.TjxfVo;
@Service
public class JsjbxxServiceImpl implements JsjbxxService{
	@Autowired
	private JsjbxxMapper mmper;
	@Autowired
	private PxhdMapper pmmper;
	@Autowired
	private BmpjxxMapper bmmper;
	
	@Override
	public Jsjbxx getJsjbxxInfo(JsjbxxVo js) {
		String zgh = js.getZgh();
		return mmper.getJsjbxxInfo(zgh);
	}

	@Override
	public List<Jsjbxx> getAllJsjbxx(JsjbxxVo js) {
		return mmper.getAllJsjbxx(js);
	}

	@Override
	public int getAlljsjbxxCount(JsjbxxVo js) {
		return mmper.getAllJsjbxxCount(js);
	}

	@Override
	public void addJsxf(TjxfVo js) {
		if(StringUtils.isEmpty(js.getHdid())){
			js.setHdid(UUID.randomUUID().toString().replaceAll("-", ""));
		}
		//首先给培训活动表添加数据；
		Pxhd pxhd = new Pxhd();
		Bmpjxx bm = new Bmpjxx();
		BeanUtils.copyProperties(js, pxhd);
		BeanUtils.copyProperties(js, bm);
		try {
			initPxhd(pxhd);
			initBmpx(bm);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		pmmper.insertSelective(pxhd);
		bmmper.insertSelective(bm);
		//然后给 评价表中添加数据
//		System.out.println(pxhd);
//		System.out.println(bm);
	}

	private void initBmpx(Bmpjxx bm) {
		if(StringUtils.isEmpty(bm.getId())){
			bm.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		}
		if(bm.getBmbz()==null){
			bm.setBmbz(1); 
		}
	}

	private void initPxhd(Pxhd pxhd) throws ParseException {
		if(StringUtils.isEmpty(pxhd.getHdzt())){
			pxhd.setZjr("NULL");
		}
		if(pxhd.getBmjzsj()==null){
			pxhd.setBmjzsj(DateUtils.parseDatetime(DateUtils.currentDateformatDatetime()));
		}
		if(pxhd.getHdsj()==null){
			pxhd.setHdsj(DateUtils.parseDatetime(DateUtils.currentDateformatDatetime()));
		}
		if(StringUtils.isEmpty(pxhd.getHdzzdw())){
			pxhd.setHdzzdw("NULL");
		}
		if(StringUtils.isEmpty(pxhd.getHddd())){
			pxhd.setHddd("NULL");
		}
		if(pxhd.getBmzt()==null){
			pxhd.setBmzt(0);
		}
		if(pxhd.getZdcyrs()==null){
			pxhd.setZdcyrs(1);
		}
		if(pxhd.getDqcyrs()==null){
			pxhd.setZdcyrs(1);
		}
		if(pxhd.getHdjb()==null){
			pxhd.setHdjb(0);
		}
		if(pxhd.getHdxf()==null){
			pxhd.setHdxf(0);
		}
		if(StringUtils.isEmpty(pxhd.getZjr())){
			pxhd.setZjr("NULL");
		}
	}
}
