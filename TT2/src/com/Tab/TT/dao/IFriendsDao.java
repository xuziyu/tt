package com.Tab.TT.dao;
/** 
* @author : 吴健豪
* @date 创建时间：2017年9月8日 下午6:08:58 
* @version 1.0 
* @parameter  
* @since  
* @return  
*/
public interface IFriendsDao {
	//登录后获取好友信息列表
	public void getFriendsMessage(int userId);
	
	//添加好友信息到自己的好友列表中
	public void insert(int userId);
	
	//将自己加到好友的列表中
	public void insertSelf(int userId);
	
	//加好友时，往申请表中添加数据
	public void insertFriendsApply(int UserId);
}
