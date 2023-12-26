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
import javax.swing.ImageIcon;

public class Dashboard extends JPanel {

	private JPanel panel;
	
	private StudentSections section;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Dashboard frame = new Dashboard();
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
	public Dashboard() {
		setPreferredSize(new Dimension(1256, 686));
	    setLayout(null);
		setBounds(100, 100, 1687, 743);
		
		panel = new JPanel();
        panel.setBounds(0, 0, 1256, 686);
        add(panel);
        panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Overdue Book Loans");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Orbitron", Font.PLAIN, 25));
		lblNewLabel_1.setBounds(10, 370, 366, 40);
		panel.add(lblNewLabel_1);
		
		table = new JTable();
		table.setBackground(new Color(255, 255, 255));
		table.setBounds(10, 409, 783, 266);
		panel.add(table);
		
		JLabel lblNewLabel_1_1 = new JLabel("Borrowed Books");
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("Orbitron", Font.PLAIN, 25));
		lblNewLabel_1_1.setBounds(969, 11, 247, 40);
		panel.add(lblNewLabel_1_1);
		
		table_1 = new JTable();
		table_1.setBackground(Color.WHITE);
		table_1.setBounds(969, 50, 277, 312);
		panel.add(table_1);
		
		lblNewLabel_1_2 = new JLabel("Book Circulation History");
		lblNewLabel_1_2.setForeground(Color.WHITE);
		lblNewLabel_1_2.setFont(new Font("Orbitron", Font.PLAIN, 20));
		lblNewLabel_1_2.setBounds(803, 373, 382, 40);
		panel.add(lblNewLabel_1_2);
		
		table_2 = new JTable();
		table_2.setBackground(Color.WHITE);
		table_2.setBounds(803, 409, 443, 266);
		panel.add(table_2);
		
		panel_1 = new JPanel();
		panel_1.setBounds(38, 50, 208, 266);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		btnNewButton_3 = new JButton("");
		btnNewButton_3.setBounds(0, 0, 208, 266);
		panel_1.add(btnNewButton_3);
		
		panel_2 = new JPanel();
		panel_2.setBounds(256, 50, 208, 266);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		btnNewButton_4 = new JButton("");
		btnNewButton_4.setBackground(new Color(255, 255, 255));
		btnNewButton_4.setBounds(0, 0, 208, 266);
		panel_2.add(btnNewButton_4);
		
		panel_3 = new JPanel();
		panel_3.setBounds(474, 50, 208, 266);
		panel.add(panel_3);
		panel_3.setLayout(null);
		
		btnNewButton_5 = new JButton("");
		btnNewButton_5.setBackground(new Color(255, 255, 255));
		btnNewButton_5.setBounds(0, 0, 208, 266);
		panel_3.add(btnNewButton_5);
		
		panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setBounds(692, 50, 208, 266);
		panel.add(panel_4);
		
		btnNewButton = new JButton("");
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setBounds(0, 0, 208, 266);
		panel_4.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBackground(new Color(0, 0, 0));
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\pc\\Library-Management-System\\Untitled design.png"));
		lblNewLabel.setBounds(0, 0, 1256, 686);
		panel.add(lblNewLabel);
	}
	
	Connection conn;
	PreparedStatement pst;
	ResultSet rs;
	private JTable table;
	private JTable table_1;
	private JLabel lblNewLabel_1_2;
	private JTable table_2;
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel_3;
	private JButton btnNewButton_3;
	private JButton btnNewButton_4;
	private JButton btnNewButton_5;
	private JPanel panel_4;
	private JButton btnNewButton;
	
}
