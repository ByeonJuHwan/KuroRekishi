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
import java.util.List;
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

import chat.ChatDaoImpl;
import kurorekishimain.SearchMemberOptionFrame.sendSearchListener;
import member.Member;
import member.MemberDaoImpl;
import point.Point;
import point.PointDaoImpl;
import server.MultiServerThread;
import server.ServerMain;
import javax.swing.JComboBox;


public class KuroRekishiMain implements sendSearchListener{
	private static final BufferedImage[] images =  new BufferedImage[5];
	public static Map<String,String> userInfo = new  HashMap<>(); // 로그인시 로그인한 아이디, 이름을 다른 클래스에서도 쓰기위해서
	public static String idKey = null; // idKey를 통해서 map에 저장한 value값을 가져온다.
	public static ArrayList<MultiServerThread> list;
	private ArrayList<String> searchNames;
	private static final String[] stars = {"✿","✿✿","✿✿✿","✿✿✿✿","✿✿✿✿✿"};
	
	int index=0;
	int nameIndex = 0;
	int likeIndex=0;
	boolean checkLogined;
	private String sex; // 어떤 성별이 로그인 되느냐에 따라 남자면 여자사진, 여자면 남자사진이 띄워짐.
	public static String name; // 랜덤으로 받아오는 이름 -- 이 이름에 따라 메인화면에 띄워지는 사진이 바뀐다.
	
	private MemberDaoImpl dao;
	private ChatDaoImpl chatDao;
	private PointDaoImpl pointDao;
	
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
	private JLabel lblHistory;
	private JButton btnSendStar;
	private JComboBox starComboBox;
	
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
	    chatDao = ChatDaoImpl.getInstance();
		dao = MemberDaoImpl.getInstance();
		pointDao = PointDaoImpl.getInstance();
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
		
		
		

        
        
	    //------------------------------ 로그인창
        
        Main = new JPanel();
        Main.setBounds(0, 0, 648, 761);
        frame.getContentPane().add(Main);
        Main.setLayout(null);
        Main.setVisible(false);
        
        
        
        
        JPanel mainButtonPanel = new JPanel();
        mainButtonPanel.setBounds(0, 708, 648, 53);
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
        btnProfile.setBounds(490, 6, 122, 45);
        mainButtonPanel.add(btnProfile);
        
