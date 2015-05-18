package com.hussar.app.mol.model;

public enum Gender {

	MALE("男"), FEMALE("女");
	
	private String text;

	Gender(String text){
		this.text = text;
	}
	
	
	public String getText(){
		return this.text;
	}
}