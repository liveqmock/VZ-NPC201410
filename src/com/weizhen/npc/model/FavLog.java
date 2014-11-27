package com.weizhen.npc.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 点赞记录
 */
@Entity
@Table(name = "fav_log")
public class FavLog implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer favId;
	private String ip;
	private String resourceType;
	private Integer resourceId;
	private Date createdDate;

	public FavLog() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "fav_id")
	public Integer getFavId() {
		return favId;
	}

	public void setFavId(Integer favId) {
		this.favId = favId;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@Column(name = "resource_type")
	public String getResourceType() {
		return resourceType;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

	@Column(name = "resource_id")
	public Integer getResourceId() {
		return resourceId;
	}

	public void setResourceId(Integer resourceId) {
		this.resourceId = resourceId;
	}

	@Column(name = "created_date")
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

}