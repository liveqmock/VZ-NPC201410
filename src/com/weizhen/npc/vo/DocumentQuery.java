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
	
	

}
