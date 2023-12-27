import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import notification.Notification;
import panel.Backup;
//import raven.glasspanepopup.DefaultOption;
//import raven.glasspanepopup.GlassPanePopup;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import java.awt.Dimension;

public class MainMenuFrame extends JFrame {

	private JPanel contentPane;
	private Dashboard dash_1;
	private String userType;
	private String loginTime; // New variable to store login time
	private JLabel l_date, l_time, l_log;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenuFrame frame = new MainMenuFrame("");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void dt() {
		// Create a format for the date in the file name
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	    // Get the current date and format it
	    String currentDate = dateFormat.format(new Date());

		l_date.setText(currentDate);
		
		
	}
	
	public void updateTime() {
	    Timer t;
	    
	    t = new Timer(100, new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            // Use try-catch to handle potential exceptions
	            try {
	                SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
	                String currentTime = timeFormat.format(new Date());

	                l_time.setText(currentTime);
	            } catch (Exception ex) {
	                ex.printStackTrace();
	            }
	        }
	    });

	    // Start the timer
	    t.start();
	}

	/**
	 * Create the frame.
	 */
	public MainMenuFrame(String userType) {

		this.userType = userType;
		
		       
		setBackground(new Color(255, 255, 255));
		setResizable(false);
		setTitle("Library Management System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1510, 775);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 255, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 51, 102));
		panel.setBounds(0, 0, 240, 736);
		contentPane.add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 51, 102));
		panel_1.setForeground(new Color(255, 255, 255));
		panel_1.setBounds(239, 0, 1255, 50);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JPanel contentPanel = new JPanel();
		contentPanel.setBackground(new Color(0, 0, 0));
		contentPanel.setBorder(null);
		contentPanel.setBounds(239, 50, 1255, 686);
		contentPane.add(contentPanel);
		
		JLabel lblPageTitle = new JLabel("Dashboard");
		lblPageTitle.setForeground(new Color(255, 255, 255));
		lblPageTitle.setHorizontalAlignment(SwingConstants.LEFT);
		lblPageTitle.setFont(new Font("Monospaced", Font.BOLD, 23));
		lblPageTitle.setBounds(10, 11, 483, 30);
		panel_1.add(lblPageTitle);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 240, 100);
		panel.add(lblNewLabel);
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\pc\\Library-Management-System\\Santa Rosa Educational Institution.png"));
		
		JButton btnRegisterBooks = new JButton(" Register Books");
		btnRegisterBooks.setBounds(14, 221, 218, 41);
		panel.add(btnRegisterBooks);
		btnRegisterBooks.setHorizontalAlignment(SwingConstants.LEFT);
		btnRegisterBooks.setIcon(new ImageIcon("C:\\Users\\pc\\Library-Management-System\\book (1).png"));
		btnRegisterBooks.setForeground(new Color(255, 255, 255));
		btnRegisterBooks.setFont(new Font("Verdana", Font.PLAIN, 15));
		btnRegisterBooks.setBackground(new Color(0, 51, 102));
		btnRegisterBooks.setBorderPainted(false);
		
		JButton btnBackupRecords = new JButton(" Backup Records");
		btnBackupRecords.setIcon(new ImageIcon("C:\\Users\\pc\\Library-Management-System\\backup.png"));
		btnBackupRecords.setBounds(12, 426, 220, 40);
		panel.add(btnBackupRecords);
		btnBackupRecords.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Backup backup = new Backup();
				backup = new Backup();
				backup.setBounds(250, 50, 1256, 686);
				backup.setLayout(null);  // Set layout to null
	            contentPanel.removeAll();  // Remove existing components
	            contentPanel.add(backup);
	            contentPanel.revalidate();  // Revalidate the panel to reflect changes
	            contentPanel.repaint();  // Repaint the panel
	            lblPageTitle.setText("Backup Records");
			}
		});
		btnBackupRecords.setHorizontalAlignment(SwingConstants.LEFT);
		btnBackupRecords.setForeground(Color.WHITE);
		btnBackupRecords.setFont(new Font("Verdana", Font.PLAIN, 15));
		btnBackupRecords.setBorderPainted(false);
		btnBackupRecords.setBackground(new Color(0, 51, 102));
		
		JButton btnReturnBooks = new JButton(" Returning");
		btnReturnBooks.setBounds(12, 375, 220, 40);
		panel.add(btnReturnBooks);
		btnReturnBooks.setHorizontalAlignment(SwingConstants.LEFT);
		btnReturnBooks.setIcon(new ImageIcon("C:\\Users\\pc\\Library-Management-System\\return.png"));
		btnReturnBooks.setForeground(new Color(255, 255, 255));
		btnReturnBooks.setFont(new Font("Verdana", Font.PLAIN, 15));
		btnReturnBooks.setBackground(new Color(0, 51, 102));
		btnReturnBooks.setBorderPainted(false);			                  		
		
		JButton btnTransac = new JButton(" Transactions");
		btnTransac.setBounds(12, 324, 220, 40);
		panel.add(btnTransac);
		btnTransac.setIcon(new ImageIcon("C:\\Users\\pc\\Library-Management-System\\transaction.png"));
		btnTransac.setHorizontalAlignment(SwingConstants.LEFT);
		btnTransac.setForeground(new Color(255, 255, 255));
		btnTransac.setFont(new Font("Verdana", Font.PLAIN, 15));
		btnTransac.setBackground(new Color(0, 51, 102));
		btnTransac.setBorderPainted(false);
		
		JButton btnRegisterUser = new JButton(" Manage Users");
		btnRegisterUser.setBounds(12, 273, 220, 40);
		panel.add(btnRegisterUser);
		btnRegisterUser.setHorizontalAlignment(SwingConstants.LEFT);
		btnRegisterUser.setIcon(new ImageIcon("C:\\Users\\pc\\Library-Management-System\\management.png"));
		btnRegisterUser.setForeground(new Color(255, 255, 255));
		btnRegisterUser.setFont(new Font("Verdana", Font.PLAIN, 15));
		
		JPopupMenu popupMenu = new JPopupMenu();
		JMenuItem btnStudents = new JMenuItem("Students");
        JMenuItem btnStaff = new JMenuItem("Faculty/Staff");
        
        popupMenu.add(btnStudents);
        popupMenu.add(btnStaff);
		
		btnRegisterUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				popupMenu.show(btnRegisterUser, 0, btnRegisterUser.getHeight());
			}
		});
		btnRegisterUser.setBackground(new Color(0, 51, 102));
		btnRegisterUser.setBorderPainted(false);
		
		JPanel UserPanel = new JPanel();
		UserPanel.setBackground(new Color(0, 51, 102));
		UserPanel.setForeground(new Color(0, 51, 102));
		UserPanel.setBounds(12, 539, 220, 186);
		panel.add(UserPanel);
		UserPanel.setLayout(null);
		
		JLabel lbluserIcon = new JLabel("");
		lbluserIcon.setIcon(new ImageIcon("C:\\Users\\pc\\Library-Management-System\\librarian.png"));
		lbluserIcon.setBounds(10, 11, 32, 32);
		UserPanel.add(lbluserIcon);
		
		JLabel lblNewLabel_2 = new JLabel("User:");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(51, 11, 42, 32);
		UserPanel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Date:");
		lblNewLabel_2_1.setForeground(Color.WHITE);
		lblNewLabel_2_1.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblNewLabel_2_1.setBounds(10, 54, 56, 18);
		UserPanel.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Login Time:");
		lblNewLabel_2_1_1.setForeground(Color.WHITE);
		lblNewLabel_2_1_1.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblNewLabel_2_1_1.setBounds(10, 112, 83, 18);
		UserPanel.add(lblNewLabel_2_1_1);
		
		JLabel lblUserType = new JLabel("Librarian");
		lblUserType.setHorizontalAlignment(SwingConstants.LEFT);
		lblUserType.setForeground(Color.WHITE);
		lblUserType.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblUserType.setBounds(91, 11, 107, 32);
		lblUserType.setText(userType);
		
		UserPanel.add(lblUserType);
		
		JButton btnLogOut = new JButton("Sign Out");
		btnLogOut.setBounds(0, 143, 220, 32);
		UserPanel.add(btnLogOut);
		btnLogOut.setIcon(new ImageIcon("C:\\Users\\pc\\Library-Management-System\\exit (3).png"));
		btnLogOut.setFont(new Font("Verdana", Font.PLAIN, 15));
		btnLogOut.setBackground(new Color(0, 51, 102));
		btnLogOut.setForeground(new Color(255, 255, 255));
		
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LogInFrame logInFrame = new LogInFrame();
				logInFrame.setVisible(true);
				dispose();
			}
		});
		btnLogOut.setBorderPainted(false);
		
		JLabel lblNewLabel_2_1_2 = new JLabel("Time:");
		lblNewLabel_2_1_2.setForeground(Color.WHITE);
		lblNewLabel_2_1_2.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblNewLabel_2_1_2.setBounds(10, 83, 56, 18);
		UserPanel.add(lblNewLabel_2_1_2);
		
		l_date = new JLabel("0");
		l_date.setForeground(Color.WHITE);
		l_date.setFont(new Font("Verdana", Font.PLAIN, 14));
		l_date.setBounds(76, 54, 122, 18);
		UserPanel.add(l_date);
		
		l_time = new JLabel("0");
		l_time.setForeground(Color.WHITE);
		l_time.setFont(new Font("Verdana", Font.PLAIN, 14));
		l_time.setBounds(76, 83, 122, 18);
		UserPanel.add(l_time);
		
		l_log = new JLabel("0");
		l_log.setForeground(Color.WHITE);
		l_log.setFont(new Font("Verdana", Font.PLAIN, 14));
		l_log.setBounds(100, 112, 113, 18);
		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        loginTime = timeFormat.format(new Date());
        l_log.setText(loginTime);
		UserPanel.add(l_log);
		
		JButton btnDashboard = new JButton(" Dashboard");
		btnDashboard.setIcon(new ImageIcon("C:\\Users\\pc\\Library-Management-System\\webpage.png"));
		btnDashboard.setHorizontalAlignment(SwingConstants.LEFT);
		btnDashboard.setForeground(Color.WHITE);
		btnDashboard.setFont(new Font("Verdana", Font.PLAIN, 15));
		btnDashboard.setBorderPainted(false);
		btnDashboard.setBackground(new Color(0, 51, 102));
		btnDashboard.setBounds(14, 169, 218, 41);
		panel.add(btnDashboard);
		
    	dash_1 = new Dashboard();
    	dash_1.setBackground(new Color(0, 0, 0));
    	dash_1.setBounds(25, 0, 1256, 686);
    	dash_1.setLayout(null);  // Set layout to null
        contentPanel.removeAll();  // Remove existing components
        contentPanel.add(dash_1);
        contentPanel.revalidate();  // Revalidate the panel to reflect changes
        contentPanel.repaint();  // Repaint the panel
        lblPageTitle.setText("Dashboard");
		

		btnTransac.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TransactionFrame transacFrame;
				transacFrame = new TransactionFrame();
				transacFrame.setBounds(25, 0, 1256, 686);
				transacFrame.setLayout(null);  // Set layout to null
	            contentPanel.removeAll();  // Remove existing components
	            contentPanel.add(transacFrame);
	            contentPanel.revalidate();  // Revalidate the panel to reflect changes
	            contentPanel.repaint();  // Repaint the panel
	            lblPageTitle.setText("Transactions");
			}
		});
		
		btnReturnBooks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LendingBooksFrame returning = new LendingBooksFrame();
				returning = new LendingBooksFrame();
				returning.setBounds(25, 0, 1256, 686);
				returning.setLayout(null);  // Set layout to null
	            contentPanel.removeAll();  // Remove existing components
	            contentPanel.add(returning);
	            contentPanel.revalidate();  // Revalidate the panel to reflect changes
	            contentPanel.repaint();  // Repaint the panel
	            lblPageTitle.setText("Book Returns");
			}
		});
		
		btnRegisterBooks.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				RegisterBooksFrame registerFrame;
				try {
		            registerFrame = new RegisterBooksFrame();
		            registerFrame.setBounds(25, 25, 1256, 686);
		            registerFrame.setLayout(null);  // Set layout to null
		            contentPanel.removeAll();  // Remove existing components
		            contentPanel.add(registerFrame);
		            contentPanel.revalidate();  // Revalidate the panel to reflect changes
		            contentPanel.repaint();  // Repaint the panel
		            lblPageTitle.setText("Book Registration");
		        } catch (SQLException e1) {
		            e1.printStackTrace();
		        }
			}
		});
		
		btnStudents.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	RegisterStudent student = new RegisterStudent();
            	student = new RegisterStudent();
            	student.setBounds(25, 0, 1256, 686);
            	student.setLayout(null);  // Set layout to null
	            contentPanel.removeAll();  // Remove existing components
	            contentPanel.add(student);
	            contentPanel.revalidate();  // Revalidate the panel to reflect changes
	            contentPanel.repaint();  // Repaint the panel
	            lblPageTitle.setText("Student Registration");
            }
        });

		btnStaff.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	RegisterStaff staff = new RegisterStaff();
            	staff = new RegisterStaff();
            	staff.setBounds(25, 0, 1256, 686);
            	staff.setLayout(null);  // Set layout to null
	            contentPanel.removeAll();  // Remove existing components
	            contentPanel.add(staff);
	            contentPanel.revalidate();  // Revalidate the panel to reflect changes
	            contentPanel.repaint();  // Repaint the panel
	            lblPageTitle.setText("Staff/Faculty Registration");

            }
        });
		
		btnDashboard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	Dashboard dash = new Dashboard();
            	dash = new Dashboard();
            	dash.setBounds(25, 0, 1256, 686);
            	dash.setLayout(null);  // Set layout to null
	            contentPanel.removeAll();  // Remove existing components
	            contentPanel.add(dash);
	            contentPanel.revalidate();  // Revalidate the panel to reflect changes
	            contentPanel.repaint();  // Repaint the panel
	            lblPageTitle.setText("Dashboard");

            }
        });
		
		dt();
		updateTime();
		
		/**
		GlassPanePopup.install(this);
		JButton btnNotif = new JButton(" Notifications");
		btnNotif.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Notification newNotifs = new Notification();
				
				GlassPanePopup.showPopup(newNotifs, new DefaultOption() {
					public float opacity() {
						return 0;
					}
				});
			}
		});
		
		btnNotif.setForeground(new Color(255, 255, 255));
		btnNotif.setBackground(new Color(0, 51, 102));
		btnNotif.setFont(new Font("Verdana", Font.PLAIN, 14));
		btnNotif.setHorizontalAlignment(SwingConstants.LEFT);
		btnNotif.setIcon(new ImageIcon("C:\\Users\\pc\\Library-Management-System\\bell.png"));
		btnNotif.setBounds(1074, 11, 171, 32);
		btnNotif.setBorderPainted(false);
		panel_1.add(btnNotif);*/
		
		
		
	}
}
