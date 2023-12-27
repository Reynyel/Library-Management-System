import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import org.eclipse.wb.swing.FocusTraversalOnArray;

import color.AlternateColorRender;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import tablemodel.NonEditTableModel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.SpringLayout;
import javax.swing.ImageIcon;


public class RegisterBooksFrame extends JPanel {

	private JPanel panel;
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
		setBackground(new Color(0, 255, 204));
		setBorder(null);				
		setPreferredSize(new Dimension(1256, 686));
	    setLayout(null);
					
		Object[][] data = {null, null, null, null, null, null, null, null, null, null, null};
		Object[] columnNames = {"Book Number", "Title", "Author", "ISBN", "Publisher", "Language", "Subject", "Dewey", "Accession", "Status", "Date Registered"};
		NonEditTableModel model;
		Set<Integer> editableColumns = new HashSet<>();
		
        panel = new JPanel();
        panel.setBackground(new Color(0, 153, 255));
        panel.setBounds(0, 0, 1256, 686);
        add(panel);
        panel.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Book Title");
        lblNewLabel.setForeground(new Color(255, 255, 255));
        lblNewLabel.setBounds(76, 370, 72, 30);
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        panel.add(lblNewLabel);
        
        JLabel lblAuthors = new JLabel("Author(s)");
        lblAuthors.setForeground(new Color(255, 255, 255));
        lblAuthors.setBounds(386, 370, 64, 30);
        lblAuthors.setFont(new Font("Tahoma", Font.PLAIN, 15));
        panel.add(lblAuthors);
        
        txtAuthor = new JTextField();
        txtAuthor.setBounds(386, 401, 284, 30);
        txtAuthor.setFont(new Font("Tahoma", Font.PLAIN, 15));
        txtAuthor.setColumns(10);
        panel.add(txtAuthor);
        
        JLabel lblIsbn = new JLabel("ISBN");
        lblIsbn.setHorizontalAlignment(SwingConstants.LEFT);
        lblIsbn.setForeground(new Color(255, 255, 255));
        lblIsbn.setBounds(386, 439, 72, 30);
        lblIsbn.setFont(new Font("Tahoma", Font.PLAIN, 15));
        panel.add(lblIsbn);
        
        txtISBN = new JTextField();
        txtISBN.setBounds(386, 470, 284, 30);
        txtISBN.setFont(new Font("Tahoma", Font.PLAIN, 15));
        txtISBN.setColumns(10);
        panel.add(txtISBN);
        
        JLabel lblPublisher = new JLabel("Publisher");
        lblPublisher.setForeground(new Color(255, 255, 255));
        lblPublisher.setBounds(76, 442, 72, 30);
        lblPublisher.setFont(new Font("Tahoma", Font.PLAIN, 15));
        panel.add(lblPublisher);
        
        txtPublisher = new JTextField();
        txtPublisher.setBounds(76, 470, 300, 30);
        txtPublisher.setFont(new Font("Tahoma", Font.PLAIN, 15));
        txtPublisher.setColumns(10);
        panel.add(txtPublisher);
        
        JLabel lblLanguage = new JLabel("Language");
        lblLanguage.setForeground(new Color(255, 255, 255));
        lblLanguage.setBounds(332, 511, 66, 30);
        lblLanguage.setFont(new Font("Tahoma", Font.PLAIN, 15));
        panel.add(lblLanguage);
        
        JLabel lblSubject = new JLabel("Subject");
        lblSubject.setForeground(new Color(255, 255, 255));
        lblSubject.setBounds(76, 511, 64, 30);
        lblSubject.setFont(new Font("Tahoma", Font.PLAIN, 15));
        panel.add(lblSubject);
        
        comboBoxSubject = new JComboBox();
        comboBoxSubject.setBounds(76, 542, 246, 30);
        comboBoxSubject.setBackground(new Color(255, 255, 255));
        
