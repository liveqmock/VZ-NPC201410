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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 用来禁止访问指定的路径
 * 
 * @author y
 *
 */
public class DenyFilter implements Filter {
	private static Logger logger = LoggerFactory.getLogger(DenyFilter.class);

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
			ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		logger.warn("检测到来自" + httpRequest.getRemoteAddr() + "的异常访问:" + httpRequest.getRequestURL());

		httpResponse.sendRedirect(httpRequest.getContextPath());
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

}
