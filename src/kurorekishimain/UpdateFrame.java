package kurorekishimain;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import member.MemberDaoImpl;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UpdateFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private JTextField textField_1;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private MemberDaoImpl dao;

	/**
	 * Launch the application.
	 */
	public static void newUpdateFrame() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
					UpdateFrame frame = new UpdateFrame();
					frame.setVisible(true);
			}
		});
	}
	
	public UpdateFrame() {
		dao = MemberDaoImpl.getInstance();
		initialize();
	}

	/**
	 * Create the frame.
	 */
	public void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 664, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel JoinId = new JLabel("아이디");
		JoinId.setHorizontalAlignment(SwingConstants.CENTER);
		JoinId.setFont(new Font("굴림체", Font.BOLD, 18));
		JoinId.setBounds(0, 0, 137, 66);
		contentPane.add(JoinId);
		
		textField = new JTextField();
		textField.setFont(new Font("D2Coding", Font.BOLD, 17));
		textField.setColumns(10);
		textField.setBounds(149, 10, 313, 53);
		contentPane.add(textField);
		
		JLabel JoinPw = new JLabel("비밀번호");
		JoinPw.setHorizontalAlignment(SwingConstants.CENTER);
		JoinPw.setFont(new Font("굴림체", Font.BOLD, 18));
		JoinPw.setBounds(0, 76, 137, 66);
		contentPane.add(JoinPw);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(149, 89, 313, 53);
		contentPane.add(passwordField);
		
		JLabel JoinName = new JLabel("이름");
		JoinName.setHorizontalAlignment(SwingConstants.CENTER);
		JoinName.setFont(new Font("굴림체", Font.BOLD, 18));
		JoinName.setBounds(0, 152, 137, 66);
		contentPane.add(JoinName);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("D2Coding", Font.BOLD, 17));
		textField_1.setColumns(10);
		textField_1.setBounds(149, 165, 313, 53);
		contentPane.add(textField_1);
		
		JLabel JoinName_1 = new JLabel("주소");
		JoinName_1.setHorizontalAlignment(SwingConstants.CENTER);
		JoinName_1.setFont(new Font("굴림체", Font.BOLD, 18));
		JoinName_1.setBounds(0, 228, 137, 66);
		contentPane.add(JoinName_1);
		
		JComboBox LocationcomboBox = new JComboBox(new Object[]{});
		LocationcomboBox.setFont(new Font("D2Coding", Font.BOLD, 17));
		LocationcomboBox.setBounds(149, 228, 313, 53);
		contentPane.add(LocationcomboBox);
		
		JLabel JoinSex = new JLabel("성별");
		JoinSex.setHorizontalAlignment(SwingConstants.CENTER);
		JoinSex.setFont(new Font("굴림체", Font.BOLD, 18));
		JoinSex.setBounds(0, 304, 137, 66);
		contentPane.add(JoinSex);
		
		JRadioButton radioButtonMale = new JRadioButton("남자");
		buttonGroup.add(radioButtonMale);
		radioButtonMale.setFont(new Font("D2Coding", Font.BOLD, 15));
		radioButtonMale.setBounds(149, 327, 121, 23);
		contentPane.add(radioButtonMale);
		
		JRadioButton radioButtonFemale = new JRadioButton("여자");
		buttonGroup.add(radioButtonFemale);
		radioButtonFemale.setFont(new Font("D2Coding", Font.BOLD, 15));
		radioButtonFemale.setBounds(341, 327, 121, 23);
		contentPane.add(radioButtonFemale);
		
		JLabel JoinExperience = new JLabel("흑역사");
		JoinExperience.setHorizontalAlignment(SwingConstants.CENTER);
		JoinExperience.setFont(new Font("굴림체", Font.BOLD, 18));
		JoinExperience.setBounds(0, 380, 137, 66);
		contentPane.add(JoinExperience);
		
		JTextArea textAreaExperience = new JTextArea();
		textAreaExperience.setFont(new Font("D2Coding", Font.BOLD, 17));
		textAreaExperience.setBounds(149, 380, 313, 264);
		contentPane.add(textAreaExperience);
		
		JLabel lblNewLabel = new JLabel("자신의 흑역사를 간단하게 적어주세요!!");
		lblNewLabel.setFont(new Font("D2Coding", Font.BOLD, 12));
		lblNewLabel.setBounds(149, 654, 313, 27);
		contentPane.add(lblNewLabel);
		
		JButton btnBack = new JButton("뒤로가기");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnBack.setFont(new Font("굴림", Font.BOLD, 16));
		btnBack.setBounds(93, 691, 124, 58);
		contentPane.add(btnBack);
		
		JButton btnUpdateImages = new JButton("사진변경");
		btnUpdateImages.setFont(new Font("굴림", Font.BOLD, 16));
		btnUpdateImages.setBounds(229, 691, 124, 58);
		contentPane.add(btnUpdateImages);
		
		JButton btnComplete = new JButton("완료");
		btnComplete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO dao.update로 프로필 수정
				
			}
		});
		btnComplete.setFont(new Font("굴림", Font.BOLD, 16));
		btnComplete.setBounds(365, 691, 124, 58);
		contentPane.add(btnComplete);
		
		JButton btnCheckId = new JButton("중복확인");
		btnCheckId.setFont(new Font("굴림", Font.BOLD, 16));
		btnCheckId.setBounds(495, 10, 124, 58);
		contentPane.add(btnCheckId);
	}
}
