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
		// TODO Auto-generated method stub
		return pxhdMapper.selectByPrimaryKey(hdid);
	}
	@Override
	public Workbook getexcelData(List<Pxhd> list) throws Exception {
		LinkedHashMap<SheetDesc, List<?>> map=new LinkedHashMap<SheetDesc, List<?>>();
		//����sheet����
		SheetDesc desc=new SheetDesc();
		//���õ���sheet�ı�ͷ����
		desc.setTitle("��ѵ��Ϣ�б�");
		//����sheet������
		desc.setSheetName("��ѵ��Ϣ");
		
		FiledDescription  hdid=new FiledDescription("�id:hdid","hdid");
		FiledDescription  hdzt=new FiledDescription("�����:hdzt","hdzt");
		FiledDescription  zjr=new FiledDescription("������:zjr","zjr");
		FiledDescription  hdnf=new FiledDescription("����:hdnf","hdnf");
		
		FiledDescription  bmjzsj=new FiledDescription("������ֹʱ��:bmjzsj","bmjzsj");
		FiledDescription  hdsj=new FiledDescription("�ʱ��:hdsj","hdsj");
		FiledDescription  hdzzdw=new FiledDescription("���֯��λ:hdzzdw","hdzzdw");
		FiledDescription  hddd=new FiledDescription("��ص�:hddd","hddd");
		
		FiledDescription  bmzt=new FiledDescription("����״̬:bmzt","bmzt");
		FiledDescription  zdcyrs=new FiledDescription("�������:zdcyrs","zdcyrs");
		FiledDescription  dqcyrs=new FiledDescription("��ǰ��������:dqcyrs","dqcyrs");
		FiledDescription  hdpjrs=new FiledDescription("��������:hdpjrs","hdpjrs");
		
		FiledDescription  hdjb=new FiledDescription("����:hdjb","hdjb");
		FiledDescription  hdxf=new FiledDescription("ѧ��:hdxf","hdxf");
		FiledDescription  create_at=new FiledDescription("����ʱ��:createAt","createAt");
		FiledDescription  create_by=new FiledDescription("������:createBy","createBy");
		
		FiledDescription  update_at=new FiledDescription("����ʱ��:updateAt","updateAt");
		FiledDescription  update_by=new FiledDescription("������:updateBy","updateBy");
		FiledDescription  record_status=new FiledDescription("״̬:recordStatus","recordStatus");
		
		
		//����ʵ���������ƹ�ϵ�б�
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
		//��������ʵ�����Թ�ϵ�洢��������
		desc.setExcelFiledDescriptions(descriptions);
		//��������ģ��
	    map.put(desc,list);
	    //�ж������������ͣ�������������map��������true ,������false
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
