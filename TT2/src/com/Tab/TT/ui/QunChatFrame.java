package com.Tab.TT.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneLayout;

import com.Tab.TT.Entity.QunChat;
import com.Tab.TT.dao.IQunChatDao;
import com.Tab.TT.dao.imp.QunChatDao;
import com.Tab.TT.framework.context.Context;
import com.Tab.TT.framework.properties.util.PropertiesUtil;

/**
 * @author : 周小兵
 * @date 创建时间：2017年8月28日 下午4:53:14
 * @version 1.0
 * @parameter
 * @since
 * @return
 */
public class QunChatFrame extends JFrame {

	private JLabel label1 = new JLabel("聊天");
	private JLabel label2 = new JLabel("公告");
	private JLabel label3 = new JLabel("相册");
	private JLabel label4 = new JLabel("文件");
	private JLabel label5 = new JLabel("活动");
	private JLabel label6 = new JLabel("设置▼");
	private JLabel label7 = new JLabel("广告");
	private JLabel labelimg = new JLabel();
	private JLabel labelJiLu = new JLabel();
	private JScrollPane jScrollPaneContent = new JScrollPane();
	// private JScrollPane jScrollPaneMemberList = new JScrollPane();
	private JScrollPane jScrollPaneInformation = new JScrollPane();

	private String chatQQId;// QQ群号

	private JTextArea textAreaContent = new JTextArea();// 编辑内容区
	private JTextArea textAreaInformation = new JTextArea();// 聊天记录
	// 好友数组按钮和群数组按钮
	private JButton[] buttonFriendsArr = new JButton[Context.friendslist.size()];

	// 好友面板滚动条
	private JPanel panelFriends = new JPanel();
	private JScrollPane scrollPaneFriends = new JScrollPane();

	/**
	 * 关闭按钮
	 */
	private JButton buttonClose = new JButton("关闭(C)");
	/**
	 * 发送按钮
	 */
	private JButton buttonSeed = new JButton("发送(S)");

	/**
	 * 右上角关闭窗口
	 */
	private JButton close = new JButton("X");
	/**
	 * 右上角最小化窗口
	 */
	private JButton small = new JButton("—");
	/**
	 * 添加群成员
	 */
	private JButton buttonAddQunmember = new JButton("添加群成员");

	public QunChatFrame(String chatQQId) {
		this.chatQQId = chatQQId;

		this.setTitle("群聊天");
		this.setSize(800, 600);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.setUndecorated(true);
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		this.setVisible(true);

		this.initLabel();
		this.initInformation();
		this.initMemberList();
		this.initimg();
		this.initEvent();

	}

