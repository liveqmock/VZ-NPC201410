package com.weizhen.npc.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the Congress_Resume database table.
 * 
 */
@Entity
@Table(name="Congress_Resume")
@NamedQuery(name="CongressResume.findAll", query="SELECT c FROM CongressResume c")
public class CongressResume implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer congressResumeId;
	private Integer checkPublish;
	private String comment;
	private String resume;
	private Integer resumeSequence;
	private Date updateTime;
	private Congress congress;

	public CongressResume() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "congress_Resume_id")
	public Integer getCongressResumeId() {
		return this.congressResumeId;
	}

	public void setCongressResumeId(Integer congressResumeId) {
		this.congressResumeId = congressResumeId;
	}

	@Column(name = "check_Publish")
	public Integer getCheckPublish() {
		return this.checkPublish;
	}

	public void setCheckPublish(Integer checkPublish) {
		this.checkPublish = checkPublish;
	}


	@Column(name = "comment")
	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}


	@Column(name = "resume")
	public String getResume() {
		return this.resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
	}

	@Column(name = "resume_sequence")
	public Integer getResumeSequence() {
		return this.resumeSequence;
	}

	public void setResumeSequence(Integer resumeSequence) {
		this.resumeSequence = resumeSequence;
	}


	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updateTime")
	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}


	//bi-directional many-to-one association to Congress
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="Congress_id")
	public Congress getCongress() {
		return this.congress;
	}

	public void setCongress(Congress congress) {
		this.congress = congress;
	}

}