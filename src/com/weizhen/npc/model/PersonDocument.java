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
 * The persistent class for the Person_Document database table.
 * 
 */
@Entity
@Table(name="Person_Document")
@NamedQuery(name="PersonDocument.findAll", query="SELECT p FROM PersonDocument p")
public class PersonDocument implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer personDocumentId;
	private Document document;
	private Person person;
	private Date updateTime;

	public PersonDocument() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "person_Document_id")
	public Integer getPersonDocumentId() {
		return this.personDocumentId;
	}

	public void setPersonDocumentId(Integer personDocumentId) {
		this.personDocumentId = personDocumentId;
	}


	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="document_id")
	public Document getDocument() {
		return this.document;
	}

	public void setDocument(Document document) {
		this.document = document;
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