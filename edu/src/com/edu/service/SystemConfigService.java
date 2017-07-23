package com.edu.service;

import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Service;
@Service
public class SystemConfigService {
	public boolean validateKaptchaImage(HttpSession session, String kaptcha) throws Exception {
		
		String kaptchaExpected = (String) session
				.getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		if (null != kaptcha && !kaptcha.equals(kaptchaExpected)) {
			return false;
		}
		return true;
	}
}
