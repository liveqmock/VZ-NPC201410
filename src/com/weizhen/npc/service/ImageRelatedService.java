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
import com.weizhen.npc.dao.DateImageRelatedDAO;
import com.weizhen.npc.dao.ImageMainDAO;
import com.weizhen.npc.dao.ImageRelatedDAO;
import com.weizhen.npc.dao.LocationDAO;
import com.weizhen.npc.dao.LocationImageRelatedDAO;
import com.weizhen.npc.dao.PersonDAO;
import com.weizhen.npc.dao.PersonImageRelatedDAO;
import com.weizhen.npc.model.DateImageRelated;
import com.weizhen.npc.model.ImageRelated;
import com.weizhen.npc.model.Location;
import com.weizhen.npc.model.LocationImageRelated;
import com.weizhen.npc.model.PersonImageRelated;
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
	
	@Autowired
	private DateImageRelatedDAO dateImageRelatedDao;
	
	@Autowired
	private PersonDAO personDao;
	
	@Autowired
	private PersonImageRelatedDAO personImageRelatedDao;
	
	@Autowired
	private LocationDAO locationDao;
	
	@Autowired
	private LocationImageRelatedDAO locationImageRelatedDao;
	
	public List<ImageRelated> findByImageMainId(Integer imageMainId) {
		return imageRelatedDao.findByImageMainId(imageMainId);
	}
	
	public ImageRelated get(Integer imageRelatedId) {
		return imageRelatedDao.get(imageRelatedId);
	}
	
	public PagingQueryResult<ImageRelated> findByKeyword(String keyword, Paging paging) {
		ImageRelatedQuery query = new ImageRelatedQuery();
		query.setCombinationType("or");
		
		query.setImageRelatedTitle(keyword);
		query.setImageRelatedDescription(keyword);
		
		query.setSidx(new String[] {"congressId", "imageMainId", "imageRelatedSequence"});
		query.setSord(new String[] {"asc", "asc", "asc"});
		
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
		imageRelated.setCreatedDate(new Date());
		imageRelated.setStatus(ModelStatusEnum.SAVED.getItemCode());
		imageRelated = imageRelatedDao.saveOrUpdate(imageRelated);
		
		return imageRelated;
	}
	
	public Integer nextSequence(Integer imageMainId) {
		return imageRelatedDao.nextSequence(imageMainId);
	}
	
	
	/**
	 * 修改相关资料
	 * @param imageRelated
	 * @return
	 */
	public ImageRelated modifyImageRelated(ImageRelated imageRelated) {
		imageRelated.setUpdateTime(new Date());
		imageRelated.setStatus(ModelStatusEnum.SAVED.getItemCode());
		imageRelated = imageRelatedDao.saveOrUpdate(imageRelated);
		
		return imageRelated;
	}	
	
	/**
	 * 查询主题下的所有相关资料
	 * @param imageMainId
	 * @return
	 */
	public List<ImageRelated> findAllImageRelatedsOfImageMain(Integer imageMainId) {
		ImageRelatedQuery query = new ImageRelatedQuery();
		query.setImageMainId(imageMainId);
		query.setSidx(new String[] {"imageRelatedSequence"});
		query.setSord(new String[] {"asc"});
		
		return imageRelatedDao.findByQueryModel(query);
	}
	
	public List<ImageRelated> findSubmittedRelateds() {
		ImageRelatedQuery query = new ImageRelatedQuery();
		query.setStatus(ModelStatusEnum.SUBMITTED.getItemCode());
		
		return imageRelatedDao.findByQueryModel(query);
	}
	
	public void remove(Integer imageRelatedId) {
		ImageRelated imageRelated = imageRelatedDao.getExists(imageRelatedId);
		assertEntityCanBeRemoved(imageRelated);
		imageRelatedDao.delete(imageRelated);
	}
	
	public void adjustSequence(Integer imageRelatedId, String direction) {
		ImageRelated imageRelated = imageRelatedDao.getExists(imageRelatedId);
		Integer sequence = imageRelated.getImageRelatedSequence();
		
		ImageRelatedQuery query = new ImageRelatedQuery();
		query.setImageMainId(imageRelated.getImageMainId());
		query.setSidx(new String[] {"imageRelatedSequence"});
		if ("up".equalsIgnoreCase(direction)) {
			query.setSequenceLessThan(sequence);
			query.setSord(new String[]{"desc"});
		} else {
			query.setSequenceGreatThan(sequence);
			query.setSord(new String[] {"asc"});
		}
		
		List<ImageRelated> imageRelateds = imageRelatedDao.findByQueryModel(query);
		if (EntityUtils.isEmpty(imageRelateds))
			return;
		
		ImageRelated target = imageRelateds.get(0);
		Integer targetSequence = target.getImageRelatedSequence();
		
		imageRelated.setImageRelatedSequence(targetSequence);
		imageRelatedDao.saveOrUpdate(imageRelated);
		
		target.setImageRelatedSequence(sequence);
		imageRelatedDao.saveOrUpdate(target);
	}
	
	
	public void publish(Integer imageRelatedId) {
		ImageRelated imageRelated = imageRelatedDao.getExists(imageRelatedId);
		
		try {
			// 日期专题
			if (EntityUtils.notEmpty(imageRelated.getDate())) {
				DateImageRelated dateImageRelated = new DateImageRelated();
				dateImageRelated.setPublishDate(DateTimeUtils.parse(imageRelated.getDate(), "yyyy-MM-dd"));
				dateImageRelated.setImageRelated(imageRelated);
				dateImageRelatedDao.save(dateImageRelated);
			}
			
			// 人物专题
			if (EntityUtils.notEmpty(imageRelated.getPerson())) {
				String[] personNames = imageRelated.getPerson().split(",");
				for(String personName : personNames) {
					PersonImageRelated personImageRelated = new PersonImageRelated();
					personImageRelated.setImageRelated(imageRelated);
					personImageRelated.setPerson(personDao.findByPersonName(personName));
					personImageRelatedDao.save(personImageRelated);
				}
			}
			
			// 地点专题
			if (EntityUtils.notEmpty(imageRelated.getLocation())) {
				Location location = new Location();
				location.setLocationName(imageRelated.getLocation());
				location.setLocationLng(imageRelated.getLocationLong());
				location.setLocationLat(imageRelated.getLocationLat());
				location = locationDao.saveOrUpdate(location);
				LocationImageRelated locationImageRelated = new LocationImageRelated();
				locationImageRelated.setImageRelated(imageRelated);
				locationImageRelated.setLocation(location);
				locationImageRelatedDao.saveOrUpdate(locationImageRelated);

				imageRelated.setLocationId(location.getLocationId());
				imageRelatedDao.saveOrUpdate(imageRelated);
			}
		} catch (Exception e) {
			logger.error("发布相关资料失败", e);
			
			throw new RuntimeException(e);
		}
	}
	
	public void unpublish(Integer imageRelatedId) {
		ImageRelated imageRelated = imageRelatedDao.getExists(imageRelatedId);
		
		try {
			// 删除日期专题记录
			imageRelatedDao.execute("delete from DateImageRelated m where m.imageRelated.imageRelatedId = ?", imageRelatedId);
			
			// 删除人物专题记录
			imageRelatedDao.execute("delete from PersonImageRelated m where m.imageRelated.imageRelatedId = ?", imageRelatedId);
			
			// 删除地点专题记录
			imageRelatedDao.execute("delete from LocationImageRelated m where m.imageRelated.imageRelatedId = ?", imageRelatedId);
			imageRelatedDao.execute("delete from Location m where m.locationId = ?", imageRelated.getLocationId());
		} catch (Exception e) {
			logger.error("取消发布相关资料失败", e);
			
			throw new RuntimeException(e);
		}
	}	
}
