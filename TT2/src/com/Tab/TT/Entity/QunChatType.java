package com.Tab.TT.Entity;
/** 
* @author : 周小兵
* @date 创建时间：2017年9月8日 上午9:51:41 
* @version 1.0 
* @parameter  
* @since  
* @return  
*/
public class QunChatType {
	
	private int chatTypeId;
	private int chatUserId;
	private int chatId;
	private byte whetherRead;
	
	public int getChatTypeId() {
		return chatTypeId;
	}
	public void setChatTypeId(int chatTypeId) {
		this.chatTypeId = chatTypeId;
	}
	public int getChatUserId() {
		return chatUserId;
	}
	public void setChatUserId(int chatUserId) {
		this.chatUserId = chatUserId;
	}
	public int getChatId() {
		return chatId;
	}
	public void setChatId(int chatId) {
		this.chatId = chatId;
	}
	public byte getWhetherRead() {
		return whetherRead;
	}
	public void setWhetherRead(byte whetherRead) {
		this.whetherRead = whetherRead;
	}
}
