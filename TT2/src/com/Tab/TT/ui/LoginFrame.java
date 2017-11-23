package com.Tab.TT.ui;

import java.awt.Color;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.Tab.TT.Entity.User;
import com.Tab.TT.dao.imp.FriendsDao;
import com.Tab.TT.dao.imp.QunDao;
import com.Tab.TT.dao.imp.UserDao;
import com.Tab.TT.framework.context.Context;
import com.Tab.TT.framework.properties.util.PropertiesUtil;
import com.Tab.TT.dao.IUserDao;




/**
 * TT登录界面
 * @author 马贵鑫
 * @version 1.0
 */
public class LoginFrame extends JFrame{
	private JButton loginbutton = new JButton("登录");//登录按钮
	
	private JTextField userId = new JTextField("TT号码");//TT账号输入框
	private JPasswordField userPassword = new JPasswordField("密码");//密码输入框
	
	private JLabel register = new JLabel("注册账号");//注册账号
	private JLabel findPassword = new JLabel("找回密码");//找回密码
	
	private JRadioButton rememberPassword = new JRadioButton("记住密码");//记住密码按钮
	private JRadioButton autoLogin = new JRadioButton("自动登录");//自动登录按钮
	
	private JLabel logoimg = new JLabel();
	
	private JButton close = new JButton("X");
	private JButton small = new JButton("—");
	private JButton install = new JButton("▼");
	
	private int x  ;
	private int y  ;
	
	public LoginFrame(){
		this.setTitle("TT登录界面");
		this.setSize(430, 330);
		this.setUndecorated(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);
		this.setLayout(null);
		
		ui();
		
		event();
		
	}
	
	private void ui() {
		body();
		head();
		
	}

	private void event() {
		userIdClick();
		userPasswordClick();
		loginbuttonClick();
		closeClick();
		smallClick();
		move();
		register();
		findPassword();
		userId();
		
		enterKey();
		
		
	}

	
		
	

