package com.hussar.app.mol.common.ot;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.hussar.app.common.CommonConstants;
import com.hussar.app.common.data.LocalCacheManager;
import com.hussar.app.common.data.ProjectConfData;
import com.hussar.framework.exceptions.BusinessException;
import com.hussar.framework.exceptions.ErrorType;


public class OrderTimeCalculator {
	
	private String day;
	private Integer hour;
	private Integer minute;
	private Integer duration;
	private Integer totalTime;
	private Integer oprationBeforeMinute;
	private String[] days = {"SUNDAY", "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY"};
	private Calendar calendar;
	
	public OrderTimeCalculator(){
	}
	
	public void init(){
		day = getDay();
		String startTime = ProjectConfData.getValue("msj.start.time");
		int index = startTime.indexOf(":");
		hour = Integer.parseInt(startTime.substring(0, index)) ;
		minute = Integer.parseInt(startTime.substring(index + 1)) ;
		duration = ProjectConfData.getIntValue("msj.duration");
		totalTime = ProjectConfData.getIntValue("msj.total.count");
		oprationBeforeMinute = ProjectConfData.getIntValue("msj.operation.before.minutes");
		calendar = getCalendar();
	}
	
	
	private String getDay(){
		String date = LocalCacheManager.getValue(CommonConstants.MAJ_INTERIM_ADJUSTMENT_DAY);
		if(StringUtils.isEmpty(date)){
			return ProjectConfData.getValue("msj.day");
		}else{
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			try {
				Date theDate = dateFormat.parse(date);
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(theDate);
				if(isCurrentWeek(calendar)){
					return days[calendar.get(Calendar.DAY_OF_WEEK) - 1];
				}else{
					return ProjectConfData.getValue("msj.day");
				}
			} catch (ParseException e) {
				throw new BusinessException(ErrorType.INNER_ERROR, e);
			}
		}
	}
	
	public Date getOrderDate(){
		return getOrderDateTime(1);
	}

	private boolean isCurrentWeek(Calendar calendar) {
		return calendar.get(Calendar.WEEK_OF_YEAR) == Calendar.getInstance().get(Calendar.WEEK_OF_YEAR);
	}

	public String getStartOrderTime(int orderTime) {
		int time = minute + (orderTime - 1)*duration;
		int hour = this.hour + time/60;
		int minute = time%60;
		return hour + ":" + (minute == 0? "00" : minute);
    }
	
	public Date getOrderDateTime(int orderTime){
		String time = getStartOrderTime(orderTime);
		int index = time.indexOf(":");
		calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(time.substring(0, index)));
		calendar.set(Calendar.MINUTE, Integer.parseInt(time.substring(index + 1)));
		return calendar.getTime();
	}
	
	public CurrentTimeStatus compareWithCurrentTime(int orderNumber){
		Calendar calendar = Calendar.getInstance();
		Date currentStartOrderDate = getOrderDateTime(orderNumber);
		Date nextStartOrderDate = getOrderDateTime(orderNumber + 1);
		if(calendar.getTime().after(nextStartOrderDate)){
			return CurrentTimeStatus.overtime;
		}else if(calendar.getTime().after(currentStartOrderDate)){
			return CurrentTimeStatus.ongoing;
		}else{
			calendar.add(Calendar.MINUTE, oprationBeforeMinute);
			if(calendar.getTime().after(currentStartOrderDate)){
				return CurrentTimeStatus.oncoming;
			}else{
				return CurrentTimeStatus.before_start;
			}
		}
	}
	
	private Calendar getCalendar(){
		Calendar calendar = Calendar.getInstance();
		int currrentDayNo = calendar.get(Calendar.DAY_OF_WEEK);
		int dayNo = getDayNo(this.day);
		if(currrentDayNo == 1){
			calendar.add(Calendar.DAY_OF_WEEK, -1);
		}
		calendar.set(Calendar.DAY_OF_WEEK, dayNo);
		if(dayNo == 1){
			calendar.add(Calendar.DATE, 7);
		}
		calendar.set(Calendar.SECOND, 0);
		return calendar;
	}
	
	private int getDayNo(String day){
		for(int i = 1; i <= 7; i++){
			if(day.equals(days[i - 1])){
				return i;
			}
		}
		return 5;
	}
	
	
	public int getTotalTime(){
		return this.totalTime;
	}
	
	public static void main(String[] args) {
		OrderTimeCalculator calculator = new OrderTimeCalculator();
		calculator.init();
		System.out.println(calculator.getOrderDateTime(1));
	}
	
}
