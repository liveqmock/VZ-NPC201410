package com.weizhen.npc.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.weizhen.npc.base.StatusEntity;


/**
 * The persistent class for the Document database table.
 * 
 */
@Entity
@NamedQuery(name="Document.findAll", query="SELECT d FROM Document d")
public class Document implements Serializable, StatusEntity {
	private static final long serialVersionUID = 1L;
	private Integer documentId;
	private Integer checkPublish;
	private Integer cityId;
	private String comment;
	private Integer congressId;
	private Integer continentId;
	private Integer countryId;
	private String documentDescription;
	private String documentLocation;
	private String documentRef;
	private Integer documentSequence;
	private Integer documentTime;
	private String documentTitle;
	private Integer imageMainId;
	private Integer materialId;
	private Integer provinceId;
	private String publishDate;
	private Date updateTime;
	
	private List<Paragraph> paragraphs;
	
	private String status;
	private String creator;
	private Date createdDate;

	public Document() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "document_id")
	public Integer getDocumentId() {
		return this.documentId;
	}

	public void setDocumentId(Integer documentId) {
		this.documentId = documentId;
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


	@Column(name = "document_description")
	public String getDocumentDescription() {
		return this.documentDescription;
	}

	public void setDocumentDescription(String documentDescription) {
		this.documentDescription = documentDescription;
	}


	@Column(name = "document_location")
	public String getDocumentLocation() {
		return this.documentLocation;
	}

	public void setDocumentLocation(String documentLocation) {
		this.documentLocation = documentLocation;
	}


	@Column(name = "document_ref")
	public String getDocumentRef() {
		return this.documentRef;
	}

	public void setDocumentRef(String documentRef) {
		this.documentRef = documentRef;
	}


	@Column(name = "document_sequence")
	public Integer getDocumentSequence() {
		return this.documentSequence;
	}

	public void setDocumentSequence(Integer documentSequence) {
		this.documentSequence = documentSequence;
	}


	@Column(name = "document_time")
	public Integer getDocumentTime() {
		return this.documentTime;
	}

	public void setDocumentTime(Integer documentTime) {
		this.documentTime = documentTime;
	}


	@Column(name = "document_title")
	public String getDocumentTitle() {
		return this.documentTitle;
	}

	public void setDocumentTitle(String documentTitle) {
		this.documentTitle = documentTitle;
	}


	@Column(name = "image_Main_id")
	public Integer getImageMainId() {
		return this.imageMainId;
	}

	public void setImageMainId(Integer imageMainId) {
		this.imageMainId = imageMainId;
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


	//bi-directional many-to-one association to CongressResume
	@OneToMany(mappedBy="document",fetch=FetchType.EAGER)
	@OrderBy("paragraphSequence")
	public List<Paragraph> getParagraphs() {
		return paragraphs;
	}


	public void setParagraphs(List<Paragraph> paragraphs) {
		this.paragraphs = paragraphs;
	}
	
	public String comboParagraphContent() {
		return comboParagraphContent("<br />");
	}
	
	public String comboParagraphContent(String split) {
		if (null == this.paragraphs || this.paragraphs.size() == 0)
			return null;
		
		String paragraphContent = this.paragraphs.get(0).getParagraphContent();
		for(int index = 1; index < this.paragraphs.size(); index++) {
			paragraphContent += split + this.paragraphs.get(index).getParagraphContent();
		}
		
		return paragraphContent;
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
	
	
}