package com.hussar.app.common.data;

import com.hussar.framework.common.CommonUitls;
import com.hussar.framework.common.io.PropertiesReader;

/**
 * @ProjectConfData.java
 * @author XiaoYi(hussarch@126.com)
 * Created on 2015-1-31, ©2015 some rights reserved
 */
public class ProjectConfData {

	private static PropertiesReader reader = new PropertiesReader(CommonUitls.getConfPath("project.properties"));
	
	public static String getValue(String key){
		return reader.getValue(key);
	}
	
	public static Integer getIntValue(String key){
		return Integer.valueOf(reader.getValue(key));
	}
}
