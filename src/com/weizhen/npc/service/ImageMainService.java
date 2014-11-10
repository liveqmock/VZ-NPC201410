package com.weizhen.npc.service;

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
	
	public List<ImageMain> findByCongressId(Integer congressId) {
		return imageMainDao.findByCongressId(congressId);
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
		
		// TODO 检查主题的序列是否已经存在
		imageMain.setStatus(ModelStatusEnum.SUBMITTED.getItemCode());
		imageMain = imageMainDao.saveOrUpdate(imageMain);
		
		return imageMain;
	}
}
