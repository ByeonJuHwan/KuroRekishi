package kurorekishimain;

import java.awt.Component;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import recommendation.Recommendation;
import recommendation.RecommendationDaoImpl;

public class ViewAll extends JFrame {

    private JPanel contentPane;
    private Component parent;
    private JTextField nameText;
    private JTextField cateText;
    private JTextField locText;
    private JTextField enjoyText;
    private Recommendation rec;
    private JLabel lblImage1;
    private RecommendationDaoImpl dao;
    /**
     * Launch the application.
     * @param rec 
     */
    public static void newViewAll(Component parent, Recommendation rec) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                    ViewAll frame = new ViewAll(parent,rec);
                    frame.setVisible(true);
               
            }
        });
    }   
    
    public ViewAll(Component parent, Recommendation rec) {
        this.rec = rec;
        this.parent = parent;
        dao = RecommendationDaoImpl.getInstance();
        initialize();
        setView();
    }

    private void setView() {
        nameText.setText(rec.getName());
        cateText.setText(rec.getCategory());
        locText.setText(rec.getLoc());
        enjoyText.setText(rec.getEnjoy());
    }

    /**
     * Create the frame.
     */
    public void initialize() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        int x = parent.getX();
        int y = parent.getY();
        setBounds(x+665, y, 664, 400);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("이름");
        lblNewLabel.setFont(new Font("궁서체", Font.BOLD, 15));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(33, 10, 75, 41);
        contentPane.add(lblNewLabel);
        
        nameText = new JTextField();
        nameText.setFont(new Font("D2Coding", Font.BOLD, 15));
        nameText.setEditable(false);
        nameText.setBounds(131, 10, 141, 41);
        contentPane.add(nameText);
        nameText.setColumns(10);
        
        JLabel lblNewLabel_1 = new JLabel("카테고리");
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setFont(new Font("궁서체", Font.BOLD, 15));
        lblNewLabel_1.setBounds(331, 10, 102, 41);
        contentPane.add(lblNewLabel_1);
        
        cateText = new JTextField();
        cateText.setFont(new Font("D2Coding", Font.BOLD, 15));
        cateText.setEditable(false);
        cateText.setColumns(10);
        cateText.setBounds(445, 10, 141, 41);
        contentPane.add(cateText);
        
        JLabel lblNewLabel_2 = new JLabel("위치");
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2.setFont(new Font("궁서체", Font.BOLD, 15));
        lblNewLabel_2.setBounds(33, 61, 75, 41);
        contentPane.add(lblNewLabel_2);
        
        locText = new JTextField();
        locText.setFont(new Font("D2Coding", Font.BOLD, 15));
        locText.setEditable(false);
        locText.setColumns(10);
        locText.setBounds(131, 61, 141, 41);
        contentPane.add(locText);
        
        JLabel lblNewLabel_2_1 = new JLabel("후기");
        lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2_1.setFont(new Font("궁서체", Font.BOLD, 15));
        lblNewLabel_2_1.setBounds(33, 112, 75, 41);
        contentPane.add(lblNewLabel_2_1);
        
        enjoyText = new JTextField();
        enjoyText.setFont(new Font("D2Coding", Font.BOLD, 15));
        enjoyText.setEditable(false);
        enjoyText.setColumns(10);
        enjoyText.setBounds(131, 112, 455, 41);
        contentPane.add(enjoyText);
        
        JButton btnLink = new JButton("자세히보기");
        btnLink.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                linkBun(null);
            }
        });
        btnLink.setFont(new Font("D2Coding", Font.BOLD, 15));
        btnLink.setBounds(444, 61, 142, 41);
        contentPane.add(btnLink);
        
        JLabel lblNewLabel_2_1_1 = new JLabel("방문자 사진");
        lblNewLabel_2_1_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2_1_1.setFont(new Font("궁서체", Font.BOLD, 15));
        lblNewLabel_2_1_1.setBounds(12, 226, 96, 41);
        contentPane.add(lblNewLabel_2_1_1);
        
        try {
            //TODO 이미지 나오게 설정
            URL url = new URL("https://map.naver.com/v5/entry/place/19479566?c=14146078.102695633%2C4493752.402414959%2C13%2C0%2C0%2C0%2Cdh&placePath=%2Fphoto&entry=plt");
            lblImage1 = new JLabel(new ImageIcon(url));
            lblImage1.setBounds(131, 163, 210, 176);
            contentPane.add(lblImage1);
        }catch(Exception e1) {
            e1.printStackTrace();
        }
        JLabel lblImage2 = new JLabel();
        lblImage2.setBounds(376, 163, 210, 176);
        contentPane.add(lblImage2);
    }

    private void linkBun(java.awt.event.MouseEvent evt) {
        if(Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();
            try {
                URI uri = new URI(getURL());
                desktop.browse(uri);
            }catch(Exception e) {
                e.printStackTrace();
            }
        }
        
    }

    private String getURL() {
        String url = null;
        String name = nameText.getText();
        url = dao.searchUrl(name); // 이름으로 url 검색
        return url;
    }

}
