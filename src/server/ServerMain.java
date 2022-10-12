package server;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import kurorekishimain.KuroRekishiMain;

public class ServerMain {
    public static ArrayList<MultiServerThread> list;
    public static Socket socket;
    public static Map<String, String> wantChat = new HashMap<>();
    
    
    public static void checkThumb() {
        if(wantChat.get(KuroRekishiMain.name)==null) {
            return;
        }
        
        
        String key = null;
        for(String s : wantChat.keySet()) {
            key = s;
        }
        System.out.println(key);
        if(key.equals(wantChat.get(key))) {
            int result  = JOptionPane.showConfirmDialog(null,wantChat.get(key)+" 님 께 메세지가 왔습니다." , "알림", JOptionPane.YES_NO_OPTION);
            if(result == JOptionPane.YES_NO_OPTION) {
                // TODO
                System.out.println("확인완료");
                // chat프레임이 visible로 바뀌어야함
                
            }else {
                return;
            }
        }
        
    } 
    

    public static void main(String[] args) {
        list = new ArrayList<>();
        try {
            ServerSocket serverSocket = new ServerSocket(); 
            serverSocket.bind(new InetSocketAddress("127.0.0.1", 5000));
            MultiServerThread multiServerThread = null;
            boolean isStop = true;
            System.out.println("서버 정상 실행중...");
            while(isStop) {
                socket = serverSocket.accept();
                multiServerThread = new MultiServerThread();
                list.add(multiServerThread);
                multiServerThread.start();
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
}
