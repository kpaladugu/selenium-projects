package com.amazon.libs.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {
	private static PropertyReader propertyReader = new PropertyReader();
	Properties properties;
	
	private PropertyReader(){
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(System.getProperty("user.dir") + File.separator + "properties" + File.separator + "config.properties");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		properties = new Properties();
		try {
			properties.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static PropertyReader getPropertyReaderInstance(){
		return propertyReader;
	}
	
	public String getConfigData(String key){
		return properties.getProperty(key);
	}
}
