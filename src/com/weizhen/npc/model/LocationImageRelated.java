package com.weizhen.npc.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the Location_Image_Related database table.
 * 
 */
@Entity
@Table(name="Location_Image_Related")
@NamedQuery(name="LocationImageRelated.findAll", query="SELECT l FROM LocationImageRelated l")
public class LocationImageRelated implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer locationImageRelatedId;
	private ImageRelated imageRelated;
	private Location location;
	private Date updateTime;

	public LocationImageRelated() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "location_Image_Related_id")
	public Integer getLocationImageRelatedId() {
		return this.locationImageRelatedId;
	}

	public void setLocationImageRelatedId(Integer locationImageRelatedId) {
		this.locationImageRelatedId = locationImageRelatedId;
	}


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "image_Related_id")
	public ImageRelated getImageRelated() {
		return this.imageRelated;
	}

	public void setImageRelated(ImageRelated imageRelated) {
		this.imageRelated = imageRelated;
	}


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "location_id")
	public Location getLocation() {
		return this.location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}


	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updateTime")
	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}