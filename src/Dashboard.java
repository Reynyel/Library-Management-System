import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.Array;
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
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import color.AlternateColorRender;
import tablemodel.NonEditTableModel;
import javax.swing.ImageIcon;
import GradientBackground.gradientBackground;
import com.toedter.calendar.JCalendar;
import javax.swing.JScrollPane;

public class Dashboard extends JPanel {
	
	private StudentSections section;
	
	class jPanelGradient extends JPanel {
		protected void paintComponent(Graphics g) {
	        super.paintComponents(g);
	        Graphics2D g2d = (Graphics2D) g;
	        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
	        int w = getWidth(), h = getHeight();
	        Color color1 = new Color(52,143,80);
	        Color color2 = new Color(86,180,211);
	        GradientPaint gp = new GradientPaint(0, 0, color1, w, h, color2);
	        g2d.setPaint(gp);
	        g2d.fillRect(0, 0, w, h);
	    }
	}	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Dashboard frame = new Dashboard();
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
	public Dashboard() {
		// Calculate the center of the screen
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int centerX = (screenSize.width - getWidth()) / 2;
		int centerY = (screenSize.height - getHeight()) / 2;
		
		// Set the frame location
		setLocation(centerX, centerY);
		
		setPreferredSize(new Dimension(1256, 815));
	    setLayout(null);
		setBounds(100, 100, 1687, 743);
		
        
		gradientBackground contentPanel = new gradientBackground();
		contentPanel.setLayout(null);
		contentPanel.setBorder(null);
		contentPanel.setBounds(0, 0, 1255, 756);
		add(contentPanel);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(798, 260, 444, 125);
		contentPanel.add(scrollPane_3);
		
		tblBorrowed = new JTable();
		tblBorrowed.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tblBorrowed.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null},
			},
			new String[] {
				"Title", "Return Date"
			}
		));
		scrollPane_3.setViewportView(tblBorrowed);
		
		JCalendar calendar = new JCalendar();
		calendar.setBounds(992, 26, 250, 211);
		contentPanel.add(calendar);
		
		JLabel label = new JLabel("Overdue Books");
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Dialog", Font.PLAIN, 22));
		label.setBounds(10, 386, 164, 39);
		contentPanel.add(label);
		
		JLabel label_1 = new JLabel("Book Circulation History");
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("Dialog", Font.PLAIN, 22));
		label_1.setBounds(801, 391, 275, 29);
		contentPanel.add(label_1);
		
		JLabel label_1_1 = new JLabel("Borrowed Books");
		label_1_1.setForeground(Color.WHITE);
		label_1_1.setFont(new Font("Dialog", Font.PLAIN, 22));
		label_1_1.setBounds(801, 222, 275, 39);
		contentPanel.add(label_1_1);
		
		String borrowedCount = countBorrowedBooks();
        String borrowedCountOD = countOverdue();
        String countBooks = countBooks();
        String countAvail = countBooksAvailable();
        
		JLabel numBorr = new JLabel("0");
		numBorr.setForeground(Color.WHITE);
		numBorr.setFont(new Font("Dialog", Font.PLAIN, 35));
		numBorr.setBounds(77, 103, 164, 40);
		numBorr.setText(borrowedCount);
		contentPanel.add(numBorr);
		
		JLabel numOD = new JLabel("0");
		numOD.setForeground(Color.WHITE);
		numOD.setFont(new Font("Dialog", Font.PLAIN, 35));
		numOD.setBounds(322, 103, 164, 40);
		numOD.setText(borrowedCountOD);
		contentPanel.add(numOD);
		
		JLabel numBooks = new JLabel("0");
		numBooks.setForeground(Color.WHITE);
		numBooks.setFont(new Font("Dialog", Font.PLAIN, 35));
		numBooks.setBounds(558, 103, 164, 40);
		numBooks.setText(countBooks);
		contentPanel.add(numBooks);
		
		JLabel label_2_2 = new JLabel("Borrowed");
		label_2_2.setForeground(Color.WHITE);
		label_2_2.setFont(new Font("Dialog", Font.PLAIN, 13));
		label_2_2.setBounds(77, 71, 164, 22);
		contentPanel.add(label_2_2);
		
		JLabel label_2_2_1 = new JLabel("Overdue");
		label_2_2_1.setForeground(Color.WHITE);
		label_2_2_1.setFont(new Font("Dialog", Font.PLAIN, 13));
		label_2_2_1.setBounds(322, 71, 164, 22);
		contentPanel.add(label_2_2_1);
		
		JLabel label_2_2_1_1 = new JLabel("Registered Books");
		label_2_2_1_1.setForeground(Color.WHITE);
		label_2_2_1_1.setFont(new Font("Dialog", Font.PLAIN, 13));
		label_2_2_1_1.setBounds(558, 71, 164, 22);
		contentPanel.add(label_2_2_1_1);
		
		JLabel label_2_2_1_1_1 = new JLabel("Calendar");
		label_2_2_1_1_1.setForeground(Color.WHITE);
		label_2_2_1_1_1.setBounds(992, 11, 84, 14);
		contentPanel.add(label_2_2_1_1_1);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 424, 756, 321);
		contentPanel.add(scrollPane_2);
		
		tblOverdue = new JTable();
		tblOverdue.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tblOverdue.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null},
			},
			new String[] {
				"ID", "Member", "Title", "Accession", "Overdue", "Return Date"
			}
		));
		scrollPane_2.setViewportView(tblOverdue);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(801, 424, 444, 321);
		contentPanel.add(scrollPane);
		
		tblReturned = new JTable();
		tblReturned.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tblReturned.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
			},
			new String[] {
				"Title", "Date Returned", "Status"
			}
		));
		scrollPane.setViewportView(tblReturned);
		
		
		setupDashboardView();
	}
	
	Connection conn;
	PreparedStatement pst;
	ResultSet rs;
	private JTable tblBorrowed;
	private JTable tblOverdue;
	private JTable tblReturned;
	
	private void setupDashboardView() {		    	    
		// Call the methods to display the relevant data
	    displayOverdue();
	    displayReturned();
	    displayBorrowed();
	    	 
	}
	
	
	public void displayOverdue() {
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
	            String overdue = (daysBetween < 0) ?  Math.abs(daysBetween + 2) + " days" : "On time";

	            // array to store data into jtable
	            String tbData[] = {id, name, title, acc, overdue, returnDateStr};

	            // add string array data to jtable
	            tblModel.addRow(tbData); 
	            
	        }

	    } catch (SQLException e1) {
	        e1.printStackTrace();
	    }
	}
	
	public void displayReturned() {
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
	}
	
	public void displayBorrowed() {
		
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
