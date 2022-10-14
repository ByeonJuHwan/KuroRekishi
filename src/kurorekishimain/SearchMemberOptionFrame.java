package kurorekishimain;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class SearchMemberOptionFrame extends JFrame {

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
     */
    public static void newSearchMemberOptionFrame(Component parent, String sex) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                    SearchMemberOptionFrame frame = new SearchMemberOptionFrame(parent,sex);
                    frame.setVisible(true);
            }
        });
    }
    
    public SearchMemberOptionFrame(Component parent, String sex) {
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
             
                // 아무것도 입력하지 않고 누를시 경고 메세지
                if(!hightCheck.isSelected() && !ageCheck.isSelected() && !mbtiCheck.isSelected()) {
                    checkAll();
                    return;
                }
                
 
                // 키, 나이에서 한쪽만 입력하지않고 양쪽다 입력했는지
                // case 1 age,mbti 가 선택되서 수정불가 상태에서 키를 한쪽만 입력했을경우.
                
                checkHight();
                
                // case 2 hight,mbti 가 선택되서 수정불가 상태에서 나이를 한쪽만 입력했을경우.
                checkAge();
                
                // case 3 hight, age 가 선택되서 수정불가 상태에서 mbti를 입력 안했을경우.
                checkMbti();
                
                // case 4 hight 가 선택되서 수정불가 상태에서 나이가 입력이 안되있거나 mbti가 입력이 안되어있는경우.
                checkAgeMbti();
                
                // case 5 age가 선택되서 수정불가 상태에서 키가 입력이 안되있거나 mbti가 입력이 안되어있는경우.
                checkHightMbti();
                
                // case 6 mbti가 선택되서 수정불가 상태에서 키가 입력이 안되있거나 age가 입력이 안되어있는경우.
                checkHightAge();
                
                // TODO 전부 체크되어 아무것도 입력이 안되는경우
                //checkAllSelect();
                
                // mbti에 4글자 입력을 했는지 확인
                checkMbtiLegth();
                
            }
        });
        btnMemberSearch.setFont(new Font("Lucida Grande", Font.BOLD, 16));
        btnMemberSearch.setBounds(349, 272, 145, 44);
        contentPane.add(btnMemberSearch);
        
        btnGoBack = new JButton("뒤로가기");
        btnGoBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btnGoBack.setFont(new Font("Lucida Grande", Font.BOLD, 16));
        btnGoBack.setBounds(103, 272, 145, 44);
        contentPane.add(btnGoBack);
        
        hightCheck = new JCheckBox("");
        hightCheck.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                lowHight.setEditable(false);
                maxHight.setEditable(false);
            }
        });
        hightCheck.setBounds(586, 62, 28, 23);
        contentPane.add(hightCheck);
        
        ageCheck = new JCheckBox("");
        ageCheck.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               lowAge.setEditable(false);
               maxAge.setEditable(false);
            }
        });
        ageCheck.setBounds(586, 136, 28, 23);
        contentPane.add(ageCheck);
        
        mbtiCheck = new JCheckBox("");
        mbtiCheck.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mbti.setEditable(false);
            }
        });
        mbtiCheck.setBounds(585, 210, 28, 23);
        contentPane.add(mbtiCheck);
    }

    protected void checkMbtiLegth() {
        if(!mbtiCheck.isSelected()) {
            int mbtiLength = mbti.getText().length();
            if(mbtiLength<4) {
                JOptionPane.showMessageDialog(this, "mbti는 4글자 입니다.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            
        }
        
    }

    private void checkAllSelect() {
        // // 전부 체크되어 아무것도 입력이 안되는경우
        if(hightCheck.isSelected() && ageCheck.isSelected() && mbtiCheck.isSelected()) {
            JOptionPane.showMessageDialog(this, "체크를 해제해주세요.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
    }

    private void checkHightAge() {
        // mbti가 선택되서 수정불가 상태에서 키가 입력이 안되있거나 age가 입력이 안되어있는경우.
        if(mbtiCheck.isSelected()) {
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
            }
        }
        
    }

    private void checkHightMbti() {
        // age가 선택되서 수정불가 상태에서 키가 입력이 안되있거나 mbti가 입력이 안되어있는경우.
        if(ageCheck.isSelected()) {
            if(lowHight.getText().equals("")&&maxHight.getText().equals("")&&mbti.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "키와 mbti 를 입력해주세요.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }else if(lowHight.getText().equals("") || maxHight.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "키를 입력해주세요.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }else if(mbti.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "mbti를 입력해주세요.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        
    }

    private void checkAgeMbti() {
        // hight 가 선택되서 수정불가 상태에서 나이가 입력이 안되있거나 mbti가 입력이 안되어있는경우.
        if(hightCheck.isSelected()) {
            if(lowAge.getText().equals("") && maxAge.getText().equals("")&&mbti.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "나이와 mbti 를 입력해주세요.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }else if(lowAge.getText().equals("")||maxAge.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "나이 를 입력해주세요.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }else if(mbti.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "mbti 를 입력해주세요.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        
    }
    
    private void checkMbti() {
        // hight, age 가 선택되서 수정불가 상태에서 mbti를 입력 안했을경우.
        if(hightCheck.isSelected() && ageCheck.isSelected()) {
            if(mbti.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "mbti 를 입력해주세요.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        
    }

    private void checkAge() {
        // hight,mbti 가 선택되서 수정불가 상태에서 나를 한쪽만 입력했을경우.
        if(hightCheck.isSelected() && mbtiCheck.isSelected()) {
            if(lowAge.getText().equals("") || maxAge.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "나이를 입력해주세요.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        
    }

    private void checkHight() {
        // age,mbti 가 선택되서 수정불가 상태에서 키를 한쪽만 입력했을경우.
        if(ageCheck.isSelected()&&mbtiCheck.isSelected()) {
            if(lowHight.getText().equals("") || maxHight.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "키를 입력해주세요.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        
    }

    private void checkAll() {
        // 3칸다 아무것도 입력하지 않았을경우
       if(!hightCheck.isSelected() && !ageCheck.isSelected() && !mbtiCheck.isSelected()) {
           if((lowHight.getText().equals("") || maxHight.getText().equals("") 
                   || lowAge.getText().equals("") && maxAge.getText().equals("")
                   || mbti.getText().equals(""))){
               JOptionPane.showMessageDialog(this, "검색내용을 입력해주세요.", "Error", JOptionPane.ERROR_MESSAGE);
               return;
           }
       } 
        
    }
}