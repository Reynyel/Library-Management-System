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
import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import color.AlternateColorRender;
import tablemodel.NonEditTableModel;

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
		setPreferredSize(new Dimension(1256, 686));
	    setLayout(null);
		setBounds(100, 100, 1687, 743);
		
		panel = new JPanel();
        panel.setBounds(-67, 22, 1288, 686);
        add(panel);
        panel.setLayout(null);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblLastName.setBounds(149, 402, 77, 30);
		panel.add(lblLastName);
		
		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblFirstName.setBounds(149, 443, 77, 30);
		panel.add(lblFirstName);
		
		JLabel lblMiddleName = new JLabel("Middle Name");
		lblMiddleName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMiddleName.setBounds(149, 484, 108, 30);
		panel.add(lblMiddleName);
		
		JLabel lblStudentNo = new JLabel("Student Number: ");
		lblStudentNo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblStudentNo.setBounds(149, 361, 133, 30);
		panel.add(lblStudentNo);
		
		JLabel lblGrade = new JLabel("Level");
		lblGrade.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblGrade.setBounds(149, 525, 77, 30);
		panel.add(lblGrade);
		
		JLabel lblSection = new JLabel("Section");
		lblSection.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSection.setBounds(149, 566, 77, 30);
		panel.add(lblSection);
		
		txtLastName = new JTextField();
		txtLastName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtLastName.setColumns(10);
		txtLastName.setBounds(292, 403, 629, 30);
		panel.add(txtLastName);
		
		txtFirstName = new JTextField();
		txtFirstName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtFirstName.setColumns(10);
		txtFirstName.setBounds(292, 444, 629, 30);
		panel.add(txtFirstName);
		
		txtMiddleName = new JTextField();
		txtMiddleName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtMiddleName.setColumns(10);
		txtMiddleName.setBounds(292, 485, 629, 30);
		panel.add(txtMiddleName);
		
		txtStudentNum = new JTextField();
		txtStudentNum.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtStudentNum.setColumns(10);
		txtStudentNum.setBounds(292, 362, 217, 30);
		panel.add(txtStudentNum);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registerStudent();
				displayData();
			}
		});
		btnRegister.setForeground(Color.WHITE);
		btnRegister.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnRegister.setBorderPainted(false);
		btnRegister.setBackground(new Color(220, 20, 60));
		btnRegister.setBounds(828, 565, 93, 33);
		panel.add(btnRegister);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserTypeFrame type = new UserTypeFrame();
				type.setVisible(true);
			}
		});
		btnBack.setForeground(Color.WHITE);
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBack.setBorderPainted(false);
		btnBack.setBackground(new Color(65, 105, 225));
		btnBack.setBounds(10, 667, 93, 33);
		panel.add(btnBack);
		
		gradeComboBox = new JComboBox();
		gradeComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateSectionComboBox();
			}
		});
		gradeComboBox.setBounds(292, 527, 108, 30);
		gradeComboBox.setBackground(new Color(255, 255, 255));
		
		section = new StudentSections();
		
		sectionComboBox = new JComboBox();
		sectionComboBox.setBounds(292, 568, 108, 30);
		panel.add(sectionComboBox);
		
		int[] level = new int[] {1,2,3,4,5,6,7,8,9,10,11,12};
	
		try {
			for(int i = 1; i <= level.length; i++) {
				gradeComboBox.addItem(i);
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		panel.add(gradeComboBox);
		
		tblStudents = new JTable();
		tblStudents.setColumnSelectionAllowed(true);
		tblStudents.setCellSelectionEnabled(true);
		tblStudents.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null},
			},
			new String[] {
				"Student No.", "Last Name", "First Name", "Middle Name", "Grade Level", "Section", "User Type"
			}
		));
		tblStudents.setBorder(new LineBorder(new Color(0, 0, 0)));
		tblStudents.setBounds(149, 0, 1036, 350);
		AlternateColorRender alternate = new AlternateColorRender();
		tblStudents.setDefaultRenderer(Object.class, alternate);
		
		Object[][] data = {null, null, null, null, null, null, null, null, null, null, null};
		Object[] columnNames = {"Student No.", "Last Name", "First Name", "MiddleName", "Grade Level", "Section", "User Type"};
		NonEditTableModel model;
		Set<Integer> editableColumns = new HashSet<>();
		
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
						gradeComboBox.setSelectedItem(gradeLevel);
						sectionComboBox.setSelectedItem(section);
						txtStudentNum.setText(studentNum);
						
					}
				}
			}
		});
		panel.add(tblStudents);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateUserData();
			}
		});
		btnUpdate.setForeground(new Color(245, 255, 250));
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnUpdate.setBorderPainted(false);
		btnUpdate.setBackground(new Color(32, 178, 170));
		btnUpdate.setBounds(714, 566, 93, 33);
		panel.add(btnUpdate);
		
		displayData();
	}
	
	public void updateSectionComboBox() {
		int selectedGrade = (int) gradeComboBox.getSelectedItem();
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
							
			String sql = "UPDATE Students SET LastName = ?, FirstName = ?, MiddleName = ?, GradeLevel = ?, Section = ? WHERE StudentNo = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			int selectedRow = tblStudents.getSelectedRow();
			
			if(selectedRow != -1) {
				
				//get book_num value from row
				String bookNum = (String) tblStudents.getValueAt(selectedRow, 0);
				
				pstmt.setString(1, lastName);
				pstmt.setString(2, firstName);
				pstmt.setString(3, middleName);
				pstmt.setString(4, gradeLevel);
				pstmt.setString(5, section);
				pstmt.setString(6, studentNo);
				
				int rowsAffected = pstmt.executeUpdate();
				if(rowsAffected > 0) {
					JOptionPane.showMessageDialog(getRootPane(), "Updated succesfully");
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
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	        
			Connection conn;
			
			try {
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/booksDB", "root", "ranielle25");
				Statement stmt = conn.createStatement();
				System.out.println("Connected");
				//Get the inputs
				String studentNum = txtStudentNum.getText();
				String lastName = txtLastName.getText();
				String firstName = txtFirstName.getText();
				String middleName = txtMiddleName.getText();
				int level = (int) gradeComboBox.getSelectedItem();
				String section = sectionComboBox.getSelectedItem().toString();
										
				//Build query
				String sql = "INSERT INTO Students (StudentNo, LastName, FirstName, MiddleName, GradeLevel, Section, UserType)" +
						"VALUES ('" + studentNum + "', '" + lastName + "', '" + firstName+ "', '" + middleName+ "', '" + level +  "', '" + section + "', '" + "Student" + "')";
				
				//Execute query
				stmt.executeUpdate(sql);
				
				JOptionPane.showMessageDialog(getRootPane(), "Student Registered");
				
				
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
