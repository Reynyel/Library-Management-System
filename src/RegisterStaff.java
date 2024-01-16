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
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;

public class RegisterStaff extends JPanel {

	private JPanel panel;
	private JTextField txtEmployeeID;
	private JTextField txtLastName;
	private JTextField txtFirstName;
	private JTextField txtMiddleName;
	private JTextField txtContactNum;
	private JTextField txtEmail;
	private JRadioButton radioFaculty, radioStaff;
	private JTable tblEmployees;
	private ButtonGroup G;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterStaff frame = new RegisterStaff();
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
	public RegisterStaff() {
		setPreferredSize(new Dimension(1425, 980));
	    setLayout(null);
		setBounds(100, 100, 1687, 743);
		
		panel = new gradientBackground();
		panel.setBackground(new Color(153, 153, 255));
        panel.setBounds(0, 0, 1425, 980);
        add(panel);
        panel.setLayout(null);
		
		JLabel lblEmployeeID = new JLabel("Employee ID");
		lblEmployeeID.setForeground(new Color(255, 255, 255));
		lblEmployeeID.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblEmployeeID.setBounds(66, 169, 126, 30);
		panel.add(lblEmployeeID);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setForeground(new Color(255, 255, 255));
		lblLastName.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblLastName.setBounds(66, 360, 102, 30);
		panel.add(lblLastName);
		
		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setForeground(new Color(255, 255, 255));
		lblFirstName.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblFirstName.setBounds(244, 360, 112, 30);
		panel.add(lblFirstName);
		
		JLabel lblMiddleName = new JLabel("Middle Name");
		lblMiddleName.setForeground(new Color(255, 255, 255));
		lblMiddleName.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblMiddleName.setBounds(416, 360, 112, 30);
		panel.add(lblMiddleName);
		
		txtEmployeeID = new JTextField();
		txtEmployeeID.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtEmployeeID.setColumns(10);
		txtEmployeeID.setBounds(66, 207, 500, 35);
		panel.add(txtEmployeeID);
		
		txtLastName = new JTextField();
		txtLastName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtLastName.setColumns(10);
		txtLastName.setBounds(66, 401, 150, 35);
		panel.add(txtLastName);
		
		txtFirstName = new JTextField();
		txtFirstName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtFirstName.setColumns(10);
		txtFirstName.setBounds(244, 401, 150, 35);
		panel.add(txtFirstName);
		
		txtMiddleName = new JTextField();
		txtMiddleName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtMiddleName.setColumns(10);
		txtMiddleName.setBounds(416, 401, 150, 35);
		panel.add(txtMiddleName);
		
		JLabel lblContactNo = new JLabel("Contact No.");
		lblContactNo.setForeground(new Color(255, 255, 255));
		lblContactNo.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblContactNo.setBounds(66, 468, 112, 30);
		panel.add(lblContactNo);
		
		JLabel lblEmail = new JLabel("Email Address");
		lblEmail.setForeground(new Color(255, 255, 255));
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblEmail.setBounds(66, 573, 150, 30);
		panel.add(lblEmail);
		
		txtContactNum = new JTextField();
		txtContactNum.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtContactNum.setColumns(10);
		txtContactNum.setBounds(66, 509, 314, 35);
		panel.add(txtContactNum);
		
		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtEmail.setColumns(10);
		txtEmail.setBounds(66, 614, 314, 35);
		panel.add(txtEmail);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = txtEmployeeID.getText().trim();
		        String lastName = txtLastName.getText().trim();
		        String firstName = txtFirstName.getText().trim();
		        String contact = txtContactNum.getText().trim();
		        
		        if (id.isEmpty() || lastName.isEmpty() || firstName.isEmpty() || contact.isEmpty() || (!radioFaculty.isSelected() && !radioStaff.isSelected())) {
		            JOptionPane.showMessageDialog(getRootPane(), "Please enter all required information before registering.");
		        } else {
		            registerStaff();
		            displayData();
		        }
			}
		});
		btnRegister.setForeground(Color.WHITE);
		btnRegister.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnRegister.setBorderPainted(false);
		btnRegister.setBackground(new Color(65, 105, 225));
		btnRegister.setBounds(416, 699, 150, 40);
		panel.add(btnRegister);
		
		JLabel lblEmployeeType = new JLabel("Employee Type");
		lblEmployeeType.setForeground(new Color(255, 255, 255));
		lblEmployeeType.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblEmployeeType.setBounds(68, 274, 148, 30);
		panel.add(lblEmployeeType);
		
		radioFaculty = new JRadioButton("Faculty");
		radioFaculty.setForeground(new Color(255, 255, 255));
		radioFaculty.setBackground(new Color(0, 74, 174));
		radioFaculty.setFont(new Font("Tahoma", Font.BOLD, 17));
		radioFaculty.setBounds(66, 305, 109, 30);
		radioFaculty.setOpaque(false);
		radioFaculty.setContentAreaFilled(false);
        radioFaculty.setBorderPainted(false);
		panel.add(radioFaculty);
		
		radioStaff = new JRadioButton("Staff");
		radioStaff.setForeground(new Color(255, 255, 255));
		radioStaff.setBackground(new Color(0, 51, 153));
		radioStaff.setFont(new Font("Tahoma", Font.BOLD, 17));
		radioStaff.setBounds(177, 305, 109, 30);
		radioStaff.setOpaque(false);
		radioStaff.setContentAreaFilled(false);
        radioStaff.setBorderPainted(false);
		panel.add(radioStaff);
		
		
		/*Add radio buttons for employee type
		 * in a group, so only one radio button
		 * can be ticked*/
		G = new ButtonGroup();
		G.add(radioFaculty);
		G.add(radioStaff);
		
		Object[][] data = {null, null, null, null, null, null, null};
		Object[] columnNames = {"Employee ID", "Last Name", "First Name", "Middle Name", "Contact No.", "Email", "Employee Type"};
		NonEditTableModel model;
		Set<Integer> editableColumns = new HashSet<>();
		AlternateColorRender alternate = new AlternateColorRender();
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(645, 37, 740, 914);
		panel.add(scrollPane);
		
		
		
		tblEmployees = new JTable();
		tblEmployees.setFont(new Font("Tahoma", Font.PLAIN, 15));
		scrollPane.setViewportView(tblEmployees);
		tblEmployees.setColumnSelectionAllowed(true);
		tblEmployees.setCellSelectionEnabled(true);
		tblEmployees.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
			},
			new String[] {
				"Employee ID", "Last Name", "First Name", "Middle Name", "Contact No.", "Email", "Employee Type"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tblEmployees.setModel(new NonEditTableModel(data, columnNames, editableColumns));
		tblEmployees.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 2) { //double click rows
					int selectedRow = tblEmployees.getSelectedRow();
					if(selectedRow != -1) {
						//get data and populate textbox and combobox
						String employeeId = (String) tblEmployees.getValueAt(selectedRow, 0);
						String lastName = (String) tblEmployees.getValueAt(selectedRow, 1);
						String firstName = (String) tblEmployees.getValueAt(selectedRow, 2);
						String middleName = (String) tblEmployees.getValueAt(selectedRow, 3);
						String contactNum = (String) tblEmployees.getValueAt(selectedRow, 4);
						String email = (String) tblEmployees.getValueAt(selectedRow, 5);
						String employeeType = (String) tblEmployees.getValueAt(selectedRow, 6);
							
						//Populate fields
						txtEmployeeID.setText(employeeId);
						txtLastName.setText(lastName);
						txtFirstName.setText(firstName);
						txtMiddleName.setText(middleName);
						txtEmail.setText(email);
						txtContactNum.setText(contactNum);
						// Set the corresponding radio button
		                if (employeeType.equals("Faculty")) {
		                    radioFaculty.setSelected(true);
		                } else if (employeeType.equals("Staff")) {
		                    radioStaff.setSelected(true);
		                }
					}
				}
			}
		});
		tblEmployees.setBorder(new LineBorder(new Color(0, 0, 0)));
		tblEmployees.setDefaultRenderer(Object.class, alternate);
		
		btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateUserData();
			}
		});
		btnUpdate.setForeground(new Color(245, 255, 250));
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnUpdate.setBorderPainted(false);
		btnUpdate.setBackground(new Color(34, 139, 34));
		btnUpdate.setBounds(255, 699, 150, 40);
		panel.add(btnUpdate);
			
		JButton btnExport = new JButton("Export to PDF");
		btnExport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				testPdf();
			}
		});
		btnExport.setForeground(Color.WHITE);
		btnExport.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnExport.setBorderPainted(false);
		btnExport.setBackground(new Color(0, 100, 0));
		btnExport.setBounds(255, 760, 311, 30);
		panel.add(btnExport);
		
		lblImportantFields = new JLabel("- Important fields");
		lblImportantFields.setForeground(Color.WHITE);
		lblImportantFields.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblImportantFields.setBounds(464, 170, 102, 30);
		panel.add(lblImportantFields);
		
		lblStudentNo_1_2 = new JLabel("*");
		lblStudentNo_1_2.setForeground(Color.RED);
		lblStudentNo_1_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblStudentNo_1_2.setBounds(453, 169, 113, 30);
		panel.add(lblStudentNo_1_2);
		
		lblStudentNo_1 = new JLabel("*");
		lblStudentNo_1.setForeground(Color.RED);
		lblStudentNo_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblStudentNo_1.setBounds(193, 169, 23, 30);
		panel.add(lblStudentNo_1);
		
		lblStudentNo_1_1 = new JLabel("*");
		lblStudentNo_1_1.setForeground(Color.RED);
		lblStudentNo_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblStudentNo_1_1.setBounds(206, 274, 23, 30);
		panel.add(lblStudentNo_1_1);
		
		lblStudentNo_1_3 = new JLabel("*");
		lblStudentNo_1_3.setForeground(Color.RED);
		lblStudentNo_1_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblStudentNo_1_3.setBounds(159, 360, 23, 30);
		panel.add(lblStudentNo_1_3);
		
		lblStudentNo_1_4 = new JLabel("*");
		lblStudentNo_1_4.setForeground(Color.RED);
		lblStudentNo_1_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblStudentNo_1_4.setBounds(342, 361, 23, 30);
		panel.add(lblStudentNo_1_4);
		
		lblStudentNo_1_5 = new JLabel("*");
		lblStudentNo_1_5.setForeground(Color.RED);
		lblStudentNo_1_5.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblStudentNo_1_5.setBounds(169, 468, 23, 30);
		panel.add(lblStudentNo_1_5);
			
		btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = tblEmployees.getSelectedRow();

		        if (selectedRow == -1) {
		            // No row selected, show an error message
		            JOptionPane.showMessageDialog(getRootPane(), "Please select a user to remove.", "Error", JOptionPane.ERROR_MESSAGE);
		        } else {
		            // Get the Book_Num from the selected row
		            String id = tblEmployees.getValueAt(selectedRow, 0).toString(); // Assuming Book_Num is in the first column (index 0)
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
		btnRemove.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnRemove.setBorderPainted(false);
		btnRemove.setBackground(new Color(128, 0, 0));
		btnRemove.setBounds(66, 699, 150, 40);
		panel.add(btnRemove);
		
		displayData();
	}
		
	Connection conn;
	PreparedStatement pst;
	ResultSet rs;
	
	// Method to remove a book by Book_Num
		private void removeUser(String id) {
		    try {
		        String deleteSql = "DELETE FROM Employees WHERE employeeID = ?";

		        try (PreparedStatement deleteStmt = conn.prepareStatement(deleteSql)) {
		            deleteStmt.setString(1, id);
		            // Execute the update
		            int rowsAffected = deleteStmt.executeUpdate();

		            if (rowsAffected > 0) {
		                JOptionPane.showMessageDialog(getRootPane(), "User has been removed.");
		            } else {
		                JOptionPane.showMessageDialog(getRootPane(), "Failed to remove the user.", "Error", JOptionPane.ERROR_MESSAGE);
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
	    String baseFileName = "C:\\Users\\LINDELL\\Desktop\\employees_export_" + currentDate + ".csv";

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
	        fw.append("List of All Registered Employee");
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
	        fw.append("Employee ID");
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
	        fw.append("Contact No.");
	        fw.append(',');
	        fw.append(',');
	        fw.append("Email");
	        fw.append(',');
	        fw.append(',');
	        fw.append(',');
	        fw.append("User Type");
	        fw.append('\n');

	        // Fetch data from the database
	        pst = conn.prepareStatement("SELECT * FROM Employees");
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
		        fw.append(',');
	            fw.append(rs.getString(7));  //the column index for Type
	            fw.append('\n');

	            totalBooks++;
	        }

	        // Write the total number of books registered
	        fw.append('\n');
	        fw.append("Total Staff Registered: " + totalBooks);
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
	
	private JButton btnUpdate;
	private JScrollPane scrollPane;
	private JLabel lblImportantFields;
	private JLabel lblStudentNo_1_2;
	private JLabel lblStudentNo_1;
	private JLabel lblStudentNo_1_1;
	private JLabel lblStudentNo_1_3;
	private JLabel lblStudentNo_1_4;
	private JLabel lblStudentNo_1_5;
	private JButton btnRemove;
	
	public void updateUserData() {
		String lastName = txtLastName.getText();
		String firstName = txtFirstName.getText();
		String middleName = txtMiddleName.getText();
		String email = txtEmail.getText();
		String contactNo = txtContactNum.getText();
		String employeeType = "";
		
		if(radioFaculty.isSelected()) {
			employeeType = "Faculty";
		}
		else if(radioStaff.isSelected()) {
			employeeType = "Staff";
		}
		
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
							
			String sql = "UPDATE Employees SET LastName = ?, FirstName = ?, MiddleName = ?, email = ?, ContactNo = ?, UserType = ?, employeeID = ? WHERE employeeID = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			int selectedRow = tblEmployees.getSelectedRow();
			
			if(selectedRow != -1) {
				
				//get book_num value from row
				String employeeID = (String) tblEmployees.getValueAt(selectedRow, 0);
				String id = txtEmployeeID.getText();
				pstmt.setString(1, lastName);
				pstmt.setString(2, firstName);
				pstmt.setString(3, middleName);
				pstmt.setString(4, email);
				pstmt.setString(5, contactNo);
				pstmt.setString(6, employeeType);
				pstmt.setString(7, id);
				pstmt.setString(8, employeeID);
				
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
								
				String sql = "SELECT * FROM Employees";
				ResultSet rs = stmt.executeQuery(sql);
				
				
				DefaultTableModel tblModel = (DefaultTableModel)tblEmployees.getModel();
				
				// Clear existing rows in the table
	            tblModel.setRowCount(0);
	            
				while(rs.next()) {
					//add data until there is none
					String employeeId = rs.getString("employeeID");
					String lastName = rs.getString("LastName");
					String firstName = rs.getString("FirstName");
					String middleName = rs.getString("MiddleName");
					String contactNo = rs.getString("ContactNo");
					String email = rs.getString("email");
					String type = rs.getString("UserType");
					
					//array to store data into jtable
					String tbData[] = {employeeId, lastName, firstName, middleName,
							contactNo, email, type};
									
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
	
	public void registerStaff() {
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
	            String employeeID = txtEmployeeID.getText().trim();
	            String lastName = txtLastName.getText().trim();
	            String firstName = txtFirstName.getText().trim();
	            String middleName = txtMiddleName.getText().trim();
	            String contact = txtContactNum.getText().trim();
	            String email = txtEmail.getText().trim();
	            String type;

	            // Check if the user ID already exists
	            String checkIdQuery = "SELECT * FROM Employees WHERE employeeID = '" + employeeID + "'";
	            ResultSet resultSet = stmt.executeQuery(checkIdQuery);

	            if (resultSet.next()) {
	                // User ID already exists, show an error message
	            	Font customFont = new Font("Arial", Font.PLAIN, 16);
	                UIManager.put("OptionPane.messageFont", customFont);
	                UIManager.put("OptionPane.buttonFont", customFont);
	                
	                JOptionPane.showMessageDialog(getRootPane(), "Employee with this ID already exists.",
	                        "Note", JOptionPane.ERROR_MESSAGE);

	                // Reset UIManager properties to default
	                UIManager.put("OptionPane.messageFont", UIManager.getDefaults().getFont("OptionPane.messageFont"));
	                UIManager.put("OptionPane.buttonFont", UIManager.getDefaults().getFont("OptionPane.buttonFont"));
	                resetFieldsAndSelection();
	                return;
	            }
	            
	            // If radioFaculty is ticked, set the employee type to 'Faculty'
	            if (radioFaculty.isSelected()) {
	                type = "Faculty";
	                // Build query
	                String sql = "INSERT INTO Employees (employeeID, LastName, FirstName, MiddleName, ContactNo, email, UserType)" +
	                        "VALUES ('" + employeeID + "', '" + lastName + "', '" + firstName + "', '" + middleName + "', '" + contact + "', '" + email + "', '" + type + "')";

	                // Execute query
	                stmt.executeUpdate(sql);
	                Font customFont = new Font("Arial", Font.PLAIN, 16);
	                UIManager.put("OptionPane.messageFont", customFont);
	                UIManager.put("OptionPane.buttonFont", customFont);
	                
	                JOptionPane.showMessageDialog(getRootPane(), "Employee Registered",
	                        "Success", JOptionPane.INFORMATION_MESSAGE);

	                // Reset UIManager properties to default
	                UIManager.put("OptionPane.messageFont", UIManager.getDefaults().getFont("OptionPane.messageFont"));
	                UIManager.put("OptionPane.buttonFont", UIManager.getDefaults().getFont("OptionPane.buttonFont"));
	                resetFieldsAndSelection();
	            }

	            // If radioFaculty is ticked, set the employee type to 'Staff'
	            else if (radioStaff.isSelected()) {
	                type = "Staff";
	                // Build query
	                String sql = "INSERT INTO Employees (employeeID, LastName, FirstName, MiddleName, ContactNo, email, UserType)" +
	                        "VALUES ('" + employeeID + "', '" + lastName + "', '" + firstName + "', '" + middleName + "', '" + contact + "', '" + email + "', '" + type + "')";

	                // Execute query
	                stmt.executeUpdate(sql);
	                resetFieldsAndSelection();
	                JOptionPane.showMessageDialog(getRootPane(), "Employee Registered");
	            }

	            // If no radio button is ticked, this message pops up
	            else {
	                JOptionPane.showMessageDialog(getRootPane(), "Please select employee type");
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
	    txtEmployeeID.setText("");
	    txtLastName.setText("");
	    txtFirstName.setText("");
	    txtMiddleName.setText("");
	    txtEmail.setText("");
	    txtContactNum.setText("");
	    G.clearSelection(); 
	}

}
