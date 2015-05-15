package com.hussar.framework.common.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.hussar.framework.exceptions.BusinessException;
import com.hussar.framework.exceptions.ErrorType;

/**
 * @PropertiesReader.java
 * @author XiaoYi(hussarch@126.com)
 * Created on 2015-1-31, ©2015 some rights reserved
 */
public class PropertiesReader {

	private Properties properties = new Properties();
	private String path;
	private long lastModifiedTime = 0;
	
	public PropertiesReader(String path){
		this.path = path;
	}
	
	private void loadFile(File file){
		try {
			properties.load(new FileInputStream(file));
		} catch (IOException e) {
			throw new BusinessException(ErrorType.INVALID_PARAM, e);
		}
	}
	
	private void loadFile(){
		File file = new File(this.path);
		if(file.lastModified() > this.lastModifiedTime){
			this.lastModifiedTime = file.lastModified();
			loadFile(file);
		}
	} 
	
	public String getValue(String key){
		loadFile();
		String val = this.properties.getProperty(key);
		return val == null ? null : val.trim();
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PropertiesReader reader = new PropertiesReader(PropertiesReader.class.getResource("project.properties").getPath());
		for (int i = 0; i < 10; i++) {
			System.out.println(reader.getValue("msj.day"));;
			try {
				Thread.sleep(2 * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	public Properties getProperties() {
		return properties;
	}

}
