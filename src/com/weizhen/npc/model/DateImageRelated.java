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
 * The persistent class for the Date_Image_Related database table.
 * 
 */
@Entity
@Table(name="Date_Image_Related")
@NamedQuery(name="DateImageRelated.findAll", query="SELECT d FROM DateImageRelated d")
public class DateImageRelated implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer dateImageRelatedId;
	private ImageRelated imageRelated;
	private Date publishDate;

	public DateImageRelated() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "date_Image_Related_id")
	public Integer getDate_Image_Related_id() {
		return this.dateImageRelatedId;
	}

	public void setDate_Image_Related_id(Integer date_Image_Related_id) {
		this.dateImageRelatedId = date_Image_Related_id;
	}


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "image_Related_id")
	public ImageRelated getImageRelated() {
		return this.imageRelated;
	}

	public void setImageRelated(ImageRelated imageRelated) {
		this.imageRelated = imageRelated;
	}


	@Temporal(TemporalType.DATE)
	@Column(name="publish_date")
	public Date getPublishDate() {
		return this.publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

}