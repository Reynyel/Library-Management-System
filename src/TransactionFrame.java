import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
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
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TransactionFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtBookNum;
	private JTextField txtTitle;
	private JTextField txtAuthor;
	private JTable tblTransac;
	private JTextField txtBorrName;
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

	/**
	 * Create the frame.
	 */
	public TransactionFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1229, 917);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblBookNumber = new JLabel("Book Number");
		lblBookNumber.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblBookNumber.setBounds(47, 54, 113, 14);
		contentPane.add(lblBookNumber);
		
		txtBookNum = new JTextField();
		txtBookNum.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtBookNum.setColumns(10);
		txtBookNum.setBounds(150, 51, 608, 20);
		contentPane.add(txtBookNum);
		
		txtTitle = new JTextField();
		txtTitle.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtTitle.setColumns(10);
		txtTitle.setBounds(150, 93, 629, 20);
		contentPane.add(txtTitle);
		
		JLabel lblNewLabel = new JLabel("Book Title");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(47, 96, 80, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblAuthors = new JLabel("Author(s)");
		lblAuthors.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAuthors.setBounds(44, 141, 80, 14);
		contentPane.add(lblAuthors);
		
		txtAuthor = new JTextField();
		txtAuthor.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtAuthor.setColumns(10);
		txtAuthor.setBounds(150, 138, 629, 20);
		contentPane.add(txtAuthor);
		
		cbAccession = new JComboBox();
		cbAccession.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cbAccession.setBackground(Color.WHITE);
		cbAccession.setBounds(150, 183, 49, 22);
		contentPane.add(cbAccession);
		
		JLabel lblAccession = new JLabel("Accession");
		lblAccession.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAccession.setBounds(47, 187, 80, 14);
		contentPane.add(lblAccession);
		
		JLabel lblStatus = new JLabel("Status");
		lblStatus.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblStatus.setBounds(47, 232, 80, 14);
		contentPane.add(lblStatus);
		
		JComboBox cbStatus = new JComboBox();
		cbStatus.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cbStatus.setBackground(Color.WHITE);
		cbStatus.setBounds(150, 228, 89, 22);
		contentPane.add(cbStatus);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update();
			}
		});
		btnUpdate.setBounds(333, 442, 128, 40);
		contentPane.add(btnUpdate);
		
		tblTransac = new JTable();
		tblTransac.setBorder(new LineBorder(new Color(0, 0, 0)));
		tblTransac.setColumnSelectionAllowed(true);
		tblTransac.setCellSelectionEnabled(true);
		tblTransac.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null},
			},
			new String[] {
				"Transaction ID", "Book Num", "Title", "Accession", "Status", "Transaction Date", "Return Date", "Borrower"
			}
		));
		tblTransac.setBounds(126, 598, 632, -215);
		contentPane.add(tblTransac);
		
		JScrollPane js = new JScrollPane(tblTransac);
		js.setVisible(true);
		js.setBounds(42, 525, 1085, 342); // Adjust the bounds to match the table
		contentPane.add(js);
		
		JPanel panel = new JPanel();
		panel.setBounds(24, 279, 759, 135);
		contentPane.add(panel);
		panel.setLayout(null);
		
		txtBorrName = new JTextField();
		txtBorrName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtBorrName.setColumns(10);
		txtBorrName.setBounds(133, 11, 588, 20);
		panel.add(txtBorrName);
		
		JLabel lblBorrowersName = new JLabel("Borrower's Name");
		lblBorrowersName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblBorrowersName.setBounds(10, 12, 113, 14);
		panel.add(lblBorrowersName);
		
		JButton btnSearchBook = new JButton("Search Book");
		btnSearchBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				search();
			}
		});
		btnSearchBook.setBounds(492, 442, 128, 40);
		contentPane.add(btnSearchBook);
	}
	
	Connection conn;
	PreparedStatement pst;
	ResultSet rs;
	
	public void update() {
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
								
				String sql = "INSERT INTO Transactions (BooNum, Title, AccessionNum, Borrower, BookStatus, transaction_date) " +
	                     "SELECT Books.Book_Num, Books.Title, Books.AccessionNum, " +
	                     "CONCAT(Students.LastName, ', ', Students.FirstName, ' ', Students.MiddleName) AS Borrower, " +
	                     "'Borrowed' AS BookStatus, NOW() AS transaction_date " +
	                     "FROM Books, Students " +
	                     "WHERE Books.Book_Num = ? AND CONCAT(Students.LastName, ', ', Students.FirstName, ' ', Students.MiddleName) = ?";
				
				PreparedStatement pstmt = conn.prepareStatement(sql);
				
				while(rs.next()) {
					//add data until there is none
					String bookNum = rs.getString("Book_Num");
					String title = rs.getString("Title");
					String author = rs.getString("Author");
					String accession = String.valueOf(rs.getInt("Accession_Num"));
					
					String borrowersName = txtBorrName.getText();
					
					//array to store data into jtable
					String tbData[] = {bookNum, title, author, accession};
					
					DefaultTableModel tblModel = (DefaultTableModel)tblTransac.getModel();
					
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

		        DefaultTableModel tblModel = (DefaultTableModel) tblTransac.getModel();

		        // Clear existing rows in the table
		        tblModel.setRowCount(0);
		        
		        if(!rs.isBeforeFirst()) {
					JOptionPane.showMessageDialog(this, "Sorry, book not found!");				
					
					txtTitle.setText("");
					txtAuthor.setText("");
					cbAccession.removeAllItems();
				}
		        
		        else {
		        	DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();
		        	while(rs.next()) {
		        				   		        		
						//add data until there is none
						String bookNum = rs.getString("Book_Num");
						String title = rs.getString("Title");
						String author = rs.getString("Author");;
						String accession = String.valueOf(rs.getInt("Accession_Num"));
						
						comboBoxModel.addElement(accession);
						
						txtTitle.setText(title);
						txtAuthor.setText(author);				
						cbAccession.setModel(comboBoxModel);
						
																
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
}
