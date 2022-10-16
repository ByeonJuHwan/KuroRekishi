package kurorekishimain;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
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

import member.Member;
import member.MemberDaoImpl;
import server.MultiServerThread;
import server.ServerMain;


public class KuroRekishiMain {
	private static final BufferedImage[] images =  new BufferedImage[5];
	public static Map<String,String> userInfo = new  HashMap<>(); // 로그인시 로그인한 아이디, 이름을 다른 클래스에서도 쓰기위해서
	public static String idKey = null; // idKey를 통해서 map에 저장한 value값을 가져온다.
	public static ArrayList<MultiServerThread> list;
	public static ArrayList<String> searchOptoinNameList;
	
	
	int index=0;
	boolean checkLogined;
	private String sex; // 어떤 성별이 로그인 되느냐에 따라 남자면 여자사진, 여자면 남자사진이 띄워짐.
	public static String name; // 랜덤으로 받아오는 이름 -- 이 이름에 따라 메인화면에 띄워지는 사진이 바뀐다.
	
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
	private JLabel lblBack;
	private JLabel lblButtonBack;
	
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
		frame.setBounds(200, 200, 664, 800);
		frame.setTitle("쿠로렉시");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
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
        textId.setFont(new Font("D2Coding", Font.BOLD, 15));
        textId.setBounds(187, 634, 260, 41);
        Login.add(textId);
        textId.setColumns(10);
        
        passwordField = new JPasswordField();
        passwordField.setFont(new Font("D2Coding", Font.BOLD, 15));
        passwordField.setBounds(187, 685, 260, 41);
        Login.add(passwordField);
        
        btnLogin = new JButton("로그인");
        btnLogin.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	   Login();
        	   checkThumb();
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
        lblImage.setBounds(0, 0, 658, 766);
        Login.add(lblImage);
        
        
        
	    //------------------------------ 로그인창
        
        Main = new JPanel();
        Main.setBounds(0, 0, 648, 761);
        frame.getContentPane().add(Main);
        Main.setLayout(null);
        Main.setVisible(false);
        
        
        
        
        JPanel mainButtonPanel = new JPanel();
        mainButtonPanel.setBounds(0, 701, 648, 60);
        Main.add(mainButtonPanel);
        mainButtonPanel.setLayout(null);
        
        JButton btnProfile = new JButton("프로필");
        btnProfile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 프로필 수정/업데이트
                String id = getLoginedId();
                System.out.println(id);
                UpdateFrame.newUpdateFrame(frame,id);
            }
        });
        btnProfile.setFont(new Font("D2Coding", Font.BOLD, 16));
        btnProfile.setBounds(420, 5, 122, 45);
        mainButtonPanel.add(btnProfile);
        
        JButton searchOption = new JButton("검색 설정");
        searchOption.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO 나이,키, mbti로 검색 
                System.out.println(sex); // 로그인한 사람의 성
                SearchMemberOptionFrame.newSearchMemberOptionFrame(frame,sex);
            }
        });
        searchOption.setBounds(70, 5, 122, 45);
        searchOption.setFont(new Font("D2Coding", Font.BOLD, 16));
        mainButtonPanel.add(searchOption);
        
        
        
        JButton btnNewChat = new JButton("데이트추천");
        btnNewChat.setBounds(250, 5, 122, 45);
        mainButtonPanel.add(btnNewChat);
        btnNewChat.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DateFrame.newDateFrame(frame);
            }
        });
        btnNewChat.setFont(new Font("D2Coding", Font.BOLD, 16));
        
        btnNotgood = new JButton("별로에요");
        btnNotgood.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		showDiffrentSexImages();
        		//TODO 하루 5번만 누를수있게 변경
        	}
        });
        btnNotgood.setFont(new Font("D2Coding", Font.BOLD, 16));
        btnNotgood.setBounds(141, 651, 122, 45);
        Main.add(btnNotgood);
        
        JButton btnGood = new JButton("좋아요");
        btnGood.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	giveThumb();
            	JOptionPane.showMessageDialog(frame, "상대방의 응답을 기다려보세요!");
            	ChatFrame.newChatFrame(frame);
            }
        });
        btnGood.setFont(new Font("D2Coding", Font.BOLD, 16));
        btnGood.setBounds(380, 651, 122, 45);
        Main.add(btnGood);
        
        
        JButton btnGoPreImage = new JButton("<");
        btnGoPreImage.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		goBackImage();
        	}
        });
        btnGoPreImage.setBounds(12, 264, 41, 45);
        Main.add(btnGoPreImage);
        
        JButton btnGoNextImage = new JButton(">");
        btnGoNextImage.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	    goNextImage();
        	}
        });
        btnGoNextImage.setBounds(595, 264, 41, 45);
        Main.add(btnGoNextImage);
        
        lblMemberImages = new JLabel(new ImageIcon());
        lblMemberImages.setFont(new Font("궁서체", Font.BOLD, 14));
        lblMemberImages.setBackground(new Color(240, 240, 240));
        lblMemberImages.setBounds(0, 0, 648, 641);
        Main.add(lblMemberImages);
        
        
        
