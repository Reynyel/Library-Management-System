import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
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
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import color.AlternateColorRender;
import tablemodel.NonEditTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ListSelectionModel;

public class RegisterStaff extends JFrame {

	private JPanel contentPane;
	private JTextField txtEmployeeID;
	private JTextField txtLastName;
	private JTextField txtFirstName;
	private JTextField txtMiddleName;
	private JTextField txtContactNum;
	private JTextField txtEmail;
	private JRadioButton radioFaculty, radioStaff;
	private JTable tblEmployees;

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
		setResizable(false);
		setTitle("Register Staff");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1072, 750);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(135, 206, 235));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEmployeeID = new JLabel("Employee ID");
		lblEmployeeID.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEmployeeID.setBounds(146, 347, 115, 30);
		contentPane.add(lblEmployeeID);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblLastName.setBounds(146, 393, 130, 30);
		contentPane.add(lblLastName);
		
		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblFirstName.setBounds(146, 441, 130, 30);
		contentPane.add(lblFirstName);
		
		JLabel lblMiddleName = new JLabel("Middle Name");
		lblMiddleName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMiddleName.setBounds(146, 482, 130, 30);
		contentPane.add(lblMiddleName);
		
		txtEmployeeID = new JTextField();
		txtEmployeeID.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtEmployeeID.setColumns(10);
		txtEmployeeID.setBounds(271, 348, 217, 30);
		contentPane.add(txtEmployeeID);
		
		txtLastName = new JTextField();
		txtLastName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtLastName.setColumns(10);
		txtLastName.setBounds(271, 394, 629, 30);
		contentPane.add(txtLastName);
		
		txtFirstName = new JTextField();
		txtFirstName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtFirstName.setColumns(10);
		txtFirstName.setBounds(271, 442, 629, 30);
		contentPane.add(txtFirstName);
		
		txtMiddleName = new JTextField();
		txtMiddleName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtMiddleName.setColumns(10);
		txtMiddleName.setBounds(271, 483, 629, 30);
		contentPane.add(txtMiddleName);
		
		JLabel lblContactNo = new JLabel("Contact No.");
		lblContactNo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblContactNo.setBounds(146, 530, 130, 30);
		contentPane.add(lblContactNo);
		
		JLabel lblEmail = new JLabel("Email Address");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEmail.setBounds(146, 571, 130, 30);
		contentPane.add(lblEmail);
		
		txtContactNum = new JTextField();
		txtContactNum.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtContactNum.setColumns(10);
		txtContactNum.setBounds(271, 531, 629, 30);
		contentPane.add(txtContactNum);
		
		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtEmail.setColumns(10);
		txtEmail.setBounds(271, 572, 629, 30);
		contentPane.add(txtEmail);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registerStaff();
				displayData();
			}
		});
		btnRegister.setForeground(Color.WHITE);
		btnRegister.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnRegister.setBorderPainted(false);
		btnRegister.setBackground(new Color(220, 20, 60));
		btnRegister.setBounds(807, 612, 93, 33);
		contentPane.add(btnRegister);
		
		//Back button
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserTypeFrame type = new UserTypeFrame();
				type.setVisible(true);
				dispose();
			}
		});
		btnBack.setForeground(Color.WHITE);
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBack.setBorderPainted(false);
		btnBack.setBackground(new Color(65, 105, 225));
		btnBack.setBounds(10, 667, 93, 33);
		contentPane.add(btnBack);
		
		JLabel lblEmployeeType = new JLabel("Employee Type");
		lblEmployeeType.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEmployeeType.setBounds(146, 612, 130, 30);
		contentPane.add(lblEmployeeType);
		
		radioFaculty = new JRadioButton("Faculty");
		radioFaculty.setBackground(new Color(135, 206, 250));
		radioFaculty.setFont(new Font("Tahoma", Font.PLAIN, 15));
		radioFaculty.setBounds(271, 613, 109, 30);
		contentPane.add(radioFaculty);
		
		radioStaff = new JRadioButton("Staff");
		radioStaff.setBackground(new Color(135, 206, 250));
		radioStaff.setFont(new Font("Tahoma", Font.PLAIN, 15));
		radioStaff.setBounds(379, 615, 109, 30);
		contentPane.add(radioStaff);
		
		
		/*Add radio buttons for employee type
		 * in a group, so only one radio button
		 * can be ticked*/
		ButtonGroup G = new ButtonGroup();
		G.add(radioFaculty);
		G.add(radioStaff);
		
		
		
		tblEmployees = new JTable();
		tblEmployees.setColumnSelectionAllowed(true);
		tblEmployees.setCellSelectionEnabled(true);
		tblEmployees.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null},
			},
			new String[] {
				"Employee ID", "Last Name", "First Name", "Middle Name", "Contact No.", "Email", "Employee Type"
			}
		));
		
		Object[][] data = {null, null, null, null, null, null, null};
		Object[] columnNames = {"Employee ID", "Last Name", "First Name", "Middle Name", "Contact No.", "Email", "Employee Type"};
		NonEditTableModel model;
		Set<Integer> editableColumns = new HashSet<>();
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
						txtContactNum.setText(contactNum);;										
					}
				}
			}
		});
		tblEmployees.setBorder(new LineBorder(new Color(0, 0, 0)));	
		tblEmployees.setBounds(10, 11, 1036, 325);
		AlternateColorRender alternate = new AlternateColorRender();
		tblEmployees.setDefaultRenderer(Object.class, alternate);
		contentPane.add(tblEmployees);
		
		btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateUserData();
			}
		});
		btnUpdate.setForeground(new Color(245, 255, 250));
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnUpdate.setBorderPainted(false);
		btnUpdate.setBackground(new Color(32, 178, 170));
		btnUpdate.setBounds(697, 613, 93, 33);
		contentPane.add(btnUpdate);
		
		displayData();
	}
	
	Connection conn;
	PreparedStatement pst;
	ResultSet rs;
	private JButton btnUpdate;
	
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
							
			String sql = "UPDATE Employees SET LastName = ?, FirstName = ?, MiddleName = ?, email = ?, ContactNo = ?, UserType = ? WHERE employeeID = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			int selectedRow = tblEmployees.getSelectedRow();
			
			if(selectedRow != -1) {
				
				//get book_num value from row
				String employeeID = (String) tblEmployees.getValueAt(selectedRow, 0);
				
				pstmt.setString(1, lastName);
				pstmt.setString(2, firstName);
				pstmt.setString(3, middleName);
				pstmt.setString(4, email);
				pstmt.setString(5, contactNo);
				pstmt.setString(6, employeeType);
				pstmt.setString(7, employeeID);
				
				int rowsAffected = pstmt.executeUpdate();
				if(rowsAffected > 0) {
					JOptionPane.showMessageDialog(rootPane, "Updated succesfully");
					displayData();
				}
				
				else {
					JOptionPane.showMessageDialog(rootPane, "No rows updated");
				}
				
			}
			else {
	            JOptionPane.showMessageDialog(rootPane, "No row selected");
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
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	        
			Connection conn;
			
			try {
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/booksDB", "root", "ranielle25");
				Statement stmt = conn.createStatement();
				System.out.println("Connected");
				//Get the inputs
				String employeeID = txtEmployeeID.getText();
				String lastName = txtLastName.getText();
				String firstName = txtFirstName.getText();
				String middleName = txtMiddleName.getText();
				String contact = txtContactNum.getText();
				String email = txtEmail.getText();
				String type;
				
				 // Query to get the next available employee ID
	            String getIdQuery = "SELECT MAX(employeeID) FROM Employees";
	            ResultSet idResultSet = stmt.executeQuery(getIdQuery);

	            int nextEmployeeID = 100000;

	            if (idResultSet.next()) {
	                nextEmployeeID = idResultSet.getInt(1) + 1;
	            }
				

				//If radioFaculty is ticked, set the employee type to 'Faculty'
				if(radioFaculty.isSelected()) {
					type = "Faculty";
					//Build query
					String sql = "INSERT INTO Employees (employeeID, LastName, FirstName, MiddleName, ContactNo, email, UserType)" +
							"VALUES ('" + nextEmployeeID + "', '" + lastName + "', '" + firstName+ "', '" + middleName+ "', '" + contact +  "', '" + email + "', '" + type +"')";
					
					//Execute query
					stmt.executeUpdate(sql);
					JOptionPane.showMessageDialog(rootPane, "Employee Registered");
				}
				
				//If radioFaculty is ticked, set the employee type to 'Staff'
				else if(radioStaff.isSelected()) {
					type = "Staff";
					//Build query
					String sql = "INSERT INTO Employees (employeeID, LastName, FirstName, MiddleName, ContactNo, email, UserType)" +
							"VALUES ('" + nextEmployeeID + "', '" + lastName + "', '" + firstName+ "', '" + middleName+ "', '" + contact +  "', '" + email + "', '" + type +"')";
					
					//Execute query
					stmt.executeUpdate(sql);
					JOptionPane.showMessageDialog(rootPane, "Employee Registered");
				}
				
				//If no radio button is ticked, this message pops up
				else {
					JOptionPane.showMessageDialog(rootPane, "Please select employee type");
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
