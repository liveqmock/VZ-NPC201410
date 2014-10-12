package com.weizhen.npc.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.weizhen.npc.base.BaseController;
import com.weizhen.npc.service.CongressService;

/**
 * 通用请求
 * @author y
 *
 */
@Controller
public class CommonController extends BaseController {
	
	@Autowired
	private CongressService congressService;

	@RequestMapping(value = "/nav.do")
	public ModelAndView navigation(HttpServletRequest request) {
		return new ModelAndView("nav");
	}

	@RequestMapping(value = "/top.do")
	public ModelAndView top(HttpServletRequest request) {
		return new ModelAndView("top");
	}
	
	@RequestMapping(value="submit.do")
	public String submit(HttpServletRequest request, @RequestParam String directUrl) {
		return "redirect:" + directUrl;
	}
	
	@RequestMapping(value="/index.do")
	public ModelAndView index(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("index");
		mav.addObject("congresses", congressService.loadAll());
		
		return mav;
	}
	
}
