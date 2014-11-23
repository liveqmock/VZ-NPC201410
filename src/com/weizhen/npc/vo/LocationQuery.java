package com.weizhen.npc.vo;

import com.chineseall.dams.common.paging.BaseQueryModel;

public class LocationQuery extends BaseQueryModel<LocationQuery> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String locationName;

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

}
