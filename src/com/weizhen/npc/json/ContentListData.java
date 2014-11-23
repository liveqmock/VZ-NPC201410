package com.weizhen.npc.json;

import java.io.Serializable;

import com.weizhen.npc.model.Document;
import com.weizhen.npc.model.ImageMain;
import com.weizhen.npc.model.ImageRelated;
import com.weizhen.npc.utils.MaterialTypeEnum;
import com.weizhen.npc.utils.ResourceTypeEnum;

public class ContentListData implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final Integer UNIT = 1000000;

	private Integer id;
	private Integer pid;
	private String title;
	private Integer type;
	private String typeDisplay;
	private Integer resourceId;
	private String resourceType;
	
	public static ContentListData from(ImageMain imageMain) {
		ContentListData data = new ContentListData();
		data.setId(imageMain.getImageMainId());
		data.setTitle("【主题】" + imageMain.getImageMainTitle());
		data.setType(0);
		data.setTypeDisplay("主题");
		data.setResourceId(imageMain.getImageMainId());
		data.setResourceType(ResourceTypeEnum.IMAGEMAIN.getItemCode());
		
		return data;
	}
	
	public static ContentListData from(ImageMain imageMain, ImageRelated imageRelated) {
		ContentListData data = new ContentListData();
		data.setId((imageMain.getImageMainId() + 1) * UNIT + imageRelated.getImageRelatedId());
		data.setPid(imageMain.getImageMainId());
		
		String prefix = "【相关图片】";
		if (MaterialTypeEnum.VIDEO.getItemCode().intValue() ==  imageRelated.getMaterialId())
			prefix = "【相关视频】";
		data.setTitle(prefix + imageRelated.getImageRelatedTitle());
		
		data.setType(1);
		data.setTypeDisplay("相关资料");
		data.setResourceId(imageRelated.getImageRelatedId());
		data.setResourceType(ResourceTypeEnum.IMAGERELATED.getItemCode());
		
		return data;
	}
	
	public static ContentListData from(ImageMain imageMain, Document document) {
		ContentListData data = new ContentListData();
		data.setId((imageMain.getImageMainId() + 1) * UNIT + document.getDocumentId());
		data.setPid(imageMain.getImageMainId());
		data.setTitle("【相关文章】" + document.getDocumentTitle());
		data.setType(1);
		data.setTypeDisplay("相关资料");
		data.setResourceId(document.getDocumentId());
		data.setResourceType(ResourceTypeEnum.DOCUMENT.getItemCode());
		
		return data;
	}
	
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getTypeDisplay() {
		return typeDisplay;
	}

	public void setTypeDisplay(String typeDisplay) {
		this.typeDisplay = typeDisplay;
	}

	public Integer getResourceId() {
		return resourceId;
	}

	public void setResourceId(Integer resourceId) {
		this.resourceId = resourceId;
	}

	public String getResourceType() {
		return resourceType;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

}
