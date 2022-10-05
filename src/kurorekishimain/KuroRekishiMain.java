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

public class KuroRekishiMain {

	private JFrame frame;
	private JLabel lblImage;
	private JTextField textField;
	private JPasswordField passwordField;

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
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		JPanel Login = new JPanel();
        Login.setBounds(0, 0, 648, 850);
        frame.getContentPane().add(Login);
        Login.setLayout(null);
        
        
        JLabel lblId = new JLabel("아이디");
        lblId.setBackground(Color.BLACK);
        lblId.setOpaque(true);
        lblId.setForeground(Color.WHITE);
        lblId.setHorizontalAlignment(SwingConstants.CENTER);
        lblId.setFont(new Font("궁서체", Font.BOLD, 15));
        lblId.setEnabled(true);
        lblId.setBounds(37, 713, 95, 41);
        Login.add(lblId);
        
        JLabel lblPw = new JLabel("비밀번호");
        lblPw.setBackground(Color.BLACK);
        lblPw.setOpaque(true);
        lblPw.setForeground(Color.WHITE);
        lblPw.setFont(new Font("궁서체", Font.BOLD, 15));
        lblPw.setHorizontalAlignment(SwingConstants.CENTER);
        lblPw.setBounds(37, 784, 95, 41);
        Login.add(lblPw);
        
        textField = new JTextField();
        textField.setBounds(187, 713, 260, 41);
        Login.add(textField);
        textField.setColumns(10);
        
        passwordField = new JPasswordField();
        passwordField.setBounds(187, 784, 260, 41);
        Login.add(passwordField);
        
        lblImage = new JLabel(new ImageIcon("appImage/사진.png"));
        lblImage.setBackground(Color.WHITE);
        lblImage.setBounds(0, 0, 648, 850);
        Login.add(lblImage);
        
        JButton btnNewButton = new JButton("New button");
        btnNewButton.setBounds(492, 713, 104, 41);
        Login.add(btnNewButton);
        
		
		JPanel Main = new JPanel();
		Main.setBounds(0, 0, 648, 850);
		frame.getContentPane().add(Main);
		Main.setLayout(null);
		
		
		JPanel Join = new JPanel();
		Join.setBounds(0, 0, 648, 850);
		frame.getContentPane().add(Join);
	}
}
