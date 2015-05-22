/**
 * 
 */
package com.hussar.app.mol.dao.sm;

import java.util.Date;
import java.util.List;

import com.hussar.app.mol.model.MeetingRoomEntity;
import com.hussar.app.mol.model.ScheduledMeetingEntity;
import com.hussar.framework.dao.GenericDao;

/**
 * @author yi.xiao
 *
 */
public interface ScheduledMeetingDao extends GenericDao<ScheduledMeetingEntity> {
    
    List<ScheduledMeetingEntity> getScheduledMeetingList(Date date);
    void deleteByMeetingRoom(MeetingRoomEntity meetingRoom);
    
}
