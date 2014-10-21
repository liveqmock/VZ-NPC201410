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


/**
 * The persistent class for the Person_Image_Main database table.
 * 
 */
@Entity
@Table(name="Person_Image_Main")
@NamedQuery(name="PersonImageMain.findAll", query="SELECT p FROM PersonImageMain p")
public class PersonImageMain implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer personImageMainId;
	private ImageMain imageMain;
	private Person person;
	private Date updateTime;

	public PersonImageMain() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "person_Image_Main_id")
	public Integer getPersonImageMainId() {
		return this.personImageMainId;
	}

	public void setPersonImageMainId(Integer personImageMainId) {
		this.personImageMainId = personImageMainId;
	}


	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="image_main_id")
	public ImageMain getImageMain() {
		return this.imageMain;
	}

	public void setImageMain(ImageMain imageMain) {
		this.imageMain = imageMain;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="person_id")
	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}


	@Column(name = "updateTime")
	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}