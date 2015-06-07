package com.hussar.app.mol.domain;

/**
 * @MeetingRoomOrderInfo.java
 * @author XiaoYi(hussarch@126.com) Created on 2015-6-7, ©2015 some rights reserved
 */
public class MeetingRoomOrderInfo {

	private Integer id;
	private String name;
	private String tds;


	public String getTds() {
		return tds;
	}

	public void setTds(String tds) {
		this.tds = tds;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


}
