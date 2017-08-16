package com.edu.session;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LocalSessionListener implements HttpSessionListener{
	Logger logger = LoggerFactory.getLogger(LocalSessionListener.class);
	@Override
	public void sessionCreated(HttpSessionEvent httpEvt) {
		String sessionId = httpEvt.getSession().getId();
		HttpSession session = httpEvt.getSession();
		logger.info("创建 sessionId: "+sessionId);
		LocalSession.addSession(sessionId, session);
		logger.info("当前在线人数："+LocalSession.activeCount());
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent httpEvt) {
		logger.info("销毁 sessionId: "+httpEvt.getSession().getId());
		LocalSession.delSession(httpEvt.getSession().getId());
	}
}
