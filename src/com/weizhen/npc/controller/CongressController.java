package com.weizhen.npc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.weizhen.npc.base.BaseController;
import com.weizhen.npc.model.Congress;
import com.weizhen.npc.model.ImageMain;
import com.weizhen.npc.service.CongressService;
import com.weizhen.npc.service.ImageMainService;
import com.weizhen.npc.service.ImageRelatedService;

/**
 * 处理界别相关请求
 * 
 * @author y
 * 
 */
@Controller
@RequestMapping(value="/congress")
@SessionAttributes({ "congresses" })
public class CongressController extends BaseController {

	@Autowired
	private CongressService congressService;
	
	@Autowired
	private ImageMainService imageMainService;
	
	@Autowired
	private ImageRelatedService imageRelatedService;

	@RequestMapping(value = "/{congressId}")
	public ModelAndView index(@PathVariable("congressId") Integer congressId) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("congress/index");
		
		List<Congress> congresses = congressService.loadAll();
		mav.addObject("congresses", congresses);
		Congress thisCongress = congressService.load(congressId);
		mav.addObject("congress", thisCongress);
//		for(Congress congress : congresses) {
//			if (congressId == congress.getCongressId()) {
//				mav.addObject("congress", congress);
//				break;
//			}
//		}
		
		List<ImageMain> imageMains = imageMainService.findByCongressId(congressId);
		mav.addObject("imageMains", imageMains);
		
		ImageMain firstImageMain = imageMains.get(0);
		mav.addObject("firstImageMain", firstImageMain);
		
			
		return mav;
	}
}
