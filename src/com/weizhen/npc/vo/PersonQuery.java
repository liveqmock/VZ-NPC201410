package com.weizhen.npc.vo;

import com.chineseall.dams.common.paging.BaseQueryModel;

public class PersonQuery extends BaseQueryModel<PersonQuery> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String personName;

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

}
