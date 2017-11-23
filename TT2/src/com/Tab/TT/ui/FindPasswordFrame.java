package com.Tab.TT.ui;

import java.awt.Color;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.Tab.TT.Entity.User;
import com.Tab.TT.dao.imp.UserDao;


public class FindPasswordFrame extends JFrame{
	private JLabel title = new JLabel("找回密码");
	
	private JButton closeTop = new JButton("X");
	private JButton small = new JButton("—");
	
	private JLabel QQnumber = new JLabel("QQ号码");
	private JTextField QQnumberInput = new JTextField();
	
	private JLabel telephone = new JLabel("绑定手机号码");
	private JTextField telephoneInput = new JTextField();
	
	private JLabel password = new JLabel("您的QQ号码是");
	private JTextField passwordFind = new JTextField();
	
	private JButton find = new JButton("找回密码");
	
	private int x;
	private int y;
	
	public FindPasswordFrame(){
		this.setSize(400, 300);
		this.setUndecorated(true);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setLayout(null);
		
		ui();
		event();
	}
	
	private void event() {
		closeTop();
		small();
		move();
		QQnumberInput();
		find();
	}

	private void find() {
		this.find.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				User user = new User();
				UserDao userDao = new UserDao();
				int userId = Integer.parseInt(FindPasswordFrame.this.QQnumberInput.getText());
				user.setUserId(userId);
				String password = userDao.getPassword(userId, FindPasswordFrame.this.telephoneInput.getText());
				if(password==null){
					JOptionPane.showMessageDialog(null, "查询有误，请检查QQ号与绑定手机号码是否正确！");
				}else{
					FindPasswordFrame.this.passwordFind.setText(password);
				}
			}
		});
	}

	private void QQnumberInput() {
		this.QQnumberInput.addKeyListener(new KeyAdapter() {
		
			
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
				FindPasswordFrame.this.setLocation((int)(FindPasswordFrame.this.getLocation().getX()+
						(e.getX()-x)), (int)(FindPasswordFrame.this.getLocation().getY()+
						(e.getY()-y)));
				
			}
		});
	}

	private void small() {
		this.small.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseExited(MouseEvent e) {
				FindPasswordFrame.this.small.setBackground(new Color(238, 238, 238));
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				FindPasswordFrame.this.small.setBackground(new Color(229, 229, 229));
				
			}
		});
		
		this.small.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				FindPasswordFrame.this.setExtendedState(ICONIFIED);
				
			}
		});
		
	}

	private void closeTop() {
		this.closeTop.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				
			}
		});
		
		this.closeTop.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseExited(MouseEvent e) {
				FindPasswordFrame.this.closeTop.setBackground(new Color(238, 238, 238));
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				FindPasswordFrame.this.closeTop.setBackground(new Color(251, 97, 85));
				
			}
		});
		
	}

	private void ui() {
		this.add(find);
		this.find.setBounds(100, 230, 194, 31);
		this.find.setBackground(new Color(9, 163, 220));
		this.find.setBorder(null);
		this.find.setForeground(new Color(255, 255, 255));
		this.find.setFocusPainted(false);
		
		this.add(password);
		this.password.setBounds(40, 170, 90, 30);
		
		this.add(passwordFind);
		this.passwordFind.setBounds(140, 170, 200, 30);
		
		this.add(telephone);
		this.telephone.setBounds(40, 110, 90, 30);
		
		this.add(telephoneInput);
		this.telephoneInput.setBounds(140, 110, 200, 30);
		
		this.add(QQnumber);
		this.QQnumber.setBounds(40, 50, 90, 30);
		
		this.add(QQnumberInput);
		this.QQnumberInput.setBounds(140, 50, 200, 30);
		
		this.add(title);
		this.title.setBounds(10, 0, 100, 30);
		
		this.add(closeTop);
		this.closeTop.setBounds(370, 0, 30, 30);
		this.closeTop.setMargin(new Insets(0, 0, 0, 0));
		this.closeTop.setBackground(new Color(238, 238, 238));
		this.closeTop.setBorder(null);
		this.closeTop.setFocusPainted(false);
		
		this.add(small);
		this.small.setBounds(340, 0, 30, 30);
		this.small.setMargin(new Insets(0, 0, 0, 0));
		this.small.setBackground(new Color(238, 238, 238));
		this.small.setBorder(null);
		this.small.setFocusPainted(false);
	}

	public static void main(String[] args) {
		FindPasswordFrame findPasswordFrame = new FindPasswordFrame();
	}
}
