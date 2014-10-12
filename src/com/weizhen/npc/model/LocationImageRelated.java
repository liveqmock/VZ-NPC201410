package com.weizhen.npc.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Location_Image_Related database table.
 * 
 */
@Entity
@Table(name="Location_Image_Related")
@NamedQuery(name="LocationImageRelated.findAll", query="SELECT l FROM LocationImageRelated l")
public class LocationImageRelated implements Serializable {
	private static final long serialVersionUID = 1L;
	private int location_Image_Related_id;
	private int image_Related_id;
	private int location_id;
	private byte[] updateTime;

	public LocationImageRelated() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getLocation_Image_Related_id() {
		return this.location_Image_Related_id;
	}

	public void setLocation_Image_Related_id(int location_Image_Related_id) {
		this.location_Image_Related_id = location_Image_Related_id;
	}


	public int getImage_Related_id() {
		return this.image_Related_id;
	}

	public void setImage_Related_id(int image_Related_id) {
		this.image_Related_id = image_Related_id;
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