package com.edu.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.dao.BmpjxxMapper;
import com.edu.dao.PxhdMapper;
import com.edu.domain.Bmpjxx;
import com.edu.domain.Pxhd;
import com.edu.service.TrainingInfoServiceI;
import com.edu.util.excel.ExcelUtil;
import com.edu.util.excel.ExportExcelDataInfo;
import com.edu.util.excel.FiledDescription;
import com.edu.util.excel.SheetDesc;
import com.edu.vo.BmpjxxVo;
import com.edu.vo.PxhdVo;

@Service("TrainingInfoService")
public class TrainingInfoServiceImpl implements TrainingInfoServiceI{

	@Autowired
	private PxhdMapper pxhdMapper;
	@Autowired
	private BmpjxxMapper bmpjxxMapper;

	public int getAllPxhdCount(PxhdVo pxhdVo){  
		return pxhdMapper.getAllPxhdCount(pxhdVo);
    }
	public List<Pxhd> getAllPxhd(PxhdVo pxhdVo,String zgh){  
		Integer count = pxhdMapper.getAllPxhdCount(pxhdVo);
		pxhdVo.setTotalCount(count);
        List<Pxhd> list = pxhdMapper.getAllPxhd(pxhdVo);
        List<BmpjxxVo> tlist = bmpjxxMapper.getPxhdByZgh(zgh);
        
        for(Pxhd p: list){
        	String hdid = p.getHdid();
        	p.setBmzt(0);
        	for(BmpjxxVo bv : tlist){
        		String thdid = bv.getHdid();
        		if(hdid.equals(thdid)){
        			p.setBmzt(1);
        		}
        	}
        }
        
        return list;
    }

	@Override
	public List<Pxhd> getAllPxhdNoPage(PxhdVo pxhdVo) {
		List<Pxhd> list = pxhdMapper.getAllPxhdNoPage(pxhdVo);
        return list;
	}

	public int signUp(Bmpjxx record) {
		return bmpjxxMapper.insert(record);
	}
	
/*	public List<Bmpjxx> getAllBm(PxhdVo pxhdVo){
        List<Bmpjxx> list = bmpjxxMapper.getAllBmpjxx(pxhdVo);
        return list;
	}*/

	@Override
	public int cancelSignUp(String id) {
		// TODO Auto-generated method stub
		return bmpjxxMapper.deleteByPrimaryKey(id);
	}
	@Override
	public int delete(String id) {
		// TODO Auto-generated method stub
		return pxhdMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Pxhd pxhd) {
		// TODO Auto-generated method stub
		return pxhdMapper.insert(pxhd);
	}

	@Override
	public int updateByPrimaryKeySelective(Pxhd pxhd) {
		// TODO Auto-generated method stub
		return pxhdMapper.updateByPrimaryKeySelective(pxhd);
	}
	
	@Override
	public Pxhd selectByPrimaryKey(String hdid) {
		return pxhdMapper.selectByPrimaryKey(hdid);
	}
	@Override
	public Workbook getexcelData(List<Pxhd> list) throws Exception {
		LinkedHashMap<SheetDesc, List<?>> map=new LinkedHashMap<SheetDesc, List<?>>();
		//创建sheet描述
		SheetDesc desc=new SheetDesc();
		//设置单个sheet的表头名称
		desc.setTitle("培训信息列表");
		//设置sheet的名称
		desc.setSheetName("培训信息");
		
		FiledDescription  hdid=new FiledDescription("活动id:hdid","hdid");
		FiledDescription  hdzt=new FiledDescription("活动主题:hdzt","hdzt");
		FiledDescription  zjr=new FiledDescription("主讲人:zjr","zjr");
		FiledDescription  hdnf=new FiledDescription("活动年份:hdnf","hdnf");
		
		FiledDescription  bmjzsj=new FiledDescription("报名截止时间:bmjzsj","bmjzsj");
		FiledDescription  hdsj=new FiledDescription("活动时间:hdsj","hdsj");
		FiledDescription  hdzzdw=new FiledDescription("活动组织单位:hdzzdw","hdzzdw");
		FiledDescription  hddd=new FiledDescription("活动地点:hddd","hddd");
		
		FiledDescription  bmzt=new FiledDescription("报名状态:bmzt","bmzt");
		FiledDescription  zdcyrs=new FiledDescription("最大人数:zdcyrs","zdcyrs");
		FiledDescription  dqcyrs=new FiledDescription("当前报名人数:dqcyrs","dqcyrs");
		FiledDescription  hdpjrs=new FiledDescription("评价人数:hdpjrs","hdpjrs");
		
		FiledDescription  hdjb=new FiledDescription("级别:hdjb","hdjb");
		FiledDescription  hdxf=new FiledDescription("学分:hdxf","hdxf");
		FiledDescription  create_at=new FiledDescription("创建时间:createAt","createAt");
		FiledDescription  create_by=new FiledDescription("创建人:createBy","createBy");
		
		FiledDescription  update_at=new FiledDescription("更新时间:updateAt","updateAt");
		FiledDescription  update_by=new FiledDescription("更新人:updateBy","updateBy");
		FiledDescription  record_status=new FiledDescription("状态:recordStatus","recordStatus");
		
		
		//建立实体与列名称关系列表
		List<FiledDescription> descriptions=new ArrayList<FiledDescription>();
		descriptions.add(hdid);
		descriptions.add(hdzt);
		descriptions.add(zjr);
		descriptions.add(hdnf);
		
		descriptions.add(bmjzsj);
		descriptions.add(hdsj);
		descriptions.add(hdzzdw);
		descriptions.add(hddd);
		
		descriptions.add(bmzt);
		descriptions.add(zdcyrs);
		descriptions.add(dqcyrs);
		descriptions.add(hdpjrs);
		
		descriptions.add(hdjb);
		descriptions.add(hdxf);
		descriptions.add(create_at);
		descriptions.add(create_by);
		//列名称与实体属性关系存储到集合中
		desc.setExcelFiledDescriptions(descriptions);
		//构建返回模型
	    map.put(desc,list);
	    //判断数据输入类型，当输入数据是map是下面填true ,否则填false
		ExportExcelDataInfo dataInfo = new ExportExcelDataInfo();
		dataInfo.setObjsMap(map);
		dataInfo.setMap(true);
	    return ExcelUtil.export2Excel(dataInfo);
	}
	@Override
	public void insertTrainingInfoBach(List<Object> vals) {
//		pxhdMapper.insertTrainingInfoBach(vals);
		for (Object object : vals) {
			pxhdMapper.insertSelective((Pxhd)object);
		}
	}
}
