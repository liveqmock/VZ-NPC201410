package com.weizhen.npc.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.weizhen.npc.base.StatusEntity;


/**
 * The persistent class for the Image_Related database table.
 * 
 */
@Entity
@Table(name="Image_Related")
@NamedQuery(name="ImageRelated.findAll", query="SELECT i FROM ImageRelated i")
public class ImageRelated implements Serializable, StatusEntity {
	private static final long serialVersionUID = 1L;
	private Integer imageRelatedId;
	private Integer checkPublish;
	private Integer cityId;
	private String comment;
	private Integer congressId;
	private Integer continentId;
	private Integer countryId;
	private Integer imageMainId;
	private String imageRelatedDescription;
	private String imageRelatedFilepath;
	private String imageRelatedLocation;
	private Integer imageRelatedSequence;
	private String imageRelatedThumbFilepath;
	private Integer imageRelatedTime;
	private String imageRelatedTitle;
	private Integer materialId;
	private Integer provinceId;
	private String publishDate;
	private Date updateTime;
	
	private String status;
	private String creator;
	private Date createdDate;
	
	private String keyword;
	private String date;
	private String person;
	private String location;
	private Double locationLong;
	private Double locationLat;	

	public ImageRelated() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "image_Related_id")
	public Integer getImageRelatedId() {
		return this.imageRelatedId;
	}

	public void setImageRelatedId(Integer imageRelatedId) {
		this.imageRelatedId = imageRelatedId;
	}

	@Column(name = "check_Publish")
	public Integer getCheckPublish() {
		return this.checkPublish;
	}

	public void setCheckPublish(Integer checkPublish) {
		this.checkPublish = checkPublish;
	}

	@Column(name = "city_id")
	public Integer getCityId() {
		return this.cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}


	@Column(name = "comment")
	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Column(name = "congress_id")
	public Integer getCongressId() {
		return this.congressId;
	}

	public void setCongressId(Integer congressId) {
		this.congressId = congressId;
	}

	@Column(name = "continent_id")
	public Integer getContinentId() {
		return this.continentId;
	}

	public void setContinentId(Integer continentId) {
		this.continentId = continentId;
	}

	@Column(name = "country_id")
	public Integer getCountryId() {
		return this.countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	@Column(name = "image_Main_id")
	public Integer getImageMainId() {
		return this.imageMainId;
	}

	public void setImageMainId(Integer imageMainId) {
		this.imageMainId = imageMainId;
	}


	@Column(name = "image_Related_description")
	public String getImageRelatedDescription() {
		return this.imageRelatedDescription;
	}

	public void setImageRelatedDescription(String imageRelatedDescription) {
		this.imageRelatedDescription = imageRelatedDescription;
	}


	@Column(name = "image_Related_filepath")
	public String getImageRelatedFilepath() {
		return this.imageRelatedFilepath;
	}

	public void setImageRelatedFilepath(String imageRelatedFilepath) {
		this.imageRelatedFilepath = imageRelatedFilepath;
	}


	@Column(name = "image_Related_location")
	public String getImageRelatedLocation() {
		return this.imageRelatedLocation;
	}

	public void setImageRelatedLocation(String imageRelatedLocation) {
		this.imageRelatedLocation = imageRelatedLocation;
	}

	@Column(name = "image_Related_sequence")
	public Integer getImageRelatedSequence() {
		return this.imageRelatedSequence;
	}

	public void setImageRelatedSequence(Integer imageRelatedSequence) {
		this.imageRelatedSequence = imageRelatedSequence;
	}


	@Column(name = "image_Related_Thumb_filepath")
	public String getImageRelatedThumbFilepath() {
		return this.imageRelatedThumbFilepath;
	}

	public void setImageRelatedThumbFilepath(String imageRelatedThumbFilepath) {
		this.imageRelatedThumbFilepath = imageRelatedThumbFilepath;
	}

	@Column(name = "image_Related_time")
	public Integer getImageRelatedTime() {
		return this.imageRelatedTime;
	}

	public void setImageRelatedTime(Integer imageRelatedTime) {
		this.imageRelatedTime = imageRelatedTime;
	}


	@Column(name = "image_Related_title")
	public String getImageRelatedTitle() {
		return this.imageRelatedTitle;
	}

	public void setImageRelatedTitle(String imageRelatedTitle) {
		this.imageRelatedTitle = imageRelatedTitle;
	}

	@Column(name = "material_id")
	public Integer getMaterialId() {
		return this.materialId;
	}

	public void setMaterialId(Integer materialId) {
		this.materialId = materialId;
	}

	@Column(name = "province_id")
	public Integer getProvinceId() {
		return this.provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}


	@Column(name="publish_date")
	public String getPublishDate() {
		return this.publishDate;
	}

	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}


	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updateTime")
	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "status")
	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}
	
	@Column(name = "creator")
	public String getCreator() {
		return creator;
	}


	public void setCreator(String creator) {
		this.creator = creator;
	}


	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date")
	public Date getCreatedDate() {
		return createdDate;
	}


	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getPerson() {
		return person;
	}

	public void setPerson(String person) {
		this.person = person;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Column(name = "location_long")
	public Double getLocationLong() {
		return locationLong;
	}

	public void setLocationLong(Double locationLong) {
		this.locationLong = locationLong;
	}

	@Column(name = "location_lat")
	public Double getLocationLat() {
		return locationLat;
	}

	public void setLocationLat(Double locationLat) {
		this.locationLat = locationLat;
	}
}