package com.weizhen.npc.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;


/**
 * The persistent class for the Image_Main database table.
 * 
 */
@Entity
@Table(name="Image_Main")
@NamedQuery(name="ImageMain.findAll", query="SELECT i FROM ImageMain i")
public class ImageMain implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer imageMainId;
	private Integer checkPublish;
	private Integer cityId;
	private String comment;
	private Integer congressId;
	private Integer continentId;
	private Integer countryId;
	private String imageMainDescription;
	private String imageMainFilepath;
	private String imageMainLocation;
	private Integer imageMainSequence;
	private Integer imageMainTime;
	private String imageMainTitle;
	private Integer materialId;
	private Integer provinceId;
	private String publishDate;
	private Date updateTime;
	
	private List<ImageRelated> imageRelateds;
	private List<Document> documents;
	
	private String status;

	public ImageMain() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "image_Main_id")
	public Integer getImageMainId() {
		return this.imageMainId;
	}

	public void setImageMainId(Integer imageMainId) {
		this.imageMainId = imageMainId;
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


	@Column(name = "image_Main_description")
	public String getImageMainDescription() {
		return this.imageMainDescription;
	}

	public void setImageMainDescription(String imageMainDescription) {
		this.imageMainDescription = imageMainDescription;
	}


	@Column(name = "image_Main_filepath")
	public String getImageMainFilepath() {
		return this.imageMainFilepath;
	}

	public void setImageMainFilepath(String imageMainFilepath) {
		this.imageMainFilepath = imageMainFilepath;
	}


	@Column(name = "image_Main_location")
	public String getImageMainLocation() {
		return this.imageMainLocation;
	}

	public void setImageMainLocation(String imageMainLocation) {
		this.imageMainLocation = imageMainLocation;
	}


	@Column(name = "image_Main_sequence")
	public Integer getImageMainSequence() {
		return this.imageMainSequence;
	}

	public void setImageMainSequence(Integer imageMainSequence) {
		this.imageMainSequence = imageMainSequence;
	}


	@Column(name = "image_Main_time")
	public Integer getImageMainTime() {
		return this.imageMainTime;
	}

	public void setImageMainTime(Integer imageMainTime) {
		this.imageMainTime = imageMainTime;
	}


	@Column(name = "image_Main_title")
	public String getImageMainTitle() {
		return this.imageMainTitle;
	}

	public void setImageMainTitle(String imageMainTitle) {
		this.imageMainTitle = imageMainTitle;
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
	
	@Transient
	public List<ImageRelated> getImageRelateds() {
		return this.imageRelateds;
	}
	
	public void setImageRelateds(List<ImageRelated> imageRelateds) {
		this.imageRelateds = imageRelateds;
	}

	@Transient
	public List<Document> getDocuments() {
		return documents;
	}


	public void setDocuments(List<Document> documents) {
		this.documents = documents;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}

}