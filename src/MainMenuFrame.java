import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Panel;

public class MainMenuFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenuFrame frame = new MainMenuFrame();
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
	public MainMenuFrame() {
		setBackground(new Color(255, 255, 255));
		setResizable(false);
		setTitle("Library Management System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1500, 775);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 255, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 128, 128));
		panel.setBounds(0, 0, 240, 736);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnTransac = new JButton("Transactions");
		btnTransac.setForeground(new Color(255, 255, 255));
		btnTransac.setBounds(10, 325, 220, 29);
		panel.add(btnTransac);
		btnTransac.setFont(new Font("Verdana", Font.PLAIN, 15));
		btnTransac.setBackground(new Color(0, 128, 128));
		btnTransac.setBorderPainted(false);
		
		JButton btnRegisterBooks = new JButton("Register Books");
		btnRegisterBooks.setBounds(10, 245, 220, 29);
		panel.add(btnRegisterBooks);
		btnRegisterBooks.setForeground(new Color(255, 255, 255));
		btnRegisterBooks.setFont(new Font("Verdana", Font.PLAIN, 15));
		btnRegisterBooks.setBackground(new Color(0, 128, 128));
		btnRegisterBooks.setBorderPainted(false);
		
		JButton btnRegisterUser = new JButton("Manage Users");
		btnRegisterUser.setForeground(new Color(255, 255, 255));
		btnRegisterUser.setBounds(10, 285, 220, 29);
		panel.add(btnRegisterUser);
		btnRegisterUser.setFont(new Font("Verdana", Font.PLAIN, 15));
		btnRegisterUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserTypeFrame user = new UserTypeFrame();
				user.setVisible(true);
				dispose();
			}
		});
		btnRegisterUser.setBackground(new Color(0, 128, 128));
		btnRegisterUser.setBorderPainted(false);
		
		JButton btnReturnBooks = new JButton("Returning");
		btnReturnBooks.setForeground(new Color(255, 255, 255));
		btnReturnBooks.setBounds(10, 365, 220, 29);
		panel.add(btnReturnBooks);
		btnReturnBooks.setFont(new Font("Verdana", Font.PLAIN, 15));
		btnReturnBooks.setBackground(new Color(0, 128, 128));
		btnReturnBooks.setBorderPainted(false);
		
		JButton btnLogOut = new JButton("Log out");
		btnLogOut.setBounds(10, 692, 220, 33);
		panel.add(btnLogOut);
		btnLogOut.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnLogOut.setBackground(new Color(65, 105, 225));
		btnLogOut.setForeground(new Color(255, 255, 255));
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LogInFrame logInFrame = new LogInFrame();
				logInFrame.setVisible(true);
				dispose();
			}
		});
		btnLogOut.setBorderPainted(false);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 51, 102));
		panel_1.setForeground(new Color(255, 255, 255));
		panel_1.setBounds(0, 0, 1484, 49);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JPanel contentPanel = new JPanel();
		contentPanel.setBounds(239, 48, 1245, 688);
		contentPane.add(contentPanel);
		btnReturnBooks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LendingBooksFrame returning = new LendingBooksFrame();
				returning.setVisible(true);
				dispose();
			}
		});
		btnRegisterBooks.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				RegisterBooksFrame registerFrame;
				try {
					registerFrame = new RegisterBooksFrame();
					registerFrame.setVisible(true);
					dispose();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnTransac.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TransactionFrame transacFrame = new TransactionFrame();
				contentPanel.add(transacFrame);
				transacFrame.setVisible(true);
			}
		});
	}
}
