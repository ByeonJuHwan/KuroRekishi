package kurorekishimain;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class JoinMember extends JFrame {
	private static final String[] locations = {"서울","경기","충북","충남","전북","전남","경북","경남","강원도","제주"};
	
	private Component parent;
	
	private JPanel JoinPanel;
	private JTextField inputId;
	private JTextField inputPw;
	private JTextField inputName;

	/**
	 * Launch the application.
	 */
	public static void newJoinMember(Component parent) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				JoinMember frame = new JoinMember(parent);
				frame.setVisible(true);
			}
		});
	}
	
	/**
	 * Create the frame.
	 * @param parent 
	 * @param listener 
	 */
	
	public JoinMember(Component parent) {
		this.parent = parent;
		initialize();
	}
	
	
	/**
	 * Create the frame.
	 * @return 
	 */
	public void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 664, 800);
		JoinPanel = new JPanel();
		JoinPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(JoinPanel);
		JoinPanel.setLayout(null);
		
		JLabel JoinId = new JLabel("아이디");
		JoinId.setHorizontalAlignment(SwingConstants.CENTER);
		JoinId.setFont(new Font("굴림체", Font.BOLD, 18));
		JoinId.setBounds(0, 0, 137, 66);
		JoinPanel.add(JoinId);
		
		JLabel JoinPw = new JLabel("비밀번호");
		JoinPw.setHorizontalAlignment(SwingConstants.CENTER);
		JoinPw.setFont(new Font("굴림체", Font.BOLD, 18));
		JoinPw.setBounds(0, 76, 137, 66);
		JoinPanel.add(JoinPw);
		
		inputId = new JTextField();
		inputId.setFont(new Font("D2Coding", Font.BOLD, 17));
		inputId.setColumns(10);
		inputId.setBounds(149, 10, 313, 53);
		JoinPanel.add(inputId);
		
		inputPw = new JTextField();
		inputPw.setFont(new Font("D2Coding", Font.BOLD, 17));
		inputPw.setColumns(10);
		inputPw.setBounds(149, 76, 313, 53);
		JoinPanel.add(inputPw);
		
		JButton btnCheckId = new JButton("중복확인");
		btnCheckId.setFont(new Font("굴림", Font.BOLD, 16));
		btnCheckId.setBounds(474, 10, 124, 58);
		JoinPanel.add(btnCheckId);
		
		JLabel JoinName = new JLabel("이름");
		JoinName.setHorizontalAlignment(SwingConstants.CENTER);
		JoinName.setFont(new Font("굴림체", Font.BOLD, 18));
		JoinName.setBounds(0, 152, 137, 66);
		JoinPanel.add(JoinName);
		
		inputName = new JTextField();
		inputName.setFont(new Font("D2Coding", Font.BOLD, 17));
		inputName.setColumns(10);
		inputName.setBounds(149, 152, 313, 53);
		JoinPanel.add(inputName);
		
		JLabel JoinName_1 = new JLabel("주소");
		JoinName_1.setHorizontalAlignment(SwingConstants.CENTER);
		JoinName_1.setFont(new Font("굴림체", Font.BOLD, 18));
		JoinName_1.setBounds(0, 228, 137, 66);
		JoinPanel.add(JoinName_1);
		
		JComboBox LocationcomboBox = new JComboBox(locations);
		LocationcomboBox.setFont(new Font("D2Coding", Font.BOLD, 17));
		LocationcomboBox.setBounds(149, 235, 313, 53);
		JoinPanel.add(LocationcomboBox);
		
		JLabel JoinExperience = new JLabel("흑역사");
		JoinExperience.setHorizontalAlignment(SwingConstants.CENTER);
		JoinExperience.setFont(new Font("굴림체", Font.BOLD, 18));
		JoinExperience.setBounds(0, 304, 137, 66);
		JoinPanel.add(JoinExperience);
		
		JTextArea textAreaExperience = new JTextArea();
		textAreaExperience.setFont(new Font("D2Coding", Font.BOLD, 17));
		textAreaExperience.setBounds(149, 326, 313, 264);
		JoinPanel.add(textAreaExperience);
		
		JButton btnMemberJoin = new JButton("사진넣기");
		btnMemberJoin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PhotoFrame.newPhotoFrame();
			}
		});
		btnMemberJoin.setFont(new Font("굴림", Font.BOLD, 16));
		btnMemberJoin.setBounds(149, 654, 124, 58);
		JoinPanel.add(btnMemberJoin);
		
		JButton btnBack = new JButton("뒤로가기");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnBack.setFont(new Font("굴림", Font.BOLD, 16));
		btnBack.setBounds(338, 654, 124, 58);
		JoinPanel.add(btnBack);
		
		JLabel lblNewLabel = new JLabel("자신의 흑역사를 간단하게 적어주세요!!");
		lblNewLabel.setFont(new Font("D2Coding", Font.BOLD, 12));
		lblNewLabel.setBounds(149, 600, 313, 27);
		JoinPanel.add(lblNewLabel);
		
		
		
	}
}
