import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import color.AlternateColorRender;
import tablemodel.NonEditTableModel;

public class SearchEmployeesFrame extends JFrame {

	private JPanel contentPane;;
	private JTextField txtName;
	private JTextField txtUserId;
	private JTable tblUser;
	private JTextField txtEmail;
	private JTextField txtContact;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchEmployeesFrame frame = new SearchEmployeesFrame();
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
	public SearchEmployeesFrame() {
		setTitle("Search Employee");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1072, 750);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(135, 206, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnShowData = new JButton("Show Data");
		btnShowData.setForeground(new Color(245, 255, 250));
		btnShowData.setBackground(new Color(220, 20, 60));
		btnShowData.setBorderPainted(false);
		btnShowData.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnShowData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayData();
			}
			
		});
		btnShowData.setBounds(698, 559, 128, 40);
		contentPane.add(btnShowData);
		
		JButton btnSearch = new JButton("Search User");
		btnSearch.setForeground(new Color(245, 255, 250));
		btnSearch.setBackground(new Color(65, 105, 225));
		btnSearch.setBorderPainted(false);
		btnSearch.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				search();
			}
		});
		btnSearch.setBounds(560, 559, 128, 40);
		contentPane.add(btnSearch);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(198, 477, 80, 30);
		contentPane.add(lblNewLabel);
		
		txtName = new JTextField();
		txtName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtName.setColumns(10);
		txtName.setBounds(313, 477, 513, 30);
		contentPane.add(txtName);
		
		JLabel lblBookNumber = new JLabel("User ID");
		lblBookNumber.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblBookNumber.setBounds(198, 437, 105, 30);
		contentPane.add(lblBookNumber);
		
		txtUserId = new JTextField();
		txtUserId.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtUserId.setColumns(10);
		txtUserId.setBounds(313, 438, 513, 30);
		contentPane.add(txtUserId);
		
		JButton btnBack = new JButton("Back");
		btnBack.setForeground(new Color(255, 255, 255));
		btnBack.setBackground(new Color(65, 105, 225));
		btnBack.setBorderPainted(false);
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SearchUserType menu = new SearchUserType();
				menu.setVisible(true);
				dispose();
			}
		});
		btnBack.setBounds(10, 660, 128, 40);
		contentPane.add(btnBack);
		
		JLabel lblGrade = new JLabel("Email");
		lblGrade.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblGrade.setBounds(198, 518, 80, 30);
		contentPane.add(lblGrade);
		
		tblUser = new JTable();
		tblUser.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		tblUser.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, null, null, null, null},
				},
				new String[] {
						"User ID", "Last Name", "First Name", "Middle Name", "Contact No.", "Email"
				}
				));
		tblUser.setColumnSelectionAllowed(true);
		Object[][] data = {null, null, null, null, null, null};
		Object[] columnNames = {"User ID", "Last Name", "First Name", "Middle Name", "Contact No.", "Email"};
		NonEditTableModel model;
		Set<Integer> editableColumns = new HashSet<>();
		tblUser.setModel(new NonEditTableModel(data, columnNames, editableColumns));
		tblUser.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 2) { //double click rows
					int selectedRow = tblUser.getSelectedRow();
					if(selectedRow != -1) {
						//get data and populate textbox and combobox
						String userId = (String) tblUser.getValueAt(selectedRow, 0);
						String lastName = (String) tblUser.getValueAt(selectedRow, 1);
						String firstName = (String) tblUser.getValueAt(selectedRow, 2);
						String middleName = (String) tblUser.getValueAt(selectedRow, 3);
						String contactNo = (String) tblUser.getValueAt(selectedRow, 4);
						String email = (String) tblUser.getValueAt(selectedRow, 5);						
						
						//Populate fields
						txtUserId.setText(userId);
						txtName.setText(lastName + ", " + firstName + "  " + middleName);
						txtContact.setText(contactNo);
						txtEmail.setText(email);
						
					}
				}
			}
		});
		tblUser.setBorder(new LineBorder(new Color(0, 0, 0)));
		tblUser.setBounds(10, 11, 1036, 402);
		
		AlternateColorRender alternate = new AlternateColorRender();
		tblUser.setDefaultRenderer(Object.class, alternate);
		contentPane.add(tblUser);
		
		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtEmail.setColumns(10);
		txtEmail.setBounds(313, 518, 209, 30);
		contentPane.add(txtEmail);
		
		txtContact = new JTextField();
		txtContact.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtContact.setColumns(10);
		txtContact.setBounds(313, 559, 209, 30);
		contentPane.add(txtContact);
		
		JLabel lblSection = new JLabel("Contact No.");
		lblSection.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSection.setBounds(198, 559, 80, 30);
		contentPane.add(lblSection);
		
		displayData();
	}
	
	Connection conn;
	PreparedStatement pst;
	ResultSet rs;
	private JTextField txtLevel;
	private JTextField txtSection;
	
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
								
				String sql = "SELECT * FROM Employees WHERE employeeID = ?";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				
				String userId = txtUserId.getText();
				pstmt.setString(1, userId);
				
				ResultSet rs = pstmt.executeQuery();

		        DefaultTableModel tblModel = (DefaultTableModel) tblUser.getModel();	        
	
	        	DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();
	        	
	        	if(!rs.isBeforeFirst()) {
	        		JOptionPane.showMessageDialog(this, "Employee not found.");
	        		txtUserId.setText("");
	        		txtName.setText("");
	        		txtContact.setText("");
	        		txtEmail.setText("");
	        	}
	        	
	        	else {
	        		// Clear existing rows in the table
		        	tblModel.setRowCount(0);	
	        		while(rs.next()) {
        			//add data until there is none
        			String userID = rs.getString("employeeID");
        			String lastName = rs.getString("LastName");
        			String firstName = rs.getString("FirstName");
        			String middleName = rs.getString("MiddleName");
        			String contactNo = rs.getString("ContactNo");
        			String email = rs.getString("email");
        			
        			txtUserId.setText(userID);
        			txtName.setText(lastName + ", " + firstName + " " + middleName);
        			txtContact.setText(contactNo);
        			txtEmail.setText(email);
        			
        			//array to store data into jtable
        			String tbData[] = {userId, lastName, firstName, middleName, contactNo,
        					email};
        			
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
				
				
				DefaultTableModel tblModel = (DefaultTableModel)tblUser.getModel();
				
				// Clear existing rows in the table
	            tblModel.setRowCount(0);
	            
				while(rs.next()) {
					//add data until there is none
					String userId = rs.getString("employeeID");
					String lastName = rs.getString("LastName");
					String firstName = rs.getString("FirstName");
					String middleName = rs.getString("MiddleName");
					String contactNo = String.valueOf(rs.getString("ContactNo"));
					String email = rs.getString("email");
					String userType = rs.getString("UserType");
					//array to store data into jtable
					String tbData[] = {userId, lastName, firstName, middleName,
							contactNo, email};
									
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
