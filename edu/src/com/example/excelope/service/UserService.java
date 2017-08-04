package com.example.excelope.service;

import java.text.ParseException;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;

import com.example.excelope.entity.UserEntity;
import com.example.excelope.entity.UserVo;

public interface UserService {

	List<UserEntity> searchUserInfo(UserVo userVo) throws ParseException;

	Workbook getexcelData(List<UserEntity> list)  throws Exception ;

}
