package com.weizhen.npc.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.fylaw.utils.EntityUtils;
import com.weizhen.npc.base.BaseEntityDaoSupport;
import com.weizhen.npc.model.Congress;

/**
 * 
 * @author y
 * 
 */
@Repository
public class CongressDAO extends BaseEntityDaoSupport<Congress> {

	public Congress findByCongressId(Long congressId) {
		entityClass.getSimpleName();
		String hql = " from ";
		hql += entityClass.getSimpleName();
		hql += " t where t.congress_id = ?";
		List<Congress> congresses = this.find(hql, congressId);

		if (EntityUtils.isEmpty(congresses))
			return null;

		return congresses.get(0);
	}
}
