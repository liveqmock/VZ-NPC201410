package com.weizhen.npc.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.chineseall.dams.common.paging.NameValuePair;
import com.weizhen.npc.base.BaseEntityDaoSupport;
import com.weizhen.npc.model.ImageRelated;

/**
 * 
 * @author y
 * 
 */
@Repository
public class ImageRelatedDAO extends BaseEntityDaoSupport<ImageRelated> {

	public List<ImageRelated> findByImageMainId(Integer imageMainId) {
		entityClass.getSimpleName();
		String hql = " from " + entityClass.getSimpleName() + " t ";
		hql += " where t.imageMainId = :imageMainId and checkPublish = :checkPublish order by imageRelatedSequence";
		
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new NameValuePair("imageMainId", imageMainId));
		nameValuePairs.add(new NameValuePair("checkPublish", 0));
		
		List<ImageRelated> imageRelateds = this.findByNameValuePairs(hql, nameValuePairs);

		return imageRelateds;
	}
}
