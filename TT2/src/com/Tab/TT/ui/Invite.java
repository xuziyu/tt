package com.Tab.TT.ui;

import java.awt.Color;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.Tab.TT.Entity.Group;

import com.Tab.TT.dao.IUserTabDao;
import com.Tab.TT.framework.context.Context;
import com.Tab.TT.framework.properties.util.proUtil;

/** 
 * 创建群
* @author : 白长昊
* @date 创建时间：2017年8月28日 下午6:29:48 
* @version 1.0 
* @parameter  
* @since  
* @return  
*/
public class Invite extends JFrame{
	 private JLabel gropu=new JLabel("创建群");
	 private JLabel groupType=new JLabel("1"+"   选择群类型");
	 private JLabel fillInInformation =new JLabel("2"+"   填写群信息");
	 private JLabel invite=new JLabel("3"+"   邀请群成员");
	 private JTextField text1=new JTextField("插入查找关键词");
	 private JButton back =new JButton("上一步");
	 private JButton achieve=new JButton("完成");
	 private JButton add =new JButton("添加");
	 private JButton remove=new JButton("移除");
	 private JTextField friend=new JTextField();
	 private JTextField groupFriend=new JTextField();
	    private JButton close = new JButton("X");
		private JButton small = new JButton("—");
		 private int x  ;
			private int y  ;
	public Invite(){
		this.setTitle("创建群");
		this.setSize(544, 450);
		this.setUndecorated(true);
		this.setVisible(true);
		this.setLayout(null);
		this.setLocationRelativeTo(null);
		this.add(gropu);
		this.gropu.setBounds(5, 0, 80, 40);
		this.add(small);
		this.small.setBounds(490, 0, 25, 25);
		this.small.setMargin(new Insets(0, 0, 0, 0));
		this.small.setBackground(new Color(238, 238, 238));
		this.small.setBorder(null);
		this.small.setFocusPainted(false);
		this.add(close);
		this.close.setBounds(512, 0, 30, 25);
		this.close.setMargin(new Insets(0, 0, 0, 0));
		this.close.setBackground(new Color(238, 238, 238));
		this.close.setBorder(null);
		this.close.setFocusPainted(false);
		this.add(groupType);
		this.groupType.setBounds(10, 40, 220, 32);
		this.add(fillInInformation);
		this.fillInInformation.setBounds(225, 40, 220, 32);
		this.add(invite);
		this.invite.setBounds(445, 40, 180, 32);
		this.add(text1);
		this.text1.setBounds(5, 80, 200, 22);
		this.add(back);
		this.back.setBounds(390, 423, 75, 25);
		this.add(achieve);
		this.achieve.setBounds(465, 423, 75, 25);
		this.add(add);
		this.add.setBounds(220, 170, 60, 30);
		this.add(remove);
		this.remove.setBounds(220, 230, 60, 30);
		this.add(friend);
		this.friend.setBounds(30, 120, 160, 210);
		this.add(groupFriend);
		this.groupFriend.setBounds(350, 120, 160 ,210);
		move();
        this.close.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				
			}
		});
		
	this.small.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Invite.this.setExtendedState(ICONIFIED);
				
			}
		});
	this.small.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseExited(MouseEvent e) {
				Invite.this.small.setBackground(new Color(238, 238, 238));
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				Invite.this.small.setBackground(new Color(229, 229, 229));
				
			}
		});
      
      
      this.close.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseExited(MouseEvent e) {
				Invite.this.close.setBackground(new Color(238, 238, 238));
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				Invite.this.close.setBackground(new Color(251, 97, 85));
				
			}
		});
		this.back.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				FillInInformation fillininformation=new FillInInformation (FillInInformation.text);
					
					
			}
		});
		 
		this.achieve.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				
				Group b= new Group();
				 
				 b.setGroupName(FillInInformation.text2);
				 b.setGroupType(FillInInformation.text);
				 b.setGroupNumber(FillInInformation.max);
				 b.setUserId(Context.currentUser.getUserId());
				proUtil a =new proUtil();
				IUserTabDao groupdao =a.getGroupDao();
				int groupId = groupdao.Test1(b);
				
				FillInInformationAchieve fillininformationAchieve = new FillInInformationAchieve(groupId);
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
					Invite.this.setLocation((int)(Invite.this.getLocation().getX()+
							(e.getX()-x)), (int)(Invite.this.getLocation().getY()+
							(e.getY()-y)));
					
				}
			});
			
		
	}
	public static void main(String[] args) {
		Invite invite=new Invite();

	}

}
