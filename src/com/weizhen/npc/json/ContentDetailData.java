package com.weizhen.npc.json;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.weizhen.npc.model.Congress;
import com.weizhen.npc.model.Document;
import com.weizhen.npc.model.ImageMain;
import com.weizhen.npc.model.ImageRelated;
import com.weizhen.npc.model.Paragraph;
import com.weizhen.npc.utils.MaterialTypeEnum;
import com.weizhen.npc.utils.ModelStatusEnum;
import com.weizhen.npc.utils.ResourceTypeEnum;

public class ContentDetailData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer contentId;
	private String belongCongress;
	private String contentType;
	private String submitPerson;
	private String belongImageMain;
	private Date submitTime;
	private String contentState;
	private String contentTitle;
	private String contentDescription;
	private String imageMainImgPath;
	private Integer imageRelatedType;
	private String imageRelatedImgPath;
	private String imageRelatedVideoPath;
	private String imageRelatedVideoThumbImgPath;
	private List<String> paragraphs;
	private String contentKeyword;
	private String contentDate;
	private String contentPerson;
	private String contentLocation;
	private Double contentLocationLong;
	private Double contentLocationLat;

	public static ContentDetailData from(ImageMain imageMain, Congress congress) {
		ContentDetailData data = new ContentDetailData();
		data.setContentId(imageMain.getImageMainId());
		data.setBelongCongress(congress.getCongressTitle());
		data.setContentType(ResourceTypeEnum.IMAGEMAIN.getItemValue());
		data.setSubmitPerson(imageMain.getCreator());
		data.setBelongImageMain(imageMain.getImageMainTitle());
		data.setSubmitTime(imageMain.getCreatedDate());
		data.setContentState(ModelStatusEnum.from(imageMain.getStatus()).getItemValue());
		data.setContentTitle(imageMain.getImageMainTitle());
		data.setContentDescription(imageMain.getImageMainDescription());
		data.setImageMainImgPath(imageMain.getImageMainFilepath());
		data.setContentKeyword(imageMain.getKeyword());
		data.setContentDate(imageMain.getDate());
		data.setContentPerson(imageMain.getPerson());
		data.setContentLocation(imageMain.getLocation());
		data.setContentLocationLong(imageMain.getLocationLong());
		data.setContentLocationLat(imageMain.getLocationLat());

		return data;
	}

	public static ContentDetailData from(ImageRelated imageRelated, ImageMain imageMain, Congress congress) {
		ContentDetailData data = new ContentDetailData();
		data.setContentId(imageRelated.getImageRelatedId());
		data.setBelongCongress(congress.getCongressTitle());
		data.setContentType(ResourceTypeEnum.IMAGERELATED.getItemValue());
		data.setSubmitPerson(imageRelated.getCreator());
		data.setBelongImageMain(imageMain.getImageMainTitle());
		data.setSubmitTime(imageRelated.getCreatedDate());
		data.setContentState(ModelStatusEnum.from(imageRelated.getStatus()).getItemValue());
		data.setContentTitle(imageRelated.getImageRelatedTitle());
		data.setContentDescription(imageRelated.getImageRelatedDescription());
		data.setImageRelatedType(imageRelated.getMaterialId());

		if (MaterialTypeEnum.IMAGE.getItemCode().intValue() == imageRelated.getMaterialId())
			data.setImageRelatedImgPath(imageRelated.getImageRelatedFilepath());
		if (MaterialTypeEnum.VIDEO.getItemCode().intValue() == imageRelated.getMaterialId()) {
			data.setImageRelatedVideoPath(imageRelated.getImageRelatedFilepath());
			data.setImageRelatedVideoThumbImgPath(imageRelated.getImageRelatedThumbFilepath());
		}

		data.setContentKeyword(imageRelated.getKeyword());
		data.setContentDate(imageRelated.getDate());
		data.setContentPerson(imageRelated.getPerson());
		data.setContentLocation(imageRelated.getLocation());
		data.setContentLocationLong(imageRelated.getLocationLong());
		data.setContentLocationLat(imageRelated.getLocationLat());

		return data;
	}

	public static ContentDetailData from(Document document, ImageMain imageMain, Congress congress) {
		ContentDetailData data = new ContentDetailData();
		data.setContentId(document.getDocumentId());
		data.setBelongCongress(congress.getCongressTitle());
		data.setContentType(ResourceTypeEnum.DOCUMENT.getItemValue());
		data.setSubmitPerson(document.getCreator());
		data.setBelongImageMain(imageMain.getImageMainTitle());
		data.setSubmitTime(document.getCreatedDate());
		data.setContentState(ModelStatusEnum.from(document.getStatus()).getItemValue());
		data.setContentTitle(document.getDocumentTitle());
		data.setContentDescription(document.getDocumentDescription());
		data.setImageRelatedType(document.getMaterialId());

		List<String> paragraphContents = new ArrayList<String>();
		for (Paragraph paragraph : document.getParagraphs()) {
			paragraphContents.add(paragraph.getParagraphContent());
		}
		data.setParagraphs(paragraphContents);

		data.setContentKeyword(document.getKeyword());
		data.setContentDate(document.getDate());
		data.setContentPerson(document.getPerson());
		data.setContentLocation(document.getLocation());
		data.setContentLocationLong(document.getLocationLong());
		data.setContentLocationLat(document.getLocationLat());

		return data;
	}

	public Integer getContentId() {
		return contentId;
	}

	public void setContentId(Integer contentId) {
		this.contentId = contentId;
	}

	public String getBelongCongress() {
		return belongCongress;
	}

	public void setBelongCongress(String belongCongress) {
		this.belongCongress = belongCongress;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getSubmitPerson() {
		return submitPerson;
	}

	public void setSubmitPerson(String submitPerson) {
		this.submitPerson = submitPerson;
	}

	public String getBelongImageMain() {
		return belongImageMain;
	}

	public void setBelongImageMain(String belongImageMain) {
		this.belongImageMain = belongImageMain;
	}

	public Date getSubmitTime() {
		return submitTime;
	}

	public void setSubmitTime(Date submitTime) {
		this.submitTime = submitTime;
	}

	public String getContentState() {
		return contentState;
	}

	public void setContentState(String contentState) {
		this.contentState = contentState;
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

	public List<String> getParagraphs() {
		return paragraphs;
	}

	public void setParagraphs(List<String> paragraphs) {
		this.paragraphs = paragraphs;
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

}
