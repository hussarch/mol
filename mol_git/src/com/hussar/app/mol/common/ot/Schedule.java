package com.hussar.app.mol.common.ot;

/**
 * @Schedule.java
 * @author XiaoYi(hussarch@126.com) Created on 2015-2-1, ©2015 some rights
 *         reserved
 */
public class Schedule {

	private String day;
	private Integer hour;
	private Integer minute;
	private Integer duration;
	private Integer totalTime;

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public Integer getHour() {
		return hour;
	}

	public void setHour(Integer hour) {
		this.hour = hour;
	}

	public Integer getMinute() {
		return minute;
	}

	public void setMinute(Integer minute) {
		this.minute = minute;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public Integer getTotalTime() {
		return totalTime;
	}

	public void setTotalTime(Integer totalTime) {
		this.totalTime = totalTime;
	}

}
