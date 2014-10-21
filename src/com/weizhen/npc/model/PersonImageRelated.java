package com.weizhen.npc.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the Person_Image_Related database table.
 * 
 */
@Entity
@Table(name="Person_Image_Related")
@NamedQuery(name="PersonImageRelated.findAll", query="SELECT p FROM PersonImageRelated p")
public class PersonImageRelated implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer personImageRelatedId;
	private ImageRelated imageRelated;
	private Person person;
	private Date updateTime;

	public PersonImageRelated() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "person_Image_Related_id")
	public Integer getPersonImageRelatedId() {
		return this.personImageRelatedId;
	}

	public void setPersonImageRelatedId(Integer personImageRelatedId) {
		this.personImageRelatedId = personImageRelatedId;
	}


	public ImageRelated getImageRelated() {
		return this.imageRelated;
	}

	public void setImageRelated(ImageRelated imageRelated) {
		this.imageRelated = imageRelated;
	}


	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}


	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updateTime")
	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}