        JButton searchOption = new JButton("검색 설정");
        searchOption.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println(sex); // 로그인한 사람의 성
                SearchMemberOptionFrame.newSearchMemberOptionFrame(frame,sex,KuroRekishiMain.this,idKey);
            }
        });
        searchOption.setBounds(26, 6, 122, 45);
        searchOption.setFont(new Font("D2Coding", Font.BOLD, 16));
        mainButtonPanel.add(searchOption);
        
        
        
        JButton btnNewChat = new JButton("데이트추천");
        btnNewChat.setBounds(177, 6, 122, 45);
        mainButtonPanel.add(btnNewChat);
        btnNewChat.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DateFrame.newDateFrame(frame);
            }
        });
        btnNewChat.setFont(new Font("D2Coding", Font.BOLD, 16));
        
        JButton btnCheckChat = new JButton("채팅방");
        btnCheckChat.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ChatRoomFrame.newChatRoomFrame(frame,idKey);
            }
        });
        btnCheckChat.setFont(new Font("Dialog", Font.BOLD, 16));
        btnCheckChat.setBounds(337, 6, 122, 45);
        mainButtonPanel.add(btnCheckChat);
        
        
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
        
        btnNotgood = new JButton("별로에요");
        btnNotgood.setBounds(85, 658, 122, 45);
        Main.add(btnNotgood);
        btnNotgood.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                index=0;
                if(searchNames == null) {
                    showDiffrentSexImages();
                    getHistory();
                }else {
                    nameIndex ++;
                    if(nameIndex>=searchNames.size()) {
                        JOptionPane.showMessageDialog(frame, "마지막 검색 결과입니다! 처음으로 돌아갑니다.","ERROR",JOptionPane.ERROR_MESSAGE);
                        nameIndex=0;
                        name = searchNames.get(nameIndex);
                        setSearchImages();
                        getHistory();
                    }else {
                        name = searchNames.get(nameIndex);
                        setSearchImages();
                        getHistory();
                    }
                }
            }
        });
        btnNotgood.setFont(new Font("D2Coding", Font.BOLD, 16));
        
        JButton btnGood = new JButton("좋아요");
        btnGood.setBounds(485, 658, 122, 45);
        Main.add(btnGood);
        btnGood.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                likeIndex++;
                if(likeIndex==5) {
                    JOptionPane.showMessageDialog(frame, "좋아요는 하루에 5번만 누를수 있습니다!","ERROR",JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                giveThumb();
                JOptionPane.showMessageDialog(frame, "상대방의 응답을 기다려보세요!");
                ChatFrame.newChatFrame(frame);
            }
        });
        btnGood.setFont(new Font("D2Coding", Font.BOLD, 16));
        
        btnSendStar = new JButton("점수주기");
        btnSendStar.setBounds(351, 658, 122, 45);
        btnSendStar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                List<String>giveIdList = new ArrayList<>();
                String id = dao.findIdByName(name);
                giveIdList = pointDao.getGiveId(id);
                for(String s : giveIdList) {
                    if(s.equals(idKey)) {
                        JOptionPane.showMessageDialog(frame, "이미 같은 대상에게 점수를주셨습니다..","ERROR",JOptionPane.ERROR_MESSAGE);
                        break;
                    }else {
                        insertStar();
                    }
                }
            }
        });
        btnSendStar.setFont(new Font("D2Coding", Font.BOLD, 16));
        Main.add(btnSendStar);
        
        starComboBox = new JComboBox(stars);
        starComboBox.setBounds(219, 656, 120, 50);
        Main.add(starComboBox);
        
        lblHistory = new JLabel();
        lblHistory.setFont(new Font("Lucida Grande", Font.BOLD, 14));
        lblHistory.setHorizontalAlignment(SwingConstants.CENTER);
        lblHistory.setBounds(74, 640, 498, 16);
        Main.add(lblHistory);
        
        lblMemberImages = new JLabel(new ImageIcon());
        lblMemberImages.setFont(new Font("궁서체", Font.BOLD, 14));
        lblMemberImages.setBackground(new Color(240, 240, 240));
        lblMemberImages.setBounds(0, 0, 662, 596);
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
        btnLogin.setBounds(492, 635, 119, 41);
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
        
        
        
	} // end initialize()

    private void insertStar() {
        String star = (String) starComboBox.getSelectedItem();
        int result = 0;
        if(star.equals("✿")) {
            // 1. 넣을 점수, 입력하는 사람 아이디,받는 사람 아이디를 가져와서 넣는다.
            String id = dao.findIdByName(name);
            Point point = new Point(1,idKey,id);
            result = pointDao.insertPoint(point);
            if(result == 1) {
                JOptionPane.showMessageDialog(frame, "점수 반영 성공!");
            }else {
                JOptionPane.showMessageDialog(frame, "점수 반영 실패!");
            }
        }else if(star.equals("✿✿")) {
            String id = dao.findIdByName(name);
            Point point = new Point(2,idKey,id);
            result = pointDao.insertPoint(point);
            if(result == 1) {
                JOptionPane.showMessageDialog(frame, "점수 반영 성공!");
            }else {
                JOptionPane.showMessageDialog(frame, "점수 반영 실패!");
            }   
        }else if(star.equals("✿✿✿")) {
            String id = dao.findIdByName(name);
            Point point = new Point(3,idKey,id);
            result = pointDao.insertPoint(point);
            if(result == 1) {
                JOptionPane.showMessageDialog(frame, "점수 반영 성공!");
            }else {
                JOptionPane.showMessageDialog(frame, "점수 반영 실패!");
            } 
            
            
        }else if(star.equals("✿✿✿✿")) {
            String id = dao.findIdByName(name);
            Point point = new Point(4,idKey,id);
            result = pointDao.insertPoint(point);
            if(result == 1) {
                JOptionPane.showMessageDialog(frame, "점수 반영 성공!");
            }else {
                JOptionPane.showMessageDialog(frame, "점수 반영 실패!");
            } 
            
        }else {
            String id = dao.findIdByName(name);
            Point point = new Point(5,idKey,id);
            result = pointDao.insertPoint(point);
            if(result == 1) {
                JOptionPane.showMessageDialog(frame, "점수 반영 성공!");
            }else {
                JOptionPane.showMessageDialog(frame, "점수 반영 실패!");
            } 
           
        }
    }

    private void getHistory() {
        String history = null;
        history = dao.findHistory(name);
        lblHistory.setText(history);
    }

    protected void setSearchImages() {
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
			String id = dao.findIdByName(name);
			chatDao.insertId(idKey, id);
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
	        
	        // 흑역사 설정
	        getHistory();
			
			Login.setVisible(false);
			Main.setVisible(true);
			
			// 점수 확인
			checkMyPoint();
		}else {
		    // 회원이 아닌경우 회원가입 유도.
			JOptionPane.showMessageDialog(frame, "등록된 아이디가 없습니다.", "Warning", JOptionPane.WARNING_MESSAGE);
		}
	
    }

    private void checkMyPoint() {
        // TODO
        List<Integer> pointList = new ArrayList<>();
        pointList = pointDao.getStarPoint(idKey);
        int point = pointList.get(1);
        int starGiveNum = pointList.get(0);
        if(point>0) {
            double avg =(double) point/starGiveNum;
            String userAvg = String.format("%.1f", avg);
            if(avg<=1) {
                JOptionPane.showMessageDialog(frame,"당신의 점수는 " + userAvg + " 점 입니다.\n" + " 와 증말로 못생기셨네요...");
            }else if(avg<=2) {
                JOptionPane.showMessageDialog(frame,"당신의 점수는 " + userAvg + " 점 입니다.\n" + " 진짜 못생기셨네요...");
            }else if(avg<=3) {
                JOptionPane.showMessageDialog(frame,"당신의 점수는 " + userAvg + " 점 입니다.\n" + "평타 그 이하입니다.");
            }else if(avg<=4) {
                JOptionPane.showMessageDialog(frame,"당신의 점수는 " + userAvg + " 점 입니다.\n" + "딱 평타 이상 그 이하도 아닙니다.");
            }else {
                JOptionPane.showMessageDialog(frame,"당신의 점수는 " + userAvg + " 점 입니다.\n" + "good");
            }
        }
    }

    private String findName(String id) {
		String name = dao.fineName(id);
		return name;
	}

    @Override
    public void sendSearchResult(ArrayList<String> searchOptoinNameList) {
        searchNames = searchOptoinNameList;
        if(searchNames.size()==0) {
            JOptionPane.showMessageDialog(frame, "조건에 부합하는 검색결과가 없습니다. 다시 검색해주세요.", "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }else {
            dao.setSearch(idKey);
            JOptionPane.showMessageDialog(frame, "세부검색 설정 완료.", "완료", JOptionPane.PLAIN_MESSAGE);
        }
        name = searchNames.get(0);
        String imageLink = null;
        if(sex.equals("여자")) { // 여자가 로그인했을때
            imageLink = "usersImageMale/"+name+"/"+name+nameIndex;
            System.out.println(imageLink);
            lblMemberImages.setIcon(new ImageIcon(imageLink));
            chageImage();
        }else { // 남자가 로그인했을때
            imageLink = "usersImageFeMale/"+name+"/"+name+nameIndex;
            System.out.println(imageLink);
            lblMemberImages.setIcon(new ImageIcon(imageLink));
            chageImage();
        }
    }
}
