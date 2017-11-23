package com.Tab.TT.ui;

import java.awt.Color;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.management.JMRuntimeException;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;


/** 
 * 创建群
* @author : 白长昊
* @date 创建时间：2017年8月28日 下午6:29:48 
* @version 1.0 
* @parameter  
* @since  
* @return  
*/

public class FillInInformation extends JFrame {

	     private JLabel gropu=new JLabel();
		 private JLabel groupType=new JLabel("1"+"   选择群类型");
		 private JLabel fillInInformation =new JLabel("2"+"   填写群信息");
		 private JLabel invite=new JLabel("3"+"   邀请群成员");
		 private JLabel type=new JLabel("类型:");
		 private JLabel text1=new JLabel();
		 private JLabel address=new JLabel("群地点:");
		 private JComboBox province=new JComboBox<>();
		 private JComboBox city =new JComboBox<>();
		 private JLabel name=new JLabel("群名称:");
		 private JTextField text3=new JTextField("请输入群名称");
		 private JRadioButton th= new JRadioButton("200人");
		 private JRadioButton fh= new JRadioButton("500人");
		 private JRadioButton os= new JRadioButton("1000人");
		 private JRadioButton ts= new JRadioButton("2000人");
		 private JLabel qScale=new JLabel("群规模");
		 private JLabel verification=new JLabel("加群验证");
		 private JRadioButton any= new JRadioButton("允许任何人");
		 private JRadioButton verify= new JRadioButton("需要身份验证");
		 private JRadioButton noAny= new JRadioButton("不允许任何人");
		    private JButton close = new JButton("X");
			private JButton small = new JButton("—");
		 private int x  ;
	     private int y  ;
		
		 private JButton back=new JButton("上一步");
		 private JButton next=new JButton("下一步");
		 public static  String text;
		 public static  String text2;
		 public static  int max;
		 public   FillInInformation(String type){
				this.setTitle("创建群");
				this.setSize(544, 450);
				this.setUndecorated(true);
				this.setVisible(true);
				this.setLayout(null);
				
				this.setLocationRelativeTo(null);
				move();
				this.setLocationRelativeTo(null);
				this.add(gropu);
				this.gropu.setBounds(5, 0, 100, 40);
				this.add(groupType);
				this.groupType.setBounds(10, 40, 220, 32);
				this.add(fillInInformation);
				this.fillInInformation.setBounds(225, 40, 220, 32);
				this.add(invite);
				this.invite.setBounds(445, 40, 180, 32);
				this.add(this.type);
				this.type.setBounds(97, 70,50,25);
				this.add(text1);
				this.text1.setBounds(135, 70, 290, 25);
				this.add(address);
				this.address.setBounds(83, 100, 50, 25);
				this.add(province);
				 this.province.addItem("福建");
				 this.province.addItem("江西");
				 this.province.setBounds(135,100, 140, 20);
				 this.add(city);
				 this.city.addItem("厦门");
				 this.city.addItem("南昌");
				 this.city.setBounds(285,100, 140, 20);
				this.add(name);
				this.name.setBounds(83, 130, 50, 25);
				this.add(text3);
				this.text3.setBounds(135, 130, 290, 25);
			    this.add(qScale);
			    this.qScale.setBounds(83, 160, 50, 25);
			    this.add(th);
			    this.th.setBounds(130, 160, 70, 20);
			    this.add(fh);
			    this.fh.setBounds(200, 160, 70, 20);
			    this.add(os);
			    this.os.setBounds(270, 160, 70, 20);
			    this.add(ts);
			    this.ts.setBounds(340, 160, 70, 20);
			    this.add(small);
				this.small.setBounds(490, 0, 25, 25);
				this.small.setMargin(new Insets(0, 0, 0, 0));
				this.small.setBackground(new Color(238, 238, 238));
				this.small.setBorder(null);
				this.small.setFocusPainted(false);
				this.add(close);
				this.close.setBounds(512, 0, 30, 25);
				this.close.setMargin(new Insets(0, 0, 0, 0));
				this.close.setBackground(new Color(238, 238, 238));
				this.close.setBorder(null);
				this.close.setFocusPainted(false);
			    ButtonGroup qscale = new ButtonGroup();
			    qscale.add(th);
			    qscale.add(fh);
			    qscale.add(os);
			    qscale.add(ts);
			    this.th.setSelected(true);
			    this.add(verification);
			    this.verification.setBounds(73, 190, 60, 25);
			    this.add(any);
			    this.any.setBounds(130, 190, 90, 20);
			    this.add(verify);
			    this.verify.setBounds(220, 190, 110, 20);
			    this.add(noAny);
			    this.noAny.setBounds(335, 190, 110, 20);
			    ButtonGroup verification = new ButtonGroup();
			    verification.add(any);
			    verification.add(verify);
			    verification.add(noAny);
			    this.any.setSelected(true);
			    this.text1.setText(type);
			    this.gropu.setText("创建"+type+"群");
				this.add(back);
				this.back.setBounds(390, 423, 75, 25);
				this.add(next);
				this.next.setBounds(465, 423, 75, 25);
				
		          this.close.addActionListener(new ActionListener() {
		  			
		  			@Override
		  			public void actionPerformed(ActionEvent e) {
		  				dispose();
		  			}
		  		});
		  		
		  	this.small.addActionListener(new ActionListener() {
		  			
		  			@Override
		  			public void actionPerformed(ActionEvent e) {
		  				FillInInformation.this.setExtendedState(ICONIFIED);
		  				
		  			}
		  		});
		    this.small.addMouseListener(new MouseAdapter() {
	  			
	  			@Override
	  			public void mouseExited(MouseEvent e) {
	  				FillInInformation.this.small.setBackground(new Color(238, 238, 238));
	  				
	  			}
	  			
	  			@Override
	  			public void mouseEntered(MouseEvent e) {
	  				FillInInformation.this.small.setBackground(new Color(229, 229, 229));
	  				
	  			}
	  		});
	          
	          
	          this.close.addMouseListener(new MouseAdapter() {
	  			
	  			@Override
	  			public void mouseExited(MouseEvent e) {
	  				FillInInformation.this.close.setBackground(new Color(238, 238, 238));
	  				
	  			}
	  			
	  			@Override
	  			public void mouseEntered(MouseEvent e) {
	  				FillInInformation.this.close.setBackground(new Color(251, 97, 85));
	  				
	  			}
	  		});
				this.back.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						dispose();
						CreateGroup creategroup= new CreateGroup();
						
					}
				});
				
				this.next.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						if(text3.getText().equals("")){
							JOptionPane.showMessageDialog(null, "群名称不能为空");						}else{
							
						
						dispose();
						Invite invite = new Invite();
						text=text1.getText();
						text2=text3.getText();
						if(th.isSelected()){
							max=200;
						}if(fh.isSelected()){
							max=500;
						}if(os.isSelected()){
							max=1000;
						}if(ts.isSelected()){
							max=2000;
						}
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
							FillInInformation.this.setLocation((int)(FillInInformation.this.getLocation().getX()+
									(e.getX()-x)), (int)(FillInInformation.this.getLocation().getY()+
									(e.getY()-y)));
							
						}
					});
					
	}
	

}
