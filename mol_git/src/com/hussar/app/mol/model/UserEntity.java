package com.hussar.app.mol.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.hussar.framework.entity.BaseEntity;

/**
 * @UserInfo.java
 * @author XiaoYi(hussarch@126.com) Created on 下午7:22:54 2014-12-20 ©2014, some
 *         rights reserved
 */
@Entity
@Table(name = "USER_INFO")
@DynamicInsert(true)
@DynamicUpdate(true)
public class UserEntity extends BaseEntity {
	
	@Column(name="USER_NAME", length=32, nullable=false)
	private String name;
	
	@Column(name="ENGLISH_NAME", length=64, nullable=true)
	private String englishName;
	
	@Column(name="EMPLOYEE_ID", nullable=true)
	private Integer employeeId;
	
	@Column(name="EMAIL", length=64, nullable=true)
	private String email;
	
	@Column(name="SKYPE_ID", length=64, nullable=true)
	private String skypeId;
	
	@Column(name="MOBILE_NUMBER", length=32, nullable=true)
	private String mobileNumber;
	
	@Column(name="EXTENSION_NUMBER", nullable=true)
	private Integer extensionNumber;
	
	@JoinColumn(name = "ORGANIZATION_ID", nullable = true)
	@ManyToOne(fetch = FetchType.LAZY)
	private OrganizationEntity organization;
	
	@Column(name="POSITION", length=64, nullable=true)
	private String position;
	
	@Column(name="LOCATION", length=64, nullable=true)
	private String location;
	
	@Column(name="ROLE_TYPE", nullable=true, length = 16)
	@Enumerated(EnumType.STRING)
	private RoleType role;
	
	@Column(name="GENDER", nullable=true, length = 8)
	@Enumerated(EnumType.STRING)
	private Gender gender = Gender.MALE;
	
	@Column(name="PASSWORD", length=32, nullable=true)
	private String password;
	
	@Column(name="JOIN_DATE", nullable=true)
	@Temporal(TemporalType.DATE)
	private Date joinDate;
	
	public String getTitle(){
		if(RoleType.admin.equals(this.role)){
			return name + "(管理员)";
		}else if(RoleType.super_admin.equals(this.role)){
			return name + "(超级管理员)";
		}else{
			return name;
		}
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSkypeId() {
		return skypeId;
	}

	public void setSkypeId(String skypeId) {
		this.skypeId = skypeId;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEnglishName() {
    	return englishName;
    }

	public void setEnglishName(String englishName) {
    	this.englishName = englishName;
    }


	public Integer getExtensionNumber() {
    	return extensionNumber;
    }

	public void setExtensionNumber(Integer extensionNumber) {
    	this.extensionNumber = extensionNumber;
    }

	public RoleType getRole() {
    	return role;
    }

	public void setRole(RoleType role) {
    	this.role = role;
    }

	public Gender getGender() {
    	return gender;
    }

	public void setGender(Gender gender) {
    	this.gender = gender;
    }

	public Date getJoinDate() {
    	return joinDate;
    }

	public void setJoinDate(Date joinDate) {
    	this.joinDate = joinDate;
    }

	public OrganizationEntity getOrganization() {
		return organization;
	}

	public void setOrganization(OrganizationEntity organization) {
		this.organization = organization;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((employeeId == null) ? 0 : employeeId.hashCode());
		result = prime * result + ((englishName == null) ? 0 : englishName.hashCode());
		result = prime * result + ((extensionNumber == null) ? 0 : extensionNumber.hashCode());
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result + ((joinDate == null) ? 0 : joinDate.hashCode());
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((mobileNumber == null) ? 0 : mobileNumber.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((organization == null) ? 0 : organization.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((position == null) ? 0 : position.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + ((skypeId == null) ? 0 : skypeId.hashCode());
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
		UserEntity other = (UserEntity) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (employeeId == null) {
			if (other.employeeId != null)
				return false;
		} else if (!employeeId.equals(other.employeeId))
			return false;
		if (englishName == null) {
			if (other.englishName != null)
				return false;
		} else if (!englishName.equals(other.englishName))
			return false;
		if (extensionNumber == null) {
			if (other.extensionNumber != null)
				return false;
		} else if (!extensionNumber.equals(other.extensionNumber))
			return false;
		if (gender != other.gender)
			return false;
		if (joinDate == null) {
			if (other.joinDate != null)
				return false;
		} else if (!joinDate.equals(other.joinDate))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (mobileNumber == null) {
			if (other.mobileNumber != null)
				return false;
		} else if (!mobileNumber.equals(other.mobileNumber))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (organization == null) {
			if (other.organization != null)
				return false;
		} else if (!organization.equals(other.organization))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (position == null) {
			if (other.position != null)
				return false;
		} else if (!position.equals(other.position))
			return false;
		if (role != other.role)
			return false;
		if (skypeId == null) {
			if (other.skypeId != null)
				return false;
		} else if (!skypeId.equals(other.skypeId))
			return false;
		return true;
	}

}
