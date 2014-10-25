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
 * The persistent class for the Date_Image_Main database table.
 * 
 */
@Entity
@Table(name="Date_Image_Main")
@NamedQuery(name="DateImageMain.findAll", query="SELECT d FROM DateImageMain d")
public class DateImageMain implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer dateImageMainId;
	private ImageMain imageMain;
	private Date publishDate;

	public DateImageMain() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "date_Image_Main_id")
	public Integer getDateImageMainId() {
		return this.dateImageMainId;
	}

	public void setDateImageMainId(Integer dateImageMainId) {
		this.dateImageMainId = dateImageMainId;
	}


	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "image_Main_id")
	public ImageMain getImageMain() {
		return this.imageMain;
	}

	public void setImageMain(ImageMain imageMain) {
		this.imageMain = imageMain;
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