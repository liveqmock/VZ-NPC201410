package com.weizhen.npc.json;

import java.io.Serializable;

import com.weizhen.npc.model.Congress;

public class CongressData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer congressId;
	private String congressName;

	public static CongressData from(Congress congress) {
		CongressData data = new CongressData();
		data.setCongressId(congress.getCongressId());
		data.setCongressName(congress.getCongressTitle());
		
		return data;
	}

	public Integer getCongressId() {
		return congressId;
	}

	public void setCongressId(Integer congressId) {
		this.congressId = congressId;
	}

	public String getCongressName() {
		return congressName;
	}

	public void setCongressName(String congressName) {
		this.congressName = congressName;
	}

}
