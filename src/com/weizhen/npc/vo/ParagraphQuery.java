package com.weizhen.npc.vo;

import com.chineseall.dams.common.paging.BaseQueryModel;
import com.chineseall.dams.common.paging.Expression;
import com.chineseall.dams.common.paging.OperatorType;

public class ParagraphQuery extends BaseQueryModel<ParagraphQuery> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String paragraphContent;
	private DocumentQuery document;

	@Expression(operatorType = OperatorType.LIKE)
	public String getParagraphContent() {
		return paragraphContent;
	}

	public void setParagraphContent(String paragraphContent) {
		this.paragraphContent = paragraphContent;
	}

	public DocumentQuery getDocument() {
		return document;
	}

	public void setDocument(DocumentQuery document) {
		this.document = document;
	}

}
