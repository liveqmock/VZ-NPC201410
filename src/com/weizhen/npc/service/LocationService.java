package com.weizhen.npc.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weizhen.npc.base.BaseService;
import com.weizhen.npc.dao.LocationDAO;
import com.weizhen.npc.model.Document;
import com.weizhen.npc.model.ImageRelated;
import com.weizhen.npc.model.Location;

/**
 * 地点主题
 * 
 * @author y
 * 
 */
@Service
public class LocationService extends BaseService {

	@Autowired
	private LocationDAO locationDao;
	
	public List<Location> loadAll() {
		return locationDao.loadAll();
	}
	
	public List<Map<String, Object>> findImageMainsByLocationId(Integer locationId) {
		return locationDao.findImageMainsByLocationId(locationId);
	}
	
	
	public List<ImageRelated> findImageRelatedsByLocationId(Integer locationId) {
		return locationDao.findImageRelatedsByLocationId(locationId);
	}
	
	public List<Document> findDocumentsByLocationId(Integer locationId) {
		return locationDao.findDocumentsByLocationId(locationId);
	}
}
