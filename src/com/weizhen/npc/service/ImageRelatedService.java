package com.weizhen.npc.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chineseall.dams.common.paging.Paging;
import com.chineseall.dams.common.paging.PagingQueryResult;
import com.weizhen.npc.base.BaseService;
import com.weizhen.npc.dao.ImageMainDAO;
import com.weizhen.npc.dao.ImageRelatedDAO;
import com.weizhen.npc.model.ImageRelated;
import com.weizhen.npc.utils.ModelStatusEnum;
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
	
	@Autowired
	private ImageMainDAO imageMainDao;
	
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
	
	/**
	 * 新建相关资料
	 * @param imageRelated
	 * @return
	 */
	public ImageRelated addImageRelated(ImageRelated imageRelated) {
		imageMainDao.loadExists(imageRelated.getImageMainId());
		
		imageRelated.setCheckPublish(1);
		imageRelated.setUpdateTime(new Date());
		imageRelated.setStatus(ModelStatusEnum.SUBMITTED.getItemCode());
		imageRelated = imageRelatedDao.saveOrUpdate(imageRelated);
		
		return imageRelated;
	}
}
