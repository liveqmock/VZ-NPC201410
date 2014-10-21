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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the Date_Document database table.
 * 
 */
@Entity
@Table(name="Date_Document")
@NamedQuery(name="DateDocument.findAll", query="SELECT d FROM DateDocument d")
public class DateDocument implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer dateDocumentId;
	private Document document;
	private Date publishDate;

	public DateDocument() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "date_Document_id")
	public Integer getDateDocumentId() {
		return this.dateDocumentId;
	}

	public void setDateDocumentId(Integer dateDocumentId) {
		this.dateDocumentId = dateDocumentId;
	}

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "document_id")
	public Document getDocument() {
		return this.document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}


	@Temporal(TemporalType.DATE)
	@Column(name="publish_date")
	public Date getPublishDate() {
		return this.publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

}