package com.weizhen.npc.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Person database table.
 * 
 */
@Entity
@NamedQuery(name="Person.findAll", query="SELECT p FROM Person p")
public class Person implements Serializable {
	private static final long serialVersionUID = 1L;
	private int person_id;
	private String person_academic_level;
	private int person_birthday;
	private String person_birthplace_province;
	private String person_ethnic;
	private String person_Image;
	private String person_name;
	private String person_party_grouping;
	private String person_resume;
	private String person_sex;
	private String person_workplace_province;
	private byte[] updateTime;

	public Person() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getPerson_id() {
		return this.person_id;
	}

	public void setPerson_id(int person_id) {
		this.person_id = person_id;
	}


	@Lob
	public String getPerson_academic_level() {
		return this.person_academic_level;
	}

	public void setPerson_academic_level(String person_academic_level) {
		this.person_academic_level = person_academic_level;
	}


	public int getPerson_birthday() {
		return this.person_birthday;
	}

	public void setPerson_birthday(int person_birthday) {
		this.person_birthday = person_birthday;
	}


	@Lob
	public String getPerson_birthplace_province() {
		return this.person_birthplace_province;
	}

	public void setPerson_birthplace_province(String person_birthplace_province) {
		this.person_birthplace_province = person_birthplace_province;
	}


	@Lob
	public String getPerson_ethnic() {
		return this.person_ethnic;
	}

	public void setPerson_ethnic(String person_ethnic) {
		this.person_ethnic = person_ethnic;
	}


	@Lob
	public String getPerson_Image() {
		return this.person_Image;
	}

	public void setPerson_Image(String person_Image) {
		this.person_Image = person_Image;
	}


	@Lob
	public String getPerson_name() {
		return this.person_name;
	}

	public void setPerson_name(String person_name) {
		this.person_name = person_name;
	}


	@Lob
	public String getPerson_party_grouping() {
		return this.person_party_grouping;
	}

	public void setPerson_party_grouping(String person_party_grouping) {
		this.person_party_grouping = person_party_grouping;
	}


	@Lob
	public String getPerson_resume() {
		return this.person_resume;
	}

	public void setPerson_resume(String person_resume) {
		this.person_resume = person_resume;
	}


	@Lob
	public String getPerson_sex() {
		return this.person_sex;
	}

	public void setPerson_sex(String person_sex) {
		this.person_sex = person_sex;
	}


	@Lob
	public String getPerson_workplace_province() {
		return this.person_workplace_province;
	}

	public void setPerson_workplace_province(String person_workplace_province) {
		this.person_workplace_province = person_workplace_province;
	}


	@Lob
	public byte[] getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(byte[] updateTime) {
		this.updateTime = updateTime;
	}

}