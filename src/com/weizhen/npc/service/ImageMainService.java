package com.weizhen.npc.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chineseall.dams.common.paging.Paging;
import com.chineseall.dams.common.paging.PagingQueryResult;
import com.weizhen.npc.base.BaseService;
import com.weizhen.npc.dao.CongressDAO;
import com.weizhen.npc.dao.ImageMainDAO;
import com.weizhen.npc.model.ImageMain;
import com.weizhen.npc.utils.ModelStatusEnum;
import com.weizhen.npc.vo.ImageMainQuery;

/**
 * 界别主题
 * 
 * @author y
 * 
 */
@Service
public class ImageMainService extends BaseService {

	@Autowired
	private ImageMainDAO imageMainDao;
	
	@Autowired
	private CongressDAO congressDao;
	
	public List<ImageMain> findPublishedImageMainsByCongressId(Integer congressId) {
		ImageMainQuery query = new ImageMainQuery();
		query.setCongressId(congressId);
		query.setCheckPublish(0);
		
		return imageMainDao.findByQueryModel(query);
	}
	
	public List<ImageMain> findAllImageMainsByCongressId(Integer congressId) {
		ImageMainQuery query = new ImageMainQuery();
		query.setCongressId(congressId);
		
		return imageMainDao.findByQueryModel(query);
	}
	
	public ImageMain get(Integer imageMainId) {
		return imageMainDao.get(imageMainId);
	}
	
	
	public PagingQueryResult<ImageMain> findByKeyword(String keyword, Paging paging) {
		ImageMainQuery query = new ImageMainQuery();
		query.setCombinationType("or");
		
		query.setImageMainTitle(keyword);
		query.setImageMainDescription(keyword);
		
		return imageMainDao.pagingQuery(query, paging);
	}
	
	public ImageMain addImageMain(ImageMain imageMain) {
		congressDao.loadExists(imageMain.getCongressId());
		
		imageMain.setMaterialId(0);
		imageMain.setCheckPublish(1);
		imageMain.setCreatedDate(new Date());
		imageMain.setStatus(ModelStatusEnum.SAVED.getItemCode());
		imageMain = imageMainDao.saveOrUpdate(imageMain);
		
		return imageMain;
	}
	
	public Integer nextSequence(Integer congressId) {
		return imageMainDao.nextSequence(congressId);
	}
	
	public ImageMain modifyImageMain(ImageMain imageMain) {
		imageMain.setUpdateTime(new Date());
		imageMain.setStatus(ModelStatusEnum.SAVED.getItemCode());
		imageMain = imageMainDao.saveOrUpdate(imageMain);
		
		return imageMain;
	}
	
	/**
	 * 查询待审核的主题
	 * @return
	 */
	public List<ImageMain> findSubmittedImageMains() {
		ImageMainQuery query = new ImageMainQuery();
		query.setStatus(ModelStatusEnum.SUBMITTED.getItemCode());
		
		return imageMainDao.findByQueryModel(query);
	}
	
	public void remove(Integer imageMainId) {
		ImageMain imageMain = imageMainDao.getExists(imageMainId);
		assertEntityCanBeRemoved(imageMain);
		imageMainDao.delete(imageMain);
	}
}
