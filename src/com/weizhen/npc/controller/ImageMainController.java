package com.weizhen.npc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.weizhen.npc.base.BaseController;
import com.weizhen.npc.model.ImageMain;
import com.weizhen.npc.service.CongressService;
import com.weizhen.npc.service.DocumentService;
import com.weizhen.npc.service.ImageMainService;
import com.weizhen.npc.service.ImageRelatedService;

/**
 * 处理界面下主题请求
 * 
 * @author y
 * 
 */
@Controller
@RequestMapping(value="/imagemain")
public class ImageMainController extends BaseController {

	@Autowired
	private CongressService congressService;
	
	@Autowired
	private ImageMainService imageMainService;
	
	@Autowired
	private ImageRelatedService imageRelatedService;
	
	@Autowired
	private DocumentService documentService;

	@RequestMapping(value = "/{imageMainId}")
	@ResponseBody
	public ImageMain index(@PathVariable("imageMainId") Integer imageMainId) {
		ImageMain imageMain = imageMainService.get(imageMainId);
		imageMain.setImageRelateds(imageRelatedService.findByImageMainId(imageMainId));
		imageMain.setDocuments(documentService.findByImageMainId(imageMainId));
			
		return imageMain;
	}
}
