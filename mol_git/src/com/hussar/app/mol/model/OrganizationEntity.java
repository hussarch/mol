/**
 * 
 */
package com.hussar.app.mol.model;

import java.util.Set;

import javax.persistence.CascadeType;
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
	
	@OneToMany(targetEntity = OrganizationEntity.class, fetch=FetchType.LAZY, mappedBy="superOrganization", cascade=CascadeType.REMOVE)
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((manager == null) ? 0 : manager.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((subOrganizations == null) ? 0 : subOrganizations.hashCode());
		result = prime * result + ((superOrganization == null) ? 0 : superOrganization.hashCode());
		result = prime * result + ((zhName == null) ? 0 : zhName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrganizationEntity other = (OrganizationEntity) obj;
		if (manager == null) {
			if (other.manager != null)
				return false;
		} else if (!manager.equals(other.manager))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (subOrganizations == null) {
			if (other.subOrganizations != null)
				return false;
		} else if (!subOrganizations.equals(other.subOrganizations))
			return false;
		if (superOrganization == null) {
			if (other.superOrganization != null)
				return false;
		} else if (!superOrganization.equals(other.superOrganization))
			return false;
		if (zhName == null) {
			if (other.zhName != null)
				return false;
		} else if (!zhName.equals(other.zhName))
			return false;
		return true;
	}


}
