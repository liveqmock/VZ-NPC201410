package com.weizhen.npc.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.weizhen.npc.vo.DetailVO;


/**
 * The persistent class for the Location database table.
 * 
 */
@Entity
@NamedQuery(name="Location.findAll", query="SELECT l FROM Location l")
public class Location implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer locationId;
	private String locationImage;
	private Integer locationIndex;
	private Double locationLat;
	private Double locationLng;
	private String locationName;
	private String locationResume;
	private String locationTitle;
	private Date updateTime;
	
	private DetailVO detail;

	public Location() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "location_id")
	public Integer getLocationId() {
		return this.locationId;
	}

	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}


	@Column(name = "location_image")
	public String getLocationImage() {
		return this.locationImage;
	}

	public void setLocationImage(String locationImage) {
		this.locationImage = locationImage;
	}


	@Column(name = "location_index")
	public Integer getLocationIndex() {
		return this.locationIndex;
	}

	public void setLocationIndex(Integer locationIndex) {
		this.locationIndex = locationIndex;
	}


	@Column(name = "location_lat")
	public Double getLocationLat() {
		return this.locationLat;
	}

	public void setLocationLat(Double locationLat) {
		this.locationLat = locationLat;
	}


	@Column(name = "location_lng")
	public Double getLocationLng() {
		return this.locationLng;
	}

	public void setLocationLng(Double locationLng) {
		this.locationLng = locationLng;
	}


	@Column(name = "location_name")
	public String getLocationName() {
		return this.locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}


	@Column(name = "location_resume")
	public String getLocationResume() {
		return this.locationResume;
	}

	public void setLocationResume(String locationResume) {
		this.locationResume = locationResume;
	}


	@Column(name = "location_title")
	public String getLocationTitle() {
		return this.locationTitle;
	}

	public void setLocationTitle(String locationTitle) {
		this.locationTitle = locationTitle;
	}


	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updateTime")
	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Transient
	public DetailVO getDetail() {
		return detail;
	}

	public void setDetail(DetailVO detail) {
		this.detail = detail;
	}
}