package com.Tab.TT.framework.properties.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.Tab.TT.dao.IUserTabDao;
import com.Tab.TT.framework.exception.MyException;
 

public class proUtil {
	public IUserTabDao getGroupDao(){
		Properties properties = new Properties();
		
		try {
			InputStream resourceStream =this.getClass().getResourceAsStream("/config/system.properties");
			properties.load(resourceStream);
			String className=properties.getProperty("GroupDao");
			Class  clazz =Class.forName(className);
			return (IUserTabDao)clazz.newInstance();
		}
			
		 catch (IOException e) {
			e.printStackTrace();
			throw new MyException();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new MyException();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new MyException();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new MyException();
		}
		 
	
}
}
