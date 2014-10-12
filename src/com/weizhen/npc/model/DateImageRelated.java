package com.weizhen.npc.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Date_Image_Related database table.
 * 
 */
@Entity
@Table(name="Date_Image_Related")
@NamedQuery(name="DateImageRelated.findAll", query="SELECT d FROM DateImageRelated d")
public class DateImageRelated implements Serializable {
	private static final long serialVersionUID = 1L;
	private int date_Image_Related_id;
	private int image_Related_id;
	private byte[] publishDate;

	public DateImageRelated() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getDate_Image_Related_id() {
		return this.date_Image_Related_id;
	}

	public void setDate_Image_Related_id(int date_Image_Related_id) {
		this.date_Image_Related_id = date_Image_Related_id;
	}


	public int getImage_Related_id() {
		return this.image_Related_id;
	}

	public void setImage_Related_id(int image_Related_id) {
		this.image_Related_id = image_Related_id;
	}


	@Lob
	@Column(name="publish_date")
	public byte[] getPublishDate() {
		return this.publishDate;
	}

	public void setPublishDate(byte[] publishDate) {
		this.publishDate = publishDate;
	}

}