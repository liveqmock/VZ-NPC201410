package com.weizhen.npc.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
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
	private Integer personBirthday;
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


	@Column(name="person_birthday")
	public Integer getPersonBirthday() {
		return this.personBirthday;
	}

	public void setPersonBirthday(Integer personBirthday) {
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
	public String getPerson_Image() {
		return this.personImage;
	}

	public void setPerson_Image(String person_Image) {
		this.personImage = person_Image;
	}


	@Column(name="person_name")
	public String getPerson_name() {
		return this.personName;
	}

	public void setPerson_name(String person_name) {
		this.personName = person_name;
	}


	@Column(name="person_party_grouping")
	public String getPerson_party_grouping() {
		return this.personPartyGrouping;
	}

	public void setPerson_party_grouping(String person_party_grouping) {
		this.personPartyGrouping = person_party_grouping;
	}


	@Column(name="person_resume")
	public String getPerson_resume() {
		return this.personResume;
	}

	public void setPerson_resume(String person_resume) {
		this.personResume = person_resume;
	}


	@Column(name="person_sex")
	public String getPerson_sex() {
		return this.personSex;
	}

	public void setPerson_sex(String person_sex) {
		this.personSex = person_sex;
	}


	@Column(name="person_workplace_province")
	public String getPerson_workplace_province() {
		return this.personWorkplaceProvince;
	}

	public void setPerson_workplace_province(String person_workplace_province) {
		this.personWorkplaceProvince = person_workplace_province;
	}


	@Temporal(TemporalType.TIMESTAMP)
	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}