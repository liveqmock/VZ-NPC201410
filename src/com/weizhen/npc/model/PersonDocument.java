package com.weizhen.npc.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Person_Document database table.
 * 
 */
@Entity
@Table(name="Person_Document")
@NamedQuery(name="PersonDocument.findAll", query="SELECT p FROM PersonDocument p")
public class PersonDocument implements Serializable {
	private static final long serialVersionUID = 1L;
	private int person_Document_id;
	private int document_id;
	private int person_id;
	private byte[] updateTime;

	public PersonDocument() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getPerson_Document_id() {
		return this.person_Document_id;
	}

	public void setPerson_Document_id(int person_Document_id) {
		this.person_Document_id = person_Document_id;
	}


	public int getDocument_id() {
		return this.document_id;
	}

	public void setDocument_id(int document_id) {
		this.document_id = document_id;
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