import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
import javax.swing.UIManager;

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
		setBounds(100, 100, 765, 524);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		this.setTitle("LogIn"); //Sets title for this frame
		this.setResizable(false);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel userLabel = new JLabel("Username");
		userLabel.setBackground(new Color(0, 0, 0));
		userLabel.setForeground(new Color(0, 0, 0));
		userLabel.setBounds(44, 219, 207, 19);
		userLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(userLabel);
		
		JLabel passLabel = new JLabel("Password");
		passLabel.setForeground(new Color(0, 0, 0));
		passLabel.setBounds(44, 291, 207, 19);
		passLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(passLabel);
		
		String username[] = {"admin", "librarian"};
		String password[] = {"admin123", "librarian123"};
		
		JButton logBtn = new JButton("Log In");
		logBtn.setBounds(44, 375, 207, 40);
		logBtn.setForeground(new Color(255, 250, 250));
		logBtn.setBackground(new Color(65, 105, 225));
		logBtn.addActionListener(new ActionListener() {
			/*
			 * Invoked when the log in button is clicked.
			 * Creates and shows the MainMenuFrame while
			 * disposing the LogInFrame*/
			public void actionPerformed(ActionEvent e) {
				String enteredUsername = usernameTxt.getText();
				char[] enteredPasswordChars = passwordTxt.getPassword();
		        String enteredPassword = new String(enteredPasswordChars);
		        String userType = "";
		        
		        boolean isValidUser = false;
		        for (int i = 0; i < username.length; i++) {
		            if (enteredUsername.equals(username[i]) && enteredPassword.equals(password[i])) {
		                isValidUser = true;
		                userType = username[i];
		                break;
		            }
		        }
		        
		        if(isValidUser) {
		        	MainMenuFrame mainFrame = new MainMenuFrame(userType); 
		        	mainFrame.setVisible(true);
		        	dispose();		        	
		        }
		        else {
		        	 JOptionPane.showMessageDialog(getRootPane(), "Invalid username or password. Please try again.");
		        }
		        
		        passwordTxt.setText("");
			}
		});
		logBtn.setFont(new Font("Verdana", Font.PLAIN, 16));
		logBtn.setBorderPainted(false);
		
	
		
		contentPane.add(logBtn);
		
		usernameTxt = new JTextField();
		usernameTxt.setBackground(new Color(173, 216, 230));
		usernameTxt.setBounds(44, 249, 207, 30);
		usernameTxt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(usernameTxt);
		usernameTxt.setColumns(10);
		
		passwordTxt = new JPasswordField();
		passwordTxt.setBackground(new Color(173, 216, 230));
		passwordTxt.setBounds(44, 321, 207, 30);
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
				String userType = "";
				MainMenuFrame mainFrame = new MainMenuFrame(userType);
				mainFrame.setVisible(true);
				dispose();
			}
		});
		btnHidden.setOpaque(false);
		btnHidden.setContentAreaFilled(false);
		btnHidden.setBorderPainted(false);
		contentPane.add(btnHidden);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\pc\\Library-Management-System\\res\\Library Management System.png"));
		lblNewLabel.setBounds(0, 0, 750, 487);
		contentPane.add(lblNewLabel);
		
		ImageIcon icon = new ImageIcon("download(1).jpg");

		
	}
}
