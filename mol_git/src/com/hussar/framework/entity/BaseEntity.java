package com.hussar.framework.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @CommonEntity.java
 * @author XiaoYi(hussarch@126.com)
 * Created on 下午10:28:03 2014-8-30
 * ©2014, some rights reserved
 */
@MappedSuperclass 
public class BaseEntity {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="ID")
	private Integer id;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATED_DATE", updatable=false)
	private Date createdDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="UPDATED_DATE")
	private Date updatedDate;
	
	public boolean isEquals(Object obj1, Object obj2){
		if(obj1 == obj2){
			return true;
		}
		if(obj1 != null){
			return obj1.equals(obj2);
		}else{
			return obj2 == null;
		}
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	@Override
	public String toString() {
		return "BaseEntity [id=" + id + ", createdDate=" + createdDate + ", updatedDate=" + updatedDate + "]";
	}
	
}
