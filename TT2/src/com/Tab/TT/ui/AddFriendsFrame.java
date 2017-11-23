package com.Tab.TT.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.Tab.TT.Entity.User;
import com.Tab.TT.dao.imp.FriendsDao;
import com.Tab.TT.dao.imp.UserDao;
import com.Tab.TT.framework.context.Context;
import com.Tab.TT.framework.exception.SystemException;
/**
 * 添加好友
 * 		查找界面
 * 
 * @author wjh
 *
 * 时间 2017/08/28
 */
public class AddFriendsFrame extends JFrame{
	
	private JButton buttonColse = new JButton("╳");
	private JButton buttonMinimize = new JButton("─");
	private JLabel BlueImage = new JLabel();//顶部背景为蓝色
	private JLabel penguin = new JLabel();//添加企鹅图片
	private JLabel chazhao = new JLabel("添加好友");
	
	private JLabel labelPeple = new JLabel("找人");
	private JLabel labeltQun = new JLabel("找群");
	private JLabel labelZhuBo = new JLabel("找主播");
	private JLabel labelKeCheng = new JLabel("找课程");
	private JLabel labelFuWu = new JLabel("找服务");
	
	private JTextField textFieldInput = new JTextField("请输入TT号");
	//好友id
	public static String textstaticFieldInput ;
	//好友昵称
	public static String friendstaticNick;
	
	private JTextField textFieldAddress = new JTextField("所在地:");
	private JTextField textFieldAddressCountry = new JTextField("故乡:");
	
	private JTextField Gender = new JTextField("性别:");
	private JTextField textFieldAge = new JTextField("年龄:");
	
	private JCheckBox checkBoxOnline = new JCheckBox("在 线");
	private JCheckBox checkBoxShengXiangTou = new JCheckBox("摄像头");
			
	private JButton buttonselect = new JButton("查找");
	
	private JLabel labelTongCheng = new JLabel("同城交友 同城老乡 附近的人");
	
	//鼠标位置
	private int oldX ;
	private int oldY ;
	public AddFriendsFrame(){
		
	}
	public AddFriendsFrame(int userId , String nick){
		//窗体标题
		this.setTitle("添加好友");
		//窗体大小
		this.setSize(650, 400);
		//窗体位置居中
		this.setLocationRelativeTo(null);
		//空布局
		
		this.setUndecorated(true);
		
		this.init();
		//顶部的设置
		this.init0Top();
		//中间的文字设置
		this.init2Serach();
		//文本框的属性设置
		this.init4Input();
		//查找按钮的设置
		this.init6Seclet();
		//文本框事件
		this.field();
		
		
		
//		//添加企鹅图片
//		ImageIcon iconP = new ImageIcon(this.getClass().getResource("/img/PenguinBlue.png"));
//		penguin = new JLabel(iconP);
//		this.add(penguin);
//		this.penguin.setBounds(5, 5, iconP.getIconWidth(), iconP.getIconHeight());
		//顶部添加蓝色图片
		ImageIcon icon = new ImageIcon(this.getClass().getResource("/img/Blue.png"));
		BlueImage = new JLabel(icon);
		this.add(BlueImage);
		this.BlueImage.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight());
		
