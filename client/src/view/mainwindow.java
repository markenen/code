package view;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import org.omg.CORBA.ORB;

import client.ChatManager;

public class mainwindow extends JFrame {
	JPanel uppanel,downpanel;
	JTextArea jt;// ������ʾ�����ͺͽ��ܵ���Ϣ
	JTextField ip; //��ʾip��ַ
	JTextField send;// ������Ϣ���ı���
	Button b1;//���ӵ�������
	Button b2;//���Ͱ�ť

	// ���캯��
	public mainwindow() {
		this.setAlwaysOnTop(true);// ʹ�������������н�������Ϸ�
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("��ܽС��");
		this.setBounds(100, 100, 450, 300);
		uppanel = new JPanel(new BorderLayout());
		//uppanel.setBorder(new TitledBorder(""));
		downpanel = new JPanel(new BorderLayout());
		
		// �������ӵ������,���������
		JScrollPane js=new JScrollPane();
		js.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		js.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		jt=new JTextArea();
		js.add(jt);
		js.setViewportView(jt);
		ip=new JTextField("127.0.0.1");
		b1=new Button("���ӵ�������");
		uppanel.add(ip, BorderLayout.CENTER);
		uppanel.add(b1,BorderLayout.EAST);
		
		send=new JTextField("���");
		b2=new Button("����");
		downpanel.add(send,BorderLayout.CENTER);
		downpanel.add(b2,BorderLayout.EAST);
		
		this.setLayout(new BorderLayout());
		this.add(uppanel,BorderLayout.NORTH);
		this.add(downpanel,BorderLayout.SOUTH);
		this.add(js,BorderLayout.CENTER);
		
		//�԰�ť���м���
		b1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				ChatManager.getChatManager().connet(ip.getText());
			}
		});
		
		b2.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				ChatManager.getChatManager().send(send.getText());
				appendTest("��˵��"+send.getText());
				send.setText("");
			}
		});
		
		this.setVisible(true);
			
	}
	public void appendTest(String in) {
		jt.append("\n" + in);
	}
   
}