//        lblBack = new JLabel(new ImageIcon("backgroundimage/배경.png"));
//        lblBack.setBounds(0, 0, 646, 766);
//        Main.add(lblBack);
		
     // ---------------------------- 메인창작업
		
	} // end initialize()


    private String setName() {
        
        return null;
    }

    private void checkThumb() {
		Member member = dao.checkThumb(idKey);
		System.out.println(member.getName());
		if(member.getName().equals(member.getGavedThumbName())) {
			int result = JOptionPane.showConfirmDialog(frame, member.getGivedThumbName() + " 님 께서 채팅을 원합니다.", "알림", JOptionPane.YES_NO_OPTION);
			if(result == JOptionPane.YES_OPTION) {
				ChatFrame.newChatFrame(frame);
			}
		}
	}

	private void giveThumb() {
		int result = dao.giveThumb(userInfo.get(idKey),name);
		if(result == 1) {
			JOptionPane.showMessageDialog(frame, name + " 님에게 좋아요를 보냈습니다.");
		}else {
			JOptionPane.showMessageDialog(frame, "좋아요를 보낼수 없습니다.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	// 사진을 다음장으로 이동
	private void goNextImage() {
        if(index<4) {
            index++;
        }else {
            index=0;
        }
        goNext();
	}
	
	private void goNext() {
	    String imageLink = null;
	    if(sex.equals("여자")) { // 여자가 로그인했을때
            imageLink = "usersImageMale/"+name+"/"+name+index;
            System.out.println(imageLink);
            lblMemberImages.setIcon(new ImageIcon(imageLink));
            chageImage();
        }else { // 남자가 로그인했을때
            imageLink = "usersImageFeMale/"+name+"/"+name+index;
            System.out.println(imageLink);
            lblMemberImages.setIcon(new ImageIcon(imageLink));
            chageImage();
        }
        
    }

    // 사진을 이전장을 이동
	private void goBackImage() {
	    if(index>0) {
	        index--;
	    }else {
	        index=4;
	    }
		goBack();
	}

	private void goBack() {
	    String imageLink = null;
        if(sex.equals("여자")) { // 여자가 로그인했을때
            imageLink = "usersImageMale/"+name+"/"+name+index;
            System.out.println(imageLink);
            lblMemberImages.setIcon(new ImageIcon(imageLink));
            chageImage();
        }else { // 남자가 로그인했을때
            imageLink = "usersImageFeMale/"+name+"/"+name+index;
            System.out.println(imageLink);
            lblMemberImages.setIcon(new ImageIcon(imageLink));
            chageImage();
        }
    }

    private void showDiffrentSexImages() {
		name = null;
		String imageLink = null;
		if(sex.equals("여자")) { // 여자가 로그인했을때
			name = dao.pickUserRamdom("남자");
			imageLink = "usersImageMale/"+name+"/"+name+0;
			System.out.println(imageLink);
			lblMemberImages.setIcon(new ImageIcon(imageLink));
			chageImage();
		}else { // 남자가 로그인했을때
			name = dao.pickUserRamdom("여자");
			imageLink = "usersImageFeMale/"+name+"/"+name+0;
			System.out.println(imageLink);
			lblMemberImages.setIcon(new ImageIcon(imageLink));
			chageImage();
		}
    }
	
	private void chageImage() {
		lblMemberImages.setBounds(0, 0, 648, 641);
        Main.add(lblMemberImages);
	}

    private  String getLoginedId() {
	    String userId = null;
	    for(String id : userInfo.keySet()) {
	        userId = id;
       }
        return userId;
    }

    private void Login() {
        // 입력창에서 아이디, 비밀번호를 받는다.
		idKey = textId.getText();
		String pw = String.valueOf(passwordField.getPassword());
		System.out.println("ID : " + idKey);
		System.out.println("PW : " + pw);
		
		// 아이디 또는 비밀번호를 입력하지 않았을경우
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
		
		// 로그인 메서드로 유저가 있는지 없는지 검사.
		checkLogined = dao.Login(idKey, pw);
		
		// 회원인 경우에는 map에 id,이름을 저장하고 메인화면 입장.
		if(checkLogined) {
			String name =findName(idKey);
			userInfo.put(idKey, name);
			System.out.println("MAP에 저장된 이름 = " +  userInfo.get(idKey));
			JOptionPane.showMessageDialog(frame, "새짝을 찾아봐요~!!", "환영", JOptionPane.PLAIN_MESSAGE);
			
			// 아이디에따른 성별도 가져옴
			sex = dao.loginedUserSex(idKey);
			
			// 기본이미지 설정
			showDiffrentSexImages();
			
			Login.setVisible(false);
			Main.setVisible(true);
		}else {
		    // 회원이 아닌경우 회원가입 유도.
			JOptionPane.showMessageDialog(frame, "등록된 아이디가 없습니다.", "Warning", JOptionPane.WARNING_MESSAGE);
		}
	
    }

    private String findName(String id) {
		String name = dao.fineName(id);
		return name;
	}
}
