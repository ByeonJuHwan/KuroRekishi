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

public class PhotoFrame extends JFrame {
	private String name;
	private String sex;
	private JPanel photoPane;
	private Component parent;
	int index = 0;

	/**
	 * Launch the application.
	 * @param parent 
	 * @param name 
	 * @param sex 
	 */
	public static void newPhotoFrame(Component parent, String name, String sex) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				PhotoFrame frame = new PhotoFrame(parent,name,sex);
				frame.setVisible(true);
			}
		});
	}

	public PhotoFrame(Component parent, String name, String sex) {
		this.parent = parent;
		this.name = name;
		this.sex = sex;
		initialize();
	}
	
	/**
	 * Create the frame.
	 */
	public void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		int x = parent.getX(); // 부모창 의 X 좌표
        int y = parent.getY(); // 부모창 의 Y 좌표
		setBounds(x, y, 664, 800);
		photoPane = new JPanel();
		photoPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setTitle("사진 미리보기");
		setResizable(false);
		
		setContentPane(photoPane);
		photoPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel(new ImageIcon(showImages()));
		lblNewLabel.setBounds(0, 0, 648, 761);
		photoPane.add(lblNewLabel);
		showImages();
	}

	
	private String showImages() {
		String image = null;
		
		for(int i=0; i<5; i++) {
			if(i==4) {
				index=4;
			}else if(JoinMember.images[i+1]==null) {
				index=i;
				break;
			}
		}
		
		
		if(sex.equals("남자")) {
			image = "usersImageMale\\"+name+"\\"+name+index;
			
			System.out.println(index);
			System.out.println(image);
			
		}else if(sex.equals("여자")) {
			image = "usersImageFemale\\"+name+"\\"+name+index;
			
			System.out.println(index);
			System.out.println(image);
			
		}
		return image;
	}
}