	private void findPassword() {
		this.findPassword.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseEntered(MouseEvent e) {
				LoginFrame.this.findPassword.setForeground(new Color(102, 225, 248));
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				LoginFrame.this.findPassword.setForeground(new Color(18, 144, 195));
				
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				new FindPasswordFrame();
				
			}
		});
	}

	private void enterKey() {
		this.userPassword.addKeyListener(new KeyAdapter() {			
			@Override
			public void keyPressed(KeyEvent e) {
				
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					boolean isRight = Pattern.matches("^[1-9]\\d{4,10}$", userId.getText());
					if("".equals(userId.getText().trim())){
						JOptionPane.showMessageDialog(null, "未输入账号");
					}else if("".equals(userPassword.getText())||"密码".equals(userPassword.getText())){
						JOptionPane.showMessageDialog(null, "未输入密码");
					}else if(isRight!=true){
						JOptionPane.showMessageDialog(null, "TT号输入不正确");
					}else{
						
						PropertiesUtil propertiesUtil = new PropertiesUtil();
						IUserDao userDao = propertiesUtil.getUserDaoImp();
						if (userDao.isRight(Integer.parseInt(userId.getText()), userPassword.getText())){
							dispose();
							
							User user = new User();
							user.setUserId((Integer.parseInt(userId.getText())));
//							user.setUserNick(userDao.load(Integer.parseInt(userId.getText())));
							userDao.getEveryThing(user);
							Context.currentUser = user;

							System.out.println("用户id"+Context.currentUser.getUserId());

							MainFrame mainFrame = new MainFrame();
						}else{
							JOptionPane.showMessageDialog(null, "密码不正确");
						}
						
					}
				}
				
			}						
		});
								
	}

	private void userId() {
		this.userId.addKeyListener(new KeyAdapter() {
		
			
			@Override
			public void keyTyped(KeyEvent e) {
				int keyChar = e.getKeyChar();                 
                if(keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9){  
                      
                }else{  
                    e.consume(); //关键，屏蔽掉非法输入  
                }  
				
			}
		});
		

	
	}			
			
	private void register() {
		this.register.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseEntered(MouseEvent e) {
				LoginFrame.this.register.setForeground(new Color(102, 225, 248));
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				LoginFrame.this.register.setForeground(new Color(18, 144, 195));
				
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				new RegisterFrame();
				
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
				LoginFrame.this.setLocation((int)(LoginFrame.this.getLocation().getX()+
						(e.getX()-x)), (int)(LoginFrame.this.getLocation().getY()+
						(e.getY()-y)));
				
			}
		});
		
	}

	private void smallClick() {
		this.small.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseExited(MouseEvent e) {
				LoginFrame.this.small.setBackground(new Color(0, 169, 236));
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				LoginFrame.this.small.setBackground(new Color(229, 229, 229));
				
			}
		});
		
		this.small.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				LoginFrame.this.setExtendedState(ICONIFIED);
				
			}
		});
		
	}

	private void closeClick() {
		this.close.addMouseListener(new MouseAdapter() {
	
			@Override
			public void mouseExited(MouseEvent e) {
				LoginFrame.this.close.setBackground(new Color(0, 169, 236));
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				LoginFrame.this.close.setBackground(new Color(251, 97, 85));
				
			}
		});
		
		this.close.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(-1);
				
			}
		});
		
	}
	
	private void loginbuttonClick() {
		
		
		this.loginbutton.addActionListener(new ActionListener() {
			 
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean isRight = Pattern.matches("^[1-9]\\d{4,10}$", userId.getText());
				if("".equals(userId.getText().trim())){
					JOptionPane.showMessageDialog(null, "未输入账号");
				}else if("".equals(userPassword.getText())||"密码".equals(userPassword.getText())){
					JOptionPane.showMessageDialog(null, "未输入密码");
				}else if(isRight!=true){
					JOptionPane.showMessageDialog(null, "TT号输入不正确");
				}else{
					
					PropertiesUtil propertiesUtil = new PropertiesUtil();
					IUserDao userDao = propertiesUtil.getUserDaoImp();
					if (userDao.isRight(Integer.parseInt(userId.getText()), userPassword.getText())){
						dispose();						
						User user = new User();
						//将输入的‘userId’赋给User
						user.setUserId((Integer.parseInt(userId.getText())));
						user.setPassword(userPassword.getText());
						//user获得所以属性
						userDao.getEveryThing(user);
//						user.setUserNick(userDao.load(Integer.parseInt(userId.getText())));
						Context.currentUser = user;						

						System.out.println("用户的地址"+Context.currentUser);

						FriendsDao f = new FriendsDao();
						f.getFriendsMessage(Integer.parseInt(userId.getText()));
						QunDao q = new QunDao();
						q.getQunMessage(Integer.parseInt(userId.getText()));
						MainFrame mainFrame = new MainFrame();
					}else{
						JOptionPane.showMessageDialog(null, "密码不正确");
					}
					
				}
			}

			

			
			
		});
		
	}
	
	private void userPasswordClick() {
		this.userPassword.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				userPassword.setEchoChar('*');
				
			}
		});
		this.userPassword.addFocusListener(new FocusAdapter() {
			
			@Override
			public void focusGained(FocusEvent e) {
				if("密码".equals(LoginFrame.this.userPassword.getText())){
					LoginFrame.this.userPassword.setText("");
				}
					
				
				
			}
		
		});
		
	}
	
	private void userIdClick() {
		this.userId.addFocusListener(new FocusAdapter() {
	
			@Override
			public void focusGained(FocusEvent e) {
				if("TT号码".equals(LoginFrame.this.userId.getText())){
					LoginFrame.this.userId.setText("");
				}
				
			}
		});
		
	}
	
	private void head() {
		ImageIcon logo = new ImageIcon(this.getClass().getResource("/img/logo4.png"));
		logoimg.setIcon(logo);
		this.add(logoimg);
		this.logoimg.setBounds(0,0,logo.getIconWidth(),logo.getIconHeight());
		
	}
	
	private void body() {
		
		this.add(loginbutton);
		this.loginbutton.setBounds(133, 290, 194, 31);
		this.loginbutton.setBackground(new Color(9, 163, 220));
		this.loginbutton.setBorder(null);
		this.loginbutton.setForeground(new Color(255, 255, 255));
		this.loginbutton.setFocusPainted(false);
		
		this.add(userId);
		this.userId.setBounds(133, 195, 194, 31);
		
		this.add(userPassword);
		this.userPassword.setBounds(133, 227, 194, 31);
		this.userPassword.setEchoChar((char)(0));
		
		this.add(register);
		this.register.setBounds(348, 195, 55, 31);
		LoginFrame.this.register.setForeground(new Color(18, 144, 195));
		
		this.add(findPassword);
		this.findPassword.setBounds(348, 227, 55, 31);
		LoginFrame.this.findPassword.setForeground(new Color(18, 144, 195));
		
		this.add(rememberPassword);
		this.rememberPassword.setBounds(133, 260, 100, 20);
		this.rememberPassword.setFocusPainted(false);
		
		this.add(autoLogin);
		this.autoLogin.setBounds(238, 260, 100, 20);
		this.autoLogin.setFocusPainted(false);
		
		this.add(close);
		this.close.setBounds(400, 0, 30, 25);
		this.close.setMargin(new Insets(0, 0, 0, 0));
		this.close.setBackground(new Color(0, 169, 236));
		this.close.setBorder(null);
		this.close.setFocusPainted(false);
		
		this.add(small);
		this.small.setBounds(375, 0, 25, 25);
		this.small.setMargin(new Insets(0, 0, 0, 0));
		this.small.setBackground(new Color(0, 169, 236));
		this.small.setBorder(null);
		this.small.setFocusPainted(false);
		
		this.add(install);
		this.install.setBounds(350, 0, 25, 25);
		this.install.setMargin(new Insets(0, 0, 0, 0));
		this.install.setBackground(new Color(0, 169, 236));
		this.install.setBorder(null);
		this.install.setFocusPainted(false);
	}
	
	public static void main(String[] args) {
		LoginFrame loginFrame = new LoginFrame();

	}

}
