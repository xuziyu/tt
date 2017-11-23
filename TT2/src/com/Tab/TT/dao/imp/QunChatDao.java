package com.Tab.TT.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

import com.Tab.TT.Entity.QunChat;
import com.Tab.TT.Entity.User;
import com.Tab.TT.dao.IQunChatDao;
import com.Tab.TT.framework.context.Context;
import com.Tab.TT.framework.jdbc.util.JDBCUtil;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

/**
 * @author : 周小兵
 * @date 创建时间：2017年9月8日 上午9:15:37
 * @version 1.0
 * @parameter
 * @since
 * @return
 */
public class QunChatDao implements IQunChatDao {

	ArrayList arrayList = new ArrayList();

	public void insertChat(QunChat qunChat) throws Exception {
		JDBCUtil jdbcUtil = new JDBCUtil();
		jdbcUtil.connection();
		Connection connection = jdbcUtil.getConnection();
		StringBuffer sql = new StringBuffer();
		sql.append("insert into group_chat ");
		sql.append("(send_userId,group_id,datetime,content)");
		sql.append(" values (?,?,?,?)");
		try {
			PreparedStatement prepareStatement = connection.prepareStatement(sql.toString(),
					PreparedStatement.RETURN_GENERATED_KEYS);
			int index = 1;
			prepareStatement.setObject(index++, qunChat.getSeedId());
			prepareStatement.setObject(index++, qunChat.getQunId());
			prepareStatement.setObject(index++, qunChat.getDatetime());
			prepareStatement.setObject(index++, qunChat.getContent());

			int rowsCount = prepareStatement.executeUpdate();
			if (rowsCount != 1) {
				throw new Exception("发送失败");
			} else {
				ResultSet resultSet = prepareStatement.getGeneratedKeys();
				if (resultSet.next()) {

					qunChat.setChatId(resultSet.getInt(1));

					System.out.println("消息编号:" + resultSet.getInt(1));


				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception("发送失败" + e);
		}

	}

	// 通过群id获取群内的信息
	@SuppressWarnings("unchecked")
	public ArrayList getChat(String groupId) throws Exception {
		JDBCUtil jdbcUtil = new JDBCUtil();
		jdbcUtil.connection();
		Connection connection = jdbcUtil.getConnection();
		StringBuffer sql = new StringBuffer();
		sql.append("select  content, send_userId ,datetime ");
		sql.append("from group_chat ");
		sql.append("where group_id = ?");

		try {
			PreparedStatement prepareStatement = connection.prepareStatement(sql.toString());

			int index = 1;

			prepareStatement.setObject(index, groupId);
			ResultSet resultSet = prepareStatement.executeQuery();

			HashMap hashMap = new HashMap();
			while (resultSet.next()) {
				UserDao dao = new UserDao();
				User user = new User();

				String sendUserId = resultSet.getString("send_userId");
				String sendUserNick = dao.load(Integer.parseInt(sendUserId));
				hashMap.put(sendUserId, sendUserNick);
				System.out.println("qunchatdao:" + sendUserId + "昵称：" + sendUserNick);

				arrayList.add("\t\t" + resultSet.getString("datetime") + "\n\n"
						+ hashMap.get(resultSet.getString("send_userId")) + ":" 
						+ resultSet.getString("content") + "\n\n");

			}

			System.out.println("遍历信息:");


			for (int i = 0; i < arrayList.size(); i++) {

				System.out.println("qunchatdao信息为:\t" + arrayList.get(i));
				
			}

			return arrayList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception(e);
		}

	}

//	public static void main(String[] args) {
//		QunChatDao chatDao = new QunChatDao();
//		try {
//			chatDao.getChat("1");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

}
