package com.weizhen.npc.vo;

import com.chineseall.dams.common.paging.BaseQueryModel;

public class ResourceAuditLogQuery extends BaseQueryModel<ResourceAuditLogQuery> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String resourceType;
	private Integer resourceId;

	public String getResourceType() {
		return resourceType;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

	public Integer getResourceId() {
		return resourceId;
	}

	public void setResourceId(Integer resourceId) {
		this.resourceId = resourceId;
	}

}
