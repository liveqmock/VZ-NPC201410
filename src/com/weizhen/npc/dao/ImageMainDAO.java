package com.weizhen.npc.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.chineseall.dams.common.paging.NameValuePair;
import com.weizhen.npc.base.BaseEntityDaoSupport;
import com.weizhen.npc.model.ImageMain;

/**
 * 
 * @author y
 * 
 */
@Repository
public class ImageMainDAO extends BaseEntityDaoSupport<ImageMain> {

	public List<ImageMain> findByCongressId(Integer congressId) {
		entityClass.getSimpleName();
		String hql = " from " + entityClass.getSimpleName() + " t ";
		hql += " where t.congressId = :congressId and checkPublish = :checkPublish order by imageMainSequence";
		
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new NameValuePair("congressId", congressId));
		nameValuePairs.add(new NameValuePair("checkPublish", 0));
		
		List<ImageMain> imageMains = this.findByNameValuePairs(hql, nameValuePairs);

		return imageMains;
	}
}
