package com.hussar.app.mol.service.msj;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hussar.app.common.CommonConstants;
import com.hussar.app.common.data.LocalCacheManager;
import com.hussar.app.mol.common.ot.OrderTimeCalculator;
import com.hussar.app.mol.dao.msj.MassageBookingDao;
import com.hussar.app.mol.domain.MassageBookingInfo;
import com.hussar.app.mol.model.MassageBookingEntity;
import com.hussar.app.mol.model.UserEntity;
import com.hussar.framework.exceptions.BusinessException;

/**
 * @MassageBookingServiceImpl.java
 * @author XiaoYi(hussarch@126.com)
 * Created on 下午8:55:50 2014-12-23
 * ©2014, some rights reserved
 */
@Service("MassageBookingService")
@Transactional
public class MassageBookingServiceImpl implements MassageBookingService {

	@Autowired
	private MassageBookingDao massageBookingDao;
	private OrderTimeCalculator calculator = new OrderTimeCalculator();
	
	
	@Override
	public List<MassageBookingInfo> getMassageBookInfo(UserEntity currentLoginUser) {
		calculator.init();
		List<MassageBookingEntity> entityList = massageBookingDao.getMassageBookInfo(getOrderDate());
		List<MassageBookingInfo> list = getListInitNull(calculator.getTotalTime());
		for(MassageBookingEntity info : entityList){
			list.set(info.getOrderNumber() - 1, new MassageBookingInfo(calculator, info, currentLoginUser));
		}
		boolean bookedAlready = whetherBooked(entityList, currentLoginUser);
		for(int i = 0; i < list.size(); i++){
			MassageBookingInfo info = list.get(i);
			if(info == null){
				info = new MassageBookingInfo(calculator, bookedAlready);
				MassageBookingEntity entity = new MassageBookingEntity();
				entity.setOrderDate(getOrderDate());
				info.setBookingEntity(entity);
				list.set(i, info);
			}
			info.setOrderNumber(i + 1);
		}
		return list;
	}
	
	private boolean whetherBooked(List<MassageBookingEntity> entityList, UserEntity currentLoginUser){
		for(MassageBookingEntity info : entityList){
			if(info.getUserInfo().equals(currentLoginUser)){
				return true;
			}
		}
		return currentLoginUser == null;
	}

	private List<MassageBookingInfo> getListInitNull(int size) {
		List<MassageBookingInfo> list = new ArrayList<>();
		for(int i = 0; i < size; i++){
			list.add(null);
		}
	    return list;
    }
	
	@Override
	public Date getOrderDate() {
		return calculator.getOrderDate();
	}

	public boolean isBooked(Date date, int index) {
		return massageBookingDao.isBooked(date, index);
	}

	@Override
	public void addBooking(MassageBookingEntity bookingEntity) {
		if(!isBooked(getOrderDate(), bookingEntity.getOrderNumber())){
			bookingEntity.setOrderDate(getOrderDate());
			bookingEntity.setCreatedDate(new Date());
			massageBookingDao.addBooking(bookingEntity);
		}else{
			throw new BusinessException("该时间段按摩已预定");
		}
	}

	@Override
	public void removeBooking(int orderNumber) {
		massageBookingDao.removeBooking(getOrderDate(), orderNumber);
	}

	@Override
	public void setOrderDate(Date newOrderDate) {
		massageBookingDao.updateOrderDate(newOrderDate, getOrderDate());
		saveCache(newOrderDate);
	}

	private void saveCache(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		LocalCacheManager.save(CommonConstants.MAJ_INTERIM_ADJUSTMENT_DAY, dateFormat.format(date));
	}


}
