package com.Tab.TT.Entity;
/** 
* @author : 周小兵
* @date 创建时间：2017年9月7日 下午8:24:19 
* @version 1.0 
* @parameter  
* @since  
* @return  
*/
public class QunChat {
	private int chatId;
	private String qunId;
	private String seedId;
	private String datetime;
	private String content;
	
	public int getChatId() {
		return chatId;
	}
	public void setChatId(int chatId) {
		this.chatId = chatId;
	}
	public String getQunId() {
		return qunId;
	}
	public void setQunId(String qunId) {
		this.qunId = qunId;
	}
	public String getSeedId() {
		return seedId;
	}
	public void setSeedId(String seedId) {
		this.seedId = seedId;
	}
	public String getDatetime() {
		return datetime;
	}
	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
