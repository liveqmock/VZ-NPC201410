package com.weizhen.npc.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Date_Document database table.
 * 
 */
@Entity
@Table(name="Date_Document")
@NamedQuery(name="DateDocument.findAll", query="SELECT d FROM DateDocument d")
public class DateDocument implements Serializable {
	private static final long serialVersionUID = 1L;
	private int date_Document_id;
	private int document_id;
	private byte[] publishDate;

	public DateDocument() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getDate_Document_id() {
		return this.date_Document_id;
	}

	public void setDate_Document_id(int date_Document_id) {
		this.date_Document_id = date_Document_id;
	}


	public int getDocument_id() {
		return this.document_id;
	}

	public void setDocument_id(int document_id) {
		this.document_id = document_id;
	}


	@Lob
	@Column(name="publish_date")
	public byte[] getPublishDate() {
		return this.publishDate;
	}

	public void setPublishDate(byte[] publishDate) {
		this.publishDate = publishDate;
	}

}