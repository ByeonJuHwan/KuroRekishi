package kurorekishimain;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;

public class KuroRekishiMain {

	private JFrame frame;
	private JLabel lblImage;

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
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 594, 889);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel Main = new JPanel();
		Main.setBounds(0, 0, 578, 850);
		frame.getContentPane().add(Main);
		Main.setLayout(null);
		
		lblImage = new JLabel(new ImageIcon("appImage/사진.png"));
		lblImage.setBounds(0, 0, 578, 850);
		Main.add(lblImage);
		
		JPanel Login = new JPanel();
		Login.setBounds(0, 0, 578, 850);
		frame.getContentPane().add(Login);
		
		JPanel Join = new JPanel();
		Join.setBounds(0, 0, 578, 850);
		frame.getContentPane().add(Join);
	}
}
