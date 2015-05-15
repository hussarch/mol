package com.hussar.app.mol.service.msj;

import java.util.Date;
import java.util.List;

import com.hussar.app.mol.domain.MassageBookingInfo;
import com.hussar.app.mol.model.MassageBookingEntity;
import com.hussar.app.mol.model.UserEntity;

/**
 * @MassageBookingService.java
 * @author XiaoYi(hussarch@126.com)
 * Created on 下午8:52:51 2014-12-23
 * ©2014, some rights reserved
 */
public interface MassageBookingService {
	
	List<MassageBookingInfo> getMassageBookInfo(UserEntity user);
	void addBooking(MassageBookingEntity bookingEntity);
	void removeBooking(int orderNumber);
	Date getOrderDate();
	void setOrderDate(Date date);
	
}
