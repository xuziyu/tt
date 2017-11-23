package com.Tab.TT.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
/**
 * 添加好友
 * 好友备注
 * 
 * @author wjh
 *
 * 时间 2017/08/28
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.Tab.TT.framework.context.Context;
import com.Tab.TT.framework.exception.SystemException;



public class AddFriendsRemarkFrame extends JFrame{
	
	private JButton buttonColse = new JButton("Χ");
	private JButton buttonMinimize = new JButton("－");
	private JLabel labelAddFriends = new JLabel("添加好友");
	private JLabel penguin = new JLabel();
	private JLabel BlueImage = new JLabel();
	
	private JLabel labelFriendsId = new JLabel("好友TT账号：");
	private JLabel labelFriendsIdShow = new JLabel();
	private JLabel labelFriendsNick = new JLabel("好友TT昵称：");
	private JLabel labelFriendsNickShow = new JLabel();
	
	private JLabel labelReamrk = new JLabel("好友备注:");
	private JTextField textFieldReamrkNmae = new JTextField("输入好友备注");
	public static String textFieldstaticRemarkName ;
	private JButton buttonNext = new JButton("下一步");
	private JButton buttonCancel = new JButton("取消");
	
	private int oldX ; 
	private int oldY ;
	
	
	public AddFriendsRemarkFrame(int userId , String nick){
		this.setTitle("添加好友");
		this.setSize(460, 360);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		this.setUndecorated(true);
		
		this.init();
		this.init0Top();
		this.init2Body();//中间信息
		this.init4Botton();//底部
		this.fontstyle();//字体设置
		
		//企鹅logo
//		ImageIcon iconP = new ImageIcon(this.getClass().getResource("/img/PenguinBLue.png"));
//		penguin = new JLabel(iconP);
//		this.add(penguin);
//		this.penguin.setBounds(5, 5, iconP.getIconWidth(), iconP.getIconHeight());		
		//顶部添加蓝色图片
		ImageIcon iconBlue = new ImageIcon(this.getClass().getResource("/img/Blue.png"));
		BlueImage = new JLabel(iconBlue);
		this.add(BlueImage);
		this.BlueImage.setBounds(0, 0, iconBlue.getIconWidth(), 30);
		//好友信息图片
		
		//关闭按钮事件
		this.buttonColse.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				
			}
		});

		//窗口最小化事件
		this.buttonMinimize.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AddFriendsRemarkFrame.this.setExtendedState(ICONIFIED);
				
			}
		});
		
		//窗口移动事件
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				oldX = e.getX();
				oldY = e.getY();
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		this.addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				AddFriendsRemarkFrame.this.setLocation((int)(AddFriendsRemarkFrame.this.getLocation().getX()+e.getX()-oldX),
						(int)(AddFriendsRemarkFrame.this.getLocation().getY()+e.getY()-oldY));
				
			}
		});
		//好友备注文本
		this.textFieldReamrkNmae.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AddFriendsRemarkFrame.this.textFieldReamrkNmae.setText("");
			}
			
		});

		//下一步按钮
		this.buttonNext.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					if("输入好友备注".equals(textFieldReamrkNmae.getText())||"".equals(textFieldReamrkNmae.getText().trim())){
						textFieldstaticRemarkName = "";
					}else{
						textFieldstaticRemarkName = textFieldReamrkNmae.getText();
					}
					AddFriendsSuccessFrame addFriendsSuccessFrame = new AddFriendsSuccessFrame(Context.currentUser.getUserId(),Context.currentUser.getUserNick());
					dispose();
				}
				catch(SystemException se){
					se.printStackTrace();
				}
				
			}
		});
		//取消按钮
		this.buttonCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		
		this.setVisible(true);
	}
	
	
	private void init0Top() {
		//标题
		this.add(labelAddFriends);
		this.labelAddFriends.setBounds(30, 5, 100, 20);
		this.labelAddFriends.setFont(new Font("华文楷体", Font.PLAIN, 12));
		//关闭按钮
		this.add(this.buttonColse);
		this.buttonColse.setBounds(430, 5, 25, 20);
		this.buttonColse.setMargin(new Insets(0, 0, 0, 0));
		this.buttonColse.setBackground(new Color(92, 190, 229));
		this.buttonColse.setForeground(Color.white);
		this.buttonColse.setBorder(null);
		this.buttonColse.setFocusPainted(false);
		//最小化按钮
		this.add(this.buttonMinimize);
		this.buttonMinimize.setBounds(400, 5, 25, 20);
		this.buttonMinimize.setMargin(new Insets(0, 0, 0, 0));
		this.buttonMinimize.setBackground(new Color(92, 190, 229));
		this.buttonMinimize.setForeground(Color.white);
		this.buttonMinimize.setBorder(null);
		this.buttonMinimize.setFocusPainted(false);
		
	}


	private void fontstyle() {
		Font font = new Font("华文楷体", Font.PLAIN, 13);
		this.labelFriendsId.setFont(font);
		this.labelFriendsIdShow.setFont(font);
		this.labelFriendsNick.setFont(font);
		this.labelFriendsNickShow.setFont(font);
		this.labelReamrk.setFont(font);
		this.textFieldReamrkNmae.setFont(font);
		this.buttonNext.setFont(font);
		this.buttonCancel.setFont(font);
		
	}
	private void init4Botton() {
		//下一步
		this.add(this.buttonNext);
		this.buttonNext.setBounds(320, 320, 50, 30);
		this.buttonNext.setBorder(null);
		this.buttonNext.setMargin(null);
		//取消
		this.add(this.buttonCancel);
		this.buttonCancel.setBounds(380, 320, 50, 30);
		this.buttonCancel.setBorder(null);
		this.buttonCancel.setMargin(null);
		
	}
	private void init2Body() {
		this.init();
		//好友信息
		this.add(labelFriendsId);
		this.labelFriendsId.setBounds(10, 100, 100, 25);
		this.add(labelFriendsIdShow);
		this.labelFriendsIdShow.setBounds(20, 130, 200, 25);
		//上一界面输入的好友id
		this.labelFriendsIdShow.setText(AddFriendsFrame.textstaticFieldInput);
		this.labelFriendsIdShow.setForeground(Color.BLUE);
		this.add(labelFriendsNick);
		this.labelFriendsNick.setBounds(10, 170, 100, 25);
		this.add(labelFriendsNickShow);
		this.labelFriendsNickShow.setBounds(20, 200, 200, 25);
		//上一界面查出的好友昵称
		this.labelFriendsNickShow.setText(AddFriendsFrame.friendstaticNick);
		this.labelFriendsNickShow.setForeground(Color.BLUE);
		
		//好友备注
		this.add(this.labelReamrk);
		this.labelReamrk.setBounds(160, 60, 60, 25);
		//备注名称
		this.add(this.textFieldReamrkNmae);
		this.textFieldReamrkNmae.setBounds(220, 60, 150, 25);
		
		
	}
	private void init() {
		this.setLayout(null);		
	}
	public static void main(String[] args) {
		try {
			AddFriendsRemarkFrame addFriendsRemark = new AddFriendsRemarkFrame(Context.currentUser.getUserId(),Context.currentUser.getUserNick());
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "您还没有登录");
		}
	}
}
