import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class RegisterStudent extends JFrame {

	private JPanel contentPane;
	private JTextField txtLastName;
	private JTextField txtFirstName;
	private JTextField txtMiddleName;
	private JTextField txtStudentNum;
	
	private JComboBox gradeComboBox;
	private JComboBox sectionComboBox;
	
	private StudentSections section;

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
		setTitle("Register User");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 953, 666);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblLastName.setBounds(38, 126, 77, 14);
		contentPane.add(lblLastName);
		
		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblFirstName.setBounds(38, 165, 77, 14);
		contentPane.add(lblFirstName);
		
		JLabel lblMiddleName = new JLabel("Middle Name");
		lblMiddleName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMiddleName.setBounds(38, 202, 108, 14);
		contentPane.add(lblMiddleName);
		
		JLabel lblStudentNo = new JLabel("Student No.");
		lblStudentNo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblStudentNo.setBounds(38, 85, 108, 14);
		contentPane.add(lblStudentNo);
		
		JLabel lblGrade = new JLabel("Level");
		lblGrade.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblGrade.setBounds(38, 244, 77, 14);
		contentPane.add(lblGrade);
		
		JLabel lblSection = new JLabel("Section");
		lblSection.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSection.setBounds(38, 285, 77, 14);
		contentPane.add(lblSection);
		
		txtLastName = new JTextField();
		txtLastName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtLastName.setColumns(10);
		txtLastName.setBounds(125, 125, 629, 20);
		contentPane.add(txtLastName);
		
		txtFirstName = new JTextField();
		txtFirstName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtFirstName.setColumns(10);
		txtFirstName.setBounds(125, 164, 629, 20);
		contentPane.add(txtFirstName);
		
		txtMiddleName = new JTextField();
		txtMiddleName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtMiddleName.setColumns(10);
		txtMiddleName.setBounds(125, 201, 629, 20);
		contentPane.add(txtMiddleName);
		
		txtStudentNum = new JTextField();
		txtStudentNum.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtStudentNum.setColumns(10);
		txtStudentNum.setBounds(125, 84, 217, 20);
		contentPane.add(txtStudentNum);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registerStudent();
			}
		});
		btnRegister.setForeground(Color.WHITE);
		btnRegister.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnRegister.setBorderPainted(false);
		btnRegister.setBackground(new Color(157, 179, 227));
		btnRegister.setBounds(661, 335, 93, 33);
		contentPane.add(btnRegister);
		
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
		
		gradeComboBox = new JComboBox();
		gradeComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateSectionComboBox();
			}
		});
		gradeComboBox.setBounds(125, 242, 43, 22);
		gradeComboBox.setBackground(new Color(255, 255, 255));
		
		section = new StudentSections();
		
		sectionComboBox = new JComboBox();
		sectionComboBox.setBounds(125, 283, 108, 22);
		contentPane.add(sectionComboBox);
		
		int[] level = new int[] {1,2,3,4,5,6,7,8,9,10,11,12};
	
		try {
			for(int i = 1; i <= level.length; i++) {
				gradeComboBox.addItem(i);
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		contentPane.add(gradeComboBox);
		
		
	}
	
	public void updateSectionComboBox() {
		int selectedGrade = (int) gradeComboBox.getSelectedItem();
		String[] sectionsForGrade = section.getSectionsForGrade(selectedGrade);
		
		sectionComboBox.removeAllItems();
		
		for(String sectionName : sectionsForGrade) {
			sectionComboBox.addItem(sectionName);
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
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentDB", "root", "ranielle25");
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
				String sql = "INSERT INTO Students (StudentNo, LastName, FirstName, MiddleName, GradeLevel, Section)" +
						"VALUES ('" + studentNum + "', '" + lastName + "', '" + firstName+ "', '" + middleName+ "', '" + level +  "', '" + section +"')";
				
				//Execute query
				stmt.executeUpdate(sql);
				
				JOptionPane.showMessageDialog(rootPane, "Student Registered");
				
				
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
