package com.weizhen.npc.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weizhen.npc.base.BaseEntityDaoSupport;
import com.weizhen.npc.base.BaseService;
import com.weizhen.npc.base.StatusEntity;
import com.weizhen.npc.dao.CongressDAO;
import com.weizhen.npc.dao.DocumentDAO;
import com.weizhen.npc.dao.ImageMainDAO;
import com.weizhen.npc.dao.ImageRelatedDAO;
import com.weizhen.npc.dao.ResourceAuditLogDAO;
import com.weizhen.npc.model.Congress;
import com.weizhen.npc.model.Document;
import com.weizhen.npc.model.ImageMain;
import com.weizhen.npc.model.ImageRelated;
import com.weizhen.npc.model.ResourceAuditLog;
import com.weizhen.npc.utils.ModelStatusEnum;
import com.weizhen.npc.utils.ModelStatusTransformer;
import com.weizhen.npc.vo.ResourceAuditLogQuery;

/**
 * 审核相关服务
 * 
 * @author y
 * 
 */
@Service
public class ResourceAuditLogService extends BaseService {

	@Autowired
	private ResourceAuditLogDAO resourceAuditLogDAO;
	
	@Autowired
	private DocumentDAO documentDAO;
	
	@Autowired
	private ImageRelatedDAO imageRelatedDAO;
	
	@Autowired
	private ImageMainDAO imageMainDAO;
	
	@Autowired
	private CongressDAO congressDAO;
	
	private Map<String, BaseEntityDaoSupport<? extends StatusEntity>> mapper;
	
	public void audit(ResourceAuditLog resourceAuditLog) {
		BaseEntityDaoSupport<? extends StatusEntity> entityDAO = judgeEntityDAO(resourceAuditLog.getResourceType());
		StatusEntity entity = entityDAO.getExists(resourceAuditLog.getResourceId());
		
		String statusFrom = entity.getStatus();
		String statusTo = ModelStatusTransformer.getStatus(statusFrom, resourceAuditLog.getOperation());
		entity.setStatus(statusTo);
		entityDAO.updateEntity(entity);
		
		resourceAuditLog.setAuditDate(new Date());
		resourceAuditLog.setStatusFrom(statusFrom);
		resourceAuditLog.setStatusTo(statusTo);
		
		resourceAuditLogDAO.save(resourceAuditLog);
	}
	
	private BaseEntityDaoSupport<? extends StatusEntity> judgeEntityDAO(String resourceType) {
		if (null == mapper) initMapper();
		
		return mapper.get(resourceType);
	}
	
	private void initMapper() {
		mapper = new HashMap<String, BaseEntityDaoSupport<? extends StatusEntity>>();
		mapper.put(Document.class.getSimpleName(), documentDAO);
		mapper.put(ImageRelated.class.getSimpleName(), imageRelatedDAO);
		mapper.put(ImageMain.class.getSimpleName(), imageMainDAO);
		mapper.put(Congress.class.getSimpleName(), congressDAO);
	}
	
	public void publish(ResourceAuditLog resourceAuditLog) throws Exception {
		BaseEntityDaoSupport<? extends StatusEntity> entityDAO = judgeEntityDAO(resourceAuditLog.getResourceType());
		StatusEntity entity = entityDAO.getExists(resourceAuditLog.getResourceId());
		
		String statusFrom = entity.getStatus();
		if (!ModelStatusEnum.RATIFIED.getItemCode().equalsIgnoreCase(statusFrom))
			throw new Exception("数据未处于待发布状态");
		
		entity.setStatus(ModelStatusEnum.PUBLISHED.getItemCode());
		entity.setCheckPublish(0);
		entityDAO.updateEntity(entity);
		
		resourceAuditLog.setAuditDate(new Date());
		resourceAuditLog.setStatusFrom(ModelStatusEnum.RATIFIED.getItemCode());
		resourceAuditLog.setStatusTo(ModelStatusEnum.PUBLISHED.getItemCode());
		
		resourceAuditLogDAO.save(resourceAuditLog);		
	}
	
	/**
	 * 新建或修改数据时记录日志
	 * @param resourceAuditLog
	 */
	public void saveOrUpdate(ResourceAuditLog resourceAuditLog) {
		resourceAuditLog.setAuditDate(new Date());
		
		resourceAuditLogDAO.save(resourceAuditLog);
	}

	
	public List<ResourceAuditLog> showAuditLogs(ResourceAuditLogQuery query) {
		return resourceAuditLogDAO.findByQueryModel(query);
	}
}
