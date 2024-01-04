import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;

public class SearchType extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchType frame = new SearchType();
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
	public SearchType() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1072, 750);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(51, 102, 102));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton srchBookBtn = new JButton("Search for Books");
		srchBookBtn.setFont(new Font("Microsoft JhengHei UI Light", Font.BOLD, 50));
		srchBookBtn.setBackground(new Color(176, 224, 230));
		srchBookBtn.setBorderPainted(false);
		srchBookBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SearchBooks searchBooks = new SearchBooks();
				searchBooks.setVisible(true);
				dispose();
			}
		});
		srchBookBtn.setBounds(25, 11, 500, 645);
		contentPane.add(srchBookBtn);
		
		JButton srchStudentBtn = new JButton("Search for Users");
		srchStudentBtn.setFont(new Font("Microsoft YaHei UI Light", Font.BOLD, 50));
		srchStudentBtn.setBackground(new Color(135, 206, 250));
		srchStudentBtn.setBorderPainted(false);
		srchStudentBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SearchUserType search = new SearchUserType();
				search.setVisible(true);
				dispose();
			}
		});
		srchStudentBtn.setBounds(535, 11, 500, 645);
		contentPane.add(srchStudentBtn);
		
	}
}
