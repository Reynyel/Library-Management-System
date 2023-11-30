import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.ScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.LineBorder;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;

public class SearchBooks extends JFrame {

	private JPanel contentPane;
	private JTable tblBooks;
	private JTextField txtTitle;
	private JTextField txtBookNum;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchBooks frame = new SearchBooks();
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
	public SearchBooks() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1085, 768);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tblBooks = new JTable();
		tblBooks.setColumnSelectionAllowed(true);
		tblBooks.setCellSelectionEnabled(true);
		tblBooks.setBorder(new LineBorder(new Color(0, 0, 0)));
		tblBooks.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Book Num", "Title", "Author", "ISBN", "Publisher", "Language", "Subject", "Quantity", "Dewey Decimal", "Accession Number", "Status"
			}
		));
		tblBooks.setBounds(108, 300, 750, 316);
		contentPane.add(tblBooks);
		
		JScrollPane js = new JScrollPane(tblBooks);
		js.setVisible(true);
		js.setBounds(26, 327, 1033, 342); // Adjust the bounds to match the table
		contentPane.add(js);
		
		JButton btnShowData = new JButton("Show Data");
		btnShowData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					view();
			}
			
		});
		btnShowData.setBounds(618, 199, 128, 40);
		contentPane.add(btnShowData);
		
		JButton btnSearchBook = new JButton("Search Book");
		btnSearchBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				search();
			}
		});
		btnSearchBook.setBounds(328, 199, 128, 40);
		contentPane.add(btnSearchBook);
		
		JLabel lblNewLabel = new JLabel("Book Title");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(35, 82, 80, 14);
		contentPane.add(lblNewLabel);
		
		txtTitle = new JTextField();
		txtTitle.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtTitle.setColumns(10);
		txtTitle.setBounds(117, 81, 629, 20);
		contentPane.add(txtTitle);
		
		JLabel lblBookNumber = new JLabel("Book Number");
		lblBookNumber.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblBookNumber.setBounds(35, 40, 113, 14);
		contentPane.add(lblBookNumber);
		
		txtBookNum = new JTextField();
		txtBookNum.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtBookNum.setColumns(10);
		txtBookNum.setBounds(138, 39, 608, 20);
		contentPane.add(txtBookNum);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainMenuFrame menu = new MainMenuFrame();
				menu.setVisible(true);
				dispose();
			}
		});
		btnBack.setBounds(10, 680, 128, 40);
		contentPane.add(btnBack);
		
		JComboBox cbStatus = new JComboBox();
		cbStatus.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cbStatus.setBackground(Color.WHITE);
		cbStatus.setBounds(117, 114, 89, 22);
		cbStatus.addItem("Available");
		cbStatus.addItem("Not Available");
		cbStatus.addItem("Borrowed");
		contentPane.add(cbStatus);
		
		JLabel lblStatus = new JLabel("Status");
		lblStatus.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblStatus.setBounds(35, 118, 80, 14);
		contentPane.add(lblStatus);
		
		view();
	}
	
	Connection conn;
	PreparedStatement pst;
	ResultSet rs;
	
	public void search() {
		try {		
			 // Load the JDBC driver (version 4.0 or later)
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}   		
			
			try {
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/BooksDB", "root", "ranielle25");
				Statement stmt = conn.createStatement();
				System.out.println("Connected");
								
				String sql = "SELECT * FROM Books WHERE Book_Num = ?";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				
				String bookNumber = txtBookNum.getText();
				//int bookNumber = txtBookNumber
				pstmt.setString(1, bookNumber);
				
				ResultSet rs = pstmt.executeQuery();

		        DefaultTableModel tblModel = (DefaultTableModel) tblBooks.getModel();

		        // Clear existing rows in the table
		        tblModel.setRowCount(0);
		        
	        
	        	DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();
	        	while(rs.next()) {
	        				   		        		
					//add data until there is none
					String bookNum = rs.getString("Book_Num");
					String title = rs.getString("Title");
					String author = rs.getString("Author");
					String isbn = rs.getString("isbn");
					String publisher = rs.getString("Publisher");
					String language = rs.getString("Language");
					String subject = rs.getString("Subject");
					String quantity = String.valueOf(rs.getInt("Quantity"));
					String dewey = String.valueOf(rs.getDouble("Dewey_Decimal"));
					String accession = String.valueOf(rs.getInt("Accession_Num"));
					String status = rs.getString("book_status");
					
					comboBoxModel.addElement(accession);
					
					txtTitle.setText(title);
					
					//array to store data into jtable
					String tbData[] = {bookNum, title, author, isbn, publisher,
							language, subject, quantity, dewey, accession, status};
				
					
					//add string array data to jtable
					tblModel.addRow(tbData);
															
				}
		        
				
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}          				
								
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void view() {
		try {		
			 // Load the JDBC driver (version 4.0 or later)
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}   		
			
			try {
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/BooksDB", "root", "ranielle25");
				Statement stmt = conn.createStatement();
				System.out.println("Connected");
								
				String sql = "SELECT * FROM Books";
				ResultSet rs = stmt.executeQuery(sql);
				
				
				DefaultTableModel tblModel = (DefaultTableModel)tblBooks.getModel();
				
				// Clear existing rows in the table
	            tblModel.setRowCount(0);
	            
				while(rs.next()) {
					//add data until there is none
					String bookNum = rs.getString("Book_Num");
					String title = rs.getString("Title");
					String author = rs.getString("Author");
					String isbn = rs.getString("isbn");
					String publisher = rs.getString("Publisher");
					String language = rs.getString("Language");
					String subject = rs.getString("Subject");
					String quantity = String.valueOf(rs.getInt("Quantity"));
					String dewey = String.valueOf(rs.getDouble("Dewey_Decimal"));
					String accession = String.valueOf(rs.getInt("Accession_Num"));
					String status = rs.getString("book_status");
					
					//array to store data into jtable
					String tbData[] = {bookNum, title, author, isbn, publisher,
							language, subject, quantity, dewey, accession, status};
					
					
					
					//add string array data to jtable
					tblModel.addRow(tbData);
					
					
					
				}
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}          				
								
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}