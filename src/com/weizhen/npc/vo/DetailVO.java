package com.weizhen.npc.vo;

import java.io.Serializable;
import java.util.Map;

import com.weizhen.npc.model.Document;
import com.weizhen.npc.model.ImageMain;
import com.weizhen.npc.model.ImageRelated;

public class DetailVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String detailType;
	private Integer id;
	private String materialType;
	private String title;
	private String description;
	private String filepath;
	private String thumbFilepath;
	
	public DetailVO() {
		
	}
	
	public DetailVO(Map<String, Object> imageMain) {
		if (null == imageMain) return;
		
		this.detailType = "imageMain";
		this.id = (Integer) imageMain.get("imageMainId");
		this.materialType = Constants.MATERIAL_TYPE_IMAGE;
		this.title = (String) imageMain.get("imageMainTitle");
		this.description = (String) imageMain.get("imageMainDescription");
		this.filepath = (String) imageMain.get("imageMainFilepath");
		this.thumbFilepath = (String) imageMain.get("imageMainFilepath");
	}
	
	public DetailVO(ImageMain imageMain) {
		if (null == imageMain) return;
		
		this.detailType = "imageMain";
		this.id = imageMain.getImageMainId();
		this.materialType = Constants.MATERIAL_TYPE_IMAGE;
		this.title = imageMain.getImageMainTitle();
		this.description = imageMain.getImageMainDescription();
		this.filepath = imageMain.getImageMainFilepath();
		this.thumbFilepath = imageMain.getImageMainFilepath();
	}
	
	public DetailVO(ImageRelated imageRelated) {
		if (null == imageRelated) return;
		
		this.detailType = "imageRelated";
		this.id = imageRelated.getImageRelatedId();
		this.materialType = imageRelated.getMaterialId() == 2 ? Constants.MATERIAL_TYPE_VIDEO : Constants.MATERIAL_TYPE_IMAGE;
		this.title = imageRelated.getImageRelatedTitle();
		this.description = imageRelated.getImageRelatedDescription();
		this.filepath = imageRelated.getImageRelatedFilepath();
		this.thumbFilepath = imageRelated.getImageRelatedThumbFilepath();
	}
	
	public DetailVO(Document document) {
		if (null == document) return;
		
		this.detailType = "document";
		this.id = document.getDocumentId();
		this.materialType = Constants.MATERIAL_TYPE_ARTICLE;
		this.title = document.getDocumentTitle();
		this.description = document.comboParagraphContent();
	}
	
	public String getDetailType() {
		return detailType;
	}
	public void setDetailType(String detailType) {
		this.detailType = detailType;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getMaterialType() {
		return materialType;
	}

	public void setMaterialType(String materialType) {
		this.materialType = materialType;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	public String getThumbFilepath() {
		return thumbFilepath;
	}
	public void setThumbFilepath(String thumbFilepath) {
		this.thumbFilepath = thumbFilepath;
	}
}
