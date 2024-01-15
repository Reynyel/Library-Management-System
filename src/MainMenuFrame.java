import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import notification.Notification;
import panel.BackupDB;
//import raven.glasspanepopup.DefaultOption;
//import raven.glasspanepopup.GlassPanePopup;
import panel.BackupDB;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import java.awt.Dimension;
import java.awt.ScrollPane;
import java.awt.Toolkit;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.Label;
import java.awt.RenderingHints;

import com.toedter.calendar.JDateChooser;

import GradientBackground.gradientBackground;

import com.toedter.calendar.JCalendar;

public class MainMenuFrame extends JFrame {

	private JPanel contentPane, contentPanel;
	private String userType;
	private String loginTime; // New variable to store login time
	private static JLabel lblUserType;
	private JLabel l_date;
	private JLabel l_time;
	private JLabel l_log;
	private JLabel lblPageTitle;
	
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
		// Calculate the center of the screen
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int centerX = (screenSize.width - getWidth()) / 2;
		int centerY = (screenSize.height - getHeight()) / 2;
		
		// Set the frame location
		setLocation(centerX, centerY);
		this.userType = userType;
		
		       
		setBackground(new Color(255, 255, 255));
		setResizable(false);
		setTitle("Library Management System");
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 1680, 1050);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 255, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 51, 102));
		panel.setBounds(0, 0, 240, 1018);
		contentPane.add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 51, 102));
		panel_1.setForeground(new Color(255, 255, 255));
		panel_1.setBounds(239, 0, 1425, 50);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		contentPanel = new gradientBackground();
		contentPanel.setBorder(null);
		contentPanel.setBounds(478, 246, 1425, 980);
		contentPane.add(contentPanel);
		
		lblPageTitle = new JLabel("Dashboard");
		lblPageTitle.setForeground(new Color(255, 255, 255));
		lblPageTitle.setHorizontalAlignment(SwingConstants.LEFT);
		lblPageTitle.setFont(new Font("Monospaced", Font.BOLD, 23));
		lblPageTitle.setBounds(10, 11, 483, 30);
		panel_1.add(lblPageTitle);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 240, 100);
		panel.add(lblNewLabel);
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\LINDELL\\Projects\\Library-Management-System\\res\\Santa Rosa Educational Institution.png"));
		
		JButton btnRegisterBooks = new JButton("Manage Books");
		btnRegisterBooks.setBounds(14, 274, 218, 41);
		panel.add(btnRegisterBooks);
		btnRegisterBooks.setHorizontalAlignment(SwingConstants.LEFT);
		btnRegisterBooks.setIcon(new ImageIcon("C:\\Users\\LINDELL\\Projects\\Library-Management-System\\res\\book (1).png"));
		btnRegisterBooks.setForeground(new Color(255, 255, 255));
		btnRegisterBooks.setFont(new Font("Verdana", Font.BOLD, 15));
		btnRegisterBooks.setBackground(new Color(0, 51, 102));
		btnRegisterBooks.setBorderPainted(false);
		
		
		JButton btnBackupRecords = new JButton(" Backup & Import");
		btnBackupRecords.setIcon(new ImageIcon("C:\\Users\\LINDELL\\Projects\\Library-Management-System\\res\\backup.png"));
		btnBackupRecords.setBounds(14, 481, 220, 40);
		panel.add(btnBackupRecords);						
		
		btnBackupRecords.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BackupDB backup;
				backup = new BackupDB();
				backup.setBounds(0, 0, 1256, 686);
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
		btnBackupRecords.setFont(new Font("Verdana", Font.BOLD, 15));
		btnBackupRecords.setBorderPainted(false);
		btnBackupRecords.setBackground(new Color(0, 51, 102));
		
		JButton btnReturnBooks = new JButton(" Returning");
		btnReturnBooks.setBounds(14, 430, 220, 40);
		panel.add(btnReturnBooks);
		btnReturnBooks.setHorizontalAlignment(SwingConstants.LEFT);
		btnReturnBooks.setIcon(new ImageIcon("C:\\Users\\LINDELL\\Projects\\Library-Management-System\\res\\return.png"));
		btnReturnBooks.setForeground(new Color(255, 255, 255));
		btnReturnBooks.setFont(new Font("Verdana", Font.BOLD, 15));
		btnReturnBooks.setBackground(new Color(0, 51, 102));
		btnReturnBooks.setBorderPainted(false);			                  		
		
		JButton btnTransac = new JButton(" Lending");
		btnTransac.setBounds(14, 379, 220, 40);
		panel.add(btnTransac);
		btnTransac.setIcon(new ImageIcon("C:\\Users\\LINDELL\\Projects\\Library-Management-System\\res\\transaction.png"));
		btnTransac.setHorizontalAlignment(SwingConstants.LEFT);
		btnTransac.setForeground(new Color(255, 255, 255));
		btnTransac.setFont(new Font("Verdana", Font.BOLD, 15));
		btnTransac.setBackground(new Color(0, 51, 102));
		btnTransac.setBorderPainted(false);
		
		JButton btnRegisterUser = new JButton(" Manage Users");
		btnRegisterUser.setBounds(14, 328, 220, 40);
		panel.add(btnRegisterUser);
		btnRegisterUser.setHorizontalAlignment(SwingConstants.LEFT);
		btnRegisterUser.setIcon(new ImageIcon("C:\\Users\\LINDELL\\Projects\\Library-Management-System\\res\\management.png"));
		btnRegisterUser.setForeground(new Color(255, 255, 255));
		btnRegisterUser.setFont(new Font("Verdana", Font.BOLD, 15));
		
		JPopupMenu popupMenu = new JPopupMenu();
		JMenuItem btnStudents = new JMenuItem("Students");
        JMenuItem btnStaff = new JMenuItem("Faculty/Staff");
        JMenuItem btnBlocked = new JMenuItem("Blocked Users");
        
        btnStudents.setFont(new Font("Verdana", Font.PLAIN, 15));
        btnStaff.setFont(new Font("Verdana", Font.PLAIN, 15));
        btnBlocked.setFont(new Font("Verdana", Font.PLAIN, 15));
        popupMenu.add(btnStudents);
        popupMenu.add(btnStaff);
        popupMenu.add(btnBlocked);
			
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
		UserPanel.setBounds(14, 728, 220, 203);
		panel.add(UserPanel);
		UserPanel.setLayout(null);
		
		JLabel lbluserIcon = new JLabel("");
		lbluserIcon.setIcon(new ImageIcon("C:\\Users\\pc\\Library-Management-System\\librarian.png"));
		lbluserIcon.setBounds(10, 11, 32, 32);
		UserPanel.add(lbluserIcon);
		
		JLabel lblNewLabel_2 = new JLabel("User:");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setFont(new Font("Verdana", Font.BOLD, 15));
		lblNewLabel_2.setBounds(51, 11, 56, 32);
		UserPanel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Date:");
		lblNewLabel_2_1.setForeground(Color.WHITE);
		lblNewLabel_2_1.setFont(new Font("Verdana", Font.BOLD, 15));
		lblNewLabel_2_1.setBounds(10, 76, 56, 18);
		UserPanel.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Login Time:");
		lblNewLabel_2_1_1.setForeground(Color.WHITE);
		lblNewLabel_2_1_1.setFont(new Font("Verdana", Font.BOLD, 15));
		lblNewLabel_2_1_1.setBounds(10, 134, 97, 18);
		UserPanel.add(lblNewLabel_2_1_1);
		
		lblUserType = new JLabel("Librarian");
		lblUserType.setHorizontalAlignment(SwingConstants.LEFT);
		lblUserType.setForeground(Color.WHITE);
		lblUserType.setFont(new Font("Verdana", Font.PLAIN, 15));
		lblUserType.setBounds(108, 11, 107, 32);
		lblUserType.setText(userType);
		
		UserPanel.add(lblUserType);
		
		JButton btnLogOut = new JButton("Sign Out");
		btnLogOut.setBounds(0, 163, 220, 32);
		UserPanel.add(btnLogOut);
		btnLogOut.setIcon(new ImageIcon("C:\\Users\\LINDELL\\Projects\\Library-Management-System\\res\\exit (3).png"));
		btnLogOut.setFont(new Font("Verdana", Font.PLAIN, 20));
		btnLogOut.setBackground(new Color(0, 51, 102));
		btnLogOut.setForeground(new Color(255, 255, 255));
		
		
		btnLogOut.setBorderPainted(false);
		
		JLabel lblNewLabel_2_1_2 = new JLabel("Time:");
		lblNewLabel_2_1_2.setForeground(Color.WHITE);
		lblNewLabel_2_1_2.setFont(new Font("Verdana", Font.BOLD, 15));
		lblNewLabel_2_1_2.setBounds(10, 105, 56, 18);
		UserPanel.add(lblNewLabel_2_1_2);
		
		l_date = new JLabel("0");
		l_date.setForeground(Color.WHITE);
		l_date.setFont(new Font("Verdana", Font.PLAIN, 15));
		l_date.setBounds(108, 76, 102, 18);
		UserPanel.add(l_date);
		
		l_time = new JLabel("0");
		l_time.setForeground(Color.WHITE);
		l_time.setFont(new Font("Verdana", Font.PLAIN, 15));
		l_time.setBounds(108, 105, 102, 18);
		UserPanel.add(l_time);
		
		l_log = new JLabel("0");
		l_log.setForeground(Color.WHITE);
		l_log.setFont(new Font("Verdana", Font.PLAIN, 15));
		l_log.setBounds(107, 134, 113, 18);
		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        loginTime = timeFormat.format(new Date());
        l_log.setText(loginTime);
		UserPanel.add(l_log);
		
		JLabel lblAcadYear = new JLabel("2023-2024");
		Year currentYear = Year.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy");
		String formattedYear = currentYear.format(formatter);
		lblAcadYear.setForeground(Color.WHITE);
		lblAcadYear.setFont(new Font("Verdana", Font.PLAIN, 15));
		lblAcadYear.setBounds(101, 47, 97, 18);
		AcademicYear ya = AcademicYear.now( ZoneId.systemDefault( ));
		String formattedAcadYear = ya.format( FormatStyle.FULL);
		lblAcadYear.setText(formattedAcadYear);
		UserPanel.add(lblAcadYear);
		
		JLabel lblNewLabel_2_1_2_1 = new JLabel("Acad Yr:");
		lblNewLabel_2_1_2_1.setForeground(Color.WHITE);
		lblNewLabel_2_1_2_1.setFont(new Font("Verdana", Font.BOLD, 15));
		lblNewLabel_2_1_2_1.setBounds(10, 47, 83, 18);
		UserPanel.add(lblNewLabel_2_1_2_1);
		
		JButton btnDashboard = new JButton(" Dashboard");
		btnDashboard.setIcon(new ImageIcon("C:\\Users\\LINDELL\\Projects\\Library-Management-System\\res\\webpage.png"));
		btnDashboard.setHorizontalAlignment(SwingConstants.LEFT);
		btnDashboard.setForeground(Color.WHITE);
		btnDashboard.setFont(new Font("Verdana", Font.BOLD, 15));
		btnDashboard.setBorderPainted(false);
		btnDashboard.setBackground(new Color(0, 51, 102));
		btnDashboard.setBounds(14, 222, 218, 41);
		panel.add(btnDashboard);
		
		JButton btnLogs = new JButton(" Log-in Trail");
		btnLogs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Logs viewLogs = new Logs();
				viewLogs.setBounds(0, 0, 1256, 686);
				viewLogs.setLayout(null);  // Set layout to null
	            contentPanel.removeAll();  // Remove existing components
	            contentPanel.add(viewLogs);
	            contentPanel.revalidate();  // Revalidate the panel to reflect changes
	            contentPanel.repaint();  // Repaint the panel
	            lblPageTitle.setText("Login Audit Trail");
			}
		});
		
		btnLogs.setIcon(new ImageIcon("C:\\Users\\LINDELL\\Projects\\Library-Management-System\\res\\logimg-small.png"));
		btnLogs.setHorizontalAlignment(SwingConstants.LEFT);
		btnLogs.setForeground(Color.WHITE);
		btnLogs.setFont(new Font("Verdana", Font.BOLD, 15));
		btnLogs.setBorderPainted(false);
		btnLogs.setBackground(new Color(0, 51, 102));
		btnLogs.setBounds(14, 542, 220, 40);
		panel.add(btnLogs);
        contentPanel.removeAll();  // Remove existing components
        contentPanel.setLayout(null);
        contentPanel.revalidate();  // Revalidate the panel to reflect changes
        contentPanel.repaint();  // Repaint the panel
        lblPageTitle.setText("Dashboard");
        
        
        
        
        // Check the user's role
     		if ("Librarian".equalsIgnoreCase(userType)) {
     		    // If the user is a librarian, disable and make the button invisible
     		    btnBackupRecords.setEnabled(false);
     		    btnBackupRecords.setVisible(false);
     		    btnLogs.setEnabled(false);
     		    btnLogs.setVisible(false);
     		} else if ("Admin".equalsIgnoreCase(userType)) {
     		    // If the user is an admin, enable and make the button visible
     		    btnBackupRecords.setEnabled(true);
     		    btnBackupRecords.setVisible(true);
     		    btnLogs.setEnabled(true);
     		    btnLogs.setVisible(true);

     		}
        

		btnTransac.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TransactionFrame transacFrame;
				transacFrame = new TransactionFrame();
				transacFrame.setBounds(0, 0, 1256, 686);
				transacFrame.setLayout(null);  // Set layout to null
	            contentPanel.removeAll();  // Remove existing components
	            contentPanel.add(transacFrame);
	            contentPanel.revalidate();  // Revalidate the panel to reflect changes
	            contentPanel.repaint();  // Repaint the panel
	            lblPageTitle.setText("Book Lending");
			}
		});
		
		btnReturnBooks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LendingBooksFrame returning = new LendingBooksFrame();
				returning = new LendingBooksFrame();
				returning.setBounds(0, 0, 1256, 686);
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
		            registerFrame.setBounds(0, 0, 1256, 686);
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
            	student.setBounds(0, 0, 1256, 686);
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
            	staff.setBounds(0, 0, 1256, 686);
            	staff.setLayout(null);  // Set layout to null
	            contentPanel.removeAll();  // Remove existing components
	            contentPanel.add(staff);
	            contentPanel.revalidate();  // Revalidate the panel to reflect changes
	            contentPanel.repaint();  // Repaint the panel
	            lblPageTitle.setText("Staff/Faculty Registration");

            }
        });
		
		btnBlocked.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	BlockedUsers blocked = new BlockedUsers();
            	blocked = new BlockedUsers();
            	blocked.setBounds(0, 0, 1256, 686);
            	blocked.setLayout(null);  // Set layout to null
	            contentPanel.removeAll();  // Remove existing components
	            contentPanel.add(blocked);
	            contentPanel.revalidate();  // Revalidate the panel to reflect changes
	            contentPanel.repaint();  // Repaint the panel
	            lblPageTitle.setText("Blocklist");

            }
        });
		
		btnDashboard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	setupDashboardView();
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
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getLog();
				LogInFrame logInFrame = new LogInFrame();
				logInFrame.setVisible(true);
				
				dispose();
			}
		});
		
		setupDashboardView();
		
	}		
	
	Connection conn;
	PreparedStatement pst;
	ResultSet rs;
			
	
	public static String getUser() {
		return lblUserType.getText();
	}
	private void setupDashboardView() {		    	    

	    //  set the page title and clear other components
	    lblPageTitle.setText("Dashboard");

	    // Repaint the panel
	    contentPanel.revalidate();
	    contentPanel.repaint();
	    
	    Dashboard dashBoard = new Dashboard();
		dashBoard = new Dashboard();
		dashBoard.setBounds(0, 0, 1425, 980);
		dashBoard.setLayout(null);  // Set layout to null
        contentPanel.removeAll();  // Remove existing components
        contentPanel.add(dashBoard);
        contentPanel.revalidate();  // Revalidate the panel to reflect changes
        contentPanel.repaint();  // Repaint the panel
        lblPageTitle.setText("Dashboard");
	    
	   
	    	 
	}
	
	public void getLog() {
		try {
	        Class.forName("com.mysql.jdbc.Driver");
	    } catch (ClassNotFoundException e1) {
	        e1.printStackTrace();
	    }

	    try {
	    	conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/booksDB", "root", "ranielle25");
            Statement stmt = conn.createStatement();
            System.out.println("Connected");

            // Get the inputs
            String user = lblUserType.getText().toString();
            String date = l_date.getText().toString();

            // Use a PreparedStatement to handle parameterized queries
            String sql = "INSERT INTO Logs (user_name, log_date, time_in, time_out) VALUES (?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, user);
                pstmt.setString(2, date);
                pstmt.setString(3, l_log.getText().toString());
                
                Timestamp currentTime = new Timestamp(System.currentTimeMillis());

                // Format the Timestamp to get only the time
                SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
                String time = timeFormat.format(currentTime);
                
                // Use the current timestamp for time_out
                pstmt.setString(4, time);
                
                // Execute the update
                pstmt.executeUpdate();
            }
	    } catch (SQLException e1) {
	        e1.printStackTrace();
	    }
	}
	
	
}
