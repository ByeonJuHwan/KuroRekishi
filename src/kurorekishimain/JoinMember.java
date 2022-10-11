package kurorekishimain;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import member.Member;
import member.MemberDaoImpl;

import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import javax.swing.JPasswordField;
import javax.swing.JRadioButtonMenuItem;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;

public class JoinMember extends JFrame {
	public static final String[] locations = {"서울","경기","충북","충남","전북","전남","경북","경남","강원도","제주"};
	public static final BufferedImage[] images =  new BufferedImage[5];
	
	int index=0;
	int clearCheckId=0;
	
	private Component parent;
	private MemberDaoImpl dao;
	
	private JPanel JoinPanel;
	private JTextField inputId;
	private JTextField inputName;
	private JPasswordField inputPw;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton radioButtonMale;
	private JRadioButton radioButtonFemale;
	private JComboBox LocationcomboBox;
	private JTextArea textAreaExperience;

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
		dao = MemberDaoImpl.getInstance();
		initialize();
	}
	
	
	/**
	 * Create the frame.
	 * @return 
	 */
	public void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		int x = parent.getX(); // 부모창 의 X 좌표
        int y = parent.getY(); // 부모창 의 Y 좌표
		setBounds(x, y, 664, 800);
		JoinPanel = new JPanel();
		JoinPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setTitle("회원가입");
		setResizable(false);
		

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
		
		JButton btnCheckId = new JButton("중복확인");
		btnCheckId.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    clearCheckId = idIsExist();
			}
		});
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
		
		LocationcomboBox = new JComboBox(locations);
		LocationcomboBox.setFont(new Font("D2Coding", Font.BOLD, 17));
		LocationcomboBox.setBounds(149, 235, 313, 53);
		JoinPanel.add(LocationcomboBox);
		
		JLabel JoinExperience = new JLabel("흑역사");
		JoinExperience.setHorizontalAlignment(SwingConstants.CENTER);
		JoinExperience.setFont(new Font("굴림체", Font.BOLD, 18));
		JoinExperience.setBounds(0, 382, 137, 66);
		JoinPanel.add(JoinExperience);
		
		textAreaExperience = new JTextArea();
		textAreaExperience.setFont(new Font("D2Coding", Font.BOLD, 17));
		textAreaExperience.setBounds(149, 382, 313, 264);
		JoinPanel.add(textAreaExperience);
		
		JButton btnMemberJoin = new JButton("사진넣기");
		btnMemberJoin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertImages();
				String sex = sexCheck();
				PhotoFrame.newPhotoFrame(inputName.getText(),sex);
			}
		});
		btnMemberJoin.setFont(new Font("굴림", Font.BOLD, 16));
		btnMemberJoin.setBounds(244, 693, 124, 58);
		JoinPanel.add(btnMemberJoin);
		
		JButton btnBack = new JButton("뒤로가기");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnBack.setFont(new Font("굴림", Font.BOLD, 16));
		btnBack.setBounds(108, 693, 124, 58);
		JoinPanel.add(btnBack);
		
		JLabel lblNewLabel = new JLabel("자신의 흑역사를 간단하게 적어주세요!!");
		lblNewLabel.setFont(new Font("D2Coding", Font.BOLD, 12));
		lblNewLabel.setBounds(149, 656, 313, 27);
		JoinPanel.add(lblNewLabel);
		
		JButton btnComplete = new JButton("완료");
		btnComplete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkFindid(); // 중복확인을 하고 회원가입시 정상적으로 회원가입성공, 중복확인을 하지않을시 경고메세
			    if(index == 0) {
		            JOptionPane.showMessageDialog(null, "사진을 적어도 한장 넣어주세요..", "알림", JOptionPane.WARNING_MESSAGE);
		            return;
		        }
				completeInsertMember();
			}
		});
		btnComplete.setFont(new Font("굴림", Font.BOLD, 16));
		btnComplete.setBounds(380, 693, 124, 58);
		JoinPanel.add(btnComplete);
		
		JLabel JoinSex = new JLabel("성별");
		JoinSex.setHorizontalAlignment(SwingConstants.CENTER);
		JoinSex.setFont(new Font("굴림체", Font.BOLD, 18));
		JoinSex.setBounds(0, 304, 137, 66);
		JoinPanel.add(JoinSex);
		
		inputPw = new JPasswordField();
		inputPw.setBounds(149, 76, 313, 53);
		JoinPanel.add(inputPw);
		
		radioButtonMale = new JRadioButton("남자");
		radioButtonMale.setFont(new Font("D2Coding", Font.BOLD, 15));
		buttonGroup.add(radioButtonMale);
		radioButtonMale.setBounds(149, 327, 121, 23);
		JoinPanel.add(radioButtonMale);
		
		radioButtonFemale = new JRadioButton("여자");
		radioButtonFemale.setFont(new Font("D2Coding", Font.BOLD, 15));
		buttonGroup.add(radioButtonFemale);
		radioButtonFemale.setBounds(341, 327, 121, 23);
		JoinPanel.add(radioButtonFemale);
		
		
		
	}
	// photoFrame으로 가는 성별에 따라서 선택되는 폴더가 다르기때문
	private String sexCheck() {
		String sex = null;
		if(radioButtonFemale.isSelected()) {
			sex = radioButtonFemale.getText();
		}else if (radioButtonMale.isSelected()) {
			sex = radioButtonMale.getText();
		}
		return sex;
	}

	private void checkFindid() {
        if(clearCheckId != 1) {
            JOptionPane.showMessageDialog(JoinPanel, "아이디 중복확인을 해주세요.", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
    }

    // 회원 가입완료 최종 DB에 넣는 작업.
	private void completeInsertMember() {
        try {
            String id = inputId.getText();
            String pw = String.valueOf(inputPw.getPassword());
            String name = inputName.getText();
            String sex = null;
            if(!radioButtonMale.isSelected() && !radioButtonFemale.isSelected()) {
                JOptionPane.showMessageDialog(JoinPanel, "성별을 선택해주세요.", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }else if(radioButtonMale.isSelected()) {
                sex = radioButtonMale.getText();
            }else {
                sex = radioButtonFemale.getText();
            }
            String location = (String) LocationcomboBox.getSelectedItem();
            String history = textAreaExperience.getText();
	    
            Member member  = new Member(id,pw,name,sex,location,history);
	    
            int result = dao.addmember(member);
	    
            if(result == 1) {
                JOptionPane.showMessageDialog(JoinPanel, "회원가입이 완료되었습니다.", "환영",JOptionPane.PLAIN_MESSAGE);
                JoinPanel.setVisible(false);
            }else {
                JOptionPane.showMessageDialog(JoinPanel, "입력이 안된 부분이 있습니다.", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        }catch(Exception e) {
            e.printStackTrace();
            JOptionPane.showConfirmDialog(JoinPanel, "누락된 부분없이 입력해주세요.", "Warning", JOptionPane.WARNING_MESSAGE);
        }
        
    }

    private int idIsExist() {
		boolean result = dao.findIdExist(inputId.getText());
		if(inputId.getText().equals(null) || inputId.getText().equals("")) {
			JOptionPane.showMessageDialog(JoinPanel, "아이디를 입력해주세요!", "Warnig", JOptionPane.WARNING_MESSAGE); // 공백을 입력한 경우
		}else if(result == true) { 
			JOptionPane.showMessageDialog(JoinPanel, "이미 사용중인 아이디입니다.", "중복", JOptionPane.WARNING_MESSAGE); // 중복되는 아이디를 입력한경우
			inputId.setText("");
		}else {
			JOptionPane.showMessageDialog(JoinPanel, "사용할 수 있는 아이디입니다.", "사용가능", JOptionPane.PLAIN_MESSAGE); // 사용가능한 아이디
			clearCheckId=1;
		}
        return clearCheckId;
	}

	private void insertImages() {
		
		// 5장까지 저장 인지 확인.
        if(index == 5) {
            JOptionPane.showMessageDialog(null, "5장 다 넣으셨습니다.", "경고", JOptionPane.WARNING_MESSAGE);
            index=0;
            return;
        }
	    
        // 5장보다 아래라면 더 추가 하도록 만듬.
	    JFileChooser jFileChooser = new JFileChooser();
		
		int result = jFileChooser.showSaveDialog(null);
		
		// 회원가입 페이지에서 등록한 이름으로 이미지들을 저장할 파일 생성.
		if(radioButtonMale.isSelected()) { // 남자가 회원가입한경우 남자 파일생성.
			File newFolder = new File("usersImageMale",inputName.getText());
			if(!newFolder.exists()) {
			    newFolder.mkdir();
			}
		
			File userImages  = new File("usersImageMale\\"+inputName.getText(), inputName.getText()+index);
	        if(!userImages.exists()) {
	            userImages.mkdir();
	        }
	        
			if(result == JFileChooser.APPROVE_OPTION) {
				File file = jFileChooser.getSelectedFile();
				
				try {
	                BufferedImage image = ImageIO.read(file);
	                images[index]=image;
	                ImageIO.write(image, "png", userImages);
	                index++;
	                System.out.println("success");
	                JOptionPane.showMessageDialog(parent, "현재("+(index)+"/5) 장", "확인", JOptionPane.PLAIN_MESSAGE);
	            } catch (Exception e) {
	                e.printStackTrace();
	            }	
			}
			
		}else if(radioButtonFemale.isSelected()) { // 여자가 회원가읍한경우 여자 파일 생성.
			File newFolder = new File("usersImageFemale",inputName.getText());
			if(!newFolder.exists()) {
			    newFolder.mkdir();
			}
			File userImages  = new File("usersImageFemale\\"+inputName.getText(), inputName.getText()+index);
	        if(!userImages.exists()) {
	            userImages.mkdir();
	        }
	        
			if(result == JFileChooser.APPROVE_OPTION) {
				File file = jFileChooser.getSelectedFile();
				
				try {
	                BufferedImage image = ImageIO.read(file);
	                images[index]=image;
	                ImageIO.write(image, "png", userImages);
	                index++;
	                System.out.println("success");
	                JOptionPane.showMessageDialog(parent, "현재("+(index)+"/5) 장", "확인", JOptionPane.PLAIN_MESSAGE);
	            } catch (Exception e) {
	                e.printStackTrace();
	            }	
			}
		}
	}
}
