package com.Tab.TT.ui;

import java.awt.Color;
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
public class CreateGroup extends JFrame{
	  private JLabel gropu=new JLabel("创建群");
    private JLabel groupType=new JLabel("1"+"   选择群类型");
    private JLabel fillInInformation =new JLabel("2"+"   填写群信息");
    private JLabel invite=new JLabel("3"+"   邀请群成员");
    private JButton student=new JButton("同学.同事");
    private JButton fans=new JButton("粉丝");
    private JButton relaxation =new JButton("生活休闲");
    private JButton school =new JButton("学校师生");
    private JButton computerGame =new JButton("游戏");
    private JButton study =new JButton("学习考试");
    private JButton interest =new JButton("兴趣爱好");
    private JButton business =new JButton("行业交流");
    private JButton settle =new JButton("置业安家");
    private JButton brand =new JButton("品牌产品");
    private JButton close = new JButton("X");
	private JButton small = new JButton("—");
	
    private int x  ;
	private int y  ;
	public CreateGroup(){
		this.setTitle("创建群");
		this.setSize(544, 450);
		this.setUndecorated(true);
		this.setVisible(true);
		this.setLayout(null);
		this.setLocationRelativeTo(null);
		this.add(gropu);
		this.gropu.setBounds(5, 0, 80, 40);
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
		this.add(groupType);
		this.groupType.setBounds(10, 40, 220, 32);
		this.add(fillInInformation);
		this.fillInInformation.setBounds(225, 40, 220, 32);
		this.add(invite);
		this.invite.setBounds(445, 40, 180, 32);
		this.add(student);
		
		this.student.setBounds(10, 72, 170, 90);
		this.add(fans);
		this.fans.setBounds(185, 72, 170, 90);
		this.add(relaxation);
		this.relaxation.setBounds(360, 72, 170, 90);
		this.add(school);
		this.school.setBounds(10, 165, 170, 90);
		this.add(computerGame);
		this.computerGame.setBounds(185,165 , 170, 185);
		this.add(study);
		this.study.setBounds(360,165,170,90);
		this.add(interest);
		this.interest.setBounds(10,260,170,90);
		this.add(business);
		this.business.setBounds(10, 355, 170, 90);
		this.add(settle);
		this.settle.setBounds(185, 355, 170, 90);
		this.add(brand);
		this.brand.setBounds(360,260, 170, 185);
		
		move();
          this.close.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				
			}
		});
          
          this.small.addMouseListener(new MouseAdapter() {
  			
  			@Override
  			public void mouseExited(MouseEvent e) {
  				CreateGroup.this.small.setBackground(new Color(238, 238, 238));
  				
  			}
  			
  			@Override
  			public void mouseEntered(MouseEvent e) {
  				CreateGroup.this.small.setBackground(new Color(229, 229, 229));
  				
  			}
  		});
          
          
          this.close.addMouseListener(new MouseAdapter() {
  			
  			@Override
  			public void mouseExited(MouseEvent e) {
  				CreateGroup.this.close.setBackground(new Color(238, 238, 238));
  				
  			}
  			
  			@Override
  			public void mouseEntered(MouseEvent e) {
  				CreateGroup.this.close.setBackground(new Color(251, 97, 85));
  				
  			}
  		});
		
	this.small.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				CreateGroup.this.setExtendedState(ICONIFIED);
				
			}
		});
		this.student.addActionListener(new ActionListener() {
		
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				 FillInInformation fillininformation = new FillInInformation("同事同学");
				
			}
		});
	this.fans.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				 FillInInformation fillininformation = new FillInInformation("粉丝");
				
			}
		});
	this.relaxation.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
			 FillInInformation fillininformation = new FillInInformation("生活休闲");
			
		}
	});
	this.school.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
			 FillInInformation fillininformation = new FillInInformation("家校师生");
			
		}
	});
this.computerGame.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
			 FillInInformation fillininformation = new FillInInformation("游戏");
			
		}
	});
this.study.addActionListener(new ActionListener() {
	
	@Override
	public void actionPerformed(ActionEvent e) {
		dispose();
		 FillInInformation fillininformation = new FillInInformation("学习考试");
		
	}
});
this.interest.addActionListener(new ActionListener() {
	
	@Override
	public void actionPerformed(ActionEvent e) {
		dispose();
		 FillInInformation fillininformation = new FillInInformation("兴趣爱好");
		
	}
});
this.business.addActionListener(new ActionListener() {
	
	@Override
	public void actionPerformed(ActionEvent e) {
		dispose();
		 FillInInformation fillininformation = new FillInInformation("行业交流");
		
	}
});
this.settle.addActionListener(new ActionListener() {
	
	@Override
	public void actionPerformed(ActionEvent e) {
		dispose();
		 FillInInformation fillininformation = new FillInInformation("置业安家");
		
	}
});
this.brand.addActionListener(new ActionListener() {
	
	@Override
	public void actionPerformed(ActionEvent e) {
		dispose();
		 FillInInformation fillininformation = new FillInInformation("品牌产品");
		
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
				CreateGroup.this.setLocation((int)(CreateGroup.this.getLocation().getX()+
						(e.getX()-x)), (int)(CreateGroup.this.getLocation().getY()+
						(e.getY()-y)));
				
			}
		});
		
		
	}


}
