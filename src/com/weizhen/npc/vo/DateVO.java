package com.weizhen.npc.vo;

import java.io.Serializable;
import java.util.List;

import com.weizhen.npc.model.Document;
import com.weizhen.npc.model.ImageMain;
import com.weizhen.npc.model.ImageRelated;

/**
 * 日期专题展示对象
 * 
 * @author y
 *
 */
public class DateVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String publishDate;
	private List<ImageMain> imageMains;
	private List<ImageRelated> imageRelateds;
	private List<Document> documents;

	public String getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}

	public List<ImageMain> getImageMains() {
		return imageMains;
	}

	public void setImageMains(List<ImageMain> imageMains) {
		this.imageMains = imageMains;
	}

	public List<ImageRelated> getImageRelateds() {
		return imageRelateds;
	}

	public void setImageRelateds(List<ImageRelated> imageRelateds) {
		this.imageRelateds = imageRelateds;
	}

	public List<Document> getDocuments() {
		return documents;
	}

	public void setDocuments(List<Document> documents) {
		this.documents = documents;
	}

}
