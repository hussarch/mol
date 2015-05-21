package com.hussar.app.mol.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * @MeetingRoomOrderEntity.java
 * @author XiaoYi(hussarch@126.com)
 * Created on 2015-5-21, ©2015 some rights reserved
 */
@Entity
@Table(name = "MASSAGE_BOOKING_INFO")
@DynamicInsert(true)
@DynamicUpdate(true)
public class MeetingRoomOrderEntity {
	
	private Date orderDate;
	
	private Integer startTimeIndex;
	
	private Integer durationUnitNumber;
	
	private MeetingRoomEntity meetingRoom;
	
	private UserEntity orderedBy;
	
	private String participants;

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Integer getStartTimeIndex() {
		return startTimeIndex;
	}

	public void setStartTimeIndex(Integer startTimeIndex) {
		this.startTimeIndex = startTimeIndex;
	}

	public Integer getDurationUnitNumber() {
		return durationUnitNumber;
	}

	public void setDurationUnitNumber(Integer durationUnitNumber) {
		this.durationUnitNumber = durationUnitNumber;
	}

	public MeetingRoomEntity getMeetingRoom() {
		return meetingRoom;
	}

	public void setMeetingRoom(MeetingRoomEntity meetingRoom) {
		this.meetingRoom = meetingRoom;
	}

	public UserEntity getOrderedBy() {
		return orderedBy;
	}

	public void setOrderedBy(UserEntity orderedBy) {
		this.orderedBy = orderedBy;
	}

	public String getParticipants() {
		return participants;
	}

	public void setParticipants(String participants) {
		this.participants = participants;
	}
	
}
