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
