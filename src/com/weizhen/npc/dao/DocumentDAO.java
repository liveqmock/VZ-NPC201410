package com.weizhen.npc.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.chineseall.dams.common.paging.NameValuePair;
import com.weizhen.npc.base.BaseEntityDaoSupport;
import com.weizhen.npc.model.Document;

/**
 * 
 * @author y
 * 
 */
@Repository
public class DocumentDAO extends BaseEntityDaoSupport<Document> {

	public List<Document> findByImageMainId(Integer imageMainId) {
		entityClass.getSimpleName();
		String hql = " from " + entityClass.getSimpleName() + " t ";
		hql += " where t.imageMainId = :imageMainId and checkPublish = :checkPublish order by documentSequence";
		
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new NameValuePair("imageMainId", imageMainId));
		nameValuePairs.add(new NameValuePair("checkPublish", 0));
		
		List<Document> documents = this.findByNameValuePairs(hql, nameValuePairs);

		return documents;
	}
	
	/**
	 * 获取当前主题下最大的文章序列
	 * @param imageMainId
	 * @return
	 */
	public Integer nextSequence(Integer imageMainId) {
		String queryString = "select max(documentSequence + 1) from Document where imageMainId = ?";
		Integer currentMaxSequence = (Integer) this.findFirst(queryString, imageMainId);

		return nvl(currentMaxSequence, 1).intValue();
	}	
}
