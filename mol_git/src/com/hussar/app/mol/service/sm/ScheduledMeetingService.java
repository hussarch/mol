/**
 * 
 */
package com.hussar.app.mol.service.sm;

import java.util.Date;
import java.util.List;

import com.hussar.app.mol.model.ScheduledMeetingEntity;

/**
 * @author yi.xiao
 *
 */
public interface ScheduledMeetingService {
    
    ScheduledMeetingEntity getEntity(int id);
    void add(ScheduledMeetingEntity meeting);
    void update(ScheduledMeetingEntity meeting);
    void delete(int id);
    List<ScheduledMeetingEntity> getMeetingRoomList(Date orderDate);
    
}
