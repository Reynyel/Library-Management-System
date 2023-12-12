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
import javax.swing.JComboBox;
import color.AlternateColorRender;

public class SearchStudentsFrame extends JFrame {

	private JPanel contentPane;
	private JTable tblBooks;
	private JTextField txtTitle;
	private JTextField txtBookNum;
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
		setTitle("Book Search");
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
			}
			
		});
		btnShowData.setBounds(698, 559, 128, 40);
		contentPane.add(btnShowData);
		
		JButton btnSearchBook = new JButton("Search User");
		btnSearchBook.setForeground(new Color(245, 255, 250));
		btnSearchBook.setBackground(new Color(65, 105, 225));
		btnSearchBook.setBorderPainted(false);
		btnSearchBook.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnSearchBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSearchBook.setBounds(560, 559, 128, 40);
		contentPane.add(btnSearchBook);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(198, 477, 80, 30);
		contentPane.add(lblNewLabel);
		
		txtTitle = new JTextField();
		txtTitle.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtTitle.setColumns(10);
		txtTitle.setBounds(313, 477, 513, 30);
		contentPane.add(txtTitle);
		
		JLabel lblBookNumber = new JLabel("User ID");
		lblBookNumber.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblBookNumber.setBounds(198, 437, 105, 30);
		contentPane.add(lblBookNumber);
		
		txtBookNum = new JTextField();
		txtBookNum.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtBookNum.setColumns(10);
		txtBookNum.setBounds(313, 438, 513, 30);
		contentPane.add(txtBookNum);
		
		JButton btnBack = new JButton("Back");
		btnBack.setForeground(new Color(255, 255, 255));
		btnBack.setBackground(new Color(65, 105, 225));
		btnBack.setBorderPainted(false);
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainMenuFrame menu = new MainMenuFrame();
				menu.setVisible(true);
				dispose();
			}
		});
		btnBack.setBounds(10, 660, 128, 40);
		contentPane.add(btnBack);
		
		JComboBox cbStatus = new JComboBox();
		cbStatus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		cbStatus.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cbStatus.setBackground(Color.WHITE);
		cbStatus.setBounds(313, 518, 105, 30);
		cbStatus.addItem("All");
		cbStatus.addItem("Available");
		cbStatus.addItem("Not Available");
		cbStatus.addItem("Borrowed");
		contentPane.add(cbStatus);
		
		JLabel lblStatus = new JLabel("User Type");
		lblStatus.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblStatus.setBounds(198, 518, 80, 30);
		contentPane.add(lblStatus);
		
		tblUser = new JTable();
		tblUser.setColumnSelectionAllowed(true);
		tblUser.setCellSelectionEnabled(true);
		tblUser.setBorder(new LineBorder(new Color(0, 0, 0)));
		tblUser.setBounds(10, 11, 1036, 402);
		contentPane.add(tblUser);
		
	}
	Connection conn;
	PreparedStatement pst;
	ResultSet rs;
	
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
}