package com.weizhen.npc.vo;

import com.chineseall.dams.common.paging.BaseQueryModel;
import com.chineseall.dams.common.paging.Expression;
import com.chineseall.dams.common.paging.OperatorType;

/**
 * 主题相关图片视频查询对象
 * 
 * @author y
 *
 */
public class ImageRelatedQuery extends BaseQueryModel<ImageRelatedQuery> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String imageRelatedTitle;
	private String imageRelatedDescription;

	private Integer imageMainId;
	private Integer checkPublish;
	private String status;
	
	private Integer sequenceLessThan;
	private Integer sequenceGreatThan;

	@Expression(operatorType = OperatorType.LIKE)
	public String getImageRelatedTitle() {
		return imageRelatedTitle;
	}

	public void setImageRelatedTitle(String imageRelatedTitle) {
		this.imageRelatedTitle = imageRelatedTitle;
	}

	@Expression(operatorType = OperatorType.LIKE)
	public String getImageRelatedDescription() {
		return imageRelatedDescription;
	}

	public void setImageRelatedDescription(String imageRelatedDescription) {
		this.imageRelatedDescription = imageRelatedDescription;
	}

	public Integer getImageMainId() {
		return imageMainId;
	}

	public void setImageMainId(Integer imageMainId) {
		this.imageMainId = imageMainId;
	}

	public Integer getCheckPublish() {
		return checkPublish;
	}

	public void setCheckPublish(Integer checkPublish) {
		this.checkPublish = checkPublish;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Expression(fieldName="imageRelatedSequence", operatorType = OperatorType.LT)
	public Integer getSequenceLessThan() {
		return sequenceLessThan;
	}

	public void setSequenceLessThan(Integer sequenceLessThan) {
		this.sequenceLessThan = sequenceLessThan;
	}

	@Expression(fieldName="imageRelatedSequence", operatorType = OperatorType.GT)
	public Integer getSequenceGreatThan() {
		return sequenceGreatThan;
	}

	public void setSequenceGreatThan(Integer sequenceGreatThan) {
		this.sequenceGreatThan = sequenceGreatThan;
	}
	
}
