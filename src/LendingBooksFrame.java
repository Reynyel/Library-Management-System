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
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
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

import GradientBackground.gradientBackground;
import color.AlternateColorRender;
import color.BlockedColorRender;
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
	private JComboBox comboBoxStatus;
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
		setPreferredSize(new Dimension(1256, 754));
	    setLayout(null);
		setBounds(100, 100, 1687, 743);
		
		ButtonGroup radioGroup = new ButtonGroup();
		
		panel = new gradientBackground();
		panel.setBackground(new Color(0, 153, 255));
        panel.setBounds(0, 0, 1255, 756);
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
						String userType = (String) tblTransac.getValueAt(selectedRow, 9);
					
						//Populate fields
						txtBorrID.setText(userId);
						txtTitle.setText(title);
						txtBookNum.setText(bookNum);
						txtAccession.setText(acc); //FIX THIS						
						txtBorrDate.setText(transacDate);
						txtReturnDate.setText(returnDate);
						if("Borrowed".equals(status)) {
							String good = "Good";
							comboBoxStatus.setSelectedItem(good);
						}
						else {
							comboBoxStatus.setSelectedItem(status);							
						}
						//if Student display penalty fee, if there is a fee to be paid. If staff, only display 0
						
						// Display penalty fee based on user type
		                if ("Student".equals(userType)) {
		                    // Display penalty fee for students
		                    txtPenalty.setText(String.valueOf(calculateFee()));
		                } else {
		                    // Display 0 for staff
		                    txtPenalty.setText("0.0");
		                }
						
					}
				}
			}
		});		
		tblTransac.setBounds(0, 20, 89, 144);
		
		AlternateColorRender alternate = new AlternateColorRender();
		tblTransac.setDefaultRenderer(Object.class, alternate);
		
		JLabel lblBookStatus = new JLabel("Book Status");
		lblBookStatus.setForeground(Color.WHITE);
		lblBookStatus.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblBookStatus.setBounds(398, 194, 107, 24);
		panel.add(lblBookStatus);
		
		comboBoxStatus = new JComboBox();
		comboBoxStatus.setModel(new DefaultComboBoxModel(new String[] {"Good", "Damaged", "Lost"}));
		comboBoxStatus.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBoxStatus.setBounds(501, 195, 155, 22);
		panel.add(comboBoxStatus);
		
		JLabel lblPenalty = new JLabel("Penalty Fee");
		lblPenalty.setForeground(new Color(255, 255, 255));
		lblPenalty.setBounds(398, 229, 80, 20);
		panel.add(lblPenalty);
		lblPenalty.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JRadioButton radioReturned = new JRadioButton("Returned Books");
		radioReturned.setForeground(new Color(255, 255, 255));
		radioReturned.setBackground(new Color(0, 0, 51));
		radioReturned.setFont(new Font("Verdana", Font.PLAIN, 13));
		radioReturned.setBounds(1099, 302, 147, 23);
		radioReturned.setOpaque(false);
        radioReturned.setContentAreaFilled(false);
        radioReturned.setBorderPainted(false);
		panel.add(radioReturned);
		radioReturned.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayReturnedBooks();
			}
		});
		
		radioGroup.add(radioReturned);
		
		JLabel lblReturnDate = new JLabel("Return Date");
		lblReturnDate.setForeground(new Color(255, 255, 255));
		lblReturnDate.setBounds(32, 270, 80, 20);
		panel.add(lblReturnDate);
		lblReturnDate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		txtTitle = new JTextField();
		txtTitle.setEditable(false);
		txtTitle.setBounds(147, 153, 705, 27);
		panel.add(txtTitle);
		txtTitle.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtTitle.setColumns(10);
		
		txtAccession = new JTextField();
		txtAccession.setBounds(147, 191, 155, 27);
		panel.add(txtAccession);
		txtAccession.setEditable(false);
		txtAccession.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtAccession.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Book Title");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(32, 152, 70, 28);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		txtBookNum = new JTextField();
		txtBookNum.setEditable(false);
		txtBookNum.setBounds(144, 114, 708, 27);
		panel.add(txtBookNum);
		txtBookNum.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtBookNum.setColumns(10);
		
		JLabel lblBorrowedDate = new JLabel("Lending Date");
		lblBorrowedDate.setForeground(new Color(255, 255, 255));
		lblBorrowedDate.setBounds(32, 232, 107, 20);
		panel.add(lblBorrowedDate);
		lblBorrowedDate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lblBookNumber = new JLabel("Book Number");
		lblBookNumber.setForeground(new Color(255, 255, 255));
		lblBookNumber.setBounds(32, 113, 113, 28);
		panel.add(lblBookNumber);
		lblBookNumber.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		
		txtBorrID = new JTextField();
		txtBorrID.setBounds(140, 41, 705, 30);
		panel.add(txtBorrID);
		txtBorrID.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtBorrID.setColumns(10);
		
		JButton btnUpdate = new JButton("Record Transaction");
		btnUpdate.setBounds(666, 259, 170, 40);
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
		JButton btnSearchBook = new JButton("Search ");
		btnSearchBook.setBounds(858, 34, 128, 40);
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
		lblAccession.setBounds(32, 192, 80, 24);
		panel.add(lblAccession);
		lblAccession.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		txtBorrDate = new JTextField();
		txtBorrDate.setBounds(147, 229, 155, 27);
		panel.add(txtBorrDate);
		txtBorrDate.setEditable(false);
		txtBorrDate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtBorrDate.setColumns(10);
		panel.add(tblTransac);
		
		JScrollPane js = new JScrollPane(tblTransac);
		js.setVisible(true);
		js.setBounds(10, 332, 1236, 315); // Adjust the bounds to match the table
		panel.add(js);
		
		
		
		JLabel lblBorrowersName = new JLabel("Borrower's ID");
		lblBorrowersName.setForeground(new Color(255, 255, 255));
		lblBorrowersName.setBounds(32, 40, 113, 30);
		panel.add(lblBorrowersName);
		lblBorrowersName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JRadioButton radioBorrowed = new JRadioButton("Borrowed Books");
		radioBorrowed.setForeground(new Color(255, 255, 255));
		radioBorrowed.setBackground(new Color(0, 0, 51));
		radioBorrowed.setFont(new Font("Verdana", Font.PLAIN, 13));
		radioBorrowed.setBounds(950, 302, 147, 23);
		radioBorrowed.setOpaque(false);
        radioBorrowed.setContentAreaFilled(false);
        radioBorrowed.setBorderPainted(false);
        radioBorrowed.setSelected(true);
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
		btnExport.setBounds(1096, 658, 150, 25);
		panel.add(btnExport);
		
		txtReturnDate = new JTextField();
		txtReturnDate.setEnabled(true);
		txtReturnDate.setEditable(true);
		txtReturnDate.setBounds(147, 267, 155, 27);
		panel.add(txtReturnDate);
		txtReturnDate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtReturnDate.setColumns(10);
		
		txtPenalty = new JTextField();
		txtPenalty.setBounds(501, 229, 155, 27);
		panel.add(txtPenalty);
		txtPenalty.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtPenalty.setEditable(false);
		txtPenalty.setColumns(10);
		
		JButton btnUpdateReturnDate = new JButton("Update Return Date");
		btnUpdateReturnDate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateReturnDate();
			}
		});
		btnUpdateReturnDate.setForeground(Color.WHITE);
		btnUpdateReturnDate.setFont(new Font("Segoe UI Light", Font.BOLD, 15));
		btnUpdateReturnDate.setBorderPainted(false);
		btnUpdateReturnDate.setBackground(new Color(0, 153, 0));
		btnUpdateReturnDate.setBounds(312, 266, 163, 27);
		panel.add(btnUpdateReturnDate);
		
		fetchAndDisplayData();
