package kurorekishimain;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import member.MemberDaoImpl;

public class KuroRekishiMain {
	private static final BufferedImage[] images =  new BufferedImage[5];
	public static Map<String,String> userInfo = new  HashMap<>();
	public static String idKey = null;
	
	int index=0;
	boolean checkLogined;
	
	private MemberDaoImpl dao;
	
	private JFrame frame;
	private JLabel lblImage;
	private JTextField textId;
	private JPasswordField passwordField;
	private JButton btnLogin;
	private JButton btnJoin;
	private JPanel Login;
	private JPanel Main;
	private JButton btnNotgood;
	private JLabel lblMemberImages;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KuroRekishiMain window = new KuroRekishiMain();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public KuroRekishiMain() {
		dao = MemberDaoImpl.getInstance();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 664, 800);
		frame.setTitle("쿠로렉시");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		Main = new JPanel();
        Main.setBounds(0, 0, 648, 761);
        frame.getContentPane().add(Main);
        Main.setLayout(null);
        
        JPanel mainButtonPanel = new JPanel();
        mainButtonPanel.setBounds(0, 701, 648, 60);
        Main.add(mainButtonPanel);
        mainButtonPanel.setLayout(null);
        
        JButton btnNewChat = new JButton("채팅");
        btnNewChat.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ChatFrame.newChatFrame();
            }
        });
        btnNewChat.setFont(new Font("D2Coding", Font.BOLD, 16));
        btnNewChat.setBounds(140, 5, 122, 45);
        mainButtonPanel.add(btnNewChat);
        
        JButton btnProfile = new JButton("프로필");
        btnProfile.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		// 프로필 수정/업데이트
        		UpdateFrame.newUpdateFrame();
        	}
        });
        btnProfile.setFont(new Font("D2Coding", Font.BOLD, 16));
        btnProfile.setBounds(382, 5, 122, 45);
        mainButtonPanel.add(btnProfile);
        
        btnNotgood = new JButton("별로에요");
        btnNotgood.setFont(new Font("D2Coding", Font.BOLD, 16));
        btnNotgood.setBounds(141, 651, 122, 45);
        Main.add(btnNotgood);
        
        JButton btnGood = new JButton("좋아요");
        btnGood.setFont(new Font("D2Coding", Font.BOLD, 16));
        btnGood.setBounds(380, 651, 122, 45);
        Main.add(btnGood);
        
        
        JButton btnGoPreImage = new JButton("<");
        btnGoPreImage.setBounds(12, 264, 41, 45);
        Main.add(btnGoPreImage);
        
        JButton btnGoNextImage = new JButton(">");
        btnGoNextImage.setBounds(595, 264, 41, 45);
        Main.add(btnGoNextImage);
        
        lblMemberImages = new JLabel(new ImageIcon("usersimage\\변주환\\변주환1"));
        lblMemberImages.setBounds(0, 0, 648, 641);
        Main.add(lblMemberImages);
        
		
		// ---------------------------- 메인창작업
		
		
		Login = new JPanel();
        Login.setBounds(0, 0, 658, 766);
        frame.getContentPane().add(Login);
        Login.setLayout(null);
        
        JLabel lblId = new JLabel("아이디");
        lblId.setBackground(Color.WHITE);
        lblId.setOpaque(true);
        lblId.setForeground(Color.BLACK);
        lblId.setHorizontalAlignment(SwingConstants.CENTER);
        lblId.setFont(new Font("궁서체", Font.BOLD, 15));
        lblId.setEnabled(true);
        lblId.setBounds(37, 634, 95, 41);
        Login.add(lblId);
		
        JLabel lblPw = new JLabel("비밀번호");
        lblPw.setBackground(Color.WHITE);
        lblPw.setOpaque(true);
        lblPw.setForeground(Color.BLACK);
        lblPw.setFont(new Font("궁서체", Font.BOLD, 15));
        lblPw.setHorizontalAlignment(SwingConstants.CENTER);
        lblPw.setBounds(37, 685, 95, 41);
        Login.add(lblPw);
		
        textId = new JTextField();
        textId.setBounds(187, 634, 260, 41);
        Login.add(textId);
        textId.setColumns(10);
        
        passwordField = new JPasswordField();
        passwordField.setBounds(187, 685, 260, 41);
        Login.add(passwordField);
        
        btnLogin = new JButton("로그인");
        btnLogin.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	   Login();
        	}
        });
        btnLogin.setFont(new Font("궁서체", Font.BOLD, 15));
        btnLogin.setBounds(492, 634, 119, 41);
        Login.add(btnLogin);
        
        btnJoin = new JButton("회원가입");
        btnJoin.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		JoinMember.newJoinMember(frame);
        	}
        });
        btnJoin.setFont(new Font("궁서체", Font.BOLD, 15));
        btnJoin.setBounds(492, 685, 119, 41);
        Login.add(btnJoin);

        
        lblImage = new JLabel(new ImageIcon("appImage/사진.png"));
        lblImage.setFont(new Font("궁서체", Font.BOLD, 15));
        lblImage.setBackground(Color.WHITE);
        lblImage.setBounds(0, 0, 646, 766);
        Login.add(lblImage);
	    
	    //------------------------------ 사진입력창
        
		
		
		
	} // end initialize()

	private void Login() {
		idKey = textId.getText();
		String pw = String.valueOf(passwordField.getPassword());
		if((idKey.equals(null) || idKey.equals(""))&&(pw.equals(null)||pw.equals(""))){
			JOptionPane.showMessageDialog(frame,"아이디, 비밀번호 를 입력해주세요.", "Warning", JOptionPane.WARNING_MESSAGE);
			return;
		}else if(idKey.equals(null) || idKey.equals("")) {
			JOptionPane.showMessageDialog(frame, "아이디를 입력해주세요.", "Warning", JOptionPane.WARNING_MESSAGE);
			return;
		}else if(pw.equals(null)||pw.equals("")) {
			JOptionPane.showMessageDialog(frame, "비밀번호를 입력해주세요.", "Warning", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		checkLogined = dao.Login(idKey, pw);
		
		if(checkLogined) {
			String name =findName(idKey);
			userInfo.put(idKey, name);
			System.out.println("MAP에 저장된 이름 = " +  userInfo.get(idKey));
			JOptionPane.showMessageDialog(frame, "새짝을 찾아봐요~!!", "환영", JOptionPane.PLAIN_MESSAGE);
			Login.setVisible(false);
		}else {
			JOptionPane.showMessageDialog(frame, "등록된 아이디가 없습니다.", "Warning", JOptionPane.WARNING_MESSAGE);
		}
	
    }

	private String findName(String id) {
		String name = dao.fineName(id);
		return name;
	}

	

	
}
