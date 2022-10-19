package kurorekishimain;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import member.MemberDaoImpl;

public class SearchMemberOptionFrame extends JFrame {
    public interface sendSearchListener{
        void sendSearchResult(ArrayList<String> searchOptoinNameList);
        void sendSearchReset();
    }
    private sendSearchListener listener;
   
    public static ArrayList<String> searchOptoinNameList;
    public static String mbtis;
    private MemberDaoImpl dao;
    private String id;
    
   
    private JPanel contentPane;
    private String sex; // 로그인한 사람의 성별 ex) 남자면 남자 여자면 여자
    private JTextField lowHight;
    private JTextField maxHight;
    private JTextField lowAge;
    private JTextField maxAge;
    private JTextField mbti;
    private JButton btnMemberSearch;
    private JButton btnGoBack;
    private Component parent;
    private JCheckBox ageCheck;
    private JCheckBox mbtiCheck;
    private JCheckBox hightCheck;

    /**
     * Launch the application.
     * @param frame 
     * @param sex 
     * @param id 
     */
    public static void newSearchMemberOptionFrame(Component parent, String sex,sendSearchListener listener, String id) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                    SearchMemberOptionFrame frame = new SearchMemberOptionFrame(parent,sex,listener,id);
                    frame.setVisible(true);
            }
        });
    }
    
    public SearchMemberOptionFrame(Component parent, String sex, sendSearchListener listener, String id) {
        this.listener = listener;
        this.id = id;
        dao = MemberDaoImpl.getInstance();
        this.parent = parent;
        this.sex = sex;
        initialize();
    }

    /**
     * Create the frame.
     */
    public void initialize() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        int x = parent.getX();
        int y = parent.getY();
        setBounds(x, y, 620, 370);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setResizable(false);
        setTitle("세부 검색");
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel JoinId = new JLabel("세부검색");
        JoinId.setHorizontalAlignment(SwingConstants.CENTER);
        JoinId.setFont(new Font("Dialog", Font.BOLD, 18));
        JoinId.setBounds(234, 6, 137, 53);
        contentPane.add(JoinId);
        
        JLabel lblNewLabel = new JLabel("키");
        lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(6, 56, 85, 38);
        contentPane.add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("나이");
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
        lblNewLabel_1.setBounds(6, 127, 85, 38);
        contentPane.add(lblNewLabel_1);
        
        JLabel lblMbti = new JLabel("MBTI");
        lblMbti.setHorizontalAlignment(SwingConstants.CENTER);
        lblMbti.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
        lblMbti.setBounds(6, 201, 85, 38);
        contentPane.add(lblMbti);
        
        lowHight = new JTextField();
        lowHight.setBounds(103, 56, 145, 38);
        contentPane.add(lowHight);
        lowHight.setColumns(10);
        
        JLabel lblNewLabel_2 = new JLabel("이상");
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
        lblNewLabel_2.setBounds(260, 56, 85, 38);
        contentPane.add(lblNewLabel_2);
        
        maxHight = new JTextField();
        maxHight.setColumns(10);
        maxHight.setBounds(349, 56, 145, 38);
        contentPane.add(maxHight);
        
        JLabel lblNewLabel_2_1 = new JLabel("이하");
        lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2_1.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
        lblNewLabel_2_1.setBounds(506, 56, 67, 38);
        contentPane.add(lblNewLabel_2_1);
        
        lowAge = new JTextField();
        lowAge.setColumns(10);
        lowAge.setBounds(103, 127, 145, 38);
        contentPane.add(lowAge);
        
        JLabel 이상 = new JLabel("이상");
        이상.setHorizontalAlignment(SwingConstants.CENTER);
        이상.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
        이상.setBounds(260, 127, 85, 38);
        contentPane.add(이상);
        
        maxAge = new JTextField();
        maxAge.setColumns(10);
        maxAge.setBounds(349, 129, 145, 38);
        contentPane.add(maxAge);
        
        JLabel lblNewLabel_2_1_1 = new JLabel("이하");
        lblNewLabel_2_1_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2_1_1.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
        lblNewLabel_2_1_1.setBounds(506, 127, 67, 38);
        contentPane.add(lblNewLabel_2_1_1);
        
        mbti = new JTextField();
        mbti.setBounds(103, 201, 391, 44);
        contentPane.add(mbti);
        mbti.setColumns(10);
        
        btnMemberSearch = new JButton("검색");
        btnMemberSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
             
                // 아무것도 입력하지 않고 누를시 경고 메세지 / mbti를 4글자 입력했는지도 검사.
                if(!hightCheck.isSelected() && !ageCheck.isSelected() && !mbtiCheck.isSelected()) {
                    checkAll();
                    return;
                }
                // 전부 체크되어 아무것도 입력이 안되는경우
                if(hightCheck.isSelected() && ageCheck.isSelected() && mbtiCheck.isSelected()) {                   
                    checkAllSelect();
                    return;
                }
 
                // 키, 나이에서 한쪽만 입력하지않고 양쪽다 입력했는지
                // case 1 age,mbti 가 선택되서 수정불가 상태에서 키를 한쪽만 입력했을경우.
                if(ageCheck.isSelected()&&mbtiCheck.isSelected()) {
                    checkHight();
                    return;                    
                }
                
                // case 2 hight,mbti 가 선택되서 수정불가 상태에서 나이를 한쪽만 입력했을경우.
                if(hightCheck.isSelected() && mbtiCheck.isSelected()) {
                    checkAge();
                    return;
                }
                
                // case 3 hight, age 가 선택되서 수정불가 상태에서 mbti를 입력 안했을경우.
                if(hightCheck.isSelected() && ageCheck.isSelected()) {                       
                    checkMbti();   
                    return;                        
                }
                
                // case 4 hight 가 선택되서 수정불가 상태에서 나이가 입력이 안되있거나 mbti가 입력이 안되어있는경우.
                if(hightCheck.isSelected()) {
                    checkAgeMbti();
                    return;
                }
                
                // case 5 age가 선택되서 수정불가 상태에서 키가 입력이 안되있거나 mbti가 입력이 안되어있는경우.
                if(ageCheck.isSelected()) {
                    checkHightMbti();
                    return;
                }
                
                // case 6 mbti가 선택되서 수정불가 상태에서 키가 입력이 안되있거나 age가 입력이 안되어있는경우.
                if(mbtiCheck.isSelected()) {
                    checkHightAge();
                    return;
                }
                
            }
        });
        btnMemberSearch.setFont(new Font("Lucida Grande", Font.BOLD, 16));
        btnMemberSearch.setBounds(390, 272, 145, 44);
        contentPane.add(btnMemberSearch);
        
        btnGoBack = new JButton("뒤로가기");
        btnGoBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btnGoBack.setFont(new Font("Lucida Grande", Font.BOLD, 16));
        btnGoBack.setBounds(62, 272, 145, 44);
        contentPane.add(btnGoBack);
        
        hightCheck = new JCheckBox("");
        hightCheck.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(hightCheck.isSelected()) {
                    lowHight.setEditable(false);
                    maxHight.setEditable(false);
                    lowHight.setText("");
                    maxHight.setText("");
                }else {
                    lowHight.setEditable(true);
                    maxHight.setEditable(true);
                }
            }
        });
        hightCheck.setBounds(586, 62, 28, 23);
        contentPane.add(hightCheck);
        
        ageCheck = new JCheckBox("");
        ageCheck.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(ageCheck.isSelected()) {
                    lowAge.setEditable(false);
                    maxAge.setEditable(false);
                    lowAge.setText("");
                    maxAge.setText("");
                }else {
                    lowAge.setEditable(true);
                    maxAge.setEditable(true);
                } 
            }
        });
        ageCheck.setBounds(586, 136, 28, 23);
        contentPane.add(ageCheck);
        
        mbtiCheck = new JCheckBox("");
        mbtiCheck.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(mbtiCheck.isSelected()) {
                    mbti.setEditable(false);
                    mbti.setText("");
                }else {
                    mbti.setEditable(true);
                }
            }
        });
        mbtiCheck.setBounds(585, 210, 28, 23);
        contentPane.add(mbtiCheck);
        
        JButton btnSearchReset = new JButton("초기화");
        btnSearchReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchReset();
            }
        });
        btnSearchReset.setFont(new Font("Lucida Grande", Font.BOLD, 16));
        btnSearchReset.setBounds(226, 272, 145, 44);
        contentPane.add(btnSearchReset);
    }// end initialize


    private void searchReset() {
        // TODO 누르면 검색초기화해서 검색한 영역이 아니라 전체영역으로 변경
        int result = JOptionPane.showConfirmDialog(this, "검색범위를 초기화 하시겠습니까??", "???", JOptionPane.YES_NO_OPTION);
        if(result==JOptionPane.YES_OPTION) {
            // dao.searchreset으로 setsearch부분을 false로 변경.
            int resetResult = dao.updateSearchReset(id);
            
            if(resetResult == 1) {
                JOptionPane.showMessageDialog(this, "검색범위를 초기화 했습니다.");
                listener.sendSearchReset();
            }else {
                JOptionPane.showMessageDialog(this, "검색범위를 초기화 실패");
            }
        }
        
    }

    private void checkAllSelect() {
         // 전부 체크되어 아무것도 입력이 안되는경우
         JOptionPane.showMessageDialog(this, "체크를 해제해주세요.", "Error", JOptionPane.ERROR_MESSAGE); 
    }

    private void checkHightAge() {
        // mbti가 선택되서 수정불가 상태에서 키가 입력이 안되있거나 age가 입력이 안되어있는경우.
        
            if(lowHight.getText().equals("")&&maxHight.getText().equals("")&&
                    lowAge.getText().equals("") && maxAge.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "키와 나이 를 입력해주세요.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }else if(lowHight.getText().equals("") || maxHight.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "키를 입력해주세요.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }else if(lowAge.getText().equals("")||maxAge.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "나이 를 입력해주세요.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }else { // 키와 나이를 받아야함.
                String lowhight = lowHight.getText();
                String maxhight = maxHight.getText();
                String lowage = lowAge.getText();
                String maxage = maxAge.getText();
                searchOptoinNameList = (ArrayList<String>) dao.findHightAgeOption(lowhight, maxhight, lowage, maxage);
                listener.sendSearchResult(searchOptoinNameList);
                
            }
    }

    private void checkHightMbti() {
        // age가 선택되서 수정불가 상태에서 키가 입력이 안되있거나 mbti가 입력이 안되어있는경우.       
            if(lowHight.getText().equals("")&&maxHight.getText().equals("")&&mbti.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "키와 mbti 를 입력해주세요.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }else if(lowHight.getText().equals("") || maxHight.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "키를 입력해주세요.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }else if(mbti.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "mbti를 입력해주세요.", "Error", JOptionPane.ERROR_MESSAGE);
            } else { // 키와 mbti를 받아야함
                String lowhight = lowHight.getText();
                String maxhight = maxHight.getText();
                mbtis = mbti.getText();
                searchOptoinNameList = (ArrayList<String>) dao.findHightMbtiOption(lowhight, maxhight, mbtis);
                listener.sendSearchResult(searchOptoinNameList);

            }
    }

    private void checkAgeMbti() {
        // hight 가 선택되서 수정불가 상태에서 나이가 입력이 안되있거나 mbti가 입력이 안되어있는경우.       
            if(lowAge.getText().equals("") && maxAge.getText().equals("")&&mbti.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "나이와 mbti 를 입력해주세요.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }else if(lowAge.getText().equals("")||maxAge.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "나이 를 입력해주세요.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }else if(mbti.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "mbti 를 입력해주세요.", "Error", JOptionPane.ERROR_MESSAGE);
            }else { // 나이 mbti를 넣어야함.
                String lowage = lowAge.getText();
                String maxage = maxAge.getText();
                mbtis = mbti.getText();
                searchOptoinNameList = (ArrayList<String>) dao.findAgeMbtiOption(lowage, maxage, mbtis);
                listener.sendSearchResult(searchOptoinNameList);
            }
    }
    
    private void checkMbti() {
        // hight, age 가 선택되서 수정불가 상태에서 mbti를 입력 안했을경우.
          if(mbti.getText().equals("")) {
              JOptionPane.showMessageDialog(this, "mbti 를 입력해주세요.", "Error", JOptionPane.ERROR_MESSAGE);
              return;            
        }else {
            mbtis = mbti.getText();
            searchOptoinNameList = (ArrayList<String>) dao.findMbtiOption(mbtis);
            listener.sendSearchResult(searchOptoinNameList);
        }
        
    }

    private void checkAge() {
        // hight,mbti 가 선택되서 수정불가 상태에서 나를 한쪽만 입력했을경우.
        if(lowAge.getText().equals("") || maxAge.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "나이를 입력해주세요.", "Error", JOptionPane.ERROR_MESSAGE);
        }else {
            String lowage = lowAge.getText();
            String maxage = maxAge.getText();
            searchOptoinNameList = (ArrayList<String>) dao.findAgeOption(lowage, maxage);
            listener.sendSearchResult(searchOptoinNameList);
            // 세부수정한경우 다시 로그인했을때도 설정한 내용대로 사람들이뜨게 db에 설정.
        }
    }

    private void checkHight() {
        // age,mbti 가 선택되서 수정불가 상태에서 키를 한쪽만 입력했을경우.
        
            if(lowHight.getText().equals("") || maxHight.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "키를 입력해주세요.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }else {
                String lowhight = lowHight.getText();
                String maxhight = maxHight.getText();

                // dao의 명령어로 NAME 리턴받기 여기서 그 인터페이스 사용해서 메인창에서 해야할일 해야함
                searchOptoinNameList = (ArrayList<String>) dao.findHightOption(lowhight, maxhight);
                listener.sendSearchResult(searchOptoinNameList);
                // 세부수정한경우 다시 로그인했을때도 설정한 내용대로 사람들이뜨게 db에 설정.
            }
        }
    

    private void checkAll() {
        // 3칸다 아무것도 입력하지 않았을경우
        if((lowHight.getText().equals("") || maxHight.getText().equals("") 
                || lowAge.getText().equals("") && maxAge.getText().equals("")
                || mbti.getText().equals(""))){
             JOptionPane.showMessageDialog(this, "검색내용을 입력해주세요.", "Error", JOptionPane.ERROR_MESSAGE);   
        }else if(!mbtiCheck.isSelected()) {
           int mbtiLength = mbti.getText().length();
           if(mbtiLength<4) {
               JOptionPane.showMessageDialog(this, "mbti는 4글자 입니다.", "Error", JOptionPane.ERROR_MESSAGE);
           }else {
               String lowhight = lowHight.getText();
               String maxhight = maxHight.getText();
               String lowage = lowAge.getText();
               String maxage = maxAge.getText();
               mbtis = mbti.getText();
               searchOptoinNameList = (ArrayList<String>) dao.findHightAgeMBTIOption(lowhight, maxhight, lowage, maxage, mbtis);
               listener.sendSearchResult(searchOptoinNameList);
           }
       }
    }    
}
