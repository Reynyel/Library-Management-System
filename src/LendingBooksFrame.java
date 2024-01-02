import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import color.AlternateColorRender;
import tablemodel.NonEditTableModel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JRadioButton;
import javax.swing.ImageIcon;

public class LendingBooksFrame extends JPanel {

	private JPanel contentPane;
	private JPanel panel;
	private JTextField txtBookNum;
	private JTextField txtTitle;
	private JTable tblTransac;
	private JTextField txtBorrID;
	private ButtonGroup radioGroup;
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
		setPreferredSize(new Dimension(1256, 686));
	    setLayout(null);
		setBounds(100, 100, 1687, 743);
		
		ButtonGroup radioGroup = new ButtonGroup();
		
		panel = new JPanel();
		panel.setBackground(new Color(0, 153, 255));
        panel.setBounds(0, 0, 1256, 686);
        add(panel);
        panel.setLayout(null);
		
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
		tblTransac.setBounds(0, 20, 89, 144);
		
		AlternateColorRender alternate = new AlternateColorRender();
		tblTransac.setDefaultRenderer(Object.class, alternate);
		
		JLabel lblPenalty = new JLabel("Penalty Fee");
		lblPenalty.setForeground(new Color(255, 255, 255));
		lblPenalty.setBounds(386, 186, 80, 20);
		panel.add(lblPenalty);
		lblPenalty.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JRadioButton radioReturned = new JRadioButton("Returned Books");
		radioReturned.setForeground(new Color(255, 255, 255));
		radioReturned.setBackground(new Color(0, 0, 51));
		radioReturned.setFont(new Font("Verdana", Font.PLAIN, 13));
		radioReturned.setBounds(1087, 302, 147, 23);
		panel.add(radioReturned);
		radioReturned.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayReturnedBooks();
			}
		});
		
		radioGroup.add(radioReturned);
		
		JLabel lblReturnDate = new JLabel("Return Date");
		lblReturnDate.setForeground(new Color(255, 255, 255));
		lblReturnDate.setBounds(32, 189, 80, 20);
		panel.add(lblReturnDate);
		lblReturnDate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		txtTitle = new JTextField();
		txtTitle.setBounds(147, 68, 705, 27);
		panel.add(txtTitle);
		txtTitle.setEditable(false);
		txtTitle.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtTitle.setColumns(10);
		
		txtAccession = new JTextField();
		txtAccession.setBounds(147, 110, 155, 27);
		panel.add(txtAccession);
		txtAccession.setEditable(false);
		txtAccession.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtAccession.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Book Title");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(32, 67, 70, 28);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		txtBookNum = new JTextField();
		txtBookNum.setBounds(144, 29, 708, 27);
		panel.add(txtBookNum);
		txtBookNum.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtBookNum.setColumns(10);
		
		JLabel lblBorrowedDate = new JLabel("Lending Date");
		lblBorrowedDate.setForeground(new Color(255, 255, 255));
		lblBorrowedDate.setBounds(32, 151, 107, 20);
		panel.add(lblBorrowedDate);
		lblBorrowedDate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lblBookNumber = new JLabel("Book Number");
		lblBookNumber.setForeground(new Color(255, 255, 255));
		lblBookNumber.setBounds(32, 28, 113, 28);
		panel.add(lblBookNumber);
		lblBookNumber.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		
		txtBorrID = new JTextField();
		txtBorrID.setBounds(140, 295, 705, 30);
		panel.add(txtBorrID);
		txtBorrID.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtBorrID.setColumns(10);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBounds(724, 175, 128, 40);
		panel.add(btnUpdate);
		btnUpdate.setForeground(new Color(255, 255, 255));
		btnUpdate.setBackground(new Color(0, 153, 0));
		btnUpdate.setBorderPainted(false);
		btnUpdate.setFont(new Font("Segoe UI Light", Font.BOLD, 15));
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update();
			}
		});
		JButton btnSearchBook = new JButton("Search Book");
		btnSearchBook.setBounds(724, 131, 128, 40);
		panel.add(btnSearchBook);
		btnSearchBook.setForeground(new Color(255, 255, 255));
		btnSearchBook.setBackground(new Color(220, 20, 60));
		btnSearchBook.setBorderPainted(false);
		btnSearchBook.setFont(new Font("Segoe UI Light", Font.BOLD, 15));
		btnSearchBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				search();
			}
		});
		
		JLabel lblAccession = new JLabel("Accession");
		lblAccession.setForeground(new Color(255, 255, 255));
		lblAccession.setBounds(32, 111, 80, 24);
		panel.add(lblAccession);
		lblAccession.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		txtBorrDate = new JTextField();
		txtBorrDate.setBounds(147, 148, 155, 27);
		panel.add(txtBorrDate);
		txtBorrDate.setEditable(false);
		txtBorrDate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtBorrDate.setColumns(10);
		panel.add(tblTransac);
		
		JScrollPane js = new JScrollPane(tblTransac);
		js.setVisible(true);
		js.setBounds(10, 332, 1236, 306); // Adjust the bounds to match the table
		panel.add(js);
		
		
		
		JLabel lblBorrowersName = new JLabel("Borrower's ID");
		lblBorrowersName.setForeground(new Color(255, 255, 255));
		lblBorrowersName.setBounds(32, 294, 113, 30);
		panel.add(lblBorrowersName);
		lblBorrowersName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JRadioButton radioBorrowed = new JRadioButton("Borrowed Books");
		radioBorrowed.setForeground(new Color(255, 255, 255));
		radioBorrowed.setBackground(new Color(0, 0, 51));
		radioBorrowed.setFont(new Font("Verdana", Font.PLAIN, 13));
		radioBorrowed.setBounds(938, 302, 147, 23);
		panel.add(radioBorrowed);
		radioBorrowed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayTransactionHistory();
			}
		});
		radioGroup.add(radioBorrowed);
		
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
		btnExport.setBounds(1096, 650, 150, 25);
		panel.add(btnExport);
		
		txtReturnDate = new JTextField();
		txtReturnDate.setBounds(147, 186, 155, 27);
		panel.add(txtReturnDate);
		txtReturnDate.setEditable(false);
		txtReturnDate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtReturnDate.setColumns(10);
		
		txtPenalty = new JTextField();
		txtPenalty.setBounds(489, 186, 155, 27);
		panel.add(txtPenalty);
		txtPenalty.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtPenalty.setEditable(false);
		txtPenalty.setColumns(10);
		
		JLabel lblDateReturned = new JLabel("Date Returned");
		lblDateReturned.setForeground(new Color(255, 255, 255));
		lblDateReturned.setBounds(386, 148, 107, 24);
		panel.add(lblDateReturned);
		lblDateReturned.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		txtDateReturned = new JTextField();
		txtDateReturned.setBounds(489, 147, 155, 27);
		panel.add(txtDateReturned);
		txtDateReturned.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtDateReturned.setEditable(false);
		txtDateReturned.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\LINDELL\\Projects\\Library-Management-System\\res\\Untitled design.png"));
		lblNewLabel_1.setBounds(0, -3, 1266, 686);
		panel.add(lblNewLabel_1);
		
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
	
