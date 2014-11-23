package com.weizhen.npc.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

import com.fylaw.utils.DateTimeUtils;
import com.fylaw.utils.EntityUtils;
import com.weizhen.npc.base.BaseController;
import com.weizhen.npc.model.Congress;
import com.weizhen.npc.model.DateDocument;
import com.weizhen.npc.model.DateImageMain;
import com.weizhen.npc.model.DateImageRelated;
import com.weizhen.npc.model.Document;
import com.weizhen.npc.model.ImageMain;
import com.weizhen.npc.model.ImageRelated;
import com.weizhen.npc.service.CongressService;
import com.weizhen.npc.service.DateService;
import com.weizhen.npc.vo.DateVO;

/**
 * 处理人物主题相关请求
 * 
 *  @author y
 * 
 */
@Controller
@RequestMapping(value="/date")
@SessionAttributes({ "congresses" })
@SuppressWarnings({ "unchecked", "rawtypes" })
public class DateController extends BaseController {
	
	@Autowired
	private CongressService congressService;

	@Autowired
	private DateService dateService;

	@RequestMapping(value = "/index")
	public ModelAndView index(String direction) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("date/index");
		
		List<Congress> congresses = congressService.loadAllPublishedCongresses();
		mav.addObject("congresses", congresses);
		
		if (null == direction) direction = "asc";
		Map<String, Map<String, List>> datas = new HashMap<String, Map<String, List>>();
		
		List<DateImageMain> dateImageMains = dateService.findDateImageMains(direction);
		List<DateImageRelated> dateImageRelateds = dateService.findDateImageRelateds(direction);
		List<DateDocument> dateDocuments = dateService.findDateDocuments(direction);
		
		if (EntityUtils.notEmpty(dateImageMains)) {
			for (DateImageMain dateImageMain : dateImageMains) {
				Map<String, List> data = getData(datas, DateTimeUtils.format(dateImageMain.getPublishDate(), "yyyyMM"));
				data.get("imageMains").add(dateImageMain.getImageMain());
			}
		}
		
		if (EntityUtils.notEmpty(dateImageRelateds)) {
			for (DateImageRelated dateImageRelated : dateImageRelateds) {
				Map<String, List> data = getData(datas, DateTimeUtils.format(dateImageRelated.getPublishDate(), "yyyyMM"));
				data.get("imageRelateds").add(dateImageRelated.getImageRelated());
			}
		}
		
		if (EntityUtils.notEmpty(dateDocuments)) {
			for (DateDocument dateDocument : dateDocuments) {
				Map<String, List> data = getData(datas, DateTimeUtils.format(dateDocument.getPublishDate(), "yyyyMM"));
				data.get("documents").add(dateDocument.getDocument());
			}
		}	
		
		List<DateVO> dateVOs = new ArrayList<DateVO>();
		for(Object publishDate : datas.keySet()) {
			DateVO dateVO = new DateVO();
			dateVO.setPublishDate((String)publishDate);
			
			Map<String, List> data = (Map<String, List>)datas.get(publishDate);
			dateVO.setImageMains((List<ImageMain>) data.get("imageMains"));
			dateVO.setImageRelateds((List<ImageRelated>) data.get("imageRelateds"));
			dateVO.setDocuments((List<Document>) data.get("documents"));
			dateVOs.add(dateVO);
		}
		
		Collections.sort(dateVOs, new Comparator<DateVO>() {

			@Override
			public int compare(DateVO arg0, DateVO arg1) {
				return arg0.getPublishDate().compareTo(arg1.getPublishDate());
			}
			
		});
		
		mav.addObject("dateVOs", dateVOs);
			
		return mav;
	}
	
	private Map<String, List> getData(Map<String, Map<String, List>> datas, String publishDate) {
		Map<String, List> data = datas.get(publishDate);
		if (null == data) {
			data = new HashMap<String, List>();
			data.put("imageMains", new ArrayList<ImageMain>());
			data.put("imageRelateds", new ArrayList<ImageRelated>());
			data.put("documents", new ArrayList<Document>());
			
			datas.put(publishDate, data);
		}
		
		return data;
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
