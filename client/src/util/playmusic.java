package util;
/**
 * �����������ֵĹ���   �û����յ���Ϣ
 * @author Administrator
 *
 */

import java.applet.AudioClip;
import java.applet.*;
import java.net.URL;



public class playmusic {
     private  static AudioClip reciveAudio;
     private static AudioClip loginAudio;
     //���յ���Ϣʱ��������
     public static void pm() {
    	 //��ȡ�����ļ���·��
    	 URL url=playmusic.class.getResource("../sound/msg.wav");
    	 //����������������
    	 reciveAudio =Applet.newAudioClip(url);
    	 //��������
    	 reciveAudio.play();
     }
     
     //��½�ɹ�ʱ������ʾ��Ϣ
     public static void lm() {
    	 //��ȡ�����ļ���·��
    	 URL url=playmusic.class.getResource("../sound/global.wav");
    	 //����������������
    	 loginAudio=Applet.newAudioClip(url);
    	 //��������
    	 loginAudio.play();
     }
}
