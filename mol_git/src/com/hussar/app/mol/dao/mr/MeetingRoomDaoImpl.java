/**
 * 
 */
package com.hussar.app.mol.dao.mr;



import org.springframework.stereotype.Repository;

import com.hussar.app.mol.model.MeetingRoomEntity;
import com.hussar.framework.dao.GenericDaoImpl;

/**
 * @author yi.xiao
 *
 */
@Repository("MeetingRoomDao")
public class MeetingRoomDaoImpl extends GenericDaoImpl<MeetingRoomEntity> implements MeetingRoomDao{


}
