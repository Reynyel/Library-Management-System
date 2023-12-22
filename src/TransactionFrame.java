import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFormattedTextField.AbstractFormatter;
import color.AlternateColorRender;
import javax.swing.JRadioButton;

public class TransactionFrame extends JPanel {

	private JPanel contentPane;
	private JPanel panel;	
	private JTextField txtBookNum;
	private JTextField txtTitle;
	private JTextField txtAuthor;
	private JTable tblTransac;
	private JTextField txtBorrID;
	private JComboBox cbAccession;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TransactionFrame frame = new TransactionFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void Remove(JTable table, int[] col_indices) {
	    TableColumnModel columnModel = table.getColumnModel();
	    
	    for (int col_index : col_indices) {
	        TableColumn col = columnModel.getColumn(col_index);
	        columnModel.removeColumn(col);
	    }
	}
	 
	/**
	 * Create the frame.
	 */
	public TransactionFrame() {
		setPreferredSize(new Dimension(1256, 686));
	    setLayout(null);
		setBounds(100, 100, 1687, 743);
		
		panel = new JPanel();
        panel.setBounds(-67, 22, 1288, 686);
        add(panel);
        panel.setLayout(null);
		
		tblTransac = new JTable();
		tblTransac.setBorder(new LineBorder(new Color(0, 0, 0)));
		tblTransac.setColumnSelectionAllowed(true);
		tblTransac.setCellSelectionEnabled(true);
		/*TODO:
		 * Change the table to books table when you press search
		 * Change the table to transaction table after pressing update
		 * Set a limit to how many books/titles a user can borrow - 3
		 * The user cannot also borrow the same titles twice
		 * add a date and time in the dashboard
		 * add a notification feature
		 * set a color for books that have already reached their due date*/
		tblTransac.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null, null, null},
			},
			new String[] {
					"Book Number", "Title", "Author", "ISBN", "Publisher", "Language", "Subject", "Dewey", "Accession", "Status"
			}
		));
		tblTransac.setBounds(126, 598, 632, -215);
		
		AlternateColorRender alternate = new AlternateColorRender();
		tblTransac.setDefaultRenderer(Object.class, alternate);
		panel.add(tblTransac);
		
		JScrollPane js = new JScrollPane(tblTransac);
		js.setVisible(true);
		js.setBounds(70, 317, 1207, 276); // Adjust the bounds to match the table
		panel.add(js);
		

		
		txtBorrID = new JTextField();
		txtBorrID.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtBorrID.setColumns(10);
		txtBorrID.setBounds(193, 273, 621, 30);
		panel.add(txtBorrID);
		
		JLabel lblBorrowersName = new JLabel("Borrower's ID");
		lblBorrowersName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblBorrowersName.setBounds(70, 272, 113, 30);
		panel.add(lblBorrowersName);
		
				
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setFont(new Font("Segoe UI Light", Font.BOLD, 15));
		btnUpdate.setBounds(837, 266, 128, 40);
		panel.add(btnUpdate);
		
		JRadioButton radioTransaction = new JRadioButton("View Transactions");
		radioTransaction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayTransactionHistory();
			}
		});
		radioTransaction.setBounds(978, 278, 137, 23);
		panel.add(radioTransaction);
		
		JRadioButton radioBooks = new JRadioButton("View Books");
		radioBooks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewBooks();
			}
		});
		radioBooks.setBounds(1117, 278, 109, 23);
		panel.add(radioBooks);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update();
			}
		});
		
		 ButtonGroup radioGroup = new ButtonGroup();
		    radioGroup.add(radioTransaction);
		    radioGroup.add(radioBooks);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(70, 0, 788, 250);
		
		String title = "Borrow Book";
		Border border = BorderFactory.createTitledBorder(title);
		panel_1.setBorder(border);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		txtStatus = new JTextField();
		txtStatus.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtStatus.setColumns(10);
		txtStatus.setBounds(122, 191, 91, 27);
		panel_1.add(txtStatus);
		
		txtTitle = new JTextField();
		txtTitle.setBounds(124, 36, 621, 27);
		panel_1.add(txtTitle);
		txtTitle.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtTitle.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Book Title");
		lblNewLabel.setBounds(21, 35, 90, 28);
		panel_1.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		txtBookNum = new JTextField();
		txtBookNum.setBounds(124, 75, 621, 27);
		panel_1.add(txtBookNum);
		txtBookNum.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtBookNum.setColumns(10);
		
		JLabel lblBookNumber = new JLabel("Book Number");
		lblBookNumber.setBounds(20, 74, 113, 28);
		panel_1.add(lblBookNumber);
		lblBookNumber.setFont(new Font("Tahoma", Font.PLAIN, 15));
		JButton btnSearchBook = new JButton("Search Book");
		btnSearchBook.setBounds(618, 167, 128, 40);
		panel_1.add(btnSearchBook);
		btnSearchBook.setFont(new Font("Segoe UI Light", Font.BOLD, 15));
		
		txtAuthor = new JTextField();
		txtAuthor.setBounds(124, 113, 621, 27);
		panel_1.add(txtAuthor);
		txtAuthor.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtAuthor.setColumns(10);
		
		JLabel lblAuthors = new JLabel("Author(s)");
		lblAuthors.setBounds(21, 113, 77, 27);
		panel_1.add(lblAuthors);
		lblAuthors.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lblAccession = new JLabel("Accession");
		lblAccession.setBounds(21, 151, 80, 24);
		panel_1.add(lblAccession);
		lblAccession.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		cbAccession = new JComboBox();
		cbAccession.setBounds(122, 152, 89, 28);
		panel_1.add(cbAccession);
		cbAccession.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setStatusBasedOnAccession();
			}
		});
		cbAccession.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cbAccession.setBackground(Color.WHITE);
		
		JLabel lblStatus = new JLabel("Status");
		lblStatus.setBounds(21, 194, 80, 20);
		panel_1.add(lblStatus);
		lblStatus.setFont(new Font("Tahoma", Font.PLAIN, 15));
		String title2 = "Return Book";
		Border border2 = BorderFactory.createTitledBorder(title2);
		btnSearchBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				search();
			}
		});
		
		fetchAndDisplayData();
	}
	
	Connection conn;
	PreparedStatement pst;
	ResultSet rs;
	
	// This method is to set the status based on the selected accession number
	private void setStatusBasedOnAccession() {
		try {
			String bookNumber = txtBookNum.getText();
			Object obAccession = cbAccession.getSelectedItem();
			
			if(obAccession != null) {
				int accessionNumber = Integer.parseInt(cbAccession.getSelectedItem().toString());				
				String selectStatusSql = "SELECT book_status FROM Books WHERE Book_Num = ? AND Accession_Num = ?";
				try(PreparedStatement selectStatusStmt = conn.prepareStatement(selectStatusSql)){
					selectStatusStmt.setString(1, bookNumber);
					selectStatusStmt.setInt(2, accessionNumber);
					ResultSet statusResultSet = selectStatusStmt.executeQuery();
					
					if(statusResultSet.next()) {
						String status = statusResultSet.getString("book_status");
						txtStatus.setText(status);
					}
					
					else {
						txtStatus.setText(selectStatusSql);
					}
				}
				
				catch(SQLException e) {
					e.printStackTrace();
				}
			}
			else {
				// Handle the case when no item is selected in the combo box
	            txtStatus.setText("No Accession Selected");
			}
		}
		
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	// New method to fetch and display data in the JTable
    private void fetchAndDisplayData() {
        try {
            // Load the JDBC driver (version 4.0 or later)
            Class.forName("com.mysql.jdbc.Driver");

            // Establish a connection
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/BooksDB", "root", "ranielle25");
            System.out.println("Connected");

            // Fetch data from Transactions table
            String selectTransactionsSql = "SELECT * FROM Books WHERE book_status = 'Available'";
            try (PreparedStatement selectTransactionsStmt = conn.prepareStatement(selectTransactionsSql)) {
                ResultSet rs = selectTransactionsStmt.executeQuery();

                DefaultTableModel tblModel = (DefaultTableModel) tblTransac.getModel();
                tblModel.setRowCount(0); // Clear existing rows

                while (rs.next()) {
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
    				
    				//array to store data into jtable
    				String tbData[] = {bookNum, title, author, isbn, publisher,
    						language, subject, dewey, accession, status};

                    // add string array data to jtable
                    tblModel.addRow(tbData);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String userId;
    private JTextField txtStatus;
    public void update() {
        try {
            // Load the JDBC driver (version 4.0 or later)
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish a connection
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/BooksDB?useSSL=false", "root", "ranielle25");
            System.out.println("Connected");

            // Get values from UI components
            String bn = txtBookNum.getText();
            String tl = txtTitle.getText();
            
            userId = txtBorrID.getText();
            String borrId = txtBorrID.getText();
            
            Object obAcc = cbAccession.getSelectedItem();
            
            String userType = getUserType(borrId);
            
            // check if the user has inserted a book title
            if(tl.isEmpty() && bn.isEmpty() && obAcc == null) {
            	JOptionPane.showMessageDialog(getRootPane(), "Please search for the book first");       
            }
            
            else {
            	int acc = Integer.parseInt(cbAccession.getSelectedItem().toString());
            	// Check if the user is a student or faculty/school staff based on ID number
            	if ("Student".equals(userType)) {
            	      			
            		String borrowerNameSql = "SELECT LastName, FirstName, MiddleName FROM Students WHERE StudentNo = ?";
            		try(PreparedStatement borrowerNameStmt = conn.prepareStatement(borrowerNameSql)){
            			borrowerNameStmt.setString(1, borrId);
            			ResultSet borrowerNameResult = borrowerNameStmt.executeQuery();
            			
            			int borrowedBooksCount = getBorrowedBooksCount(userId);
            			
            			// Check if the student has already borrowed the same title
            		    if (hasBorrowedSameTitle(userId, tl)) {
            		        JOptionPane.showMessageDialog(getRootPane(), "This student cannot borrow the same title twice.");
            		        return; // Exit the method without proceeding with the transaction
            		    } 
            		    if(borrowedBooksCount >= 3) {
            		    	JOptionPane.showMessageDialog(getRootPane(), "This student cannot borrow more than 3 books.");
            		        return; // Exit the method without proceeding with the transaction
            		    	
            		    }
            		    
            			if(borrowerNameResult.next()) {
            				String borrowerName = borrowerNameResult.getString("LastName") + 
            						", " + borrowerNameResult.getString("FirstName") +
            						" " + borrowerNameResult.getString("MiddleName");
            				
            				insertTransaction(bn, tl, acc, borrowerName);          			
            			}
            			else {
            				JOptionPane.showMessageDialog(getRootPane(), "Name not found!");
            			}
            		}
            		
            		
            	} else if ("Faculty".equals(userType) ||  "Staff".equals(userType)) {
            		String borrowerNameSql = "SELECT LastName, FirstName, MiddleName FROM Employees WHERE employeeID = ?";
            		try(PreparedStatement borrowerNameStmt = conn.prepareStatement(borrowerNameSql)){
            			borrowerNameStmt.setString(1, borrId);
            			ResultSet borrowerNameResult = borrowerNameStmt.executeQuery();
            			
            			if(borrowerNameResult.next()) {
            				String borrowerName = borrowerNameResult.getString("LastName") + 
            						", " + borrowerNameResult.getString("FirstName") +
            						" " + borrowerNameResult.getString("MiddleName");
            				
            				insertTransaction(bn, tl, acc, borrowerName);
            				
            			}
            			else {
            				JOptionPane.showMessageDialog(getRootPane(), "Name not found!");
            			}
            		}
            		
            		
            	} else {
            		JOptionPane.showMessageDialog(getRootPane(), "Invalid user ID format!");
            	}
            	
            }
            
            
            
            
            fetchAndDisplayData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void insertTransaction(String bn, String tl, int acc, String borrowerName) {
        String checkId = userId;

        try {
            // Load the JDBC driver (version 4.0 or later)
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish a connection
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/BooksDB?useSSL=false", "root", "ranielle25");
            System.out.println("Connected");
            
            // Update book status in Books table
            String updateBookStatusSql = "UPDATE Books SET book_status = 'Borrowed' WHERE Book_Num = ? AND Accession_Num = ?";
            
           
            String bookStatus = getBookStatus(bn, acc);
            
            
            if(bookStatus.equals("Borrowed")) {
            	JOptionPane.showMessageDialog(getRootPane(), "This book is currently borrowed");
            }
            else {
            	try(PreparedStatement updateBookStatusStmt = conn.prepareStatement(updateBookStatusSql)){
            		updateBookStatusStmt.setString(1, bn);
            		updateBookStatusStmt.setInt(2, acc);      
            		
            		//Execute query
            		int rowAffected = updateBookStatusStmt.executeUpdate();
            		String borrId = txtBorrID.getText();
            		
            		String userType = getUserType(borrId);
            		
            		if ("Faculty".equals(userType) || "Staff".equals(userType)) {                  
            			String insertSql = "INSERT INTO Transactions (BooNum, Title, AccessionNum, Borrower, BookStatus, transaction_date, return_date, user_id, user_type) " +
            					"VALUES (?, ?, ?, ?, 'Borrowed', CURRENT_DATE(), 'IND', ?, ?)";
            			
            			try (PreparedStatement pstmt = conn.prepareStatement(insertSql)) {
            				pstmt.setString(1, bn);
            				pstmt.setString(2, tl);
            				pstmt.setInt(3, acc);
            				pstmt.setString(4, borrowerName);
            				pstmt.setString(5, userId);
            				pstmt.setString(6, userType);            				
            				
            				// Execute the update
            				int rowsAffected = pstmt.executeUpdate();
            				
            				JOptionPane.showMessageDialog(getRootPane(), "Transaction Recorded!");
            				
            			} catch (SQLException e) {
            				e.printStackTrace();
            			}
            		} else {
            			        			
            			// If user ID does not start with "1", set return date to three days from the transaction date
            			String insertSql = "INSERT INTO Transactions (BooNum, Title, AccessionNum, Borrower, BookStatus, transaction_date, return_date, user_id, user_type) " +
            					"VALUES (?, ?, ?, ?, 'Borrowed', CURRENT_DATE(), DATE_ADD(CURDATE(), INTERVAL 3 DAY), ?, ?)";
            			
            			
            			try (PreparedStatement pstmt = conn.prepareStatement(insertSql)) {
            				pstmt.setString(1, bn);
            				pstmt.setString(2, tl);
            				pstmt.setInt(3, acc);
            				pstmt.setString(4, borrowerName);
            				pstmt.setString(5, userId);
            				pstmt.setString(6, userType);
            				
            				// Execute the update
            				int rowsAffected = pstmt.executeUpdate();
            				
            				JOptionPane.showMessageDialog(getRootPane(), "Transaction Recorded!");
            				
            			} catch (SQLException e) {
            				e.printStackTrace();
            			}
            			
            		}
            		
            	}
            	catch(SQLException e) {
            		e.printStackTrace();
            	}
            	
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }   
    
    //check if the student has already borrowed the same title
    private boolean hasBorrowedSameTitle(String userId, String title) {
        boolean alreadyBorrowed = false;

        String checkSql = "SELECT * FROM Transactions WHERE user_id = ? AND Title = ? AND BookStatus = 'Borrowed'";
        try (PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {
            checkStmt.setString(1, userId);
            checkStmt.setString(2, title);
            ResultSet checkResult = checkStmt.executeQuery();

            alreadyBorrowed = checkResult.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return alreadyBorrowed;
    }
    
    //get the count of books already borrowed by the student
    private int getBorrowedBooksCount(String userId) {
        int count = 0;

        String countSql = "SELECT COUNT(*) AS bookCount FROM Transactions WHERE user_id = ? AND BookStatus = 'Borrowed'";
        try (PreparedStatement countStmt = conn.prepareStatement(countSql)) {
            countStmt.setString(1, userId);
            ResultSet countResult = countStmt.executeQuery();

            if (countResult.next()) {
                count = countResult.getInt("bookCount");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count;
    }
    
    //get book status
    private String getBookStatus(String bookNum, int accNum) {
    	String bookStatus = "";
    	
    	String checkBookStatusSql = "SELECT book_status FROM Books WHERE Book_NUM = ? AND Accession_Num = ?";
    	
    	try(PreparedStatement bookStatusStmt = conn.prepareStatement(checkBookStatusSql)){
    		bookStatusStmt.setString(1, bookNum);
    		bookStatusStmt.setInt(2, accNum);
    		
    		ResultSet bookStatusResult = bookStatusStmt.executeQuery();
    		
    		if(bookStatusResult.next()) {
    			bookStatus = bookStatusResult.getString("book_status");
    		}
    	}
    	catch(SQLException e) {
    		e.printStackTrace();
    	}
    	
    	return bookStatus;
    }
    
    //Get user type from both Employees and Students table
    private String getUserType(String userId) {
    	String userType = "";
    	
		String userTypeSql = "SELECT UserType FROM Students WHERE StudentNo = ? UNION SELECT UserType FROM Employees WHERE employeeID = ?";
		try(PreparedStatement userTypeStmt = conn.prepareStatement(userTypeSql)) {
			userTypeStmt.setString(1, userId);
			userTypeStmt.setString(2, userId);
			
			ResultSet userTypeResult = userTypeStmt.executeQuery();
			
			if(userTypeResult.next()) {
				userType = userTypeResult.getString("UserType");
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return userType;
    	
    }
    
	//search books table and returns all available titles
    public void search() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/BooksDB", "root", "ranielle25");

            String title = txtTitle.getText();
            String sql = "SELECT * FROM Books WHERE Title = ? AND book_status = 'Available'";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, title);
                ResultSet rs = pstmt.executeQuery();

                DefaultTableModel tblModel = (DefaultTableModel) tblTransac.getModel();            
             
                // Remove existing columns
	            tblModel.setColumnCount(0);
	            
	            // Clear existing rows
	            tblModel.setRowCount(0); 
	            
	            // Define new column names
	            String[] newColumnNames = {"Book Number", "Title", "Author", "ISBN", "Publisher", "Language", "Subject", "Dewey", "Accession", "Status"};

	            // Set new column names
	            tblModel.setColumnIdentifiers(newColumnNames);

                if (!rs.isBeforeFirst()) {
                    JOptionPane.showMessageDialog(this, "Sorry, book not found!");

                    txtTitle.setText("");
                    txtAuthor.setText("");
                    cbAccession.removeAllItems();
                } else {
                    DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();
                    while (rs.next()) {
                    	//add data until there is none
	        			String bookNum = rs.getString("Book_Num");
	        			String searchTitle = rs.getString("Title");
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

                        txtTitle.setText(searchTitle);
                        txtAuthor.setText(author);
                        txtBookNum.setText(bookNum);
                        cbAccession.setModel(comboBoxModel);

                        // Update the table with search results
                        String tbData[] = {bookNum, searchTitle, author, isbn, publisher,
	        					language, subject, dewey, accession, status};
                        tblModel.addRow(tbData);
                    }
                }
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
	
	public void displayTransactionHistory() {
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

	            DefaultTableModel tblModel = (DefaultTableModel) tblTransac.getModel();

	            // Remove existing columns
	            tblModel.setColumnCount(0);
	            
	            // Set row count to 0 to clear existing rows
	            tblModel.setRowCount(0);
	            
	            // Define new column names
	            String[] newColumnNames = {"Transaction ID", "Book Num", "Title", "Accession", "Status", "Transaction Date", "Return Date", "Borrower", "ID", "User Type"};

	            // Set new column names
	            tblModel.setColumnIdentifiers(newColumnNames);

	            // Fetch and add new data
	            while (rs.next()) {
	                String transacId = String.valueOf(rs.getInt("Transaction_ID"));
	                String bookNum = rs.getString("BooNum");
	                String title = rs.getString("Title");
	                String accession = String.valueOf(rs.getInt("AccessionNum"));
	                String status = rs.getString("BookStatus");
	                String transacDate = rs.getString("transaction_date");
	                String returnDate = rs.getString("return_date");
	                String borrower = rs.getString("Borrower");
	                String userId = rs.getString("user_id");
	                String userType = rs.getString("user_type");
	                // array to store data into JTable
	                String tbData[] = {transacId, bookNum, title, accession, status, transacDate, returnDate, borrower, userId, userType};

	                // add string array data to JTable
	                tblModel.addRow(tbData);
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	public void viewBooks() {
		try {
            // Load the JDBC driver (version 4.0 or later)
            Class.forName("com.mysql.jdbc.Driver");

            // Establish a connection
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/BooksDB", "root", "ranielle25");
            System.out.println("Connected");

            // Fetch data from Transactions table
            String selectTransactionsSql = "SELECT * FROM Books WHERE book_status = 'Available'";
            try (PreparedStatement selectTransactionsStmt = conn.prepareStatement(selectTransactionsSql)) {
                ResultSet rs = selectTransactionsStmt.executeQuery();

                DefaultTableModel tblModel = (DefaultTableModel) tblTransac.getModel();
                
                // Remove existing columns
	            tblModel.setColumnCount(0);
	            
	            // Clear existing rows
	            tblModel.setRowCount(0); 
	            
	            // Define new column names
	            String[] newColumnNames = {"Book Number", "Title", "Author", "ISBN", "Publisher", "Language", "Subject", "Dewey", "Accession", "Status", "Date Registered"};

	            // Set new column names
	            tblModel.setColumnIdentifiers(newColumnNames);
	            
                while (rs.next()) {
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

                    // add string array data to jtable
                    tblModel.addRow(tbData);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}
