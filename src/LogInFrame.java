import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class LogInFrame extends JFrame {

	private JPanel contentPane;
	private JTextField usernameTxt;
	private JPasswordField passwordTxt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogInFrame frame = new LogInFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LogInFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 581, 393);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setLayout(null); //sets layout to absolute
		this.setTitle("LogIn"); //Sets title for this frame
		this.setResizable(false);

		setContentPane(contentPane);
		
		JLabel userLabel = new JLabel("Username");
		userLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		userLabel.setBounds(118, 131, 75, 14);
		contentPane.add(userLabel);
		
		JLabel passLabel = new JLabel("Password");
		passLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		passLabel.setBounds(117, 171, 75, 14);
		contentPane.add(passLabel);
		
		JButton logBtn = new JButton("Log In");
		logBtn.setForeground(new Color(255, 255, 255));
		logBtn.setBackground(new Color(157, 179, 227));
		logBtn.addActionListener(new ActionListener() {
			/*
			 * Invoked when the log in button is clicked.
			 * Creates and shows the MainMenuFrame while
			 * disposing the LogInFrame*/
			public void actionPerformed(ActionEvent e) {
				MainMenuFrame mainFrame = new MainMenuFrame(); 
				mainFrame.setVisible(true);
				dispose();
			}
		});
		logBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		logBtn.setBounds(248, 211, 95, 33);
		logBtn.setBorderPainted(false);
		contentPane.add(logBtn);
		
		usernameTxt = new JTextField();
		usernameTxt.setFont(new Font("Tahoma", Font.PLAIN, 14));
		usernameTxt.setBounds(203, 130, 212, 20);
		contentPane.add(usernameTxt);
		usernameTxt.setColumns(10);
		
		passwordTxt = new JPasswordField();
		passwordTxt.setFont(new Font("Tahoma", Font.PLAIN, 14));
		passwordTxt.setBounds(202, 170, 212, 20);
		contentPane.add(passwordTxt);
		
		JButton btnHidden = new JButton("");
		btnHidden.addActionListener(new ActionListener() {
			/*
			 * A hidden button that will
			 * show the main menu frame
			 * when clicked*/
			public void actionPerformed(ActionEvent e) {
				MainMenuFrame mainFrame = new MainMenuFrame();
				mainFrame.setVisible(true);
				dispose();
			}
		});
		btnHidden.setBounds(430, 176, 15, 14);
		btnHidden.setOpaque(false);
		btnHidden.setContentAreaFilled(false);
		btnHidden.setBorderPainted(false);
		contentPane.add(btnHidden);
	}
}
