package com.weizhen.npc.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.chineseall.dams.common.paging.NameValuePair;
import com.chineseall.dams.common.paging.Paging;
import com.chineseall.dams.common.paging.PagingQueryResult;
import com.weizhen.npc.base.BaseEntityDaoSupport;
import com.weizhen.npc.model.Document;
import com.weizhen.npc.model.ImageRelated;
import com.weizhen.npc.model.Person;

/**
 * 
 * @author y
 * 
 */
@Repository
public class PersonDAO extends BaseEntityDaoSupport<Person> {
	private Paging paging = new Paging(1, Integer.MAX_VALUE);

	public List<Map<String, Object>> findImageMainsByPersonId(Integer personId) {
		String ql = "select new map(m.imageMain.imageMainId, m.imageMain.imageMainTitle, m.imageMain.imageMainDescription, m.imageMain.imageMainFilepath) ";
			ql += "from PersonImageMain m where m.person.personId = :personId";
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new NameValuePair("personId", personId));
		
		PagingQueryResult<Map<String, Object>> result = this.pagingQuery(ql, nameValuePairs, paging);
		
		return result.getRecords();
	}
	
	public List<ImageRelated> findImageRelatedsByPersonId(Integer personId) {
		String ql = "select m.imageRelated from PersonImageRelated m where m.person.personId = :personId";
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new NameValuePair("personId", personId));
		
		PagingQueryResult<ImageRelated> result = this.pagingQuery(ql, nameValuePairs, paging);
		
		return result.getRecords();
	}
	
	public List<Document> findDocumentsByPersonId(Integer personId) {
		String ql = "select m.document from PersonDocument m where m.person.personId = :personId";
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new NameValuePair("personId", personId));
		
		PagingQueryResult<Document> result = this.pagingQuery(ql, nameValuePairs, paging);
		
		return result.getRecords();
	}
	
}
