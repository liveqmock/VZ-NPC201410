package com.weizhen.npc.controller;

import java.awt.image.BufferedImage;
import java.util.Date;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.chineseall.dams.common.paging.Paging;
import com.fylaw.utils.EntityUtils;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.weizhen.npc.base.BaseController;
import com.weizhen.npc.dao.FavLogDAO;
import com.weizhen.npc.model.FavLog;
import com.weizhen.npc.model.User;
import com.weizhen.npc.service.CongressService;
import com.weizhen.npc.service.DocumentService;
import com.weizhen.npc.service.ImageMainService;
import com.weizhen.npc.service.ImageRelatedService;
import com.weizhen.npc.service.UserService;
import com.weizhen.npc.utils.UserTypeEnum;
import com.weizhen.npc.vo.Response;

/**
 * 通用请求
 * 
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

	@Autowired
	private FavLogDAO favLogDao;

	private Map<String, String> managerViewNames; // 根据userType设置登录后的viewName

	@RequestMapping(value = "/index.html")
	public ModelAndView index(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("index");
		mav.addObject("congresses", congressService.loadAllPublishedCongresses());

		return mav;
	}

	@RequestMapping(value = "/search.html")
	public ModelAndView search(HttpServletRequest request, String keyword, Paging paging) {
		ModelAndView mav = new ModelAndView("search");
		mav.addObject("congresses", congressService.loadAllPublishedCongresses());
		mav.addObject("keyword", keyword);

		if (EntityUtils.notEmpty(keyword)) {
			mav.addObject("imageMains", imageMainService.findByKeyword(keyword, paging));
			mav.addObject("imageRelateds", imageRelatedService.findByKeyword(keyword, paging));
			mav.addObject("documents", documentService.findByKeyword(keyword, paging));
		}

		return mav;
	}

	@RequestMapping(value = "/3d.html")
	public ModelAndView threed(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("3d");
		mav.addObject("congresses", congressService.loadAllPublishedCongresses());

		return mav;
	}

	@RequestMapping(value = "/360.html")
	public ModelAndView threesixzero(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("360");
		mav.addObject("congresses", congressService.loadAllPublishedCongresses());

		return mav;
	}

	/**
	 * 显示登录页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "login.html", method = RequestMethod.GET)
	public ModelAndView showLogin(String errorMessage) {
		ModelAndView mav = new ModelAndView("login");
		mav.addObject("errorMessage", errorMessage);

		return mav;
	}

	/**
	 * 请求登录
	 * 
	 * @param request
	 * @param session
	 * @param response
	 * @param userName
	 *            用户名
	 * @param password
	 *            密码
	 * @param verifyCode
	 *            图形验证码
	 * @return
	 */
	@RequestMapping(value = "login.html", method = RequestMethod.POST)
	public String login(HttpServletRequest request, HttpSession session, HttpServletResponse response,
			@RequestParam String userName, @RequestParam String password, @RequestParam String verifyCode) {
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
			session.setAttribute("errorMessage", "用户名或密码不正确,请重新输入");
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

	@RequestMapping(value = "fav.html")
	@ResponseBody
	public Response<String> fav(HttpServletRequest request, @RequestParam String resourceType,
			@RequestParam Integer resourceId) {
		String queryString = " from FavLog where ip = ? and resourceType = ? and resourceId = ? ";
		Object data = favLogDao.findFirst(queryString, request.getRemoteAddr(), resourceType, resourceId);

		Response<String> res;

		if (null != data) {
			res = Response.error("您已经点过赞了");
		} else {
			FavLog favLog = new FavLog();
			favLog.setIp(request.getRemoteAddr());
			favLog.setResourceType(resourceType);
			favLog.setResourceId(resourceId);
			favLog.setCreatedDate(new Date());
			favLogDao.save(favLog);

			res = new Response<String>("点赞成功");
		}
		
		logger.info("resourceType:" + resourceType);
		logger.info("resourceId:" + resourceId);

		Long cnt = (Long) favLogDao.findFirst("select count(*) from FavLog where resourceType = ? and resourceId = ? ",
				resourceType, resourceId);
		res.setData(cnt.toString());
		
		logger.info("cnt:" + cnt);

		return res;
	}
}
