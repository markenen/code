package conmunication;

import java.util.Vector;

public class ChatMannager {
	private ChatMannager() {
	}

	private static final ChatMannager cm = new ChatMannager();

	public static ChatMannager getChatMannager() {
		return cm;
	}

	Vector<ChatSocket> vector = new Vector<ChatSocket>();

	public void add(ChatSocket cs) {
		vector.add(cs);
	}

	public void publish(ChatSocket cs, String out) {
		for (int i = 0; i < vector.size(); i++) {
			ChatSocket csChatSocket = vector.get(i);
			if (!cs.equals(csChatSocket)) {// ��������Լ���������Ϣ�򷢳�
				csChatSocket.out(out + "\n");
			}
		}
	}

}