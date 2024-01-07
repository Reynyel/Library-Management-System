import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JPanel;

import GradientBackground.gradientBackground;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class BlockedUsers extends JPanel {
	private JTable table;
	Connection conn;
	PreparedStatement pst;
	ResultSet rs;
	private JTextField txtID;
	
	/**
	 * Create the panel.
	 */
	public BlockedUsers() {
		setLayout(null);
		
		JPanel panel = new gradientBackground();
		panel.setBounds(0, 0, 1346, 718);
		add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(214, 45, 826, 524);
		panel.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null},
			},
			new String[] {
				"User ID", "Name"
			}
		));
		scrollPane.setViewportView(table);
		
		txtID = new JTextField();
		txtID.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtID.setBounds(346, 580, 335, 35);
		panel.add(txtID);
		txtID.setColumns(10);
		
		JButton btnSearch = new JButton("Search ID");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				search();
			}
		});
		btnSearch.setBackground(new Color(0, 147, 217));
		btnSearch.setBounds(698, 580, 146, 35);
		btnSearch.setForeground(new Color(255, 255, 255));
		btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSearch.setBorderPainted(false);
		panel.add(btnSearch);
		
		JButton btnViewAllData = new JButton("View All Data");
		btnViewAllData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				view();
			}
		});
		btnViewAllData.setForeground(Color.WHITE);
		btnViewAllData.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnViewAllData.setBorderPainted(false);
		btnViewAllData.setBackground(new Color(220, 20, 60));
		btnViewAllData.setBounds(861, 580, 146, 35);
		panel.add(btnViewAllData);
		
		JLabel lblBorrowersName = new JLabel("Borrower's ID");
		lblBorrowersName.setForeground(Color.WHITE);
		lblBorrowersName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblBorrowersName.setBounds(235, 585, 113, 30);
		panel.add(lblBorrowersName);
		
		view();
	}
	
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
								
				String sql = "SELECT * FROM Blocked WHERE user_id = ?";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				String id = txtID.getText().toString();
				pstmt.setString(1, id);
				
				ResultSet rs = pstmt.executeQuery();

		        DefaultTableModel tblModel = (DefaultTableModel) table.getModel();	               	
	        	
	        	if(!rs.isBeforeFirst()) {
	        		JOptionPane.showMessageDialog(this, "User not found.");
	        		txtID.setText("");
	        	}
	        	
	        	else {
	        		// Clear existing rows in the table
		        	tblModel.setRowCount(0);	
	        		while(rs.next()) {
	        			//add data until there is none
						String userId = String.valueOf(rs.getInt("user_id"));
						String name = rs.getString("borrower_name");
					
						//array to store data into jtable
						String tbData[] = {userId, name};
									
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
	
	public void view() {
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
								
				String sql = "SELECT * FROM Blocked";
				ResultSet rs = stmt.executeQuery(sql);
							
				DefaultTableModel tblModel = (DefaultTableModel)table.getModel();
				
				// Clear existing rows in the table
	            tblModel.setRowCount(0);
	            
	            
				while(rs.next()) {
					//add data until there is none
					String userId = String.valueOf(rs.getInt("user_id"));
					String name = rs.getString("borrower_name");

					
					//array to store data into jtable
					String tbData[] = {userId, name};
								
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
