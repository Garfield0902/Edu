package com.edu.component;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.edu.component.entity.SystemLog;
import com.edu.component.service.SystemLogService;
import com.edu.component.until.NetworkUtil;

@Component
@Aspect
public class SystemLogAspect {
	Logger logger = Logger.getLogger(SystemLogAspect.class);
	@Resource
	private SystemLogService sls;
	
	@Pointcut("execution (* com.edu.controller..*.*(..))")
	public void aspect(){
		logger.debug(" ================================================================= ");
	}
	
	@After("aspect()")
	public void after(JoinPoint jp) throws IOException{
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		String ip  = NetworkUtil.getIpAddress(request);
		HttpSession session = request.getSession();	
		//可以从session 中拿一些登录人的信息
		String userName = (String) session.getAttribute("userName");
		
		String targetName = jp.getTarget().getClass().getName();
		String method = jp.getSignature().getName();
		String operationType ="";
		String operationName ="";
		try {
			Object [] args = jp.getArgs();
			Class tarclazz = Class.forName(targetName);
			Method[] tarMethods = tarclazz.getDeclaredMethods();
			for (Method m: tarMethods) {
				//先 找到 方法名字相同的方法 
				if(method.equals(m.getName())){
					//然后根据 方法的 参数个数/类型 排除掉重载方法
					Class [] pc = m.getParameterTypes();
					if(args.length==pc.length){
						//过滤没有日志(log) 标识的方法；
						if(m.getAnnotation(Log.class)==null){
							return;
						}
						operationType = m.getAnnotation(Log.class).operationType();
						operationName = m.getAnnotation(Log.class).operationName();
						break;
					}
				}
			}
            //*========控制台输出=========*//  
            logger.debug("=====controller后置通知开始=====");  
            logger.debug("请求方法:" + (targetName+ "." +method+ "()")+"."+operationType);  
            logger.debug("方法描述:" + operationName);  
            logger.debug("请求人:" + userName);
            logger.debug("请求IP:" + ip);
            //*========数据库日志=========*//
            SystemLog log = new SystemLog();
            log.setId(UUID.randomUUID().toString());
            log.setDescription(operationName);
            log.setMethod((targetName+ "." +method+ "()")+"."+operationType);  
            log.setLogtype(0);
            log.setRequestip(ip);
            log.setExceptioncode( null);
            log.setExceptiondetail(null);
            log.setParams(Arrays.toString(args));
            log.setCreateby(userName);
            log.setCreatedate(new Date());
            //保存数据库 
            sls.insert(log);
            logger.debug("=====controller后置通知结束=====");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
