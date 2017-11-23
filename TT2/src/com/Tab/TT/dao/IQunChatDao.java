package com.Tab.TT.dao;

import java.util.ArrayList;

import com.Tab.TT.Entity.QunChat;

/** 
* @author : 周小兵
* @date 创建时间：2017年9月9日 上午10:47:17 
* @version 1.0 
* @parameter  
* @since  
* @return  
*/
public interface IQunChatDao {

	public void insertChat(QunChat qunChat) throws Exception;
	
	public ArrayList getChat(String groupId) throws Exception;
	
}
