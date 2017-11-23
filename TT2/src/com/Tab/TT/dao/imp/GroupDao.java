package com.Tab.TT.dao.imp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.Tab.TT.Entity.Group;

import com.Tab.TT.dao.IUserTabDao;
import com.Tab.TT.framework.jdbc.util.JDBCUtil;

public class GroupDao implements IUserTabDao {

	ResultSet resultSet = null;
	Connection connection = null;
	PreparedStatement prepareStatement = null;

	public int Test1(Group a) {
		int groupId = 0;

		try {
			JDBCUtil jdbcUtil = new JDBCUtil();

			jdbcUtil.connection();
			StringBuilder sql = new StringBuilder("insert into bas_group");
			sql.append("(group_type ,group_name,group_number,user_id) ");
			sql.append("values(?,?,?,?)");
			prepareStatement = jdbcUtil.getConnection().prepareStatement(sql.toString(),
					PreparedStatement.RETURN_GENERATED_KEYS);

			int index = 1;
			prepareStatement.setObject(index++, a.getGroupType());
			prepareStatement.setObject(index++, a.getGroupName());
			prepareStatement.setObject(index++, a.getGroupNumber());
			prepareStatement.setObject(index++, a.getUserId());

			int rowsCount = prepareStatement.executeUpdate();
			resultSet = prepareStatement.getGeneratedKeys();
			if (resultSet.next()) {
				a.setGroupId(resultSet.getInt(1));
			}
			if (rowsCount != 1) {
				throw new RuntimeException("新增错误");
			}
			insertMember(a);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (prepareStatement != null) {// 建议if写在外面
				try {
					prepareStatement.close();
				} catch (SQLException e) {

					e.printStackTrace();
				}
			}

			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
		return groupId;
	}

	 public void insertMember(Group group) throws Exception{
		   JDBCUtil jdbcUtil = new JDBCUtil();
		   jdbcUtil.connection();
		   
		    connection = jdbcUtil.getConnection();
		   
		    try {
		    	String sql = "insert into group_member (user_id, group_id) values (?,?)";
				prepareStatement = connection.prepareStatement(sql);
				prepareStatement.setObject(1, group.getUserId());
				prepareStatement.setObject(2, group.getGroupId());
				
				int rowsCount = prepareStatement.executeUpdate();
				if (rowsCount!=1) {
					throw new Exception();
				}
				 System.out.println( group.getUserId()+"sss"+ group.getGroupId());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	   }
}
