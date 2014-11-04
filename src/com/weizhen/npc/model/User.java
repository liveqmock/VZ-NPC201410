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
@Table(name = "user")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer userId;
	private Integer userName;
	private String realName;
	private String password;
	private String userType;
	private Boolean enabled;
	private Date lastLoginTime;
	private Date createdDate;
	private Date lastUpdateDate;
	
	public static final String USER_TYPE_EDITOR = "editor"; // 用户类型_编辑员
	public static final String USER_TYPE_AUDITOR = "auditor"; // 用户类型_审核员
	public static final String USER_TYPE_MANAGER = "mananger"; // 用户类型_管理员

	public User() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_id")
	public Integer getUserId() {
		return userId;
	}


	public void setUserId(Integer userId) {
		this.userId = userId;
	}


	@Column(name="user_name")
	public Integer getUserName() {
		return userName;
	}


	public void setUserName(Integer userName) {
		this.userName = userName;
	}


	@Column(name="real_name")
	public String getRealName() {
		return realName;
	}


	public void setRealName(String realName) {
		this.realName = realName;
	}


	@Column(name="password")
	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	@Column(name="user_type")
	public String getUserType() {
		return userType;
	}


	public void setUserType(String userType) {
		this.userType = userType;
	}


	@Column(name="enabled")
	public Boolean getEnabled() {
		return enabled;
	}


	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}


	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="last_login_time")
	public Date getLastLoginTime() {
		return lastLoginTime;
	}


	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}


	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_date")
	public Date getCreatedDate() {
		return createdDate;
	}


	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}


	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="last_update_date")
	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}


	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}
}