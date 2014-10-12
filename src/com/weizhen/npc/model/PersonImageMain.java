package com.weizhen.npc.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Person_Image_Main database table.
 * 
 */
@Entity
@Table(name="Person_Image_Main")
@NamedQuery(name="PersonImageMain.findAll", query="SELECT p FROM PersonImageMain p")
public class PersonImageMain implements Serializable {
	private static final long serialVersionUID = 1L;
	private int person_Image_Main_id;
	private int image_Main_id;
	private int person_id;
	private byte[] updateTime;

	public PersonImageMain() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getPerson_Image_Main_id() {
		return this.person_Image_Main_id;
	}

	public void setPerson_Image_Main_id(int person_Image_Main_id) {
		this.person_Image_Main_id = person_Image_Main_id;
	}


	public int getImage_Main_id() {
		return this.image_Main_id;
	}

	public void setImage_Main_id(int image_Main_id) {
		this.image_Main_id = image_Main_id;
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