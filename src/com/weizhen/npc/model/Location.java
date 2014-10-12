package com.weizhen.npc.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Location database table.
 * 
 */
@Entity
@NamedQuery(name="Location.findAll", query="SELECT l FROM Location l")
public class Location implements Serializable {
	private static final long serialVersionUID = 1L;
	private int location_id;
	private String location_image;
	private int location_index;
	private double location_lat;
	private double location_lng;
	private String location_name;
	private String location_resume;
	private String location_title;
	private byte[] updateTime;

	public Location() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getLocation_id() {
		return this.location_id;
	}

	public void setLocation_id(int location_id) {
		this.location_id = location_id;
	}


	@Lob
	public String getLocation_image() {
		return this.location_image;
	}

	public void setLocation_image(String location_image) {
		this.location_image = location_image;
	}


	public int getLocation_index() {
		return this.location_index;
	}

	public void setLocation_index(int location_index) {
		this.location_index = location_index;
	}


	public double getLocation_lat() {
		return this.location_lat;
	}

	public void setLocation_lat(double location_lat) {
		this.location_lat = location_lat;
	}


	public double getLocation_lng() {
		return this.location_lng;
	}

	public void setLocation_lng(double location_lng) {
		this.location_lng = location_lng;
	}


	@Lob
	public String getLocation_name() {
		return this.location_name;
	}

	public void setLocation_name(String location_name) {
		this.location_name = location_name;
	}


	@Lob
	public String getLocation_resume() {
		return this.location_resume;
	}

	public void setLocation_resume(String location_resume) {
		this.location_resume = location_resume;
	}


	@Lob
	public String getLocation_title() {
		return this.location_title;
	}

	public void setLocation_title(String location_title) {
		this.location_title = location_title;
	}


	@Lob
	public byte[] getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(byte[] updateTime) {
		this.updateTime = updateTime;
	}

}