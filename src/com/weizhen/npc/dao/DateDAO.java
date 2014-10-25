package com.weizhen.npc.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.chineseall.dams.common.paging.NameValuePair;
import com.chineseall.dams.common.paging.Paging;
import com.chineseall.dams.common.paging.PagingQueryResult;
import com.weizhen.npc.base.BaseEntityDaoSupport;
import com.weizhen.npc.model.DateDocument;
import com.weizhen.npc.model.DateImageMain;
import com.weizhen.npc.model.DateImageRelated;
import com.weizhen.npc.model.Document;
import com.weizhen.npc.model.ImageMain;
import com.weizhen.npc.model.ImageRelated;

/**
 * 
 * @author y
 * 
 */
@Repository
public class DateDAO extends BaseEntityDaoSupport<DateImageMain> {
	private Paging paging = new Paging(1, Integer.MAX_VALUE);
	private List<NameValuePair> emptyNameValuePairs = new ArrayList<NameValuePair>();
	
	
	public List<String> findAllPublishDateInMonth() {
		Map<String, Object> publishDates = new HashMap<String, Object>();
		List<String> dates;
		PagingQueryResult<String> result;
		String ql;
		
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		
		ql = "select distinct date_format(publishDate, '%Y%m') from DateImageMain";
		result = this.pagingQuery(ql, nameValuePairs,  paging);
		dates = result.getRecords();
		for(String date : dates) publishDates.put(date, null);
		
		ql = "select distinct date_format(publishDate, '%Y%m') from DateImageRelated";
		result = this.pagingQuery(ql, nameValuePairs,  paging);
		dates = result.getRecords();
		for(String date : dates) publishDates.put(date, null);
		
		ql = "select distinct date_format(publishDate, '%Y%m') from DateDocument";
		result = this.pagingQuery(ql, nameValuePairs,  paging);
		dates = result.getRecords();
		for(String date : dates) publishDates.put(date, null);
		
		dates.clear();
		dates.addAll(publishDates.keySet());
		
		return dates;
	}

	public List<ImageMain> findImageMainsByDate(Date start, Date end) {
		String ql = "select m.imageMain from DateImageMain m where m.publishDate >= :start and m.publishDate <= :end";
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new NameValuePair("start", start));
		nameValuePairs.add(new NameValuePair("end", end));
		
		PagingQueryResult<ImageMain> result = this.pagingQuery(ql, nameValuePairs, paging);
		
		return result.getRecords();
	}
	
	public List<ImageRelated> findImageRelatedsByDate(Date start, Date end) {
		String ql = "select m.imageRelated from DateImageRelated m where m.publishDate >= :start and m.publishDate <= :end";
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new NameValuePair("start", start));
		nameValuePairs.add(new NameValuePair("end", end));
		
		PagingQueryResult<ImageRelated> result = this.pagingQuery(ql, nameValuePairs, paging);
		
		return result.getRecords();
	}
	
	public List<Document> findDocumentsByDate(Date start, Date end) {
		String ql = "select m.document from DateDocument m where m.publishDate >= :start and m.publishDate <= :end";
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new NameValuePair("start", start));
		nameValuePairs.add(new NameValuePair("end", end));
		
		PagingQueryResult<Document> result = this.pagingQuery(ql, nameValuePairs, paging);
		
		return result.getRecords();
	}
	
	public List<DateImageMain> findDateImageMains(String direction) {
		String ql = " from DateImageMain m order by m.publishDate " + direction;
		
		PagingQueryResult<DateImageMain> result = this.pagingQuery(ql, emptyNameValuePairs, paging);
		
		return result.getRecords();
	}
	
	public List<DateImageRelated> findDateImageRelateds(String direction) {
		String ql = " from DateImageRelated m order by m.publishDate " + direction;
		
		PagingQueryResult<DateImageRelated> result = this.pagingQuery(ql, emptyNameValuePairs, paging);
		
		return result.getRecords();
	}
	
	public List<DateDocument> findDateDocuments(String direction) {
		String ql = " from DateDocument m order by m.publishDate " + direction;
		
		PagingQueryResult<DateDocument> result = this.pagingQuery(ql, emptyNameValuePairs, paging);
		
		return result.getRecords();
	}	
	
}
