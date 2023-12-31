import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.ScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.LineBorder;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
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
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JComboBox;
import color.AlternateColorRender;
import tablemodel.NonEditTableModel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SearchStudentsFrame extends JFrame {

	private JPanel contentPane;
	private JTable tblBooks;
	private JTextField txtName;
	private JTextField txtUserId;
	private JTable tblUser;

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchStudentsFrame frame = new SearchStudentsFrame();
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
	public SearchStudentsFrame() {
		setTitle("Search Student");
		setResizable(false);
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
		
		JLabel lblGrade = new JLabel("Grade");
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
						"User ID", "Last Name", "First Name", "Middle Name", "Grade Level", "Section"
				}
				));
		tblUser.setColumnSelectionAllowed(true);
		Object[][] data = {null, null, null, null, null, null};
		Object[] columnNames = {"User ID", "Last Name", "First Name", "Middle Name", "Grade Level", "Section"};
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
						String level = (String) tblUser.getValueAt(selectedRow, 4);
						String section = (String) tblUser.getValueAt(selectedRow, 5);						
						
						//Populate fields
						txtUserId.setText(userId);
						txtName.setText(lastName + ", " + firstName + "  " + middleName);
						txtLevel.setText(level);
						txtSection.setText(section);
						
					}
				}
			}
		});
		tblUser.setBorder(new LineBorder(new Color(0, 0, 0)));
		tblUser.setBounds(10, 11, 1036, 402);
		AlternateColorRender alternate = new AlternateColorRender();
		tblUser.setDefaultRenderer(Object.class, alternate);
		contentPane.add(tblUser);
		
		txtLevel = new JTextField();
		txtLevel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtLevel.setColumns(10);
		txtLevel.setBounds(313, 518, 62, 30);
		contentPane.add(txtLevel);
		
		txtSection = new JTextField();
		txtSection.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtSection.setColumns(10);
		txtSection.setBounds(313, 559, 175, 30);
		contentPane.add(txtSection);
		
		JLabel lblSection = new JLabel("Section");
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
								
				String sql = "SELECT * FROM Students WHERE StudentNo = ?";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				
				String userId = txtUserId.getText();
				pstmt.setString(1, userId);
				
				ResultSet rs = pstmt.executeQuery();

		        DefaultTableModel tblModel = (DefaultTableModel) tblUser.getModel();	        
	
	        	DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();
	        	
	        	if(!rs.isBeforeFirst()) {
	        		JOptionPane.showMessageDialog(this, "Student not found.");
	        		txtUserId.setText("");
	        		txtName.setText("");
	        		txtSection.setText("");
	        		txtLevel.setText("");
	        	}
	        	
	        	else {
	        		// Clear existing rows in the table
		        	tblModel.setRowCount(0);	
	        		while(rs.next()) {
        			//add data until there is none
        			String studentNo = rs.getString("StudentNo");
        			String lastName = rs.getString("LastName");
        			String firstName = rs.getString("FirstName");
        			String middleName = rs.getString("MiddleName");
        			String level = rs.getString("GradeLevel");
        			String section = rs.getString("Section");
        			
        			txtUserId.setText(studentNo);
        			txtName.setText(lastName + ", " + firstName + " " + middleName);
        			txtLevel.setText(level);
        			txtSection.setText(section);
        			
        			//array to store data into jtable
        			String tbData[] = {studentNo, lastName, firstName, middleName, level,
        					section};
        			
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
								
				String sql = "SELECT * FROM Students";
				ResultSet rs = stmt.executeQuery(sql);
				
				
				DefaultTableModel tblModel = (DefaultTableModel)tblUser.getModel();
				
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
							gradeLevel, section};
									
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