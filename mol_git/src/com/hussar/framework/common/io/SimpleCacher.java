package com.hussar.framework.common.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

import com.hussar.framework.common.CommonUitls;
import com.hussar.framework.exceptions.BusinessException;
import com.hussar.framework.exceptions.ErrorType;

/**
 * @SimpleCacheReader.java
 * @author XiaoYi(hussarch@126.com)
 * Created on 2015-2-7, ©2015 some rights reserved
 */
public class SimpleCacher {

	private PropertiesReader reader;
	private String path;
	
	
	public SimpleCacher(String path) {
		this.path = CommonUitls.getCacheDataPath("cache.data");
		init();
	}
	
	private void init(){
		File file = new File(this.path);
		if(!file.exists()){
			try {
	            file.createNewFile();
            } catch (IOException e) {
	            throw new BusinessException(ErrorType.FILE_WRITE, e);
            }
		}
		reader = new PropertiesReader(this.path);
		
	}

	public String getValue(String key) {
		return reader.getValue(key);
	}

	public void saveVaule(String key, String value) {
		Properties properties = reader.getProperties();
		properties.put(key, value);
		OutputStream fos = null;
	    try {
			fos = new FileOutputStream(this.path);
			properties.store(fos, "Update the " + key);
		} catch (FileNotFoundException e) {
			throw new BusinessException(ErrorType.FILE_READ, e);
		} catch (IOException e) {
			throw new BusinessException(ErrorType.FILE_WRITE, e);
		}finally{
			if(fos != null){
				try {
					fos.close();
				} catch (IOException e) {
					throw new BusinessException(ErrorType.FILE_WRITE, e);
				}
			}
		}
	}
	
}
