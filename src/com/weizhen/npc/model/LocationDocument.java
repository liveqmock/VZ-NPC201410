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
 * The persistent class for the Location_Document database table.
 * 
 */
@Entity
@Table(name="Location_Document")
@NamedQuery(name="LocationDocument.findAll", query="SELECT l FROM LocationDocument l")
public class LocationDocument implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer locationDocumentId;
	private Document document;
	private Location location;
	private Date updateTime;

	public LocationDocument() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "location_Document_id")
	public Integer getLocationDocumentId() {
		return this.locationDocumentId;
	}

	public void setLocationDocumentId(Integer locationDocumentId) {
		this.locationDocumentId = locationDocumentId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "document_id")
	public Document getDocument() {
		return this.document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "location_id")
	public Location getLocation() {
		return this.location;
	}

	public void setLocation(Location location) {
		this.location = location;
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