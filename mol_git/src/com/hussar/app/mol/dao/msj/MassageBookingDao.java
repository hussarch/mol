package com.hussar.app.mol.dao.msj;

import java.util.Date;
import java.util.List;

import com.hussar.app.mol.model.MassageBookingEntity;
import com.hussar.framework.dao.GenericDao;

/**
 * @MassageBookingDao.java
 * @author XiaoYi(hussarch@126.com)
 * Created on 下午10:05:21 2014-12-22
 * ©2014, some rights reserved
 */
public interface MassageBookingDao extends GenericDao<MassageBookingEntity> {
	
	List<MassageBookingEntity> getMassageBookInfo(Date date);
	boolean isBooked(Date date, int index);
	void addBooking(MassageBookingEntity bookingInfo);
	void updateBooking(MassageBookingEntity bookingInfo);
	void removeBooking(Date date, int index);
	void updateOrderDate(Date newOrderDate, Date orderDate);
	
}
