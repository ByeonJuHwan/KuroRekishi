package kurorekishimain;

import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import member.Member;
import member.MemberDaoImpl;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

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
import java.util.List;
import java.awt.event.ActionEvent;

public class UpdateFrame extends JFrame {

	private JPanel contentPane;
	private JTextField updateIdField;
	private JPasswordField updatePasswordField;
	private JTextField updateNameField;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private MemberDaoImpl dao;
	private Component parent;
	private String loginedId;
	private JTextArea updateTextAreaExperience;
	private JRadioButton radioButtonMale;
	private JRadioButton radioButtonFemale;
	private JComboBox updateLocationcomboBox;
	/**
	 * Launch the application.
	 * @param loginedId 
	 * @param frame 
	 */
	public static void newUpdateFrame(Component parent, String loginedId) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
					UpdateFrame frame = new UpdateFrame(parent,loginedId);
					frame.setVisible(true);
			}
		});
	}
	
	public UpdateFrame(Component parent, String loginedId) {
	    this.parent = parent;
	    this.loginedId = loginedId;
		dao = MemberDaoImpl.getInstance();
		initialize();
		inserInfo(); // 현재 로그인하고 있는 회원의 정보를 상세 정보창에 입력.
	}

	private void inserInfo() {
       List<Member> list = dao.selectById(loginedId);
       for(Member m : list) {
           updateIdField.setText(m.getId());
           updatePasswordField.setText(m.getPw());
           updateNameField.setText(m.getName());
           updateTextAreaExperience.setText(m.getHistory());
           // 성별
           if(m.getSex().equals("남자")) {
               radioButtonMale.setSelected(true);
           }else {
               radioButtonFemale.setSelected(true);
           }
           // 주소
           if(m.getLocation().equals("서울")) {
               updateLocationcomboBox.setSelectedItem(JoinMember.locations[0]);
           }else if(m.getLocation().equals("경기")) {
               updateLocationcomboBox.setSelectedItem(JoinMember.locations[1]);
           }else if(m.getLocation().equals("충북")) {
               updateLocationcomboBox.setSelectedItem(JoinMember.locations[2]);
           }else if(m.getLocation().equals("충남")) {
               updateLocationcomboBox.setSelectedItem(JoinMember.locations[3]);
           }else if(m.getLocation().equals("전북")) {
               updateLocationcomboBox.setSelectedItem(JoinMember.locations[4]);
           }else if(m.getLocation().equals("전남")) {
               updateLocationcomboBox.setSelectedItem(JoinMember.locations[5]);
           }else if(m.getLocation().equals("경북")) {
               updateLocationcomboBox.setSelectedItem(JoinMember.locations[6]);
           }else if(m.getLocation().equals("경남")) {
               updateLocationcomboBox.setSelectedItem(JoinMember.locations[7]);
           }else if(m.getLocation().equals("강원도")) {
               updateLocationcomboBox.setSelectedItem(JoinMember.locations[8]);
           }else {
               updateLocationcomboBox.setSelectedItem(JoinMember.locations[9]);
           }
       }
    }

    

    /**
	 * Create the frame.
	 */
	public void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		int x = parent.getX();
		int y = parent.getY();
		setBounds(x, y, 664, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setTitle("프로필 수정");
		setResizable(false);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel JoinId = new JLabel("아이디");
		JoinId.setHorizontalAlignment(SwingConstants.CENTER);
		JoinId.setFont(new Font("굴림체", Font.BOLD, 18));
		JoinId.setBounds(0, 0, 137, 66);
		contentPane.add(JoinId);
		
		updateIdField = new JTextField();
		updateIdField.setFont(new Font("D2Coding", Font.BOLD, 17));
		updateIdField.setColumns(10);
		updateIdField.setBounds(149, 10, 313, 53);
		updateIdField.setEditable(false);
		contentPane.add(updateIdField);
		
		JLabel updatePwField = new JLabel("비밀번호");
		updatePwField.setHorizontalAlignment(SwingConstants.CENTER);
		updatePwField.setFont(new Font("굴림체", Font.BOLD, 18));
		updatePwField.setBounds(0, 76, 137, 66);
		contentPane.add(updatePwField);
		
		updatePasswordField = new JPasswordField();
		updatePasswordField.setBounds(149, 89, 313, 53);
		contentPane.add(updatePasswordField);
		
		JLabel JoinName = new JLabel("이름");
		JoinName.setHorizontalAlignment(SwingConstants.CENTER);
		JoinName.setFont(new Font("굴림체", Font.BOLD, 18));
		JoinName.setBounds(0, 152, 137, 66);
		contentPane.add(JoinName);
		
		updateNameField = new JTextField();
		updateNameField.setFont(new Font("D2Coding", Font.BOLD, 17));
		updateNameField.setColumns(10);
		updateNameField.setBounds(149, 165, 313, 53);
		contentPane.add(updateNameField);
		
		JLabel JoinName_1 = new JLabel("주소");
		JoinName_1.setHorizontalAlignment(SwingConstants.CENTER);
		JoinName_1.setFont(new Font("굴림체", Font.BOLD, 18));
		JoinName_1.setBounds(0, 228, 137, 66);
		contentPane.add(JoinName_1);
		
		updateLocationcomboBox = new JComboBox(JoinMember.locations);
		updateLocationcomboBox.setFont(new Font("D2Coding", Font.BOLD, 17));
		updateLocationcomboBox.setBounds(149, 228, 313, 53);
		contentPane.add(updateLocationcomboBox);
		
		JLabel JoinSex = new JLabel("성별");
		JoinSex.setHorizontalAlignment(SwingConstants.CENTER);
		JoinSex.setFont(new Font("굴림체", Font.BOLD, 18));
		JoinSex.setBounds(0, 304, 137, 66);
		contentPane.add(JoinSex);
		
		radioButtonMale = new JRadioButton("남자");
		buttonGroup.add(radioButtonMale);
		radioButtonMale.setFont(new Font("D2Coding", Font.BOLD, 15));
		radioButtonMale.setBounds(149, 327, 121, 23);
		contentPane.add(radioButtonMale);
		
		radioButtonFemale = new JRadioButton("여자");
		buttonGroup.add(radioButtonFemale);
		radioButtonFemale.setFont(new Font("D2Coding", Font.BOLD, 15));
		radioButtonFemale.setBounds(341, 327, 121, 23);
		contentPane.add(radioButtonFemale);
		
		JLabel JoinExperience = new JLabel("흑역사");
		JoinExperience.setHorizontalAlignment(SwingConstants.CENTER);
		JoinExperience.setFont(new Font("굴림체", Font.BOLD, 18));
		JoinExperience.setBounds(0, 380, 137, 66);
		contentPane.add(JoinExperience);
		
		updateTextAreaExperience = new JTextArea();
		updateTextAreaExperience.setFont(new Font("D2Coding", Font.BOLD, 17));
		updateTextAreaExperience.setBounds(149, 380, 313, 264);
		contentPane.add(updateTextAreaExperience);
		
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
				updateProfile();
			}
		});
		btnComplete.setFont(new Font("굴림", Font.BOLD, 16));
		btnComplete.setBounds(365, 691, 124, 58);
		contentPane.add(btnComplete);
	}

    private void updateProfile() {
        String id = updateIdField.getText();
        String pw = String.valueOf(updatePasswordField.getPassword());
        String name = updateNameField.getText();
        String loc = (String) updateLocationcomboBox.getSelectedItem();
        String sex = null;
        if(radioButtonMale.isSelected()) {
            sex = radioButtonMale.getText();
        }else {
            sex = radioButtonFemale.getText();
        }
        String history = updateTextAreaExperience.getText();
        
        Member member  = new Member(id,pw,name,loc,sex,history);
        System.out.println(id);
        System.out.println(pw);
        System.out.println(name);
        System.out.println(loc);
        System.out.println(sex);
        System.out.println(history);
        
        int result = dao.updateMember(member);
        
        if(result == 1) {
        	dispose();
            JOptionPane.showMessageDialog(parent, "수정 완료");
        }else {
            JOptionPane.showMessageDialog(parent, "수정 불가");
        }
    }
}
