package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import client.ChatManager;
import util.playmusic;

 
public class loginpanel extends JFrame implements ActionListener {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private JPanel pan = new JPanel();
private JLabel namelab = new JLabel("用户名");
private JLabel passlab = new JLabel("密    码");
private JTextField nametext = new JTextField();
private JPasswordField passtext = new JPasswordField();
 
public JButton denglu = new JButton("登录");
public JButton zhuce = new JButton("注册");
public JButton updatepass = new JButton("修改密码");
public JButton deleteuser = new JButton("删除用户");
 
public loginpanel(){
	Font font = new Font("宋体",Font.BOLD,12);
	super.setTitle("欢迎登录本系统");
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	pan.setLayout(null);
	namelab.setBounds(20,20,60,30);
	nametext.setBounds(90,20,140,30);
	passlab.setBounds(20,60,60,30);
	passtext.setBounds(90,60,140,30);
	denglu.setBounds(30,120,90,20);
	zhuce.setBounds(140,120,90,20);
	updatepass.setBounds(30,150,90,20);
	deleteuser.setBounds(140,150,90,20);
	
	pan.add(namelab);
	pan.add(nametext);
	pan.add(passlab);
	pan.add(passtext);
	pan.add(denglu);
	pan.add(zhuce);
	pan.add(updatepass);
	pan.add(deleteuser);
	
	passtext.setFont(font);
	zhuce.setFont(font);
	updatepass.setFont(font);
	deleteuser.setFont(font);
	
	denglu.addActionListener(this);
	zhuce.addActionListener(this);
	updatepass.addActionListener(this);
	deleteuser.addActionListener(this);
	
	super.add(pan);
	super.setSize(300,250);
	super.setVisible(true);
}
 

 
	@Override
	public void actionPerformed(ActionEvent arg0) {
	if(arg0.getSource()==denglu){
		denglu();
	}else if (arg0.getSource()==zhuce){
		zhuce();
	}else if (arg0.getSource()==updatepass){
		updatepass();
	}else if (arg0.getSource()==deleteuser){
		deleteuser();
	}
 
	}
	//登录按钮的事件处理函数
	@SuppressWarnings("deprecation")
	public void denglu(){
	 jdbc d  =	new jdbc();
	 String username = nametext.getText();
	 String password = passtext.getText();
      if(d.compare(username, password)){
    	  dispose();
    	  new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				playmusic.lm();
			}
		}).start();
    	  EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						mainwindow frame = new mainwindow();
						frame.setVisible(true);
						 ChatManager.getChatManager().setWindow(frame);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
      }
	}
	//注册按钮触发后的事件处理函数
	public void zhuce(){
		jdbc d  =	new jdbc();
		String username = nametext.getText();
		 @SuppressWarnings("deprecation")
		String password = passtext.getText();
		 d.insert(username,password);
	}
	//修改密码按钮触发后的事件处理函数
	public void updatepass(){
		pan.setEnabled(false);
		JFrame frame1 = new JFrame("密码修改");
		frame1.setSize(250, 200);
		JPanel updatepass = new JPanel();
		JLabel namelab1 = new JLabel("用户名");
		JLabel passlab1 = new JLabel("旧密码");
		JLabel newpasslab = new JLabel("新密码");
		JTextField nametext1  = new JTextField(""+nametext.getText());
		JPasswordField passtext1 = new JPasswordField();
		JPasswordField newpasstext  = new JPasswordField();
		JButton ok = new JButton("修改");
		JButton resert = new JButton("重置");
		
		updatepass.setLayout(null);
		
		namelab1.setBounds(5,5,70,20);
		nametext1.setBounds(80,5,120,20);
		passlab1.setBounds(5,30,70,20);
		passtext1.setBounds(80,30,120,20);
		newpasslab.setBounds(5,60,70,20);
		newpasstext.setBounds(80,60,120,20);
		ok.setBounds(10,110,60,20);
		resert.setBounds(90,110,60,20);
		
		updatepass.add(namelab1);
		updatepass.add(nametext1);
		updatepass.add(passlab1);
		updatepass.add(passtext1);
		updatepass.add(newpasslab);
		updatepass.add(newpasstext);
		updatepass.add(ok);
		updatepass.add(resert);
		
		frame1.add(updatepass);
		frame1.setVisible(true);
		
		ok.addActionListener(new ActionListener(){
 
			@Override
			public void actionPerformed(ActionEvent arg0) {
				jdbc d  =	new jdbc();
				String username = nametext1.getText();
				 @SuppressWarnings("deprecation")
				String password1 = passtext1.getText();
				 @SuppressWarnings("deprecation")
				String newpassword = newpasstext.getText();
				if(d.update(username,password1,newpassword)){
					frame1.setVisible(false);
				}
			}
		});
		//重置文本框 里的内容
		resert.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				nametext1.setText("");
				passtext1.setText("");
				newpasstext.setText("");
			}
		});
	}
	//删除用户按钮触发后的事件处理函数
	public void deleteuser(){
	String username = nametext.getText();
	String password = passtext.getText();
	jdbc s = new jdbc();
		s.delete(username,password);
	}
	
	public class jdbc {
		Connection con = null;
		Statement statement = null;
		ResultSet res = null;
		String driver = "com.mysql.jdbc.Driver";
		String url  = "jdbc:mysql://localhost:3306/user";
		String name = "root";
		String passwd = "root";
		 
		public jdbc(){
			try{
			Class.forName(driver).newInstance();
			con = DriverManager.getConnection(url,name,passwd);
			statement = con.createStatement();
			
			}catch(ClassNotFoundException e){
				System.out.println("对不起，找不到这个Driver");
				e.printStackTrace();
			}catch(SQLException e){
				e.printStackTrace();
			}catch(Exception e){
				e.printStackTrace();
				}
		}
		//对用户信息的修改实际上就是对密码的修改
		public boolean update(String username1,String password1,String newpassword){
			boolean judge = false;
			boolean s =compare(username1,password1);
			if(s){
			String sql = "update q set password=\""+newpassword+"\"where username=\""+username1+"\"";
			try {
				int a = statement.executeUpdate(sql);
				if(a==1){
					JOptionPane.showMessageDialog(null,"密码修改成功！");
					judge = true;
				}
				con.close();
				statement.close();
			} catch (SQLException e) {
		        JOptionPane.showMessageDialog(null, "用户不存在！");
				e.printStackTrace();
			}
			}
			else{
				 JOptionPane.showMessageDialog(null, "修改失败");
			}
			return judge;
		}
		//删除用户信息
		public void delete(String username,String password){
			if(compare(username,password)){
				JOptionPane.showMessageDialog(null,"已经完成删除");
			}else{
				return;
			}
			String sql = "delete from q where username=\""+username+"\"";
			try{
				int a =  statement.executeUpdate(sql);
				con.close();
				statement.close();
			}catch(SQLException e){
				JOptionPane.showMessageDialog(null,"不存在该用户！");
				e.printStackTrace();
			}
			
		}
		//用户注册功能的实现，添加数据
		public void insert(String username,String password){
			String sql = "insert into q(username,password) values(\""+username+"\",\""+password+"\")";
			try{
				int a = statement.executeUpdate(sql);
				con.close();
				statement.close();
				if(a==1){
					JOptionPane.showMessageDialog(null,"注册成功！");
				}
			}catch(SQLException e){
				JOptionPane.showMessageDialog(null, "对不起该用户名已经有了！");
				e.printStackTrace();
			}
		}
		//对比用户名和密码是不匹配
		public boolean compare(String username,String password){
			boolean m = false;
			String sql = "select password from q where username=\""+username+"\"";
		    try{
		    	res = statement.executeQuery(sql);
		    	if(res.next()){
		    		String pa = res.getString(1);
		    		System.out.println(pa+" " +password);
		    		if(pa.equals(password)){
		    			m = true;
		    		}else {
		    			JOptionPane.showMessageDialog(null, "密码错误！");
		        	}
		    	}else {
		    		JOptionPane.showMessageDialog(null, "用户名不存在！");
		    	}
		    	res.close();
		    	con.close();
				statement.close();
		    	
		    }catch(SQLException e){
		    	e.printStackTrace();
		    }
		    return m;
		}
		}
}


