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
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.awt.event.ActionEvent;

public class SearchBooks extends JFrame {

	private JPanel contentPane;
	private JTable tblBooks;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 952, 666);
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
				"Book Num", "Title", "Author", "ISBN", "Publisher", "Language", "Subject", "Quantity", "Dewey Decimal", "Accession Number", "Data and Time", "New column"
			}
		));
		tblBooks.setBounds(108, 300, 750, 316);
		contentPane.add(tblBooks);
		
		JScrollPane js = new JScrollPane(tblBooks);
		js.setVisible(true);
		js.setBounds(108, 300, 750, 316); // Adjust the bounds to match the table
		contentPane.add(js);
		
		JButton btnShowData = new JButton("Show Data");
		btnShowData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
										
						String sql = "SELECT * FROM Books";
						ResultSet rs = stmt.executeQuery(sql);
						
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
							
							//array to store data into jtable
							String tbData[] = {bookNum, title, author, isbn, publisher,
									language, subject, quantity, dewey, accession};
							
							DefaultTableModel tblModel = (DefaultTableModel)tblBooks.getModel();
							
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
			
		});
		btnShowData.setBounds(67, 126, 148, 57);
		contentPane.add(btnShowData);
	}
	
	public void update() {
		
	}
}
