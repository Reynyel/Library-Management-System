import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
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



public class RegisterBooksFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtTitle;
	private JTextField txtAuthor;
	private JTextField txtISBN;
	private JTextField txtPublisher;
	private JTextField txtQuantity;
	private JComboBox comboBoxSubject, languageComboBox;

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
	 */
	public RegisterBooksFrame() {
		setTitle("Register Books");
		setBackground(new Color(255, 255, 255));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 953, 666);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Book Title");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(10, 82, 80, 14);
		contentPane.add(lblNewLabel);
		
		txtTitle = new JTextField();
		txtTitle.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtTitle.setBounds(92, 81, 629, 20);
		contentPane.add(txtTitle);
		txtTitle.setColumns(10);
		
		JLabel lblAuthors = new JLabel("Author(s)");
		lblAuthors.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAuthors.setBounds(10, 123, 80, 14);
		contentPane.add(lblAuthors);
		
		txtAuthor = new JTextField();
		txtAuthor.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtAuthor.setColumns(10);
		txtAuthor.setBounds(92, 122, 629, 20);
		contentPane.add(txtAuthor);
		
		JLabel lblIsbn = new JLabel("ISBN");
		lblIsbn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblIsbn.setBounds(10, 166, 80, 14);
		contentPane.add(lblIsbn);
		
		txtISBN = new JTextField();
		txtISBN.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtISBN.setColumns(10);
		txtISBN.setBounds(92, 163, 629, 20);
		contentPane.add(txtISBN);
		
		JLabel lblPublisher = new JLabel("Publisher");
		lblPublisher.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPublisher.setBounds(10, 202, 80, 14);
		contentPane.add(lblPublisher);
		
		txtPublisher = new JTextField();
		txtPublisher.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtPublisher.setColumns(10);
		txtPublisher.setBounds(92, 201, 629, 20);
		contentPane.add(txtPublisher);
		
		JLabel lblLanguage = new JLabel("Language");
		lblLanguage.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblLanguage.setBounds(10, 291, 80, 14);
		contentPane.add(lblLanguage);
		
		JLabel lblSubject = new JLabel("Subject");
		lblSubject.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSubject.setBounds(10, 333, 80, 14);
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
		comboBoxSubject.setBounds(92, 331, 186, 22);
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
		btnRegister.setBounds(628, 354, 93, 33);
		contentPane.add(btnRegister);
		
		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblQuantity.setBounds(10, 245, 80, 14);
		contentPane.add(lblQuantity);
		
		txtQuantity = new JTextField();
		txtQuantity.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtQuantity.setColumns(10);
		txtQuantity.setBounds(92, 244, 97, 20);
		contentPane.add(txtQuantity);
		
		languageComboBox = new JComboBox();
		String[] langArr = {"English", "Filipino"};
		for (String s : langArr) {
			languageComboBox.addItem(s);
		}
		languageComboBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
		languageComboBox.setBackground(Color.WHITE);
		languageComboBox.setBounds(92, 287, 115, 22);
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
							
							//Build query
							String sql = "INSERT INTO Books (Title, Author, ISBN, Publisher, Language, Subject, Quantity, Book_Num, Dewey_Decimal, Accession_Num, book_status)" +
									"VALUES ('" + title + "', '" + author + "', '" + isbn + "', '" + publisher + "', '" + language + "', '" + subject + "', '" + quantity + "', '" + usedBookNum + "', '" + deweyDecimal + "', '" + (accessionNum + i) + "', '" + "Available" + "')";
							
							//Execute query
							stmt.executeUpdate(sql);
						
						}
						
					else {					
						//Build query
						String sql = "INSERT INTO Books (Title, Author, ISBN, Publisher, Language, Subject, Quantity, Book_Num, Dewey_Decimal, Accession_Num, book_status)" +
								"VALUES ('" + title + "', '" + author + "', '" + isbn + "', '" + publisher + "', '" + language + "', '" + subject + "', '" + quantity + "', '" + bookNum + "', '" + deweyDecimal + "', '" + (accessionNum + i) + "', '" + "Available" + "')";
						
						//Execute query
						stmt.executeUpdate(sql);
					}
				
				}
				
				JOptionPane.showMessageDialog(rootPane, "Book Registered");
				
				
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
