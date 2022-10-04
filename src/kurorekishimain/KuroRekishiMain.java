package kurorekishimain;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class KuroRekishiMain {

	private JFrame frame;

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
		
		JPanel Login = new JPanel();
		Login.setBounds(0, 0, 578, 850);
		frame.getContentPane().add(Login);
		
		JPanel Join = new JPanel();
		Join.setBounds(0, 0, 578, 850);
		frame.getContentPane().add(Join);
	}
}
