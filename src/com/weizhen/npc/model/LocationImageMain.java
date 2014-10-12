package com.weizhen.npc.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Location_Image_Main database table.
 * 
 */
@Entity
@Table(name="Location_Image_Main")
@NamedQuery(name="LocationImageMain.findAll", query="SELECT l FROM LocationImageMain l")
public class LocationImageMain implements Serializable {
	private static final long serialVersionUID = 1L;
	private int location_Image_Main_id;
	private int image_Main_id;
	private int location_id;
	private byte[] updateTime;

	public LocationImageMain() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getLocation_Image_Main_id() {
		return this.location_Image_Main_id;
	}

	public void setLocation_Image_Main_id(int location_Image_Main_id) {
		this.location_Image_Main_id = location_Image_Main_id;
	}


	public int getImage_Main_id() {
		return this.image_Main_id;
	}

	public void setImage_Main_id(int image_Main_id) {
		this.image_Main_id = image_Main_id;
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