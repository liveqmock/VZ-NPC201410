package com.weizhen.npc.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Document database table.
 * 
 */
@Entity
@NamedQuery(name="Document.findAll", query="SELECT d FROM Document d")
public class Document implements Serializable {
	private static final long serialVersionUID = 1L;
	private int document_id;
	private int check_Publish;
	private int city_id;
	private String comment;
	private int congress_id;
	private int continent_id;
	private int country_id;
	private String document_description;
	private String document_location;
	private String document_ref;
	private int document_sequence;
	private int document_time;
	private String document_title;
	private int image_Main_id;
	private int material_id;
	private int province_id;
	private String publishDate;
	private byte[] updateTime;

	public Document() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getDocument_id() {
		return this.document_id;
	}

	public void setDocument_id(int document_id) {
		this.document_id = document_id;
	}


	public int getCheck_Publish() {
		return this.check_Publish;
	}

	public void setCheck_Publish(int check_Publish) {
		this.check_Publish = check_Publish;
	}


	public int getCity_id() {
		return this.city_id;
	}

	public void setCity_id(int city_id) {
		this.city_id = city_id;
	}


	@Lob
	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}


	public int getCongress_id() {
		return this.congress_id;
	}

	public void setCongress_id(int congress_id) {
		this.congress_id = congress_id;
	}


	public int getContinent_id() {
		return this.continent_id;
	}

	public void setContinent_id(int continent_id) {
		this.continent_id = continent_id;
	}


	public int getCountry_id() {
		return this.country_id;
	}

	public void setCountry_id(int country_id) {
		this.country_id = country_id;
	}


	@Lob
	public String getDocument_description() {
		return this.document_description;
	}

	public void setDocument_description(String document_description) {
		this.document_description = document_description;
	}


	@Lob
	public String getDocument_location() {
		return this.document_location;
	}

	public void setDocument_location(String document_location) {
		this.document_location = document_location;
	}


	@Lob
	public String getDocument_ref() {
		return this.document_ref;
	}

	public void setDocument_ref(String document_ref) {
		this.document_ref = document_ref;
	}


	public int getDocument_sequence() {
		return this.document_sequence;
	}

	public void setDocument_sequence(int document_sequence) {
		this.document_sequence = document_sequence;
	}


	public int getDocument_time() {
		return this.document_time;
	}

	public void setDocument_time(int document_time) {
		this.document_time = document_time;
	}


	@Lob
	public String getDocument_title() {
		return this.document_title;
	}

	public void setDocument_title(String document_title) {
		this.document_title = document_title;
	}


	public int getImage_Main_id() {
		return this.image_Main_id;
	}

	public void setImage_Main_id(int image_Main_id) {
		this.image_Main_id = image_Main_id;
	}


	public int getMaterial_id() {
		return this.material_id;
	}

	public void setMaterial_id(int material_id) {
		this.material_id = material_id;
	}


	public int getProvince_id() {
		return this.province_id;
	}

	public void setProvince_id(int province_id) {
		this.province_id = province_id;
	}


	@Lob
	@Column(name="publish_date")
	public String getPublishDate() {
		return this.publishDate;
	}

	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}


	@Lob
	public byte[] getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(byte[] updateTime) {
		this.updateTime = updateTime;
	}

}