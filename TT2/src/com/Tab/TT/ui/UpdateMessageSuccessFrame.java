package com.Tab.TT.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Properties;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.Tab.TT.Entity.User;
import com.Tab.TT.dao.IUserDao;
import com.Tab.TT.dao.imp.UserDao;
import com.Tab.TT.framework.context.Context;
import com.Tab.TT.framework.properties.util.PropertiesUtil;

/**
 * 发送验证消息
 * 
 * @author libin
 *
 */
public class UpdateMessageSuccessFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 4大板块 
	// 导航
	private JPanel panelLogo = new JPanel();
	// 好友信息
	private JPanel panelFriendMessage = new JPanel();
	// 验证信息
	private JPanel panelVerification = new JPanel();
	// 按钮
	private JPanel panelButton = new JPanel();

	// north板块
	// 声明窗口x轴和y轴
	private int oldX;
	private int oldY;
	// 标题
	private JLabel labelTitle = new JLabel("§编辑资料");
	// 关闭按钮
	private JButton buttonCloseWindow = new JButton("×");
	// 最小化按钮
	private JButton buttonMinWindow = new JButton("▁");

	// west板块+
	// center板块
	private JLabel labelQQ = new JLabel("账   号：");
	String qq = String.valueOf(Context.currentUser.getUserId());
	private JLabel id = new JLabel(qq);
	// private JLabel id = new JLabel();

	private JLabel labelNick = new JLabel("昵   称：");
	private JLabel message = new JLabel("请重新刷新界面更新您的信息！");
	
	String name = String.valueOf(Context.currentUser.getUserNick());
	private JLabel labelName = new JLabel(name);
	// private JLabel labelName = new JLabel();

	// south板块
	private JButton buttonComplete = new JButton("完成");

	public UpdateMessageSuccessFrame() {

		this.setSize(455, 350);
		this.setLocation(568, 255);
		this.setUndecorated(true);// 隐藏窗口标题

		this.initUI();

		this.setVisible(true);
	}

	private void initUI() {
		Container ui = getContentPane();
		ui.setLayout(new BorderLayout());
		ui.add(panelLogo, BorderLayout.NORTH);
		ui.add(panelButton, BorderLayout.SOUTH);
		ui.add(panelFriendMessage, BorderLayout.WEST);
		ui.add(panelVerification, BorderLayout.CENTER);

		this.north();
		this.west();
		this.center();
		this.south();

	}

	private void south() {
		this.panelButton.setLayout(null);
		panelButton.setPreferredSize(new Dimension(458, 33));
		panelButton.setBackground(new Color(241, 231, 244));

	}

	private void center() {
		this.panelVerification.setLayout(null);
		// 账号
		panelVerification.add(id);
		this.id.setBounds(10, 20, 160, 25);
		this.id.setFont(new Font("黑体", 1, 12));

		// 昵称
		panelVerification.add(labelName);
		this.labelName.setBounds(10, 45, 160, 25);
		this.labelName.setFont(new Font("黑体", 1, 12));
		
		panelVerification.add(message);
		this.message.setBounds(10, 70, 250, 25);
		this.message.setFont(new Font("楷体", 0, 16));

		panelVerification.add(buttonComplete);
		this.buttonComplete.setBounds(80, 170, 70, 22);
		this.buttonComplete.setFont(new Font("黑体", 0, 12));
		this.buttonComplete.setBackground(new Color(241, 231, 244));

		this.buttonComplete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				dispose();

			}
		});

	}

	private void west() {
		this.panelFriendMessage.setLayout(null);
		panelFriendMessage.setPreferredSize(new Dimension(130, 290));
		panelFriendMessage.setBackground(new Color(241, 231, 244));

		// 账号
		panelFriendMessage.add(labelQQ);
		this.labelQQ.setBounds(50, 20, 100, 25);
		this.labelQQ.setFont(new Font("楷体", 0, 16));

		// 昵称
		panelFriendMessage.add(labelNick);
		this.labelNick.setBounds(50, 45, 100, 25);
		this.labelNick.setFont(new Font("楷体", 0, 16));
		
		

	}

	private void north() {
		this.panelLogo.setLayout(null);

		panelLogo.setPreferredSize(new Dimension(458, 33));
		panelLogo.setBackground(new Color(179, 221, 235));
		// 标题
		panelLogo.add(labelTitle);
		this.labelTitle.setBounds(10, 0, 200, 30);
		this.labelTitle.setFont(new java.awt.Font("黑体", 0, 14));
		this.labelTitle.setForeground(Color.white);
		// 关闭窗口按钮
		panelLogo.add(buttonCloseWindow);
		this.buttonCloseWindow.setBounds(430, 0, 30, 30);
		this.buttonCloseWindow.setMargin(new Insets(10, 0, 0, 0));
		this.buttonCloseWindow.setBorder(null);
		this.buttonCloseWindow.setBackground(new Color(179, 221, 235));
		this.buttonCloseWindow.setFont(new java.awt.Font("黑体", 1, 12));
		this.buttonCloseWindow.setForeground(Color.white);
		// 最小化窗口按钮
		panelLogo.add(buttonMinWindow);
		this.buttonMinWindow.setBounds(400, 0, 30, 30);
		this.buttonMinWindow.setMargin(new Insets(10, 0, 0, 0));
		this.buttonMinWindow.setBorder(null);
		this.buttonMinWindow.setBackground(new Color(179, 221, 235));
		this.buttonMinWindow.setFont(new java.awt.Font("黑体", 1, 12));
		this.buttonMinWindow.setForeground(Color.white);

		// 窗口最小化事件
		this.buttonMinWindow.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				UpdateMessageSuccessFrame.this.setExtendedState(ICONIFIED);

			}
		});

		// 关闭窗口事件
		this.buttonCloseWindow.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();

			}
		});
		// 拖动窗口
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				oldX = e.getX();
				oldY = e.getY();
			}
		});
		this.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				UpdateMessageSuccessFrame.this.setLocation(
						(int) (UpdateMessageSuccessFrame.this.getLocation().getX() + (e.getX() - oldX)),
						(int) (UpdateMessageSuccessFrame.this.getLocation().getY() + (e.getY() - oldY)));
			}

			@Override
			public void mouseMoved(MouseEvent e) {

			}
		});

	}

	public static void main(String[] args) {
		new UpdateMessageSuccessFrame();

	}

}
