package com.Tab.TT.ui;
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import com.Tab.TT.Entity.User;
import com.Tab.TT.framework.exception.SystemException;
import com.Tab.TT.framework.properties.util.PropertiesUtil;
import com.Tab.TT.dao.IUserDao;

/**
 * 注册界面
 * @author 马贵鑫
 *
 */
public class RegisterFrame extends JFrame{
	private JLabel QQRegister = new JLabel("账号注册");
	private JLabel line = new JLabel("----------------------------------------------------------------------------------------------------------------------------------------");
	
	private JLabel nick = new JLabel("昵称");
	private JLabel password = new JLabel("密码");
	
	private JLabel passwordIs = new JLabel("确认密码");
	private JLabel gender = new JLabel("性别");
	
	private JLabel telephone = new JLabel("手机号码");
	
	private JTextField textFieldNick = new JTextField();
	private JPasswordField passwordFieldPassword = new JPasswordField();
	private JPasswordField passwordFieldPasswordIs = new JPasswordField();
	private JTextField textFieldTelephone = new JTextField();
	
	private JRadioButton genderFemale = new JRadioButton("女"); 
	private JRadioButton genderMale = new JRadioButton("男"); 
	private JRadioButton openQQzone = new JRadioButton("是否开通TT空间"); 
	private int genderValue = 1;
	private int QQzoneValue = 1;
	
	private JLabel nickShow = new JLabel("输入昵称");
	private JLabel passwordShow = new JLabel("输入密码");
	private JLabel passwordIsShow = new JLabel("确认密码");
	
	private int x  ;
	private int y  ;
	
	private JButton close = new JButton("X");
	private JButton small = new JButton("—");
	
