package com.hussar.app.mol.domain;

import com.hussar.app.mol.common.ot.CurrentTimeStatus;
import com.hussar.app.mol.common.ot.OrderTimeCalculator;
import com.hussar.app.mol.model.MassageBookingEntity;
import com.hussar.app.mol.model.UserEntity;

public class MassageBookingInfo  {

	private MassageBookingEntity bookingEntity;
	
	private String startTime;
	
	private boolean disableFlag = false;
	private String operationName;
	private boolean setFlag = false;
	private String trColor;
	
	private OrderTimeCalculator calculator;

	private boolean currentLoginUserFlag = false;
	
	public MassageBookingInfo(OrderTimeCalculator calculator, boolean bookedAlready){
		this.disableFlag = bookedAlready; 
		this.operationName = "预定";
		this.setFlag = true;
		this.calculator = calculator;
	}
	
	public MassageBookingInfo(OrderTimeCalculator calculator, MassageBookingEntity info, UserEntity currentLoginUser) {
		this.calculator = calculator;
		this.bookingEntity = info;
	    if(currentLoginUser != null){
	    	if(currentLoginUser.equals(info.getUserInfo())){
	    		this.disableFlag = false;
	    		this.currentLoginUserFlag = true;
	    		trColor = "#FFC78E";
	    	}else{
	    		this.disableFlag = true;
	    	}
	    }else{
	    	this.disableFlag = true;
	    }
	    this.operationName = "取消";
	    this.setFlag = false;
    }
	
	private void compareCurrentDate(Integer orderNumber){
		CurrentTimeStatus status = this.calculator.compareWithCurrentTime(orderNumber);
		if(CurrentTimeStatus.overtime.equals(status)){
			trColor = "#E3E3E3";
			this.disableFlag = true;
		}else if(CurrentTimeStatus.ongoing.equals(status)){
			trColor = "#FFFF6F";
			this.disableFlag = true;
		}else if(CurrentTimeStatus.oncoming.equals(status)){
			trColor = "#A6FFA6";
			if(!this.setFlag){
				this.disableFlag = true;
			}
		}else{
			this.currentLoginUserFlag = false;
		}
	}

	public boolean isDisableFlag() {
		return disableFlag;
	}

	public void setDisableFlag(boolean disableFlag) {
		this.disableFlag = disableFlag;
	}

	public String getOperationName() {
		return operationName;
	}

	public void setOperationName(String operationName) {
		this.operationName = operationName;
	}

	public String getTrColor() {
		return trColor;
	}

	public void setTrColor(String trColor) {
		this.trColor = trColor;
	}

	public String getStatus(){
		return bookingEntity.getUserInfo() != null ? bookingEntity.getUserInfo().getName() : null;
	}
	
	public void setOrderNumber(Integer orderNumber) {
		bookingEntity.setOrderNumber(orderNumber);
    	this.setStartTime(calculator.getStartOrderTime(orderNumber));
    	compareCurrentDate(orderNumber);
    }

	public String getStartTime() {
    	return startTime;
    }

	private void setStartTime(String startTime) {
    	this.startTime = startTime;
    }

	public MassageBookingEntity getBookingEntity() {
		return bookingEntity;
	}

	public void setBookingEntity(MassageBookingEntity bookingEntity) {
		this.bookingEntity = bookingEntity;
	}

	public boolean isSetFlag() {
		return setFlag;
	}

	public void setSetFlag(boolean setFlag) {
		this.setFlag = setFlag;
	}

	public boolean isCurrentLoginUserFlag() {
    	return currentLoginUserFlag;
    }

	public void setCurrentLoginUserFlag(boolean currentLoginUserFlag) {
    	this.currentLoginUserFlag = currentLoginUserFlag;
    }
	
}
