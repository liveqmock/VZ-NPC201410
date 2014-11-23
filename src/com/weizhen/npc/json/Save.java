package com.weizhen.npc.json;

import java.io.Serializable;
import java.util.List;

public class Save implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer belongCongressId;
	private Integer belongImageMainId;
	private Integer imageMainId;
	private Integer imageRelatedId;
	private String contentTypeNo;
	private String contentId;
	private String contentTitle;
	private String contentDescription;
	private String imageMainImgPath;
	private Integer imageRelatedType;
	private String imageRelatedImgPath;
	private String imageRelatedVideoPath;
	private String imageRelatedVideoThumbImgPath;
	private String contentKeyword;
	private String contentDate;
	private String contentPerson;
	private String contentLocation;
	private Double contentLocationLong;
	private Double contentLocationLat;
	
	private List<String> paragraphContents;

	public Integer getBelongCongressId() {
		return belongCongressId;
	}

	public void setBelongCongressId(Integer belongCongressId) {
		this.belongCongressId = belongCongressId;
	}

	public Integer getBelongImageMainId() {
		return belongImageMainId;
	}

	public void setBelongImageMainId(Integer belongImageMainId) {
		this.belongImageMainId = belongImageMainId;
	}

	public Integer getImageMainId() {
		return imageMainId;
	}

	public void setImageMainId(Integer imageMainId) {
		this.imageMainId = imageMainId;
	}

	public Integer getImageRelatedId() {
		return imageRelatedId;
	}

	public void setImageRelatedId(Integer imageRelatedId) {
		this.imageRelatedId = imageRelatedId;
	}

	public String getContentTypeNo() {
		return contentTypeNo;
	}

	public void setContentTypeNo(String contentTypeNo) {
		this.contentTypeNo = contentTypeNo;
	}

	public String getContentId() {
		return contentId;
	}

	public void setContentId(String contentId) {
		this.contentId = contentId;
	}

	public String getContentTitle() {
		return contentTitle;
	}

	public void setContentTitle(String contentTitle) {
		this.contentTitle = contentTitle;
	}

	public String getContentDescription() {
		return contentDescription;
	}

	public void setContentDescription(String contentDescription) {
		this.contentDescription = contentDescription;
	}

	public String getImageMainImgPath() {
		return imageMainImgPath;
	}

	public void setImageMainImgPath(String imageMainImgPath) {
		this.imageMainImgPath = imageMainImgPath;
	}

	public Integer getImageRelatedType() {
		return imageRelatedType;
	}

	public void setImageRelatedType(Integer imageRelatedType) {
		this.imageRelatedType = imageRelatedType;
	}

	public String getImageRelatedImgPath() {
		return imageRelatedImgPath;
	}

	public void setImageRelatedImgPath(String imageRelatedImgPath) {
		this.imageRelatedImgPath = imageRelatedImgPath;
	}

	public String getImageRelatedVideoPath() {
		return imageRelatedVideoPath;
	}

	public void setImageRelatedVideoPath(String imageRelatedVideoPath) {
		this.imageRelatedVideoPath = imageRelatedVideoPath;
	}

	public String getImageRelatedVideoThumbImgPath() {
		return imageRelatedVideoThumbImgPath;
	}

	public void setImageRelatedVideoThumbImgPath(String imageRelatedVideoThumbImgPath) {
		this.imageRelatedVideoThumbImgPath = imageRelatedVideoThumbImgPath;
	}

	public String getContentKeyword() {
		return contentKeyword;
	}

	public void setContentKeyword(String contentKeyword) {
		this.contentKeyword = contentKeyword;
	}

	public String getContentDate() {
		return contentDate;
	}

	public void setContentDate(String contentDate) {
		this.contentDate = contentDate;
	}

	public String getContentPerson() {
		return contentPerson;
	}

	public void setContentPerson(String contentPerson) {
		this.contentPerson = contentPerson;
	}

	public String getContentLocation() {
		return contentLocation;
	}

	public void setContentLocation(String contentLocation) {
		this.contentLocation = contentLocation;
	}

	public Double getContentLocationLong() {
		return contentLocationLong;
	}

	public void setContentLocationLong(Double contentLocationLong) {
		this.contentLocationLong = contentLocationLong;
	}

	public Double getContentLocationLat() {
		return contentLocationLat;
	}

	public void setContentLocationLat(Double contentLocationLat) {
		this.contentLocationLat = contentLocationLat;
	}

	public List<String> getParagraphContents() {
		return paragraphContents;
	}

	public void setParagraphContents(List<String> paragraphContents) {
		this.paragraphContents = paragraphContents;
	}
	
}
