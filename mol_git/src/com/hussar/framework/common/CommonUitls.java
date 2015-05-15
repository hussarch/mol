package com.hussar.framework.common;

/**
 * @CommonUitls.java
 * @author XiaoYi(hussarch@126.com)
 * Created on 2015-2-8, ©2015 some rights reserved
 */
public class CommonUitls {

	public static String getBasicUrl(){
		return CommonUitls.class.getClassLoader().getResource("/").getPath();
	}
	
	public static String getCacheDataPath(String subPath){
		return getBasicUrl() + "../../../../temp/" + subPath;
	}
	
	public static String getConfPath(String subPath){
		return getBasicUrl() + "../../conf/" + subPath;
	}
	
	public static void main(String[] args) {
		System.out.println(System.getProperty("user.dir"));
	}
	
}
