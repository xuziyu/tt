package com.Tab.TT.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.Tab.TT.Entity.PersonChat;
import com.Tab.TT.dao.IPersonChatDao;
import com.Tab.TT.framework.context.Context;
import com.Tab.TT.framework.exception.SystemException;
import com.Tab.TT.framework.jdbc.util.JDBCUtil;

public class PersonChatDao implements IPersonChatDao {
	ResultSet resultSet = null;
	PreparedStatement prepareStatement = null;
	Connection connection = null;
	
	
	@Override
	public int insert(PersonChat personChat) {
		JDBCUtil jdbcUtil = new JDBCUtil();
		jdbcUtil.connection();
		int chatId = 0;
		
		
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("insert into person_chat ");
			sql.append("(datetime,content,send_id,receive_id,message_state)");
			sql.append("values(?,?,?,?,?)");
			prepareStatement=jdbcUtil.getConnection().prepareStatement(sql.toString(),PreparedStatement.RETURN_GENERATED_KEYS);
			int index = 1;
			prepareStatement.setString(index++, personChat.getDatetime());
			prepareStatement.setString(index++, personChat.getContent());
			prepareStatement.setInt(index++,personChat.getSendId());
			prepareStatement.setInt(index++,personChat.getReceiveId());
			prepareStatement.setInt(index++,personChat.getMessageState());
			int rowCount = prepareStatement.executeUpdate();
			resultSet = prepareStatement.getGeneratedKeys() ;
			if(resultSet.next()){
				chatId = resultSet.getInt(1) ;
			}
			return chatId;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SystemException("发送失败",e);
		}finally{
			jdbcUtil.closeJDBC(resultSet, prepareStatement, jdbcUtil.getConnection());
		}

	}

	@Override
	public PersonChat load(int chatId) {
		JDBCUtil jdbcUtil = new JDBCUtil();
		jdbcUtil.connection();
		PersonChat personChat = new PersonChat();
		
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("select datetime,content from ");
			sql.append("person_chat where chat_id = ?");
			prepareStatement=jdbcUtil.getConnection().prepareStatement(sql.toString());
			int index = 1;
			prepareStatement.setInt(index++, chatId);
			
			resultSet = prepareStatement.executeQuery();
			if(resultSet.next()){
				personChat.setDatetime(resultSet.getString("datetime"));
				personChat.setContent(resultSet.getString("content"));
			}
			return personChat;
		} catch (SQLException e) {
			
			e.printStackTrace();
			throw new SystemException("接收失败",e);
		}finally{
			jdbcUtil.closeJDBC(resultSet, prepareStatement, jdbcUtil.getConnection());
		}
	}

	public void updateMessageState(int chatId){
		JDBCUtil jdbcUtil = new JDBCUtil();
		jdbcUtil.connection();
		PersonChat personChat = new PersonChat();
		
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("update person_chat set message_state = ? ");
			sql.append(" where chat_id = ?");
			prepareStatement=jdbcUtil.getConnection().prepareStatement(sql.toString());
			int index = 1;
			prepareStatement.setInt(index++, 1);
			prepareStatement.setInt(index++, chatId);
			int rowCount = prepareStatement.executeUpdate();
			if(rowCount!=1){
				throw new SystemException("修改失败！");
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
			throw new SystemException("接收失败",e);
		}finally{
			jdbcUtil.closeJDBC(resultSet, prepareStatement, jdbcUtil.getConnection());
		}
	}
	
	public void select(int receiveId){
		JDBCUtil jdbcUtil = new JDBCUtil();
		jdbcUtil.connection();
		PersonChat personChat = new PersonChat();
		
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("select chat_id,datetime,content from ");
			sql.append("person_chat where receive_id = ? and message_state = ?");
			prepareStatement=jdbcUtil.getConnection().prepareStatement(sql.toString());
			int index = 1;
			prepareStatement.setInt(index++, receiveId);
			prepareStatement.setInt(index++, 0);
			resultSet = prepareStatement.executeQuery();
			while(resultSet.next()){
				personChat.setChatId(resultSet.getInt("chat_id"));
				personChat.setDatetime(resultSet.getString("datetime"));
				personChat.setContent(resultSet.getString("content"));
				Context.personChatList.add(personChat);
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			throw new SystemException("接收失败",e);
		}finally{
			jdbcUtil.closeJDBC(resultSet, prepareStatement, jdbcUtil.getConnection());
		}
	}

}
