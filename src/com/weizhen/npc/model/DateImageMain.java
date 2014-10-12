package com.weizhen.npc.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Date_Image_Main database table.
 * 
 */
@Entity
@Table(name="Date_Image_Main")
@NamedQuery(name="DateImageMain.findAll", query="SELECT d FROM DateImageMain d")
public class DateImageMain implements Serializable {
	private static final long serialVersionUID = 1L;
	private int date_Image_Main_id;
	private int image_Main_id;
	private byte[] publishDate;

	public DateImageMain() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getDate_Image_Main_id() {
		return this.date_Image_Main_id;
	}

	public void setDate_Image_Main_id(int date_Image_Main_id) {
		this.date_Image_Main_id = date_Image_Main_id;
	}


	public int getImage_Main_id() {
		return this.image_Main_id;
	}

	public void setImage_Main_id(int image_Main_id) {
		this.image_Main_id = image_Main_id;
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