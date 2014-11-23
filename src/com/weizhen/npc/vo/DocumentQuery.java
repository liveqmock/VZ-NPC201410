package com.weizhen.npc.vo;

import com.chineseall.dams.common.paging.BaseQueryModel;
import com.chineseall.dams.common.paging.Expression;
import com.chineseall.dams.common.paging.OperatorType;

public class DocumentQuery extends BaseQueryModel<DocumentQuery> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String documentTitle;
	private String documentDescription;

	private Integer imageMainId;
	private Integer checkPublish;
	private String status;

	private Integer sequenceLessThan;
	private Integer sequenceGreatThan;

	@Expression(operatorType = OperatorType.LIKE)
	public String getDocumentTitle() {
		return documentTitle;
	}

	public void setDocumentTitle(String documentTitle) {
		this.documentTitle = documentTitle;
	}

	@Expression(operatorType = OperatorType.LIKE)
	public String getDocumentDescription() {
		return documentDescription;
	}

	public void setDocumentDescription(String documentDescription) {
		this.documentDescription = documentDescription;
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

	@Expression(fieldName="documentSequence", operatorType = OperatorType.LT)
	public Integer getSequenceLessThan() {
		return sequenceLessThan;
	}

	public void setSequenceLessThan(Integer sequenceLessThan) {
		this.sequenceLessThan = sequenceLessThan;
	}

	@Expression(fieldName="documentSequence", operatorType = OperatorType.GT)
	public Integer getSequenceGreatThan() {
		return sequenceGreatThan;
	}

	public void setSequenceGreatThan(Integer sequenceGreatThan) {
		this.sequenceGreatThan = sequenceGreatThan;
	}

}
