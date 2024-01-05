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
import javax.swing.ButtonGroup;
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

import GradientBackground.gradientBackground;
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
import javax.swing.JRadioButton;


public class RegisterBooksFrame extends JPanel {

	private JPanel panel;
	private JTextField txtTitle;
	private JTextField txtAuthor;
	private JTextField txtISBN;
	private JTextField txtPublisher;
	private JTextField txtQuantity;
	private JButton btnUpdate;
	private JComboBox comboBoxSubject, languageComboBox, cbSort;
	private JTable table;
	
	Connection conn;
	PreparedStatement pst;
	ResultSet rs;

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
		setPreferredSize(new Dimension(1255, 756));
	    setLayout(null);
					
		Object[][] data = {null, null, null, null, null, null, null, null, null, null, null};
		Object[] columnNames = {"Book Number", "Title", "Author", "ISBN", "Publisher", "Language", "Subject", "Dewey", "Accession", "Status", "Date Registered"};
		NonEditTableModel model;
		Set<Integer> editableColumns = new HashSet<>();
		
        panel = new gradientBackground();
        panel.setBackground(new Color(0, 153, 255));
        panel.setBounds(0, 0, 1255, 756);
        add(panel);
        panel.setLayout(null);
        
        ButtonGroup radioGroup = new ButtonGroup();
        
        JRadioButton radioSearch = new JRadioButton(" Search Books");
        radioSearch.setFont(new Font("Tahoma", Font.PLAIN, 15));
        
        radioSearch.setForeground(Color.WHITE);
        radioSearch.setBackground(new Color(40, 85, 238));
        radioSearch.setBounds(840, 434, 142, 23);
        radioSearch.setOpaque(false);
        radioSearch.setContentAreaFilled(false);
        radioSearch.setBorderPainted(false);
        panel.add(radioSearch);
        
        JRadioButton radioRegister = new JRadioButton("Register Books");
        radioRegister.setFont(new Font("Tahoma", Font.PLAIN, 15));
        
        radioRegister.setForeground(new Color(255, 255, 255));
        radioRegister.setBackground(new Color(40, 85, 238));
        radioRegister.setBounds(78, 403, 142, 23);
        radioRegister.setOpaque(false);
        radioRegister.setContentAreaFilled(false);
        radioRegister.setBorderPainted(false);
        panel.add(radioRegister);
        
        radioGroup.add(radioRegister);
        radioGroup.add(radioSearch);
    
        JLabel lblNewLabel = new JLabel("Book Title");
        lblNewLabel.setBackground(new Color(89, 89, 89));
        lblNewLabel.setForeground(new Color(255, 255, 255));
        lblNewLabel.setBounds(78, 434, 72, 30);
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        panel.add(lblNewLabel);
        
        JLabel lblAuthors = new JLabel("Author(s)");
        lblAuthors.setForeground(new Color(255, 255, 255));
        lblAuthors.setBounds(388, 434, 64, 30);
        lblAuthors.setFont(new Font("Tahoma", Font.PLAIN, 15));
        panel.add(lblAuthors);
        
        txtAuthor = new JTextField();
        txtAuthor.setBounds(388, 465, 284, 30);
        txtAuthor.setFont(new Font("Tahoma", Font.PLAIN, 15));
        txtAuthor.setColumns(10);
        panel.add(txtAuthor);
        
        JLabel lblIsbn = new JLabel("ISBN");
        lblIsbn.setHorizontalAlignment(SwingConstants.LEFT);
        lblIsbn.setForeground(new Color(255, 255, 255));
        lblIsbn.setBounds(388, 503, 72, 30);
        lblIsbn.setFont(new Font("Tahoma", Font.PLAIN, 15));
        panel.add(lblIsbn);
        
        txtISBN = new JTextField();
        txtISBN.setBounds(388, 534, 284, 30);
        txtISBN.setFont(new Font("Tahoma", Font.PLAIN, 15));
        txtISBN.setColumns(10);
        panel.add(txtISBN);
        
