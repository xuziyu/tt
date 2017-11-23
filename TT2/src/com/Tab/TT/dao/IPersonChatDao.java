package com.Tab.TT.dao;

import com.Tab.TT.Entity.PersonChat;

public interface IPersonChatDao {
	public int insert(PersonChat personChat);
	
	public PersonChat load(int chatId);
	
	public void updateMessageState(int chatId);
	
	public void select(int receiveId);
}
