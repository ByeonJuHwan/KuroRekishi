package kurorekishimain;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class UpdateImageFrame extends JFrame {

    private JPanel contentPane;
    private Component parent;
    private String name;
    private String sex;
    private int index = 0;
    private JLabel lblUpdateImage;
    private String link = null;

    /**
     * Launch the application.
     * @param parent 
     * @param name 
     * @param sex 
     */
    public static void newUpdateImageFrame(Component parent, String name, String sex) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                    UpdateImageFrame frame = new UpdateImageFrame(parent,name,sex);
                    frame.setVisible(true);
            }
        });
    }

    
    public UpdateImageFrame(Component parent, String name, String sex) {
        this.sex = sex;
        this.name = name;
        this.parent = parent;
        initialize();
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
        setResizable(false);
        setTitle("이미지 변경");
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JButton btnGoPreImage = new JButton("<");
        btnGoPreImage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                goBackImage();
            }
        });
        btnGoPreImage.setBounds(0, 274, 41, 45);
        contentPane.add(btnGoPreImage);
        
        JButton btnGoNextImage = new JButton(">");
        btnGoNextImage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                goNextImage();
            }
        });
        btnGoNextImage.setBounds(607, 274, 41, 45);
        contentPane.add(btnGoNextImage);
        
        JButton btnDispose = new JButton("뒤로가기");
        btnDispose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btnDispose.setFont(new Font("궁서체", Font.BOLD, 15));
        btnDispose.setBounds(157, 707, 119, 41);
        contentPane.add(btnDispose);
        
        JButton btnUpdateImage = new JButton("사진변경");
        btnUpdateImage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO 사진변경하고 변경완료 메세지 출력
                updateImage();
            }
        });
        btnUpdateImage.setFont(new Font("궁서체", Font.BOLD, 15));
        btnUpdateImage.setBounds(368, 707, 119, 41);
        contentPane.add(btnUpdateImage);
        
        
        lblUpdateImage = new JLabel(new ImageIcon(showUserImages()));
        lblUpdateImage.setBounds(0, 0, 648, 697);
        contentPane.add(lblUpdateImage);
    }


    private void updateImage() {
        if(sex.equals("여자")) { // 여자일때 여자폴더로 찾아가서 작업
            JFileChooser jFileChooser = new JFileChooser();
            int result = jFileChooser.showSaveDialog(null);
            File updateUserImage = new File("usersImageFemale/"+name+"/"+name+index);
            if(result == JFileChooser.APPROVE_OPTION) {
                File file = jFileChooser.getSelectedFile();
                try {
                    int update = JOptionPane.showConfirmDialog(this, "정말 수정하시겠습니까?", "알림", JOptionPane.YES_NO_OPTION);
                    if(update==JOptionPane.YES_OPTION) {
                        BufferedImage image = ImageIO.read(file);
                        ImageIO.write(image, "png", updateUserImage);
                        System.out.println("updateSuccess");
                        
                        String fname = "usersImageFemale/"+name+"/"+name+index;
                        System.out.println("fname : " + fname);
                        lblUpdateImage.setIcon(new ImageIcon(fname));
                        
//                      goNextImage();
                        JOptionPane.showMessageDialog(this, "수정 완료. 재시작 해주세요.");
                        int reset = JOptionPane.showConfirmDialog(this, "재시작 하시겠습니까??", "Question",JOptionPane.YES_NO_OPTION);
                        if(reset == JOptionPane.YES_OPTION) {
                            System.exit(0);
                        }
                    }
                }catch(Exception e) {
                    e.printStackTrace();
                }
            }
        }else { // 남자일때 남자 폴더로 찾아가서 작업
            JFileChooser jFileChooser = new JFileChooser();
            int result = jFileChooser.showSaveDialog(null);
            File updateUserImage = new File("usersImageMale/"+name+"/"+name+index);
            if(result == JFileChooser.APPROVE_OPTION) {
                File file = jFileChooser.getSelectedFile();
                try {
                    BufferedImage image = ImageIO.read(file);
                    ImageIO.write(image, "png", updateUserImage);
                    System.out.println("updateSuccess");
                    int update = JOptionPane.showConfirmDialog(this, "정말 수정하시겠습니까?", "알림", JOptionPane.YES_NO_OPTION);
                    if(update==JOptionPane.YES_OPTION) {
                        
                        goNextImage();
                        JOptionPane.showMessageDialog(parent, "수정 완료");
                    }
                }catch(Exception e) {
                    e.printStackTrace();
                }
            }
        }
        
    }


    private void chaneImage() {
        System.out.println(link);
        lblUpdateImage.setIcon(new ImageIcon(link));
//        lblUpdateImage.setBounds(0, 0, 648, 697);
//        contentPane.add(lblUpdateImage);
    }
    
    private void goBackImage() {
        if(index>0) {
            index--;
        }else {
            index=4;
        }
        goBack();
    }

    private void goBack() {
        if(sex.equals("남자")) {
            link = "usersImageMale/"+name+"/"+name+index;
            chaneImage();
        }else {
            link = "usersImageFeMale/"+name+"/"+name+index;
            chaneImage();
        }
    }


    private void goNextImage() {
        if(index<4) {
            index++;
        }else {
            index=0;
        }
        goNext();
    }


    private void goNext() {
        if(sex.equals("남자")) {
            link = "usersImageMale/"+name+"/"+name+index;
            chaneImage();
        }else {
            link = "usersImageFeMale/"+name+"/"+name+index;
            chaneImage();
        }
        
    }


    private String showUserImages() {
        String link = null;
        if(sex.equals("남자")) {
            link = "usersImageMale/"+name+"/"+name+index;
        }else {
            link = "usersImageFemale/"+name+"/"+name+index;
        }
        return link;
    }
}
