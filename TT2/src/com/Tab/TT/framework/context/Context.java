package com.Tab.TT.framework.context;

import java.util.ArrayList;

import com.Tab.TT.Entity.Friends;
import com.Tab.TT.Entity.PersonChat;
import com.Tab.TT.Entity.User;
import com.Tab.TT.Entity.Group;

/** 
* @author : 周小兵
* @date 创建时间：2017年9月8日 上午11:15:34 
* @version 1.0 
* @parameter  
* @since  
* @return  
*/
public class Context {
	public  static User currentUser;
	public  static ArrayList<User> friendslist = new ArrayList<User>(); 
	public  static ArrayList<Group> qunlist = new ArrayList<Group>();
	public  static ArrayList<PersonChat> personChatList = new ArrayList<PersonChat>();
	
}
