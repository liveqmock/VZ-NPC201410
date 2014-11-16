package com.weizhen.npc.base;

/**
 * 有状态字段的实体接口
 * @author y
 *
 */
public interface StatusEntity {

	public String getStatus();
	
	public void setStatus(String status);
	
	public Integer getCheckPublish();
	
	public void setCheckPublish(Integer checkPublish);
}
