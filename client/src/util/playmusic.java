package util;
/**
 * 用来播放音乐的工具   用户接收到消息
 * @author Administrator
 *
 */

import java.applet.AudioClip;
import java.applet.*;
import java.net.URL;



public class playmusic {
     private  static AudioClip reciveAudio;
     private static AudioClip loginAudio;
     //接收到消息时发出声音
     public static void pm() {
    	 //获取音乐文件的路径
    	 URL url=playmusic.class.getResource("../sound/msg.wav");
    	 //创建声音剪辑对象
    	 reciveAudio =Applet.newAudioClip(url);
    	 //播放声音
    	 reciveAudio.play();
     }
     
     //登陆成功时发出提示信息
     public static void lm() {
    	 //获取音乐文件的路径
    	 URL url=playmusic.class.getResource("../sound/global.wav");
    	 //创建声音剪辑对象
    	 loginAudio=Applet.newAudioClip(url);
    	 //播放声音
    	 loginAudio.play();
     }
}
