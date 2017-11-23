package com.Tab.TT.framework.properties.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.Tab.TT.dao.IQunChatDao;
import com.Tab.TT.dao.IUserDao;
import com.Tab.TT.framework.exception.SystemException;

public class PropertiesUtil {
	public IQunChatDao getIQunChatDaoImp(){
		Properties properties = new Properties();
		InputStream resourceAsStream = getClass().getResourceAsStream("/config/system.properties");
		try {
			properties.load(resourceAsStream);
			String className = properties.getProperty("IQunChatDao");
			Class clazz = Class.forName(className);
			return (IQunChatDao)clazz.newInstance();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("获取配置文件失败"+e);
		}
	}
	public IUserDao getUserDaoImp() {
		Properties properties = new Properties();
		
		try {
			InputStream resourceStream = this.getClass().getResourceAsStream("/config/TT.properties");
			properties.load(resourceStream);
			String className = properties.getProperty("UserDao") ;
			
			Class clazz = Class.forName(className) ;
			return (IUserDao)clazz.newInstance() ;
		} catch (IOException e) {
			
			e.printStackTrace();
			throw new SystemException("配置文件错误",e);
		} catch (ClassNotFoundException e) {
		
			e.printStackTrace();
			throw new SystemException("配置文件错误",e);
		} catch (InstantiationException e) {
	
			e.printStackTrace();
			throw new SystemException("配置文件错误",e);
		} catch (IllegalAccessException e) {
	
			e.printStackTrace();
			throw new SystemException("配置文件错误",e);
		}
		
		
	}
}
