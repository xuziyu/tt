package com.Tab.TT.dao.imp;


import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import com.Tab.TT.Entity.User;
import com.Tab.TT.framework.exception.SystemException;
import com.Tab.TT.framework.jdbc.util.JDBCUtil;
import com.Tab.TT.dao.IUserDao;


/**
 * 用户表操作
 * @author Administrator
 *
 */
public class UserDao implements IUserDao{
	
	
	
	ResultSet resultSet = null;
	PreparedStatement prepareStatement = null;
	Connection connection = null;
	
	/**
	 * 登录功能
	 * @author 马贵鑫
	 * @param userId
	 * @return 用户密码
	 */
	@SuppressWarnings("finally")
	public boolean isRight(int userId,String password){
		String returnPassword = "";
		ResultSet resultSet = null;
		PreparedStatement prepareStatement = null;
		JDBCUtil jdbcUtil = new JDBCUtil();
		try {
			
			jdbcUtil.connection();
			StringBuilder sql = new StringBuilder();
			sql.append("select password from bas_user ");
			sql.append("where user_id = ?");
			prepareStatement = jdbcUtil.getConnection().prepareStatement(sql.toString());
			int index = 1;
			prepareStatement.setInt(index++, userId);
			resultSet = prepareStatement.executeQuery();
			if(resultSet.next()){
				returnPassword = resultSet.getString("password");
			}
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			if(returnPassword.equals(password)){
				try {
					resultSet.close();
				} catch (SQLException e) {					
					e.printStackTrace();
				}
				try {
					prepareStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				try {
					jdbcUtil.getConnection().close();
				} catch (SQLException e) {
				
					e.printStackTrace();
				}
				jdbcUtil.closeJDBC(resultSet, prepareStatement, jdbcUtil.getConnection());
				return true;
				
			}else{
				jdbcUtil.closeJDBC(resultSet, prepareStatement, jdbcUtil.getConnection());
				return false;
			}
			
		}
	}
	
	/**
	 * 获取用户登录后的信息
	 */
	public void getEveryThing(User user){

		ResultSet resultSet = null;
		PreparedStatement prepareStatement = null;
		JDBCUtil jdbcUtil = new JDBCUtil();
		
		
		try {
			jdbcUtil.connection();
			jdbcUtil.getConnection();
//			System.out.println("connction...");
			String sql ;
			sql = "select user_nick, gender,birthday ,country,province,city,telephone, open_QQzone, password from  bas_user where user_id = ?";
			prepareStatement = jdbcUtil.getConnection().prepareStatement(sql);
			prepareStatement.setInt(1, user.getUserId());
			resultSet = prepareStatement.executeQuery();
			int index = 1;
			while (resultSet.next()) {
				user.setUserNick(resultSet.getString(index++));
				user.setGender(resultSet.getInt(index++));
				user.setBirthday(resultSet.getString(index++));
				user.setCountry(resultSet.getString(index++));
				user.setProvince(resultSet.getString(index++));
				user.setCity(resultSet.getString(index++));
				user.setTelephone(resultSet.getString(index++));
				user.setOpenQQzone(resultSet.getInt(index++));
				user.setPassword(resultSet.getString(index++));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public int insert(User user){
		int rowCount = 0;
		String returnPassword = "";
		ResultSet resultSet = null;
		PreparedStatement prepareStatement = null;
		int userId = 0;
		JDBCUtil jdbcUtil = new JDBCUtil();
		try {
			jdbcUtil.connection();
			StringBuilder sql = new StringBuilder();
			sql.append("insert into bas_user (user_nick, password, gender, ");
			sql.append("telephone, open_QQzone) values");
			sql.append("(?,?,?,?,?)");
			prepareStatement = jdbcUtil.getConnection().prepareStatement(sql.toString(),PreparedStatement.RETURN_GENERATED_KEYS);
			int index = 1;
			prepareStatement.setString(index++, user.getUserNick());
			prepareStatement.setString(index++, user.getPassword());
			prepareStatement.setInt(index++, user.getGender());
			prepareStatement.setString(index++, user.getTelephone());
			prepareStatement.setInt(index++, user.getOpenQQzone());
			rowCount = prepareStatement.executeUpdate();
			resultSet = prepareStatement.getGeneratedKeys() ;
			if(resultSet.next()){
				userId = resultSet.getInt(1) ;
			}
			if(rowCount!=1){
				throw new SystemException("注册失败！");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			jdbcUtil.closeJDBC(resultSet, prepareStatement, jdbcUtil.getConnection());
		}
		return userId;
	}
	
	
	public String load(int userId) {
		JDBCUtil jdbcUtil = new JDBCUtil();
		jdbcUtil.connection();
		User user = new User();
		
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("select user_nick from ");
			sql.append("bas_user where user_id = ?");
			prepareStatement=jdbcUtil.getConnection().prepareStatement(sql.toString());
			int index = 1;
			prepareStatement.setInt(index++, userId);
			
			resultSet = prepareStatement.executeQuery();
			if(resultSet.next()){
				user.setUserNick(resultSet.getString("user_nick"));
//				System.out.println("运行到user_nick"+resultSet.getString("user_nick"));
			}
			return user.getUserNick();
		} catch (SQLException e) {
			
			e.printStackTrace();
			throw new SystemException("接收失败",e);
		}finally{
			jdbcUtil.closeJDBC(resultSet, prepareStatement, jdbcUtil.getConnection());
		}
	
	
	}
	
	
	public String getPassword(int userId,String telephone){
		JDBCUtil jdbcUtil = new JDBCUtil();
		jdbcUtil.connection();
		User user = new User();
		
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("select password from ");
			sql.append("bas_user where user_id = ? and telephone = ?");
			prepareStatement=jdbcUtil.getConnection().prepareStatement(sql.toString());
			int index = 1;
			prepareStatement.setInt(index++, userId);
			prepareStatement.setString(index++, telephone);
			resultSet = prepareStatement.executeQuery();
			if(resultSet.next()){
				user.setPassword(resultSet.getString("password"));
				
			}
			return user.getPassword();
		} catch (SQLException e) {
			
			e.printStackTrace();
			throw new SystemException("接收失败",e);
		}finally{
			jdbcUtil.closeJDBC(resultSet, prepareStatement, jdbcUtil.getConnection());
		}
		
		
	};
	

	public void updateMessage(User user){
		JDBCUtil jdbcUtil = new JDBCUtil();
		jdbcUtil.connection();
		
		
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("update bas_user set user_nick=? ");
			sql.append(" where user_id = ?");
			
			prepareStatement=jdbcUtil.getConnection().prepareStatement(sql.toString());
			int index = 1;

			prepareStatement.setString(index++, user.getUserNick());
//			prepareStatement.setInt(index++, user.getGender());
//			prepareStatement.setString(index++, user.getCountry());
//			prepareStatement.setString(index++, user.getProvince());
//			prepareStatement.setString(index++, user.getCity());
			prepareStatement.setInt(index++, user.getUserId());
			int rowCount = prepareStatement.executeUpdate();
			if(rowCount!=1){
				throw new SystemException("修改失败！");
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			throw new SystemException("接收失败",e);
		}finally{
//			jdbcUtil.closeJDBC(resultSet, prepareStatement, jdbcUtil.getConnection());
			
			try {
				prepareStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				jdbcUtil.getConnection().close();
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
		}
	
	}
	//
	public void updatePassword(User user){
		JDBCUtil jdbcUtil = new JDBCUtil();
		jdbcUtil.connection();
		
		
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("update bas_user set password=? ");
			sql.append(" where user_id = ?");
			
			prepareStatement=jdbcUtil.getConnection().prepareStatement(sql.toString());
			int index = 1;

			prepareStatement.setString(index++, user.getPassword());
			prepareStatement.setInt(index++, user.getUserId());
			int rowCount = prepareStatement.executeUpdate();
			if(rowCount!=1){
				throw new SystemException("修改失败！");
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			throw new SystemException("接收失败",e);
		}finally{
			try {
				prepareStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				jdbcUtil.getConnection().close();
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
		}
	
	}
	
	
	
	
}
