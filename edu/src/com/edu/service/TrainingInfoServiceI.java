package com.edu.service;

import java.util.List;

import com.edu.domain.Bmpjxx;
import com.edu.domain.Pxhd;
import com.edu.vo.SignUpVo;
import com.edu.vo.TrainingInfoAnnouncementVo;

public interface TrainingInfoServiceI {
	public List<Pxhd> getAllPxhd(TrainingInfoAnnouncementVo trainingInfoAnnouncementVo);
	public int signUp(Bmpjxx record);
	public List<Bmpjxx> getAllBm(SignUpVo signUpVo);
	public int cancelSignUp(String id);
}
