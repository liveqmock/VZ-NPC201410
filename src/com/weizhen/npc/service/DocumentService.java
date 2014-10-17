package com.weizhen.npc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weizhen.npc.base.BaseService;
import com.weizhen.npc.dao.DocumentDAO;
import com.weizhen.npc.model.Document;

/**
 * 界别主题相关文章
 * 
 * @author y
 * 
 */
@Service
public class DocumentService extends BaseService {

	@Autowired
	private DocumentDAO documentDao;
	
	public List<Document> findByImageMainId(Integer imageMainId) {
		return documentDao.findByImageMainId(imageMainId);
	}
}
