package conmunication;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JOptionPane;

public class ServerListener extends Thread {

	@Override
	public void run() {
		try {
			@SuppressWarnings("resource")
			ServerSocket serversocket = new ServerSocket(12345);// ��serversocket����֮��Ҫ�����ͻ��˵�����
			while (true) {// ���ϵļ���
				// block ���������
				Socket socket = serversocket.accept();// ���ڵȴ��ͻ��˵�����,���ж���ͻ������˶˿ڣ���᷵�ض��socket����
				// ��������
				JOptionPane.showMessageDialog(null, "�пͻ������ӵ��˱�����12345�˿�");
				// ��socket���ݸ��µ��̣߳���Ϊÿһ��socket��������Ŀͻ��˽���ͨ��
				ChatSocket cs = new ChatSocket(socket);
				cs.start();// ÿһ��ChatSocket�����໥�����Ĳ������໥������Ϣ
				ChatMannager.getChatMannager().add(cs);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
