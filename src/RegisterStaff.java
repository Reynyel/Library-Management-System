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
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class RegisterStaff extends JFrame {

	private JPanel contentPane;
	private JTextField txtEmployeeID;
	private JTextField txtLastName;
	private JTextField txtFirstName;
	private JTextField txtMiddleName;
	private JTextField txtContactNum;
	private JTextField txtEmail;
	private JRadioButton radioFaculty, radioStaff;

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
		setTitle("Register Staff");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 953, 666);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEmployeeID = new JLabel("Employee ID");
		lblEmployeeID.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEmployeeID.setBounds(29, 78, 130, 14);
		contentPane.add(lblEmployeeID);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblLastName.setBounds(29, 124, 130, 14);
		contentPane.add(lblLastName);
		
		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblFirstName.setBounds(29, 170, 130, 14);
		contentPane.add(lblFirstName);
		
		JLabel lblMiddleName = new JLabel("Middle Name");
		lblMiddleName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMiddleName.setBounds(29, 216, 130, 14);
		contentPane.add(lblMiddleName);
		
		txtEmployeeID = new JTextField();
		txtEmployeeID.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtEmployeeID.setColumns(10);
		txtEmployeeID.setBounds(127, 77, 217, 20);
		contentPane.add(txtEmployeeID);
		
		txtLastName = new JTextField();
		txtLastName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtLastName.setColumns(10);
		txtLastName.setBounds(127, 123, 629, 20);
		contentPane.add(txtLastName);
		
		txtFirstName = new JTextField();
		txtFirstName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtFirstName.setColumns(10);
		txtFirstName.setBounds(127, 169, 629, 20);
		contentPane.add(txtFirstName);
		
		txtMiddleName = new JTextField();
		txtMiddleName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtMiddleName.setColumns(10);
		txtMiddleName.setBounds(127, 215, 629, 20);
		contentPane.add(txtMiddleName);
		
		JLabel lblContactNo = new JLabel("Contact No.");
		lblContactNo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblContactNo.setBounds(29, 261, 130, 14);
		contentPane.add(lblContactNo);
		
		JLabel lblEmail = new JLabel("Email Address");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEmail.setBounds(29, 311, 130, 14);
		contentPane.add(lblEmail);
		
		txtContactNum = new JTextField();
		txtContactNum.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtContactNum.setColumns(10);
		txtContactNum.setBounds(127, 260, 629, 20);
		contentPane.add(txtContactNum);
		
		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtEmail.setColumns(10);
		txtEmail.setBounds(127, 310, 629, 20);
		contentPane.add(txtEmail);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registerStaff();
			}
		});
		btnRegister.setForeground(Color.WHITE);
		btnRegister.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnRegister.setBorderPainted(false);
		btnRegister.setBackground(new Color(157, 179, 227));
		btnRegister.setBounds(663, 424, 93, 33);
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
		btnBack.setBackground(new Color(157, 179, 227));
		btnBack.setBounds(10, 583, 93, 33);
		contentPane.add(btnBack);
		
		JLabel lblEmployeeType = new JLabel("Employee Type");
		lblEmployeeType.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEmployeeType.setBounds(29, 365, 130, 14);
		contentPane.add(lblEmployeeType);
		
		radioFaculty = new JRadioButton("Faculty");
		radioFaculty.setBackground(new Color(255, 255, 255));
		radioFaculty.setFont(new Font("Tahoma", Font.PLAIN, 15));
		radioFaculty.setBounds(155, 363, 109, 23);
		contentPane.add(radioFaculty);
		
		radioStaff = new JRadioButton("Staff");
		radioStaff.setBackground(new Color(255, 255, 255));
		radioStaff.setFont(new Font("Tahoma", Font.PLAIN, 15));
		radioStaff.setBounds(277, 363, 109, 23);
		contentPane.add(radioStaff);
		
		
		/*Add radio buttons for employee type
		 * in a group, so only one radio button
		 * can be ticked*/
		ButtonGroup G = new ButtonGroup();
		G.add(radioFaculty);
		G.add(radioStaff);
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
				
				
				//If radioFaculty is ticked, set the employee type to 'Faculty'
				if(radioFaculty.isSelected()) {
					type = "Faculty";
					//Build query
					String sql = "INSERT INTO Employees (employeeID, LastName, FirstName, MiddleName, ContactNo, email, EmployeeType)" +
							"VALUES ('" + employeeID + "', '" + lastName + "', '" + firstName+ "', '" + middleName+ "', '" + contact +  "', '" + email + "', '" + type +"')";
					
					//Execute query
					stmt.executeUpdate(sql);
					JOptionPane.showMessageDialog(rootPane, "Employee Registered");
				}
				
				//If radioFaculty is ticked, set the employee type to 'Staff'
				else if(radioStaff.isSelected()) {
					type = "Staff";
					//Build query
					String sql = "INSERT INTO Employee (employeeID, LastName, FirstName, MiddleName, ContactNo, email, EmployeeType)" +
							"VALUES ('" + employeeID + "', '" + lastName + "', '" + firstName+ "', '" + middleName+ "', '" + contact +  "', '" + email + "', '" + type +"')";
					
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
