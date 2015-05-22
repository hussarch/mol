/**
 * 
 */
package com.hussar.app.mol.dao.sm;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.hussar.app.mol.model.MeetingRoomEntity;
import com.hussar.app.mol.model.ScheduledMeetingEntity;
import com.hussar.framework.dao.GenericDaoImpl;

/**
 * @author yi.xiao
 *
 */
@Repository("ScheduledMeetingDao")
public class ScheduledMeetingDaoImpl extends GenericDaoImpl<ScheduledMeetingEntity> implements ScheduledMeetingDao {
    
    @SuppressWarnings("unchecked")
    public List<ScheduledMeetingEntity> getScheduledMeetingList(Date date){
        Query query = getCurrentSession().createQuery("from ScheduledMeetingEntity where orderDate = ?");
        query.setDate(0, date);
        return query.list();
    }

    @Override
    public void deleteByMeetingRoom(MeetingRoomEntity meetingRoom) {
        Query query = getCurrentSession().createQuery("delete from ScheduledMeetingEntity where meetingRoom = ?");
        query.setParameter(0, meetingRoom);
        query.executeUpdate();
    }
    
}
