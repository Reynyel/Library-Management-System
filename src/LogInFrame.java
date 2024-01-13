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
import java.util.prefs.Preferences;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.UIManager;

public class LogInFrame extends JFrame {

	private JPanel contentPane;
	private JTextField usernameTxt;
	private JPasswordField passwordTxt;
	private int loginAttempts = 0;
    private long lastLoginAttemptTime = 0;
	private static final int MAX_LOGIN_ATTEMPTS = 5;
    private static long COOLDOWN_TIME = 5 * 60 * 1000; // 5 minutes in milliseconds
    
    private static final String PREFS_NODE_NAME = "com.example.LoginApp";
    private static final String PREFS_LAST_LOGIN_ATTEMPT_TIME = "lastLoginAttemptTime";
    private static final String PREFS_REMAINING_COOLDOWN = "remainingCooldown";
    
    private Preferences prefs;
    
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
		
		// Load preferences
		prefs = Preferences.userRoot().node(PREFS_NODE_NAME);
        loadCooldownInfo();
        
        
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
				if (isLoginCooldown()) {
			        JOptionPane.showMessageDialog(getRootPane(), "Too many failed login attempts. Please try again later");
			        return;
			    }

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
			    
			    if (isValidUser) {
			        // Reset login attempts upon successful login
			        resetLoginAttempts();

			        MainMenuFrame mainFrame = new MainMenuFrame(userType);
			        mainFrame.setVisible(true);
			        dispose();
			    } else {
			        incrementAttempts();

			        // Check if the user has exceeded the maximum login attempts
			        if (loginAttempts >= MAX_LOGIN_ATTEMPTS) {
			            lastLoginAttemptTime = System.currentTimeMillis();
			            JOptionPane.showMessageDialog(getRootPane(), "Too many failed login attempts. Exiting application.");
		                System.exit(0);  // Terminate the application
			        } else {
			            JOptionPane.showMessageDialog(getRootPane(), "Invalid username or password. \n" + " Log in attempt: " + loginAttempts +"/5");
			        }
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
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\LINDELL\\Documents\\GitHub\\Library-Management-System\\Library Management System.png"));
		lblNewLabel.setBounds(0, 0, 750, 487);
		contentPane.add(lblNewLabel);
		
		ImageIcon icon = new ImageIcon("download(1).jpg");

		
	}
	private void loadCooldownInfo() {
        lastLoginAttemptTime = prefs.getLong(PREFS_LAST_LOGIN_ATTEMPT_TIME, 0);
        long remainingCooldown = prefs.getLong(PREFS_REMAINING_COOLDOWN, 0);

        // If there is remaining cooldown, set it
        if (remainingCooldown > 0) {
            long currentTime = System.currentTimeMillis();
            long elapsedCooldown = currentTime - lastLoginAttemptTime;
            if (elapsedCooldown < remainingCooldown) {
                COOLDOWN_TIME = remainingCooldown - elapsedCooldown;
            }
        }
    }
	
	
	private void saveCooldownInfo() {
        prefs.putLong(PREFS_LAST_LOGIN_ATTEMPT_TIME, lastLoginAttemptTime);
        prefs.putLong(PREFS_REMAINING_COOLDOWN, COOLDOWN_TIME);
    }
	
	private void resetLoginAttempts() {
        loginAttempts = 0;
        lastLoginAttemptTime = 0;
        COOLDOWN_TIME = 5 * 60 * 1000; // Reset cooldown time to default
        saveCooldownInfo();
    }
	
	 private boolean isLoginCooldown() {
        if (loginAttempts >= MAX_LOGIN_ATTEMPTS) {
            long currentTime = System.currentTimeMillis();
            return currentTime - lastLoginAttemptTime < COOLDOWN_TIME;
        }
        return false;
    }
	private void incrementAttempts() {
		loginAttempts++;
		saveCooldownInfo();
	}

}
