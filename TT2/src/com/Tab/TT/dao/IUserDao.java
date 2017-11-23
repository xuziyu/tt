package com.Tab.TT.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.Tab.TT.Entity.User;
import com.Tab.TT.framework.exception.SystemException;
import com.Tab.TT.framework.jdbc.util.JDBCUtil;

public interface IUserDao {
	
	public boolean isRight(int userId,String password);
		
	public int insert(User user);
	
	public String load(int userId);
	
	public void getEveryThing(User user);
	
	public String getPassword(int userId,String telephone);
	
	public void updateMessage(User user);
	
	public void updatePassword(User user);
	
}
