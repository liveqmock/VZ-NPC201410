package com.weizhen.npc.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fylaw.utils.DateTimeUtils;
import com.weizhen.npc.base.BaseService;
import com.weizhen.npc.dao.DateDAO;
import com.weizhen.npc.model.Document;
import com.weizhen.npc.model.ImageMain;
import com.weizhen.npc.model.ImageRelated;

/**
 * 时间主题
 * 
 * @author y
 * 
 */
@Service
public class DateService extends BaseService {

	@Autowired
	private DateDAO dateDao;
	
	public List<String> findAllPublishDateInMonth() {
		return dateDao.findAllPublishDateInMonth();
	}
	
	public List<ImageMain> findImageMainsByDate(String publishDate) throws ParseException {
		Date start = DateTimeUtils.parse(publishDate, "YYYYMM");
		Date end = DateTimeUtils.add(start, null, 1, null, null, null, null);
		end = DateTimeUtils.add(end, null, null, -1, null, null, null);
		
		return dateDao.findImageMainsByDate(start, end);
	}
	
	public List<ImageRelated> findImageRelatedsByDate(String publishDate) throws ParseException {
		Date start = DateTimeUtils.parse(publishDate, "YYYYMM");
		Date end = DateTimeUtils.add(start, null, 1, null, null, null, null);
		end = DateTimeUtils.add(end, null, null, -1, null, null, null);
		
		return dateDao.findImageRelatedsByDate(start, end);
	}
	
	public List<Document> findDocumentsByDate(String publishDate) throws ParseException {
		Date start = DateTimeUtils.parse(publishDate, "YYYYMM");
		Date end = DateTimeUtils.add(start, null, 1, null, null, null, null);
		end = DateTimeUtils.add(end, null, null, -1, null, null, null);
		
		return dateDao.findDocumentsByDate(start, end);
	}
}
