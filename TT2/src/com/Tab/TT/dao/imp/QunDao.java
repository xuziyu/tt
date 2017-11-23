package com.Tab.TT.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.Tab.TT.Entity.Group;
import com.Tab.TT.Entity.Qun;
import com.Tab.TT.Entity.User;
import com.Tab.TT.dao.IQunDao;
import com.Tab.TT.framework.context.Context;
import com.Tab.TT.framework.exception.SystemException;
import com.Tab.TT.framework.jdbc.util.JDBCUtil;

/** 
* @author : 吴健豪
* @date 创建时间：2017年9月9日 上午10:35:40 
* @version 1.0 
* @parameter  
* @since  
* @return  
*/
public class QunDao implements IQunDao{
	Connection connection = null;
	PreparedStatement prepareStatement = null;
	ResultSet resultSet = null;
	@Override
	public void getQunMessage(int userId) {
		JDBCUtil jdbcUtil = new JDBCUtil();
		jdbcUtil.connection();
		try {
			
			Qun qun = new Qun();
			StringBuilder sql = new StringBuilder();
			sql.append("select group_id from group_member");
			sql.append(" where user_id = ?");
			prepareStatement = jdbcUtil.getConnection().prepareStatement(sql.toString());
			int index = 1 ;
			prepareStatement.setObject(index++, userId);
			
			resultSet = prepareStatement.executeQuery();
			
			ArrayList arrayList = new ArrayList();
			while(resultSet.next()){
				
				System.out.println("群的id："+resultSet.getInt("group_id"));
				arrayList.add(resultSet.getInt("group_id"));
				
			}
			
			for (int i = 0; i < arrayList.size(); i++) {
				System.out.println(arrayList.get(i));
				Group group = new Group();
				group.setGroupId((int)arrayList.get(i));
				getQunName(group);
			}
		} catch(SystemException se){
			se.printStackTrace();
		} catch(Exception e){
			e.printStackTrace();
		} finally {
			jdbcUtil.closeJDBC(resultSet, prepareStatement, jdbcUtil.getConnection());
		}
	}
	
	public void getQunName(Group group){
		JDBCUtil jdbcUtil = new JDBCUtil();
		jdbcUtil.connection();
		
			try {
				Qun qun = new Qun();
				StringBuilder sql = new StringBuilder();
				sql.append("select group_name, group_type, user_id  from bas_group");
				sql.append(" where group_id = ?");
				prepareStatement = jdbcUtil.getConnection().prepareStatement(sql.toString());
				int index = 1 ;
				prepareStatement.setObject(index++, group.getGroupId());
				
				resultSet = prepareStatement.executeQuery();
				while(resultSet.next()){
					group.setGroupName(resultSet.getString("group_name"));
					group.setGroupType(resultSet.getString("group_type"));
					group.setUserId(Integer.parseInt(resultSet.getString("user_id")));
					System.out.println("查询群消息成功");
					Context.qunlist.add(group);
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}
	public static void main(String[] args) {
		QunDao dao = new QunDao();
		dao.getQunMessage(123123);
	}
	
}
