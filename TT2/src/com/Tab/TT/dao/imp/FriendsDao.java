package com.Tab.TT.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import com.Tab.TT.Entity.Friends;
import com.Tab.TT.Entity.User;
import com.Tab.TT.dao.IFriendsDao;
import com.Tab.TT.framework.context.Context;
import com.Tab.TT.framework.exception.SystemException;
import com.Tab.TT.framework.jdbc.util.JDBCUtil;
import com.Tab.TT.ui.AddFriendsFrame;
import com.Tab.TT.ui.AddFriendsRemarkFrame;
import com.Tab.TT.ui.AddFriendsSendYanZhengFrame;
import com.sun.corba.se.impl.encoding.TypeCodeReader;

/** 
* @author : 吴健豪
* @date 创建时间：2017年9月8日 下午6:06:44 
* @version 1.0 
* @parameter  
* @since  
* @return  
*/
public class FriendsDao implements IFriendsDao{
	Connection connection = null;
	PreparedStatement prepareStatement = null;
	ResultSet resultSet = null;
	
	
	//在好友表中查询该用户是否为你的好友
	@Override
	public void getFriendsMessage(int userId) {
		JDBCUtil jdbcUtil = new JDBCUtil();
		jdbcUtil.connection();
		try {
			
			
			StringBuilder sql = new StringBuilder();
			sql.append("select friend_user_id,friend_nick from user_friend");
			sql.append(" where user_id = ?");
			prepareStatement = jdbcUtil.getConnection().prepareStatement(sql.toString());
			int index = 1 ;
			prepareStatement.setObject(index++, userId);
			
			resultSet = prepareStatement.executeQuery();
			
			
			
			while(resultSet.next()){
				User user = new User();
				user.setUserId(resultSet.getInt("friend_user_id"));
				user.setUserNick(resultSet.getString("friend_nick"));
				Context.friendslist.add(user);
			}

			for (int i = 0; i < Context.friendslist.size(); i++) {
				System.out.println("好友集合的地址"+Context.friendslist.get(i));
			}


			
		} catch (SystemException se) {
			se.printStackTrace();		
			throw new SystemException("错误" , se);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SystemException("错误" , e);
		} catch(Exception e){
			e.printStackTrace();
			throw new SystemException("错误" , e);
		}
		finally{
			jdbcUtil.closeJDBC(resultSet,prepareStatement, jdbcUtil.getConnection());
		}
			
		
		
	}

	//将好友信息添加到好友表中
	@Override
	public void insert(int userId) {
		JDBCUtil jdbcUtil = new JDBCUtil();
		jdbcUtil.connection();
		
		try {
			UserDao userdao = new UserDao();
			//好友的id
			int Id = Integer.parseInt(AddFriendsFrame.textstaticFieldInput);
			//好友的昵称
			String friendsNick = AddFriendsFrame.friendstaticNick;
			//好友备注
			String friendsRemark = AddFriendsRemarkFrame.textFieldstaticRemarkName;
			
						
			StringBuilder sql = new StringBuilder("insert into user_friend");
			sql.append("(friend_user_id,friend_nick,user_id,remark)");
			sql.append("values(?,?,?,?)");
			prepareStatement = jdbcUtil.getConnection().prepareStatement(sql.toString());
			int index = 1 ;
			prepareStatement.setObject(index++, Id);
			prepareStatement.setObject(index++, userdao.load(Id));
			prepareStatement.setObject(index++, Context.currentUser.getUserId());
			prepareStatement.setObject(index++, friendsRemark);
			int rows = prepareStatement.executeUpdate();
			if(rows != 1){
				JOptionPane.showMessageDialog(null, "添加好友失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			jdbcUtil.closeJDBC(prepareStatement, jdbcUtil.getConnection());
		}
	}

	//将自己加到好友的列表中
	@Override
	public void insertSelf(int userId) {
		JDBCUtil jdbcUtil = new JDBCUtil();
		jdbcUtil.connection();
		
		try {
			UserDao userdao = new UserDao();
			//获得好友id
			int Id = Integer.parseInt(AddFriendsFrame.textstaticFieldInput);
			//查询好友id的昵称
			
			
			StringBuilder sql = new StringBuilder("insert into user_friend");
			sql.append("(friend_user_id,friend_nick,user_id)");
			sql.append("values(?,?,?)");
			prepareStatement = jdbcUtil.getConnection().prepareStatement(sql.toString());
			int index = 1 ;
			prepareStatement.setObject(index++, Context.currentUser.getUserId());
			prepareStatement.setObject(index++, Context.currentUser.getUserNick());
			prepareStatement.setObject(index++, Id);	
			int rows = prepareStatement.executeUpdate();
			if(rows != 1){
				JOptionPane.showMessageDialog(null, "添加好友失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			jdbcUtil.closeJDBC(prepareStatement, jdbcUtil.getConnection());
		}
	}

	//加好友时，往申请表中添加数据
	public void insertFriendsApply(int UserId){
		JDBCUtil jdbcUtil = new JDBCUtil();
		jdbcUtil.connection();
		try {
			UserDao userDao = new UserDao();
			//好友的Id
			int Id = Integer.parseInt(AddFriendsFrame.textstaticFieldInput);
			StringBuilder sql = new StringBuilder("insert into friend_apply");
					//	用户id   好友id   申请理由			申请时间	（默认）同意
			sql.append("(ask_id,user_id,apply_reason,apply_time,agree)");
			sql.append("values(?,?,?,?,?)");
			prepareStatement = jdbcUtil.getConnection().prepareStatement(sql.toString());
			int index = 1 ;
			prepareStatement.setObject(index++, Context.currentUser.getUserId());
			prepareStatement.setObject(index++, Id);
			prepareStatement.setObject(index++, AddFriendsSendYanZhengFrame.textAreaStaticReason);
			prepareStatement.setObject(index++, new Date());
			prepareStatement.setObject(index++, 1);
			
			int rows = prepareStatement.executeUpdate();
			if(rows != 1 ){
				JOptionPane.showMessageDialog(null, "发送好友申请  失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.closeJDBC(prepareStatement, jdbcUtil.getConnection());
		}
	}
}
