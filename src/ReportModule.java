import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JTable;

import GradientBackground.gradientBackground;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

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
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.awt.event.ActionEvent;

public class ReportModule extends JPanel {
	private JPanel panel;	
	private JPanel panel_2;
	Connection conn;
	PreparedStatement pst;
	ResultSet rs;
	/**
	 * Create the panel.
	 */
	public ReportModule() {
		setPreferredSize(new Dimension(1425, 980));
	    setLayout(null);
		setBounds(100, 100, 1425, 980);
		
		panel = new gradientBackground();
		panel.setBackground(new Color(0, 153, 255));
        panel.setBounds(0, 0, 1425, 980);
        add(panel);
        panel.setLayout(null);
        
        JPanel panel_1 = new JPanel();
        panel_2 = new gradientBackground();

        panel_2.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel_2.setBounds(80, 95, 505, 234);
        panel.add(panel_2);
        panel_2.setLayout(null);
        
        JButton btnBooksAll = new JButton("Generate Report of All Books");
        btnBooksAll.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		testPdfBooks();
        	}
        });
        btnBooksAll.setBounds(49, 48, 382, 30);
        panel_2.add(btnBooksAll);
        btnBooksAll.setForeground(Color.WHITE);
        btnBooksAll.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnBooksAll.setBorderPainted(false);
        btnBooksAll.setBackground(new Color(0, 100, 0));
        
        JButton btnGenerateReportOf = new JButton("Generate Report of Books Based on Status");
        btnGenerateReportOf.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                String selectedStatus = (String) JOptionPane.showInputDialog(
                        getRootPane(),
                        "Choose status:",
                        "Status Selection",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        getStatusArray(),  // You need to implement getSubjectsArray() to provide subject options
                        null
                );

                if (selectedStatus != null) {
                	testPdfByStatus(selectedStatus);
                }

        	}
        });
        btnGenerateReportOf.setForeground(Color.WHITE);
        btnGenerateReportOf.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnGenerateReportOf.setBorderPainted(false);
        btnGenerateReportOf.setBackground(new Color(0, 100, 0));
        btnGenerateReportOf.setBounds(49, 89, 382, 30);
        panel_2.add(btnGenerateReportOf);
        
        JButton btnGenerateReportOf_1 = new JButton("Generate Report of Books Based on Subject");
        btnGenerateReportOf_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String selectedSubject = (String) JOptionPane.showInputDialog(
                        getRootPane(),
                        "Choose subject:",
                        "Subject Selection",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        getSubjectsArray(),  // You need to implement getSubjectsArray() to provide subject options
                        null
                );

                if (selectedSubject != null) {
                	testPdfBySub(selectedSubject);
                }
        	}
        });
        btnGenerateReportOf_1.setForeground(Color.WHITE);
        btnGenerateReportOf_1.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnGenerateReportOf_1.setBorderPainted(false);
        btnGenerateReportOf_1.setBackground(new Color(0, 100, 0));
        btnGenerateReportOf_1.setBounds(49, 130, 382, 30);
        panel_2.add(btnGenerateReportOf_1);
        
        JLabel lblBooks = new JLabel("Books");
        lblBooks.setBounds(89, 59, 99, 30);
        panel.add(lblBooks);
        lblBooks.setForeground(Color.WHITE);
        lblBooks.setFont(new Font("Verdana", Font.BOLD, 17));
        lblBooks.setBackground(new Color(89, 89, 89));
        
        gradientBackground panel_1_1 = new gradientBackground();
        panel_1_1.setLayout(null);
        panel_1_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel_1_1.setBounds(80, 411, 505, 234);
        panel.add(panel_1_1);
        
        JButton btnExport_1 = new JButton("Generate Borrowed Books Report");
        btnExport_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		testPdfBorrowed();
        	}
        });
        btnExport_1.setForeground(Color.WHITE);
        btnExport_1.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnExport_1.setBorderPainted(false);
        btnExport_1.setBackground(new Color(0, 100, 0));
        btnExport_1.setBounds(63, 82, 355, 30);
        panel_1_1.add(btnExport_1);
        
        JButton btnExport_1_1 = new JButton("Confirmed Payment Reports");
        btnExport_1_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		testPdfPaid();
        	}
        });
        btnExport_1_1.setForeground(Color.WHITE);
        btnExport_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnExport_1_1.setBorderPainted(false);
        btnExport_1_1.setBackground(new Color(0, 100, 0));
        btnExport_1_1.setBounds(63, 41, 355, 30);
        panel_1_1.add(btnExport_1_1);
        
        JButton btnExport_1_2 = new JButton("Generate Returned Books Report");
        btnExport_1_2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		testPdfReturned();
        	}
        });
        btnExport_1_2.setForeground(Color.WHITE);
        btnExport_1_2.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnExport_1_2.setBorderPainted(false);
        btnExport_1_2.setBackground(new Color(0, 100, 0));
        btnExport_1_2.setBounds(63, 123, 355, 30);
        panel_1_1.add(btnExport_1_2);
        
        JLabel lblTransactions = new JLabel("Transactions");
        lblTransactions.setForeground(Color.WHITE);
        lblTransactions.setFont(new Font("Verdana", Font.BOLD, 17));
        lblTransactions.setBackground(new Color(89, 89, 89));
        lblTransactions.setBounds(89, 375, 140, 30);
        panel.add(lblTransactions);
        
        gradientBackground panel_1_1_1 = new gradientBackground();
        panel_1_1_1.setLayout(null);
        panel_1_1_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel_1_1_1.setBounds(702, 95, 505, 234);
        panel.add(panel_1_1_1);
        
        JButton btnStudentReport = new JButton("Generate Student Report");
        btnStudentReport.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		testPdfStudents();
        	}
        });
        btnStudentReport.setForeground(Color.WHITE);
        btnStudentReport.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnStudentReport.setBorderPainted(false);
        btnStudentReport.setBackground(new Color(0, 100, 0));
        btnStudentReport.setBounds(110, 82, 259, 30);
        panel_1_1_1.add(btnStudentReport);
        
        JButton btnEmployeeReport = new JButton("Generate Employee Report");
        btnEmployeeReport.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		testPdfEmp();
        	}
        });
        btnEmployeeReport.setForeground(Color.WHITE);
        btnEmployeeReport.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnEmployeeReport.setBorderPainted(false);
        btnEmployeeReport.setBackground(new Color(0, 100, 0));
        btnEmployeeReport.setBounds(110, 123, 259, 30);
        panel_1_1_1.add(btnEmployeeReport);
        
        JLabel lblUsers = new JLabel("Users");
        lblUsers.setForeground(Color.WHITE);
        lblUsers.setFont(new Font("Verdana", Font.BOLD, 17));
        lblUsers.setBackground(new Color(89, 89, 89));
        lblUsers.setBounds(711, 59, 140, 30);
        panel.add(lblUsers);
        setConnections();
	}
	//get subjects for choices
		private String[] getSubjectsArray() {
		    // Replace this with your actual list of subjects
		    String[] subjects = {"General Information", "Philosophy & Psychology", "Religion", "Social Sciences",
		                         "Language", "Science", "Technology", "Arts & Recreation", "Literature", "History & Geography"};

		    return subjects;
		}
		
	//get subjects for choices
		private String[] getStatusArray() {
		    // Replace this with your actual list of subjects
		    String[] status = {"Available", "Damaged", "Lost"};

		    return status;
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
		public void setConnections() {
			try {
	            // Load the JDBC driver (version 4.0 or later)
	            Class.forName("com.mysql.jdbc.Driver");

	            // Establish a connection
	            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/BooksDB", "root", "ranielle25");
	            System.out.println("Connected");
          
	                
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		}
	public void testPdfBooks() {
		
		// Fetch data from the database
        try {
			pst = conn.prepareStatement("SELECT * FROM Books");
			rs = pst.executeQuery();
			
			Document PDFReport = new Document();
			    
		    
			try {
				// Set the page size to landscape
	            PDFReport.setPageSize(PageSize.A3.rotate());
	         // Inside testPdf and testPdfBySub methods
	            String outputFileName = getUniqueFileName("C:\\Users\\LINDELL\\Desktop\\OutputReport.pdf");
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
	            Paragraph title2 = new Paragraph("List of All Books");
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
	            
				PdfPTable PDFTable = new PdfPTable(11);
				
				 // Add table header
	            String[] headers = {"Book Num", "Title", "Author", "ISBN", "Publisher", "Language", "Subject",
	                                "Dewey Decimal", "Accession Num", "Status", "Date Registered"};
	            
	            
	            int totalBooks = 0;
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
					String bookNum = rs.getString("Book_Num");
					table_cell = new PdfPCell(new Phrase(bookNum));
					PDFTable.addCell(table_cell);
					String title = rs.getString("Title");
					table_cell = new PdfPCell(new Phrase(title));
					PDFTable.addCell(table_cell);
					String author = rs.getString("Author");
					table_cell = new PdfPCell(new Phrase(author));
					PDFTable.addCell(table_cell);
					String isbn = rs.getString("isbn");
					table_cell = new PdfPCell(new Phrase(isbn));
					PDFTable.addCell(table_cell);
					String publisher = rs.getString("Publisher");
					table_cell = new PdfPCell(new Phrase(publisher));
					PDFTable.addCell(table_cell);
					String language = rs.getString("Language");
					table_cell = new PdfPCell(new Phrase(language));
					PDFTable.addCell(table_cell);
					String subject = rs.getString("Subject");
					table_cell = new PdfPCell(new Phrase(subject));
					PDFTable.addCell(table_cell);
					String dewey = String.valueOf(rs.getString("Dewey_Decimal"));
					table_cell = new PdfPCell(new Phrase(dewey));
					PDFTable.addCell(table_cell);
					String accession = String.valueOf(rs.getInt("Accession_Num"));
					table_cell = new PdfPCell(new Phrase(accession));
					PDFTable.addCell(table_cell);
					String status = rs.getString("book_status");
					table_cell = new PdfPCell(new Phrase(status));
					PDFTable.addCell(table_cell);
					String dateRegistered = rs.getString("date_registered");
					table_cell = new PdfPCell(new Phrase(dateRegistered));
					PDFTable.addCell(table_cell);
					
					totalBooks++;
				}
				
										
			
				PDFReport.add(PDFTable);
				// Add total number of books
				Paragraph line = new Paragraph("\n");
				line.setAlignment(Element.ALIGN_RIGHT);
				PDFReport.add(line);
				
				// Add total number of books
				Paragraph totalBooksParagraph = new Paragraph("Total Books: " + totalBooks, customFont2);
				totalBooksParagraph.setAlignment(Element.ALIGN_LEFT);
				totalBooksParagraph.setIndentationLeft(125);
				PDFReport.add(totalBooksParagraph);

				// Add who generated the report
				String userType = MainMenuFrame.getUser();
				String positionz = "";
				
				
				if ("Librarian".equalsIgnoreCase(userType)) {
				    preparedBy = "Hilda V. Tiongco";
				    positionz += "School Librarian";
				    	
					
				 // Create a Paragraph for the generated by information
				    Paragraph generatedBy = new Paragraph();

				    // Add "Prepared by: " text without underline
				    Chunk preparedByText = new Chunk("Prepared by: ", customFont2);
				    generatedBy.add(preparedByText);

				    // Add underlined name
				    Chunk nameChunk = new Chunk(preparedBy, customFont2);
				    nameChunk.setUnderline(1.5f, -1);  // Adjust the values for underline thickness and position
				    generatedBy.add(nameChunk);

				    generatedBy.add(Chunk.NEWLINE);

				    // Add indentation for the school position
				    Paragraph position = new Paragraph(positionz, customFont2);
				    position.setIndentationLeft(95);  // Adjust the indentation as needed
				    generatedBy.add(position);

				    // Add the generated by information to the PDF
				    generatedBy.setIndentationLeft(125);
				    PDFReport.add(generatedBy);
				} else if ("Admin".equalsIgnoreCase(userType)) {
				    preparedBy = "Ronan Santos";
				    positionz += "IT Administrator";
				 // Create a Paragraph for the generated by information
				    Paragraph generatedBy = new Paragraph();

				    // Add "Prepared by: " text without underline
				    Chunk preparedByText = new Chunk("Prepared by: ", customFont2);
				    generatedBy.add(preparedByText);

				    // Add underlined name
				    Chunk nameChunk = new Chunk(preparedBy, customFont2);
				    nameChunk.setUnderline(1.5f, -1);  // Adjust the values for underline thickness and position
				    generatedBy.add(nameChunk);

				    generatedBy.add(Chunk.NEWLINE);

				    // Add indentation for the school position
				    Paragraph position = new Paragraph(positionz, customFont2);
				    position.setIndentationLeft(98);  // Adjust the indentation as needed
				    generatedBy.add(position);

				    // Add the generated by information to the PDF
				    generatedBy.setIndentationLeft(125);
				    PDFReport.add(generatedBy);
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
	
	public void testPdfBySub(String getSubject) {
		// Fetch data from the database
        try {
			
			// Fetch data from the database
	        pst = conn.prepareStatement("SELECT * FROM Books WHERE Subject = ? ");
	        pst.setString(1, getSubject);
	        rs = pst.executeQuery();
	        
			Document PDFReport = new Document();
			    
		    
			try {
				// Set the page size to landscape
	            PDFReport.setPageSize(PageSize.A3.rotate());
	         // For testPdfBySub method where the file name includes the subject
	            String outputFileName = getUniqueFileName("C:\\Users\\LINDELL\\Desktop\\OutputReport_" + getSubject + "_.pdf");
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
	            Paragraph title2 = new Paragraph("List of All Books in " + getSubject);
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
	            
				PdfPTable PDFTable = new PdfPTable(11);
				
				 // Add table header
	            String[] headers = {"Book Num", "Title", "Author", "ISBN", "Publisher", "Language", "Subject",
	                                "Dewey Decimal", "Accession Num", "Status", "Date Registered"};
	            
	            
	            int totalBooks = 0;
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
					String bookNum = rs.getString("Book_Num");
					table_cell = new PdfPCell(new Phrase(bookNum));
					PDFTable.addCell(table_cell);
					String title = rs.getString("Title");
					table_cell = new PdfPCell(new Phrase(title));
					PDFTable.addCell(table_cell);
					String author = rs.getString("Author");
					table_cell = new PdfPCell(new Phrase(author));
					PDFTable.addCell(table_cell);
					String isbn = rs.getString("isbn");
					table_cell = new PdfPCell(new Phrase(isbn));
					PDFTable.addCell(table_cell);
					String publisher = rs.getString("Publisher");
					table_cell = new PdfPCell(new Phrase(publisher));
					PDFTable.addCell(table_cell);
					String language = rs.getString("Language");
					table_cell = new PdfPCell(new Phrase(language));
					PDFTable.addCell(table_cell);
					String subject = rs.getString("Subject");
					table_cell = new PdfPCell(new Phrase(subject));
					PDFTable.addCell(table_cell);
					String dewey = String.valueOf(rs.getString("Dewey_Decimal"));
					table_cell = new PdfPCell(new Phrase(dewey));
					PDFTable.addCell(table_cell);
					String accession = String.valueOf(rs.getInt("Accession_Num"));
					table_cell = new PdfPCell(new Phrase(accession));
					PDFTable.addCell(table_cell);
					String status = rs.getString("book_status");
					table_cell = new PdfPCell(new Phrase(status));
					PDFTable.addCell(table_cell);
					String dateRegistered = rs.getString("date_registered");
					table_cell = new PdfPCell(new Phrase(dateRegistered));
					PDFTable.addCell(table_cell);
					
					totalBooks++;
				}
				
										
			
				PDFReport.add(PDFTable);
				// Add total number of books
				Paragraph line = new Paragraph("\n");
				line.setAlignment(Element.ALIGN_RIGHT);
				PDFReport.add(line);
				
				// Add total number of books
				Paragraph totalBooksParagraph = new Paragraph("Total Books: " + totalBooks, customFont2);
				totalBooksParagraph.setAlignment(Element.ALIGN_LEFT);
				totalBooksParagraph.setIndentationLeft(125);
				PDFReport.add(totalBooksParagraph);

				// Add who generated the report
				String userType = MainMenuFrame.getUser();
				String positionz = "";
				
				
				if ("Librarian".equalsIgnoreCase(userType)) {
				    preparedBy = "Hilda V. Tiongco";
				    positionz += "School Librarian";
				    	
					
				 // Create a Paragraph for the generated by information
				    Paragraph generatedBy = new Paragraph();

				    // Add "Prepared by: " text without underline
				    Chunk preparedByText = new Chunk("Prepared by: ", customFont2);
				    generatedBy.add(preparedByText);

				    // Add underlined name
				    Chunk nameChunk = new Chunk(preparedBy, customFont2);
				    nameChunk.setUnderline(1.5f, -1);  // Adjust the values for underline thickness and position
				    generatedBy.add(nameChunk);

				    generatedBy.add(Chunk.NEWLINE);

				    // Add indentation for the school position
				    Paragraph position = new Paragraph(positionz, customFont2);
				    position.setIndentationLeft(95);  // Adjust the indentation as needed
				    generatedBy.add(position);

				    // Add the generated by information to the PDF
				    generatedBy.setIndentationLeft(125);
				    PDFReport.add(generatedBy);
				} else if ("Admin".equalsIgnoreCase(userType)) {
				    preparedBy = "Ronan Santos";
				    positionz += "IT Administrator";
				 // Create a Paragraph for the generated by information
				    Paragraph generatedBy = new Paragraph();

				    // Add "Prepared by: " text without underline
				    Chunk preparedByText = new Chunk("Prepared by: ", customFont2);
				    generatedBy.add(preparedByText);

				    // Add underlined name
				    Chunk nameChunk = new Chunk(preparedBy, customFont2);
				    nameChunk.setUnderline(1.5f, -1);  // Adjust the values for underline thickness and position
				    generatedBy.add(nameChunk);

				    generatedBy.add(Chunk.NEWLINE);

				    // Add indentation for the school position
				    Paragraph position = new Paragraph(positionz, customFont2);
				    position.setIndentationLeft(98);  // Adjust the indentation as needed
				    generatedBy.add(position);

				    // Add the generated by information to the PDF
				    generatedBy.setIndentationLeft(125);
				    PDFReport.add(generatedBy);
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
	
	public void testPdfByStatus(String getStatus) {
		// Fetch data from the database
        try {
			
			// Fetch data from the database
	        pst = conn.prepareStatement("SELECT * FROM Books WHERE book_status = ? ");
	        pst.setString(1, getStatus);
	        rs = pst.executeQuery();
	        
			Document PDFReport = new Document();
			    
		    
			try {
				// Set the page size to landscape
	            PDFReport.setPageSize(PageSize.A3.rotate());
	         // For testPdfBySub method where the file name includes the subject
	            String outputFileName = getUniqueFileName("C:\\Users\\LINDELL\\Desktop\\OutputReport_" + getStatus + "_.pdf");
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
	            Paragraph title2 = new Paragraph("List of All " + getStatus + " Books");
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
	            
				PdfPTable PDFTable = new PdfPTable(11);
				
				 // Add table header
	            String[] headers = {"Book Num", "Title", "Author", "ISBN", "Publisher", "Language", "Subject",
	                                "Dewey Decimal", "Accession Num", "Status", "Date Registered"};
	            
	            
	            int totalBooks = 0;
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
					String bookNum = rs.getString("Book_Num");
					table_cell = new PdfPCell(new Phrase(bookNum));
					PDFTable.addCell(table_cell);
					String title = rs.getString("Title");
					table_cell = new PdfPCell(new Phrase(title));
					PDFTable.addCell(table_cell);
					String author = rs.getString("Author");
					table_cell = new PdfPCell(new Phrase(author));
					PDFTable.addCell(table_cell);
					String isbn = rs.getString("isbn");
					table_cell = new PdfPCell(new Phrase(isbn));
					PDFTable.addCell(table_cell);
					String publisher = rs.getString("Publisher");
					table_cell = new PdfPCell(new Phrase(publisher));
					PDFTable.addCell(table_cell);
					String language = rs.getString("Language");
					table_cell = new PdfPCell(new Phrase(language));
					PDFTable.addCell(table_cell);
					String subject = rs.getString("Subject");
					table_cell = new PdfPCell(new Phrase(subject));
					PDFTable.addCell(table_cell);
					String dewey = String.valueOf(rs.getDouble("Dewey_Decimal"));
					table_cell = new PdfPCell(new Phrase(dewey));
					PDFTable.addCell(table_cell);
					String accession = String.valueOf(rs.getInt("Accession_Num"));
					table_cell = new PdfPCell(new Phrase(accession));
					PDFTable.addCell(table_cell);
					String status = rs.getString("book_status");
					table_cell = new PdfPCell(new Phrase(status));
					PDFTable.addCell(table_cell);
					String dateRegistered = rs.getString("date_registered");
					table_cell = new PdfPCell(new Phrase(dateRegistered));
					PDFTable.addCell(table_cell);
					
					totalBooks++;
				}
				
										
			
				PDFReport.add(PDFTable);
				// Add total number of books
				Paragraph line = new Paragraph("\n");
				line.setAlignment(Element.ALIGN_RIGHT);
				PDFReport.add(line);
				
				// Add total number of books
				Paragraph totalBooksParagraph = new Paragraph("Total Books: " + totalBooks, customFont2);
				totalBooksParagraph.setAlignment(Element.ALIGN_LEFT);
				totalBooksParagraph.setIndentationLeft(125);
				PDFReport.add(totalBooksParagraph);

				// Add who generated the report
				String userType = MainMenuFrame.getUser();
				String positionz = "";
				
				
				if ("Librarian".equalsIgnoreCase(userType)) {
				    preparedBy = "Hilda V. Tiongco";
				    positionz += "School Librarian";
				    	
					
				 // Create a Paragraph for the generated by information
				    Paragraph generatedBy = new Paragraph();

				    // Add "Prepared by: " text without underline
				    Chunk preparedByText = new Chunk("Prepared by: ", customFont2);
				    generatedBy.add(preparedByText);

				    // Add underlined name
				    Chunk nameChunk = new Chunk(preparedBy, customFont2);
				    nameChunk.setUnderline(1.5f, -1);  // Adjust the values for underline thickness and position
				    generatedBy.add(nameChunk);

				    generatedBy.add(Chunk.NEWLINE);

				    // Add indentation for the school position
				    Paragraph position = new Paragraph(positionz, customFont2);
				    position.setIndentationLeft(95);  // Adjust the indentation as needed
				    generatedBy.add(position);

				    // Add the generated by information to the PDF
				    generatedBy.setIndentationLeft(125);
				    PDFReport.add(generatedBy);
				} else if ("Admin".equalsIgnoreCase(userType)) {
				    preparedBy = "Ronan Santos";
				    positionz += "IT Administrator";
				 // Create a Paragraph for the generated by information
				    Paragraph generatedBy = new Paragraph();

				    // Add "Prepared by: " text without underline
				    Chunk preparedByText = new Chunk("Prepared by: ", customFont2);
				    generatedBy.add(preparedByText);

				    // Add underlined name
				    Chunk nameChunk = new Chunk(preparedBy, customFont2);
				    nameChunk.setUnderline(1.5f, -1);  // Adjust the values for underline thickness and position
				    generatedBy.add(nameChunk);

				    generatedBy.add(Chunk.NEWLINE);

				    // Add indentation for the school position
				    Paragraph position = new Paragraph(positionz, customFont2);
				    position.setIndentationLeft(98);  // Adjust the indentation as needed
				    generatedBy.add(position);

				    // Add the generated by information to the PDF
				    generatedBy.setIndentationLeft(125);
				    PDFReport.add(generatedBy);
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
	
	//Report for students
	public void testPdfStudents() {
		// Fetch data from the database
        try {
			pst = conn.prepareStatement("SELECT * FROM Students");
			rs = pst.executeQuery();
			
			Document PDFReport = new Document();
			    
		    
			try {
				// Set the page size to landscape
	            PDFReport.setPageSize(PageSize.A3.rotate());
	         // Inside testPdf and testPdfBySub methods
	            String outputFileName = getUniqueFileName("C:\\Users\\LINDELL\\Desktop\\StudentReport.pdf");
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
	            Paragraph title2 = new Paragraph("List of All Registered Students");
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
	            
				PdfPTable PDFTable = new PdfPTable(6);
				
				 // Add table header
	            String[] headers = {"Student Number", "Last Name", "First Name", "Middle Name", "Grade Level", "Section"};
	            
	            
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
					String studNum = rs.getString("StudentNo");
					table_cell = new PdfPCell(new Phrase(studNum));
					PDFTable.addCell(table_cell);
					String lastName = rs.getString("LastName");
					table_cell = new PdfPCell(new Phrase(lastName));
					PDFTable.addCell(table_cell);
					String firstName = rs.getString("FirstName");
					table_cell = new PdfPCell(new Phrase(firstName));
					PDFTable.addCell(table_cell);
					String middleName = rs.getString("MiddleName");
					table_cell = new PdfPCell(new Phrase(middleName));
					PDFTable.addCell(table_cell);
					String level = rs.getString("GradeLevel");
					table_cell = new PdfPCell(new Phrase(level));
					PDFTable.addCell(table_cell);
					String section = rs.getString("Section");
					table_cell = new PdfPCell(new Phrase(section));
					PDFTable.addCell(table_cell);						
					
					totalStudents++;
				}
				
										
			
				PDFReport.add(PDFTable);
				// Add total number of books
				Paragraph line = new Paragraph("\n");
				line.setAlignment(Element.ALIGN_RIGHT);
				PDFReport.add(line);
				
				// Add total number of books
				Paragraph totalBooksParagraph = new Paragraph("Total No. of Students Registered: " + totalStudents, customFont2);
				totalBooksParagraph.setAlignment(Element.ALIGN_LEFT);
				totalBooksParagraph.setIndentationLeft(125);
				PDFReport.add(totalBooksParagraph);

				// Add who generated the report
				String userType = MainMenuFrame.getUser();
				String positionz = "";
				
				
				if ("Librarian".equalsIgnoreCase(userType)) {
				    preparedBy = "Hilda V. Tiongco";
				    positionz += "School Librarian";
				    	
					
				 // Create a Paragraph for the generated by information
				    Paragraph generatedBy = new Paragraph();

				    // Add "Prepared by: " text without underline
				    Chunk preparedByText = new Chunk("Prepared by: ", customFont2);
				    generatedBy.add(preparedByText);

				    // Add underlined name
				    Chunk nameChunk = new Chunk(preparedBy, customFont2);
				    nameChunk.setUnderline(1.5f, -1);  // Adjust the values for underline thickness and position
				    generatedBy.add(nameChunk);

				    generatedBy.add(Chunk.NEWLINE);

				    // Add indentation for the school position
				    Paragraph position = new Paragraph(positionz, customFont2);
				    position.setIndentationLeft(95);  // Adjust the indentation as needed
				    generatedBy.add(position);

				    // Add the generated by information to the PDF
				    generatedBy.setIndentationLeft(125);
				    PDFReport.add(generatedBy);
				} else if ("Admin".equalsIgnoreCase(userType)) {
				    preparedBy = "Ronan Santos";
				    positionz += "IT Administrator";
				 // Create a Paragraph for the generated by information
				    Paragraph generatedBy = new Paragraph();

				    // Add "Prepared by: " text without underline
				    Chunk preparedByText = new Chunk("Prepared by: ", customFont2);
				    generatedBy.add(preparedByText);

				    // Add underlined name
				    Chunk nameChunk = new Chunk(preparedBy, customFont2);
				    nameChunk.setUnderline(1.5f, -1);  // Adjust the values for underline thickness and position
				    generatedBy.add(nameChunk);

				    generatedBy.add(Chunk.NEWLINE);

				    // Add indentation for the school position
				    Paragraph position = new Paragraph(positionz, customFont2);
				    position.setIndentationLeft(98);  // Adjust the indentation as needed
				    generatedBy.add(position);

				    // Add the generated by information to the PDF
				    generatedBy.setIndentationLeft(125);
				    PDFReport.add(generatedBy);
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
	
	//Report fro employee
	public void testPdfEmp() {
		// Fetch data from the database
        try {
			pst = conn.prepareStatement("SELECT * FROM Employees");
			rs = pst.executeQuery();
			
			Document PDFReport = new Document();
			    
		    
			try {
				// Set the page size to landscape
	            PDFReport.setPageSize(PageSize.A3.rotate());
	         // Inside testPdf and testPdfBySub methods
	            String outputFileName = getUniqueFileName("C:\\Users\\LINDELL\\Desktop\\EmployeeReport.pdf");
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
	            Paragraph title2 = new Paragraph("List of All Registered Employees");
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
	            
				PdfPTable PDFTable = new PdfPTable(7);
				
				 // Add table header
	            String[] headers = {"Employee ID", "Last Name", "First Name", "Middle Name", "Contact Number", "Email", "Employee Type"};
	            
	            
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
					String employeeID = rs.getString("employeeID");
					table_cell = new PdfPCell(new Phrase(employeeID));
					PDFTable.addCell(table_cell);
					String lastName = rs.getString("LastName");
					table_cell = new PdfPCell(new Phrase(lastName));
					PDFTable.addCell(table_cell);
					String firstName = rs.getString("FirstName");
					table_cell = new PdfPCell(new Phrase(firstName));
					PDFTable.addCell(table_cell);
					String middleName = rs.getString("MiddleName");
					table_cell = new PdfPCell(new Phrase(middleName));
					PDFTable.addCell(table_cell);
					String contact = rs.getString("ContactNo");
					table_cell = new PdfPCell(new Phrase(contact));
					PDFTable.addCell(table_cell);
					String email = rs.getString("email");
					table_cell = new PdfPCell(new Phrase(email));
					PDFTable.addCell(table_cell);
					String userType = rs.getString("UserType");
					table_cell = new PdfPCell(new Phrase(userType));
					PDFTable.addCell(table_cell);
					
					
					totalStudents++;
				}
				
										
			
				PDFReport.add(PDFTable);
				// Add total number of books
				Paragraph line = new Paragraph("\n");
				line.setAlignment(Element.ALIGN_RIGHT);
				PDFReport.add(line);
				
				// Add total number of books
				Paragraph totalBooksParagraph = new Paragraph("Total No. of Employees Registered: " + totalStudents, customFont2);
				totalBooksParagraph.setAlignment(Element.ALIGN_LEFT);
				totalBooksParagraph.setIndentationLeft(125);
				PDFReport.add(totalBooksParagraph);

				// Add who generated the report
				String userType = MainMenuFrame.getUser();
				String positionz = "";
				
				
				if ("Librarian".equalsIgnoreCase(userType)) {
				    preparedBy = "Hilda V. Tiongco";
				    positionz += "School Librarian";
				    	
					
				 // Create a Paragraph for the generated by information
				    Paragraph generatedBy = new Paragraph();

				    // Add "Prepared by: " text without underline
				    Chunk preparedByText = new Chunk("Prepared by: ", customFont2);
				    generatedBy.add(preparedByText);

				    // Add underlined name
				    Chunk nameChunk = new Chunk(preparedBy, customFont2);
				    nameChunk.setUnderline(1.5f, -1);  // Adjust the values for underline thickness and position
				    generatedBy.add(nameChunk);

				    generatedBy.add(Chunk.NEWLINE);

				    // Add indentation for the school position
				    Paragraph position = new Paragraph(positionz, customFont2);
				    position.setIndentationLeft(95);  // Adjust the indentation as needed
				    generatedBy.add(position);

				    // Add the generated by information to the PDF
				    generatedBy.setIndentationLeft(125);
				    PDFReport.add(generatedBy);
				} else if ("Admin".equalsIgnoreCase(userType)) {
				    preparedBy = "Ronan Santos";
				    positionz += "IT Administrator";
				 // Create a Paragraph for the generated by information
				    Paragraph generatedBy = new Paragraph();

				    // Add "Prepared by: " text without underline
				    Chunk preparedByText = new Chunk("Prepared by: ", customFont2);
				    generatedBy.add(preparedByText);

				    // Add underlined name
				    Chunk nameChunk = new Chunk(preparedBy, customFont2);
				    nameChunk.setUnderline(1.5f, -1);  // Adjust the values for underline thickness and position
				    generatedBy.add(nameChunk);

				    generatedBy.add(Chunk.NEWLINE);

				    // Add indentation for the school position
				    Paragraph position = new Paragraph(positionz, customFont2);
				    position.setIndentationLeft(98);  // Adjust the indentation as needed
				    generatedBy.add(position);

				    // Add the generated by information to the PDF
				    generatedBy.setIndentationLeft(125);
				    PDFReport.add(generatedBy);
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
	
	//paid Reports
	public void testPdfPaid() {
		// Fetch data from the database
        try {
			pst = conn.prepareStatement("SELECT * FROM Paid");
			rs = pst.executeQuery();
			
			Document PDFReport = new Document();
			    
		    
			try {
				// Set the page size to landscape
	            PDFReport.setPageSize(PageSize.A3.rotate());
	         // Inside testPdf and testPdfBySub methods
	            String outputFileName = getUniqueFileName("C:\\Users\\LINDELL\\Desktop\\PaidReport.pdf");
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
	            Paragraph title2 = new Paragraph("List of All Confirmed Payments");
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
	            
				PdfPTable PDFTable = new PdfPTable(4);
				
				 // Add table header
	            String[] headers = {"OR Code", "Borrower Name", "ID", "Amount Paid"};
	            
	            
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
				double totalAmountPaid = 0.0;

				while(rs.next()) {
					String orCode = rs.getString("or_code");
					table_cell = new PdfPCell(new Phrase(orCode));
					PDFTable.addCell(table_cell);
					String name = rs.getString("borrower_name");
					table_cell = new PdfPCell(new Phrase(name));
					PDFTable.addCell(table_cell);
					String id = rs.getString("user_id");
					table_cell = new PdfPCell(new Phrase(id));
					PDFTable.addCell(table_cell);
					String amountPaid = rs.getString("amount_paid");
					table_cell = new PdfPCell(new Phrase(amountPaid));
					PDFTable.addCell(table_cell);
					
					totalAmountPaid += Double.parseDouble(amountPaid);
					totalStudents++;
				}
				
				
			
				PDFReport.add(PDFTable);
				// Add total number of books
				Paragraph line = new Paragraph("\n");
				line.setAlignment(Element.ALIGN_RIGHT);
				PDFReport.add(line);
				
				// Add total number of books
				Paragraph totalBooksParagraph = new Paragraph("Total No. Confirmed Payments: "  + totalStudents, customFont2);
				totalBooksParagraph.setAlignment(Element.ALIGN_LEFT);
				totalBooksParagraph.setIndentationLeft(125);
				PDFReport.add(totalBooksParagraph);
				
				com.itextpdf.text.Font customFont3 = FontFactory.getFont("Noto Sans", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 15, Font.BOLD, BaseColor.BLACK);
				System.out.println("\u20b1");  
				Paragraph totalAmountPaidParagraph = new Paragraph("Total Amount Paid: " + "PHP" + totalAmountPaid, customFont3);
				totalAmountPaidParagraph.setAlignment(Element.ALIGN_LEFT);
				totalAmountPaidParagraph.setIndentationLeft(125);
				PDFReport.add(totalAmountPaidParagraph);
				
				// Add who generated the report
				String userType = MainMenuFrame.getUser();
				String positionz = "";
				
				
				if ("Librarian".equalsIgnoreCase(userType)) {
				    preparedBy = "Hilda V. Tiongco";
				    positionz += "School Librarian";
				    	
					
				 // Create a Paragraph for the generated by information
				    Paragraph generatedBy = new Paragraph();

				    // Add "Prepared by: " text without underline
				    Chunk preparedByText = new Chunk("Prepared by: ", customFont2);
				    generatedBy.add(preparedByText);

				    // Add underlined name
				    Chunk nameChunk = new Chunk(preparedBy, customFont2);
				    nameChunk.setUnderline(1.5f, -1);  // Adjust the values for underline thickness and position
				    generatedBy.add(nameChunk);

				    generatedBy.add(Chunk.NEWLINE);

				    // Add indentation for the school position
				    Paragraph position = new Paragraph(positionz, customFont2);
				    position.setIndentationLeft(95);  // Adjust the indentation as needed
				    generatedBy.add(position);

				    // Add the generated by information to the PDF
				    generatedBy.setIndentationLeft(125);
				    PDFReport.add(generatedBy);
				} else if ("Admin".equalsIgnoreCase(userType)) {
				    preparedBy = "Ronan Santos";
				    positionz += "IT Administrator";
				 // Create a Paragraph for the generated by information
				    Paragraph generatedBy = new Paragraph();

				    // Add "Prepared by: " text without underline
				    Chunk preparedByText = new Chunk("Prepared by: ", customFont2);
				    generatedBy.add(preparedByText);

				    // Add underlined name
				    Chunk nameChunk = new Chunk(preparedBy, customFont2);
				    nameChunk.setUnderline(1.5f, -1);  // Adjust the values for underline thickness and position
				    generatedBy.add(nameChunk);

				    generatedBy.add(Chunk.NEWLINE);

				    // Add indentation for the school position
				    Paragraph position = new Paragraph(positionz, customFont2);
				    position.setIndentationLeft(98);  // Adjust the indentation as needed
				    generatedBy.add(position);

				    // Add the generated by information to the PDF
				    generatedBy.setIndentationLeft(125);
				    PDFReport.add(generatedBy);
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
	
	//paid Reports
		public void testPdfBorrowed() {
			// Fetch data from the database
	        try {
				pst = conn.prepareStatement("SELECT * FROM Borrowed");
				rs = pst.executeQuery();
				
				Document PDFReport = new Document();
				    
			    
				try {
					// Set the page size to landscape
		            PDFReport.setPageSize(PageSize.A3.rotate());
		         // Inside testPdf and testPdfBySub methods
		            String outputFileName = getUniqueFileName("C:\\Users\\LINDELL\\Desktop\\BorrowedReport.pdf");
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
		            Paragraph title2 = new Paragraph("List of All Recorded Borrowed Books");
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
		            
					PdfPTable PDFTable = new PdfPTable(8);
					
					 // Add table header
		            String[] headers = {"Transaction ID", "Book Number", "Title", "Accession", "Date Borrowed" , "Due Date", "Borrower Name", "ID"};
		            
		            
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
					double totalAmountPaid = 0.0;

					while(rs.next()) {
						String transaction_id = rs.getString("transaction_id");
						table_cell = new PdfPCell(new Phrase(transaction_id));
						PDFTable.addCell(table_cell);
						String book_num = rs.getString("book_num");
						table_cell = new PdfPCell(new Phrase(book_num));
						PDFTable.addCell(table_cell);
						String title = rs.getString("title");
						table_cell = new PdfPCell(new Phrase(title));
						PDFTable.addCell(table_cell);
						
						String accession = String.valueOf(rs.getInt("accession"));
						table_cell = new PdfPCell(new Phrase(accession));
						PDFTable.addCell(table_cell);
						
						String date_borrowed = rs.getString("date_borrowed");
						table_cell = new PdfPCell(new Phrase(date_borrowed));
						PDFTable.addCell(table_cell);
						
						String due_date = rs.getString("due_date");
						table_cell = new PdfPCell(new Phrase(due_date));
						PDFTable.addCell(table_cell);
						
						String borrower_name = rs.getString("borrower_name");
						table_cell = new PdfPCell(new Phrase(borrower_name));
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
					Paragraph totalBooksParagraph = new Paragraph("Total No. Borrowed Books: "  + totalStudents, customFont2);
					totalBooksParagraph.setAlignment(Element.ALIGN_LEFT);
					totalBooksParagraph.setIndentationLeft(125);
					PDFReport.add(totalBooksParagraph);
					
					
					// Add who generated the report
					String userType = MainMenuFrame.getUser();
					String positionz = "";
					
					
					if ("Librarian".equalsIgnoreCase(userType)) {
					    preparedBy = "Hilda V. Tiongco";
					    positionz += "School Librarian";
					    	
						
					 // Create a Paragraph for the generated by information
					    Paragraph generatedBy = new Paragraph();

					    // Add "Prepared by: " text without underline
					    Chunk preparedByText = new Chunk("Prepared by: ", customFont2);
					    generatedBy.add(preparedByText);

					    // Add underlined name
					    Chunk nameChunk = new Chunk(preparedBy, customFont2);
					    nameChunk.setUnderline(1.5f, -1);  // Adjust the values for underline thickness and position
					    generatedBy.add(nameChunk);

					    generatedBy.add(Chunk.NEWLINE);

					    // Add indentation for the school position
					    Paragraph position = new Paragraph(positionz, customFont2);
					    position.setIndentationLeft(95);  // Adjust the indentation as needed
					    generatedBy.add(position);

					    // Add the generated by information to the PDF
					    generatedBy.setIndentationLeft(125);
					    PDFReport.add(generatedBy);
					} else if ("Admin".equalsIgnoreCase(userType)) {
					    preparedBy = "Ronan Santos";
					    positionz += "IT Administrator";
					 // Create a Paragraph for the generated by information
					    Paragraph generatedBy = new Paragraph();

					    // Add "Prepared by: " text without underline
					    Chunk preparedByText = new Chunk("Prepared by: ", customFont2);
					    generatedBy.add(preparedByText);

					    // Add underlined name
					    Chunk nameChunk = new Chunk(preparedBy, customFont2);
					    nameChunk.setUnderline(1.5f, -1);  // Adjust the values for underline thickness and position
					    generatedBy.add(nameChunk);

					    generatedBy.add(Chunk.NEWLINE);

					    // Add indentation for the school position
					    Paragraph position = new Paragraph(positionz, customFont2);
					    position.setIndentationLeft(98);  // Adjust the indentation as needed
					    generatedBy.add(position);

					    // Add the generated by information to the PDF
					    generatedBy.setIndentationLeft(125);
					    PDFReport.add(generatedBy);
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
		
		public void testPdfReturned() {
			// Fetch data from the database
	        try {
				pst = conn.prepareStatement("SELECT * FROM Returned");
				rs = pst.executeQuery();
				
				Document PDFReport = new Document();
				    
			    
				try {
					// Set the page size to landscape
		            PDFReport.setPageSize(PageSize.A3.rotate());
		         // Inside testPdf and testPdfBySub methods
		            String outputFileName = getUniqueFileName("C:\\Users\\LINDELL\\Desktop\\ReturnedReport.pdf");
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
		            Paragraph title2 = new Paragraph("List of All Returned Books");
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
		            
					PdfPTable PDFTable = new PdfPTable(10);
					
					 // Add table header
		            String[] headers = {"Transaction ID", "Book No", "Title", "Accession", "Status", "Due Date", 
		            		"Date Returned", "Borrower", "ID", "Penalty Fee"};
		            
		            
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
						String book_num = rs.getString("book_num");
						table_cell = new PdfPCell(new Phrase(book_num));
						PDFTable.addCell(table_cell);
						String title = rs.getString("title");
						table_cell = new PdfPCell(new Phrase(title));
						PDFTable.addCell(table_cell);
						String accession = rs.getString("accession");
						table_cell = new PdfPCell(new Phrase(accession));
						PDFTable.addCell(table_cell);
						String status = rs.getString("status");
						table_cell = new PdfPCell(new Phrase(status));
						PDFTable.addCell(table_cell);
						String due_date = rs.getString("due_date");
						table_cell = new PdfPCell(new Phrase(due_date));
						PDFTable.addCell(table_cell);	
						String date_returned = rs.getString("date_returned");
						table_cell = new PdfPCell(new Phrase(date_returned));
						PDFTable.addCell(table_cell);
						String borrower = rs.getString("borrower");
						table_cell = new PdfPCell(new Phrase(borrower));
						PDFTable.addCell(table_cell);
						String id = rs.getString("id");
						table_cell = new PdfPCell(new Phrase(id));
						PDFTable.addCell(table_cell);
						String penalty_fee = rs.getString("penalty_fee");
						table_cell = new PdfPCell(new Phrase(penalty_fee));
						PDFTable.addCell(table_cell);
						totalStudents++;
					}
					
											
				
					PDFReport.add(PDFTable);
					// Add total number of books
					Paragraph line = new Paragraph("\n");
					line.setAlignment(Element.ALIGN_RIGHT);
					PDFReport.add(line);
					
					// Add total number of books
					Paragraph totalBooksParagraph = new Paragraph("Total No. Returned Books: " + totalStudents, customFont2);
					totalBooksParagraph.setAlignment(Element.ALIGN_LEFT);
					totalBooksParagraph.setIndentationLeft(125);
					PDFReport.add(totalBooksParagraph);

					// Add who generated the report
					String userType = MainMenuFrame.getUser();
					String positionz = "";
					
					
					if ("Librarian".equalsIgnoreCase(userType)) {
					    preparedBy = "Hilda V. Tiongco";
					    positionz += "School Librarian";
					    	
						
					 // Create a Paragraph for the generated by information
					    Paragraph generatedBy = new Paragraph();

					    // Add "Prepared by: " text without underline
					    Chunk preparedByText = new Chunk("Prepared by: ", customFont2);
					    generatedBy.add(preparedByText);

					    // Add underlined name
					    Chunk nameChunk = new Chunk(preparedBy, customFont2);
					    nameChunk.setUnderline(1.5f, -1);  // Adjust the values for underline thickness and position
					    generatedBy.add(nameChunk);

					    generatedBy.add(Chunk.NEWLINE);

					    // Add indentation for the school position
					    Paragraph position = new Paragraph(positionz, customFont2);
					    position.setIndentationLeft(95);  // Adjust the indentation as needed
					    generatedBy.add(position);

					    // Add the generated by information to the PDF
					    generatedBy.setIndentationLeft(125);
					    PDFReport.add(generatedBy);
					} else if ("Admin".equalsIgnoreCase(userType)) {
					    preparedBy = "Ronan Santos";
					    positionz += "IT Administrator";
					 // Create a Paragraph for the generated by information
					    Paragraph generatedBy = new Paragraph();

					    // Add "Prepared by: " text without underline
					    Chunk preparedByText = new Chunk("Prepared by: ", customFont2);
					    generatedBy.add(preparedByText);

					    // Add underlined name
					    Chunk nameChunk = new Chunk(preparedBy, customFont2);
					    nameChunk.setUnderline(1.5f, -1);  // Adjust the values for underline thickness and position
					    generatedBy.add(nameChunk);

					    generatedBy.add(Chunk.NEWLINE);

					    // Add indentation for the school position
					    Paragraph position = new Paragraph(positionz, customFont2);
					    position.setIndentationLeft(98);  // Adjust the indentation as needed
					    generatedBy.add(position);

					    // Add the generated by information to the PDF
					    generatedBy.setIndentationLeft(125);
					    PDFReport.add(generatedBy);
					}

					
					// Get the current date and time
				    LocalDateTime currentTime = LocalDateTime.now();

				    // Format the date and time using the desired pattern
				    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy hh:mma");
				    String formattedDateTime = currentTime.format(formatter);
				    
				    com.itextpdf.text.Font customFont3 = FontFactory.getFont("Noto Sans", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 15, Font.BOLD, BaseColor.BLACK);
					// Add who generated the report
					Paragraph reportGenerated = new Paragraph("Report Generated: " + formattedDateTime, customFont3);
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
}
