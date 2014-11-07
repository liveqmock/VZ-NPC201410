package com.weizhen.npc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.chineseall.dams.common.paging.Paging;
import com.fylaw.utils.EntityUtils;
import com.weizhen.npc.base.BaseController;
import com.weizhen.npc.model.User;
import com.weizhen.npc.service.CongressService;
import com.weizhen.npc.service.DocumentService;
import com.weizhen.npc.service.ImageMainService;
import com.weizhen.npc.service.ImageRelatedService;
import com.weizhen.npc.service.UserService;

/**
 * 通用请求
 * @author y
 *
 */
@Controller
@RequestMapping(value = "/manager")
@SessionAttributes("user")
public class ManagerController extends BaseController {
	
	@Autowired
	private CongressService congressService;
	
	@Autowired
	private ImageMainService imageMainService;
	
	@Autowired
	private ImageRelatedService imageRelatedService;
	
	@Autowired
	private DocumentService documentService;
	
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/users.html")
	public ModelAndView navigation(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("manager/users");
		mav.addObject("users", userService.loadAll());
		
		return mav;
	}
	
	@RequestMapping(value="/users/list.html")
	@ResponseBody
	public List<User> listUser(HttpServletRequest request) {
		return userService.loadAll();
	}
	
	@RequestMapping(value="/users/add.html")
	@ResponseBody
	public Map<String, Object> addUser(User user) {
		Map<String, Object> res = new HashMap<String, Object>();
		
		if (!checkValid(user)) {
			res.put("msg", "字段填写不正确,请重新填写");
			return res;
		}
		
		try {
			user = userService.addUser(user);
			res.put("user", user);
			
			return res;
		} catch(Exception e) {
			res.put("msg", e.getMessage());
			return res;
		}
	}
	
	/**
	 * 修改用户
	 * @param user
	 * @return
	 */
	public Map<String, Object> updateUser(User user) {
		Map<String, Object> res = new HashMap<String, Object>();
		
		try {
			user = userService.updateUser(user);
			res.put("user", user);
			
			return res;
		} catch (Exception e) {
			res.put("msg", e.getMessage());
			
			return res;
		}
	}
	
	private Boolean checkValid(User user) {
		return true;
	}

	@RequestMapping(value = "/top.do")
	public ModelAndView top(HttpServletRequest request) {
		return new ModelAndView("top");
	}
	
	@RequestMapping(value="submit.do")
	public String submit(HttpServletRequest request, @RequestParam String directUrl) {
		return "redirect:" + directUrl;
	}
	
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
	
}
