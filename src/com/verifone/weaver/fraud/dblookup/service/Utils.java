package com.verifone.weaver.fraud.dblookup.service;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class Utils {
	private static Properties properties;
	
	
	static
	{
		loadProperties();
	}
	
	private static void loadProperties() {
		
		try
		{
			File f=new File(System.getProperty("user.home")+File.separator+"db.properties");
			properties=new Properties();
			properties.load(new FileInputStream(f));
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	
	public static String get(String key)
	{
		return properties.getProperty(key);
	}
	
	public static Properties getProperties()
	{
		return properties;
	}

}
