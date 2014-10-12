package com.weizhen.npc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weizhen.npc.base.BaseService;
import com.weizhen.npc.dao.CongressDAO;
import com.weizhen.npc.model.Congress;

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
	
	public List<Congress> loadAll() {
		return congressDao.loadAll();
	}
}
