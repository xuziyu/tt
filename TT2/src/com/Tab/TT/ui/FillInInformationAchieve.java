package com.Tab.TT.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
/** 
 * 创建群
* @author : 白长昊
* @date 创建时间：2017年8月28日 下午6:29:48 
* @version 1.0 
* @parameter  
* @since  
* @return  
*/
public class FillInInformationAchieve extends JFrame{
	 private JLabel gropu=new JLabel("创建群");
	private JLabel FName=new JLabel("群名称:");
	 private JLabel name=new JLabel(FillInInformation.text2);
	 
	 private JLabel Fid=new JLabel("群号:");
	 private JLabel id=new JLabel();
	 private JLabel success=new JLabel("创建成功");
	 private JButton achieve = new JButton("完成");
	 private JButton close = new JButton("X");
		private JButton small = new JButton("—");
		
	    private int x  ;
		private int y  ;
	public   FillInInformationAchieve(int groupId){
		this.setTitle("创建群");
	
		this.setSize(544, 450);
		this.setUndecorated(true);
		this.setVisible(true);
		this.setLayout(null);
		this.setLocationRelativeTo(null);
		this.add(gropu);
		this.gropu.setBounds(5, 0, 80, 40);
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
		this.add(Fid);
		this.Fid.setBounds(20, 280, 70, 80);
		this.add(id);
		this.id.setBounds(70, 280, 70, 80);
		this.add(FName);
		this.FName.setBounds(20, 300, 70, 80);
		
		this.add(name);
		this.name.setBounds(70,300, 300,80);
		
		this.add(success);
		this.success.setBounds(300, 200, 100, 50);
		this.success.setFont(new Font(null, ABORT, 20));
		
		this.add(achieve);
		this.achieve.setBounds(468, 425, 75, 25);
		
		this.id.setText(groupId+"");

		move();
          this.close.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				
			}
		});
		
	this.small.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				FillInInformationAchieve.this.setExtendedState(ICONIFIED);
				
			}
		});
	 this.small.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseExited(MouseEvent e) {
				FillInInformationAchieve.this.small.setBackground(new Color(238, 238, 238));
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				FillInInformationAchieve.this.small.setBackground(new Color(229, 229, 229));
				
			}
		});
       
       
       this.close.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseExited(MouseEvent e) {
				FillInInformationAchieve.this.close.setBackground(new Color(238, 238, 238));
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				FillInInformationAchieve.this.close.setBackground(new Color(251, 97, 85));
				
			}
		});
		
		this.achieve.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				
			}
		});
	}
		public static void main(String[] args) {
			 CreateGroup creategroup= new CreateGroup();

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
					FillInInformationAchieve.this.setLocation((int)(FillInInformationAchieve.this.getLocation().getX()+
							(e.getX()-x)), (int)(FillInInformationAchieve.this.getLocation().getY()+
							(e.getY()-y)));
					
				}
			});
			
			
		
	}
	
	
//	 public static void main(String[] args) {
//		
//		 FillInInformationAchieve fillininformationAchieve = new FillInInformationAchieve();
//		 	
//		}

}
