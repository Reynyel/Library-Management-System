import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Component;
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
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JFormattedTextField.AbstractFormatter;
import color.AlternateColorRender;
import tablemodel.NonEditTableModel;

import javax.swing.JRadioButton;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.toedter.calendar.IDateEvaluator;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

import GradientBackground.gradientBackground;
import com.toedter.calendar.JDayChooser;

public class TransactionFrame extends JPanel {

	private JPanel contentPane;
	private JPanel panel;	
	private JTextField txtBookNum;
	private JTextField txtTitle;
	private JTextField txtAuthor;
	private JTable tblTransac;
	private JTextField txtBorrID;
	private JComboBox cbAccession;
	private List<Book> selectedBooks = new ArrayList<>();
	 // Set to store selected titles for each user
    private Set<String> selectedTl = new HashSet<>();
	private List<Name> selectedName = new ArrayList<>();
	// Declare a variable to store the current active user ID
    private String activeUserId = null;
    
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
		setPreferredSize(new Dimension(1425, 980));
	    setLayout(null);
		setBounds(100, 100, 1425, 980);
		
		panel = new gradientBackground();
		panel.setBackground(new Color(0, 153, 255));
        panel.setBounds(0, 0, 1425, 980);
        add(panel);
        panel.setLayout(null);
		