        JLabel lblPublisher = new JLabel("Publisher");
        lblPublisher.setForeground(new Color(255, 255, 255));
        lblPublisher.setBounds(78, 535, 72, 30);
        lblPublisher.setFont(new Font("Tahoma", Font.PLAIN, 15));
        panel.add(lblPublisher);
        
        txtPublisher = new JTextField();
        txtPublisher.setBounds(78, 534, 300, 30);
        txtPublisher.setFont(new Font("Tahoma", Font.PLAIN, 15));
        txtPublisher.setColumns(10);
        panel.add(txtPublisher);
        
        JLabel lblLanguage = new JLabel("Language");
        lblLanguage.setForeground(new Color(255, 255, 255));
        lblLanguage.setBounds(334, 575, 66, 30);
        lblLanguage.setFont(new Font("Tahoma", Font.PLAIN, 15));
        panel.add(lblLanguage);
        
        JLabel lblSubject = new JLabel("Subject");
        lblSubject.setForeground(new Color(255, 255, 255));
        lblSubject.setBounds(78, 575, 64, 30);
        lblSubject.setFont(new Font("Tahoma", Font.PLAIN, 15));
        panel.add(lblSubject);
        
        comboBoxSubject = new JComboBox();
        comboBoxSubject.setBounds(78, 606, 246, 30);
        comboBoxSubject.setBackground(new Color(255, 255, 255));
        
