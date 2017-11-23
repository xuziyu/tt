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
public class UpdatePasswordFrame extends JFrame {

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

	// west板块+
	// center板块
	private JLabel labelOldPassword = new JLabel("请输入旧密码：");
	private JTextField fieldOldPassword = new JTextField();
	private JLabel labelOldError = new JLabel("有误！");
	private JLabel labelOldCorrect = new JLabel("正确。");

	private JLabel labelNewPassword = new JLabel("请输入新密码：");
	private JTextField fieldNewPassword = new JTextField();

	private JLabel labelConfirmPassword = new JLabel("请再次输入新密码：");
	private JTextField fieldConfirmPassword = new JTextField();
	private JLabel labelConfirmError = new JLabel("前后不一致！");
	private JLabel labelConfirmCorrect = new JLabel("前后一致。");

	// south板块
	private JButton buttonSubmit = new JButton("确认");
	private JButton buttonCancel = new JButton("取消");

	public UpdatePasswordFrame() {

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
		panelButton.add(buttonSubmit);
		this.buttonSubmit.setBounds(306, 6, 70, 22);
		this.buttonSubmit.setFont(new Font("黑体", 0, 12));
		this.buttonSubmit.setBackground(new Color(241, 231, 244));

		panelButton.add(buttonCancel);
		this.buttonCancel.setBounds(382, 6, 70, 22);
		this.buttonCancel.setFont(new Font("黑体", 0, 12));
		this.buttonCancel.setBackground(new Color(241, 231, 244));

		this.buttonSubmit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (fieldOldPassword.getText().equals(Context.currentUser.getPassword())
						&& (fieldConfirmPassword.getText().equals(fieldNewPassword.getText()))) {

					// 通过user对象+UsrDao.updatePassword(User user) 修改sql数据
					User user = new User();
					user.setUserId(Context.currentUser.getUserId());
					user.setPassword(fieldNewPassword.getText());
					PropertiesUtil p = new PropertiesUtil();
					IUserDao userDao = p.getUserDaoImp();
					userDao.updatePassword(user);
					dispose();
					new UpdatePasswordSuccessFrame();
					// JOptionPane.showMessageDialog(null, "修改成功!");

				} else {
					JOptionPane.showMessageDialog(null, "请输入完整信息!");
				}

			}
		});

		this.buttonCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);

			}
		});
	}

	private void center() {
		this.panelVerification.setLayout(null);
		// 旧密码
		panelVerification.add(fieldOldPassword);
		this.fieldOldPassword.setBounds(10, 20, 200, 25);
		this.fieldOldPassword.setFont(new Font("黑体", 1, 12));

		// 旧密码有误提示
		panelVerification.add(labelOldError);
		this.labelOldError.setBounds(210, 20, 50, 25);
		this.labelOldError.setFont(new Font("黑体", 0, 12));
		this.labelOldError.setForeground(Color.RED);
		this.labelOldError.setVisible(false);

		// 旧密码正确提示
		panelVerification.add(labelOldCorrect);
		this.labelOldCorrect.setBounds(210, 20, 50, 25);
		this.labelOldCorrect.setFont(new Font("黑体", 0, 12));
		this.labelOldCorrect.setForeground(Color.green);
		this.labelOldCorrect.setVisible(false);

		// 新密码
		panelVerification.add(fieldNewPassword);
		this.fieldNewPassword.setBounds(10, 65, 200, 25);
		this.fieldNewPassword.setFont(new Font("黑体", 1, 12));

		// 确认密码
		panelVerification.add(fieldConfirmPassword);
		this.fieldConfirmPassword.setBounds(10, 110, 200, 25);
		this.fieldConfirmPassword.setFont(new Font("黑体", 1, 12));

		// 确认密码有误提示
		panelVerification.add(labelConfirmError);
		this.labelConfirmError.setBounds(210, 110, 100, 25);
		this.labelConfirmError.setFont(new Font("黑体", 0, 12));
		this.labelConfirmError.setForeground(Color.RED);
		this.labelConfirmError.setVisible(false);
		
		// 确认密码正确提示
		panelVerification.add(labelConfirmCorrect);
		this.labelConfirmCorrect.setBounds(210, 110, 100, 25);
		this.labelConfirmCorrect.setFont(new Font("黑体", 0, 12));
		this.labelConfirmCorrect.setForeground(Color.GREEN);
		this.labelConfirmCorrect.setVisible(false);

		// 旧密码事件
		this.fieldOldPassword.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {

				if (fieldOldPassword.getText().equals(Context.currentUser.getPassword())) {
					labelOldError.setVisible(false);
					labelOldCorrect.setVisible(true);
				} else {

//					System.out.println(fieldOldPassword.getText());
//					System.out.println(Context.currentUser.getPassword());
//					System.out.println(Context.currentUser.getUserId());
					labelOldError.setVisible(true);
					labelOldCorrect.setVisible(false);
					
				}
			}

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub

			}
		});
		// 确认新密码事件
		this.fieldConfirmPassword.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {

				if (!fieldConfirmPassword.getText().equals(fieldNewPassword.getText())) {
					labelConfirmError.setVisible(true);
					labelConfirmCorrect.setVisible(false);
				} else {
					labelConfirmError.setVisible(false);
					labelConfirmCorrect.setVisible(true);
				}
			}

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub

			}
		});

	}

	private void west() {
		this.panelFriendMessage.setLayout(null);
		panelFriendMessage.setPreferredSize(new Dimension(180, 290));
		panelFriendMessage.setBackground(new Color(247, 242, 249));

		// 旧密码
		panelFriendMessage.add(labelOldPassword);
		this.labelOldPassword.setBounds(50, 20, 200, 25);
		this.labelOldPassword.setFont(new Font("楷体", 0, 16));

		// 新密码
		panelFriendMessage.add(labelNewPassword);
		this.labelNewPassword.setBounds(50, 65, 200, 25);
		this.labelNewPassword.setFont(new Font("楷体", 0, 16));

		// 确认密码
		panelFriendMessage.add(labelConfirmPassword);
		this.labelConfirmPassword.setBounds(20, 110, 200, 25);
		this.labelConfirmPassword.setFont(new Font("楷体", 0, 16));

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
				UpdatePasswordFrame.this.setExtendedState(ICONIFIED);

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
				UpdatePasswordFrame.this.setLocation(
						(int) (UpdatePasswordFrame.this.getLocation().getX() + (e.getX() - oldX)),
						(int) (UpdatePasswordFrame.this.getLocation().getY() + (e.getY() - oldY)));
			}

			@Override
			public void mouseMoved(MouseEvent e) {

			}
		});

	}

	public static void main(String[] args) {
		new UpdatePasswordFrame();

	}

}
