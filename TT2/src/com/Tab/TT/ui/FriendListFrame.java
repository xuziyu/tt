package com.Tab.TT.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.Tab.TT.Entity.Group;
import com.Tab.TT.Entity.User;
import com.Tab.TT.dao.imp.GroupDao;
import com.Tab.TT.framework.context.Context;

public class FriendListFrame extends JFrame {

	// 好友数组按钮和群数组按钮
	private JButton[] buttonFriendsArr = new JButton[Context.friendslist.size()];
	private JButton button = new JButton("sss");
	// 好友面板滚动条
	private JPanel panelFriends = new JPanel();
	private JScrollPane scrollPaneFriends = new JScrollPane();
	private String chatId;
	private JButton buttonclose = new JButton("关闭");

	public FriendListFrame(String chatId) {
		this.chatId = chatId;
		this.setTitle("好友列表");
		this.setSize(200, 330);
		this.setLocationRelativeTo(null);
		this.setUndecorated(true);
		this.setVisible(true);
		this.setLayout(null);

		User user = new User();
		user.setUserId(1);
		user.setUserNick("setUserNick");
		for (int i = 0; i < 10; i++) {
			Context.friendslist.add(user);
		}

		this.add(buttonclose);
		this.buttonclose.setBounds(0, 300, 200, 30);
		this.buttonclose.setMargin(new Insets(0, 0, 0, 0));
		this.buttonclose.setBorder(null);
		this.initUI();
		this.initEvent();
	}

	private void initEvent() {
		this.buttonclose.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				
			}
		});
		
	}

	private void initUI() {

		
		this.add(panelFriends);
		this.panelFriends.setLayout(new GridLayout(200, 0));
		System.out.println("个数:" + buttonFriendsArr.length);
		System.out.println("geshu" + Context.friendslist.size());
		// 获取好友动态数组，并转换成按钮显示，添加到好友面板中
		for (int i = 0; i < buttonFriendsArr.length; i++) {
			buttonFriendsArr[i] = new JButton();
			buttonFriendsArr[i].setText(
					Context.friendslist.get(i).getUserNick() + "(" + Context.friendslist.get(i).getUserId() + ")");
			this.buttonFriendsArr[i].setFont(new Font("楷体", Font.PLAIN, 15));
			this.buttonFriendsArr[i].setSize(290, 20);////////////重要重要*********************
			this.buttonFriendsArr[i].setBackground(new Color(214, 219, 223));
			this.buttonFriendsArr[i].setFocusPainted(false);
			this.buttonFriendsArr[i].setHorizontalAlignment(10);
			panelFriends.add(buttonFriendsArr[i]);
			
			final int j = i;
			this.buttonFriendsArr[i].addActionListener(new ActionListener() {
				
				

				@Override
				public void actionPerformed(ActionEvent e) {
					Group group = new Group();
					group.setGroupId(Integer.parseInt(chatId));
					group.setUserId(Context.friendslist.get(j).getUserId());
					GroupDao dao = new GroupDao();
					try {
						dao.insertMember(group);
						JOptionPane.showMessageDialog(null, "成功添加该好友为成群成员");
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			});

		}
		System.out.println("个数:" + buttonFriendsArr.length);

//		panelFriends.add(button);
		// 添加滚动条的面板
		this.add(scrollPaneFriends);
		// 将好友面板添加到滚动条中
		this.scrollPaneFriends.setViewportView(panelFriends);
		this.scrollPaneFriends.setBounds(0, 0, 200, 333);
		this.scrollPaneFriends.setBorder(null);
		this.scrollPaneFriends.setBackground(Color.red);

		
	}

	public static void main(String[] args) {
		new FriendListFrame(2+"");
	}
}
