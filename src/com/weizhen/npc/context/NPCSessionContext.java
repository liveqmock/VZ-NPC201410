package com.weizhen.npc.context;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

public class NPCSessionContext {
	private static NPCSessionContext instance;
	private Map<String, HttpSession> sessions;

	private NPCSessionContext() {
		sessions = new HashMap<String, HttpSession>();
	}

	public static NPCSessionContext getInstance() {
		if (instance == null) {
			instance = new NPCSessionContext();
		}
		return instance;
	}

	public synchronized void addSession(HttpSession session) {
		if (session != null) {
			sessions.put(session.getId(), session);
		}
	}

	public synchronized void delSession(HttpSession session) {
		if (session != null) {
			sessions.remove(session.getId());
		}
	}

	public synchronized HttpSession getSession(String session_id) {
		if (session_id == null)
			return null;
		return (HttpSession) sessions.get(session_id);
	}
}
