package com.weizhen.npc.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fylaw.utils.EntityUtils;
import com.weizhen.npc.context.NPCSessionContext;
import com.weizhen.npc.model.User;

public class SessionFilter implements Filter {
	public static final String USER_SESSION_KEY = "user";

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
			ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpSession session = httpRequest.getSession();

		User user = (User) session.getAttribute(USER_SESSION_KEY);

		if (null == user) {
			String jsessionId = request.getParameter("JSESSIONID");
			if (EntityUtils.notEmpty(jsessionId)) {
				HttpSession savedSession = NPCSessionContext.getInstance().getSession(jsessionId);
				if (null != savedSession)
					user = (User) savedSession.getAttribute(USER_SESSION_KEY);
			}
			if (null == user)
				httpResponse.sendRedirect(httpRequest.getContextPath() + "/login.html");
		}

		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
	}

}
