package com.weizhen.npc.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Paragraph database table.
 * 
 */
@Entity
@NamedQuery(name="Paragraph.findAll", query="SELECT p FROM Paragraph p")
public class Paragraph implements Serializable {
	private static final long serialVersionUID = 1L;
	private int paragraph_id;
	private int document_id;
	private String paragraph_content;
	private String paragraph_index;
	private int paragraph_sequence;
	private byte[] updateTime;

	public Paragraph() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getParagraph_id() {
		return this.paragraph_id;
	}

	public void setParagraph_id(int paragraph_id) {
		this.paragraph_id = paragraph_id;
	}


	public int getDocument_id() {
		return this.document_id;
	}

	public void setDocument_id(int document_id) {
		this.document_id = document_id;
	}


	@Lob
	public String getParagraph_content() {
		return this.paragraph_content;
	}

	public void setParagraph_content(String paragraph_content) {
		this.paragraph_content = paragraph_content;
	}


	@Lob
	public String getParagraph_index() {
		return this.paragraph_index;
	}

	public void setParagraph_index(String paragraph_index) {
		this.paragraph_index = paragraph_index;
	}


	public int getParagraph_sequence() {
		return this.paragraph_sequence;
	}

	public void setParagraph_sequence(int paragraph_sequence) {
		this.paragraph_sequence = paragraph_sequence;
	}


	@Lob
	public byte[] getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(byte[] updateTime) {
		this.updateTime = updateTime;
	}

}