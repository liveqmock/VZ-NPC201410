package com.weizhen.npc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weizhen.npc.base.BaseService;
import com.weizhen.npc.dao.CongressDAO;
import com.weizhen.npc.model.Congress;
import com.weizhen.npc.vo.CongressQuery;

/**
 * 界别
 * 
 * @author y
 * 
 */
@Service
public class CongressService extends BaseService {

	@Autowired
	private CongressDAO congressDao;

	public Congress load(Integer congressId) {
		Congress congress = congressDao.get(congressId);
		if (null == congress)
			throw new RuntimeException("未找到此界别");

		return congress;
	}
	
	/**
	 * 加载已发布的界别
	 * @return
	 */
	public List<Congress> loadAllPublishedCongresses() {
		CongressQuery query = new CongressQuery();
		query.setCheckPublish(0);
		
		return congressDao.findByQueryModel(query);
	}
	
	/**
	 * 加载所有界别
	 * @return
	 */
	public List<Congress> loadAllCongresses() {
		return congressDao.loadAll();
	}
}
