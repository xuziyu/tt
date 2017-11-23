package com.Tab.TT.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.StyledEditorKit.ForegroundAction;

import com.Tab.TT.Entity.Friends;
import com.Tab.TT.Entity.User;
import com.Tab.TT.dao.IFriendsDao;
import com.Tab.TT.dao.imp.FriendsDao;
import com.Tab.TT.dao.imp.QunDao;
import com.Tab.TT.framework.context.Context;

/** 
 * 主界面
* @author : wjh
* @date 创建时间：2017年8月28日 下午5:36:22 
* @version 1.0 
* @parameter  
* @since  
* @return  
*/
public class MainFrame extends JFrame{
	private JButton buttonColse = new JButton("╳");
	private JButton buttonMinimize = new JButton("─");
	private JTextField textFieldSearch = new JTextField(" 搜索");
	
	private JLabel penguinLogo = new JLabel();
	private JLabel labelNick = new JLabel();
	private JLabel labelGeXingQianMing = new JLabel();
		
	private JButton buttonFriends = new JButton("好友");
	private JButton buttonQun = new JButton("群");
	private JButton buttonChat = new JButton("刷新");
	
	//好友数组按钮和群数组按钮
	private JButton[] buttonFriendsArr = new JButton[Context.friendslist.size()];
	private JButton[] buttonQunArr = new JButton[Context.qunlist.size()];
	
	//好友面板滚动条
	private JPanel panelFriends = new JPanel();
	private JScrollPane scrollPaneFriends = new JScrollPane();	
	
	//群面板滚动条
	private JPanel panelQun = new JPanel();
	private JScrollPane scrollPaneQun = new JScrollPane();
	
	//底部功能区
	private JButton buttonMainMenu = new JButton("☰");
	private JButton buttonAddFriends = new JButton("△");
	private JButton buttonAddQun = new JButton("▽");
	private JButton buttonAPPManager = new JButton("☷");
	private JButton buttonChangeMessage = new JButton("卍");
	private JButton buttonChangePassword = new JButton("※");
	
