package com.weizhen.npc.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Person_Image_Related database table.
 * 
 */
@Entity
@Table(name="Person_Image_Related")
@NamedQuery(name="PersonImageRelated.findAll", query="SELECT p FROM PersonImageRelated p")
public class PersonImageRelated implements Serializable {
	private static final long serialVersionUID = 1L;
	private int person_Image_Related_id;
	private int image_Related_id;
	private int person_id;
	private byte[] updateTime;

	public PersonImageRelated() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getPerson_Image_Related_id() {
		return this.person_Image_Related_id;
	}

	public void setPerson_Image_Related_id(int person_Image_Related_id) {
		this.person_Image_Related_id = person_Image_Related_id;
	}


	public int getImage_Related_id() {
		return this.image_Related_id;
	}

	public void setImage_Related_id(int image_Related_id) {
		this.image_Related_id = image_Related_id;
	}


	public int getPerson_id() {
		return this.person_id;
	}

	public void setPerson_id(int person_id) {
		this.person_id = person_id;
	}


	@Lob
	public byte[] getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(byte[] updateTime) {
		this.updateTime = updateTime;
	}

}