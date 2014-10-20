package com.weizhen.npc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chineseall.dams.common.paging.Paging;
import com.chineseall.dams.common.paging.PagingQueryResult;
import com.weizhen.npc.base.BaseService;
import com.weizhen.npc.dao.ImageRelatedDAO;
import com.weizhen.npc.model.ImageRelated;
import com.weizhen.npc.vo.ImageRelatedQuery;

/**
 * 界别主题相关数据
 * 
 * @author y
 * 
 */
@Service
public class ImageRelatedService extends BaseService {

	@Autowired
	private ImageRelatedDAO imageRelatedDao;
	
	public List<ImageRelated> findByImageMainId(Integer imageMainId) {
		return imageRelatedDao.findByImageMainId(imageMainId);
	}
	
	public PagingQueryResult<ImageRelated> findByKeyword(String keyword, Paging paging) {
		ImageRelatedQuery query = new ImageRelatedQuery();
		query.setCombinationType("or");
		
		query.setImageRelatedTitle(keyword);
		query.setImageRelatedDescription(keyword);
		
		return imageRelatedDao.pagingQuery(query, paging);
	}
}