		//关闭按钮事件
		this.buttonColse.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				
			}
		});

		//窗口最小化事件
		this.buttonMinimize.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AddFriendsFrame.this.setExtendedState(ICONIFIED);
				
			}
		});
		
		//窗口移动事件
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				oldX = e.getX();
				oldY = e.getY();
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		this.addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				AddFriendsFrame.this.setLocation((int)(AddFriendsFrame.this.getLocation().getX()+e.getX()-oldX),
						(int)(AddFriendsFrame.this.getLocation().getY()+e.getY()-oldY));
				
			}
		});
		
		
		
		//点击查找按钮跳转下一界面
		this.buttonselect.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				UserDao userDao = new UserDao();
				User user = new User();
				if (textFieldInput.getText().length()>9) {
					JOptionPane.showMessageDialog(null, "账号输入过长，请重新输入");
				}else if("".equals(textFieldInput.getText().trim())){
					JOptionPane.showMessageDialog(null, "账号输入不能为空，请重新输入");
				}else{
					//把信息转换成静态，方便后面调用
					textstaticFieldInput = textFieldInput.getText();
					//转换成整型，查找好友昵称
					int Id = Integer.parseInt(textstaticFieldInput);									
					try{						
						if (userDao.load(Id) == null) {
							JOptionPane.showMessageDialog(null, "该账号不存在，请重新输入");						
						}else if(Id == Context.currentUser.getUserId()){
							JOptionPane.showMessageDialog(null, "查询为本人，请重新输入");
						}else{
							for (int i = 0; i < Context.friendslist.size(); i++) {
								if(Id == Context.friendslist.get(i).getUserId()){
									JOptionPane.showMessageDialog(null, "该用户已经是您的好友");
									return;
								}
							}
							friendstaticNick = userDao.load(Id);
							JOptionPane.showMessageDialog(null, "查询结果为："+friendstaticNick);
							AddFriendsSendYanZhengFrame add = new AddFriendsSendYanZhengFrame(Context.currentUser.getUserId(),Context.currentUser.getUserNick());									
							dispose();							
						}						
					} catch(SystemException se){
						se.printStackTrace();
					}
				}
			}
		});
		
		
		
		//可见
		this.setVisible(true);
	}
	


	//鼠标进入文本框事件
	private void field() {
		this.textFieldInput.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AddFriendsFrame.this.textFieldInput.setText("");
			}
			
		});
		this.textFieldAddress.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AddFriendsFrame.this.textFieldAddress.setText("");
			}		
		});
		this.textFieldAddressCountry.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AddFriendsFrame.this.textFieldAddressCountry.setText("");
			}			
		});
		this.Gender.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AddFriendsFrame.this.Gender.setText("");
			}
		});
		this.textFieldAge.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AddFriendsFrame.this.textFieldAge.setText("");
			}
		});
		
		
		
	}



	private void init0Top() {
		this.init();
		//顶部的标签查找
		this.add(this.chazhao);
		this.chazhao.setBounds(30, 5, 50, 20);
		this.chazhao.setForeground(Color.WHITE);
		this.chazhao.setFont(new Font("华文楷体", Font.PLAIN, 12));
		//关闭按钮
		this.add(this.buttonColse);
		this.buttonColse.setBounds(620, 5, 25, 20);
		this.buttonColse.setMargin(new Insets(0, 0, 0, 0));
		this.buttonColse.setBackground(new Color(92, 190, 229));
		this.buttonColse.setForeground(Color.white);
		this.buttonColse.setBorder(null);
		this.buttonColse.setFocusPainted(false);
		//最小化按钮
		this.add(this.buttonMinimize);
		this.buttonMinimize.setBounds(595, 5, 25, 20);
		this.buttonMinimize.setMargin(new Insets(0, 0, 0, 0));
		this.buttonMinimize.setBackground(new Color(92, 190, 229));
		this.buttonMinimize.setForeground(Color.white);
		this.buttonMinimize.setBorder(null);
		this.buttonMinimize.setFocusPainted(false);
		
	}
	
	private void init6Seclet() {
		this.init();
		//查找
		this.add(this.buttonselect);
		this.buttonselect.setBounds(380, 110, 90, 40);
		this.buttonselect.setForeground(Color.WHITE);
		this.buttonselect.setBackground(new Color(92, 190, 229));
		this.buttonselect.setFont(new Font("华文楷体", Font.PLAIN, 20));
		//同城
		this.add(this.labelTongCheng);
		this.labelTongCheng.setBounds(480, 120, 170, 25);
		this.labelTongCheng.setForeground(Color.gray);
		this.labelTongCheng.setFont(new Font("华文楷体", Font.PLAIN, 10));
		
	}
	
	private void init4Input() {
		this.init();
		Font font = new Font("楷体", Font.PLAIN, 12);
		this.textFieldInput.setFont(font);
		this.checkBoxOnline.setFont(font);
		this.textFieldAddress.setFont(font);
		this.textFieldAddressCountry.setFont(font);
		this.Gender.setFont(font);
		this.textFieldAge.setFont(font);
		this.checkBoxShengXiangTou.setFont(font);	
		//输入QQ号
		this.add(this.textFieldInput);
		this.textFieldInput.setBounds(20, 100, 265, 25);
		//在线--单选框
		this.add(this.checkBoxOnline);
		this.checkBoxOnline.setBounds(295, 100, 60, 25);
		
		
		//地址
		this.add(this.textFieldAddress);
		this.textFieldAddress.setBounds(20, 130, 75, 25);
		//故乡
		this.add(this.textFieldAddressCountry);
		this.textFieldAddressCountry.setBounds(100, 130, 75, 25);
		//性别
		this.add(this.Gender);	
		this.Gender.setBounds(180, 130, 50, 25);
		this.Gender.setBackground(Color.WHITE);
		//年龄
		this.add(this.textFieldAge);
		this.textFieldAge.setBounds(235, 130, 50, 25);
		//摄像头
		this.add(this.checkBoxShengXiangTou);
		this.checkBoxShengXiangTou.setBounds(295, 130, 65, 25);
		
	}
	
	private void init2Serach() {
		this.init();
		Font font = new Font("楷体", Font.PLAIN, 20);
		this.labelPeple.setFont(font);
		this.labeltQun.setFont(font);
		this.labelZhuBo.setFont(font);
		this.labelKeCheng.setFont(font);
		this.labelFuWu.setFont(font);
		
		//找人
		this.add(this.labelPeple);
		this.labelPeple.setBounds(130, 30, 60, 50);
		this.labelPeple.setForeground(Color.WHITE);
		//找群
		this.add(this.labeltQun);
		this.labeltQun.setBounds(200, 30, 60, 50);
		this.labeltQun.setForeground(Color.WHITE);
		//找主播
		this.add(this.labelZhuBo);
		this.labelZhuBo.setBounds(270, 30, 80, 50);
		this.labelZhuBo.setForeground(Color.WHITE);
		//找课程
		this.add(this.labelKeCheng);
		this.labelKeCheng.setBounds(360, 30, 80, 50);
		this.labelKeCheng.setForeground(Color.WHITE);
		//找服务
		this.add(this.labelFuWu);
		this.labelFuWu.setBounds(460, 30, 80, 50);
		this.labelFuWu.setForeground(Color.WHITE);
		
	}
	private void init() {
		this.setLayout(null);
		
	}
	public static void main(String[] args) {
		try {
			AddFriendsFrame addFriends = new AddFriendsFrame(Context.currentUser.getUserId(),Context.currentUser.getUserNick());
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "您还没有登录");
		}
	}
}
