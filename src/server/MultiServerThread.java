package server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

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
            while(!isStop) {
                message = br_in.readLine();
                String[] str = message.split("#");
                if(str[1].equals("exit")) {
                    chat(message);
                    isStop = true;
                }else {
                	chat(message);
                }
            }
            ServerMain.list.remove(this);
            ChatFrame.textArea.append(ChatFrame.socket.getInetAddress()+"IP 주소의 사용자 께서 종료하셨습니다. \n");
            ChatFrame.textArea.setText("남은 사용자 수 : " + ServerMain.list.size());
  
            
        }catch(Exception e){
        	ServerMain.list.remove(this);
            ChatFrame.textArea.append(ChatFrame.socket.getInetAddress()+"IP 주소의 사용자 께서 비정상종료하셨습니다. \n");
            ChatFrame.textArea.setText("남은 사용자 수 : " + ServerMain.list.size());
        }
    }

   
	

	private void chat(String message) {
        System.out.println("Chatting");
        for(MultiServerThread mt : ServerMain.list) {
            mt.send(message);
        }
        
    }

    private void send(String message) {
        pw.println(message);
        
    }
}
