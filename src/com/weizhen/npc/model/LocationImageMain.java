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
 * The persistent class for the Location_Image_Main database table.
 * 
 */
@Entity
@Table(name="Location_Image_Main")
@NamedQuery(name="LocationImageMain.findAll", query="SELECT l FROM LocationImageMain l")
public class LocationImageMain implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer locationImageMainId;
	private ImageMain imageMain;
	private Location location;
	private Date updateTime;

	public LocationImageMain() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "location_Image_Main_id")
	public Integer getLocationImageMainId() {
		return this.locationImageMainId;
	}

	public void setLocationImageMainId(Integer locationImageMainId) {
		this.locationImageMainId = locationImageMainId;
	}


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "image_Main_id")
	public ImageMain getImageMain() {
		return this.imageMain;
	}

	public void setImageMain(ImageMain imageMain) {
		this.imageMain = imageMain;
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