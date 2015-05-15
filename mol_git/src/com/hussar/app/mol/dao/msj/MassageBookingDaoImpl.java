package com.hussar.app.mol.dao.msj;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.hussar.app.mol.model.MassageBookingEntity;
import com.hussar.framework.dao.GenericDaoImpl;

/**
 * @MassageBookingDaoImpl.java
 * @author XiaoYi(hussarch@126.com)
 * Created on 下午10:38:13 2014-12-22
 * ©2014, some rights reserved
 */
@Repository("MassageBookingDao")
public class MassageBookingDaoImpl extends GenericDaoImpl<MassageBookingEntity> implements MassageBookingDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<MassageBookingEntity> getMassageBookInfo(Date date) {
		Query query = getCurrentSession().createQuery("from MassageBookingEntity where orderDate = ?");
		query.setDate(0, date);
		return query.list();
	}

	@Override
	public boolean isBooked(Date date, int index) {
		Query query = getCurrentSession().createQuery("from MassageBookingEntity where orderDate = ? and orderNumber = ?");
		query.setDate(0, date);
		query.setInteger(1, index);
		return query.list().size() > 0;
	}

	@Override
	public void addBooking(MassageBookingEntity bookingInfo) {
		super.insert(bookingInfo);
	}

	@Override
	public void updateBooking(MassageBookingEntity bookingInfo) {
		super.update(bookingInfo);
	}

	@Override
	public void removeBooking(Date date, int index) {
		Query query = getCurrentSession().createQuery("delete from MassageBookingEntity where orderDate = ? and orderNumber = ?");
		query.setDate(0, date);
		query.setInteger(1, index);
		query.executeUpdate();
	}

	@Override
	public void updateOrderDate(Date newOrderDate, Date orderDate) {
		Query query = getCurrentSession().createQuery("update MassageBookingEntity set orderDate = ?, updatedDate = ? where orderDate = ?");
		query.setDate(0, newOrderDate);
		query.setTimestamp(1, new Date());
		query.setDate(2, orderDate);
		query.executeUpdate();
	}


}
