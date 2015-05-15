/**
 * 
 */
package com.hussar.app.mol.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.hussar.framework.entity.BaseEntity;

/**
 * @author xyi
 *
 */
@Entity
@Table(name = "MASSAGE_BOOKING_INFO")
@DynamicInsert(true)
@DynamicUpdate(true)
public class MassageBookingEntity extends BaseEntity{

	@Column(name="ORDER_NUMBER", nullable=true)
	private Integer orderNumber;
	
	@Column(name="ORDER_DATE", nullable=true)
	@Temporal(TemporalType.DATE)
	private Date orderDate;
	
	@OneToOne
	@JoinColumn(name="USER_ID")
	private UserEntity userInfo;

	public Integer getOrderNumber() {
    	return orderNumber;
    }

	public void setOrderNumber(Integer orderNumber) {
    	this.orderNumber = orderNumber;
    }

	public Date getOrderDate() {
    	return orderDate;
    }

	public void setOrderDate(Date orderDate) {
    	this.orderDate = orderDate;
    }

	public UserEntity getUserInfo() {
    	return userInfo;
    }

	public void setUserInfo(UserEntity userInfo) {
    	this.userInfo = userInfo;
    }
	
}
