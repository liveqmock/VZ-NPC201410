package com.weizhen.npc.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * 用户
 */
@Entity
@Table(name = "resource_audit_log")
public class ResourceAuditLog implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer resourceAuditLogId;
	private String resourceType;
	private Integer resourceId;
	private String auditor;
	private Date auditDate;
	private String note;
	private String statusFrom;
	private String statusTo;
	private String operation;

	public ResourceAuditLog() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="resource_audit_log_id")
	public Integer getResourceAuditLogId() {
		return resourceAuditLogId;
	}


	public void setResourceAuditLogId(Integer resourceAuditLogId) {
		this.resourceAuditLogId = resourceAuditLogId;
	}


	@Column(name = "resource_type")
	public String getResourceType() {
		return resourceType;
	}


	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}


	@Column(name ="resource_id")
	public Integer getResourceId() {
		return resourceId;
	}


	public void setResourceId(Integer resourceId) {
		this.resourceId = resourceId;
	}


	@Column(name = "auditor")
	public String getAuditor() {
		return auditor;
	}


	public void setAuditor(String auditor) {
		this.auditor = auditor;
	}


	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "audit_date")
	public Date getAuditDate() {
		return auditDate;
	}


	public void setAuditDate(Date auditDate) {
		this.auditDate = auditDate;
	}


	@Column(name = "note")
	public String getNote() {
		return note;
	}


	public void setNote(String note) {
		this.note = note;
	}


	@Column(name = "status_from")
	public String getStatusFrom() {
		return statusFrom;
	}


	public void setStatusFrom(String statusFrom) {
		this.statusFrom = statusFrom;
	}


	@Column(name = "status_to")
	public String getStatusTo() {
		return statusTo;
	}


	public void setStatusTo(String statusTo) {
		this.statusTo = statusTo;
	}


	@Column(name = "operation")
	public String getOperation() {
		return operation;
	}


	public void setOperation(String operation) {
		this.operation = operation;
	}	
	
	
	
}