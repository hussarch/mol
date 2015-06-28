/**
 * 
 */
package com.hussar.app.mol.service.mr;

import java.util.List;

import com.hussar.app.mol.domain.MemberNode;
import com.hussar.app.mol.model.MeetingRoomEntity;
import com.hussar.framework.common.domain.PagingCountBean;

/**
 * @author yi.xiao
 *
 */
public interface MeetingRoomService {
    
    MeetingRoomEntity getEntity(int id);
    void add(MeetingRoomEntity entity);
    void update(MeetingRoomEntity entity);
    void delete(int id);
    boolean checkExist(String fieldName, String value);
    boolean checkExist(String fieldName, String value, Integer id);
    List<MeetingRoomEntity> getMeetingRoomList();
    List<MeetingRoomEntity> getMeetingRoomList(int currentPage, int pageSize);
    PagingCountBean getPagingCountBean(int currentPage, int pageSize);
    MemberNode getRootMemberNode();
    
}
