package com.weizhen.npc.controller;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.chineseall.dams.common.paging.Paging;
import com.fylaw.utils.EntityUtils;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.weizhen.npc.base.BaseController;
import com.weizhen.npc.model.User;
import com.weizhen.npc.service.CongressService;
import com.weizhen.npc.service.DocumentService;
import com.weizhen.npc.service.ImageMainService;
import com.weizhen.npc.service.ImageRelatedService;
import com.weizhen.npc.service.UserService;
import com.weizhen.npc.utils.UserTypeEnum;

/**
 * 通用请求
 * @author y
 *
 */
@Controller
public class CommonController extends BaseController {
	private static Logger logger = LoggerFactory.getLogger(CommonController.class);
	
	@Autowired
	private CongressService congressService;
	
	@Autowired
	private ImageMainService imageMainService;
	
	@Autowired
	private ImageRelatedService imageRelatedService;
	
	@Autowired
	private DocumentService documentService;
	
	@Autowired
	private Producer captchaProducer;
	
	@Autowired
	private UserService userService;
	
	private Map<String, String> managerViewNames; // 根据userType设置登录后的viewName
	
	@RequestMapping(value="/index.html")
	public ModelAndView index(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("index");
		mav.addObject("congresses", congressService.loadAll());
		
		return mav;
	}
	
	@RequestMapping(value="/search.html")
	public ModelAndView search(HttpServletRequest request, String keyword, Paging paging) {
		ModelAndView mav = new ModelAndView("search");
		mav.addObject("congresses", congressService.loadAll());
		mav.addObject("keyword", keyword);
	
		if (EntityUtils.notEmpty(keyword)) {
			mav.addObject("imageMains", imageMainService.findByKeyword(keyword, paging));
			mav.addObject("imageRelateds", imageRelatedService.findByKeyword(keyword, paging));
			mav.addObject("documents", documentService.findByKeyword(keyword, paging));
		}
		
		return mav;
	}
	
	@RequestMapping(value="/3d.html")
	public ModelAndView threed(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("3d");
		mav.addObject("congresses", congressService.loadAll());
		
		return mav;
	}
	
	@RequestMapping(value="/360.html")
	public ModelAndView threesixzero(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("360");
		mav.addObject("congresses", congressService.loadAll());
		
		return mav;
	}
	
	/**
	 * 显示登录页面
	 * @return
	 */
	@RequestMapping(value="login.html",method=RequestMethod.GET)
	public ModelAndView showLogin(String errorMessage) {
		ModelAndView mav = new ModelAndView("login");
		mav.addObject("errorMessage", errorMessage);
		
		return mav;
	}
	
	/**
	 * 请求登录
	 * @param request
	 * @param session
	 * @param response
	 * @param userName 用户名
	 * @param password 密码
	 * @param verifyCode 图形验证码
	 * @return
	 */
	@RequestMapping(value = "login.html", method = RequestMethod.POST)
	public String login(HttpServletRequest request, HttpSession session,
			HttpServletResponse response, @RequestParam String userName,
			@RequestParam String password, @RequestParam String verifyCode) {
		logger.info("请求登录");

		String sessionCode = (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
		if (null == verifyCode || !verifyCode.equalsIgnoreCase(sessionCode)) {
			session.setAttribute(Constants.KAPTCHA_SESSION_KEY, null);
			session.setAttribute("errorMessage", "验证码不正确,请重新输入");
			return "redirect:login.html";
		}
		
		User user = userService.login(userName, password);
		if (null == user) {
			session.setAttribute(Constants.KAPTCHA_SESSION_KEY, null);
			session.setAttribute("errorMessage", "验用户名或密码不正确,请重新输入");
			return "redirect:login.html";
		}
		
		session.setAttribute("user", user);
		session.removeAttribute("errorMessage");
		
		return "redirect:" + initManagerViewNames().get(user.getUserType());
	}
	
	private Map<String, String> initManagerViewNames() {
		if (null == managerViewNames) {
			managerViewNames = new HashMap<String, String>();
			managerViewNames.put(UserTypeEnum.AUDITOR.getItemCode(), "/manager/auditing.html");
			managerViewNames.put(UserTypeEnum.EDITOR.getItemCode(), "/manager/content.html");
			managerViewNames.put(UserTypeEnum.MANAGER.getItemCode(), "/manager/users.html");
		}
		
		return managerViewNames;
	}
	
	@RequestMapping("/logout.html")
	public String logout() {
		session.removeAttribute("user");
		
		return "redirect:login.html";
	}
	
	/**
	 * 验证码的控制配置
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "getcode.html")
	public void initCaptcha(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		response.setDateHeader("Expires", 0);
		response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
		response.addHeader("Cache-Control", "post-check=0, pre-check=0");
		response.setHeader("Pragma", "no-cache");
		response.setContentType("image/png");
		String capText = captchaProducer.createText();
		session.setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);
		BufferedImage bufferedImage = captchaProducer.createImage(capText);

		ServletOutputStream out = response.getOutputStream();
		ImageIO.write(bufferedImage, "png", out);
		try {
			if (out != null) {
				out.flush();
			}
		} finally {
			if (out != null) {
				out.close();
			}
		}
	} 
}
