package com.hussar.app.mol.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.hussar.framework.entity.BaseEntity;

/**
 * @MeetingParticipantEntity.java
 * @author XiaoYi(hussarch@126.com)
 * Created on 2015-5-22, ©2015 some rights reserved
 */
@Entity
@Table(name = "MEETING_PARTICIPANT")
@DynamicInsert(true)
@DynamicUpdate(true)
public class MeetingParticipantEntity extends BaseEntity{
	
    @JoinColumn(name = "SCHEDULED_MEETING_ID", nullable = false)
	private ScheduledMeetingEntity scheduledMeeting;
	
    @JoinColumn(name = "USER_ID", nullable = false)
	private UserEntity participant;

    public ScheduledMeetingEntity getScheduledMeeting() {
        return scheduledMeeting;
    }

    public void setScheduledMeeting(ScheduledMeetingEntity scheduledMeeting) {
        this.scheduledMeeting = scheduledMeeting;
    }

    public UserEntity getParticipant() {
        return participant;
    }

    public void setParticipant(UserEntity participant) {
        this.participant = participant;
    }
	
}
