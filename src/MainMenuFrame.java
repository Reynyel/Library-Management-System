import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.FlowLayout;
import java.awt.BorderLayout;

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
		setBounds(100, 100, 1510, 775);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 255, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 51, 102));
		panel.setBounds(0, 0, 240, 736);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel contentPanel = new JPanel();
		contentPanel.setBounds(239, 50, 1256, 686);
		contentPane.add(contentPanel);
		contentPanel.setLayout(null);
		
		JButton btnLogOut = new JButton("Sign Out");
		btnLogOut.setIcon(new ImageIcon("C:\\Users\\pc\\Library-Management-System\\exit (3).png"));
		btnLogOut.setBounds(10, 667, 220, 32);
		panel.add(btnLogOut);
		btnLogOut.setFont(new Font("Verdana", Font.PLAIN, 15));
		btnLogOut.setBackground(new Color(0, 51, 102));
		btnLogOut.setForeground(new Color(255, 255, 255));
		
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LogInFrame logInFrame = new LogInFrame();
				logInFrame.setVisible(true);
				dispose();
			}
		});
		btnLogOut.setBorderPainted(false);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 240, 100);
		panel.add(lblNewLabel);
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\pc\\Library-Management-System\\Santa Rosa Educational Institution.png"));
		
		JButton btnRegisterBooks = new JButton(" Register Books");
		btnRegisterBooks.setBounds(10, 148, 220, 40);
		panel.add(btnRegisterBooks);
		btnRegisterBooks.setHorizontalAlignment(SwingConstants.LEFT);
		btnRegisterBooks.setIcon(new ImageIcon("C:\\Users\\pc\\Library-Management-System\\book (1).png"));
		btnRegisterBooks.setForeground(new Color(255, 255, 255));
		btnRegisterBooks.setFont(new Font("Verdana", Font.PLAIN, 15));
		btnRegisterBooks.setBackground(new Color(0, 51, 102));
		btnRegisterBooks.setBorderPainted(false);
		
		JPopupMenu popupMenu = new JPopupMenu();
		JMenuItem btnStudents = new JMenuItem("Students");
        JMenuItem btnStaff = new JMenuItem("Faculty/Staff");
        
        popupMenu.add(btnStudents);
        popupMenu.add(btnStaff);
        
		JButton btnRegisterUser = new JButton(" Manage Users");
		btnRegisterUser.setBounds(10, 199, 220, 40);
		panel.add(btnRegisterUser);
		btnRegisterUser.setHorizontalAlignment(SwingConstants.LEFT);
		btnRegisterUser.setIcon(new ImageIcon("C:\\Users\\pc\\Library-Management-System\\management.png"));
		btnRegisterUser.setForeground(new Color(255, 255, 255));
		btnRegisterUser.setFont(new Font("Verdana", Font.PLAIN, 15));
		
		btnRegisterUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				popupMenu.show(btnRegisterUser, 0, btnRegisterUser.getHeight());
			}
		});
		btnRegisterUser.setBackground(new Color(0, 51, 102));
		btnRegisterUser.setBorderPainted(false);
		
		btnStudents.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	RegisterStudent student = new RegisterStudent();
            	student = new RegisterStudent();
            	student.setBounds(25, 0, 1256, 686);
            	student.setLayout(null);  // Set layout to null
	            contentPanel.removeAll();  // Remove existing components
	            contentPanel.add(student);
	            contentPanel.revalidate();  // Revalidate the panel to reflect changes
	            contentPanel.repaint();  // Repaint the panel
            }
        });

		btnStaff.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	RegisterStaff staff = new RegisterStaff();
            	staff = new RegisterStaff();
            	staff.setBounds(25, 0, 1256, 686);
            	staff.setLayout(null);  // Set layout to null
	            contentPanel.removeAll();  // Remove existing components
	            contentPanel.add(staff);
	            contentPanel.revalidate();  // Revalidate the panel to reflect changes
	            contentPanel.repaint();  // Repaint the panel

            }
        });
        
		JButton btnTransac = new JButton(" Transactions");
		btnTransac.setBounds(10, 250, 220, 40);
		panel.add(btnTransac);
		btnTransac.setIcon(new ImageIcon("C:\\Users\\pc\\Library-Management-System\\transaction.png"));
		btnTransac.setHorizontalAlignment(SwingConstants.LEFT);
		btnTransac.setForeground(new Color(255, 255, 255));
		btnTransac.setFont(new Font("Verdana", Font.PLAIN, 15));
		btnTransac.setBackground(new Color(0, 51, 102));
		btnTransac.setBorderPainted(false);
		
		JButton btnReturnBooks = new JButton(" Returning");
		btnReturnBooks.setBounds(10, 301, 220, 40);
		panel.add(btnReturnBooks);
		btnReturnBooks.setHorizontalAlignment(SwingConstants.LEFT);
		btnReturnBooks.setIcon(new ImageIcon("C:\\Users\\pc\\Library-Management-System\\return.png"));
		btnReturnBooks.setForeground(new Color(255, 255, 255));
		btnReturnBooks.setFont(new Font("Verdana", Font.PLAIN, 15));
		btnReturnBooks.setBackground(new Color(0, 51, 102));
		btnReturnBooks.setBorderPainted(false);			                  		
		btnReturnBooks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LendingBooksFrame returning = new LendingBooksFrame();
				returning = new LendingBooksFrame();
				returning.setBounds(25, 0, 1256, 686);
				returning.setLayout(null);  // Set layout to null
	            contentPanel.removeAll();  // Remove existing components
	            contentPanel.add(returning);
	            contentPanel.revalidate();  // Revalidate the panel to reflect changes
	            contentPanel.repaint();  // Repaint the panel
			}
		});
		
		btnTransac.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TransactionFrame transacFrame;
				transacFrame = new TransactionFrame();
				transacFrame.setBounds(25, 0, 1256, 686);
				transacFrame.setLayout(null);  // Set layout to null
	            contentPanel.removeAll();  // Remove existing components
	            contentPanel.add(transacFrame);
	            contentPanel.revalidate();  // Revalidate the panel to reflect changes
	            contentPanel.repaint();  // Repaint the panel

			}
		});
						
		btnRegisterBooks.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				RegisterBooksFrame registerFrame;
				try {
		            registerFrame = new RegisterBooksFrame();
		            registerFrame.setBounds(25, 25, 1256, 686);
		            registerFrame.setLayout(null);  // Set layout to null
		            contentPanel.removeAll();  // Remove existing components
		            contentPanel.add(registerFrame);
		            contentPanel.revalidate();  // Revalidate the panel to reflect changes
		            contentPanel.repaint();  // Repaint the panel
		        } catch (SQLException e1) {
		            e1.printStackTrace();
		        }
				
			}
		});
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 51, 102));
		panel_1.setForeground(new Color(255, 255, 255));
		panel_1.setBounds(239, 0, 1255, 50);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnNotif = new JButton(" Notifications");
		btnNotif.setForeground(new Color(255, 255, 255));
		btnNotif.setBackground(new Color(0, 51, 102));
		btnNotif.setFont(new Font("Verdana", Font.PLAIN, 14));
		btnNotif.setHorizontalAlignment(SwingConstants.LEFT);
		btnNotif.setIcon(new ImageIcon("C:\\Users\\pc\\Library-Management-System\\bell.png"));
		btnNotif.setBounds(1074, 11, 171, 32);
		btnNotif.setBorderPainted(false);
		panel_1.add(btnNotif);
		
		
		
	}
}
