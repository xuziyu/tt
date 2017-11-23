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
public class UpdateMessageFrame extends JFrame {

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

	private JLabel labelNick = new JLabel("昵   称：");
	private JTextField fieldNick = new JTextField(Context.currentUser.getUserNick());

	// 性别
	private JLabel jLabelGender = new JLabel("性   别：");
	// 性别-男
	private JRadioButton buttonMan = new JRadioButton();
	private JLabel jLabelMan = new JLabel("男");
	// 性别-女
	private JRadioButton buttonWomen = new JRadioButton();
	private JLabel jLabelWomen = new JLabel("女");

	private JLabel labelMessage = new JLabel("个性签名：");
	private JTextArea areaMessage = new JTextArea();

	// 生日：公历，年，月，日
	private JLabel jLabelBirth = new JLabel("生   日：");
	private JComboBox boxCalendar = new JComboBox();
	private JComboBox boxYear = new JComboBox();
	private JComboBox boxMonth = new JComboBox();
	private JComboBox boxDay = new JComboBox();
	// 所在地：国，省，市
	private JLabel jLabelPlace = new JLabel("所 在 地：");
	private JComboBox boxCountry = new JComboBox();
	private JComboBox boxProvince = new JComboBox();
	private JComboBox boxCity = new JComboBox();

	// south板块
	private JButton buttonSubmit = new JButton("保存");
	private JButton buttonCancel = new JButton("取消");

	public UpdateMessageFrame() {

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
				// 通过user对象+UsrDao.update(User user) 修改sql数据
				User user = new User();
				user.setUserId(Context.currentUser.getUserId());
				user.setUserNick(fieldNick.getText());
				PropertiesUtil p = new PropertiesUtil();
				IUserDao userDao = p.getUserDaoImp();
				userDao.updateMessage(user);
				// 把修改的数据传到Context
				Context.currentUser.setUserNick(fieldNick.getText());

				dispose();
				new UpdateMessageSuccessFrame();
				
				
				

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
		// 账号
		panelVerification.add(id);
		this.id.setBounds(10, 20, 160, 25);
		this.id.setFont(new Font("黑体", 1, 12));

		// 昵称
		panelVerification.add(fieldNick);
		this.fieldNick.setBounds(10, 45, 250, 25);
		this.fieldNick.setFont(new Font("黑体", 1, 12));

		// 性别
		ButtonGroup gender = new ButtonGroup();
		gender.add(buttonMan);
		gender.add(buttonWomen);

		panelVerification.add(buttonMan);
		this.buttonMan.setBounds(10, 73, 20, 20);
		panelVerification.add(jLabelMan);
		this.jLabelMan.setBounds(30, 55, 25, 60);
		this.jLabelMan.setFont(new Font("黑体", 0, 12));

		panelVerification.add(buttonWomen);
		this.buttonWomen.setBounds(70, 73, 20, 20);
		panelVerification.add(jLabelWomen);
		this.jLabelWomen.setBounds(90, 55, 25, 60);
		this.jLabelWomen.setFont(new Font("黑体", 0, 12));

		// 个性签名
		panelVerification.add(areaMessage);
		this.areaMessage.setBounds(10, 95, 250, 60);

		// 生日
		// 公历下拉框
		panelVerification.add(boxCalendar);
		this.boxCalendar.setBounds(10, 160, 50, 25);
		this.boxCalendar.addItem("公历");
		this.boxCalendar.addItem("农历");
		this.boxCalendar.setFont(new Font("华文楷体", 0, 12));
		this.boxCalendar.setForeground(new Color(8, 163, 217));
		// 年下拉框
		panelVerification.add(boxYear);
		this.boxYear.setBounds(65, 160, 80, 25);
		this.boxYear.addItem("年");
		this.boxYear.addItem("2000");
		this.boxYear.setFont(new Font("华文楷体", 0, 12));
		this.boxYear.setForeground(new Color(8, 163, 217));
		// 月下拉框
		panelVerification.add(boxMonth);
		this.boxMonth.setBounds(150, 160, 55, 25);
		this.boxMonth.addItem("月");
		this.boxMonth.addItem("10");
		this.boxMonth.setFont(new Font("华文楷体", 0, 12));
		this.boxMonth.setForeground(new Color(8, 163, 217));
		// 日下拉框
		panelVerification.add(boxDay);
		this.boxDay.setBounds(210, 160, 50, 25);
		this.boxDay.addItem("日");
		this.boxDay.addItem("10");
		this.boxDay.setFont(new Font("华文楷体", 0, 12));
		this.boxDay.setForeground(new Color(8, 163, 217));

		// 所在地
		// 国
		panelVerification.add(boxCountry);
		this.boxCountry.setBounds(10, 190, 80, 25);
		this.boxCountry.addItem("中国");
		this.boxCountry.setFont(new Font("华文楷体", 0, 12));
		this.boxCountry.setForeground(new Color(8, 163, 217));
		// 省
		panelVerification.add(boxProvince);
		this.boxProvince.setBounds(95, 190, 80, 25);
		this.boxProvince.addItem("福建");
		this.boxProvince.setFont(new Font("华文楷体", 0, 12));
		this.boxProvince.setForeground(new Color(8, 163, 217));
		// 市
		panelVerification.add(boxCity);
		this.boxCity.setBounds(180, 190, 80, 25);
		this.boxCity.addItem("厦门");
		this.boxCity.setFont(new Font("华文楷体", 0, 12));
		this.boxCity.setForeground(new Color(8, 163, 217));

	}

	private void west() {
		this.panelFriendMessage.setLayout(null);
		panelFriendMessage.setPreferredSize(new Dimension(130, 290));
		panelFriendMessage.setBackground(new Color(247, 242, 249));

		// 账号
		panelFriendMessage.add(labelQQ);
		this.labelQQ.setBounds(50, 20, 100, 25);
		this.labelQQ.setFont(new Font("楷体", 0, 16));

		// 昵称
		panelFriendMessage.add(labelNick);
		this.labelNick.setBounds(50, 45, 100, 25);
		this.labelNick.setFont(new Font("楷体", 0, 16));

		// 性别
		panelFriendMessage.add(jLabelGender);
		this.jLabelGender.setBounds(50, 70, 100, 25);
		this.jLabelGender.setFont(new Font("楷体", 0, 16));

		// 个性签名
		panelFriendMessage.add(labelMessage);
		this.labelMessage.setBounds(50, 95, 100, 25);
		this.labelMessage.setFont(new Font("楷体", 0, 16));

		// 生日
		panelFriendMessage.add(jLabelBirth);
		this.jLabelBirth.setBounds(50, 160, 100, 25);
		this.jLabelBirth.setFont(new Font("楷体", 0, 16));

		// 所在地
		panelFriendMessage.add(jLabelPlace);
		this.jLabelPlace.setBounds(50, 190, 100, 25);
		this.jLabelPlace.setFont(new Font("楷体", 0, 16));

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
				UpdateMessageFrame.this.setExtendedState(ICONIFIED);

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
				UpdateMessageFrame.this.setLocation(
						(int) (UpdateMessageFrame.this.getLocation().getX() + (e.getX() - oldX)),
						(int) (UpdateMessageFrame.this.getLocation().getY() + (e.getY() - oldY)));
			}

			@Override
			public void mouseMoved(MouseEvent e) {

			}
		});

	}

	public static void main(String[] args) {
		new UpdateMessageFrame();

	}

}