//		displayTransactionHistory();
	}
	Connection conn;
	PreparedStatement pst;
	ResultSet rs;
	private JTextField txtBorrDate;
	private JTextField txtReturnDate;
	private JTextField txtAccession;
	private JTextField txtPenalty;
		
	//UPDATE RETURN DATE
	private void updateReturnDate() {
		String returnDate = txtReturnDate.getText().toString();
		
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
							
			String sql = "UPDATE Transactions SET return_date = ? WHERE transaction_id= ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			int selectedRow = tblTransac.getSelectedRow();
			
			if(selectedRow != -1) {
				
				//get book_num value from row
				String transactionId = (String) tblTransac.getValueAt(selectedRow, 0);
				
				pstmt.setString(1, returnDate);
				pstmt.setString(2, transactionId);
				
				int rowsAffected = pstmt.executeUpdate();
				if(rowsAffected > 0) {
					JOptionPane.showMessageDialog(getRootPane(), "Updated succesfully");
					fetchAndDisplayData();
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
	public void export() {
	    // Create a format for the date in the file name
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	    // Get the current date and format it
	    String currentDate = dateFormat.format(new Date());

	    // Construct the base file name with the current date
	    String baseFileName = "C:\\Users\\LINDELL\\Desktop\\returned_export_" + currentDate + ".csv";

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
	        AcademicYear ya = AcademicYear.now( ZoneId.systemDefault( ));
			String formattedAcadYear = ya.format( FormatStyle.FULL);
	        fw.append("List of All Returned Books");
	        fw.append("\n");
	        fw.append("Academic Year: " + formattedAcadYear);
	        fw.append("\n");
	        fw.append("\n");
	        // Add headers to the CSV file
	        fw.append("Transaction ID");
	        fw.append(',');
	        fw.append(',');
	        fw.append("Book Number");
	        fw.append(',');
	        fw.append(',');
	        fw.append("Title");
	        fw.append(',');
	        fw.append(',');
	        fw.append("Accession");
	        fw.append(',');
	        fw.append(',');
	        fw.append("Status");
	        fw.append(',');
	        fw.append(',');
	        fw.append("Due Date");
	        fw.append(',');
	        fw.append(',');
	        fw.append("Date Returned");
	        fw.append(',');
	        fw.append(',');
	        fw.append("Borrower");
	        fw.append(',');
	        fw.append(',');
	        fw.append(',');
	        fw.append(',');
	        fw.append("ID");
	        fw.append(',');
	        fw.append(',');
	        fw.append(',');
	        fw.append("Penalty Fee");
	        fw.append('\n');

	        // Fetch data from the database
	        pst = conn.prepareStatement("SELECT * FROM Returned ");
	        rs = pst.executeQuery();

	        int totalBooks = 0;

	        while (rs.next()) {
	        	fw.append(rs.getString(1));  //the column index for ID
	            fw.append(',');
	            fw.append(',');
	            fw.append(rs.getString(2));  //the column index for Last
	            fw.append(',');
	            fw.append(',');
	            fw.append(rs.getString(3));  //the column index for First
	            fw.append(',');
	            fw.append(',');
	            fw.append(rs.getString(4));  //the column index for Middle
	            fw.append(',');
	            fw.append(',');
	            fw.append(rs.getString(5));  //the column index for Contact
	            fw.append(',');
	            fw.append(',');
	            fw.append(rs.getString(6));  //the column index for Email
	            fw.append(',');
	            fw.append(',');
	            fw.append(rs.getString(7));  //the column index for Email
	            fw.append(',');
	            fw.append(',');
	            fw.append(rs.getString(8));  //the column index for Email
	            fw.append(',');
	            fw.append(',');
	            fw.append(',');
	            fw.append(rs.getString(9));  //the column index for Email
	            fw.append(',');
	            fw.append(',');
	            fw.append(',');
	            fw.append(rs.getString(10));  //the column index for Email
	            fw.append('\n');

	            totalBooks++;
	        }

	        // Write the total number of books registered
	        fw.append('\n');
	        fw.append("Total Returned Books Recorded: " + totalBooks);
	        fw.append('\n');
	        String userType = MainMenuFrame.getUser();
	        
	        if ("Librarian".equalsIgnoreCase(userType)) {
	        	fw.append("Prepared by: " + "Librarian");
     		} else if ("Admin".equalsIgnoreCase(userType)) {
     			fw.append("Prepared by: " + "Admin");
     		}
	        
	        JOptionPane.showMessageDialog(getRootPane(), "Export success");
	        
	        // Flush and close the FileWriter
	        fw.flush();
	        fw.close();
	    } catch (IOException | SQLException e) {
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
            String selectTransactionsSql = "SELECT * FROM Transactions";
            
            
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

//            int selectStatus = tblTransac.getSelectedRow(4);
            
            // Check if a row is selected
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(getRootPane(), "Please select a book from the table for return.");
                return;
            }
            
            double fee = calculateFee();
            
            String updatePenaltyFeeSql = "UPDATE Returned SET penalty_fee = ? WHERE transaction_id = ?";
            
                     
            try(PreparedStatement updatePenaltyFeeStmt = conn.prepareStatement(updatePenaltyFeeSql)){
            	// Set parameters for updatePenaltyFeeStmt
                updatePenaltyFeeStmt.setDouble(1, fee);
                updatePenaltyFeeStmt.setString(2, transactionId);
                
                int rowsPenaltyAffected = updatePenaltyFeeStmt.executeUpdate();


            }
            catch(SQLException e) {
            	e.printStackTrace();
            }
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    
    private void addToBlocklist(String transactionId) {
        try {

            String userType = getUserType(transactionId);

            // Check if the user is a student
            if ("Student".equals(userType)) {
                String userId = getID(transactionId);
                String name = getName(transactionId);
                // Check if the user is not already on the blocklist
                if (!isUserBlocked(userId, name)) {
                    // Insert the user into the blocklist
                    String insertBlockedUserSql = "INSERT INTO Blocked (user_id, borrower_name) VALUES (?, ?)";

                    try (PreparedStatement pstmt = conn.prepareStatement(insertBlockedUserSql)) {
                        pstmt.setString(1, userId);
                        pstmt.setString(2, name);

                        int rowsAffected = pstmt.executeUpdate();

                        if (rowsAffected > 0) {
                            System.out.println("User added to blocklist successfully.");
                        } else {
                            System.out.println("Failed to add user to blocklist.");
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                        // Handle the SQL exception
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception
        }
    }
    
    private void removeFromBlocklistPenalty(String userId, String borrowerName) {
        try {
            // ... existing code to establish database connection ...

            // Check if the user is on the blocklist
            if (isUserBlocked(userId, borrowerName)) {
                // If yes, remove the user from the blocklist
                String deleteBlockedUserSql = "DELETE FROM Blocked WHERE user_id = ? AND borrower_name = ?";

                try (PreparedStatement pstmt = conn.prepareStatement(deleteBlockedUserSql)) {
                    pstmt.setString(1, userId);
                    pstmt.setString(2, borrowerName);

                    int rowsAffected = pstmt.executeUpdate();

                    if (rowsAffected > 0) {
                        System.out.println("User removed from blocklist successfully.");
                    } else {
                        System.out.println("Failed to remove user from blocklist.");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    // Handle the SQL exception
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception
        }
    }

    
    public double calculateFee() {
        double penaltyFee = 0.0;

        int selectedRow = tblTransac.getSelectedRow();

        if (selectedRow != -1) {
            Object returnDateObj = tblTransac.getValueAt(selectedRow, 6);

            if (returnDateObj != null) {
                String returnDateStr = returnDateObj.toString();

                try {
                    // Parse return date to a Date object
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date returnDate = dateFormat.parse(returnDateStr);

                    // Get the current date
                    LocalDate currentDate = LocalDate.now();
                    LocalDate returnLocalDate = returnDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

                    // Calculate the penalty fee only if the book is returned late
                    if (returnLocalDate.isBefore(currentDate)) {
                        long overdueDays = 0;
                        LocalDate tempDate = returnLocalDate.plusDays(1); // Start from the day after the return date

                        while (tempDate.isBefore(currentDate) || tempDate.isEqual(currentDate)) {
                            DayOfWeek dayOfWeek = tempDate.getDayOfWeek();

                            // Skip weekends (Saturday and Sunday)
                            if (dayOfWeek != DayOfWeek.SATURDAY && dayOfWeek != DayOfWeek.SUNDAY) {
                                overdueDays++;
                            }

                            tempDate = tempDate.plusDays(1);
                        }

                        penaltyFee = overdueDays * 10.0;

                    } else {
                        System.out.println("Book returned on time or early.");
                    }
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
    
    //Get names type from both Employees and Students table
    private String getName(String transactionId) {
    	String name = "";
    	
		String userTypeSql = "SELECT Borrower FROM Transactions WHERE transaction_id = ?";
		try(PreparedStatement userTypeStmt = conn.prepareStatement(userTypeSql)) {
			userTypeStmt.setString(1, transactionId);

			
			ResultSet userTypeResult = userTypeStmt.executeQuery();
			
			if(userTypeResult.next()) {
				name = userTypeResult.getString("Borrower");
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}	
		return name; 	
    }
    
    //Get id from both Employees and Students table
    private String getID(String transactionId) {
    	String id = "";
    	
		String userTypeSql = "SELECT user_id FROM Transactions WHERE transaction_id = ?";
		try(PreparedStatement userTypeStmt = conn.prepareStatement(userTypeSql)) {
			userTypeStmt.setString(1, transactionId);

			
			ResultSet userTypeResult = userTypeStmt.executeQuery();
			
			if(userTypeResult.next()) {
				id = userTypeResult.getString("user_id");
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}	
		return id; 	
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

            String status = comboBoxStatus.getSelectedItem().toString();
            String id = tblTransac.getValueAt(selectedRow, 8).toString();
        	String name = tblTransac.getValueAt(selectedRow, 7).toString();
        	String bookNum = tblTransac.getValueAt(selectedRow, 1).toString();
        	String acc = tblTransac.getValueAt(selectedRow, 3).toString();
        	int confirmResult = JOptionPane.showConfirmDialog(
                    getRootPane(),
                    "Are you sure you want to return this book?",
                    "Confirmation",
                    JOptionPane.YES_NO_OPTION
            );
        	
        	if (confirmResult == JOptionPane.YES_OPTION) {
        		// Use a switch statement to handle different statuses
                switch (status) {
                    case "Good":
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
                        
                        if ("Student".equals(userType)) {
                        	double penaltyFee = Double.parseDouble(txtPenalty.getText());

                            // Check if penalty fee is greater than 0
                            if (penaltyFee > 0) {
                                int confirmResult1 = JOptionPane.showConfirmDialog(
                                        getRootPane(),
                                        "This student has a penalty fee of: PHP" + penaltyFee + ". Is this paid already or not?",
                                        "Confirmation",
                                        JOptionPane.YES_NO_OPTION
                                );
                                
                                if (confirmResult1 == JOptionPane.YES_OPTION) {
                                    // User clicked "Yes"
                                    String orCode = JOptionPane.showInputDialog(getRootPane(), "Please enter the O.R. code:");
                                    
                                    // Check if the user entered a valid O.R. code
                                    if (orCode != null && !orCode.isEmpty()) {
                                    	
                                        // Record the O.R. code into the database table of paid users
                                        recordPaidUser(orCode, id, name);
                                        
                                        // Remove student from blocklist after payment confirmation
                                        removeFromBlocklistPenalty(id, name);
                                    } else {
                                        // Handle the case where the user canceled the input or entered an empty O.R. code
                                        JOptionPane.showMessageDialog(getRootPane(), "Invalid or empty O.R. code. Payment not recorded.");
                                    }
                                }
     
                                
                                if (confirmResult != JOptionPane.YES_OPTION) {
                                    // User chose not to proceed
                                    JOptionPane.showMessageDialog(getRootPane(), "Return canceled.\n" + "This user won't be able to borrow new titles\n" + "until they have settled their fee.");
                                    addToBlocklist(transacId);

                                    return;
                                }
                            } else {
                            	
                                // No penalty fee, proceed without showing the confirmation dialog
                            	
                            	/*Will check if the user is on the blocklist or not
                            	 * if the user is on the blocklist and the book has been replaced or fee paid
                            	 * unblock the user*/
                            	unblockUser(id, name);
                            	
                            }
                        }
                            
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
                            	resetFieldsAndSelection();
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
                        break;
                    case "Damaged":
                        // Handle "Damaged" case
                        JOptionPane.showMessageDialog(getRootPane(), "Damaged books must be replaced.");
                        recordBlockedUser(id, name);
                        JOptionPane.showMessageDialog(getRootPane(), "The user is currently blocked from borrowing new books.\n" + "until the damaged book has been replaced.");
                        // Update the book status to "Damaged" in the Books table
                        updateBookStatus(bookNum, "Damaged", acc);
                        updateTransactionStatus(bookNum, "Damaged", acc);
                        return;
                    case "Lost":
                        // Handle "Lost" case
                        JOptionPane.showMessageDialog(getRootPane(), "Lost books must be replaced.");
                        recordBlockedUser(id, name);
                        JOptionPane.showMessageDialog(getRootPane(), "The user is currently blocked from borrowing new books.\n" + "until the lost book has been replaced.");
                        updateBookStatus(bookNum, "Lost", acc);
                        updateTransactionStatus(bookNum, "Lost", acc);
                        return;
                    default:
                        // Handle unexpected status
                        JOptionPane.showMessageDialog(getRootPane(), "Invalid book status selected.");
                        return;
                        
                }
            } else {
                // User chose not to proceed
                JOptionPane.showMessageDialog(getRootPane(), "Return canceled.");
            }
        	
                                                                        
        } catch (Exception e) {
            e.printStackTrace();
        }
    	    	
    }
    // Method to record a blocked user
    private void unblockUser(String id, String name) {
    	// Check if the user exists on the blocklist
        if (isUserBlocked(id, name)) {
            // If yes, unblock the user
            String deleteBlockedUserSql = "DELETE FROM Blocked WHERE user_id = ? AND borrower_name = ?";

            try (PreparedStatement pstmt = conn.prepareStatement(deleteBlockedUserSql)) {
                pstmt.setString(1, id);
                pstmt.setString(2, name);

                // Execute the update
                int rowsAffected = pstmt.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("User unblocked successfully.");
                    JOptionPane.showMessageDialog(getRootPane(), "The user has been unblocked.");
                } else {
                    System.out.println("Failed to unblock user.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                // Handle the SQL exception
            }
        }
    }
    
    // Method to check if the user is on the blocklist
    private boolean isUserBlocked(String id, String name) {
        String selectBlockedUserSql = "SELECT * FROM Blocked WHERE user_id = ? AND borrower_name = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(selectBlockedUserSql)) {
            pstmt.setString(1, id);
            pstmt.setString(2, name);

            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next(); // Returns true if the user is on the blocklist
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Handle the SQL exception
        }
    }
       
    private void updateTransactionStatus(String bookId, String status, String acc) {
        String updateBookStatusSql = "UPDATE Transactions SET BookStatus = ? WHERE BooNum = ? AND AccessionNum = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(updateBookStatusSql)) {
            // Set parameters for the prepared statement
            pstmt.setString(1, status);
            pstmt.setString(2, bookId);
            pstmt.setString(3, acc);

            // Execute the update
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Book status updated successfully.");
            } else {
                System.out.println("Failed to update book status.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the SQL exception
        }
    }
    
    //THISSS WILLLL UPDATE BOOK STATUS TO EITHER DAMAGED OR LOST DEPENDING ON DA SITUATIONSSSZZZZ
    private void updateBookStatus(String bookId, String status, String acc) {
        String updateBookStatusSql = "UPDATE Books SET book_status = ? WHERE Book_Num = ? AND Accession_Num = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(updateBookStatusSql)) {
            // Set parameters for the prepared statement
            pstmt.setString(1, status);
            pstmt.setString(2, bookId);
            pstmt.setString(3, acc);

            // Execute the update
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Book status updated successfully.");
            } else {
                System.out.println("Failed to update book status.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the SQL exception
        }
    }
    
    // Method to record a blocked user
    private void recordBlockedUser(String id, String name) {
        // Check if the user is already on the block list
        if (!isUserBlocked(id, name)) {
            String insertBlockedUserSql = "INSERT INTO Blocked (user_id, borrower_name) VALUES (?, ?)";

            try (PreparedStatement pstmt = conn.prepareStatement(insertBlockedUserSql)) {
                String userId = id;

                // Set parameters for the prepared statement
                pstmt.setString(1, userId);
                pstmt.setString(2, name);

                // Execute the update
                int rowsAffected = pstmt.executeUpdate();

                if (rowsAffected > 0) {
                	JOptionPane.showMessageDialog(getRootPane(), "User has been blocked from borrowing new books.");
                } else {
                	JOptionPane.showMessageDialog(getRootPane(), "Failed to record blocked user.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                // Handle the SQL exception
            }
        } else {
            JOptionPane.showMessageDialog(getRootPane(), "User is already on the block list");
        }
    }

    
    // Method to record a paid user with the provided O.R. code
    private void recordPaidUser(String orCode, String id, String name) {
        String insertSql = "INSERT INTO Paid (or_code, borrower_name, user_id) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(insertSql)) {
            // Set parameters for the prepared statement
            pstmt.setString(1, orCode);
            pstmt.setString(2, id);
            pstmt.setString(3, id);

            // Execute the update
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Payment recorded successfully.");
            } else {
                System.out.println("Failed to record payment.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the SQL exception
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
	            int statusColumnIndex = 4;
	    		tblTransac.getColumnModel().getColumn(statusColumnIndex).setCellRenderer(new BlockedColorRender());
	            
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
	
	//reset text fields and radio button selection
	private void resetFieldsAndSelection() {
		txtBorrID.setText("");
        txtBookNum.setText("");
        txtTitle.setText("");
        txtAccession.setText("");
        txtBorrDate.setText("");
        txtReturnDate.setText("");
        txtPenalty.setText("");
        comboBoxStatus.setSelectedIndex(0);
	}
}