	private JButton register = new JButton("立即注册");
	public RegisterFrame(){
		this.setTitle("QQ用户注册");
		this.setSize(600, 500);
		this.setUndecorated(true);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setLayout(null);
		this.getContentPane().setBackground(new Color(255, 255, 255));
		
		ui();
		event();
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	
	private void event() {
		move();
		textFieldNickClick();
		passwordFieldPasswordClick();
		passwordFieldPasswordIsClick();
		closeClick();
		smallClick();
		registerClick();
		genderValue();
		QQzoneValue();
	}


	


	


	


	private void QQzoneValue() {
		this.openQQzone.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				RegisterFrame.this.QQzoneValue++;
				
			}
		});
		
	}


	private void genderValue() {
		this.genderFemale.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				RegisterFrame.this.genderValue = 0;
			}
		});
		
		this.genderMale.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				RegisterFrame.this.genderValue = 1;
				
			}
		});
		
	}


	private void registerClick() {
		this.register.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if("".equals(textFieldNick.getText().trim())){
					JOptionPane.showMessageDialog(null, "昵称不能为空！");
				}else if(passwordFieldPassword.getText().length()<6){
					JOptionPane.showMessageDialog(null, "密码不能少于6位！");
				}else if(!passwordFieldPassword.getText().equals(passwordFieldPasswordIs.getText())){
					JOptionPane.showMessageDialog(null, "确认密码有误！");
				}else{
					PropertiesUtil propertiesUtil = new PropertiesUtil();
					IUserDao userDao = propertiesUtil.getUserDaoImp();
					User user = new User();
					user.setUserNick(textFieldNick.getText());
					user.setPassword(passwordFieldPassword.getText());
					user.setGender(genderValue);
					user.setTelephone(textFieldTelephone.getText());
					if(RegisterFrame.this.QQzoneValue%2==1){
						user.setOpenQQzone(1);
					}else{
						user.setOpenQQzone(0);
					}
					try{
						JOptionPane.showMessageDialog(null, "注册成功！您的TT号码为"+userDao.insert(user));
						dispose();
					}catch(SystemException ex){
						
						JOptionPane.showMessageDialog(null, ex.getMessage());
					}
				}
			}
		});
		
	}


	private void ui() {
		head();
		body();
		
	}
	
	private void smallClick() {
		this.small.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				RegisterFrame.this.setExtendedState(ICONIFIED);
				
			}
		});
		
		this.small.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseExited(MouseEvent e) {
				RegisterFrame.this.small.setBackground(new Color(255, 255, 255));
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				RegisterFrame.this.small.setBackground(new Color(229, 229, 229));
				
			}
			
		
		});
		
	}
	
	private void closeClick() {
		this.close.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				
			}
		});
		
		this.close.addMouseListener(new MouseAdapter() {
		
			@Override
			public void mouseExited(MouseEvent e) {
				RegisterFrame.this.close.setBackground(new Color(255, 255, 255));
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				RegisterFrame.this.close.setBackground(new Color(251, 97, 85));
				
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
				RegisterFrame.this.setLocation((int)(RegisterFrame.this.getLocation().getX()+
						(e.getX()-x)), (int)(RegisterFrame.this.getLocation().getY()+
						(e.getY()-y)));
				
			}
		});
		
	}
	
	private void passwordFieldPasswordIsClick() {
		this.passwordFieldPasswordIs.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				if(!passwordFieldPasswordIs.getText().equals(passwordFieldPassword.getText())){
					passwordIsShow.setText("密码不相同");
					passwordIsShow.setForeground(new Color(255, 0, 0));
				}else if(!"".equals(passwordFieldPasswordIs.getText())){
					passwordIsShow.setText("输入正确");
					passwordIsShow.setForeground(new Color(0, 225, 0));
				}
				
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				passwordIsShow.setText("");
				passwordIsShow.setForeground(new Color(0, 0, 0));
			}
		});
		
	}
	
	private void passwordFieldPasswordClick() {
		this.passwordFieldPassword.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				if(passwordFieldPassword.getText().equals("")){
					passwordShow.setText("密码不可以为空");
					passwordShow.setForeground(new Color(255, 0, 0));
				}else if(passwordFieldPassword.getText().length()<6){
					passwordShow.setText("密码少于6位");
					passwordShow.setForeground(new Color(255, 0, 0));
				}else{
					passwordShow.setText("输入正确");
					passwordShow.setForeground(new Color(0, 225, 0));
				}
				
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				passwordShow.setText("");
				passwordShow.setForeground(new Color(0, 0, 0));
			}
		});
		
	}
	
	private void textFieldNickClick() {
		this.textFieldNick.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				if(textFieldNick.getText().equals("")){
					nickShow.setText("昵称不可以为空");
					nickShow.setForeground(new Color(255, 0, 0));
				}else{
					nickShow.setText("输入正确");
					nickShow.setForeground(new Color(0, 255, 0));
				}
				
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				nickShow.setText("");
				nickShow.setForeground(new Color(0, 0, 0));
			}
		});
		
	}
	
	private void body() {
		this.add(nick);
		this.nick.setBounds(140, 100, 60, 20);
		this.add(textFieldNick);
		this.textFieldNick.setBounds(200, 100, 240, 20);
		this.add(nickShow);
		this.nickShow.setBounds(440, 100, 100, 20);
		
		this.add(password);
		this.password.setBounds(140, 150, 60, 20);
		this.add(passwordFieldPassword);
		this.passwordFieldPassword.setBounds(200, 150, 240, 20);
		this.add(passwordShow);
		this.passwordShow.setBounds(440, 150, 100, 20);
		
		this.add(passwordIs);
		this.passwordIs.setBounds(140, 200, 60, 20);
		this.add(passwordFieldPasswordIs);
		this.passwordFieldPasswordIs.setBounds(200, 200, 240, 20);
		this.add(passwordIsShow);
		this.passwordIsShow.setBounds(440, 200, 100, 20);
		
		this.add(gender);
		this.gender.setBounds(140, 250, 60, 20);
		this.add(genderMale);
		this.genderMale.setBounds(200, 250, 50, 20);
		this.genderMale.setFocusPainted(false);
		this.add(genderFemale);
		this.genderFemale.setBounds(250, 250, 50, 20);
		this.genderFemale.setFocusPainted(false);
		this.genderMale.setSelected(true);
		ButtonGroup gender = new ButtonGroup();
		gender.add(genderFemale);
		gender.add(genderMale);
		
		this.add(openQQzone);
		this.openQQzone.setBounds(300, 250, 150, 20);
		this.openQQzone.setSelected(true);
		
		this.add(telephone);
		this.telephone.setBounds(140, 300, 60, 20);
		this.add(textFieldTelephone);
		this.textFieldTelephone.setBounds(200, 300, 240, 20);
		
		this.add(register);
		this.register.setBounds(230, 360, 140, 50);
		this.register.setFont(new Font("华文楷体", Font.PLAIN, 15));
		this.register.setBackground(new Color(9, 163, 220));
		this.register.setForeground(new Color(255, 255, 255));
		this.register.setBorder(null);
		this.register.setFocusPainted(false);
		
		this.add(close);
		this.close.setBounds(570, 0, 30, 30);
		this.close.setMargin(new Insets(0, 0, 0, 0));
		this.close.setBackground(new Color(255, 255, 255));
		this.close.setBorder(null);
		this.close.setFocusPainted(false);
		
		this.add(small);
		this.small.setBounds(540, 0, 30, 30);
		this.small.setMargin(new Insets(0, 0, 0, 0));
		this.small.setBackground(new Color(255, 255, 255));
		this.small.setBorder(null);
		this.small.setFocusPainted(false);
		
	}
	
	private void head() {
		this.add(QQRegister);
		this.QQRegister.setBounds(10, 20, 60, 20);
		this.add(line);
		this.line.setBounds(10, 40, 580, 10);
		
	}
	
	public static void main(String[] args) {
		RegisterFrame reg = new RegisterFrame();

	}
	
	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public JButton getClose() {
		return close;
	}
	
	public void setClose(JButton close) {
		this.close = close;
	}
	
	public JButton getSmall() {
		return small;
	}
	
	public void setSmall(JButton small) {
		this.small = small;
	}



}
