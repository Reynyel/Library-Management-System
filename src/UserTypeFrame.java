import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;

public class UserTypeFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserTypeFrame frame = new UserTypeFrame();
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
	public UserTypeFrame() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1072, 750);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(51, 102, 102));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton studentBtn = new JButton("Register Student");
		studentBtn.setFont(new Font("Microsoft JhengHei UI Light", Font.BOLD, 50));
		studentBtn.setBackground(new Color(204, 255, 255));
		studentBtn.setBorderPainted(false);
		studentBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterStudent student = new RegisterStudent();
				student.setVisible(true);
				dispose();
			}
		});
		studentBtn.setBounds(23, 11, 500, 645);
		contentPane.add(studentBtn);
		
		JButton teacherBtn = new JButton("Register Faculty/Staff");
		teacherBtn.setFont(new Font("Microsoft YaHei UI Light", Font.BOLD, 40));
		teacherBtn.setBackground(new Color(135, 206, 235));
		teacherBtn.setBorderPainted(false);
		teacherBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterStaff staff = new RegisterStaff();
				staff.setVisible(true);
				dispose();
			}
		});
		teacherBtn.setBounds(535, 11, 500, 645);
		contentPane.add(teacherBtn);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainMenuFrame menu = new MainMenuFrame();
				menu.setVisible(true);
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
