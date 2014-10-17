package com.weizhen.npc.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.chineseall.dams.common.paging.NameValuePair;
import com.weizhen.npc.base.BaseEntityDaoSupport;
import com.weizhen.npc.model.Paragraph;

/**
 * 
 * @author y
 * 
 */
@Repository
public class ParagraphDAO extends BaseEntityDaoSupport<Paragraph> {

	public List<Paragraph> findByDocumentId(Integer documentId) {
		entityClass.getSimpleName();
		String hql = " from " + entityClass.getSimpleName() + " t ";
		hql += " where t.documentId = :documentId and checkPublish = :checkPublish order by paragraphSequence";
		
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new NameValuePair("documentId", documentId));
		nameValuePairs.add(new NameValuePair("checkPublish", 0));
		
		List<Paragraph> paragraphs = this.findByNameValuePairs(hql, nameValuePairs);

		return paragraphs;
	}
}
