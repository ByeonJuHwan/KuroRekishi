package kurorekishimain;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import member.MemberDaoImpl;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class ChatFrame extends JFrame implements Runnable{
	private static final String SERVER_IP = "127.0.0.1";
	public static Socket socket;
	private MemberDaoImpl dao;
	
	private JPanel contentPane;
	public static JTextField textField;
	private JButton btnSend;
	
	private String chatName;
	private String ip;
	private InputStream is;
	private OutputStream os;
	private BufferedReader br_in;
	private BufferedWriter bw = null;
	private PrintWriter pw = null; 
	public static JTextArea textArea;
	

	/**
	 * Launch the application.
	 */
	public static void newChatFrame() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				ChatFrame frame = new ChatFrame();
				frame.setVisible(true);
			}
		});
	}

	public ChatFrame() {
		dao = MemberDaoImpl.getInstance();
		insertChatName();
		initialize();
		init();
	}
	

	private void insertChatName() {
		chatName = KuroRekishiMain.userInfo.get(KuroRekishiMain.idKey);
		if(chatName.length() == 0 || chatName ==null) {
			System.exit(0);
		}
		
	}

	/**
	 * Create the frame.
	 */
	public void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(760, 100, 664, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblChatName = new JLabel("대화명 : [[" + chatName + "]]");
		lblChatName.setBounds(0, 0, 311, 38);
		contentPane.add(lblChatName);
		
		JLabel lblChatServer = new JLabel("서버 IP 주소 " + SERVER_IP);
		lblChatServer.setBounds(323, 0, 325, 38);
		contentPane.add(lblChatServer);
		
		textField = new JTextField();
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object obj = e.getSource();
				String msg = textField.getText();
				if(obj==textField) {
					if(msg == null || msg.length() == 0) {
						JOptionPane.showMessageDialog(null, "채팅을 작성해주세요.", "경고", JOptionPane.WARNING_MESSAGE);
					}else {// 내용을 입력하고 엔터한경우
						try {
							pw.println(chatName + "#" + msg);
						}catch(Exception ee) {
							ee.printStackTrace();
						}
						textField.setText("");
					}
				}
			}
		});
		textField.setBounds(0, 429, 535, 32);
		contentPane.add(textField);
		textField.setColumns(10);
		
		btnSend = new JButton("종료");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					pw.println(chatName + "#exit");
				}catch(Exception ee) {
					ee.printStackTrace();
				}
				System.exit(0);
			}
		});
		btnSend.setBounds(536, 429, 112, 32);
		contentPane.add(btnSend);
		
		textArea = new JTextArea();
		textArea.setBackground(Color.PINK);
		textArea.setBounds(0, 37, 648, 393);
		contentPane.add(textArea);
	}
	
	public void init() {
		try {
			socket = new Socket(SERVER_IP,5000);
			is = socket.getInputStream();
			os = socket.getOutputStream();
			br_in = new BufferedReader(new InputStreamReader(is));
			bw = new BufferedWriter(new OutputStreamWriter(os));
			pw = new PrintWriter(bw,true);
			Thread chatThread = new Thread(this);
			chatThread.start();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
	}

	@Override
	public void run() {
		String message = null;
		String[] receiveMag = null;
		boolean isStop = false;
		while(!isStop) {
			try {
				message = (String)br_in.readLine();
				receiveMag = message.split("#");
			}catch(Exception e) {
				e.printStackTrace();
				isStop = true; // 프로그램 종료
			}// end try-catch
			System.out.println(receiveMag[0] + ":" + receiveMag[1]);
			if(receiveMag[1].equals("exit")) { // 해당 사용자의 exit
				if(receiveMag[1].equals(chatName)) {
					System.exit(0);
				}else {
					textArea.append(
							receiveMag[0] + "님이 종료했습니다. \n");
					textArea.setCaretPosition(
							textArea.getDocument().getLength());
				}
			}else { // exit이 아닌 경우 채팅 내용을 화면에 보여준다.
				textArea.append(receiveMag[0] + ":" + receiveMag[1] + "\n");
				textArea.setCaretPosition(
						textArea.getDocument().getLength());
			}
		}// end while
	}// end run
}
