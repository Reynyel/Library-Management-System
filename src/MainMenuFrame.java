import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

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
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnRegisterBooks = new JButton("Register Books");
		btnRegisterBooks.setBounds(193, 139, 148, 108);
		contentPane.add(btnRegisterBooks);
		
		JButton btnSearchBooks = new JButton("Search Books");
		btnSearchBooks.setBounds(406, 139, 148, 108);
		contentPane.add(btnSearchBooks);
		
		JButton btnRegisterUser = new JButton("Register Users");
		btnRegisterUser.setBounds(193, 312, 148, 108);
		contentPane.add(btnRegisterUser);
		
		JButton btnLogOut = new JButton("Log out");
		btnLogOut.setBounds(10, 527, 93, 33);
		contentPane.add(btnLogOut);
	}
}