	private void initMemberList() {

		this.buttonAddQunmember.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new FriendListFrame(chatQQId);
			}
		});

		this.add(panelFriends);
		this.panelFriends.setLayout(new GridLayout(200, 0));
		// 获取好友动态数组，并转换成按钮显示，添加到好友面板中
		for (int i = 0; i < buttonFriendsArr.length; i++) {
			buttonFriendsArr[i] = new JButton();
			buttonFriendsArr[i].setText(
					Context.friendslist.get(i).getUserNick() + "(" + Context.friendslist.get(i).getUserId() + ")");
			 this.buttonFriendsArr[i].setFont(new Font("楷体", Font.PLAIN, 15));
			this.buttonFriendsArr[i].setSize(290, 20);
			 this.buttonFriendsArr[i].setBackground(new Color(214, 219, 223));
			 this.buttonFriendsArr[i].setFocusPainted(false);
			this.buttonFriendsArr[i].setHorizontalAlignment(10);
			panelFriends.add(buttonFriendsArr[i]);

		}

		this.add(scrollPaneFriends);
		// 将好友面板添加到滚动条中
		this.scrollPaneFriends.setViewportView(panelFriends);
		this.scrollPaneFriends.setBounds(580, 60, 200, 333);
		this.scrollPaneFriends.setBorder(null);
		this.scrollPaneFriends.setBackground(Color.red);
		// 垂直滚动条需要时显示（面板的内容超出范围时显示滚动条）
		this.scrollPaneFriends.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		// 水平滚动条永不显示
		this.scrollPaneFriends.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);


	}

	private void initInformation() {
		PropertiesUtil propertiesUtil = new PropertiesUtil();
		IQunChatDao chatDao = propertiesUtil.getIQunChatDaoImp();
		ArrayList arrayListChat = new ArrayList();
		try {
			arrayListChat = chatDao.getChat(getChatQQId());// 参数是QQ群号
			String seedAndtime = "";
			for (int i = 0; i < arrayListChat.size(); i++) {
				seedAndtime += arrayListChat.get(i).toString();
			}
			textAreaInformation.setText(seedAndtime);// 显示信息
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	double oldx;
	double oldy;

	private void initEvent() {

		this.initEventMove();
		this.initEventWindowClose();
		this.initEventButton();
		this.initTextField();
		this.initEventAddMember();

	}

	// 添加群成员
	private void initEventAddMember() {
		this.buttonAddQunmember.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});

	}

	private void initTextField() {
		this.textAreaInformation.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				super.keyTyped(e);
				e.consume();
			}

		});
		// 回车键发送消息
		this.textAreaContent.addKeyListener(new KeyAdapter() {
			SimpleDateFormat dateFormatinto = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			String strDate = dateFormatinto.format(date);

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				super.keyPressed(e);
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					String seed = textAreaContent.getText().trim();
					if (!("".equals(seed.trim()))) {
						QunChat qunChat = new QunChat();

						qunChat.setDatetime(strDate);
						qunChat.setContent(seed);
						qunChat.setSeedId(Integer.toString(Context.currentUser.getUserId()));// 通过context这个类获得userId
						qunChat.setQunId(getChatQQId());// qunchat类中设置QQ群的id

						PropertiesUtil propertiesUtil = new PropertiesUtil();
						IQunChatDao chatDao = propertiesUtil.getIQunChatDaoImp();

						try {
							chatDao.insertChat(qunChat);
							ArrayList arrayListChat = new ArrayList();
							arrayListChat = chatDao.getChat(getChatQQId());// 参数是QQ群号
							String seedAndtime = "";
							for (int i = 0; i < arrayListChat.size(); i++) {
								seedAndtime += arrayListChat.get(i).toString();
							}
							textAreaInformation.setText(seedAndtime);// 显示信息
							textAreaContent.setText("");// 清除发送了的信息
						} catch (Exception e1) {
							e1.printStackTrace();
						}

					} else {
						JOptionPane.showMessageDialog(null, "输入不能为空,请重新输入");
					}
				}

			}
		});

	}

	private void initEventButton() {

		this.buttonSeed.addActionListener(new ActionListener() {
			SimpleDateFormat dateFormatinto = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			String strDate = dateFormatinto.format(date);

			@Override
			public void actionPerformed(ActionEvent e) {
				String seed = textAreaContent.getText().trim();
				if (!("".equals(seed.trim()))) {
					QunChat qunChat = new QunChat();

					qunChat.setDatetime(strDate);
					qunChat.setContent(seed);
					qunChat.setSeedId(Integer.toString(Context.currentUser.getUserId()));// 通过context这个类获得userId
					qunChat.setQunId(getChatQQId());// qunchat类中设置QQ群的id

					PropertiesUtil propertiesUtil = new PropertiesUtil();
					IQunChatDao chatDao = propertiesUtil.getIQunChatDaoImp();

					try {
						chatDao.insertChat(qunChat);
						ArrayList arrayListChat = new ArrayList();
						arrayListChat = chatDao.getChat(getChatQQId());// 参数是QQ群号
						String seedAndtime = "";
						for (int i = 0; i < arrayListChat.size(); i++) {
							seedAndtime += arrayListChat.get(i).toString();
						}
						textAreaInformation.setText(seedAndtime);// 显示信息
						textAreaContent.setText("");// 清除发送了的信息
					} catch (Exception e1) {
						e1.printStackTrace();
					}

				} else {
					JOptionPane.showMessageDialog(null, "输入不能为空,请重新输入");
				}
			}
		});

		this.buttonClose.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				QunChatFrame.this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				dispose();

			}
		});
		this.close.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		this.small.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				QunChatFrame.this.setExtendedState(ICONIFIED);

			}
		});

	}

	private void initEventWindowClose() {

		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);
				int result = JOptionPane.showConfirmDialog(null, "是否关闭窗口");
				if (result == JOptionPane.YES_OPTION) {
					setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				} else {
					setDefaultCloseOperation(HIDE_ON_CLOSE);
				}

			}
		});

	}

	private void initEventMove() {
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				oldx = e.getX();
				oldy = e.getY();
			}
		});

		this.addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {

				double x = QunChatFrame.this.getLocation().getX() + (e.getX() - oldx);
				double y = QunChatFrame.this.getLocation().getY() + (e.getY() - oldy);
				QunChatFrame.this.setLocation((int) x, (int) y);

			}

			@Override
			public void mouseMoved(MouseEvent e) {

			}
		});

	}

	private void initimg() {

		ImageIcon iconQunname = new ImageIcon(getClass().getResource("/img/qunname.png"));
		this.labelimg.setIcon(iconQunname);
		this.add(labelimg);
		this.labelimg.setBounds(0, 0, 200, 30);

		ImageIcon iconJiLu = new ImageIcon(getClass().getResource("/img/qunliaotianxiaoxijilu.png"));
		this.labelJiLu.setIcon(iconJiLu);
		this.add(labelJiLu);
		this.labelJiLu.setBounds(0, 415, 500, 30);

	}

	private void initLabel() {
		this.add(label1);
		this.label1.setBounds(10, 30, 40, 30);

		this.add(label2);
		this.label2.setBounds(50, 30, 40, 30);

		this.add(label3);
		this.label3.setBounds(90, 30, 40, 30);

		this.add(label4);
		this.label4.setBounds(130, 30, 40, 30);

		this.add(label5);
		this.label5.setBounds(170, 30, 40, 30);

		this.add(label6);
		this.label6.setBounds(210, 30, 40, 30);

		this.add(label7);
		this.label7.setBounds(700, 30, 40, 30);

		this.add(jScrollPaneInformation);
		this.jScrollPaneInformation.setBounds(10, 60, 555, 333);
		this.jScrollPaneInformation.setViewportView(textAreaInformation);

		this.add(jScrollPaneContent);
		this.jScrollPaneContent.setBounds(10, 450, 555, 110);
		this.jScrollPaneContent.setViewportView(textAreaContent);

		// this.add(jScrollPaneMemberList);
		// this.jScrollPaneMemberList.setBounds(580, 60, 200, 333);
		// this.jScrollPaneMemberList.setViewportView(textAreaMemberList);

		this.add(buttonClose);
		this.buttonClose.setBounds(400, 562, 80, 25);

		this.add(buttonSeed);
		this.buttonSeed.setBounds(485, 562, 80, 25);

		this.add(close);
		this.close.setBounds(770, 0, 30, 30);
		this.close.setMargin(new Insets(0, 0, 0, 0));
		this.close.setBorder(null);

		this.add(small);
		this.small.setBounds(739, 0, 30, 30);
		this.small.setMargin(new Insets(0, 0, 0, 0));
		this.small.setBorder(null);

		this.add(buttonAddQunmember);
		this.buttonAddQunmember.setBorder(null);
		this.buttonAddQunmember.setFocusPainted(false);
		this.buttonAddQunmember.setBounds(485, 420, 80, 25);
		this.buttonAddQunmember.setMargin(new Insets(0, 0, 0, 0));
		this.buttonAddQunmember.setBackground(new Color(9, 163, 220));
		this.buttonAddQunmember.setForeground(new Color(255, 255, 255));

	}

	public String getChatQQId() {
		return chatQQId;
	}

	public void setChatQQId(String chatId) {
		this.chatQQId = chatId;
	}

	public static void main(String[] args) {
		QunChatFrame chatFrame = new QunChatFrame("2");
	}

}
