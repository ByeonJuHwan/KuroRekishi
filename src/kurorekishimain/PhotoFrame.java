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

	private JPanel photoPane;
	int index = 0;

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
		JLabel lblNewLabel = new JLabel(new ImageIcon(showImages()));
		lblNewLabel.setBounds(0, 0, 648, 761);
		photoPane.add(lblNewLabel);
		showImages();
	}

	//TODO 2번째사진은 안나옴. 나머지 4장은 겁나잘나옴
	private String showImages() {
		for(int i=0; i<5; i++) {
			if(i==4) {
				index=4;
			}else if(JoinMember.images[i+1]==null) {
				index=i;
				break;
			}
		}
		String a = "usersimage\\변주환\\변주환"+index;
		
		System.out.println(index);
		System.out.println(a);
		
		return a;
	}
}
