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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the Paragraph database table.
 * 
 */
@Entity
@NamedQuery(name="Paragraph.findAll", query="SELECT p FROM Paragraph p")
public class Paragraph implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer paragraphId;
	private Document document;
	private String paragraphContent;
	private String paragraphIndex;
	private Integer paragraphSequence;
	private Date updateTime;

	public Paragraph() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "paragraph_id")
	public Integer getParagraphId() {
		return this.paragraphId;
	}

	public void setParagraphId(Integer paragraphId) {
		this.paragraphId = paragraphId;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="paragraph_id")
	public Document getDocument() {
		return this.document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}


	@Column(name = "paragraph_content")
	public String getParagraphContent() {
		return this.paragraphContent;
	}

	public void setParagraphContent(String paragraphContent) {
		this.paragraphContent = paragraphContent;
	}


	@Column(name = "paragraph_index")
	public String getParagraphIndex() {
		return this.paragraphIndex;
	}

	public void setParagraphIndex(String paragraphIndex) {
		this.paragraphIndex = paragraphIndex;
	}


	@Column(name = "paragraph_sequence")
	public Integer getParagraphSequence() {
		return this.paragraphSequence;
	}

	public void setParagraphSequence(Integer paragraphSequence) {
		this.paragraphSequence = paragraphSequence;
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