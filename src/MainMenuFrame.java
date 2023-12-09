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
		setTitle("Main Menu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1100, 711);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 255, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnRegisterBooks = new JButton("Register Books");
		btnRegisterBooks.setForeground(new Color(0, 0, 0));
		btnRegisterBooks.setFont(new Font("Microsoft YaHei UI Light", Font.BOLD, 60));
		btnRegisterBooks.setBackground(new Color(204, 255, 153));
		btnRegisterBooks.setBorderPainted(false);
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
		btnRegisterBooks.setBounds(10, 11, 519, 305);
		contentPane.add(btnRegisterBooks);
		
		JButton btnSearchBooks = new JButton("Search");
		btnSearchBooks.setFont(new Font("Microsoft YaHei UI Light", Font.BOLD, 60));
		btnSearchBooks.setBackground(new Color(153, 255, 153));
		btnSearchBooks.setBorderPainted(false);
		btnSearchBooks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SearchType searchType = new SearchType();
				searchType.setVisible(true);
				dispose();
			}
		});
		btnSearchBooks.setBounds(555, 11, 519, 305);
		contentPane.add(btnSearchBooks);
		
		JButton btnRegisterUser = new JButton("Manage Users");
		btnRegisterUser.setFont(new Font("Microsoft YaHei UI Light", Font.BOLD, 60));
		btnRegisterUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserTypeFrame user = new UserTypeFrame();
				user.setVisible(true);
				dispose();
			}
		});
		btnRegisterUser.setBackground(new Color(102, 255, 153));
		btnRegisterUser.setBorderPainted(false);
		btnRegisterUser.setBounds(10, 327, 519, 290);
		contentPane.add(btnRegisterUser);
		
		JButton btnLogOut = new JButton("Log out");
		btnLogOut.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnLogOut.setBackground(new Color(157, 179, 227));
		btnLogOut.setForeground(new Color(0, 0, 0));
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LogInFrame logInFrame = new LogInFrame();
				logInFrame.setVisible(true);
				dispose();
			}
		});
		btnLogOut.setBorderPainted(false);
		btnLogOut.setBounds(10, 628, 93, 33);
		contentPane.add(btnLogOut);
		
		JButton btnTransac = new JButton("Transactions");
		btnTransac.setFont(new Font("Microsoft YaHei UI Light", Font.BOLD, 60));
		btnTransac.setBackground(new Color(102, 255, 204));
		btnTransac.setBorderPainted(false);
		btnTransac.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TransactionFrame transacFrame = new TransactionFrame();
				transacFrame.setVisible(true);
				dispose();
			}
		});
		btnTransac.setBounds(555, 327, 519, 290);
		contentPane.add(btnTransac);
	}
}
