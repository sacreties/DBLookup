package com.verifone.weaver.fraud.dblookup.service;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.mysql.jdbc.ResultSetMetaData;

public class DBAccessor {
	
	static Properties properties;
	private static boolean propertyLoaded;
	private static Map<String,Object> values;
	
	private static Map<String,Map<String,List>> data=new HashMap<String, Map<String,List>>();
	private static Connection connection;
	private static Statement statement;
	
	static
	{
		loadProperties();
		values=new HashMap<String,Object>();
	}
	
	public static Map getTableDetails()
	{
		
		connect();
		
		try {
			statement=connection.createStatement();
			
			return createData();
			
			
			/*ResultSet resultset=st.executeQuery("Select * from Merchant_Filter");
			
			values.put("Merchnat",resultset);*/
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		finally
		{
			try {
				connection.close();
				connection=null;
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		
		return null;
	}
	
	
	private static Map createData() {
		
		if(properties.getProperty("tables")!=null)
		{
			return getTabledata();
		}
		return null;
	}


	private static Map getTabledata() {
		
		try
		{
			String tables[]=properties.getProperty("tables").split(",");
			
			
			
			for(int i=0;i<tables.length;i++)				
			{
				Map<String,List> m=new HashMap<String, List>();
				ResultSet set=statement.executeQuery("select * from "+tables[i]);
				
				System.err.println(set.getMetaData());
				
				ResultSetMetaData meta=(ResultSetMetaData) set.getMetaData();
				
				int cols=meta.getColumnCount();
				
				for(int j=1;j<=cols;j++)
				{
					m.put(meta.getColumnName(j), new ArrayList());
				}
				
				
				while(set.next())
				{
					for(int k=1;k<=cols;k++)
					{
						m.get(meta.getColumnName(k)).add(set.getObject(k));
					}
				}
				
				data.put(tables[i],m);
			}
			
			System.out.println(data);
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return data;
		
	}


	private static void loadProperties() {
		
		/*try
		{
			File f=new File(System.getProperty("user.home")+File.separator+"db.properties");
			properties=new Properties();
			properties.load(new FileInputStream(f));
			if(!properties.isEmpty())
				propertyLoaded=true;
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}*/
		
		properties=Utils.getProperties();
	}


	private static void connect()
	{
		try
		{
			Class.forName(properties.getProperty("driverClassName"));
			connection=DriverManager.getConnection(properties.getProperty("url"), properties.getProperty("username"), properties.getProperty("password"));
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static boolean isLoaded()
	{
		return propertyLoaded;
	}
	
	
	public static void main(String...args) throws SQLException
	{
		Map<String,Map<String,Object>> v=new HashMap<String, Map<String,Object>>();
		
		getTableDetails();
		/*
		ResultSet set=(ResultSet) values.get("Merchnat");
		
		ResultSetMetaData meta=(ResultSetMetaData) set.getMetaData();
		
		int l=meta.getColumnCount();
		
		
		for(int i=1;i<=l;i++)
		{
			System.out.print(meta.getColumnName(i)+"\t");
		}
		System.out.println();
		
		
		try {
			while(set.next())
			{
				
				for(int i=1;i<=l;i++)
				{
					System.out.print(set.getObject(i)+"\t");
				}
				System.out.println();
				
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		System.err.println(set);
		connection.close();*/
		
		
	}

}
