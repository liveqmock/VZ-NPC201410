package com.weizhen.npc.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the Person database table.
 * 
 */
@Entity
@NamedQuery(name="Person.findAll", query="SELECT p FROM Person p")
public class Person implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer personId;
	private String personAcademicLevel;
	private Date personBirthday;
	private String personBirthplaceProvince;
	private String personEthnic;
	private String personImage;
	private String personName;
	private String personPartyGrouping;
	private String personResume;
	private String personSex;
	private String personWorkplaceProvince;
	private Date updateTime;

	public Person() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="person_id")
	public Integer getPersonId() {
		return this.personId;
	}

	public void setPersonId(Integer personId) {
		this.personId = personId;
	}


	@Column(name="person_academic_level")
	public String getPersonAcademicLevel() {
		return this.personAcademicLevel;
	}

	public void setPersonAcademicLevel(String personAcademicLevel) {
		this.personAcademicLevel = personAcademicLevel;
	}


	@Temporal(TemporalType.DATE)
	@Column(name="person_birthday")
	public Date getPersonBirthday() {
		return this.personBirthday;
	}

	public void setPersonBirthday(Date personBirthday) {
		this.personBirthday = personBirthday;
	}


	@Column(name="person_birthplace_province")
	public String getPersonBirthplaceProvince() {
		return this.personBirthplaceProvince;
	}

	public void setPersonBirthplaceProvince(String personBirthplaceProvince) {
		this.personBirthplaceProvince = personBirthplaceProvince;
	}


	@Column(name="person_ethnic")
	public String getPersonEthnic() {
		return this.personEthnic;
	}

	public void setPersonEthnic(String personEthnic) {
		this.personEthnic = personEthnic;
	}


	@Column(name="person_Image")
	public String getPersonImage() {
		return this.personImage;
	}

	public void setPersonImage(String personImage) {
		this.personImage = personImage;
	}


	@Column(name="person_name")
	public String getPersonName() {
		return this.personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}


	@Column(name="person_party_grouping")
	public String getPersonPartyGrouping() {
		return this.personPartyGrouping;
	}

	public void setPersonPartyGrouping(String personPartyGrouping) {
		this.personPartyGrouping = personPartyGrouping;
	}


	@Column(name="person_resume")
	public String getPersonResume() {
		return this.personResume;
	}

	public void setPersonResume(String personResume) {
		this.personResume = personResume;
	}


	@Column(name="person_sex")
	public String getPersonSex() {
		return this.personSex;
	}

	public void setPersonSex(String personSex) {
		this.personSex = personSex;
	}


	@Column(name="person_workplace_province")
	public String getPersonWorkplaceProvince() {
		return this.personWorkplaceProvince;
	}

	public void setPersonWorkplaceProvince(String personWorkplaceProvince) {
		this.personWorkplaceProvince = personWorkplaceProvince;
	}


	@Temporal(TemporalType.TIMESTAMP)
	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}