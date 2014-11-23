package com.weizhen.npc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.weizhen.npc.base.BaseController;
import com.weizhen.npc.model.Congress;
import com.weizhen.npc.model.Person;
import com.weizhen.npc.service.CongressService;
import com.weizhen.npc.service.PersonService;

/**
 * 处理人物主题相关请求
 * 
 *  @author y
 * 
 */
@Controller
@RequestMapping(value="/person")
@SessionAttributes({ "congresses" })
public class PersonController extends BaseController {
	
	@Autowired
	private CongressService congressService;

	@Autowired
	private PersonService personService;

	@RequestMapping(value = "/index")
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("person/index");
		
		List<Congress> congresses = congressService.loadAllPublishedCongresses();
		mav.addObject("congresses", congresses);
		
		List<Person> persons = personService.loadAll();
		mav.addObject("persons", persons);
			
		return mav;
	}
	
	@RequestMapping(value = "/{personId}")
	@ResponseBody
	public Map<String, Object> detail(@PathVariable("personId") Integer personId) {
		Map<String, Object> datas = new HashMap<String, Object>();
		
		datas.put("person", personService.get(personId));
		datas.put("imageMains", personService.findImageMainsByPersonId(personId));
		datas.put("imageRelateds", personService.findImageRelatedsByPersonId(personId));
		datas.put("documents", personService.findDocumentsByPersonId(personId));
			
		return datas;
	}
}
