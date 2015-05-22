/**
 * 
 */
package com.hussar.app.mol.service.sm;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hussar.app.mol.dao.sm.ScheduledMeetingDao;
import com.hussar.app.mol.model.MeetingRoomEntity;
import com.hussar.app.mol.model.ScheduledMeetingEntity;

/**
 * @author yi.xiao
 *
 */
@Service("ScheduledMeetingService")
@Transactional
public class ScheduledMeetingServiceImpl implements ScheduledMeetingService {
    
    @Autowired
    private ScheduledMeetingDao dao;
    
    @Override
    public ScheduledMeetingEntity getEntity(int id) {
        return dao.queryById(id);
    }

    @Override
    public void add(ScheduledMeetingEntity meeting) {
        dao.insert(meeting);
    }

    @Override
    public void update(ScheduledMeetingEntity meeting) {
        dao.update(meeting);
    }

    @Override
    public void delete(int id) {
        dao.deleteById(id);
    }

    @Override
    public List<ScheduledMeetingEntity> getMeetingRoomList(Date orderDate) {
        return dao.getScheduledMeetingList(orderDate);
    }

    @Override
    public boolean doesMeetingRoomBooked(MeetingRoomEntity meetingRoom) {
        return dao.doesMeetingRoomBooked(meetingRoom);
    }

}
