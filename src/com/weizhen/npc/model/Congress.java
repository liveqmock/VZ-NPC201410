package com.weizhen.npc.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;


/**
 * The persistent class for the Congress database table.
 * 
 */
@Entity
@Table(name = "congress")
@NamedQuery(name="Congress.findAll", query="SELECT c FROM Congress c")
public class Congress implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer congressId;
	private Integer checkPublish;
	private String comment;
	private String congressDescription;
	private String congressImageFilePath;
	private String congressLocation;
	private String congressTime;
	private String congressTitle;
	private Date updateTime;
	private List<CongressResume> congressResumes;

	public Congress() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="congress_id")
	public Integer getCongressId() {
		return this.congressId;
	}

	public void setCongressId(Integer congressId) {
		this.congressId = congressId;
	}


	@Column(name="check_publish")
	public Integer getCheckPublish() {
		return this.checkPublish;
	}

	public void setCheckPublish(Integer checkPublish) {
		this.checkPublish = checkPublish;
	}


	@Column(name="comment")
	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}


	@Column(name="congress_description")
	public String getCongressDescription() {
		return this.congressDescription;
	}

	public void setCongressDescription(String congressDescription) {
		this.congressDescription = congressDescription;
	}


	@Column(name="congress_image_filepath")
	public String getCongressImageFilePath() {
		return this.congressImageFilePath;
	}

	public void setCongressImageFilePath(String congressImageFilePath) {
		this.congressImageFilePath = congressImageFilePath;
	}


	@Column(name="congress_location")
	public String getCongressLocation() {
		return this.congressLocation;
	}

	public void setCongressLocation(String congressLocation) {
		this.congressLocation = congressLocation;
	}


	@Column(name="congress_time")
	public String getCongressTime() {
		return this.congressTime;
	}

	public void setCongressTime(String congressTime) {
		this.congressTime = congressTime;
	}


	@Column(name="congress_title")
	public String getCongressTitle() {
		return this.congressTitle;
	}

	public void setCongressTitle(String congressTitle) {
		this.congressTitle = congressTitle;
	}


	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="updateTime")
	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}


	//bi-directional many-to-one association to CongressResume
	@OneToMany(mappedBy="congress",fetch=FetchType.EAGER)
	@OrderBy("resumeSequence")
	public List<CongressResume> getCongressResumes() {
		return this.congressResumes;
	}

	public void setCongressResumes(List<CongressResume> congressResumes) {
		this.congressResumes = congressResumes;
	}

	public CongressResume addCongressResume(CongressResume congressResume) {
		getCongressResumes().add(congressResume);
		congressResume.setCongress(this);

		return congressResume;
	}

	public CongressResume removeCongressResume(CongressResume congressResume) {
		getCongressResumes().remove(congressResume);
		congressResume.setCongress(null);

		return congressResume;
	}
	
	/**
	 * 将界别的内容组合为完整的文本
	 * @return
	 */
	@Transient
	public String getCongressResumeContent() {
		if (null == this.congressResumes || this.congressResumes.isEmpty())
			return null;
		
		StringBuffer content = new StringBuffer("");
		for(CongressResume congressResume : getCongressResumes()) {
			content.append(congressResume.getResume());
		}
		
		return content.toString();
	}

}