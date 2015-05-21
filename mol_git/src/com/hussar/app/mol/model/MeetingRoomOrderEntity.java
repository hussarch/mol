package com.hussar.app.mol.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
	
	@Column(name = "ORDER_DATE", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date orderDate;
	
	@Column(name = "START_TIME_INDEX", length = 2, nullable = false)
	private Integer startTimeIndex;
	
	@Column(name = "DURATION_UNIT_NUMBER", length = 2, nullable = false)
	private Integer durationUnitNumber;
	
	@JoinColumn(name = "MEETING_ROOM_ID", nullable = false)
	@ManyToOne(fetch = FetchType.EAGER)
	private MeetingRoomEntity meetingRoom;
	
	@JoinColumn(name = "ORDERED_BY_USER_ID", nullable = false)
	@ManyToOne(fetch = FetchType.EAGER)
	private UserEntity orderedBy;
	
	@Column(name = "PARTICIPANTS_ID", length = 2, nullable = false)
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
