package com.Tab.TT.ui;

import org.junit.Test;

import com.Tab.TT.Entity.User;
import com.Tab.TT.dao.imp.UserDao;

/** 
* @author : 周小兵
* @date 创建时间：2017年9月8日 下午2:12:34 
* @version 1.0 
* @parameter  
* @since  
* @return  
*/
public class TextDao {

	@Test
	public void testload(){
		User user = new User();
		user.setUserId(Integer.parseInt("123123"));
		UserDao dao = new UserDao();
		
		
		System.out.println(dao.load(user.getUserId()));
	}
	public static void main(String[] args) {
		TextDao dao = new TextDao();
		dao.test();
		
	}
	@Test
	public void test(){
		User user = new User();
		user.setUserId(2);
		UserDao dao = new UserDao();
		dao.getEveryThing(user);
		System.out.println(user.getBirthday());
		System.out.println(user.getUserNick());
	}
}
