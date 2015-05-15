package com.hussar.app.common.data;

import com.hussar.framework.common.io.SimpleCacher;

/**
 * @LocalCacheManager.java
 * @author XiaoYi(hussarch@126.com)
 * Created on 2015-2-7, ©2015 some rights reserved
 */
public class LocalCacheManager {

	private static SimpleCacher cacher = new SimpleCacher("/cache.data");
	
	public static String getValue(String key){
		return cacher.getValue(key);
	}
	
	public static void save(String key, String value){
		cacher.saveVaule(key, value);
	}
}
