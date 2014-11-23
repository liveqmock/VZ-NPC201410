package com.weizhen.npc.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chineseall.dams.common.paging.Paging;
import com.chineseall.dams.common.paging.PagingQueryResult;
import com.fylaw.utils.DateTimeUtils;
import com.fylaw.utils.EntityUtils;
import com.weizhen.npc.base.BaseService;
import com.weizhen.npc.dao.CongressDAO;
import com.weizhen.npc.dao.DateImageMainDAO;
import com.weizhen.npc.dao.ImageMainDAO;
import com.weizhen.npc.dao.LocationDAO;
import com.weizhen.npc.dao.PersonDAO;
import com.weizhen.npc.dao.PersonImageMainDAO;
import com.weizhen.npc.model.DateImageMain;
import com.weizhen.npc.model.ImageMain;
import com.weizhen.npc.model.LocationImageMain;
import com.weizhen.npc.model.PersonImageMain;
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
	
	@Autowired
	private DateImageMainDAO dateImageMainDao;
	
	@Autowired
	private PersonDAO personDao;
	
	@Autowired
	private PersonImageMainDAO personImageMainDao;
	
	@Autowired
	private LocationDAO locationDao;
	
	
	
	public List<ImageMain> findPublishedImageMainsByCongressId(Integer congressId) {
		ImageMainQuery query = new ImageMainQuery();
		query.setCongressId(congressId);
		query.setCheckPublish(0);
		
		return imageMainDao.findByQueryModel(query);
	}
	
	public List<ImageMain> findAllImageMainsByCongressId(Integer congressId) {
		ImageMainQuery query = new ImageMainQuery();
		query.setCongressId(congressId);
		query.setSidx(new String[] {"imageMainSequence"});
		query.setSord(new String[] {"asc"});
		
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
	
	public void adjustSequence(Integer imageMainId, String direction) {
		ImageMain imageMain = imageMainDao.getExists(imageMainId);
		Integer sequence = imageMain.getImageMainSequence();
		
		ImageMainQuery query = new ImageMainQuery();
		query.setCongressId(imageMain.getCongressId());
		query.setSidx(new String[]{"imageMainSequence"});
		if ("up".equalsIgnoreCase(direction)) {
			query.setSequenceLessThan(sequence);
			query.setSord(new String[] {"desc"});
		} else {
			query.setSequenceGreatThan(sequence);
			query.setSord(new String[] {"asc"});
		}
		
		List<ImageMain> imageMains = imageMainDao.findByQueryModel(query);
		if (EntityUtils.isEmpty(imageMains))
			return;
		
		ImageMain target = imageMains.get(0);
		Integer targetSequence = target.getImageMainSequence();
		
		imageMain.setImageMainSequence(targetSequence);
		imageMainDao.saveOrUpdate(imageMain);
		
		target.setImageMainSequence(sequence);
		imageMainDao.saveOrUpdate(target);
	}
	
	public void publish(Integer imageMainId) {
		ImageMain imageMain = imageMainDao.getExists(imageMainId);
		
		try {
			DateImageMain dateImageMain = new DateImageMain();
			dateImageMain.setPublishDate(DateTimeUtils.parse(imageMain.getDate(), "yyyy-MM-dd"));
			dateImageMain.setImageMain(imageMain);
			dateImageMainDao.save(dateImageMain);
			
			String person = imageMain.getPerson();
			if (EntityUtils.notEmpty(person)) {
				String[] personNames = person.split(",");
				for(String personName : personNames) {
					PersonImageMain personImageMain = new PersonImageMain();
					personImageMain.setImageMain(imageMain);
					personImageMain.setPerson(personDao.findByPersonName(personName));
					personImageMainDao.save(personImageMain);
				}
			}
			
			LocationImageMain locationImageMain = new LocationImageMain();
			locationImageMain.setImageMain(imageMain);

			// TODO 
			
		} catch (Exception e) {
			logger.error("发布主题失败", e);
			
			throw new RuntimeException(e);
		}
	}
}
