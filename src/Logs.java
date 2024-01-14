import javax.swing.JPanel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import GradientBackground.gradientBackground;
import java.awt.Font;

public class Logs extends JPanel {
	private JTable table;

	/**
	 * Create the panel.
	 */
	public Logs() {
		setLayout(null);
		
		JPanel panel = new gradientBackground();
		panel.setBounds(0, 0, 1425, 980);
		add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(319, 169, 655, 400);
		panel.add(scrollPane);
		
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 15));
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
			},
			new String[] {
				"User", "Date", "Time in", "Time out"
			}
		));
		scrollPane.setViewportView(table);
		
		viewLogs();
	}
	
	Connection conn;
	PreparedStatement pst;
	ResultSet rs;
	
	public void viewLogs() {
		try {		
			 // Load the JDBC driver (version 4.0 or later)
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}   		
			
			try {
				
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/booksDB", "root", "ranielle25");
				Statement stmt = conn.createStatement();
				System.out.println("Connected");
								
				String sql = "SELECT * FROM Logs";
				PreparedStatement pstmt = conn.prepareStatement(sql);
			
				ResultSet rs = pstmt.executeQuery();

		        DefaultTableModel tblModel = (DefaultTableModel) table.getModel();	        
	       	
	        	
	        	if(!rs.isBeforeFirst()) {
	        		JOptionPane.showMessageDialog(this, "Record not found.");
	        	}
	        	
	        	else {
	        		// Clear existing rows in the table
		        	tblModel.setRowCount(0);	
	        		while(rs.next()) {
	       			//add data until there is none
	       			String user = rs.getString("user_name");
	       			String date = rs.getString("log_date");
	       			String timeIn = rs.getString("time_in");
	       			String timeOut = rs.getString("time_out");
     	       			
	       			//array to store data into jtable
	       			String tbData[] = {user, date, timeIn, timeOut};
	       			
       			
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
}
