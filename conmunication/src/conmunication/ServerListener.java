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
			ServerSocket serversocket = new ServerSocket(12345);// 在serversocket创建之后要监听客户端的连接
			while (true) {// 不断的监听
				// block 会造成阻塞
				Socket socket = serversocket.accept();// 用于等待客户端的连接,当有多个客户连接了端口，则会返回多个socket对象
				// 建立连接
				JOptionPane.showMessageDialog(null, "有客户端连接到了本机的12345端口");
				// 将socket传递给新的线程，因为每一个socket又与独立的客户端进行通信
				ChatSocket cs = new ChatSocket(socket);
				cs.start();// 每一个ChatSocket都是相互独立的并不能相互交流信息
				ChatMannager.getChatMannager().add(cs);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