        comboBoxSubject.setFont(new Font("Tahoma", Font.PLAIN, 15));
        panel.add(comboBoxSubject);
		        
		
		JButton btnRegister = new JButton("Register");
		btnRegister.setBounds(560, 660, 112, 30);
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registerBooks();
				txtTitle.setText("");
				txtAuthor.setText("");
				txtPublisher.setText("");
				txtQuantity.setText("");
				txtISBN.setText("");
				comboBoxSubject.setSelectedIndex(0);
				languageComboBox.setSelectedIndex(0);
				
			}
				
		});
		
		btnRegister.setForeground(new Color(255, 255, 255));
		btnRegister.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnRegister.setBorderPainted(false);
		btnRegister.setBackground(new Color(0, 147, 217));
		panel.add(btnRegister);
		
		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setForeground(new Color(255, 255, 255));
		lblQuantity.setBounds(493, 575, 66, 30);
		lblQuantity.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel.add(lblQuantity);
		
		txtQuantity = new JTextField();
		txtQuantity.setBounds(492, 605, 89, 30);
		txtQuantity.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtQuantity.setColumns(10);
		panel.add(txtQuantity);
		
		languageComboBox = new JComboBox();
		languageComboBox.setBounds(334, 606, 148, 30);
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
		scrollPane.setBounds(10, 31, 1236, 361);
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
						
						btnUpdate.setEnabled(true);
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
		
		btnUpdate = new JButton("Update");
		btnUpdate.setBounds(437, 660, 113, 30);
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
		txtSrTitle.setBounds(840, 495, 358, 30);
		txtSrTitle.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtSrTitle.setColumns(10);
		panel.add(txtSrTitle);
		
		JLabel lblTitle1 = new JLabel("Book Title");
		lblTitle1.setForeground(new Color(255, 255, 255));
		lblTitle1.setBounds(840, 464, 64, 30);
		lblTitle1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel.add(lblTitle1);
		
		txtSrBookNum = new JTextField();
		txtSrBookNum.setBounds(840, 564, 358, 30);
		txtSrBookNum.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtSrBookNum.setColumns(10);
		panel.add(txtSrBookNum);
		
		JLabel lblBookNum1 = new JLabel("Book Number");
		lblBookNum1.setForeground(new Color(255, 255, 255));
		lblBookNum1.setBounds(840, 533, 89, 30);
		lblBookNum1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel.add(lblBookNum1);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setBounds(1027, 621, 125, 30);
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
		btnViewData.setBounds(893, 621, 124, 30);
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
		txtTitle.setBounds(78, 464, 300, 30);
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
		btnExport.setBounds(893, 662, 259, 30);
		panel.add(btnExport);
		
		JLabel lblSort = new JLabel("Sort By:");
		lblSort.setForeground(Color.WHITE);
		lblSort.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSort.setBackground(new Color(89, 89, 89));
		lblSort.setBounds(1024, 401, 64, 30);
		panel.add(lblSort);
		
		cbSort = new JComboBox();
		cbSort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sort();
			}
		});
		cbSort.setModel(new DefaultComboBoxModel(new String[] {"Date Registered", "Title"}));
		cbSort.setFont(new Font("Tahoma", Font.PLAIN, 15));	
		cbSort.setBackground(Color.WHITE);
		cbSort.setBounds(1098, 401, 148, 30);
		

		panel.add(cbSort);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();

		        if (selectedRow == -1) {
		            // No row selected, show an error message
		            JOptionPane.showMessageDialog(getRootPane(), "Please select a book to remove.", "Error", JOptionPane.ERROR_MESSAGE);
		        } else {
		            // Get the Book_Num from the selected row
		            String bookNum = table.getValueAt(selectedRow, 0).toString(); // Assuming Book_Num is in the first column (index 0)
		            String getAcc = table.getValueAt(selectedRow, 8).toString();
		            int acc = Integer.parseInt(String.valueOf(getAcc)); 
		            // Confirm the removal with a dialog
		            int dialogResult = JOptionPane.showConfirmDialog(getRootPane(), "Are you sure you want to remove this book?", "Confirmation", JOptionPane.YES_NO_OPTION);
		            
		            if (dialogResult == JOptionPane.YES_OPTION) {
		                // User confirmed, proceed with removal
		                removeBook(bookNum, acc);
		                try {
							displayLatestData();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} // Update the displayed data after removal
		            }
		        }
			}
		});
		btnRemove.setForeground(Color.WHITE);
		btnRemove.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnRemove.setBorderPainted(false);
		btnRemove.setBackground(new Color(220, 20, 60));
		btnRemove.setBounds(80, 660, 112, 30);
		panel.add(btnRemove);
										
		radioRegister.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	
        		//Enable register assets
        		txtTitle.setEnabled(true);
        		txtTitle.setEditable(true);
        		txtAuthor.setEnabled(true);
        		txtAuthor.setEditable(true);
        		txtPublisher.setEnabled(true);
        		txtPublisher.setEditable(true);
        		txtQuantity.setEnabled(true);
        		txtQuantity.setEditable(true);
        		txtISBN.setEnabled(true);
        		txtISBN.setEditable(true);
        		comboBoxSubject.setEnabled(true);
        		languageComboBox.setEnabled(true);
        		btnRegister.setEnabled(true);
        		
		    	//Disable search assets        		
		    	txtSrTitle.setEnabled(false);
		    	txtSrTitle.setEditable(false);
		    	txtSrBookNum.setEnabled(false);
		    	txtSrBookNum.setEnabled(false);
		    	btnViewData.setEnabled(false);
		    	btnSearch.setEnabled(false);
		    	btnExport.setEnabled(false);
        	}
        });
		
		radioSearch.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		//Disable register assets
        		txtTitle.setEnabled(false);
        		txtTitle.setEditable(false);
        		txtAuthor.setEnabled(false);
        		txtAuthor.setEditable(false);
        		txtPublisher.setEnabled(false);
        		txtPublisher.setEditable(false);
        		txtQuantity.setEnabled(false);
        		txtQuantity.setEditable(false);
        		txtISBN.setEnabled(false);
        		txtISBN.setEditable(false);
        		comboBoxSubject.setEnabled(false);
        		languageComboBox.setEnabled(false);
        		btnRegister.setEnabled(false);
        		
		    	//Enable search assets        		
		    	txtSrTitle.setEnabled(true);
		    	txtSrTitle.setEditable(true);
		    	txtSrBookNum.setEnabled(true);
		    	txtSrBookNum.setEnabled(true);
		    	btnViewData.setEnabled(true);
		    	btnSearch.setEnabled(true);
		    	btnExport.setEnabled(true);
        	}
        });
		
		displayLatestData();
	}
			
	private JTextField txtSrTitle;
	private JTextField txtSrBookNum;
	
	
	// Method to remove a book by Book_Num
	private void removeBook(String bookNum, int acc) {
	    try {
	        String deleteSql = "DELETE FROM Books WHERE Book_Num = ? AND Accession_Num = ?";

	        try (PreparedStatement deleteStmt = conn.prepareStatement(deleteSql)) {
	            deleteStmt.setString(1, bookNum);
	            deleteStmt.setInt(2, acc);
	            // Execute the update
	            int rowsAffected = deleteStmt.executeUpdate();

	            if (rowsAffected > 0) {
	                JOptionPane.showMessageDialog(getRootPane(), "Book removed successfully.");
	            } else {
	                JOptionPane.showMessageDialog(getRootPane(), "Failed to remove the book.", "Error", JOptionPane.ERROR_MESSAGE);
	            }
	        }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	}
	
	// Use this method to get the selected sorting criteria
	private String getOrderByClause() {
	    String selectedSortBy = (String) cbSort.getSelectedItem();
	    String orderByClause;

	    switch (selectedSortBy) {
	        case "Date Registered":
	            orderByClause = "ORDER BY date_registered";
	            break;
	        case "Title":
	            orderByClause = "ORDER BY title, Accession_Num";
	            break;

	        default:
	            orderByClause = ""; 
	    }

	    return orderByClause;
	}
	
	public void sort() {
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
								
				String sql = "SELECT * FROM Books " + getOrderByClause();
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
					String dateRegistered = String.valueOf(rs.getString("date_registered"));
					String status = rs.getString("book_status");
					
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
								
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
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
	        
	        // Add headers to the CSV file
	        fw.append("Book Num");
	        fw.append(',');
	        fw.append(',');
	        fw.append("Title");
	        fw.append(',');
	        fw.append(',');
	        fw.append("Author");
	        fw.append(',');
	        fw.append(',');
	        fw.append("ISBN");
	        fw.append(',');
	        fw.append(',');
	        fw.append("Publisher");
	        fw.append(',');
	        fw.append(',');
	        fw.append("Language");
	        fw.append(',');
	        fw.append(',');
	        fw.append("Subject");
	        fw.append(',');
	        fw.append(',');
	        fw.append("Quantity");
	        fw.append(',');
	        fw.append(',');
	        fw.append("Dewey Decimal");
	        fw.append(',');
	        fw.append(',');
	        fw.append("Accession Num");
	        fw.append(',');
	        fw.append(',');
	        fw.append("Date Registered");
	        fw.append('\n');

	        // Fetch data from the database
	        pst = conn.prepareStatement("SELECT * FROM Books");
	        rs = pst.executeQuery();

	        int totalBooks = 0;

	        while (rs.next()) {
	        	fw.append(rs.getString(1));  //column index for Title
	            fw.append(',');
	            fw.append(',');
	            fw.append(rs.getString(2));  //column index for Title
	            fw.append(',');
	            fw.append(',');
	            fw.append(rs.getString(3));  //column index for Author
	            fw.append(',');
	            fw.append(',');
	            fw.append(rs.getString(4));  //column index for ISBN
	            fw.append(',');
	            fw.append(',');
	            fw.append(rs.getString(5));  //column index for Publisher
	            fw.append(',');
	            fw.append(',');
	            fw.append(rs.getString(6));  //column index for Language
	            fw.append(',');
	            fw.append(',');
	            fw.append(rs.getString(7));  //column index for Subject
	            fw.append(',');
	            fw.append(',');
	            fw.append(rs.getString(8));  //column index for Quantity
	            fw.append(',');
	            fw.append(',');
	            fw.append(rs.getString(9));  //column index for Book_Num
	            fw.append(',');
	            fw.append(',');
	            fw.append(rs.getString(10)); //column index for Dewey_Decimal
	            fw.append(',');
	            fw.append(',');
	            fw.append(rs.getString(12)); //column index for Book_Status
	            fw.append('\n');

	            totalBooks++;
	        }

	        // Write the total number of books registered
	        fw.append('\n');
	        fw.append("Total Books Registered: " + totalBooks);
	        
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
	            e1.printStackTrace();
	        }

	        Connection conn;

	        try {
	            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/BooksDB", "root", "ranielle25");
	            Statement stmt = conn.createStatement();
	            System.out.println("Connected");
	            // Get the inputs
	            String title = txtTitle.getText();
	            String author = txtAuthor.getText();
	            String isbn = txtISBN.getText();
	            String publisher = txtPublisher.getText();
	            String language = languageComboBox.getSelectedItem().toString();
	            String subject = comboBoxSubject.getSelectedItem().toString();
	            int quantity = Integer.valueOf(txtQuantity.getText()); // Converts String to Int
	            DeweyMap deweyMap = new DeweyMap();
	            double deweyDecimal = deweyMap.getDeweyForSubject(subject);

	            // Create a map to store used book numbers for each title
	            Map<String, Integer> titleToUsedBookNumber = new HashMap<>();

	            // Check if a book with the same title already exists
	            String checkExistingBookSql = "SELECT Book_Num, MAX(Accession_Num) AS latestAccessionNum FROM Books WHERE Title = ? GROUP BY Book_Num";
	            try (PreparedStatement checkExistingBookStmt = conn.prepareStatement(checkExistingBookSql)) {
	                checkExistingBookStmt.setString(1, title);
	                ResultSet existingBookResult = checkExistingBookStmt.executeQuery();

	                int usedBookNum;
	                int latestAccessionNum = 0;

	                if (existingBookResult.next()) {
	                    // Book with the same title already exists
	                    usedBookNum = existingBookResult.getInt("Book_Num");
	                    latestAccessionNum = existingBookResult.getInt("latestAccessionNum");
	                } else {
	                    // Book with the same title doesn't exist, generate new book number
	                    usedBookNum = 100000 + new Random().nextInt(900000);
	                }

	                titleToUsedBookNumber.put(title, usedBookNum);

	                for (int i = 0; i < quantity; i++) {
	                    // Increment accessionNum for each new book
	                    int accessionNum = latestAccessionNum + i + 1;

	                    String sql = "INSERT INTO Books (Title, Author, ISBN, Publisher, Language, Subject, Quantity, Book_Num, Dewey_Decimal, Accession_Num, book_status, date_registered)" +
	                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, CURRENT_DATE())";

	                    try (PreparedStatement insertBookStmt = conn.prepareStatement(sql)) {
	                        insertBookStmt.setString(1, title);
	                        insertBookStmt.setString(2, author);
	                        insertBookStmt.setString(3, isbn);
	                        insertBookStmt.setString(4, publisher);
	                        insertBookStmt.setString(5, language);
	                        insertBookStmt.setString(6, subject);
	                        insertBookStmt.setInt(7, quantity);
	                        insertBookStmt.setInt(8, usedBookNum);
	                        insertBookStmt.setDouble(9, deweyDecimal);
	                        insertBookStmt.setInt(10, accessionNum);
	                        insertBookStmt.setString(11, "Available");

	                        // Execute query
	                        insertBookStmt.executeUpdate();
	                    }
	                }
	            }

	            JOptionPane.showMessageDialog(getRootPane(), "Book Registered");
	            // Fetch and display the latest data
	            displayLatestData();

	        } catch (SQLException e1) {
	            e1.printStackTrace();
	        }

	    } catch (Exception e1) {
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
