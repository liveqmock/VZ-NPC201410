package com.weizhen.npc.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.chineseall.dams.common.paging.NameValuePair;
import com.chineseall.dams.common.paging.Paging;
import com.chineseall.dams.common.paging.PagingQueryResult;
import com.weizhen.npc.base.BaseEntityDaoSupport;
import com.weizhen.npc.model.Document;
import com.weizhen.npc.model.ImageMain;
import com.weizhen.npc.model.ImageRelated;
import com.weizhen.npc.model.Location;

/**
 * 
 * @author y
 * 
 */
@Repository
public class LocationDAO extends BaseEntityDaoSupport<Location> {
	private Paging paging = new Paging(1, Integer.MAX_VALUE);

	public List<ImageMain> findImageMainsByLocationId(Integer locationId) {
		String ql = "select m.imageMain from LocationImageMain m where m.location.locationId = :locationId";
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new NameValuePair("locationId", locationId));
		
		PagingQueryResult<ImageMain> result = this.pagingQuery(ql, nameValuePairs, paging);
		
		return result.getRecords();
	}
	
	public List<ImageRelated> findImageRelatedsByLocationId(Integer locationId) {
		String ql = "select m.imageRelated from LocationImageRelated m where m.location.locationId = :locationId";
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new NameValuePair("locationId", locationId));
		
		PagingQueryResult<ImageRelated> result = this.pagingQuery(ql, nameValuePairs, paging);
		
		return result.getRecords();
	}
	
	public List<Document> findDocumentsByLocationId(Integer locationId) {
		String ql = "select m.document from LocationDocument m where m.location.locationId = :locationId";
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new NameValuePair("locationId", locationId));
		
		PagingQueryResult<Document> result = this.pagingQuery(ql, nameValuePairs, paging);
		
		return result.getRecords();
	}
	
}
