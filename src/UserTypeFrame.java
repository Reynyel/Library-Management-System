import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 953, 666);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton studentBtn = new JButton("Student");
		studentBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterUser student = new RegisterUser();
				student.setVisible(true);
				dispose();
			}
		});
		studentBtn.setBounds(253, 234, 141, 103);
		contentPane.add(studentBtn);
		
		JButton teacherBtn = new JButton("Teacher");
		teacherBtn.setBounds(495, 234, 141, 103);
		contentPane.add(teacherBtn);
	}
}