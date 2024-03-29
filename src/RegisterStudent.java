import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
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
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import GradientBackground.gradientBackground;
import color.AlternateColorRender;
import tablemodel.NonEditTableModel;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;

public class RegisterStudent extends JPanel {

	private JPanel panel;
	private JTextField txtLastName;
	private JTextField txtFirstName;
	private JTextField txtMiddleName;
	private JTextField txtStudentNum;
	
	private JComboBox gradeComboBox;
	private JComboBox sectionComboBox;
	
	private StudentSections section;
	private JTable tblStudents;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterStudent frame = new RegisterStudent();
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
	public RegisterStudent() {
		setPreferredSize(new Dimension(1425, 980));
	    setLayout(null);
		setBounds(100, 100, 1425, 980);
		
		panel = new gradientBackground();
        panel.setBounds(0, 0, 1425, 980);
        add(panel);
        panel.setLayout(null);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setForeground(new Color(255, 255, 255));
		lblLastName.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblLastName.setBounds(62, 387, 90, 30);
		panel.add(lblLastName);
		
		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setForeground(new Color(255, 255, 255));
		lblFirstName.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblFirstName.setBounds(234, 387, 97, 30);
		panel.add(lblFirstName);
		
		JLabel lblMiddleName = new JLabel("Middle Name");
		lblMiddleName.setForeground(new Color(255, 255, 255));
		lblMiddleName.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblMiddleName.setBounds(404, 387, 122, 30);
		panel.add(lblMiddleName);
		
		JLabel lblStudentNo = new JLabel("Student Number");
		lblStudentNo.setForeground(new Color(255, 255, 255));
		lblStudentNo.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblStudentNo.setBounds(62, 270, 153, 30);
		panel.add(lblStudentNo);
		
		JLabel lblGrade = new JLabel("Level");
		lblGrade.setForeground(new Color(255, 255, 255));
		lblGrade.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblGrade.setBounds(62, 494, 77, 30);
		panel.add(lblGrade);
		
		JLabel lblSection = new JLabel("Section");
		lblSection.setForeground(new Color(255, 255, 255));
		lblSection.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblSection.setBounds(254, 494, 77, 30);
		panel.add(lblSection);
		
		txtLastName = new JTextField();
		txtLastName.setFont(new Font("Verdana", Font.PLAIN, 18));
		txtLastName.setColumns(10);
		txtLastName.setBounds(62, 428, 153, 35);
		panel.add(txtLastName);
		
		txtFirstName = new JTextField();
		txtFirstName.setFont(new Font("Verdana", Font.PLAIN, 18));
		txtFirstName.setColumns(10);
		txtFirstName.setBounds(234, 428, 153, 35);
		panel.add(txtFirstName);
		
		txtMiddleName = new JTextField();
		txtMiddleName.setFont(new Font("Verdana", Font.PLAIN, 18));
		txtMiddleName.setColumns(10);
		txtMiddleName.setBounds(404, 428, 153, 35);
		panel.add(txtMiddleName);
		
		txtStudentNum = new JTextField();
		txtStudentNum.setFont(new Font("Verdana", Font.PLAIN, 18));
		txtStudentNum.setColumns(10);
		txtStudentNum.setBounds(62, 311, 495, 35);
		panel.add(txtStudentNum);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String studentNum = txtStudentNum.getText().trim();
		        String lastName = txtLastName.getText().trim();
		        String firstName = txtFirstName.getText().trim();

		        if (studentNum.isEmpty() || lastName.isEmpty() || firstName.isEmpty()) {
		            JOptionPane.showMessageDialog(getRootPane(), "Please enter all required information before registering.");
		        } else {
		            registerStudent();
		            displayData();
		        }
				
			}
		});
		btnRegister.setForeground(Color.WHITE);
		btnRegister.setFont(new Font("Verdana", Font.BOLD, 15));
		btnRegister.setBorderPainted(false);
		btnRegister.setBackground(new Color(65, 105, 225));
		btnRegister.setBounds(407, 633, 150, 40);
		panel.add(btnRegister);
		
		gradeComboBox = new JComboBox();
		gradeComboBox.setFont(new Font("Verdana", Font.PLAIN, 18));
		gradeComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateSectionComboBox();
			}
		});
		gradeComboBox.setBounds(62, 535, 133, 35);
		gradeComboBox.setBackground(new Color(255, 255, 255));
		
		section = new StudentSections();
		
		sectionComboBox = new JComboBox();
		sectionComboBox.setFont(new Font("Verdana", Font.PLAIN, 18));
		sectionComboBox.setBounds(254, 535, 108, 35);
		panel.add(sectionComboBox);
		
		String[] level = new String[] {"1","2","3","4","5","6","7","8","9","10","11","12"};
	
		try {
			for(String s : level) {
				gradeComboBox.addItem(s);
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		panel.add(gradeComboBox);
		AlternateColorRender alternate = new AlternateColorRender();
		
		Object[][] data = {null, null, null, null, null, null, null, null, null, null, null};
		Object[] columnNames = {"Student No.", "Last Name", "First Name", "MiddleName", "Grade Level", "Section", "User Type"};
		NonEditTableModel model;
		Set<Integer> editableColumns = new HashSet<>();
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(618, 43, 761, 902);
		panel.add(scrollPane);
		
		tblStudents = new JTable();
		tblStudents.setFont(new Font("Tahoma", Font.PLAIN, 15));
		scrollPane.setViewportView(tblStudents);
		tblStudents.setColumnSelectionAllowed(true);
		tblStudents.setCellSelectionEnabled(true);
		tblStudents.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
			},
			new String[] {
				"Student No.", "Last Name", "First Name", "MiddleName", "Grade Level", "Section", "User Type"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tblStudents.setBorder(new LineBorder(new Color(0, 0, 0)));
		tblStudents.setDefaultRenderer(Object.class, alternate);
		
		tblStudents.setModel(new NonEditTableModel(data, columnNames, editableColumns));
		tblStudents.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 2) { //double click rows
					int selectedRow = tblStudents.getSelectedRow();
					if(selectedRow != -1) {
						//get data and populate textbox and combobox
						String studentNum = (String) tblStudents.getValueAt(selectedRow, 0);
						String lastName = (String) tblStudents.getValueAt(selectedRow, 1);
						String firstName = (String) tblStudents.getValueAt(selectedRow, 2);
						String middleName = (String) tblStudents.getValueAt(selectedRow, 3);
						String gradeLevel = (String) tblStudents.getValueAt(selectedRow, 4);
						String section = (String) tblStudents.getValueAt(selectedRow, 5);
						String userType = (String) tblStudents.getValueAt(selectedRow, 6);
						
						
						//Populate fields
						txtLastName.setText(lastName);
						txtFirstName.setText(firstName);
						txtMiddleName.setText(middleName);
						txtStudentNum.setText(studentNum);
						gradeComboBox.setSelectedItem(gradeLevel);
						sectionComboBox.setSelectedItem(section);
							
					}
				}
			}
		});
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateUserData();
			}
		});
		btnUpdate.setForeground(new Color(245, 255, 250));
		btnUpdate.setFont(new Font("Verdana", Font.BOLD, 15));
		btnUpdate.setBorderPainted(false);
		btnUpdate.setBackground(new Color(34, 139, 34));
		btnUpdate.setBounds(247, 633, 150, 40);
		panel.add(btnUpdate);
		
		JButton btnExport = new JButton("Export to PDF");
		btnExport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				testPdf();
			}
		});
		btnExport.setForeground(Color.WHITE);
		btnExport.setFont(new Font("Verdana", Font.BOLD, 15));
		btnExport.setBorderPainted(false);
		btnExport.setBackground(new Color(34, 139, 34));
		btnExport.setBounds(246, 695, 311, 30);
		panel.add(btnExport);
		
		JLabel lblStudentNo_1 = new JLabel("*");
		lblStudentNo_1.setForeground(new Color(255, 0, 0));
		lblStudentNo_1.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblStudentNo_1.setBounds(207, 270, 23, 30);
		panel.add(lblStudentNo_1);
		
		JLabel lblStudentNo_1_1 = new JLabel("*");
		lblStudentNo_1_1.setForeground(Color.RED);
		lblStudentNo_1_1.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblStudentNo_1_1.setBounds(154, 387, 23, 30);
		panel.add(lblStudentNo_1_1);
		
		JLabel lblStudentNo_1_2 = new JLabel("*");
		lblStudentNo_1_2.setForeground(Color.RED);
		lblStudentNo_1_2.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblStudentNo_1_2.setBounds(329, 387, 23, 30);
		panel.add(lblStudentNo_1_2);
		
		JLabel lblStudentNo_1_2_1 = new JLabel("*");
		lblStudentNo_1_2_1.setForeground(Color.RED);
		lblStudentNo_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblStudentNo_1_2_1.setBounds(440, 270, 23, 30);
		panel.add(lblStudentNo_1_2_1);
		
		JLabel lblImportantFields = new JLabel("- Important fields");
		lblImportantFields.setForeground(Color.WHITE);
		lblImportantFields.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblImportantFields.setBounds(449, 271, 108, 30);
		panel.add(lblImportantFields);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = tblStudents.getSelectedRow();

		        if (selectedRow == -1) {
		            // No row selected, show an error message
		            JOptionPane.showMessageDialog(getRootPane(), "Please select a book to remove.", "Error", JOptionPane.ERROR_MESSAGE);
		        } else {
		            // Get the Book_Num from the selected row
		            String id = tblStudents.getValueAt(selectedRow, 0).toString(); // Assuming Book_Num is in the first column (index 0)
		            // Confirm the removal with a dialog
		            int dialogResult = JOptionPane.showConfirmDialog(getRootPane(), "Are you sure you want to remove this user?", "Confirmation", JOptionPane.YES_NO_OPTION);
		            
		            if (dialogResult == JOptionPane.YES_OPTION) {
		                // User confirmed, proceed with removal
		                removeUser(id);
		                displayData();
		            }
		        }
			}
		});
		btnRemove.setForeground(Color.WHITE);
		btnRemove.setFont(new Font("Verdana", Font.BOLD, 15));
		btnRemove.setBorderPainted(false);
		btnRemove.setBackground(new Color(128, 0, 0));
		btnRemove.setBounds(62, 633, 150, 40);
		panel.add(btnRemove);
		
		displayData();
	}
	
	// Method to remove a book by Book_Num
		private void removeUser(String id) {
		    try {
		        String deleteSql = "DELETE FROM Students WHERE StudentNo = ?";

		        try (PreparedStatement deleteStmt = conn.prepareStatement(deleteSql)) {
		            deleteStmt.setString(1, id);
		            // Execute the update
		            int rowsAffected = deleteStmt.executeUpdate();

		            if (rowsAffected > 0) {
		                JOptionPane.showMessageDialog(getRootPane(), "User has been removed.");
		            } else {
		                JOptionPane.showMessageDialog(getRootPane(), "Failed to remove the book.", "Error", JOptionPane.ERROR_MESSAGE);
		            }
		        }
		    } catch (SQLException ex) {
		        ex.printStackTrace();
		    }
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
	    String baseFileName = "C:\\Users\\LINDELL\\Desktop\\students_export_" + currentDate + ".csv";

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
	        fw.append("List of All Registered Students");
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
	        fw.append("Student ID");
	        fw.append(',');
	        fw.append(',');
	        fw.append("Last Name");
	        fw.append(',');
	        fw.append(',');
	        fw.append("First Name");
	        fw.append(',');
	        fw.append(',');
	        fw.append("Middle Name");
	        fw.append(',');
	        fw.append(',');
	        fw.append("Grade Level");
	        fw.append(',');
	        fw.append(',');
	        fw.append("Section");
	        fw.append('\n');

	        // Fetch data from the database
	        pst = conn.prepareStatement("SELECT * FROM Students ");
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
	            fw.append(rs.getString(5));  //the column index for Grade Level
	            fw.append(',');
	            fw.append(',');
	            fw.append(rs.getString(6));  //the column index for Section
	            fw.append('\n');

	            totalBooks++;
	        }

	        // Write the total number of books registered
	        fw.append('\n');
	        fw.append("Total Students Registered: " + totalBooks);
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

	
	public void updateSectionComboBox() {
		String selectedGrade = gradeComboBox.getSelectedItem().toString();
		String[] sectionsForGrade = section.getSectionsForGrade(selectedGrade);
		
		sectionComboBox.removeAllItems();
		
		for(String sectionName : sectionsForGrade) {
			sectionComboBox.addItem(sectionName);
		}
	}
	
	Connection conn;
	PreparedStatement pst;
	ResultSet rs;
	
	public void updateUserData() {
		String lastName = txtLastName.getText();
		String firstName = txtFirstName.getText();
		String middleName = txtMiddleName.getText();
		String gradeLevel = gradeComboBox.getSelectedItem().toString();
		String section = sectionComboBox.getSelectedItem().toString();
		String studentNo = txtStudentNum.getText();
		
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
							
			String sql = "UPDATE Students SET LastName = ?, FirstName = ?, MiddleName = ?, GradeLevel = ?, Section = ?, StudentNo = ? WHERE StudentNo = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			int selectedRow = tblStudents.getSelectedRow();
			
			if(selectedRow != -1) {
				
				//get book_num value from row
				String id = (String) tblStudents.getValueAt(selectedRow, 0);
				
				pstmt.setString(1, lastName);
				pstmt.setString(2, firstName);
				pstmt.setString(3, middleName);
				pstmt.setString(4, gradeLevel);
				pstmt.setString(5, section);
				pstmt.setString(6, studentNo);
				pstmt.setString(7, id);
				
				int rowsAffected = pstmt.executeUpdate();
				if(rowsAffected > 0) {
					JOptionPane.showMessageDialog(getRootPane(), "Updated succesfully");
					resetFieldsAndSelection();
					displayData();
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
	
	public void displayData(){
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
								
				String sql = "SELECT * FROM Students";
				ResultSet rs = stmt.executeQuery(sql);
				
				
				DefaultTableModel tblModel = (DefaultTableModel)tblStudents.getModel();
				
				// Clear existing rows in the table
	            tblModel.setRowCount(0);
	            
				while(rs.next()) {
					//add data until there is none
					String studentNum = rs.getString("StudentNo");
					String lastName = rs.getString("LastName");
					String firstName = rs.getString("FirstName");
					String middleName = rs.getString("MiddleName");
					String gradeLevel = String.valueOf(rs.getString("GradeLevel"));
					String section = rs.getString("Section");
					String userType = rs.getString("UserType");
					//array to store data into jtable
					String tbData[] = {studentNum, lastName, firstName, middleName,
							gradeLevel, section, userType};
									
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
	
	public void registerStudent() {
	    try {
	        // Load the JDBC driver (version 4.0 or later)
	        try {
	            Class.forName("com.mysql.jdbc.Driver");
	        } catch (ClassNotFoundException e1) {
	            e1.printStackTrace();
	        }

	        Connection conn;
	        
	        try {
	            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/booksDB", "root", "ranielle25");
	            Statement stmt = conn.createStatement();
	            System.out.println("Connected");

	            // Get the inputs
	            String studentNum = txtStudentNum.getText();
	            String lastName = txtLastName.getText();
	            String firstName = txtFirstName.getText();
	            String middleName = txtMiddleName.getText();
	            String level = gradeComboBox.getSelectedItem().toString();
	            String section = sectionComboBox.getSelectedItem().toString();

	            // Check if the student with the same ID already exists
	            String checkExistingStudentSql = "SELECT * FROM Students WHERE StudentNo = ?";
	            try (PreparedStatement checkExistingStudentStmt = conn.prepareStatement(checkExistingStudentSql)) {
	                checkExistingStudentStmt.setString(1, studentNum);
	                ResultSet existingStudentResult = checkExistingStudentStmt.executeQuery();

	                if (existingStudentResult.next()) {
	                    // Student with the same ID already exists, show an error message
	                    JOptionPane.showMessageDialog(getRootPane(), "Student with ID " + studentNum + " is already registered.", "Error", JOptionPane.ERROR_MESSAGE);
	                } else {
	                    // Student with the same ID doesn't exist, proceed with registration

	                    // Build query
	                    String sql = "INSERT INTO Students (StudentNo, LastName, FirstName, MiddleName, GradeLevel, Section, UserType)" +
	                            "VALUES (?, ?, ?, ?, ?, ?, ?)";

	                    // Execute query
	                    try (PreparedStatement insertStudentStmt = conn.prepareStatement(sql)) {
	                        insertStudentStmt.setString(1, studentNum);
	                        insertStudentStmt.setString(2, lastName);
	                        insertStudentStmt.setString(3, firstName);
	                        insertStudentStmt.setString(4, middleName);
	                        insertStudentStmt.setString(5, level);
	                        insertStudentStmt.setString(6, section);
	                        insertStudentStmt.setString(7, "Student");

	                        insertStudentStmt.executeUpdate();

	                        resetFieldsAndSelection();
	                        JOptionPane.showMessageDialog(getRootPane(), "Student Registered");
	                    }
	                }
	            }

	        } catch (SQLException e1) {
	            e1.printStackTrace();
	        }

	    } catch (Exception e1) {
	        e1.printStackTrace();
	    }
	}

	//reset text fields and radio button selection
		private void resetFieldsAndSelection() {
			txtStudentNum.setText("");
            txtLastName.setText("");
            txtFirstName.setText("");
            txtMiddleName.setText("");
            gradeComboBox.setSelectedIndex(0);
            sectionComboBox.setSelectedIndex(0);
		}
}
