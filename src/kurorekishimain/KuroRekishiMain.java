package kurorekishimain;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;

public class KuroRekishiMain {
	private static final String[] locations = {"서울","경기","충북","충남","전북","전남","경북","경남","강원도","제주"};

	private JFrame frame;
	private JLabel lblImage;
	private JTextField textId;
	private JPasswordField passwordField;
	private JButton btnLogin;
	private JButton btnJoin;
	private JTextField inputId;
	private JTextField inputPw;
	private JTextField inputName;
	private JButton btnCheckId;
	private JPanel Login;
	private JPanel Join;
	private JPanel Main;
	private JPanel Photo;
	private JLabel lblPhoto;
	private JButton btnBack1;
	private JButton btnInputPhoto;
	private JButton btnBack1_1;

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
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 664, 889);
		frame.setTitle("쿠로렉시");
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		Login = new JPanel();
        Login.setBounds(0, 0, 648, 850);
        frame.getContentPane().add(Login);
        Login.setLayout(null);
		
		
		Join = new JPanel();
		Join.setBounds(0, 0, 648, 850);
		frame.getContentPane().add(Join);
		Join.setLayout(null);
		
		Photo = new JPanel();
        Photo.setBounds(0, 0, 648, 850);
        frame.getContentPane().add(Photo);
        Photo.setLayout(null);
		
		JLabel JoinId = new JLabel("아이디");
		JoinId.setHorizontalAlignment(SwingConstants.CENTER);
		JoinId.setFont(new Font("굴림체", Font.BOLD, 18));
		JoinId.setBounds(12, 42, 137, 66);
		Join.add(JoinId);
		
		JLabel JoinPw = new JLabel("비밀번호");
		JoinPw.setHorizontalAlignment(SwingConstants.CENTER);
		JoinPw.setFont(new Font("굴림체", Font.BOLD, 18));
		JoinPw.setBounds(12, 119, 137, 66);
		Join.add(JoinPw);
		
		JLabel JoinName = new JLabel("아이디");
		JoinName.setHorizontalAlignment(SwingConstants.CENTER);
		JoinName.setFont(new Font("굴림체", Font.BOLD, 18));
		JoinName.setBounds(12, 194, 137, 66);
		Join.add(JoinName);
		
		inputId = new JTextField();
		inputId.setFont(new Font("D2Coding", Font.BOLD, 17));
		inputId.setBounds(161, 50, 313, 53);
		Join.add(inputId);
		inputId.setColumns(10);
		
		inputPw = new JTextField();
		inputPw.setFont(new Font("D2Coding", Font.BOLD, 17));
		inputPw.setColumns(10);
		inputPw.setBounds(161, 132, 313, 53);
		Join.add(inputPw);
		
		inputName = new JTextField();
		inputName.setFont(new Font("D2Coding", Font.BOLD, 17));
		inputName.setColumns(10);
		inputName.setBounds(161, 207, 313, 53);
		Join.add(inputName);
		
		btnCheckId = new JButton("중복확인");
		btnCheckId.setFont(new Font("굴림", Font.BOLD, 16));
		btnCheckId.setBounds(486, 50, 124, 58);
		Join.add(btnCheckId);
		
		JComboBox LocationcomboBox = new JComboBox(locations);
		LocationcomboBox.setFont(new Font("D2Coding", Font.BOLD, 17));
		LocationcomboBox.setBounds(161, 319, 313, 53);
		Join.add(LocationcomboBox);
		
		JLabel JoinName_1 = new JLabel("주소");
		JoinName_1.setHorizontalAlignment(SwingConstants.CENTER);
		JoinName_1.setFont(new Font("굴림체", Font.BOLD, 18));
		JoinName_1.setBounds(12, 319, 137, 66);
		Join.add(JoinName_1);
		
		JLabel JoinExperience = new JLabel("흑역사");
		JoinExperience.setHorizontalAlignment(SwingConstants.CENTER);
		JoinExperience.setFont(new Font("굴림체", Font.BOLD, 18));
		JoinExperience.setBounds(12, 457, 137, 66);
		Join.add(JoinExperience);
		
		JTextArea textAreaExperience = new JTextArea();
		textAreaExperience.setFont(new Font("D2Coding", Font.BOLD, 17));
		textAreaExperience.setBounds(161, 457, 313, 264);
		Join.add(textAreaExperience);
		
		JButton btnMemberJoin = new JButton("사진넣기");
		btnMemberJoin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertPhoto();
			}
		});
		btnMemberJoin.setFont(new Font("굴림", Font.BOLD, 16));
		btnMemberJoin.setBounds(161, 750, 124, 58);
		Join.add(btnMemberJoin);
		
		JButton btnBack = new JButton("뒤로가기");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goBack();
			}
		});
		btnBack.setFont(new Font("굴림", Font.BOLD, 16));
		btnBack.setBounds(350, 750, 124, 58);
		Join.add(btnBack);
		
        
        JLabel lblId = new JLabel("아이디");
        lblId.setBackground(Color.WHITE);
        lblId.setOpaque(true);
        lblId.setForeground(Color.BLACK);
        lblId.setHorizontalAlignment(SwingConstants.CENTER);
        lblId.setFont(new Font("궁서체", Font.BOLD, 15));
        lblId.setEnabled(true);
        lblId.setBounds(37, 690, 95, 41);
        Login.add(lblId);
        
        JLabel lblPw = new JLabel("비밀번호");
        lblPw.setBackground(Color.WHITE);
        lblPw.setOpaque(true);
        lblPw.setForeground(Color.BLACK);
        lblPw.setFont(new Font("궁서체", Font.BOLD, 15));
        lblPw.setHorizontalAlignment(SwingConstants.CENTER);
        lblPw.setBounds(37, 758, 95, 41);
        Login.add(lblPw);
        
        textId = new JTextField();
        textId.setBounds(187, 690, 260, 41);
        Login.add(textId);
        textId.setColumns(10);
        
        passwordField = new JPasswordField();
        passwordField.setBounds(187, 758, 260, 41);
        Login.add(passwordField);
        
        btnLogin = new JButton("로그인");
        btnLogin.setFont(new Font("궁서체", Font.BOLD, 15));
        btnLogin.setBounds(492, 690, 119, 41);
        Login.add(btnLogin);
        
        btnJoin = new JButton("회원가입");
        btnJoin.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		joinMember();
        	}
        });
        btnJoin.setFont(new Font("궁서체", Font.BOLD, 15));
        btnJoin.setBounds(492, 758, 119, 41);
        Login.add(btnJoin);

        
        lblImage = new JLabel(new ImageIcon("appImage/사진.png"));
        lblImage.setFont(new Font("궁서체", Font.BOLD, 15));
        lblImage.setBackground(Color.WHITE);
        lblImage.setBounds(0, 0, 648, 850);
        Login.add(lblImage);
        
        
       
		
        JLabel JoinLabel = new JLabel("");
		JoinLabel.setBounds(0, 0, 648, 850);
		Join.add(JoinLabel);
		
		 btnBack1 = new JButton("뒤로가기");
	        btnBack1.setFont(new Font("D2Coding", Font.BOLD, 14));
	        btnBack1.setBounds(79, 795, 115, 45);
	        btnBack1.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		goBack1();
	        	}
	        });
	        Photo.add(btnBack1);
	        
	        btnInputPhoto = new JButton("완료");
	        btnInputPhoto.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		completeJoin();
	        	}
	        });
	        btnInputPhoto.setFont(new Font("D2Coding", Font.BOLD, 14));
	        btnInputPhoto.setBounds(455, 795, 115, 45);
	        Photo.add(btnInputPhoto);
	        
	        lblPhoto = new JLabel("");
	        lblPhoto.setBounds(0, 0, 648, 785);
	        Photo.add(lblPhoto);
	        
	        btnBack1_1 = new JButton("사진저장");
	        btnBack1_1.setFont(new Font("D2Coding", Font.BOLD, 14));
	        btnBack1_1.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		insertImages();
	        	}
	        });
	        btnBack1_1.setBounds(266, 795, 115, 45);
	        Photo.add(btnBack1_1);
        
        
		Main = new JPanel();
		Main.setBounds(0, 0, 648, 850);
		frame.getContentPane().add(Main);
		Main.setLayout(null);
		
		
		
	}
	protected void insertPhoto() {
		Join.setVisible(false);
		Photo.setVisible(true);
		
	}

	private void completeJoin() {
		Photo.setVisible(false);
		Main.setVisible(true);
	}
	
	private void insertImages() {
		JFileChooser jFileChooser = new JFileChooser();
		jFileChooser.setCurrentDirectory(new File("C:\\Users\\82107\\Desktop\\myJSP"));
		int result = jFileChooser.showSaveDialog(null);
		
	}

	private void goBack1() {
		Photo.setVisible(false);
		Join.setVisible(true);
	}

	private void goBack() {
		Login.setVisible(true);
		Join.setVisible(false);
		
	}

	private void joinMember() {
		Login.setVisible(false);
		Join.setVisible(true);
	}
}
