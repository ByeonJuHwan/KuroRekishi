package kurorekishimain;

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

public class PhotoFrame extends JFrame {
	private static final BufferedImage[] images =  new BufferedImage[5];
	
	int index=0;
	
	private JPanel photoPane;
	

	/**
	 * Launch the application.
	 */
	public static void newPhotoFrame() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				PhotoFrame frame = new PhotoFrame();
				frame.setVisible(true);
			}
		});
	}

	public PhotoFrame() {
		initialize();
	}
	
	/**
	 * Create the frame.
	 */
	public void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 664, 800);
		photoPane = new JPanel();
		photoPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(photoPane);
		photoPane.setLayout(null);
		
		// TODO 저장한 이미지를 보여주기
		JLabel lblNewLabel = new JLabel(new ImageIcon("usersimage\\"));
		lblNewLabel.setBounds(0, 0, 648, 696);
		photoPane.add(lblNewLabel);
		
		JButton btnBack1 = new JButton("뒤로가기");
		btnBack1.setFont(new Font("D2Coding", Font.BOLD, 14));
		btnBack1.setBounds(114, 706, 115, 45);
		photoPane.add(btnBack1);
		
		JButton btnClearMember = new JButton("완료");
		btnClearMember.setFont(new Font("D2Coding", Font.BOLD, 14));
		btnClearMember.setBounds(400, 706, 115, 45);
		photoPane.add(btnClearMember);
		
		JButton btnInsertImages = new JButton("사진저장");
		btnInsertImages.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertPhoto();
			}
		});
		btnInsertImages.setFont(new Font("D2Coding", Font.BOLD, 14));
		btnInsertImages.setBounds(259, 706, 115, 45);
		photoPane.add(btnInsertImages);
	}

	private void insertPhoto() {
		
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
		File newFolder = new File("usersimage","변주환");
		if(!newFolder.exists()) {
		    newFolder.mkdir();
		}
        

        File userImages  = new File("usersimage\\변주환", "변주환"+index);
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
                JOptionPane.showMessageDialog(null, "현재("+(index)+"/5) 장", "확인", JOptionPane.PLAIN_MESSAGE);
            } catch (Exception e) {
                e.printStackTrace();
            }	
		}
	}
}
