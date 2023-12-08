import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.border.*;

import java.awt.Font;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

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
		userLabel.setBackground(new Color(255, 255, 255));
		userLabel.setForeground(new Color(255, 255, 255));
		userLabel.setBounds(85, 273, 71, 30);
		userLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(userLabel);
		
		JLabel passLabel = new JLabel("Password");
		passLabel.setForeground(new Color(255, 255, 255));
		passLabel.setBounds(85, 355, 71, 30);
		passLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(passLabel);
		
		JButton logBtn = new JButton("Log In");
		logBtn.setBounds(276, 440, 107, 43);
		logBtn.setForeground(new Color(0, 0, 0));
		logBtn.setBackground(new Color(188, 143, 143));
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
		usernameTxt.setBackground(new Color(192, 192, 192));
		usernameTxt.setBounds(85, 314, 298, 30);
		usernameTxt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(usernameTxt);
		usernameTxt.setColumns(10);
		
		passwordTxt = new JPasswordField();
		passwordTxt.setBackground(new Color(192, 192, 192));
		passwordTxt.setBounds(85, 396, 298, 33);
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
		
		JLabel lblTitle = new JLabel("Santa Rosa Educational Institution");
		lblTitle.setBackground(new Color(255, 255, 255));
		lblTitle.setForeground(new Color(255, 255, 255));
		lblTitle.setFont(new Font("Microsoft JhengHei UI Light", Font.BOLD, 38));
		lblTitle.setBounds(85, 113, 636, 77);
		contentPane.add(lblTitle);
		
		JLabel lblBottomTitle = new JLabel("Library Management System");
		lblBottomTitle.setForeground(new Color(255, 255, 255));
		lblBottomTitle.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 20));
		lblBottomTitle.setBounds(95, 181, 283, 43);
		contentPane.add(lblBottomTitle);
		
		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setBackground(new Color(192, 192, 192));
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\pc\\Projects\\Library-Management-System\\bg1.jpg"));
		lblNewLabel.setBounds(10, 11, 1036, 630);
		contentPane.add(lblNewLabel);
		
		ImageIcon icon = new ImageIcon("download(1).jpg");

		
	}
}
