package com.Tab.TT.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;

import com.Tab.TT.Entity.PersonChat;
import com.Tab.TT.dao.imp.PersonChatDao;
import com.Tab.TT.framework.context.Context;




/**
 * 个人聊天界面
 * @author 马贵鑫
 * @version 1.0
 */
public class PersonalChatFrame extends JFrame{
	
	
	
	private JButton refresh = new JButton("刷新");//关闭按钮
	private JButton send = new JButton("发送");//发送按钮
	
	private JTextArea messageLogging = new JTextArea();//消息记录
	private JScrollPane messageLoggingS = new JScrollPane(messageLogging);
	
	private JTextArea inputMessage = new JTextArea();//输入框
	private JScrollPane inputMessageS = new JScrollPane(inputMessage);
	
	private JButton closeTop = new JButton("X");
	private JButton small = new JButton("—");
	private JButton install = new JButton("▼");
	
	private JLabel imgRight = new JLabel();
	private JLabel imgFriend = new JLabel();
	
	private JLabel friendNick = new JLabel("获取好友昵称失败");
	
	private JLabel showText = new JLabel("欢迎使用TT网上聊天程序(￣▽￣)~*");
	
	private int x;
	private int y;
	
	
	public PersonalChatFrame(int id,String nick){
		int userId = id;
		String userNick = nick;
		this.setTitle("个人聊天界面");
		this.setSize(680, 540);
		this.setUndecorated(true);
		this.setLocationRelativeTo(null);
		this.getContentPane().setBackground(new Color(238, 243, 249));
		this.setVisible(true);
		this.setLayout(null);
		
		ui();
		event(id,nick);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}

	private void ui() {
		this.add(showText);
		this.showText.setBounds(10, 500, 300, 30);
		
		this.add(friendNick);
		this.friendNick.setBounds(60, 5, 200, 50);
		this.friendNick.setFont(new Font("华文楷体", 1, 15));
		
		ImageIcon friend = new ImageIcon(this.getClass().getResource("/img/friendImg.png"));
		imgFriend.setIcon(friend);
		this.add(imgFriend);
		this.imgFriend.setBounds(10, 5, 50, 50);
		
		this.add(messageLoggingS);
		this.messageLoggingS.setBounds(10, 70, 520, 330);
		this.messageLoggingS.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		ImageIcon icon = new ImageIcon(this.getClass().getResource("/img/personChatImgRight.png"));
		imgRight.setIcon(icon);
		this.add(imgRight);
		this.imgRight.setBounds(535, 100, 140, 390);
		
		this.add(refresh);
		this.refresh.setBounds(360, 500, 72, 26);
		
		this.add(send);
		this.send.setBounds(440, 500, 72, 26);
		
		this.add(inputMessageS);
		this.inputMessageS.setBounds(10, 430, 520, 60);
		this.inputMessageS.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		
		
		this.add(closeTop);
		this.closeTop.setBounds(655, 0, 25, 25);
		this.closeTop.setMargin(new Insets(0, 0, 0, 0));
		this.closeTop.setBackground(new Color(238, 238, 238));
		this.closeTop.setBorder(null);
		this.closeTop.setFocusPainted(false);
		
		this.add(small);
		this.small.setBounds(630, 0, 25, 25);
		this.small.setMargin(new Insets(0, 0, 0, 0));
		this.small.setBackground(new Color(238, 238, 238));
		this.small.setBorder(null);
		this.small.setFocusPainted(false);
		
		this.add(install);
		this.install.setBounds(605, 0, 25, 25);
		this.install.setMargin(new Insets(0, 0, 0, 0));
		this.install.setBackground(new Color(238, 238, 238));
		this.install.setBorder(null);
		this.install.setFocusPainted(false);
		
		
		
	}

	private void event(int id,String nick) {
		closeClick();
		smallClick();
		sendClick(id);
		messageLogin();
		move();
		focu();
		friendNick(nick);
		
	}


	

	private void friendNick(String nick) {
		this.friendNick.setText(nick);
	}

//	设置焦点停留在输入框中
	private void focu() {
		this.addWindowListener(new WindowAdapter() {
	
			@Override
			public void windowOpened(WindowEvent e) {
				PersonalChatFrame.this.inputMessage.requestFocus();
				
			}
			
			
		});
		
	}

	private void messageLogin() {
		this.messageLogging.addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				 
                    e.consume(); //关键，屏蔽掉非法输入  
				
			}
		});
		
	}

	private void sendClick(int id) {
		final int receiveId = id;
		this.send.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if("".equals(inputMessage.getText().trim())){
					
				}else{
					Date date = new Date();
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
					String datetime = simpleDateFormat.format(date);
					PersonChat personChat = new PersonChat();
					personChat.setSendId(Context.currentUser.getUserId());
					personChat.setReceiveId(receiveId);
					personChat.setDatetime(datetime);
					personChat.setMessageState(0);
					personChat.setContent(inputMessage.getText());
					PersonChatDao personChatDao = new PersonChatDao();
					int chatId = personChatDao.insert(personChat);
					PersonChat receive = personChatDao.load(chatId);
					messageLogging.setText(messageLogging.getText()+"\n\n"+receive.getDatetime()+"\n"+receive.getContent());
					inputMessage.setText("");
					
				}
				PersonalChatFrame.this.inputMessage.setText("");
			}
		});
		
	}

	private void move() {
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				x = e.getX();
				y = e.getY();
				
			}
			
		});
		this.addMouseMotionListener(new MouseMotionAdapter() {
		
			@Override
			public void mouseDragged(MouseEvent e) {
				PersonalChatFrame.this.setLocation((int)(PersonalChatFrame.this.getLocation().getX()+
						(e.getX()-x)), (int)(PersonalChatFrame.this.getLocation().getY()+
						(e.getY()-y)));
				
			}
		});
		
	}

	private void smallClick() {
		this.small.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseExited(MouseEvent e) {
				PersonalChatFrame.this.small.setBackground(new Color(238, 238, 238));
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				PersonalChatFrame.this.small.setBackground(new Color(229, 229, 229));
				
			}
		});
		
		this.small.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				PersonalChatFrame.this.setExtendedState(ICONIFIED);
				
			}
		});
		
	}

	private void closeClick() {
		this.refresh.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				PersonChatDao personChatDao = new PersonChatDao();
				personChatDao.select(Context.currentUser.getUserId());
				for(int i=0;i<Context.personChatList.size();i++){
					messageLogging.setText(messageLogging.getText()+"\n\n"+Context.personChatList.get(i).getDatetime()+"\n"+Context.personChatList.get(i).getContent());
					personChatDao.updateMessageState(Context.personChatList.get(i).getChatId());
				}
				Context.personChatList.clear();
			}
		});
		this.closeTop.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseExited(MouseEvent e) {
				PersonalChatFrame.this.closeTop.setBackground(new Color(238, 238, 238));
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				PersonalChatFrame.this.closeTop.setBackground(new Color(251, 97, 85));
				
			}
		});
		
		this.closeTop.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				
			}
		});
		
	}

	/*public static void main(String[] args) {
		PersonalChatFrame personalChat = new PersonalChatFrame();

	}*/

}
