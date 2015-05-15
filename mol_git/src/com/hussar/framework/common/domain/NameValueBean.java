package com.hussar.framework.common.domain;

/**
 * @NameValueBean.java
 * @author XiaoYi(hussarch@126.com) Created on 2015-2-8, ©2015 some rights reserved
 */
public class NameValueBean {

	private String name;
	private Object value;
	
	public NameValueBean(){
	}
	
	public NameValueBean(String name, String value){
		this.name = name;
		this.value = value;
	}
	
	public NameValueBean(String name, int value){
		this.name = name;
		this.value = Integer.valueOf(value);
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Object getValue() {
		return value;
	}
	
	public int getIntValue(){
		return Integer.parseInt(value.toString());
	}

	public void setValue(Object value) {
		this.value = value;
	}

}
