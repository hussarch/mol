/**
 * 
 */
package com.hussar.app.mol.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.hussar.framework.entity.BaseEntity;

/**
 * @TeamEntity.java
 * @author XiaoYi(hussarch@126.com) Created on 2015-1-30 下午2:08:49, ©2015 some
 *         rights reserved
 */
@Entity
@Table(name = "ORGANIZATION_INFO")
@DynamicInsert(true)
@DynamicUpdate(true)
public class OrganizationEntity extends BaseEntity {

	@Column(name = "NAME", length = 64, nullable = false)
	private String name;

	@Column(name = "ZH_NAME", length = 64, nullable = true)
	private String zhName;

	@JoinColumn(name = "MANAGER_ID")
	@ManyToOne(targetEntity = ManagerEntity.class)
	private ManagerEntity manager;

	@JoinColumn(name = "SUPER_ORGANIZATION_ID")
	@ManyToOne(targetEntity = OrganizationEntity.class)
	private OrganizationEntity superOrganization;
	
	@OneToMany(targetEntity = OrganizationEntity.class, fetch=FetchType.EAGER, mappedBy="superOrganization")
	private Set<OrganizationEntity> subOrganizations;

	
	public String getFullName(){
		return zhName + " - " + manager.getUser().getName();
	}
	
	public String getManagerName(){
		if(getManager() != null){
			return this.manager.getUser().getName();
		}else{
			return null;
		}
	}
	
	public ManagerEntity getManager() {
		return manager;
	}

	public void setManager(ManagerEntity manager) {
		this.manager = manager;
	}

	public OrganizationEntity getSuperOrganization() {
		return superOrganization;
	}

	public void setSuperOrganization(OrganizationEntity superOrganization) {
		this.superOrganization = superOrganization;
	}

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

	public Set<OrganizationEntity> getSubOrganizations() {
		return subOrganizations;
	}

	public void setSubOrganizations(Set<OrganizationEntity> subOrganizations) {
		this.subOrganizations = subOrganizations;
	}


}