		AlternateColorRender alternate = new AlternateColorRender();
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 455, 1405, 447);
		panel.add(scrollPane);
				
		Object[][] data = {null, null, null, null, null, null, null, null, null, null};
		Object[] columnNames = {"Book Number", "Title", "Author", "ISBN", "Publisher", "Language", "Subject", "Dewey", "Accession", "Status"};
		NonEditTableModel model;
		Set<Integer> editableColumns = new HashSet<>();
		
		tblTransac = new JTable();
		tblTransac.setFont(new Font("Tahoma", Font.PLAIN, 18));
		scrollPane.setViewportView(tblTransac);
		tblTransac.setBorder(new LineBorder(new Color(0, 0, 0)));
		tblTransac.setColumnSelectionAllowed(true);
		tblTransac.setCellSelectionEnabled(true);
		tblTransac.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null, null, null},
			},
			new String[] {
					"Book Number", "Title", "Author", "ISBN", "Publisher", "Language", "Subject", "Dewey", "Accession", "Status"
			}
		));
		tblTransac.setModel(new NonEditTableModel(data, columnNames, editableColumns));
		tblTransac.setDefaultRenderer(Object.class, alternate);
		

		
		txtBorrID = new JTextField();
		txtBorrID.setFont(new Font("Verdana", Font.PLAIN, 18));
		txtBorrID.setColumns(10);
		txtBorrID.setBounds(952, 231, 342, 35);
		panel.add(txtBorrID);
		
		JLabel lblBorrowersName = new JLabel("Borrower's ID");
		lblBorrowersName.setForeground(new Color(255, 255, 255));
		lblBorrowersName.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblBorrowersName.setBounds(952, 195, 133, 30);
		panel.add(lblBorrowersName);
		
				
		JButton btnUpdate = new JButton("Enter");
		btnUpdate.setBackground(new Color(34, 139, 34));
		btnUpdate.setForeground(new Color(255, 255, 255));
		btnUpdate.setFont(new Font("Verdana", Font.BOLD, 17));
		btnUpdate.setBounds(1034, 277, 173, 40);
		btnUpdate.setBorderPainted(false);
		panel.add(btnUpdate);
		
		JRadioButton radioTransaction = new JRadioButton("View Transactions");
		radioTransaction.setFont(new Font("Verdana", Font.BOLD, 15));
		radioTransaction.setForeground(new Color(255, 255, 255));
		radioTransaction.setBackground(new Color(0, 0, 0));
		radioTransaction.setOpaque(false);
		radioTransaction.setContentAreaFilled(false);
        radioTransaction.setBorderPainted(false);
		radioTransaction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayTransactionHistory();
			}
		});
		radioTransaction.setBounds(1121, 418, 173, 30);
		panel.add(radioTransaction);
		
		JRadioButton radioBooks = new JRadioButton("View Books");
		radioBooks.setFont(new Font("Verdana", Font.BOLD, 15));
		radioBooks.setForeground(new Color(255, 255, 255));
		radioBooks.setBackground(new Color(0, 0, 0));
		radioBooks.setOpaque(false);
		radioBooks.setContentAreaFilled(false);
        radioBooks.setBorderPainted(false);
		radioBooks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewBooks();
			}
		});	
		radioBooks.setBounds(1296, 418, 119, 30);
		panel.add(radioBooks);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update();
			}
		});
		
		 ButtonGroup radioGroup = new ButtonGroup();
		    radioGroup.add(radioTransaction);
		    radioGroup.add(radioBooks);
		
		String title = "Borrow Book";
		Border border = BorderFactory.createTitledBorder(title);
		
		JButton btnExport = new JButton("Export to PDF");
		btnExport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				testPdf();
			}
		});
		btnExport.setForeground(Color.WHITE);
		btnExport.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnExport.setBorderPainted(false);
		btnExport.setBackground(new Color(0, 128, 0));
		btnExport.setBounds(1165, 919, 250, 25);
		panel.add(btnExport);
		
		txtBookNum = new JTextField();
		txtBookNum.setBounds(113, 187, 529, 35);
		panel.add(txtBookNum);
		txtBookNum.setFont(new Font("Verdana", Font.PLAIN, 18));
		txtBookNum.setColumns(10);
		
		JLabel lblBookNumber = new JLabel("Book Number");
		lblBookNumber.setForeground(new Color(255, 255, 255));
		lblBookNumber.setBounds(113, 158, 123, 28);
		panel.add(lblBookNumber);
		lblBookNumber.setFont(new Font("Tahoma", Font.BOLD, 17));
		
		JLabel lblAuthors = new JLabel("Author(s)");
		lblAuthors.setForeground(new Color(255, 255, 255));
		lblAuthors.setBounds(113, 247, 91, 27);
		panel.add(lblAuthors);
		lblAuthors.setFont(new Font("Tahoma", Font.BOLD, 17));
		
		JLabel lblAccession = new JLabel("Accession");
		lblAccession.setForeground(new Color(255, 255, 255));
		lblAccession.setBounds(113, 336, 91, 24);
		panel.add(lblAccession);
		lblAccession.setFont(new Font("Tahoma", Font.BOLD, 17));
		
		cbAccession = new JComboBox();
		cbAccession.setBounds(113, 365, 168, 28);
		panel.add(cbAccession);
		cbAccession.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setStatusBasedOnAccession();
			}
		});
		cbAccession.setFont(new Font("Verdana", Font.PLAIN, 18));
		cbAccession.setBackground(Color.WHITE);
		
		txtAuthor = new JTextField();
		txtAuthor.setBounds(113, 272, 529, 35);
		panel.add(txtAuthor);
		txtAuthor.setFont(new Font("Verdana", Font.PLAIN, 18));
		txtAuthor.setColumns(10);
		
		txtStatus = new JTextField();
		txtStatus.setBounds(445, 366, 197, 27);
		panel.add(txtStatus);
		txtStatus.setFont(new Font("Verdana", Font.PLAIN, 18));
		txtStatus.setColumns(10);
		
		JLabel lblStatus = new JLabel("Status");
		lblStatus.setForeground(new Color(255, 255, 255));
		lblStatus.setBounds(445, 333, 73, 30);
		panel.add(lblStatus);
		lblStatus.setFont(new Font("Tahoma", Font.BOLD, 17));
		
		txtTitle = new JTextField();
		txtTitle.setBounds(113, 99, 529, 35);
		panel.add(txtTitle);
		txtTitle.setFont(new Font("Verdana", Font.PLAIN, 18));
		txtTitle.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Book Title");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(113, 60, 90, 28);
		panel.add(lblNewLabel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		JButton btnSearchBook = new JButton("Search Book");
		btnSearchBook.setBounds(652, 98, 155, 35);
		panel.add(btnSearchBook);
		btnSearchBook.setForeground(new Color(255, 255, 255));
		btnSearchBook.setBackground(new Color(65, 105, 225));
		btnSearchBook.setBorderPainted(false);
		btnSearchBook.setFont(new Font("Verdana", Font.BOLD, 15));
		
		JLabel lblImportantFields = new JLabel("- Important fields");
		lblImportantFields.setForeground(Color.WHITE);
		lblImportantFields.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblImportantFields.setBounds(542, 71, 100, 30);
		panel.add(lblImportantFields);
		
		JLabel lblStudentNo_1_2 = new JLabel("*");
		lblStudentNo_1_2.setForeground(Color.RED);
		lblStudentNo_1_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblStudentNo_1_2.setBounds(531, 70, 23, 30);
		panel.add(lblStudentNo_1_2);
		
		JLabel lblStudentNo_1_2_1 = new JLabel("*");
		lblStudentNo_1_2_1.setForeground(Color.RED);
		lblStudentNo_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblStudentNo_1_2_1.setBounds(206, 60, 16, 28);
		panel.add(lblStudentNo_1_2_1);
		
		JButton btnCheckout = new JButton("Checkout");
		btnCheckout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (selectedBooks.isEmpty()) {
		    		Font customFont = new Font("Arial", Font.PLAIN, 16);
		            UIManager.put("OptionPane.messageFont", customFont);
		            UIManager.put("OptionPane.buttonFont", customFont);
		            
		            JOptionPane.showMessageDialog(getRootPane(), "Select a book first",
		                    "Checkout Error", JOptionPane.ERROR_MESSAGE);

		            // Reset UIManager properties to default
		            UIManager.put("OptionPane.messageFont", UIManager.getDefaults().getFont("OptionPane.messageFont"));
		            UIManager.put("OptionPane.buttonFont", UIManager.getDefaults().getFont("OptionPane.buttonFont"));
		            return; // Do not proceed with the transaction
		        }
				
				if(displaySelectedBooksDetails(selectedBooks) == false) {
					return;
				}
				else {			
					checkout();					
				}
			}
		});
		btnCheckout.setForeground(Color.WHITE);
		btnCheckout.setFont(new Font("Verdana", Font.BOLD, 17));
		btnCheckout.setBorderPainted(false);
		btnCheckout.setBackground(new Color(34, 139, 34));
		btnCheckout.setBounds(1034, 340, 173, 40);
		panel.add(btnCheckout);
		btnSearchBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				search();
			}
		});
		String title2 = "Return Book";
		Border border2 = BorderFactory.createTitledBorder(title2);
		
		fetchAndDisplayData();
	}	
	
	Connection conn;
	PreparedStatement pst;
	ResultSet rs;
	
	private boolean displaySelectedBooksDetails(List<Book> selectedBooks) {
	    // Build the message for the pop-up dialog
	    StringBuilder message = new StringBuilder("Books to be borrowed:\n\n");
	 // Retrieve the borrower name (assuming it's the same for all selected books)
	    String borrowerName = selectedBooks.isEmpty() ? "" : selectedBooks.get(0).getBorrowerName();
	    for (Book book : selectedBooks) {
	        message.append("Book Number: ").append(book.getBookNum()).append("\n");
	        message.append("Title: ").append(book.getTitle()).append("\n");
	        message.append("Accession Number: ").append(book.getAccessionNum()).append("\n\n");
	    }
	    message.append("Borrower: ").append(borrowerName).append("\n\n");

	    // Display a confirmation dialog with "Yes" and "No" options
	    int dialogResult = JOptionPane.showConfirmDialog(getRootPane(), message.toString(), "Selected Books Details", JOptionPane.YES_NO_OPTION);

	    // Return true if the user clicked "Yes," otherwise return false
	    return dialogResult == JOptionPane.YES_OPTION;
	}

    
 // check if file already exissts
 		private String getUniqueFileName(String baseFileName) {
 		    File file = new File(baseFileName);
 		    if (!file.exists()) {
 		        return baseFileName;  // File doesn't exist, return the original name
 		    }

 		    int counter = 1;
 		    String fileName;
 		    do {
 		        fileName = baseFileName.replaceFirst("[.][^.]+$", "(" + counter + ").$0");
 		        file = new File(fileName);
 		        counter++;
 		    } while (file.exists());

 		    return fileName;
 		}

 		
 		public void testPdf() {
 			// Fetch data from the database
 	        try {
 				pst = conn.prepareStatement("SELECT * FROM Transactions WHERE BookStatus = 'Borrowed'");
 				rs = pst.executeQuery();
 				
 				Document PDFReport = new Document();
 				    
 			    
 				try {
 					// Set the page size to landscape
 		            PDFReport.setPageSize(PageSize.A3.rotate());
 		         // Inside testPdf and testPdfBySub methods
 		            String outputFileName = getUniqueFileName("C:\\Users\\LINDELL\\Desktop\\TransactionReport.pdf");
 		            PdfWriter.getInstance(PDFReport, new FileOutputStream(outputFileName));

 					PDFReport.open();
 					
 					com.itextpdf.text.Font customFont = FontFactory.getFont(FontFactory.HELVETICA, 18, Font.BOLD, BaseColor.BLACK);
 					com.itextpdf.text.Font customFont2 = FontFactory.getFont(FontFactory.HELVETICA, 15, Font.BOLD, BaseColor.BLACK);
 					// Add table header
 					com.itextpdf.text.Font headerFont = FontFactory.getFont(FontFactory.HELVETICA, 14, Font.BOLD, BaseColor.BLACK);
 					// Add image
 		            try {
 		                Image logo = Image.getInstance("C:\\Users\\LINDELL\\Projects\\Library-Management-System\\res\\logo1.png");  // Replace with the actual path to your image
 		                logo.setAlignment(Element.ALIGN_MIDDLE); // Adjust the alignment as needed
 		                PDFReport.add(logo);
 		                
 		            } catch (BadElementException | IOException e) {
 		                e.printStackTrace();
 		            }
 		            
 		            Font boldFont = new Font(FontFactory.HELVETICA, 16, Font.BOLD);
 		            // Define a custom font
 		            
 		         // Add title
 		            Paragraph title1 = new Paragraph(" Santa Rosa Educational Institute", customFont);
 		            title1.setAlignment(Element.ALIGN_CENTER);
 		            PDFReport.add(title1);
 		            
 					// Add title
 		            Paragraph title2 = new Paragraph("List of Recorded Transactions");
 		            title2.setAlignment(Element.ALIGN_CENTER);
 		            PDFReport.add(title2);

 		            // Add academic year
 		            AcademicYear ya = AcademicYear.now(ZoneId.systemDefault());
 		            String formattedAcadYear = ya.format(FormatStyle.FULL);
 		            Paragraph acadYear = new Paragraph("Academic Year: " + formattedAcadYear);
 		            acadYear.setAlignment(Element.ALIGN_CENTER);
 		            PDFReport.add(acadYear);
 		         
 		            // Add space (empty line) between academic year and table header
 		            PDFReport.add(new Paragraph("\n"));
 		            
 					PdfPTable PDFTable = new PdfPTable(9);
 					
 					 // Add table header
 		            String[] headers = {"Transaction ID", "Book Num", "Title", "Accession Number", "Status", "Transaction Date", "Return Date",
 		            		"Borrower", "ID"};
 		            
 		            
 		            int totalStudents = 0;
 		            String preparedBy = ""; // Initialize with an empty string
 		            
 		            for (String header : headers) {
 		            	PdfPCell headerCell = new PdfPCell(new Phrase(header, headerFont));
 		                headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
 		                headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
 		                headerCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
 		                PDFTable.addCell(headerCell);
 		            }
 		            
 					PdfPCell table_cell;
 					
 					while(rs.next()) {
 						String transaction_id = rs.getString("transaction_id");
 						table_cell = new PdfPCell(new Phrase(transaction_id));
 						PDFTable.addCell(table_cell);
 						String BooNum = rs.getString("BooNum");
 						table_cell = new PdfPCell(new Phrase(BooNum));
 						PDFTable.addCell(table_cell);
 						String Title = rs.getString("Title");
 						table_cell = new PdfPCell(new Phrase(Title));
 						PDFTable.addCell(table_cell);
 						String AccessionNum = rs.getString("AccessionNum");
 						table_cell = new PdfPCell(new Phrase(AccessionNum));
 						PDFTable.addCell(table_cell);
 						String BookStatus = rs.getString("BookStatus");
 						table_cell = new PdfPCell(new Phrase(BookStatus));
 						PDFTable.addCell(table_cell);
 						String transaction_date = rs.getString("transaction_date");
 						table_cell = new PdfPCell(new Phrase(transaction_date));
 						PDFTable.addCell(table_cell);
 						String return_date = rs.getString("return_date");
 						table_cell = new PdfPCell(new Phrase(return_date));
 						PDFTable.addCell(table_cell);
 						String Borrower = rs.getString("Borrower");
 						table_cell = new PdfPCell(new Phrase(Borrower));
 						PDFTable.addCell(table_cell);
 						String user_id = rs.getString("user_id");
 						table_cell = new PdfPCell(new Phrase(user_id));
 						PDFTable.addCell(table_cell);
 						
 						
 						totalStudents++;
 					}
 					
 											
 				
 					PDFReport.add(PDFTable);
 					// Add total number of books
 					Paragraph line = new Paragraph("\n");
 					line.setAlignment(Element.ALIGN_RIGHT);
 					PDFReport.add(line);
 					
 					// Add total number of books
 					Paragraph totalBooksParagraph = new Paragraph("Total No. of Transactions: " + totalStudents, customFont2);
 					totalBooksParagraph.setAlignment(Element.ALIGN_LEFT);
 					totalBooksParagraph.setIndentationLeft(125);
 					PDFReport.add(totalBooksParagraph);

 					// Add who generated the report
 					String userType = MainMenuFrame.getUser();
 					if ("Librarian".equalsIgnoreCase(userType)) {
 					    preparedBy = "Librarian";
 					} else if ("Admin".equalsIgnoreCase(userType)) {
 					    preparedBy = "Admin";
 					}
 					
 					// Add who generated the report
 					Paragraph generatedByParagraph = new Paragraph("Prepared by: " + preparedBy, customFont2);
 					generatedByParagraph.setAlignment(Element.ALIGN_LEFT);
 					generatedByParagraph.setIndentationLeft(125);
 					PDFReport.add(generatedByParagraph);
 					
 					// Get the current date and time
 				    LocalDateTime currentTime = LocalDateTime.now();

 				    // Format the date and time using the desired pattern
 				    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy hh:mma");
 				    String formattedDateTime = currentTime.format(formatter);
 				    
 					// Add who generated the report
 					Paragraph reportGenerated = new Paragraph("Report Generated: " + formattedDateTime, customFont2);
 					reportGenerated.setAlignment(Element.ALIGN_LEFT);
 					reportGenerated.setIndentationLeft(125);
 					PDFReport.add(reportGenerated);
 					
 					// Add who generated the report with underlined name
 					Paragraph principal = new Paragraph();
 					Chunk nameChunk = new Chunk("Elaine B. Santos, Ed.D.", customFont2);
 					nameChunk.setUnderline(1.5f, -1);  // Adjust the values for underline thickness and position
 					principal.add(nameChunk);
 					principal.add(Chunk.NEWLINE);
 					
 					// Add indentation for "Principal"
 					Paragraph principalRole = new Paragraph("Principal", customFont2);
 					principalRole.setIndentationLeft(50);  // Adjust the indentation as needed
 					principal.add(principalRole);
 					principal.setIndentationLeft(800);
 					PDFReport.add(principal);
 					PDFReport.close();
 				} catch (FileNotFoundException | DocumentException e) {
 					// TODO Auto-generated catch block
 					e.printStackTrace();
 				}
 			} catch (SQLException e) {
 				// TODO Auto-generated catch block
 				e.printStackTrace();
 			}

 		}

	public void export() {
	    // Create a format for the date in the file name
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	    // Get the current date and format it
	    String currentDate = dateFormat.format(new Date());

	    // Construct the base file name with the current date
	    String baseFileName = "C:\\Users\\LINDELL\\Desktop\\transactions_export_" + currentDate + ".csv";

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
			fw.append(',');
	        fw.append(',');
	        fw.append(',');
	        fw.append(',');
	        fw.append(',');
	        fw.append(',');
	        fw.append(',');
	        fw.append(',');
	        fw.append(',');
	        fw.append(',');
	        fw.append(',');
	        fw.append("List of All Recorded Transactions");
	        fw.append("\n");
	        fw.append(',');
	        fw.append(',');
	        fw.append(',');
	        fw.append(',');
	        fw.append(',');
	        fw.append(',');
	        fw.append(',');
	        fw.append(',');
	        fw.append(',');
	        fw.append(',');
	        fw.append("Prepared On: " + currentDate);
	        fw.append("\n");
	        fw.append(',');
	        fw.append(',');
	        fw.append(',');
	        fw.append(',');
	        fw.append(',');
	        fw.append(',');
	        fw.append(',');
	        fw.append(',');
	        fw.append(',');
	        fw.append(',');
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
	        fw.append("Book Status");
	        fw.append(',');
	        fw.append(',');
	        fw.append("Transaction Date");
	        fw.append(',');
	        fw.append(',');
	        fw.append("Return Date");
	        fw.append(',');
	        fw.append(',');
	        fw.append("Borrower");
	        fw.append(',');
	        fw.append(',');
	        fw.append(',');
	        fw.append(',');
	        fw.append("Borrower ID");
	        fw.append('\n');

	        // Fetch data from the database
	        pst = conn.prepareStatement("SELECT * FROM Transactions");
	        rs = pst.executeQuery();

	        int totalBooks = 0;

	        while (rs.next()) {
	        	fw.append(rs.getString(1));  //the column index for Transaction ID
	            fw.append(',');
	            fw.append(',');
	            fw.append(rs.getString(2));  //the column index for Book Num
	            fw.append(',');
	            fw.append(',');
	            fw.append(rs.getString(3));  //the column index for Title
	            fw.append(',');
	            fw.append(',');
	            fw.append(rs.getString(4));  //the column index for Accession
	            fw.append(',');
	            fw.append(',');
	            fw.append(rs.getString(5));  //the column index for Status
	            fw.append(',');
	            fw.append(',');
	            fw.append(rs.getString(6));  //the column index for Transaction Date
	            fw.append(',');
	            fw.append(',');
	            fw.append(rs.getString(7));  //the column index for Return Date
	            fw.append(',');
	            fw.append(',');
	            fw.append(rs.getString(8));  //the column index for Borrower
	            fw.append(',');
		        fw.append(',');
		        fw.append(',');
	            fw.append(rs.getString(9));  //the column index for Borrower ID
	            fw.append('\n');

	            totalBooks++;
	        }

	        // Write the total number of books registered
	        fw.append('\n');
	        fw.append("Total Transactions Recorded: " + totalBooks);	        
	        fw.append('\n');
	        
	        String userType = MainMenuFrame.getUser();
	        
	        if ("Librarian".equalsIgnoreCase(userType)) {
	        	fw.append("Prepared by: " + "Librarian");
     		} else if ("Admin".equalsIgnoreCase(userType)) {
     			fw.append("Prepared by: " + "Admin");
     		}
	        Font customFont = new Font("Arial", Font.PLAIN, 16);
            UIManager.put("OptionPane.messageFont", customFont);
            UIManager.put("OptionPane.buttonFont", customFont);
            
            JOptionPane.showMessageDialog(getRootPane(), "Export success",
                    "Success", JOptionPane.INFORMATION_MESSAGE);

            // Reset UIManager properties to default
            UIManager.put("OptionPane.messageFont", UIManager.getDefaults().getFont("OptionPane.messageFont"));
            UIManager.put("OptionPane.buttonFont", UIManager.getDefaults().getFont("OptionPane.buttonFont"));
	        
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
            String selectTransactionsSql = "SELECT * FROM Books WHERE book_status = 'Available' AND Subject != 'Others'";
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
    				String dewey = rs.getString("Dewey_Decimal");
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
    
 // Method to check if the current day is a weekend
    private boolean isWeekend() {
        LocalDate currentDate = LocalDate.now();
        DayOfWeek dayOfWeek = currentDate.getDayOfWeek();
        return dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY;
    }

    // Method to show a message
    private void showMessage(String message) {
        Font customFont = new Font("Arial", Font.PLAIN, 16);
        UIManager.put("OptionPane.messageFont", customFont);
        UIManager.put("OptionPane.buttonFont", customFont);

        JOptionPane.showMessageDialog(getRootPane(), message, "Note", JOptionPane.INFORMATION_MESSAGE);

        // Reset UIManager properties to default
        UIManager.put("OptionPane.messageFont", UIManager.getDefaults().getFont("OptionPane.messageFont"));
        UIManager.put("OptionPane.buttonFont", UIManager.getDefaults().getFont("OptionPane.buttonFont"));
    }

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
            
            // Check if it's the weekend
            if (isWeekend()) {
                showMessage("Cannot make transactions on a weekend.");
                return; // Exit the method without proceeding with the transaction
            }
	                
            // check if the user has inserted a book title
            if(tl.isEmpty() && bn.isEmpty() && obAcc == null) {
            	JOptionPane.showMessageDialog(getRootPane(), "Please search for the book first");       
            }
            
         // Check if there is an ongoing transaction
            if (activeUserId != null && !activeUserId.equals(userId)) {
                JOptionPane.showMessageDialog(getRootPane(), "Another transaction is already in progress. Please wait.",
                        "Note", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            
            else {
            	int acc = Integer.parseInt(cbAccession.getSelectedItem().toString());
            	// Check if the user is a student or faculty/school staff based on ID number
            	if ("Student".equals(userType)) {
            	      			
            		String borrowerNameSql = "SELECT LastName, FirstName, MiddleName, GradeLevel, Section FROM Students WHERE StudentNo = ?";
            		try(PreparedStatement borrowerNameStmt = conn.prepareStatement(borrowerNameSql)){
            			borrowerNameStmt.setString(1, borrId);
            			ResultSet borrowerNameResult = borrowerNameStmt.executeQuery();
            			
            			int borrowedBooksCount = getBorrowedBooksCount(userId);
            			
            			// Check if the student has already borrowed the same title
            		    if (hasBorrowedSameTitle(userId, tl)) {
            		    	Font customFont = new Font("Arial", Font.PLAIN, 16);
        	                UIManager.put("OptionPane.messageFont", customFont);
        	                UIManager.put("OptionPane.buttonFont", customFont);
        	                
        	                JOptionPane.showMessageDialog(getRootPane(), "Students cannot borrow the same title twice.",
        	                        "Note", JOptionPane.INFORMATION_MESSAGE);

        	                // Reset UIManager properties to default
        	                UIManager.put("OptionPane.messageFont", UIManager.getDefaults().getFont("OptionPane.messageFont"));
        	                UIManager.put("OptionPane.buttonFont", UIManager.getDefaults().getFont("OptionPane.buttonFont"));
            		        return; // Exit the method without proceeding with the transaction
            		    } 
            		    if(borrowedBooksCount >= 3) {
            		    	Font customFont = new Font("Arial", Font.PLAIN, 16);
        	                UIManager.put("OptionPane.messageFont", customFont);
        	                UIManager.put("OptionPane.buttonFont", customFont);
        	                
        	                JOptionPane.showMessageDialog(getRootPane(), "This student cannot borrow more than 3 books.",
        	                        "Note", JOptionPane.INFORMATION_MESSAGE);

        	                // Reset UIManager properties to default
        	                UIManager.put("OptionPane.messageFont", UIManager.getDefaults().getFont("OptionPane.messageFont"));
        	                UIManager.put("OptionPane.buttonFont", UIManager.getDefaults().getFont("OptionPane.buttonFont"));
            		        return; // Exit the method without proceeding with the transaction        		    	
            		    }
            		    
            		    //check if the user is currently in the blocklist
            		    if(blockedFromBorrwing(userId)) {
            		    	Font customFont = new Font("Arial", Font.PLAIN, 16);
        	                UIManager.put("OptionPane.messageFont", customFont);
        	                UIManager.put("OptionPane.buttonFont", customFont);
        	                
        	                JOptionPane.showMessageDialog(getRootPane(), "This user is currently blocked from borrowing another book!",
        	                        "Note", JOptionPane.INFORMATION_MESSAGE);

        	                // Reset UIManager properties to default
        	                UIManager.put("OptionPane.messageFont", UIManager.getDefaults().getFont("OptionPane.messageFont"));
        	                UIManager.put("OptionPane.buttonFont", UIManager.getDefaults().getFont("OptionPane.buttonFont"));
            				return;
            			}
            		    
            		    //else proceed
            			if(borrowerNameResult.next()) {
            				
            				if (selectedTl.contains(tl)) {
                                Font customFont = new Font("Arial", Font.PLAIN, 16);
                                UIManager.put("OptionPane.messageFont", customFont);
                                UIManager.put("OptionPane.buttonFont", customFont);

                                JOptionPane.showMessageDialog(getRootPane(), "Students cannot borrow the same title twice.",
                                        "Note", JOptionPane.INFORMATION_MESSAGE);
                                txtTitle.setText("");
                				txtBookNum.setText("");
                				txtAuthor.setText("");
                				txtTitle.setText("");
                				cbAccession.removeAllItems();
                				txtStatus.setText("");
                                // Reset UIManager properties to default
                                UIManager.put("OptionPane.messageFont", UIManager.getDefaults().getFont("OptionPane.messageFont"));
                                UIManager.put("OptionPane.buttonFont", UIManager.getDefaults().getFont("OptionPane.buttonFont"));
                                return; // Exit the method without proceeding with the transaction
                            }
            				
            				String borrowerName = borrowerNameResult.getString("LastName") + 
            						", " + borrowerNameResult.getString("FirstName") +
            						" " + borrowerNameResult.getString("MiddleName");
            				String gradeLevel = borrowerNameResult.getString("GradeLevel");
            			    String section = borrowerNameResult.getString("Section");
            			    
            			    
            			    // Display a confirmation dialog before proceeding
            			    int confirmResult = showConfirmDialog(borrowerName, gradeLevel, section, tl, acc);
                            if (confirmResult == JOptionPane.YES_OPTION) {
                            	Book selectedBook = new Book(bn, tl, acc, borrowerName, borrId, userType);
                            	Book selectedTitle = new Book(tl);
                            	Name name = new Name(borrId);
                        		selectedName.add(name);                       		                      		
                        		
                                // Set the current user as the active user
                                activeUserId = userId;                                                              
                                
                                //checks if the user has already put more than 3 books in the list
                            	if(selectedBooks.size() >= 3) {
                            		Font customFont = new Font("Arial", Font.PLAIN, 16);
                	                UIManager.put("OptionPane.messageFont", customFont);
                	                UIManager.put("OptionPane.buttonFont", customFont);
                	                		
                	                JOptionPane.showMessageDialog(getRootPane(), "Cannot add more books!",
                	                        "Note", JOptionPane.INFORMATION_MESSAGE);
                	                txtTitle.setText("");
                    				txtBookNum.setText("");
                    				txtAuthor.setText("");
                    				txtTitle.setText("");
                    				cbAccession.removeAllItems();
                    				txtStatus.setText(""); 
                	                // Reset UIManager properties to default
                	                UIManager.put("OptionPane.messageFont", UIManager.getDefaults().getFont("OptionPane.messageFont"));
                	                UIManager.put("OptionPane.buttonFont", UIManager.getDefaults().getFont("OptionPane.buttonFont"));
                	                return;
                            	}
                            	else {
                            		selectedBooks.add(selectedBook);
                            		selectedTl.add(tl);
                            		Font customFont = new Font("Arial", Font.PLAIN, 16);
                            		UIManager.put("OptionPane.messageFont", customFont);
                            		UIManager.put("OptionPane.buttonFont", customFont);
                            		
                            		JOptionPane.showMessageDialog(getRootPane(), "Book Added!",
                            				"Note", JOptionPane.INFORMATION_MESSAGE);
                            		txtTitle.setText("");
                            		txtBookNum.setText("");
                            		txtAuthor.setText("");
                            		txtTitle.setText("");
                            		cbAccession.removeAllItems();
                            		txtStatus.setText(""); 
                            		// Reset UIManager properties to default
                            		UIManager.put("OptionPane.messageFont", UIManager.getDefaults().getFont("OptionPane.messageFont"));
                            		UIManager.put("OptionPane.buttonFont", UIManager.getDefaults().getFont("OptionPane.buttonFont"));
                            	}
                            }          			
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
      
            			// Set the current user as the active user
                        activeUserId = userId; 
            			if(blockedFromBorrwing(userId)) {
            				Font customFont = new Font("Arial", Font.PLAIN, 16);
        	                UIManager.put("OptionPane.messageFont", customFont);
        	                UIManager.put("OptionPane.buttonFont", customFont);
        	                
        	                JOptionPane.showMessageDialog(getRootPane(), "This user is currently blocked from borrowing another book!",
        	                        "Note", JOptionPane.INFORMATION_MESSAGE);

        	                // Reset UIManager properties to default
        	                UIManager.put("OptionPane.messageFont", UIManager.getDefaults().getFont("OptionPane.messageFont"));
        	                UIManager.put("OptionPane.buttonFont", UIManager.getDefaults().getFont("OptionPane.buttonFont"));
            				return;
            			}
            			
            			if(borrowerNameResult.next()) {
            				String borrowerName = borrowerNameResult.getString("LastName") + 
            						", " + borrowerNameResult.getString("FirstName") +
            						" " + borrowerNameResult.getString("MiddleName");
            				
            				Book selectedBook = new Book(bn, tl, acc, borrowerName, borrId, userType);
                            selectedBooks.add(selectedBook);
                            Font customFont = new Font("Arial", Font.PLAIN, 16);
        	                UIManager.put("OptionPane.messageFont", customFont);
        	                UIManager.put("OptionPane.buttonFont", customFont);
        	                		
        	                JOptionPane.showMessageDialog(getRootPane(), "Book Registered!",
        	                        "Note", JOptionPane.INFORMATION_MESSAGE);

        	                // Reset UIManager properties to default
        	                UIManager.put("OptionPane.messageFont", UIManager.getDefaults().getFont("OptionPane.messageFont"));
        	                UIManager.put("OptionPane.buttonFont", UIManager.getDefaults().getFont("OptionPane.buttonFont"));
            				
            			}
            			

            			else {
            				Font customFont = new Font("Arial", Font.PLAIN, 16);
        	                UIManager.put("OptionPane.messageFont", customFont);
        	                UIManager.put("OptionPane.buttonFont", customFont);
        	                
        	                JOptionPane.showMessageDialog(getRootPane(), "Name not found.",
        	                        "Note", JOptionPane.INFORMATION_MESSAGE);

        	                // Reset UIManager properties to default
        	                UIManager.put("OptionPane.messageFont", UIManager.getDefaults().getFont("OptionPane.messageFont"));
        	                UIManager.put("OptionPane.buttonFont", UIManager.getDefaults().getFont("OptionPane.buttonFont"));
            				return;
            			}
            		}
            		
            		
            	} else {
            		Font customFont = new Font("Arial", Font.PLAIN, 16);
	                UIManager.put("OptionPane.messageFont", customFont);
	                UIManager.put("OptionPane.buttonFont", customFont);
	                
	                JOptionPane.showMessageDialog(getRootPane(), "Invalid user ID format",
	                        "Note", JOptionPane.INFORMATION_MESSAGE);

	                // Reset UIManager properties to default
	                UIManager.put("OptionPane.messageFont", UIManager.getDefaults().getFont("OptionPane.messageFont"));
	                UIManager.put("OptionPane.buttonFont", UIManager.getDefaults().getFont("OptionPane.buttonFont"));
            	}
            	
            }                                             
            fetchAndDisplayData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void checkout() {
    	if (selectedBooks.isEmpty()) {
    		Font customFont = new Font("Arial", Font.PLAIN, 16);
            UIManager.put("OptionPane.messageFont", customFont);
            UIManager.put("OptionPane.buttonFont", customFont);
            
            JOptionPane.showMessageDialog(getRootPane(), "Select a book first",
                    "Checkout Error", JOptionPane.ERROR_MESSAGE);

            // Reset UIManager properties to default
            UIManager.put("OptionPane.messageFont", UIManager.getDefaults().getFont("OptionPane.messageFont"));
            UIManager.put("OptionPane.buttonFont", UIManager.getDefaults().getFont("OptionPane.buttonFont"));
            return; // Do not proceed with the transaction
        }
        // Iterate through the ArrayList and insert records into the database
        for (Book selectedBook : selectedBooks) {
        	
            insertTransaction(selectedBook.getBookNum(), selectedBook.getTitle(), selectedBook.getAccessionNum(), selectedBook.getBorrowerName(), selectedBook.getUserID(), selectedBook.getUserID());
        }

        activeUserId = null;
        // Clear the ArrayList after checking out
        selectedBooks.clear();
    }

    private int showConfirmDialog(String borrowerName, String gradeLevel, String section, String title, int accessionNum) {
        String message = "Confirm borrowing:\n\n"
        		+ "Title: " + title + "\n"
        		+ "Accession Number: " + accessionNum + "\n"
                + "Borrower: " + borrowerName + "\n"
                + "Grade Level: " + gradeLevel + "\n"
                + "Section: " + section + "\n\n"                
                + "Proceed with the transaction?";
        Font customFont = new Font("Arial", Font.PLAIN, 16);
        UIManager.put("OptionPane.messageFont", customFont);
        UIManager.put("OptionPane.buttonFont", customFont);
                
        // Reset UIManager properties to default
        UIManager.put("OptionPane.messageFont", UIManager.getDefaults().getFont("OptionPane.messageFont"));
        UIManager.put("OptionPane.buttonFont", UIManager.getDefaults().getFont("OptionPane.buttonFont"));
        
        
        return JOptionPane.showConfirmDialog(getRootPane(), message,
                "Confirmation", JOptionPane.YES_NO_OPTION);
    }
    

    private void insertTransaction(String bn, String tl, int acc, String borrowerName, String id, String userTypee) {
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
            		
            		String userType = getUserType(id);
            		
    		        
    		        // Print or use the formattedDate as needed
    		        //System.out.println("Chosen Date: " + formattedDate);
    		        
            		if ("Faculty".equals(userType) || "Staff".equals(userType)) {                  
            			String insertSql = "INSERT INTO Transactions (BooNum, Title, AccessionNum, Borrower, BookStatus, transaction_date, return_date, user_id, user_type) " +
            					"VALUES (?, ?, ?, ?, 'Borrowed', CURRENT_DATE(), 'IND', ?, ?)";
            			
            			try (PreparedStatement pstmt = conn.prepareStatement(insertSql)) {
            				pstmt.setString(1, bn);
            				pstmt.setString(2, tl);
            				pstmt.setInt(3, acc);
            				pstmt.setString(4, borrowerName);         				
            				pstmt.setString(5, id);
            				pstmt.setString(6, userType);
            				
            				
            				// Execute the update
            				int rowsAffected = pstmt.executeUpdate();
            				
            				txtTitle.setText("");
            				txtBookNum.setText("");
            				txtAuthor.setText("");
            				txtTitle.setText("");
            				cbAccession.removeAllItems();
            				txtStatus.setText("");
            				txtBorrID.setText("");
            				JOptionPane.showMessageDialog(getRootPane(), "Transaction Recorded!");
            				
            				
            			} catch (SQLException e) {
            				e.printStackTrace();
            			}
            			
            			
            		} else {            			        			
            			// If user ID does not start with "1", set return date to three days from the transaction date
            			String insertSql = "INSERT INTO Transactions (BooNum, Title, AccessionNum, Borrower, BookStatus, transaction_date, return_date, user_id, user_type) " +
            					"VALUES (?, ?, ?, ?, 'Borrowed', CURRENT_DATE(), ShiftHolidayToWorkday(DATE_ADD(CURDATE(), INTERVAL 3 DAY), YEAR(CURDATE())), ?, ?)";
            			
            			
            			try (PreparedStatement pstmt = conn.prepareStatement(insertSql)) {
            				pstmt.setString(1, bn);
            				pstmt.setString(2, tl);
            				pstmt.setInt(3, acc);
            				pstmt.setString(4, borrowerName);
            				pstmt.setString(5, id);
            				pstmt.setString(6, userType);
            				
            				// Execute the update
            				int rowsAffected = pstmt.executeUpdate();
            				
            				txtTitle.setText("");
            				txtBookNum.setText("");
            				txtAuthor.setText("");
            				txtTitle.setText("");
            				cbAccession.removeAllItems();
            				txtStatus.setText("");
            				txtBorrID.setText("");           
            				
            				// Fetch the return date for the confirmation message
            		        String fetchReturnDateSql = "SELECT ShiftHolidayToWorkday(DATE_ADD(CURDATE(), INTERVAL 3 DAY), YEAR(CURDATE())) AS return_date";
            		        try (PreparedStatement fetchReturnDateStmt = conn.prepareStatement(fetchReturnDateSql);
            		             ResultSet rs = fetchReturnDateStmt.executeQuery()) {

            		            if (rs.next()) {
            		            	// Parse the return date from the database
            		                LocalDate returnDate = rs.getDate("return_date").toLocalDate();
            		                
            		                Font customFont = new Font("Arial", Font.PLAIN, 16);
            		                UIManager.put("OptionPane.messageFont", customFont);
            		                UIManager.put("OptionPane.buttonFont", customFont);
            		                
            		                // Format the return date to "MMM d, yyyy" (e.g., Jan 9, 2023)
            		                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d, yyyy");
            		                String formattedReturnDate = returnDate.format(formatter);
            		                JOptionPane.showMessageDialog(getRootPane(), "Transaction Recorded!\nReturn Date: " + formattedReturnDate,
            		                        "Success", JOptionPane.INFORMATION_MESSAGE);
            		                
            		                // Reset UIManager properties to default
            		                UIManager.put("OptionPane.messageFont", UIManager.getDefaults().getFont("OptionPane.messageFont"));
            		                UIManager.put("OptionPane.buttonFont", UIManager.getDefaults().getFont("OptionPane.buttonFont"));
            		            } else {
            		                // Handle the case where the return date couldn't be fetched
            		                JOptionPane.showMessageDialog(getRootPane(), "Transaction Recorded! Return Date: N/A");
            		                
            		            }
            		        }
            		        
            		        
            				   
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
        
     // Fetch data from Transactions table
        String fetchTransactionDataSql = "SELECT transaction_id, return_date FROM Transactions WHERE BooNum = ? AND AccessionNum = ?";
        try (PreparedStatement fetchTransactionDataStmt = conn.prepareStatement(fetchTransactionDataSql)) {
            fetchTransactionDataStmt.setString(1, bn);
            fetchTransactionDataStmt.setInt(2, acc);

            try (ResultSet transactionDataResult = fetchTransactionDataStmt.executeQuery()) {
                if (transactionDataResult.next()) {
                    String transactionId = String.valueOf(transactionDataResult.getInt("transaction_id"));
                    String returnDate = transactionDataResult.getString("return_date");

                    // Insert into Borrowed table
                    String insertBorrowedSql = "INSERT INTO Borrowed (transaction_id, book_num, title, accession, date_borrowed, due_date, borrower_name, user_id) " +
                            "VALUES (?, ?, ?, ?, CURRENT_DATE(), ?, ?, ?)";

                    try (PreparedStatement insertBorrowedStmt = conn.prepareStatement(insertBorrowedSql)) {
                        insertBorrowedStmt.setString(1, transactionId);
                        insertBorrowedStmt.setString(2, bn);
                        insertBorrowedStmt.setString(3, tl);
                        insertBorrowedStmt.setInt(4, acc);
                        insertBorrowedStmt.setString(5, returnDate);
                        insertBorrowedStmt.setString(6, borrowerName);
                        insertBorrowedStmt.setString(7, id);

                        // Execute the update
                        int borrowedRowsAffected = insertBorrowedStmt.executeUpdate();
                        JOptionPane.showMessageDialog(getRootPane(), "Borrow Recorded!");
                        // Your existing code...
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }	
                } else {
                    JOptionPane.showMessageDialog(getRootPane(), "Transaction data not found for the book");
                }
            }
        }
     catch (SQLException e) {
        e.printStackTrace();
    
        }
    }  
    //check if the user is blocked from borrowing
    private boolean blockedFromBorrwing(String userId) {
        boolean alreadyBorrowed = false;

        String checkSql = "SELECT * FROM Blocked WHERE user_id = ?";
        try (PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {
            checkStmt.setString(1, userId);
            ResultSet checkResult = checkStmt.executeQuery();

            alreadyBorrowed = checkResult.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return alreadyBorrowed;
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
	        // Load the JDBC driver (version 4.0 or later)
	        try {
	            Class.forName("com.mysql.jdbc.Driver");
	        } catch (ClassNotFoundException e1) {
	            e1.printStackTrace();
	        }

	        try {
	            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/BooksDB", "root", "ranielle25");
	            Statement stmt = conn.createStatement();
	            System.out.println("Connected");

	            String partialTitle = txtTitle.getText();
	            String sql = "SELECT * FROM Books WHERE Title LIKE ? AND book_status = 'Available' AND Subject != 'Others'";
	            PreparedStatement pstmt = conn.prepareStatement(sql);
	            
	            // Use % as a wildcard for partial matches
	            pstmt.setString(1, "%" + partialTitle + "%");

	            ResultSet rs = pstmt.executeQuery();

	            DefaultTableModel tblModel = (DefaultTableModel) tblTransac.getModel();

	            DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();

	            if (!rs.isBeforeFirst()) {
	            	Font customFont = new Font("Arial", Font.PLAIN, 16);
	                UIManager.put("OptionPane.messageFont", customFont);
	                UIManager.put("OptionPane.buttonFont", customFont);
	                
	                JOptionPane.showMessageDialog(getRootPane(), "No matching books found. ",
	                        "Note", JOptionPane.INFORMATION_MESSAGE);
	                	
	                // Reset UIManager properties to default
	                UIManager.put("OptionPane.messageFont", UIManager.getDefaults().getFont("OptionPane.messageFont"));
	                UIManager.put("OptionPane.buttonFont", UIManager.getDefaults().getFont("OptionPane.buttonFont"));
	                txtBookNum.setText("");
	                txtTitle.setText("");
	                txtAuthor.setText("");
                    cbAccession.removeAllItems();
	            } else {
	                // Clear existing rows in the table
	                tblModel.setRowCount(0);
	                while (rs.next()) {
	                    //add data until there is none
	                    String bookNum = rs.getString("Book_Num");
	                    String title = rs.getString("Title");
	                    String author = rs.getString("Author");
	                    String isbn = rs.getString("isbn");
	                    String publisher = rs.getString("Publisher");
	                    String language = rs.getString("Language");
	                    String subject = rs.getString("Subject");
	                    String quantity = String.valueOf(rs.getInt("Quantity"));
	                    String dewey = rs.getString("Dewey_Decimal");
	                    String accession = String.valueOf(rs.getInt("Accession_Num"));
	                    String status = rs.getString("book_status");
	                    String dateRegistered = rs.getString("date_registered");

	                    comboBoxModel.addElement(accession);

	                    txtTitle.setText(title);
                        txtAuthor.setText(author);
                        txtBookNum.setText(bookNum);
                        cbAccession.setModel(comboBoxModel);
                        txtStatus.setText(status);

	                    //array to store data into jtable
	                    String tbData[] = {bookNum, title, author, isbn, publisher,
	                            language, subject, dewey, accession, status, dateRegistered};

	                    //add string array data to jtable
	                    tblModel.addRow(tbData);
	                }
	            }

	        } catch (SQLException e1) {
	            e1.printStackTrace();
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
	                
	                /*CHANGE ROW COLOR
	                 * STAFF/FACULTY - BLUE
	                 * STUDENT - WHITE*/
	                tblTransac.setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
		    		    @Override
		    		    public Component getTableCellRendererComponent(JTable table,
		    		            Object value, boolean isSelected, boolean hasFocus, int row, int col) {

		    		        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);

		    		        String status = (String)table.getModel().getValueAt(row, 9);
		    		        if ("Student".equals(status)) {
		    		        	setBackground(table.getBackground());
		    		            setForeground(table.getForeground());
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
	
	public void viewBooks() {
		try {
            // Load the JDBC driver (version 4.0 or later)
            Class.forName("com.mysql.jdbc.Driver");

            // Establish a connection
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/BooksDB", "root", "ranielle25");
            System.out.println("Connected");

            // Fetch data from Transactions table
            String selectTransactionsSql = "SELECT * FROM Books WHERE book_status = 'Available' AND Subject != 'Others'";
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
    				String dewey = rs.getString("Dewey_Decimal");
    				String accession = String.valueOf(rs.getInt("Accession_Num"));
    				String status = rs.getString("book_status");
    				String dateRegistered = rs.getString("date_registered");
    				
    				//array to store data into jtable
    				String tbData[] = {bookNum, title, author, isbn, publisher,
    						language, subject, dewey, accession, status, dateRegistered};
    				
                    // add string array data to jtable
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
}
