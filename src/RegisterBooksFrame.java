import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegisterBooksFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtTitle;
	private JTextField txrtAuthor;
	private JTextField textISBN;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterBooksFrame frame = new RegisterBooksFrame();
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
	public RegisterBooksFrame() {
		setBackground(new Color(255, 255, 255));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 953, 666);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Book Title");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(10, 64, 80, 14);
		contentPane.add(lblNewLabel);
		
		txtTitle = new JTextField();
		txtTitle.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtTitle.setBounds(92, 63, 629, 20);
		contentPane.add(txtTitle);
		txtTitle.setColumns(10);
		
		JLabel lblAuthors = new JLabel("Author(s)");
		lblAuthors.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAuthors.setBounds(10, 100, 80, 14);
		contentPane.add(lblAuthors);
		
		txrtAuthor = new JTextField();
		txrtAuthor.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txrtAuthor.setColumns(10);
		txrtAuthor.setBounds(92, 99, 629, 20);
		contentPane.add(txrtAuthor);
		
		JLabel lblIsbn = new JLabel("ISBN");
		lblIsbn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblIsbn.setBounds(10, 133, 80, 14);
		contentPane.add(lblIsbn);
		
		textISBN = new JTextField();
		textISBN.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textISBN.setColumns(10);
		textISBN.setBounds(92, 130, 629, 20);
		contentPane.add(textISBN);
		
		JLabel lblPublisher = new JLabel("Publisher");
		lblPublisher.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPublisher.setBounds(10, 164, 80, 14);
		contentPane.add(lblPublisher);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField.setColumns(10);
		textField.setBounds(92, 163, 629, 20);
		contentPane.add(textField);
		
		JLabel lblLanguage = new JLabel("Language");
		lblLanguage.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblLanguage.setBounds(10, 199, 80, 14);
		contentPane.add(lblLanguage);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_1.setColumns(10);
		textField_1.setBounds(92, 198, 97, 20);
		contentPane.add(textField_1);
		
		JButton btnBack = new JButton("Back");
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBack.setForeground(new Color(255, 255, 255));
		btnBack.setBackground(new Color(157, 179, 227));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainMenuFrame menu = new MainMenuFrame();
				menu.setVisible(true);
				dispose();
			}
		});
		btnBack.setBorderPainted(false);
		btnBack.setBounds(10, 583, 93, 33);
		contentPane.add(btnBack);
	}
}
