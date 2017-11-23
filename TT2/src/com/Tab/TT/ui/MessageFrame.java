package com.Tab.TT.ui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MessageFrame extends JFrame{
	private JLabel message = new JLabel("一条信息");
	private JButton close = new JButton("关闭");
	
	public MessageFrame(){
		
		this.setSize(300, 300);
		this.setUndecorated(true);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setLayout(null);
		
		ui();
		event();
	}
	
	private void event() {
		// TODO Auto-generated method stub
		
	}

	private void ui() {
		this.add(this.getMessage());
		
		this.message.setBounds(150, 150, 100, 50);
		this.message.setText("显示消息");
		
		
	}

	public static void main(String[] args) {
		MessageFrame a= new MessageFrame();
	}

	public JLabel getMessage() {
		return message;
	}

	public void setMessage(JLabel message) {
		this.message = message;
	}

	public JButton getClose() {
		return close;
	}

	public void setClose(JButton close) {
		this.close = close;
	}
	
	
	
}
