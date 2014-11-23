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
import com.weizhen.npc.model.Location;
import com.weizhen.npc.service.CongressService;
import com.weizhen.npc.service.ImageMainService;
import com.weizhen.npc.service.ImageRelatedService;
import com.weizhen.npc.service.LocationService;

/**
 * 处理地点主题相关请求
 * 
 * @author y
 * 
 */
@Controller
@RequestMapping(value="/location")
@SessionAttributes({ "userType", "user", "partyCode", "isManager" })
public class LocationController extends BaseController {
	
	@Autowired
	private CongressService congressService;

	@Autowired
	private LocationService locationService;
	
	@Autowired
	private ImageMainService imageMainService;
	
	@Autowired
	private ImageRelatedService imageRelatedService;

	@RequestMapping(value = "/index")
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("location/index");
		
		List<Congress> congresses = congressService.loadAllPublishedCongresses();
		mav.addObject("congresses", congresses);
		
		List<Location> locations = locationService.loadAll();
		for(Location location : locations) {
			locationService.fillWithDetail(location);
		}
		
		mav.addObject("locations", locations);
			
		return mav;
	}
	
	@RequestMapping(value = "/{locationId}")
	@ResponseBody
	public Map<String, Object> detail(@PathVariable("locationId") Integer locationId) {
		Map<String, Object> datas = new HashMap<String, Object>();
		
		datas.put("imageMains", locationService.findImageMainsByLocationId(locationId));
		datas.put("imageRelateds", locationService.findImageRelatedsByLocationId(locationId));
		datas.put("documents", locationService.findDocumentsByLocationId(locationId));
			
		return datas;
	}
}
