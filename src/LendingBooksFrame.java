import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import color.AlternateColorRender;
import tablemodel.NonEditTableModel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JRadioButton;

public class LendingBooksFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtBookNum;
	private JTextField txtTitle;
	private JTable tblTransac;
	private JTextField txtBorrID;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LendingBooksFrame frame = new LendingBooksFrame();
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
	public LendingBooksFrame() {
		setTitle("Lending Module");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1130, 750);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(127, 255, 212));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblBookNumber = new JLabel("Book Number");
		lblBookNumber.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblBookNumber.setBounds(47, 54, 113, 28);
		contentPane.add(lblBookNumber);
		
		txtBookNum = new JTextField();
		txtBookNum.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtBookNum.setColumns(10);
		txtBookNum.setBounds(150, 55, 621, 27);
		contentPane.add(txtBookNum);
		
		txtTitle = new JTextField();
		txtTitle.setEditable(false);
		txtTitle.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtTitle.setColumns(10);
		txtTitle.setBounds(150, 94, 621, 27);
		contentPane.add(txtTitle);
		
		JLabel lblNewLabel = new JLabel("Book Title");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(47, 93, 90, 28);
		contentPane.add(lblNewLabel);
		
		tblTransac = new JTable();
		tblTransac.setBorder(new LineBorder(new Color(0, 0, 0)));
		tblTransac.setColumnSelectionAllowed(true);
		tblTransac.setCellSelectionEnabled(true);
		
		tblTransac.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null, null},
			},
			new String[] {
				"Transaction ID", "Book Num", "Title", "Accession", "Status", "Transaction Date", "Return Date", "Borrower", "ID"
			}
		));
		Object[][] data = {null, null, null, null, null, null, null, null, null};
		Object[] columnNames = {"Transaction ID", "Book Num", "Title", "Accession", "Status", "Transaction Date", "Return Date", "Borrower", "ID", "User Type"};
		NonEditTableModel model;
		Set<Integer> editableColumns = new HashSet<>();
		tblTransac.setModel(new NonEditTableModel(data, columnNames, editableColumns));
		tblTransac.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 2) { //double click rows
					int selectedRow = tblTransac.getSelectedRow();
					if(selectedRow != -1) {
						//get data and populate textbox and combobox
						String transacId = (String) tblTransac.getValueAt(selectedRow, 0);
						String bookNum = (String) tblTransac.getValueAt(selectedRow, 1);
						String title = (String) tblTransac.getValueAt(selectedRow, 2);
						String acc = (String) tblTransac.getValueAt(selectedRow, 3);
						String status = (String) tblTransac.getValueAt(selectedRow, 4);
						String transacDate = (String) tblTransac.getValueAt(selectedRow, 5);
						String returnDate = (String) tblTransac.getValueAt(selectedRow, 6);
						String userName = (String) tblTransac.getValueAt(selectedRow, 7);
						String userId = (String) tblTransac.getValueAt(selectedRow, 8);
						
						//Populate fields
						txtTitle.setText(title);
						txtBookNum.setText(bookNum);
						txtAccession.setText(acc); //FIX THIS						
						txtBorrDate.setText(transacDate);
						txtReturnDate.setText(returnDate);
						
					}
				}
			}
		});
		tblTransac.setBounds(126, 598, 632, -215);
		
		AlternateColorRender alternate = new AlternateColorRender();
		tblTransac.setDefaultRenderer(Object.class, alternate);
		contentPane.add(tblTransac);
		
		JScrollPane js = new JScrollPane(tblTransac);
		js.setVisible(true);
		js.setBounds(24, 355, 1059, 276); // Adjust the bounds to match the table
		contentPane.add(js);
		
		JPanel panel = new JPanel();
		panel.setBounds(24, 279, 1059, 65);
		contentPane.add(panel);
		panel.setLayout(null);
		
		txtBorrID = new JTextField();
		txtBorrID.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtBorrID.setColumns(10);
		txtBorrID.setBounds(130, 23, 621, 30);
		panel.add(txtBorrID);
		
		JLabel lblBorrowersName = new JLabel("Borrower's ID");
		lblBorrowersName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblBorrowersName.setBounds(10, 22, 113, 30);
		panel.add(lblBorrowersName);
		
		JRadioButton radioBorrowed = new JRadioButton("Borrowed Books");
		radioBorrowed.setBounds(774, 28, 109, 23);
		radioBorrowed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayTransactionHistory();
			}
		});
		panel.add(radioBorrowed);
		
		JRadioButton radioReturned = new JRadioButton("Returned Books");
		radioReturned.setBounds(885, 28, 109, 23);
		radioReturned.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayReturnedBooks();
			}
		});
		panel.add(radioReturned);
		
		ButtonGroup radioGroup = new ButtonGroup();
	    radioGroup.add(radioBorrowed);
	    radioGroup.add(radioReturned);
		
		JButton btnBack = new JButton("Back");
		btnBack.setForeground(new Color(255, 255, 255));
		btnBack.setBackground(new Color(65, 105, 225));
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnBack.setBorderPainted(false);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainMenuFrame menu = new MainMenuFrame();
				menu.setVisible(true);
				dispose();
			}
		});
		btnBack.setBounds(34, 642, 128, 40);
		contentPane.add(btnBack);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(24, 33, 1059, 235);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblAccession = new JLabel("Accession");
		lblAccession.setBounds(25, 98, 80, 24);
		panel_1.add(lblAccession);
		lblAccession.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		txtBorrDate = new JTextField();
		txtBorrDate.setEditable(false);
		txtBorrDate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtBorrDate.setColumns(10);
		txtBorrDate.setBounds(125, 140, 155, 27);
		panel_1.add(txtBorrDate);
		
		JLabel lblBorrowedDate = new JLabel("Lending Date");
		lblBorrowedDate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblBorrowedDate.setBounds(25, 143, 107, 20);
		panel_1.add(lblBorrowedDate);
		
		txtReturnDate = new JTextField();
		txtReturnDate.setEditable(false);
		txtReturnDate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtReturnDate.setColumns(10);
		txtReturnDate.setBounds(125, 178, 155, 27);
		panel_1.add(txtReturnDate);
		
		JLabel lblReturnDate = new JLabel("Return Date");
		lblReturnDate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblReturnDate.setBounds(22, 178, 80, 20);
		panel_1.add(lblReturnDate);
		
		txtAccession = new JTextField();
		txtAccession.setEditable(false);
		txtAccession.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtAccession.setColumns(10);
		txtAccession.setBounds(125, 102, 80, 27);
		panel_1.add(txtAccession);
		
		JLabel lblPenalty = new JLabel("Penalty Fee");
		lblPenalty.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPenalty.setBounds(485, 178, 80, 20);
		panel_1.add(lblPenalty);
		
		txtPenalty = new JTextField();
		txtPenalty.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtPenalty.setEditable(false);
		txtPenalty.setColumns(10);
		txtPenalty.setBounds(588, 178, 155, 27);
		panel_1.add(txtPenalty);
		
		JLabel lblDateReturned = new JLabel("Date Returned");
		lblDateReturned.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDateReturned.setBounds(485, 140, 117, 20);
		panel_1.add(lblDateReturned);
		
		txtDateReturned = new JTextField();
		txtDateReturned.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtDateReturned.setEditable(false);
		txtDateReturned.setColumns(10);
		txtDateReturned.setBounds(588, 140, 155, 27);
		panel_1.add(txtDateReturned);
						
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBounds(777, 132, 128, 40);
		panel_1.add(btnUpdate);
		btnUpdate.setFont(new Font("Segoe UI Light", Font.BOLD, 15));
		JButton btnSearchBook = new JButton("Search Book");
		btnSearchBook.setBounds(777, 184, 128, 40);
		panel_1.add(btnSearchBook);
		btnSearchBook.setFont(new Font("Segoe UI Light", Font.BOLD, 15));
		btnSearchBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				search();
			}
		});
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update();
			}
		});
		
		fetchAndDisplayData();
	}
	Connection conn;
	PreparedStatement pst;
	ResultSet rs;
	private JTextField txtBorrDate;
	private JTextField txtReturnDate;
	private JTextField txtAccession;
	private JTextField txtPenalty;
	private JTextField txtDateReturned;
	
	// New method to fetch and display data in the JTable
    private void fetchAndDisplayData() {
        try {
            // Load the JDBC driver (version 4.0 or later)
            Class.forName("com.mysql.jdbc.Driver");

            // Establish a connection
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/BooksDB", "root", "ranielle25");
            System.out.println("Connected");

            // Fetch data from Transactions table
            String selectTransactionsSql = "SELECT * FROM Transactions WHERE BookStatus = 'Borrowed'";
            
            
            try (PreparedStatement selectTransactionsStmt = conn.prepareStatement(selectTransactionsSql)) {
                ResultSet rs = selectTransactionsStmt.executeQuery();

                DefaultTableModel tblModel = (DefaultTableModel) tblTransac.getModel();
                tblModel.setRowCount(0); // Clear existing rows

                while (rs.next()) {
                    String transacId = rs.getString("transaction_id");
                    String bookNum = rs.getString("BooNum");
                    String title = rs.getString("Title");
                    String accNum = rs.getString("AccessionNum");
                    String bookStatus = rs.getString("BookStatus");
                    String transactionDate = rs.getString("transaction_date");
                    String returnDate = rs.getString("return_date");
                    String userName = rs.getString("Borrower");
                    String userId = rs.getString("user_id");
                    String userType = rs.getString("user_type");
                    // array to store data into jtable
                    String tbData[] = {transacId, bookNum, title, accNum, bookStatus,
                            transactionDate, returnDate, userName, userId, userType};

                    // add string array data to jtable
                    tblModel.addRow(tbData);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void updatePenalty(String transactionId) {
    	try {
    		// Load the JDBC driver (version 4.0 or later)
            Class.forName("com.mysql.cj.jdbc.Driver");           
            // Establish a connection
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/BooksDB?useSSL=false", "root", "ranielle25");
            conn.setAutoCommit(true);
            System.out.println("Connected");
            
         // Get the selected row from the table
            int selectedRow = tblTransac.getSelectedRow();

            // Check if a row is selected
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(rootPane, "Please select a book from the table for return.");
                return;
            }
            
            
            String updatePenaltyFeeSql = "UPDATE Returned SET penalty_fee = '10' WHERE transaction_id = ?";
            
            try(PreparedStatement updatePenaltyFeeStmt = conn.prepareStatement(updatePenaltyFeeSql)){
            	// Set parameters for updatePenaltyFeeStmt
                updatePenaltyFeeStmt.setString(1, transactionId);
                
                int rowsPenaltyAffected = updatePenaltyFeeStmt.executeUpdate();
                
                if(rowsPenaltyAffected > 0) {
                	JOptionPane.showMessageDialog(rootPane, "PEEEEEEEEEEE");
                }
                else {
                	JOptionPane.showMessageDialog(rootPane, "Error with updatePenalty() method");
                }
            }
            catch(SQLException e) {
            	e.printStackTrace();
            }
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    
    //update records
    public void update() {
    	try {
            // Load the JDBC driver (version 4.0 or later)
            Class.forName("com.mysql.cj.jdbc.Driver");           
            // Establish a connection
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/BooksDB?useSSL=false", "root", "ranielle25");
            conn.setAutoCommit(true);
            System.out.println("Connected");
            // Get the selected row from the table
            int selectedRow = tblTransac.getSelectedRow();

            // Check if a row is selected
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(rootPane, "Please select a book from the table for return.");
                return;
            }

            // Get the transaction ID from the selected row
            String transacId = tblTransac.getValueAt(selectedRow, 0).toString();

            // Update the book and transaction status
            String updateBookStatusSql = "UPDATE Books SET book_status = 'Available' WHERE Book_Num = (SELECT BooNum FROM Transactions WHERE transaction_id = ?)";
            String updateTransactionStatusSql = "UPDATE Transactions SET BookStatus = 'Returned' WHERE transaction_id = ?";
            //String updatePenaltyFeeSql = "UPDATE Returned SET penalty_fee = '10' WHERE transaction_id = ?";

            try (PreparedStatement updateBookStatusStmt = conn.prepareStatement(updateBookStatusSql);
                 PreparedStatement updateTransactionStatusStmt = conn.prepareStatement(updateTransactionStatusSql)
                 ) {

                // Set parameters for updateBookStatusStmt
                updateBookStatusStmt.setString(1, transacId);

                // Set parameters for updateTransactionStatusStmt
                updateTransactionStatusStmt.setString(1, transacId);

                // Set parameters for updatePenaltyFeeStmt
                //updatePenaltyFeeStmt.setString(1, transacId);

                // Execute queries
                int rowsAffectedBook = updateBookStatusStmt.executeUpdate();
                int rowsAffectedTransaction = updateTransactionStatusStmt.executeUpdate();
                //int rowsAffectedPenalty = updatePenaltyFeeStmt.executeUpdate();
                
                if (rowsAffectedBook > 0 && rowsAffectedTransaction > 0) {
                    JOptionPane.showMessageDialog(rootPane, "Book returned successfully. Penalty Fee: 0 pesos");
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Error updating book and transaction status with penalty fee.");
                }
                
                
            } catch (SQLException e) {
                e.printStackTrace();
            }

            // Record the returned book in Returned table
            recordReturnedBook(transacId);
            // Remove the borrowed book from Transactions table
            removeBorrowedBook(transacId);
            
            updatePenalty(transacId);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    	
    	
    }

    
	public void search() {
		try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/BooksDB", "root", "ranielle25");

            String id = txtBorrID.getText();
            
            String sql = "SELECT * FROM Transactions WHERE user_id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, id);
                
                ResultSet rs = pstmt.executeQuery();

                DefaultTableModel tblModel = (DefaultTableModel) tblTransac.getModel();                    

	            // Remove existing columns
	            tblModel.setColumnCount(0);
	            
	            // Set row count to 0 to clear existing rows
	            tblModel.setRowCount(0);
	            
	            // Define new column names
	            String[] newColumnNames = {"Transaction ID", "Book Num", "Title", "Accession", "Status", "Transaction Date", "Return Date", "Borrower", "ID"};

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

	                // array to store data into JTable
	                String tbData[] = {transacId, bookNum, title, accession, status, transacDate, returnDate, borrower, userId};

	                // add string array data to JTable
	                tblModel.addRow(tbData);
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
	            
	            // Clear existing rows
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
	
	public void displayReturnedBooks() {
		try {
	        // Load the JDBC driver (version 4.0 or later)
	        Class.forName("com.mysql.jdbc.Driver");

	        // Establish a connection
	        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/BooksDB", "root", "ranielle25");
	        System.out.println("Connected");

	        // Fetch data from Transactions table
	        String selectTransactionsSql = "SELECT * FROM Returned";

	        try (PreparedStatement stmtTransacSql = conn.prepareStatement(selectTransactionsSql)) {
	            ResultSet rs = stmtTransacSql.executeQuery();

	            DefaultTableModel tblModel = (DefaultTableModel) tblTransac.getModel();

	            // Remove existing columns
	            tblModel.setColumnCount(0);
	            
	            // Set row count to 0 to clear existing rows
	            tblModel.setRowCount(0);
	            
	            // Define new column names
	            String[] newColumnNames = {"Transaction ID", "Book Num", "Title", "Accession", "Status", "Due Date", "Date Returned", "Borrower", "ID", "Penalty Fee"};

	            // Set new column names
	            tblModel.setColumnIdentifiers(newColumnNames);

	            // Fetch and add new data
	            while (rs.next()) {
	                String transacId = String.valueOf(rs.getInt("transaction_id"));
	                String bookNum = rs.getString("book_num");
	                String title = rs.getString("title");
	                String accession = String.valueOf(rs.getInt("accession"));
	                String status = rs.getString("status");
	                String dueDate = rs.getString("due_date");
	                String dateReturned = rs.getString("date_returned");
	                String borrower = rs.getString("Borrower");
	                String userId = rs.getString("id");
	                String penaltyFee = String.valueOf(rs.getInt("penalty_fee"));

	                // array to store data into JTable
	                String tbData[] = {transacId, bookNum, title, accession, status, dueDate, dateReturned, borrower, userId, penaltyFee};

	                // add string array data to JTable
	                tblModel.addRow(tbData);
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	/*This method will remove books that were already returned in the Transactions table*/
	public void removeBorrowedBook(String transactionId) {
		try {
			 // Establish a connection
	        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/BooksDB", "root", "ranielle25");

	        // Remove the book from the Transactions table
	        String deleteTransactionSql = "DELETE FROM Transactions WHERE transaction_id = ?";
	        try(PreparedStatement deleteStmt = conn.prepareStatement(deleteTransactionSql)){
	        	deleteStmt.setString(1, transactionId);
	        	
	        	//executee :))))
	        	int rowsAffected = deleteStmt.executeUpdate();
	        	
	        	if (rowsAffected > 0) {
	                JOptionPane.showMessageDialog(rootPane, "Book removed from transactions successfully.");
	            } else {
	                JOptionPane.showMessageDialog(rootPane, "Error removing book from transactions.");
	            }
	        	
	        }
	        catch(SQLException e) {
	        	e.printStackTrace();
	        }
		}
		
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/*This method will record the returned books into the Returned table*/
	public void recordReturnedBook(String transactionId) {
		try {
			// Establish a connection
	        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/BooksDB", "root", "ranielle25");

	        // Fetch the details of the returned book
	        String selectTransactionSql = "SELECT * FROM Transactions WHERE transaction_id = ?";
	        
	        try(PreparedStatement stmtSelectTransactionSql = conn.prepareStatement(selectTransactionSql)){	        	
	        	stmtSelectTransactionSql.setString(1, transactionId);
	            ResultSet rs = stmtSelectTransactionSql.executeQuery();
	            
	            if(rs.next()) {
	            	// Insert the details into the Returned table
	            	
	            	//WORK ON DIS
	            	String insertReturnedSql = "INSERT INTO Returned (transaction_id, book_num, title, accession, status, due_date, date_returned, borrower, id)"
	            			+ " VALUES (?, ?, ?, ?, ?, ?, CURRENT_DATE(), ?, ?)";
	            	
	            	try(PreparedStatement insertReturnedStmt = conn.prepareStatement(insertReturnedSql)){
	            		insertReturnedStmt.setString(1, rs.getString("transaction_id"));
	                    insertReturnedStmt.setString(2, rs.getString("booNum"));
	                    insertReturnedStmt.setString(3, rs.getString("Title"));
	                    insertReturnedStmt.setInt(4, rs.getInt("AccessionNum"));
	                    insertReturnedStmt.setString(5, rs.getString("BookStatus"));
	                    insertReturnedStmt.setString(6, rs.getString("return_date"));	                    
	                    insertReturnedStmt.setString(7, rs.getString("Borrower"));
	                    insertReturnedStmt.setString(8, rs.getString("user_id"));
	                    
	                    //executeeee
	                    int rowsAffected = insertReturnedStmt.executeUpdate();
	                    
	                    if (rowsAffected > 0) {
	                        JOptionPane.showMessageDialog(rootPane, "Returned Book was recorded successfully.");
	                    } else {
	                        JOptionPane.showMessageDialog(rootPane, "Error recording book as returned.");
	                    }
	            		
	            	}
	            	catch(SQLException e) {
	            		e.printStackTrace();
	            	}
	            }              
	        }
	        catch(SQLException e) {
	        	e.printStackTrace();
	        }
		}
		catch(Exception e) {
			e.printStackTrace();
		}
			
	}
	
}
