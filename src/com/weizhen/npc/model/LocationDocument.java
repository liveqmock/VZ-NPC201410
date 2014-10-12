package com.weizhen.npc.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Location_Document database table.
 * 
 */
@Entity
@Table(name="Location_Document")
@NamedQuery(name="LocationDocument.findAll", query="SELECT l FROM LocationDocument l")
public class LocationDocument implements Serializable {
	private static final long serialVersionUID = 1L;
	private int location_Document_id;
	private int document_id;
	private int location_id;
	private byte[] updateTime;

	public LocationDocument() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getLocation_Document_id() {
		return this.location_Document_id;
	}

	public void setLocation_Document_id(int location_Document_id) {
		this.location_Document_id = location_Document_id;
	}


	public int getDocument_id() {
		return this.document_id;
	}

	public void setDocument_id(int document_id) {
		this.document_id = document_id;
	}


	public int getLocation_id() {
		return this.location_id;
	}

	public void setLocation_id(int location_id) {
		this.location_id = location_id;
	}


	@Lob
	public byte[] getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(byte[] updateTime) {
		this.updateTime = updateTime;
	}

}