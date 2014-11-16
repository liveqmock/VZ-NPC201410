package com.weizhen.npc.base;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

/**
 * controller基类
 * @author y
 *
 */
public abstract class BaseController {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	protected String CONTENT_TYPE = "application/json";
	protected String CONTENT_TYPE_IE = "text/html";
	protected static final String DIRECT_URL = "directUrl";
	protected String CHARSET = "UTF-8";
	
	@Autowired
	protected HttpServletRequest request;
	
	@Autowired
	protected HttpSession session;
	
	/**
	 * 通过response对象返回前台json字符串
	 * 
	 * @param request
	 * @param response
	 * @param message 待返回的json字符串
	 * 
	 */
	protected void print(HttpServletResponse response, String message) {
		String contentType = getContentType(request);
		response.setCharacterEncoding(CHARSET);
		response.setContentType(contentType+"; charset=" + CHARSET);
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
			pw.write(message);
		}catch(IOException ex) {
			logger.error("获取writer对象出现异常: {}", ex);
		}finally {
			if(pw!=null) {
				pw.flush();
				pw.close();
			}
		}
	}
	
	/**
	 * 返回页面错误信息
	 * 
	 * @param response
	 * @param e
	 */
	protected void printError(HttpServletResponse response, Exception e) {
		String contentType = getContentType(request);
		response.setCharacterEncoding(CHARSET);
		response.setContentType(contentType+"; charset=" + CHARSET);
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		try {
			if (e != null) {
				response.getWriter().write(e.getMessage());
			} else {
				response.getWriter().write("error");
			}
		}catch (IOException ioe) {
			logger.error("获取response的writer对象出错");
		}
	}
	
	private String getContentType(HttpServletRequest request) {
		String userAgent = request.getHeader("User-Agent");
		if(StringUtils.isNotEmpty(userAgent) && userAgent.indexOf("MSIE") != -1) {
			return CONTENT_TYPE_IE;
		}
		return CONTENT_TYPE;
	}
	
	/**
	 * 1.将前台传递过来的日期格式的字符串，自动转化为Date类型
	 * 2.自动转化空字符串为NULL
	 * 
	 * 
	 * @param binder
	 */
	@InitBinder
	public void initBinder(ServletRequestDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
		binder.registerCustomEditor(BigDecimal.class, new CustomNumberEditor(BigDecimal.class, true));
	}
}
