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
import java.awt.event.ActionEvent;

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
		setTitle("Main Menu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 979, 610);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnRegisterBooks = new JButton("Register Books");
		btnRegisterBooks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterBooksFrame registerFrame = new RegisterBooksFrame();
				registerFrame.setVisible(true);
				dispose();
			}
		});
		btnRegisterBooks.setIcon(new ImageIcon("C:\\Users\\LINDELL\\Projects\\Library-Management-System\\book1.jpg"));
		btnRegisterBooks.setBounds(193, 139, 168, 108);
		btnRegisterBooks.setBorderPainted(false);
		contentPane.add(btnRegisterBooks);
		
		JButton btnSearchBooks = new JButton("Search Books");
		btnSearchBooks.setBounds(551, 139, 148, 108);
		contentPane.add(btnSearchBooks);
		
		JButton btnRegisterUser = new JButton("Register Users");
		btnRegisterUser.setBounds(193, 312, 148, 108);
		contentPane.add(btnRegisterUser);
		
		JButton btnLogOut = new JButton("Log out");
		btnLogOut.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnLogOut.setBackground(new Color(157, 179, 227));
		btnLogOut.setForeground(new Color(255, 255, 255));
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LogInFrame logInFrame = new LogInFrame();
				logInFrame.setVisible(true);
				dispose();
			}
		});
		btnLogOut.setBorderPainted(false);
		btnLogOut.setBounds(10, 527, 93, 33);
		contentPane.add(btnLogOut);
		
		JButton btnReport = new JButton("Report");
		btnReport.setBounds(551, 312, 148, 108);
		contentPane.add(btnReport);
		
		JLabel lblNewLabel = new JLabel("Register Books");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(223, 257, 107, 14);
		contentPane.add(lblNewLabel);
	}
}
