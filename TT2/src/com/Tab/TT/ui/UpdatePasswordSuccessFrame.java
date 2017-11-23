package com.Tab.TT.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.Tab.TT.Entity.User;
import com.Tab.TT.dao.IUserDao;
import com.Tab.TT.framework.context.Context;
import com.Tab.TT.framework.properties.util.PropertiesUtil;

/**
 * 发送验证消息
 * 
 * @author libin
 *
 */
public class UpdatePasswordSuccessFrame extends JFrame {

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
	private JLabel labelTitle = new JLabel("§修改密码");
	// 关闭按钮
	private JButton buttonCloseWindow = new JButton("×");
	// 最小化按钮
	private JButton buttonMinWindow = new JButton("▁");

	// center板块
	private JButton buttonSure = new JButton("重新登录");
	private JLabel labelHint1=new JLabel("恭喜您，密码修改成功！您需要重新登录！");
	private JLabel labelHint2=new JLabel("点击重新登录后将下线。");
	
	// south板块

	public UpdatePasswordSuccessFrame() {

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
		ui.add(panelVerification, BorderLayout.CENTER);

		this.north();
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
		panelVerification.setBackground(new Color(247, 242, 249));
		
		panelVerification.add(labelHint1);
		this.labelHint1.setBounds(100, 50, 300, 50);
		this.labelHint1.setFont(new Font("黑体", 0, 15));
		this.labelHint1.setForeground(new Color(198, 174, 210));
		
		panelVerification.add(labelHint2);
		this.labelHint2.setBounds(100, 80, 200, 50);
		this.labelHint2.setFont(new Font("黑体", 0, 16));
		this.labelHint2.setForeground(new Color(198, 174, 210));
		
		panelVerification.add(buttonSure);
		this.buttonSure.setBounds(170, 200, 100, 30);
		this.buttonSure.setFont(new Font("黑体", 0, 12));
		this.buttonSure.setBackground(new Color(241, 231, 244));
		

		

		this.buttonSure.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				System.exit(-1);
				
				new LoginFrame();
			}
		});

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
				UpdatePasswordSuccessFrame.this.setExtendedState(ICONIFIED);

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
				UpdatePasswordSuccessFrame.this.setLocation(
						(int) (UpdatePasswordSuccessFrame.this.getLocation().getX() + (e.getX() - oldX)),
						(int) (UpdatePasswordSuccessFrame.this.getLocation().getY() + (e.getY() - oldY)));
			}

			@Override
			public void mouseMoved(MouseEvent e) {

			}
		});

	}

	public static void main(String[] args) {
		new UpdatePasswordSuccessFrame();

	}

}
