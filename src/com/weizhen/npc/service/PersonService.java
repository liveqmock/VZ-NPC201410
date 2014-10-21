package com.weizhen.npc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weizhen.npc.base.BaseService;
import com.weizhen.npc.dao.PersonDAO;
import com.weizhen.npc.model.Document;
import com.weizhen.npc.model.ImageMain;
import com.weizhen.npc.model.ImageRelated;
import com.weizhen.npc.model.Person;

/**
 * 人物主题
 * 
 * @author y
 * 
 */
@Service
public class PersonService extends BaseService {

	@Autowired
	private PersonDAO personDao;
	
	public List<Person> loadAll() {
		return personDao.loadAll();
	}
	
	public List<ImageMain> findImageMainsByPersonId(Integer personId) {
		return personDao.findImageMainsByPersonId(personId);
	}
	
	public List<ImageRelated> findImageRelatedsByPersonId(Integer personId) {
		return personDao.findImageRelatedsByPersonId(personId);
	}
	
	public List<Document> findDocumentsByPersonId(Integer personId) {
		return personDao.findDocumentsByPersonId(personId);
	}
}
