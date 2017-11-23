package com.Tab.TT.ui;
/**
 * @author wjh
 * 
 * 时间：2017/09/05 
 * 
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.Tab.TT.Entity.User;
import com.Tab.TT.dao.imp.FriendsDao;
import com.Tab.TT.dao.imp.UserDao;
import com.Tab.TT.framework.context.Context;


public class AddFriendsSuccessFrame extends JFrame{
	private JButton buttonColse = new JButton("Χ");
	private JButton buttonMinimize = new JButton("－");
	
	private JLabel penguin = new JLabel();
	private JLabel BlueImage = new JLabel();
	
	private JLabel labelFriendsId = new JLabel("好友TT账号：");
	private JLabel labelFriendsIdShow = new JLabel();
	private JLabel labelFriendsNick = new JLabel("好友TT昵称：");
	private JLabel labelFriendsNickShow = new JLabel();
	private JLabel labelSuccess = new JLabel();//添加成功标签
	
	
	private JLabel labelAddFriends = new JLabel("添加好友");
	
	private JButton buttonAccomplish = new JButton("完成");
	
	private int oldX ; 
	private int oldY ;
	

	public AddFriendsSuccessFrame(int userId , String nick){
		super("添加好友成功");
		this.setSize(460, 360);
		
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		this.setUndecorated(true);
//		this.setResizable(true);
		
		this.init();
		this.init2Top();//顶部
		this.init4Information();//中间
		this.init6Bottom();//底部
		
		
		//企鹅logo
//		ImageIcon iconP = new ImageIcon(this.getClass().getResource("/img/PenguinBLue.png"));
//		penguin = new JLabel(iconP);
//		this.add(penguin);
//		this.penguin.setBounds(5, 5, iconP.getIconWidth(), iconP.getIconHeight());
		
		//顶部添加蓝色图片
		ImageIcon icon = new ImageIcon(this.getClass().getResource("/img/Blue.png"));
		BlueImage = new JLabel(icon);
		this.add(BlueImage);
		this.BlueImage.setBounds(0, 0, icon.getIconWidth(), 30);
				
		//完成按钮
		this.buttonAccomplish.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//获得好友的id
				int id  = Integer.parseInt(AddFriendsFrame.textstaticFieldInput);
				
				FriendsDao friendsDao = new FriendsDao();
				//将好友的信息加到数据库的好友表中
				friendsDao.insert(Context.currentUser.getUserId());
				//将自己的信息加入到对方的好友信息表中
				friendsDao.insertSelf(id);
				//将申请信息加入到好友申请表中
				friendsDao.insertFriendsApply(Context.currentUser.getUserId());
				JOptionPane.showMessageDialog(null, "添加好友成功,请刷新好友列表");
				
				dispose();				
			}
		});
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
				AddFriendsSuccessFrame.this.setExtendedState(ICONIFIED);
				
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
				AddFriendsSuccessFrame.this.setLocation((int)(AddFriendsSuccessFrame.this.getLocation().getX()+e.getX()-oldX),
						(int)(AddFriendsSuccessFrame.this.getLocation().getY()+e.getY()-oldY));
				
			}
		});
		
		this.setVisible(true);
	}
	private void init4Information() {
		this.init();
		//好友信息
		this.add(labelFriendsId);
		this.labelFriendsId.setBounds(10, 100, 100, 25);
		this.add(labelFriendsIdShow);
		this.labelFriendsIdShow.setBounds(20, 130, 200, 25);
			//上一界面输入的好友id
		this.labelFriendsIdShow.setText(AddFriendsFrame.textstaticFieldInput);
		this.labelFriendsIdShow.setForeground(Color.BLUE);
		this.add(labelFriendsNick);
		this.labelFriendsNick.setBounds(10, 170, 100, 25);
		this.add(labelFriendsNickShow);
		this.labelFriendsNickShow.setBounds(20, 200, 200, 25);
			//上一界面查出的好友昵称
		this.labelFriendsNickShow.setText(AddFriendsFrame.friendstaticNick);
		this.labelFriendsNickShow.setForeground(Color.BLUE);
		//添加成功信息
		this.add(labelSuccess);
		this.labelSuccess.setBounds(140, 150, 250, 30);
		this.labelSuccess.setText("点击完成按钮,你们就能成为好友!");
		this.labelSuccess.setFont(new Font("华文楷体", Font.PLAIN, 15));
		
	}
	private void init6Bottom() {
		this.init();
		this.add(buttonAccomplish);
		this.buttonAccomplish.setBounds(370, 325, 70, 25);
		this.buttonAccomplish.setFont(new Font("华文楷体", Font.PLAIN, 12));
		
	}
	private void init2Top() {
		//标题
		this.add(labelAddFriends);
		this.labelAddFriends.setBounds(30, 5, 100, 20);
		this.labelAddFriends.setFont(new Font("华文楷体", Font.PLAIN, 12));
		//关闭按钮
		this.add(this.buttonColse);
		this.buttonColse.setBounds(430, 5, 25, 20);
		this.buttonColse.setMargin(new Insets(0, 0, 0, 0));
		this.buttonColse.setBackground(new Color(92, 190, 229));
		this.buttonColse.setForeground(Color.white);
		this.buttonColse.setBorder(null);
		this.buttonColse.setFocusPainted(false);
		//最小化按钮
		this.add(this.buttonMinimize);
		this.buttonMinimize.setBounds(400, 5, 25, 20);
		this.buttonMinimize.setMargin(new Insets(0, 0, 0, 0));
		this.buttonMinimize.setBackground(new Color(92, 190, 229));
		this.buttonMinimize.setForeground(Color.white);
		this.buttonMinimize.setBorder(null);
		this.buttonMinimize.setFocusPainted(false);
				
	}
	private void init() {
		this.setLayout(null);
		
	}
	public static void main(String[] args) {
		try {
			AddFriendsSuccessFrame addFriendsSuccess = new AddFriendsSuccessFrame(Context.currentUser.getUserId(),Context.currentUser.getUserNick());
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "您还没有登录");
		}

	}

}

