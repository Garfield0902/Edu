package com.edu.controller;

import java.awt.image.BufferedImage;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.edu.component.Log;
import com.edu.domain.ImageCode;
import com.edu.service.SystemConfigService;
import com.edu.service.UserServiceI;
import com.edu.vo.UserVo;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;

@Controller
public class LoginController {
	Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private UserServiceI userService;
	@Autowired
    private Producer captchaProducer = null;
	@Autowired
	private SystemConfigService systemConfigService;
	 /*
     * @Resource根据名称匹配； @AutoWired 根据类型匹配；
     */
	@Resource
	private ImageCode imageCode;
	
	@RequestMapping("/login.do")
	@Log(operationName="登录操作",operationType="login操作")
	public ModelAndView login(UserVo user,HttpServletRequest request,HttpServletResponse response){
		ModelAndView mav = new ModelAndView();
		if(user==null){
			mav.addObject("msg", "验证码错误！");
			mav.setViewName("redirect:/login.jsp");
		}
		HttpSession session = request.getSession();
		//验证码
		String checkN = user.getChecknum();
		String checkNum = (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
		if((checkN!=null)&&(!checkN.equals(checkNum))){
			mav.addObject("msg", "验证码错误！");
			mav.setViewName("redirect:/login.jsp");
			return mav;
		}
		SecurityUtils.getSecurityManager().logout(SecurityUtils.getSubject());  
		
		try{
			// shiro token 验证
	        UsernamePasswordToken token = new UsernamePasswordToken(user.getName(), user.getPassword());  
	        Subject subject = SecurityUtils.getSubject();  
	        subject.login(token);
	        mav.setViewName("redirect:/home.do");
	        
	        session = request.getSession(true);
	        session.setAttribute("userName", user.getName());
//	        session.setAttribute("zgh", "admin");
		}catch (IncorrectCredentialsException e1) {
			mav.addObject("msg", "用户名或密码错误!");
			mav.setViewName("redirect:/login.jsp");
			return mav;
		}catch(Exception e){
			mav.addObject("msg", e.getMessage());
			mav.setViewName("redirect:/login.jsp");
			return mav;
		}
		return mav;
	}
	
	@RequestMapping(value = "/home.do")
	@Log(operationName="返回主页操作",operationType="home操作")
	public String home(HttpServletRequest request){
		return "home";
	}
	
	@RequestMapping(value = "/logout.do")
	@Log(operationName="退出系统操作",operationType="logout操作")
	public ModelAndView logout(HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		Subject subject = SecurityUtils.getSubject();  
		SecurityUtils.getSecurityManager().logout(subject);
		//注销session
		request.getSession().invalidate();
		mav.addObject("msg", "已成功退出登录！");
		mav.setViewName("redirect:/login.jsp");
		return mav;
	}
	
	@RequestMapping(value = "/getKaptchaImage.do")
	@Log(operationName="获取验证码图片操作",operationType="getcheckImage操作")
    public void getKaptchaImage(HttpServletRequest request, HttpServletResponse response) throws Exception {  
        HttpSession session = request.getSession();
        /*String code = (String)session.getAttribute(Constants.KAPTCHA_SESSION_KEY);  
        System.out.println("******************��֤����: " + code + "******************");  */
        response.setDateHeader("Expires", 0);  
        // Set standard HTTP/1.1 no-cache headers.  
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        // Set IE extended HTTP/1.1 no-cache headers (use addHeader).  
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        // Set standard HTTP/1.0 no-cache header.  
        response.setHeader("Pragma", "no-cache");
        // return a jpeg  
        response.setContentType("image/jpeg");
        // create the text for the image  
        String capText = captchaProducer.createText();
        // store the text in the session  
        session.setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);
        session.setAttribute("TIMEOUT", System.currentTimeMillis());
        // create the image with the text  
        BufferedImage bi = captchaProducer.createImage(capText);  
        ServletOutputStream out = response.getOutputStream();  
        // write the data out  
        ImageIO.write(bi, "jpg", out);  
        try {  
            out.flush();  
        } finally {
            out.close();  
        }  
    }
	
	@RequestMapping(value = "/validateKaptchaImage")
	@Log(operationName="校验验证码图片操作",operationType="validatecheckImage操作")
    @ResponseBody
    public boolean validateKaptchaImage(HttpSession session,String kaptcha) {  
		boolean flag = false;
    	try {
    		flag = systemConfigService.validateKaptchaImage(session,kaptcha);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
    }
/*    @RequestMapping(value = "/getImage.do")
    public void getImage(HttpServletRequest request, HttpServletResponse response
            ) throws IOException {
        // 验证码
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        // ��ͼ�������servlet�������
        ServletOutputStream sos = response.getOutputStream();
        ImageIO.write(imageCode.getImage(request), "jpeg", sos);
        sos.close();
        sRand=(String)request.getAttribute("sRand");
        String result="ok";
        System.out.println(sRand);
    }*/
}
