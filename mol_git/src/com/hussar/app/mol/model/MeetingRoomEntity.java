package com.hussar.app.mol.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.hussar.framework.entity.BaseEntity;

/**
 * @MeetingRoomEntity.java
 * @author XiaoYi(hussarch@126.com)
 * Created on 2015-5-21, ©2015 some rights reserved
 */
@Entity
@Table(name = "MASSAGE_BOOKING_INFO")
@DynamicInsert(true)
@DynamicUpdate(true)
public class MeetingRoomEntity extends BaseEntity{
	
	@Column(name = "NAME", length = 64, nullable = false)
	private String name;

	@Column(name = "ZH_NAME", length = 64, nullable = true)
	private String zhName;
	
	@Column(name = "LOCATION", length = 128, nullable = true)
	private String location;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getZhName() {
		return zhName;
	}

	public void setZhName(String zhName) {
		this.zhName = zhName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	
}
