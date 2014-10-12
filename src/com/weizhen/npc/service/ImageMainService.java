package com.weizhen.npc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weizhen.npc.base.BaseService;
import com.weizhen.npc.dao.ImageMainDAO;
import com.weizhen.npc.model.ImageMain;

/**
 * 界别主题
 * 
 * @author y
 * 
 */
@Service
public class ImageMainService extends BaseService {

	@Autowired
	private ImageMainDAO imageMainDao;
	
	public List<ImageMain> findByCongressId(Integer congressId) {
		return imageMainDao.findByCongressId(congressId);
	}
	
	public ImageMain get(Integer imageMainId) {
		return imageMainDao.get(imageMainId);
	}
}
