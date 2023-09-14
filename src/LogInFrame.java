import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.border.*;

import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Component;

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
		setBounds(100, 100, 1072, 693);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		this.setTitle("LogIn"); //Sets title for this frame
		this.setResizable(false);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel userLabel = new JLabel("Username");
		userLabel.setBounds(351, 295, 75, 14);
		userLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(userLabel);
		
		JLabel passLabel = new JLabel("Password");
		passLabel.setBounds(350, 335, 75, 14);
		passLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(passLabel);
		
		JButton logBtn = new JButton("Log In");
		logBtn.setBounds(481, 375, 95, 33);
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
		logBtn.setBorderPainted(false);
		
	
		
		contentPane.add(logBtn);
		
		usernameTxt = new JTextField();
		usernameTxt.setBounds(436, 294, 212, 20);
		usernameTxt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(usernameTxt);
		usernameTxt.setColumns(10);
		
		passwordTxt = new JPasswordField();
		passwordTxt.setBounds(435, 334, 212, 20);
		passwordTxt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(passwordTxt);
		
		JButton btnHidden = new JButton("");
		btnHidden.setBounds(430, 176, 15, 14);
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
		btnHidden.setOpaque(false);
		btnHidden.setContentAreaFilled(false);
		btnHidden.setBorderPainted(false);
		contentPane.add(btnHidden);
		
		JLabel lblNewLabel = new JLabel("SREI Santa Rosa");
		lblNewLabel.setFont(new Font("Tw Cen MT", Font.PLAIN, 51));
		lblNewLabel.setBounds(286, 91, 362, 125);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Library ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblNewLabel_1.setBounds(648, 79, 82, 60);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Management");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblNewLabel_1_1.setBounds(648, 130, 157, 60);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("System");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblNewLabel_1_2.setBounds(648, 178, 82, 60);
		contentPane.add(lblNewLabel_1_2);
	}
}
