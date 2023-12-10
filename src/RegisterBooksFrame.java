import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import tablemodel.NonEditTableModel;


public class RegisterBooksFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtTitle;
	private JTextField txtAuthor;
	private JTextField txtISBN;
	private JTextField txtPublisher;
	private JTextField txtQuantity;
	private JComboBox comboBoxSubject, languageComboBox;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterBooksFrame frame = new RegisterBooksFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public RegisterBooksFrame() throws SQLException {
		setResizable(false);
		setTitle("Register Books");
		setBackground(new Color(255, 255, 255));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1303, 666);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Book Title");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(45, 118, 80, 27);
		contentPane.add(lblNewLabel);
		
		txtTitle = new JTextField();
		txtTitle.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtTitle.setBounds(135, 115, 369, 30);
		contentPane.add(txtTitle);
		txtTitle.setColumns(10);
		
		JLabel lblAuthors = new JLabel("Author(s)");
		lblAuthors.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAuthors.setBounds(45, 156, 80, 27);
		contentPane.add(lblAuthors);
		
		txtAuthor = new JTextField();
		txtAuthor.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtAuthor.setColumns(10);
		txtAuthor.setBounds(135, 153, 369, 30);
		contentPane.add(txtAuthor);
		
		JLabel lblIsbn = new JLabel("ISBN");
		lblIsbn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblIsbn.setBounds(45, 194, 80, 27);
		contentPane.add(lblIsbn);
		
		txtISBN = new JTextField();
		txtISBN.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtISBN.setColumns(10);
		txtISBN.setBounds(135, 191, 369, 30);
		contentPane.add(txtISBN);
		
		JLabel lblPublisher = new JLabel("Publisher");
		lblPublisher.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPublisher.setBounds(45, 232, 80, 27);
		contentPane.add(lblPublisher);
		
		txtPublisher = new JTextField();
		txtPublisher.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtPublisher.setColumns(10);
		txtPublisher.setBounds(135, 229, 369, 30);
		contentPane.add(txtPublisher);
		
		JLabel lblLanguage = new JLabel("Language");
		lblLanguage.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblLanguage.setBounds(45, 308, 80, 27);
		contentPane.add(lblLanguage);
		
		JLabel lblSubject = new JLabel("Subject");
		lblSubject.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSubject.setBounds(45, 347, 80, 27);
		contentPane.add(lblSubject);
		
		comboBoxSubject = new JComboBox();
		comboBoxSubject.setBackground(new Color(255, 255, 255));
		
		String[] subjects = {"General Information", "Philosophy & Psychology", "Religion",
				"Social Sciences", "Language", "Science", "Technology", "Arts & Recreation",
				"Literature", "History & Geography"};
		
		for(String s : subjects) {
			comboBoxSubject.addItem(s);
		}
		
		comboBoxSubject.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBoxSubject.setBounds(135, 346, 186, 28);
		contentPane.add(comboBoxSubject);
			
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registerBooks();
			}
				
		});
		
		btnRegister.setForeground(Color.WHITE);
		btnRegister.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnRegister.setBorderPainted(false);
		btnRegister.setBackground(new Color(157, 179, 227));
		btnRegister.setBounds(411, 391, 93, 33);
		contentPane.add(btnRegister);
		
		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblQuantity.setBounds(45, 270, 80, 27);
		contentPane.add(lblQuantity);
		
		txtQuantity = new JTextField();
		txtQuantity.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtQuantity.setColumns(10);
		txtQuantity.setBounds(135, 270, 97, 27);
		contentPane.add(txtQuantity);
		
		languageComboBox = new JComboBox();
		String[] langArr = {"English", "Filipino"};
		for (String s : langArr) {
			languageComboBox.addItem(s);
		}
		languageComboBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
		languageComboBox.setBackground(Color.WHITE);
		languageComboBox.setBounds(135, 308, 186, 27);
		contentPane.add(languageComboBox);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainMenuFrame menu = new MainMenuFrame();
				menu.setVisible(true);
				dispose();
			}
		});
		btnBack.setForeground(Color.WHITE);
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBack.setBorderPainted(false);
		btnBack.setBackground(new Color(157, 179, 227));
		btnBack.setBounds(10, 583, 93, 33);
		contentPane.add(btnBack);
		
		JLabel lblNewLabel_1 = new JLabel("Book Registration");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 40));
		lblNewLabel_1.setBounds(45, 27, 459, 80);
		contentPane.add(lblNewLabel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(514, 90, 763, 471);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null, null, null, null},
			},
			new String[] {
				"Book Number", "Title", "Author", "ISBN", "Publisher", "Language", "Subject", "Dewey", "Accession", "Status", "Date Registered"
			}
		));
		Object[][] data = {null, null, null, null, null, null, null, null, null, null, null};
		Object[] columnNames = {"Book Number", "Title", "Author", "ISBN", "Publisher", "Language", "Subject", "Dewey", "Accession", "Status", "Date Registered"};
		NonEditTableModel model;
		Set<Integer> editableColumns = new HashSet<>();
		
		table.setModel(new NonEditTableModel(data, columnNames, editableColumns));
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 2) { //double click rows
					int selectedRow = table.getSelectedRow();
					if(selectedRow != -1) {
						//get data and populate textbox and combobox
						String bookNum = (String) table.getValueAt(selectedRow, 0);
						String title = (String) table.getValueAt(selectedRow, 1);
						String author = (String) table.getValueAt(selectedRow, 2);
						String isbn = (String) table.getValueAt(selectedRow, 3);
						String publisher = (String) table.getValueAt(selectedRow, 4);
						String language = (String) table.getValueAt(selectedRow, 5);
						String subject = (String) table.getValueAt(selectedRow, 6);
						
						//Populate fields
						txtTitle.setText(title);
						txtAuthor.setText(author);
						txtISBN.setText(isbn);
						txtPublisher.setText(publisher);
						languageComboBox.setSelectedItem(language);
						comboBoxSubject.setSelectedItem(subject);
						
					}
				}
			}
		});
		scrollPane.setViewportView(table);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					update();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				clear();
			}
		});
		btnUpdate.setForeground(Color.WHITE);
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnUpdate.setBorderPainted(false);
		btnUpdate.setBackground(new Color(157, 179, 227));
		btnUpdate.setBounds(297, 391, 93, 33);
		contentPane.add(btnUpdate);
		
		displayLatestData();
	}
	
	Connection conn;
	PreparedStatement pst;
	ResultSet rs;
	
	/*Update entries*/
	public void update() throws SQLException {
		String title = txtTitle.getText();
		String author = txtAuthor.getText();
		String isbn = txtISBN.getText();
		String publisher = txtPublisher.getText();
		String language = languageComboBox.getSelectedItem().toString();
		String subject = comboBoxSubject.getSelectedItem().toString();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/BooksDB", "root", "ranielle25");
			Statement stmt = conn.createStatement();
			System.out.println("Connected");
							
			String sql = "UPDATE Books SET Title = ?, Author = ?, ISBN = ?, Publisher = ?, Language = ?, Subject = ? WHERE Book_Num = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			int selectedRow = table.getSelectedRow();
			
			if(selectedRow != -1) {
				
				//get book_num value from row
				String bookNum = (String) table.getValueAt(selectedRow, 0);
				
				pstmt.setString(1, title);
				pstmt.setString(2, author);
				pstmt.setString(3, isbn);
				pstmt.setString(4, publisher);
				pstmt.setString(5, language);
				pstmt.setString(6, subject);
				
				pstmt.setString(7, bookNum);
				
				int rowsAffected = pstmt.executeUpdate();
				if(rowsAffected > 0) {
					JOptionPane.showMessageDialog(rootPane, "Updated succesfully");
					displayLatestData();
				}
				
				else {
					JOptionPane.showMessageDialog(rootPane, "No rows updated");
				}
				
			}
			else {
	            JOptionPane.showMessageDialog(rootPane, "No row selected");
	        }
			
		}
		
		
		catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	//Clear components
	private void clear() {
		txtTitle.setText("");
		txtAuthor.setText("");
		txtISBN.setText("");
		txtPublisher.setText("");
		languageComboBox.setSelectedIndex(0);
		comboBoxSubject.setSelectedIndex(0);
	}
	/*
	 * Displays book entries
	 * in descending order*/
	public void displayLatestData() throws SQLException {
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
			
			
			DefaultTableModel tblModel = (DefaultTableModel)table.getModel();
			
			// Clear existing rows in the table
            tblModel.setRowCount(0);
            
			while(rs.next()) {
				//add data until there is none
				String bookNum = String.valueOf(rs.getInt("Book_Num"));
				String title = rs.getString("Title");
				String author = rs.getString("Author");
				String isbn = rs.getString("isbn");
				String publisher = rs.getString("Publisher");
				String language = rs.getString("Language");
				String subject = rs.getString("Subject");
				String dewey = String.valueOf(rs.getDouble("Dewey_Decimal"));
				String accession = String.valueOf(rs.getInt("Accession_Num"));
				String status = rs.getString("book_status");
				String dateRegistered = rs.getString("date_registered");
				
				//array to store data into jtable
				String tbData[] = {bookNum, title, author, isbn, publisher,
						language, subject, dewey, accession, status, dateRegistered};
								
				//add string array data to jtable
				tblModel.addRow(tbData);
				
				
				
			}
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void registerBooks() {
		try {		
			 // Load the JDBC driver (version 4.0 or later)
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	        
			Connection conn;
			
			try {
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/BooksDB", "root", "ranielle25");
				Statement stmt = conn.createStatement();
				System.out.println("Connected");
				//Get the inputs
				String title = txtTitle.getText();
				String author = txtAuthor.getText();
				String isbn = txtISBN.getText();
				String publisher = txtPublisher.getText();
				String language = languageComboBox.getSelectedItem().toString();
				String subject = comboBoxSubject.getSelectedItem().toString();
				int quantity = Integer.valueOf(txtQuantity.getText()); //Converts String to Int
				DeweyMap deweyMap = new DeweyMap();
				double deweyDecimal = deweyMap.getDeweyForSubject(subject); 
				
				//Starting value for accession number
				int accessionNum = 00;
								
				// Create a map to store used book numbers for each title
				Map<String, Integer> titleToUsedBookNumber = new HashMap<>();
				
				// created a set to store book numbers
				Set<Integer> usedBookNumbers = new HashSet<>();
				
				Random rd = new Random();	
				
				/* Checks the book's quantity:
				 * If there is more than one copy of the same book, multiple entries will be inserted into the database.
				 * The Accession_Num will increment by 1 for each copy.
				 * If there's only one copy, a single entry is created.
				 */
				int bookNum;
				
				/*this will generate a unique 6-character
				 * book number for a title*/
				do {
					bookNum = 100000 + rd.nextInt(900000);
				} while (usedBookNumbers.contains(bookNum));
				
				usedBookNumbers.add(bookNum);
				
				/* this is used to store the designated
				 * book number for a title*/
				titleToUsedBookNumber.put(title, bookNum);

				for(int i = 0; i <= (quantity - 1); i++) {					
					
					if(quantity > 1) {
							/* this will use the same book number
							 * for multiple copies of the same title*/
							int usedBookNum = titleToUsedBookNumber.get(title);
							
							String sql = "INSERT INTO Books (Title, Author, ISBN, Publisher, Language, Subject, Quantity, Book_Num, Dewey_Decimal, Accession_Num, book_status, date_registered)" +
								    "VALUES ('" + title + "', '" + author + "', '" + isbn + "', '" + publisher + "', '" + language + "', '" + subject + "', '" + quantity + "', '" + usedBookNum + "', '" + deweyDecimal + "', '" + (accessionNum + i) + "', '" + "Available" + "', CURRENT_DATE())";
							
							//Execute query
							stmt.executeUpdate(sql);
						
						}
						
					else {					
						// For single copy
						String sql = "INSERT INTO Books (Title, Author, ISBN, Publisher, Language, Subject, Quantity, Book_Num, Dewey_Decimal, Accession_Num, book_status, date_registered)" +
						    "VALUES ('" + title + "', '" + author + "', '" + isbn + "', '" + publisher + "', '" + language + "', '" + subject + "', '" + quantity + "', '" + bookNum + "', '" + deweyDecimal + "', '" + (accessionNum + i) + "', '" + "Available" + "', CURRENT_DATE())";
						
						//Execute query
						stmt.executeUpdate(sql);
					}
				
				}
				
				JOptionPane.showMessageDialog(rootPane, "Book Registered");
				displayLatestData();
				
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