public void export() {						
		
		// Create a format for the date in the file name
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	    // Get the current date and format it
	    String currentDate = dateFormat.format(new Date());

	    // Construct the base file name with the current date
	    String baseFileName = "C:\\Users\\LINDELL\\Desktop\\returned_books_export_" + currentDate + ".csv";

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
	            pst = conn.prepareStatement("SELECT * From Returned");
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
    
    public void updatePenaltyEmployee(String transactionId) {
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
                JOptionPane.showMessageDialog(getRootPane(), "Please select a book from the table for return.");
                return;
            }


            String updatePenaltyFeeSql = "UPDATE Returned SET penalty_fee = 0.0 WHERE transaction_id = ?";

            try(PreparedStatement updatePenaltyFeeStmt = conn.prepareStatement(updatePenaltyFeeSql)){
            	// Set parameters for updatePenaltyFeeStmt
                updatePenaltyFeeStmt.setString(1, transactionId);

                int rowsPenaltyAffected = updatePenaltyFeeStmt.executeUpdate();

                if(rowsPenaltyAffected > 0) {
                	JOptionPane.showMessageDialog(getRootPane(), "PEEEEEEEEEEE");
                }
                else {
                	JOptionPane.showMessageDialog(getRootPane(), "Error with updatePenalty() method");
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
    

    public void updatePenaltyStudent(String transactionId) {
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
                JOptionPane.showMessageDialog(getRootPane(), "Please select a book from the table for return.");
                return;
            }


            String updatePenaltyFeeSql = "UPDATE Returned SET penalty_fee = ? WHERE transaction_id = ?";
            
            double fee = calculateFee();
                     
            try(PreparedStatement updatePenaltyFeeStmt = conn.prepareStatement(updatePenaltyFeeSql)){
            	// Set parameters for updatePenaltyFeeStmt
                updatePenaltyFeeStmt.setDouble(1, fee);
                updatePenaltyFeeStmt.setString(2, transactionId);
                
                int rowsPenaltyAffected = updatePenaltyFeeStmt.executeUpdate();
                System.out.println("pelase fucking wodsdrk");

                if(rowsPenaltyAffected > 0) {
                	JOptionPane.showMessageDialog(getRootPane(), "PEEEEEEEEEEE");
                }
                else {
                	JOptionPane.showMessageDialog(getRootPane(), "Error with updatePenalty() method");
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
    
    
    public double calculateFee() {
        double penaltyFee = 0.0;

        // Get the selected row from the table
        int selectedRow = tblTransac.getSelectedRow();

        // Check if a row is selected
        if (selectedRow != -1) {
            // Assuming the return date is in the seventh column (index 6)
            Object returnDateObj = tblTransac.getValueAt(selectedRow, 6);

            if (returnDateObj != null) {
                String returnDateStr = returnDateObj.toString();

                try {
                    // Parse return date to a Date object
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date returnDate = dateFormat.parse(returnDateStr);

                    // Get the current date
                    Date currentDate = new Date();

//                    Set a custom date, 11 days from now
//                    
//                    CHANGE THIS TO BACK TO CURRENT TIME BEFORE PRODUCTION
//                    SUBTRACTS BY 2
                    LocalDate customDate = LocalDate.now().plusDays(4);
                    Date customDateAsDate = Date.from(customDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

                    // Calculate the penalty fee based on the number of overdue days
                    long overdueDays = TimeUnit.DAYS.convert(customDateAsDate.getTime() - returnDate.getTime(), TimeUnit.MILLISECONDS);
                    penaltyFee = Math.max(0, overdueDays * 5.0);

                    //DEBUUGER (DELETE THIS)
                    System.out.println("Penalty Fee calculated successfully: " + penaltyFee);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Return date is null for the selected row.");
            }
        } else {
            System.out.println("No row selected in tblTransac.");
        }

        return penaltyFee;
    }

    
    //Get user type from both Employees and Students table
    private String getUserType(String transactionId) {
    	String userType = "";
    	
		String userTypeSql = "SELECT user_type FROM Transactions WHERE transaction_id = ?";
		try(PreparedStatement userTypeStmt = conn.prepareStatement(userTypeSql)) {
			userTypeStmt.setString(1, transactionId);

			
			ResultSet userTypeResult = userTypeStmt.executeQuery();
			
			if(userTypeResult.next()) {
				userType = userTypeResult.getString("user_type");
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return userType;
    	
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
                JOptionPane.showMessageDialog(getRootPane(), "Please select a book from the table for return.");
                return;
            }

            // Get the transaction ID from the selected row
            String transacId = tblTransac.getValueAt(selectedRow, 0).toString();
            
            String userType = getUserType(transacId);

            
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
                    JOptionPane.showMessageDialog(getRootPane(), "Book returned successfully.");
                } else {
                    JOptionPane.showMessageDialog(getRootPane(), "Error updating book and transaction status with penalty fee.");
                }
                
                
            } catch (SQLException e) {
                e.printStackTrace();
            }

            // Record the returned book in Returned table
            recordReturnedBook(transacId);
            // Remove the borrowed book from Transactions table
            removeBorrowedBook(transacId);
            
            if ("Student".equals(userType)) {
            	updatePenaltyStudent(transacId);            	
            }
            
            else if("Faculty".equals(userType) || "Staff".equals(userType)) {
            	//to call penalty fee
            	updatePenaltyEmployee(transacId);      	
            }
            
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
	                
	                tblTransac.setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
		    		    @Override
		    		    public Component getTableCellRendererComponent(JTable table,
		    		            Object value, boolean isSelected, boolean hasFocus, int row, int col) {

		    		        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);

		    		        String status = (String)table.getModel().getValueAt(row, 9);
		    		        if ("Student".equals(status)) {
		    		        	setBackground(table.getBackground());
		    		            setForeground(table.getForeground());
		    		            
		    		            // Assuming return date is in the 6th column (index 5)
			    		        String returnDateStr = (String) table.getModel().getValueAt(row, 6);

			    		        // Convert return date string to LocalDate
			    		        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			    		        LocalDate returnDate = LocalDate.parse(returnDateStr, formatter);

			    		        // Calculate the days between today and the return date
			    		        LocalDate today = LocalDate.now();
			    		        long daysBetween = java.time.temporal.ChronoUnit.DAYS.between(today, returnDate);
			    		        
			    		        System.out.println(daysBetween);
			    		        
			    		        if (daysBetween == 2) {
			    		            setBackground(Color.RED);
			    		            setForeground(Color.WHITE);
			    		        } else {
			    		            setBackground(table.getBackground());
			    		            setForeground(table.getForeground());
			    		        }
		    		        } else {
		    		        	setBackground(Color.BLUE);
		    		            setForeground(Color.WHITE);
		    		            
		    		        }    
		    		        
		    		     
		    		        return this;
		    		    }   
		    		});
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
	                
	                tblTransac.setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
		    		    @Override
		    		    public Component getTableCellRendererComponent(JTable table,
		    		            Object value, boolean isSelected, boolean hasFocus, int row, int col) {

		    		        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);

	    		        	setBackground(table.getBackground());
	    		            setForeground(table.getForeground());

     
		    		        return this;
		    		    }   
		    		});
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
	                JOptionPane.showMessageDialog(getRootPane(), "Book removed from transactions successfully.");
	            } else {
	                JOptionPane.showMessageDialog(getRootPane(), "Error removing book from transactions.");
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
	                        JOptionPane.showMessageDialog(getRootPane(), "Returned Book was recorded successfully.");
	                    } else {
	                        JOptionPane.showMessageDialog(getRootPane(), "Error recording book as returned.");
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
