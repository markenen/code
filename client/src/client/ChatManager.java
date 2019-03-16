package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import util.playmusic;
import view.mainwindow;

public class ChatManager {
	private static final ChatManager instance = new ChatManager();

	public static ChatManager getChatManager() {
		return instance;
	}

	Socket socket;
	BufferedReader br;
	PrintWriter pw;
	mainwindow window;
	String IP;

	public void setWindow(mainwindow window) {
		this.window = window;
		window.appendTest("进入聊天界面。。。。");
	}

	public void connet(String ip) {
		this.IP=ip;
		new Thread() {

			@Override
			public void run() {// 循环从服务器收到数据
				try {// 建立连接
					socket = new Socket(IP, 12345);
					pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(),"UTF-8"));
					br = new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));

					String line=null;
					while ((line = br.readLine()) != null) {
						window.appendTest("收到：" + line);
						new Thread(new Runnable() {
							
							@Override
							public void run() {
								// TODO Auto-generated method stub
								playmusic.pm();
							}
						}).start();
					}
					br.close();
					pw.close();
					br = null;
					pw = null;

				} catch (UnknownHostException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}.start();
	}

	public void send(String out) {
		if (pw != null) {
			pw.write(out + "\n");
			pw.flush();
		}else {
			window.appendTest("中断");
		}

	}

}
