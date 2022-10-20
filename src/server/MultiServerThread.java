package server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import kurorekishimain.ChatFrame;

public class MultiServerThread extends Thread{
    InputStream is;
    OutputStream os;
    Socket socket = null;
    BufferedReader br_in;
    BufferedReader br_out;
    PrintWriter pw = null;
    BufferedWriter bw;
    String message = null;
    
    public static List<String>chatList = new ArrayList<>();
    
    @Override
    public void run() {
        System.out.println("MultiServerThread is called");
        
        boolean isStop = false;
        try {
            is = ServerMain.socket.getInputStream();
            os = ServerMain.socket.getOutputStream();
            br_in = new BufferedReader(new InputStreamReader(is));
            bw = new BufferedWriter(new OutputStreamWriter(os));
            pw = new PrintWriter(bw,true);
            if(ServerMain.list.size()==2) {
                MultiServerThread mt = ServerMain.list.get(1);
                for(String s : chatList) {
                    String message = s;
                    mt.send(message);
                }
            }
            while(!isStop) {
                message = br_in.readLine();
                String[] str = message.split("#");
                System.out.println(ServerMain.list.size());
                if(ServerMain.list.size()==1) { // 채팅룸에 혼자 들어와있을
                    chatList.add(str[0]+"#"+str[1]);
                    chat(message);
                    
                }else { // 두명이 있을때
                    if(str[1].equals("exit")) {
                        chat(message);
                        isStop = true;
                    }else {
                        chat(message);
                    }
                }
            }
            ServerMain.list.remove(this);
         
            ChatFrame.textArea.setText("남은 사용자 수 : " + ServerMain.list.size());
  
            
        }catch(Exception e){
        	ServerMain.list.remove(this);
//            System.out.println(ChatFrame.socket.getInetAddress()+"비정상 종료됩니다.");
            System.out.println("남은 사용자 수 : " + ServerMain.list.size());
        }
    }


    private void chat(String message) {
        System.out.println("Chatting");
        for(MultiServerThread mt : ServerMain.list) {
            mt.send(message);
        }
        
    }

    public void send(String message) {
        pw.println(message);
        
    }
}
