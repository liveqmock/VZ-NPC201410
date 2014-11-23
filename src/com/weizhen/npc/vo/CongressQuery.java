package com.weizhen.npc.vo;

import com.chineseall.dams.common.paging.BaseQueryModel;

/**
 * 界别查询对象
 * @author y
 *
 */
public class CongressQuery extends BaseQueryModel<CongressQuery> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer checkPublish;

	public Integer getCheckPublish() {
		return checkPublish;
	}

	public void setCheckPublish(Integer checkPublish) {
		this.checkPublish = checkPublish;
	}

}
