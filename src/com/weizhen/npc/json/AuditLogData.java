package com.weizhen.npc.json;

import java.io.Serializable;
import java.util.Date;

import com.weizhen.npc.model.ResourceAuditLog;
import com.weizhen.npc.utils.ModelStatusEnum;

public class AuditLogData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String auditUser;
	private Date auditTime;
	private String auditContent;
	private String auditResult;
	
	public static AuditLogData from(ResourceAuditLog log) {
		AuditLogData data = new AuditLogData();
		data.setAuditUser(log.getAuditor());
		data.setAuditTime(log.getAuditDate());
		data.setAuditContent(log.getNote());
		data.setAuditResult(ModelStatusEnum.from(log.getStatusTo()).getItemValue());
		
		return data;
	}

	public String getAuditUser() {
		return auditUser;
	}

	public void setAuditUser(String auditUser) {
		this.auditUser = auditUser;
	}

	public Date getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}

	public String getAuditContent() {
		return auditContent;
	}

	public void setAuditContent(String auditContent) {
		this.auditContent = auditContent;
	}

	public String getAuditResult() {
		return auditResult;
	}

	public void setAuditResult(String auditResult) {
		this.auditResult = auditResult;
	}

}
