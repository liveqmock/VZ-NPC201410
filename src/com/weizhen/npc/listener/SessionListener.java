package com.weizhen.npc.listener;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.weizhen.npc.context.NPCSessionContext;

public class SessionListener implements HttpSessionListener {
	private NPCSessionContext sc = NPCSessionContext.getInstance();

	public void sessionCreated(HttpSessionEvent httpSessionEvent) {
		sc.addSession(httpSessionEvent.getSession());
	}

	public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
		HttpSession session = httpSessionEvent.getSession();
		sc.delSession(session);
	}
}