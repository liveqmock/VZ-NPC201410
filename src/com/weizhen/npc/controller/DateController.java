package com.weizhen.npc.controller;

import java.text.ParseException;
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
import com.weizhen.npc.model.Document;
import com.weizhen.npc.model.ImageMain;
import com.weizhen.npc.model.ImageRelated;
import com.weizhen.npc.service.DateService;

/**
 * 处理人物主题相关请求
 * 
 *  @author y
 * 
 */
@Controller
@RequestMapping(value="/date")
@SessionAttributes({ "congresses" })
public class DateController extends BaseController {

	@Autowired
	private DateService dateService;

	@RequestMapping(value = "/index")
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("date/index");
		
		List<String> publishDates = dateService.findAllPublishDateInMonth();
		mav.addObject("publishDates", publishDates);
			
		return mav;
	}
	
	@RequestMapping(value = "/{publishDate}")
	@ResponseBody
	public Map<String, Object> detail(@PathVariable("publishDate") String publishDate) throws ParseException {
		Map<String, Object> datas = new HashMap<String, Object>();
		
		List<ImageMain> imageMains = dateService.findImageMainsByDate(publishDate);
		datas.put("imageMains", imageMains);
		List<ImageRelated> imageRelateds = dateService.findImageRelatedsByDate(publishDate);
		datas.put("imageRelateds", imageRelateds);
		List<Document> documents = dateService.findDocumentsByDate(publishDate);
		datas.put("documents", documents);
			
		return datas;
	}
}
