package com.hussar.app.mol.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.hussar.framework.entity.BaseEntity;

/**
 * @ManagerEntity.java
 * @author XiaoYi(hussarch@126.com)
 * Created on 2015-2-6, ©2015 some rights reserved
 */
@Entity
@Table(name = "MANAGER_INFO")
@DynamicInsert(true)
@DynamicUpdate(true)
public class ManagerEntity extends BaseEntity{
	
	@Column(name="TITLE", length=64, nullable=false)
	private String title;
	
	@Column(name="TITLE_ZH", length=64, nullable=true)
	private String position;
	
	@Column(name="TYPE", nullable=true, length = 32)
	private String type;
	
	@JoinColumn(name = "USER_ID", nullable = false)
	@ManyToOne(fetch = FetchType.LAZY)
	private UserEntity user;
	
	@JoinColumn(name = "SUPER_MANAGER_ID", nullable = true)
	@ManyToOne(fetch = FetchType.LAZY)
	private ManagerEntity superManager;
	
	public String getFullTitle(){
		StringBuilder builder = new StringBuilder();
		builder.append(user.getName()).append(" - ").append(getPosition());
		return builder.toString();
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public ManagerEntity getSuperManager() {
		return superManager;
	}

	public void setSuperManager(ManagerEntity superManager) {
		this.superManager = superManager;
	}

}
