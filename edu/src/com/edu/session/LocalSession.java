package com.edu.session;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LocalSession {
	Logger logger = LoggerFactory.getLogger(LocalSession.class);
	//存放所有局部会话 
	private static Map<String, HttpSession> sessions = new HashMap<String,HttpSession>();
	public static void addSession(String sessionId, HttpSession session) { 
		sessions.put(sessionId, session); 
	} 
	public static void delSession(String sessionId) {
		sessions.remove(sessionId);
	} 
	//根据id得到session
	public static HttpSession getSession(String sessionId) { 
		return sessions.get(sessionId);
	}
	/**
	 * 获取在线人数
	 * @author zhangwc
	 * @return int  
	 * @date 2017-8-14
	 */
	public static int activeCount(){
		return sessions.size();
	}
}