	private JLabel TopLogo = new JLabel();
	private int oldX ;
	private int oldY ;
	public MainFrame(){		
		this.setTitle("主界面");
		this.setSize(290, 700);
		this.setLocation(1200, 50);
		this.setUndecorated(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.init();
		this.initInformation();
		this.initLogo();//顶部按钮
		this.initMain();//主体
		this.intiFunction();//底部功能区
		this.TopLogo();//顶部图片
		this.fontstyle();
		
		
		//刷新按钮
		this.buttonChat.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				//将好友动态数组里的数据全部清除
				Context.friendslist.clear();
				FriendsDao friendsDao = new FriendsDao();				
				//重新查询一遍好友
				friendsDao.getFriendsMessage(Context.currentUser.getUserId());
				//将群动态数组里的数据全部清除
				Context.qunlist.clear();
				QunDao qunDao = new QunDao();
				//重新查询一遍好友
				qunDao.getQunMessage(Context.currentUser.getUserId());
				new MainFrame();
			}
		});
		//搜索事件
		this.textFieldSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MainFrame.this.textFieldSearch.setText("");
			}
		});
		
	
		//关闭按钮事件
		this.buttonColse.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(-1);
				
			}
		});

		//窗口最小化事件
		this.buttonMinimize.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MainFrame.this.setExtendedState(ICONIFIED);
				
			}
		});
		
		//加好友事件
		this.buttonAddFriends.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				AddFriendsFrame addFriendsFrame = new AddFriendsFrame(Context.currentUser.getUserId(),Context.currentUser.getUserNick());
				
			}
		});
		
		//创建群
		this.buttonAddQun.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				CreateGroup createGroup = new CreateGroup();
				
			}
		});
		
		//修改个人资料
		this.buttonChangeMessage.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new UpdateMessageFrame();
			}
		});
		
		//修改面密码
		this.buttonChangePassword.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new UpdatePasswordFrame();
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
				MainFrame.this.setLocation((int)(MainFrame.this.getLocation().getX()+e.getX()-oldX),
						(int)(MainFrame.this.getLocation().getY()+e.getY()-oldY));
				
			}
		});
		
		this.setVisible(true);
	}
	private void initInformation() {
		//昵称
		this.add(labelNick);	
		this.labelNick.setText(Context.currentUser.getUserNick());
		this.labelNick.setBounds(80, 30, 200, 50);
		this.labelNick.setFont(new Font("楷体", Font.CENTER_BASELINE, 30));
		
		//个性签名
		this.add(labelGeXingQianMing);
		this.labelGeXingQianMing.setBounds(80, 70, 200, 30);
		this.labelGeXingQianMing.setText("编辑个性签名");
		
	}
	private void fontstyle() {
		Font font = new Font("楷体",Font.PLAIN,15);
		this.labelGeXingQianMing.setFont(font);
		this.buttonChat.setFont(font);
		this.buttonFriends.setFont(font);
		this.buttonQun.setFont(font);
		this.textFieldSearch.setFont(font);
		
		
		
	}
	private void TopLogo() {

//		//企鹅logo
//		ImageIcon iconP = new ImageIcon(this.getClass().getResource("/img/penguin.png"));
//		penguinLogo = new JLabel(iconP);
//		this.add(penguinLogo);
//		this.penguinLogo.setBounds(5, 5, iconP.getIconWidth(), iconP.getIconHeight());
		//背景图片
		ImageIcon iconImage = new ImageIcon(this.getClass().getResource("/img/touxiang.png"));
		TopLogo = new JLabel(iconImage);
		this.add(TopLogo);
		this.TopLogo.setBounds(0, 0, iconImage.getIconWidth(), iconImage.getIconHeight());
	}

	private void init() {
		this.setLayout(null);		
	}
	
	private void initLogo() {
		this.init();
		//关闭按钮
		this.add(this.buttonColse);
		this.buttonColse.setBounds(260, 5, 25, 20);
		this.buttonColse.setMargin(new Insets(0, 0, 0, 0));
		this.buttonColse.setForeground(Color.white);
		this.buttonColse.setBackground(new Color(179, 221, 235));
		this.buttonColse.setToolTipText("关闭");
		this.buttonColse.setBorder(null);
		this.buttonColse.setFocusPainted(false);
		this.buttonColse.setOpaque(false);
		//最小化按钮
		this.add(this.buttonMinimize);
		this.buttonMinimize.setBounds(235, 5, 25, 20);
		this.buttonMinimize.setMargin(new Insets(0, 0, 0, 0));
		this.buttonMinimize.setForeground(Color.white);
		this.buttonMinimize.setBackground(new Color(179, 221, 235));
		this.buttonMinimize.setToolTipText("最小化");
		this.buttonMinimize.setBorder(null);
		this.buttonMinimize.setFocusPainted(false);	
		this.buttonMinimize.setOpaque(false);
		//搜索
		this.add(this.textFieldSearch);
		this.textFieldSearch.setBounds(0, 120, 350, 30);
		this.textFieldSearch.setOpaque(false);
		
	}
	
	private void initMain() {
		
		//我的好友
		this.add(this.buttonFriends);
		this.buttonFriends.setBounds(0, 150, 96, 30);
		this.buttonFriends.setBackground(new Color(214, 219, 223));
		this.buttonFriends.setFocusPainted(false);
		//我的好友按钮点击，显示好友信息
		this.buttonFriends.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MainFrame.this.scrollPaneFriends.setVisible(true);
				MainFrame.this.scrollPaneQun.setVisible(false);
				
			}
		});
		//群
		this.add(this.buttonQun);
		this.buttonQun.setBounds(96, 150, 96, 30);
		this.buttonQun.setBackground(new Color(214, 219, 223));
		this.buttonQun.setFocusPainted(false);
		this.buttonQun.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MainFrame.this.scrollPaneFriends.setVisible(false);
				MainFrame.this.scrollPaneQun.setVisible(true);
			}
		});
		//刷新界面
		this.add(this.buttonChat);
		this.buttonChat.setBounds(192, 150, 98, 30);
		this.buttonChat.setBackground(new Color(214, 219, 223));
		this.buttonChat.setFocusPainted(false);
		
		//1-1添加好友面板
		this.add(panelFriends);
		this.panelFriends.setLayout(new GridLayout(200, 0));
		//获取好友动态数组，并转换成按钮显示，添加到好友面板中
		for (int i = 0; i < buttonFriendsArr.length; i++) {
			buttonFriendsArr[i] = new JButton();
			buttonFriendsArr[i].setText(Context.friendslist.get(i).getUserId()+Context.friendslist.get(i).getUserNick());
			this.buttonFriendsArr[i].setFont(new Font("楷体",Font.PLAIN,15));
			this.buttonFriendsArr[i].setSize(290, 20);
			this.buttonFriendsArr[i].setBackground(new Color(214, 219, 223));
			this.buttonFriendsArr[i].setFocusPainted(false);
			this.buttonFriendsArr[i].setHorizontalAlignment(10);
			panelFriends.add(buttonFriendsArr[i]);
			final int j = i;
			MainFrame.this.buttonFriendsArr[i].addActionListener(new ActionListener() {
				

				@Override
				public void actionPerformed(ActionEvent e) {
					new PersonalChatFrame(Context.friendslist.get(j).getUserId(), Context.friendslist.get(j).getUserNick());
					
				}
			});
			}
		
		
		//添加滚动条
		this.add(scrollPaneFriends);
			//将好友面板添加到滚动条中
		this.scrollPaneFriends.setViewportView(panelFriends);
		this.scrollPaneFriends.setBounds(0, 180, 290, 488);
		this.scrollPaneFriends.setBorder(null);
			//垂直滚动条需要时显示（面板的内容超出范围时显示滚动条）
		this.scrollPaneFriends.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			//水平滚动条永不显示
		this.scrollPaneFriends.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		

		//1-2添加群面板
		this.add(panelQun);
		this.panelQun.setLayout(new GridLayout(200, 1));				
		//获取群动态数组，并转换成按钮显示，添加到群面板中
		for (int i = 0; i < buttonQunArr.length; i++) {
			buttonQunArr[i] = new JButton();			
			buttonQunArr[i].setText("群主:"+Context.qunlist.get(i).getUserId()+"\t群名称:"+Context.qunlist.get(i).getGroupName());
			System.out.println("群的创建者id"+Context.qunlist.get(i).getUserId());
			this.buttonQunArr[i].setFont(new Font("楷体",Font.PLAIN,15));
			this.buttonQunArr[i].setSize(290, 20);
			this.buttonQunArr[i].setBackground(new Color(214, 219, 223));
			this.buttonQunArr[i].setHorizontalAlignment(10);
			panelQun.add(buttonQunArr[i]);
			final int j = i ;
			MainFrame.this.buttonQunArr[i].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {

					QunChatFrame chatFrame = new QunChatFrame(Context.qunlist.get(j).getGroupId()+"");

//					new QunChatFrame();
//					System.out.println(buttonQunArr[j]);
				
					
				}
			});
		}
		//添加滚动条
		this.add(scrollPaneQun);
		//将群面板添加到滚动条中
		this.scrollPaneQun.setViewportView(panelQun);
		this.scrollPaneQun.setBounds(0, 180, 290, 488);
		this.scrollPaneQun.setBorder(null);
			//垂直滚动条需要时显示（面板的内容超出范围时显示滚动条）
		this.scrollPaneQun.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			//水平滚动条永不显示
		this.scrollPaneQun.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
	
	}
	
	private void intiFunction() {
		this.init();
		//主菜单
		this.add(this.buttonMainMenu);
		this.buttonMainMenu.setBounds(0, 670, 30, 30);
		this.buttonMainMenu.setToolTipText("主菜单");
		this.buttonMainMenu.setMargin(new Insets(0, 0, 20, 0));
		this.buttonMainMenu.setBorder(null);
		this.buttonMainMenu.setBackground(Color.WHITE);
		//修改个人信息
		this.add(this.buttonChangeMessage);
		this.buttonChangeMessage.setBounds(30, 670, 30, 30);
		this.buttonChangeMessage.setToolTipText("修改个人信息");
		this.buttonChangeMessage.setMargin(new Insets(0, 0, 20, 0));
		this.buttonChangeMessage.setBorder(null);
		this.buttonChangeMessage.setBackground(Color.WHITE);
		//修改密码
		this.add(this.buttonChangePassword);
		this.buttonChangePassword.setBounds(60, 670, 30, 30);
		this.buttonChangePassword.setToolTipText("修改密码");
		this.buttonChangePassword.setMargin(new Insets(0, 0, 20, 0));
		this.buttonChangePassword.setBorder(null);
		this.buttonChangePassword.setBackground(Color.WHITE);
		//加好友
		this.add(this.buttonAddFriends);
		this.buttonAddFriends.setBounds(230, 670, 30, 30);
		this.buttonAddFriends.setToolTipText("加好友");
		this.buttonAddFriends.setMargin(new Insets(0, 0, 20, 0));
		this.buttonAddFriends.setBorder(null);
		this.buttonAddFriends.setBackground(Color.WHITE);
		//加群
		this.add(this.buttonAddQun);
		this.buttonAddQun.setBounds(200, 670, 30, 30);
		this.buttonAddQun.setToolTipText("创建群");
		this.buttonAddQun.setMargin(new Insets(0, 0, 20, 0));
		this.buttonAddQun.setBorder(null);
		this.buttonAddQun.setBackground(Color.WHITE);				
		//应用管理器
		this.add(this.buttonAPPManager);
		this.buttonAPPManager.setBounds(260, 670, 30, 30);
		this.buttonAPPManager.setToolTipText("应用管理器");
		this.buttonAPPManager.setMargin(new Insets(0, 0, 20, 0));
		this.buttonAPPManager.setBorder(null);
		this.buttonAPPManager.setBackground(Color.WHITE);
		
	}
	
	

	public static void main(String[] args) {
		try {
			MainFrame mainFrame = new MainFrame();
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "您还没有登录");
		}
		
	}

}