        comboBoxSubject.setFont(new Font("Tahoma", Font.PLAIN, 15));
        panel.add(comboBoxSubject);
		        
		
		JButton btnRegister = new JButton("Register");
		btnRegister.setBounds(558, 596, 112, 30);
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registerBooks();
			}
				
		});
		
		btnRegister.setForeground(Color.WHITE);
		btnRegister.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnRegister.setBorderPainted(false);
		btnRegister.setBackground(new Color(220, 20, 60));
		panel.add(btnRegister);
		
		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setForeground(new Color(255, 255, 255));
		lblQuantity.setBounds(491, 511, 66, 30);
		lblQuantity.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel.add(lblQuantity);
		
		txtQuantity = new JTextField();
		txtQuantity.setBounds(490, 541, 89, 30);
		txtQuantity.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtQuantity.setColumns(10);
		panel.add(txtQuantity);
		
		languageComboBox = new JComboBox();
		languageComboBox.setBounds(332, 542, 148, 30);
		languageComboBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
		languageComboBox.setBackground(Color.WHITE);
		panel.add(languageComboBox);
		
		String[] subjects = {"General Information", "Philosophy & Psychology", "Religion",
				"Social Sciences", "Language", "Science", "Technology", "Arts & Recreation",
				"Literature", "History & Geography"};
		
		for(String s : subjects) {
			comboBoxSubject.addItem(s);
		}
		
		String[] langArr = {"English", "Filipino"};
		for (String s : langArr) {
			languageComboBox.addItem(s);
		}
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 1236, 321);
		panel.add(scrollPane);
		
		table = new JTable();
		table.setShowVerticalLines(false);
		table.setShowHorizontalLines(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null, null, null, null},
			},
			new String[] {
				"Book Number", "Title", "Author", "ISBN", "Publisher", "Language", "Subject", "Dewey", "Accession", "Status", "Date Registered"
			}
		));
		
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
		
		table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
		    @Override
		    public Component getTableCellRendererComponent(JTable table,
		            Object value, boolean isSelected, boolean hasFocus, int row, int col) {

		        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);

		        String status = (String)table.getModel().getValueAt(row, 9);
		        if ("Available".equals(status)) {
		        	setBackground(table.getBackground());
		            setForeground(table.getForeground());
		        } else {
		        	setBackground(Color.GRAY);
		            setForeground(Color.WHITE);
		            
		        }       
		        return this;
		    }   
		});
		 
		scrollPane.setViewportView(table);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBounds(435, 596, 113, 30);
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
		btnUpdate.setForeground(new Color(245, 255, 250));
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnUpdate.setBorderPainted(false);
		btnUpdate.setBackground(new Color(0, 153, 0));
		panel.add(btnUpdate);
		
		txtSrTitle = new JTextField();
		txtSrTitle.setBounds(815, 400, 358, 30);
		txtSrTitle.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtSrTitle.setColumns(10);
		panel.add(txtSrTitle);
		
		JLabel lblNewLabel_1 = new JLabel("Book Title");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(815, 369, 64, 30);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel.add(lblNewLabel_1);
		
		txtSrBookNum = new JTextField();
		txtSrBookNum.setBounds(815, 469, 358, 30);
		txtSrBookNum.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtSrBookNum.setColumns(10);
		panel.add(txtSrBookNum);
		
		JLabel lblAuthors_1 = new JLabel("Book Number");
		lblAuthors_1.setForeground(new Color(255, 255, 255));
		lblAuthors_1.setBounds(815, 438, 89, 30);
		lblAuthors_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel.add(lblAuthors_1);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setBounds(1002, 526, 125, 30);
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				search();
				
			}
		});
		btnSearch.setForeground(Color.WHITE);
		btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSearch.setBorderPainted(false);
		btnSearch.setBackground(new Color(220, 20, 60));
		panel.add(btnSearch);
		
		JButton btnViewData = new JButton("View Data");
		btnViewData.setBounds(868, 526, 124, 30);
		btnViewData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				view();
			}
		});
		btnViewData.setForeground(Color.WHITE);
		btnViewData.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnViewData.setBorderPainted(false);
		btnViewData.setBackground(new Color(220, 20, 60));
		panel.add(btnViewData);
		
		txtTitle = new JTextField();
		panel.add(txtTitle);
		txtTitle.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtTitle.setBounds(76, 400, 300, 30);
		txtTitle.setColumns(10);
		
		JButton btnExport = new JButton("Export to CSV");
		btnExport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				export();					

				
			}
		});
		btnExport.setForeground(Color.WHITE);
		btnExport.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnExport.setBorderPainted(false);
		btnExport.setBackground(new Color(0, 128, 0));
		btnExport.setBounds(868, 567, 259, 30);
		panel.add(btnExport);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\pc\\Library-Management-System\\res\\Untitled design.png"));
		lblNewLabel_2.setBounds(0, 0, 1256, 686);
		panel.add(lblNewLabel_2);
										
		displayLatestData();
	}
	
	Connection conn;
	PreparedStatement pst;
	ResultSet rs;
	
	
	private JTextField txtSrTitle;
	private JTextField txtSrBookNum;
	
	public void export() {						
		
		// Create a format for the date in the file name
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	    // Get the current date and format it
	    String currentDate = dateFormat.format(new Date());

	    // Construct the base file name with the current date
	    String baseFileName = "C:\\Users\\LINDELL\\Desktop\\books_export_" + currentDate + ".csv";

	    // Initialize the file name
	    String fileName = baseFileName;

	    // Check if the file already exists
	    int fileIndex = 1;
	    while (fileExists(fileName)) {
	        // Append a suffix to make the file name unique
	        fileName = baseFileName.replace(".csv", "_" + fileIndex + ".csv");
	        fileIndex++;
	    }

	    try {
	        FileWriter fw = new FileWriter(fileName);
	        try {
	            pst = conn.prepareStatement("SELECT * From Books");
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        rs = pst.executeQuery();

	        while (rs.next()) {
	        	fw.append(rs.getString(1));
				fw.append(',');
				fw.append(rs.getString(2));
				fw.append(',');
				fw.append(rs.getString(3));
				fw.append(',');
				fw.append(rs.getString(4));
				fw.append(',');
				fw.append(rs.getString(5));
				fw.append(',');
				fw.append(rs.getString(6));
				fw.append(',');
				fw.append(rs.getString(7));
				fw.append(',');
				fw.append(rs.getString(8));
				fw.append(',');
				fw.append(rs.getString(9));
				fw.append(',');
				fw.append(rs.getString(10));
				fw.append(',');
				fw.append(rs.getString(11));
				fw.append(',');
				fw.append(rs.getString(12));
				fw.append('\n');
	        }
	        JOptionPane.showMessageDialog(getRootPane(), "Export success");
	        fw.flush();
	        fw.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
								
		
	}
	
	// check if file already exissts
	private boolean fileExists(String fileName) {
	    File file = new File(fileName);
	    return file.exists();
	}
		
		
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
					JOptionPane.showMessageDialog(getRootPane(), "Updated succesfully");
					displayLatestData();
				}
				
				else {
					JOptionPane.showMessageDialog(getRootPane(), "No rows updated");
				}
				
			}
			else {
	            JOptionPane.showMessageDialog(getRootPane(), "No row selected");
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
				
				JOptionPane.showMessageDialog(getRootPane(), "Book Registered");
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
									
					String sql = "SELECT * FROM Books WHERE Title = ?";
					PreparedStatement pstmt = conn.prepareStatement(sql);
					
					String bookTitle = txtSrTitle.getText();
					pstmt.setString(1, bookTitle);
					
					ResultSet rs = pstmt.executeQuery();

			        DefaultTableModel tblModel = (DefaultTableModel) table.getModel();	        
		
		        	DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();
		        	
		        	
		        	if(!rs.isBeforeFirst()) {
		        		JOptionPane.showMessageDialog(this, "Book not found.");
		        		txtSrBookNum.setText("");
		        		txtTitle.setText("");
		        	}
		        	
		        	else {
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
	        			String dateRegistered = rs.getString("date_registered");
	        		
	        			comboBoxModel.addElement(accession);
	        			
	        			txtSrBookNum.setText(bookNum);
	        			txtTitle.setText(title);
	        			
	        			//array to store data into jtable
	        			String tbData[] = {bookNum, title, author, isbn, publisher,
	        					language, subject, dewey, accession, status, dateRegistered};
	        			
	        			
	        			//add string array data to jtable
	        			tblModel.addRow(tbData);
		        		}
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
						String quantity = String.valueOf(rs.getInt("Quantity"));
						String dewey = String.valueOf(rs.getDouble("Dewey_Decimal"));
						String accession = String.valueOf(rs.getInt("Accession_Num"));
						String status = rs.getString("book_status");
						
						//array to store data into jtable
						String tbData[] = {bookNum, title, author, isbn, publisher,
								language, subject, dewey, accession, status};
									
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
