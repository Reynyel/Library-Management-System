import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class SearchUserType extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchUserType frame = new SearchUserType();
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
	public SearchUserType() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1072, 750);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(51, 102, 102));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton srchBookBtn = new JButton("Search for Students");
		srchBookBtn.setFont(new Font("Microsoft JhengHei UI Light", Font.BOLD, 50));
		srchBookBtn.setBackground(new Color(176, 224, 230));
		srchBookBtn.setBorderPainted(false);
		srchBookBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SearchStudentsFrame searchStudents = new SearchStudentsFrame();
				searchStudents.setVisible(true);
				dispose();
			}
		});
		srchBookBtn.setBounds(25, 11, 500, 645);
		contentPane.add(srchBookBtn);
		
		JButton srchStudentBtn = new JButton("Search for Employees");
		srchStudentBtn.setFont(new Font("Microsoft YaHei UI Light", Font.BOLD, 50));
		srchStudentBtn.setBackground(new Color(135, 206, 250));
		srchStudentBtn.setBorderPainted(false);
		srchStudentBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SearchEmployeesFrame searchStudents = new SearchEmployeesFrame();
				searchStudents.setVisible(true);
				dispose();
			}
		});
		srchStudentBtn.setBounds(535, 11, 500, 645);
		contentPane.add(srchStudentBtn);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SearchType search = new SearchType();
				search.setVisible(true);
				dispose();
			}
		});
		btnBack.setForeground(Color.WHITE);
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBack.setBorderPainted(false);
		btnBack.setBackground(new Color(0, 51, 153));
		btnBack.setBounds(10, 667, 93, 33);
		contentPane.add(btnBack);
	}

}
