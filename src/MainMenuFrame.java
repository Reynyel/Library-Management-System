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
import java.time.format.DateTimeFormatter;
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
	private JLabel lblUserType, l_date, l_time, l_log, lblPageTitle;
	private JLabel numBorr, numOD, numBooks, label_2_2, label_2_2_1, label_2_2_1_1, label_2_2_1_1_1, label, label_1,
	label_1_1;
	private JTable tblOverdue;
	private JScrollPane scrollPane, scrollPane_1, scrollPane_2;
	private JCalendar calendar;
	
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
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 1510, 845);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 255, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 51, 102));
		panel.setBounds(0, 0, 240, 806);
		contentPane.add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 51, 102));
		panel_1.setForeground(new Color(255, 255, 255));
		panel_1.setBounds(239, 0, 1255, 50);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		contentPanel = new gradientBackground();
		contentPanel.setBorder(null);
		contentPanel.setBounds(239, 50, 1255, 756);
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
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\pc\\Library-Management-System\\res\\Santa Rosa Educational Institution.png"));
		
		JButton btnRegisterBooks = new JButton("Manage Books");
		btnRegisterBooks.setBounds(14, 226, 218, 41);
		panel.add(btnRegisterBooks);
		btnRegisterBooks.setHorizontalAlignment(SwingConstants.LEFT);
		btnRegisterBooks.setIcon(new ImageIcon("C:\\Users\\pc\\Library-Management-System\\res\\book (1).png"));
		btnRegisterBooks.setForeground(new Color(255, 255, 255));
		btnRegisterBooks.setFont(new Font("Verdana", Font.PLAIN, 15));
		btnRegisterBooks.setBackground(new Color(0, 51, 102));
		btnRegisterBooks.setBorderPainted(false);
		
		
		JButton btnBackupRecords = new JButton(" Backup Records");
		btnBackupRecords.setIcon(new ImageIcon("C:\\Users\\pc\\Library-Management-System\\res\\backup.png"));
		btnBackupRecords.setBounds(12, 431, 220, 40);
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
		btnBackupRecords.setFont(new Font("Verdana", Font.PLAIN, 15));
		btnBackupRecords.setBorderPainted(false);
		btnBackupRecords.setBackground(new Color(0, 51, 102));
		
		JButton btnReturnBooks = new JButton(" Returning");
		btnReturnBooks.setBounds(12, 380, 220, 40);
		panel.add(btnReturnBooks);
		btnReturnBooks.setHorizontalAlignment(SwingConstants.LEFT);
		btnReturnBooks.setIcon(new ImageIcon("C:\\Users\\pc\\Library-Management-System\\res\\return.png"));
		btnReturnBooks.setForeground(new Color(255, 255, 255));
		btnReturnBooks.setFont(new Font("Verdana", Font.PLAIN, 15));
		btnReturnBooks.setBackground(new Color(0, 51, 102));
		btnReturnBooks.setBorderPainted(false);			                  		
		
		JButton btnTransac = new JButton(" Lending");
		btnTransac.setBounds(12, 329, 220, 40);
		panel.add(btnTransac);
		btnTransac.setIcon(new ImageIcon("C:\\Users\\pc\\Library-Management-System\\res\\transaction.png"));
		btnTransac.setHorizontalAlignment(SwingConstants.LEFT);
		btnTransac.setForeground(new Color(255, 255, 255));
		btnTransac.setFont(new Font("Verdana", Font.PLAIN, 15));
		btnTransac.setBackground(new Color(0, 51, 102));
		btnTransac.setBorderPainted(false);
		
		JButton btnRegisterUser = new JButton(" Manage Users");
		btnRegisterUser.setBounds(12, 278, 220, 40);
		panel.add(btnRegisterUser);
		btnRegisterUser.setHorizontalAlignment(SwingConstants.LEFT);
		btnRegisterUser.setIcon(new ImageIcon("C:\\Users\\pc\\Library-Management-System\\res\\management.png"));
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
		UserPanel.setBounds(12, 609, 220, 186);
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
		
		lblUserType = new JLabel("Librarian");
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
		btnDashboard.setIcon(new ImageIcon("C:\\Users\\pc\\Library-Management-System\\res\\webpage.png"));
		btnDashboard.setHorizontalAlignment(SwingConstants.LEFT);
		btnDashboard.setForeground(Color.WHITE);
		btnDashboard.setFont(new Font("Verdana", Font.PLAIN, 15));
		btnDashboard.setBorderPainted(false);
		btnDashboard.setBackground(new Color(0, 51, 102));
		btnDashboard.setBounds(14, 174, 218, 41);
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
		
		btnLogs.setIcon(new ImageIcon("C:\\Users\\pc\\Library-Management-System\\res\\logimg-small.png"));
		btnLogs.setHorizontalAlignment(SwingConstants.LEFT);
		btnLogs.setForeground(Color.WHITE);
		btnLogs.setFont(new Font("Verdana", Font.PLAIN, 15));
		btnLogs.setBorderPainted(false);
		btnLogs.setBackground(new Color(0, 51, 102));
		btnLogs.setBounds(12, 475, 220, 40);
		panel.add(btnLogs);
		
		calendar = new JCalendar();
		calendar.setBounds(992, 26, 250, 211);
        contentPanel.removeAll();  // Remove existing components
        contentPanel.setLayout(null);
        contentPanel.revalidate();  // Revalidate the panel to reflect changes
        contentPanel.repaint();  // Repaint the panel
        lblPageTitle.setText("Dashboard");
        contentPanel.add(calendar);
        
        scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 424, 765, 321);
        contentPanel.add(scrollPane);
        
        tblOverdue = new JTable();
        tblOverdue.setShowVerticalLines(false);
        tblOverdue.setShowHorizontalLines(false);
        scrollPane.setViewportView(tblOverdue);
        tblOverdue.setModel(new DefaultTableModel(
        	new Object[][] {
        		{null, null, null, null, null, null},
        	},
        	new String[] {
        		"ID", "Member", "Title", "Accession", "Overdue", "Return date"
        	}
        ));
        
        label = new JLabel("Overdue Books");
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Dialog", Font.PLAIN, 22));
        label.setBounds(10, 386, 164, 39);
        contentPanel.add(label);
        
        label_1 = new JLabel("Book Circulation History");
        label_1.setForeground(Color.WHITE);
        label_1.setFont(new Font("Dialog", Font.PLAIN, 22));
        label_1.setBounds(801, 391, 275, 29);
        contentPanel.add(label_1);
        
        scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(801, 424, 444, 321);
        contentPanel.add(scrollPane_1);
        
        tblReturned = new JTable();
        tblReturned.setModel(new DefaultTableModel(
        	new Object[][] {
        	},
        	new String[] {
        		"Title", "Date Returned", "Status"
        	}
        ));
        scrollPane_1.setViewportView(tblReturned);
        tblReturned.setShowVerticalLines(false);
        tblReturned.setShowHorizontalLines(false);
        
        label_1_1 = new JLabel("Borrowed Books");
        label_1_1.setFont(new Font("Dialog", Font.PLAIN, 22));
        label_1_1.setForeground(Color.WHITE);
        label_1_1.setBounds(801, 222, 275, 39);
        contentPanel.add(label_1_1);
        
        scrollPane_2 = new JScrollPane();
        scrollPane_2.setBounds(801, 260, 444, 125);
        contentPanel.add(scrollPane_2);
        
        tblBorrowed = new JTable();
        tblBorrowed.setModel(new DefaultTableModel(
        	new Object[][] {
        	},
        	new String[] {
        		"Title", "Return Date"
        	}
        ));
        scrollPane_2.setViewportView(tblBorrowed);
        tblBorrowed.setShowVerticalLines(false);
        tblBorrowed.setShowHorizontalLines(false);
        
        numBorr = new JLabel("0");
        numBorr.setFont(new Font("Dialog", Font.PLAIN, 22));
        numBorr.setBounds(77, 103, 164, 22);
        numBorr.setForeground(Color.WHITE);
        String borrowedCount = countBorrowedBooks();
        numBorr.setText(borrowedCount);
        contentPanel.add(numBorr);
        
        numOD = new JLabel("0");
        numOD.setFont(new Font("Dialog", Font.PLAIN, 22));       
        numOD.setBounds(322, 103, 164, 22);
        numOD.setForeground(Color.WHITE);
        String borrowedCountOD = countOverdue();
        numOD.setText(borrowedCountOD);
        contentPanel.add(numOD);
        
        numBooks = new JLabel("0");
        numBooks.setFont(new Font("Dialog", Font.PLAIN, 22));
        numBooks.setBounds(558, 103, 164, 22);
        numBooks.setForeground(Color.WHITE);
        String countBooks = countBooks();
        numBooks.setText(countBooks);
        contentPanel.add(numBooks);
        
        label_2_2 = new JLabel("Borrowed");
        label_2_2.setFont(new Font("Dialog", Font.PLAIN, 13));
        label_2_2.setBounds(77, 71, 164, 22);
        label_2_2.setForeground(Color.WHITE);
        contentPanel.add(label_2_2);
        
        label_2_2_1 = new JLabel("Overdue");
        label_2_2_1.setFont(new Font("Dialog", Font.PLAIN, 13));
        label_2_2_1.setBounds(322, 71, 164, 22);
        label_2_2_1.setForeground(Color.WHITE);
        contentPanel.add(label_2_2_1);
        String countAvail = countBooksAvailable();
        
        label_2_2_1_1 = new JLabel("Registered Books");
        label_2_2_1_1.setFont(new Font("Dialog", Font.PLAIN, 13));
        label_2_2_1_1.setBounds(558, 71, 164, 22);
        label_2_2_1_1.setForeground(Color.WHITE);
        contentPanel.add(label_2_2_1_1);
        
        label_2_2_1_1_1 = new JLabel("Calendar");
        label_2_2_1_1_1.setForeground(new Color(255, 255, 255));
        label_2_2_1_1_1.setBounds(992, 11, 84, 14);
        contentPanel.add(label_2_2_1_1_1);
        
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
		
		displayOverdue();
		displayReturned();
		displayBorrowed();
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getLog();
				LogInFrame logInFrame = new LogInFrame();
				logInFrame.setVisible(true);
				
				dispose();
			}
		});
	}		
	
	Connection conn;
	PreparedStatement pst;
	ResultSet rs;
	private JTable tblReturned;
	private JTable tblBorrowed;
	
	private void setupDashboardView() {		    	    
		// Call the methods to display the relevant data
	    displayOverdue();
	    displayReturned();
	    displayBorrowed();

	    // Optionally, you can set the page title and clear other components
	    lblPageTitle.setText("Dashboard");

	    // Repaint the panel
	    contentPanel.revalidate();
	    contentPanel.repaint();
	    
	    // You may also want to reset the layout to your initial state
	    contentPanel.setLayout(null);  // Change FlowLayout to your desired layout
	    
	    // Clear other components and add your initial components (Labels, Buttons, etc.)
	    contentPanel.removeAll();
	    
	    contentPanel.add(numBorr);
	    contentPanel.add(numOD);
	    contentPanel.add(numBooks);
	    contentPanel.add(label_2_2);
	    contentPanel.add(label_2_2_1);
	    contentPanel.add(label_2_2_1_1);
	    contentPanel.add(label_2_2_1_1_1);
	    contentPanel.add(label);
	    contentPanel.add(label_1);
	    contentPanel.add(scrollPane_1);
	    contentPanel.add(scrollPane);
	    contentPanel.add(label_1_1);
	    contentPanel.add(scrollPane_2);
	    contentPanel.add(calendar);
	    
	    // Add other initial components as needed

	    // Repaint the panel again
	    contentPanel.revalidate();
	    contentPanel.repaint();
	    	 
	}
	
	public void getLog() {
		try {
	        Class.forName("com.mysql.jdbc.Driver");
	    } catch (ClassNotFoundException e1) {
	        e1.printStackTrace();
	    }

	    try {
	    	conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/logsDB", "root", "ranielle25");
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
	
	public Component displayOverdue() {
		try {
	        Class.forName("com.mysql.jdbc.Driver");
	    } catch (ClassNotFoundException e1) {
	        e1.printStackTrace();
	    }

	    try {
	        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/BooksDB", "root", "ranielle25");
	        Statement stmt = conn.createStatement();
	        System.out.println("Connected");

	        String sql = "SELECT * FROM Transactions WHERE user_type = 'Student' AND return_date < CURDATE()";
	        ResultSet rs = stmt.executeQuery(sql);

	        DefaultTableModel tblModel = (DefaultTableModel) tblOverdue.getModel();

	        // Clear existing rows in the table
	        tblModel.setRowCount(0);

	        while (rs.next()) {
	            String id = String.valueOf(rs.getInt("user_id"));
	            String title = rs.getString("Title");
	            String acc = rs.getString("AccessionNum");
	            String name = rs.getString("Borrower");
	            String returnDateStr = rs.getString("return_date");

	            // Convert the return date string to LocalDate
	            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	            LocalDate returnDate = LocalDate.parse(returnDateStr, formatter);

	            // Calculate the days between today and the return date
	            LocalDate today = LocalDate.now();
	            long daysBetween = java.time.temporal.ChronoUnit.DAYS.between(today, returnDate);

	            // Display the days as a string in the overdue column
	            String overdue = (daysBetween < 0) ?  Math.abs(daysBetween) + " days" : "On time";

	            // array to store data into jtable
	            String tbData[] = {id, name, title, acc, overdue, returnDateStr};

	            // add string array data to jtable
	            tblModel.addRow(tbData); 
	            
	        }

	    } catch (SQLException e1) {
	        e1.printStackTrace();
	    }
		return contentPane;
	}
	
	public Component displayReturned() {
		try {
	        // Load the JDBC driver (version 4.0 or later)
	        Class.forName("com.mysql.jdbc.Driver");

	        // Establish a connection
	        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/BooksDB", "root", "ranielle25");
	        System.out.println("Connected");
	        
	        // Fetch data from Transactions table
	        String selectTransactionsSql = "SELECT * FROM Returned WHERE date_returned >= CURDATE() - INTERVAL 8 DAY ORDER BY date_returned DESC";

	        try (PreparedStatement stmtTransacSql = conn.prepareStatement(selectTransactionsSql)) {
	            ResultSet rs = stmtTransacSql.executeQuery();

	            DefaultTableModel tblModel = (DefaultTableModel) tblReturned.getModel();
	            
	            // Clear existing rows in the table
		        tblModel.setRowCount(0);
	            // Fetch and add new data
	            while (rs.next()) {
	                String title = rs.getString("title");
	                String status = rs.getString("status");
	                String dateReturned = rs.getString("date_returned");


	                // array to store data into JTable
	                String tbData[] = {title, dateReturned, status};

	                // add string array data to JTable
	                tblModel.addRow(tbData);	                

	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		
		return contentPane;
	}
	
	public Component displayBorrowed() {
		
		try {
	        // Load the JDBC driver (version 4.0 or later)
	        Class.forName("com.mysql.jdbc.Driver");

	        // Establish a connection
	        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/BooksDB", "root", "ranielle25");
	        System.out.println("Connected");

	        // Fetch data from Transactions table
	        String selectTransactionsSql = "SELECT * FROM Transactions";

	        try (PreparedStatement stmtTransacSql = conn.prepareStatement(selectTransactionsSql)) {
	            ResultSet rs = stmtTransacSql.executeQuery();

	            DefaultTableModel tblModel = (DefaultTableModel) tblBorrowed.getModel();
	            
	            // Clear existing rows in the table
		        tblModel.setRowCount(0);
	            // Fetch and add new data
	            while (rs.next()) {
	                String title = rs.getString("Title");
	                String returnDate = rs.getString("return_date");


	                // array to store data into JTable
	                String tbData[] = {title, returnDate};

	                // add string array data to JTable
	                tblModel.addRow(tbData);	                

	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		
		return contentPane;
	}
	
	public String countBooks() {
	    String countString = "0";

	    try {
	        // Load the JDBC driver (version 4.0 or later)
	        Class.forName("com.mysql.jdbc.Driver");

	        // Establish a connection
	        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/BooksDB", "root", "ranielle25");
	        System.out.println("Connected");

	        // Count the number of borrowed books
	        String countBooksSql = "SELECT COUNT(*) AS borrowedCount FROM Books";

	        try (PreparedStatement stmtCountBooksSql = conn.prepareStatement(countBooksSql)) {
	            ResultSet rs = stmtCountBooksSql.executeQuery();

	            // Fetch the count
	            if (rs.next()) {
	                int count = rs.getInt("borrowedCount");
	                countString = Integer.toString(count);
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return countString;
	}
	
	public String countBooksAvailable() {
	    String countString = "0";

	    try {
	        // Load the JDBC driver (version 4.0 or later)
	        Class.forName("com.mysql.jdbc.Driver");

	        // Establish a connection
	        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/BooksDB", "root", "ranielle25");
	        System.out.println("Connected");

	        // Count the number of borrowed books
	        String countBooksSql = "SELECT COUNT(*) AS borrowedCount FROM Books WHERE book_status = 'Available'";

	        try (PreparedStatement stmtCountBooksSql = conn.prepareStatement(countBooksSql)) {
	            ResultSet rs = stmtCountBooksSql.executeQuery();

	            // Fetch the count
	            if (rs.next()) {
	                int count = rs.getInt("borrowedCount");
	                countString = Integer.toString(count);
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return countString;
	}
	
	public String countBorrowedBooks() {
	    String countString = "0";

	    try {
	        // Load the JDBC driver (version 4.0 or later)
	        Class.forName("com.mysql.jdbc.Driver");

	        // Establish a connection
	        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/BooksDB", "root", "ranielle25");
	        System.out.println("Connected");

	        // Count the number of borrowed books
	        String countBooksSql = "SELECT COUNT(*) AS borrowedCount FROM Transactions";

	        try (PreparedStatement stmtCountBooksSql = conn.prepareStatement(countBooksSql)) {
	            ResultSet rs = stmtCountBooksSql.executeQuery();

	            // Fetch the count
	            if (rs.next()) {
	                int count = rs.getInt("borrowedCount");
	                countString = Integer.toString(count);
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return countString;
	}
	
	public String countOverdue() {
	    String countString = "0";

	    try {
	        // Load the JDBC driver (version 4.0 or later)
	        Class.forName("com.mysql.jdbc.Driver");

	        // Establish a connection
	        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/BooksDB", "root", "ranielle25");
	        System.out.println("Connected");

	        // Count the number of borrowed books
	        String countBooksSql = "SELECT COUNT(*) AS borrowedCount FROM Transactions WHERE user_type = 'Student' AND return_date < CURDATE()";

	        try (PreparedStatement stmtCountBooksSql = conn.prepareStatement(countBooksSql)) {
	            ResultSet rs = stmtCountBooksSql.executeQuery();

	            // Fetch the count
	            if (rs.next()) {
	                int count = rs.getInt("borrowedCount");
	                countString = Integer.toString(count);
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return countString;
	}
}
