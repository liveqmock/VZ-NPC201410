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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fylaw.utils.EntityUtils;
import com.weizhen.npc.context.NPCSessionContext;
import com.weizhen.npc.model.User;

public class SessionFilter implements Filter {
	public static final String USER_SESSION_KEY = "user";

	private static Logger logger = LoggerFactory.getLogger(SessionFilter.class);

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
		logger.info("用户是否登录:" + (null != user));

		if (null == user) {
			String jsessionId = request.getParameter("JSESSIONID");
			logger.info("JSESSIONID:" + jsessionId);
			if (EntityUtils.notEmpty(jsessionId)) {
				HttpSession savedSession = NPCSessionContext.getInstance().getSession(jsessionId);
				logger.info("savedSession是否为空:" + (null == savedSession));
				if (null != savedSession)
					user = (User) savedSession.getAttribute(USER_SESSION_KEY);
				logger.info("savedSession中是否有用户对象:" + (null != user));
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
