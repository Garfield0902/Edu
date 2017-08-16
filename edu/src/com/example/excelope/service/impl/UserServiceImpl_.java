package com.example.excelope.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;

import com.edu.util.excel.ExcelUtil;
import com.edu.util.excel.ExportExcelDataInfo;
import com.edu.util.excel.FiledDescription;
import com.edu.util.excel.SheetDesc;
import com.example.excelope.entity.UserEntity;
import com.example.excelope.entity.UserVo;
import com.example.excelope.service.UserService;
@Service
public class UserServiceImpl_ implements UserService{

	@Override
	public List<UserEntity> searchUserInfo(UserVo userVo) throws ParseException {
		List<UserEntity> userList = new ArrayList<UserEntity>();
		for(int i=0;i<10;i++){
			UserEntity u = new UserEntity();
			u.setName("张三 "+i);
			u.setAge(15+i);
			u.setBirthdate(new Date());
			u.setRemark(" 用户 信息 "+i);
			userList.add(u);
			try {
				TimeUnit.SECONDS.sleep(1L);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return userList;
	}

	@Override
	public Workbook getexcelData(List<UserEntity> list) throws Exception {
		LinkedHashMap<SheetDesc, List<?>> map=new LinkedHashMap<SheetDesc, List<?>>();
		//创建sheet描述
		SheetDesc desc=new SheetDesc();
		//设置单个sheet的表头名称
		desc.setTitle("用户信息列表");
		//设置sheet的名称
		desc.setSheetName("用户信息");
		
		FiledDescription  name=new FiledDescription("用户名:name","name");
		FiledDescription  age=new FiledDescription("年龄:age","age");
		FiledDescription  birthdate=new FiledDescription("生日:birthdate","birthdate");
		FiledDescription  remark=new FiledDescription("备注:remark","remark");
		
		//建立实体与列名称关系列表
		List<FiledDescription> descriptions=new ArrayList<FiledDescription>();
		descriptions.add(name);
		descriptions.add(age);
		descriptions.add(birthdate);
		descriptions.add(remark);
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

}
