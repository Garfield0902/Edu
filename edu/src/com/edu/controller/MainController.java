package com.edu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	
	
	@RequestMapping("/homePage.do")
	private String homePage(){
		return "home";
	}
	@RequestMapping("/systemInfoPage.do")
	private String systemInfoPage(){
		return "systeminfo";
	}
}

