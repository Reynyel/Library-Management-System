package panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import GradientBackground.gradientBackground;

public class BackupDB extends JPanel {

	private JTextField txtPath;

	/**
	 * Create the panel.
	 */	
	public BackupDB() {

		setLayout(null);
		
		JPanel panel = new gradientBackground();
		panel.setBounds(0, 0, 1346, 718);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Backup Database");
		lblNewLabel_1.setBounds(264, 270, 133, 24);
		panel.add(lblNewLabel_1);
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBackground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		txtPath = new JTextField();
		txtPath.setBounds(264, 324, 610, 30);
		panel.add(txtPath);
		txtPath.setForeground(new Color(255, 255, 255));
		txtPath.setBackground(new Color(0, 153, 204));
		txtPath.setColumns(10);
		
		JButton btnBrowse = new JButton("Browse Path");
		btnBrowse.setBounds(884, 324, 133, 30);
		panel.add(btnBrowse);
		btnBrowse.setFont(new Font("Verdana", Font.PLAIN, 13));
		btnBrowse.setForeground(new Color(255, 255, 255));
		btnBrowse.setBackground(new Color(60, 172, 196));
		btnBrowse.setBorderPainted(false);
		
		JButton btnBackupNow = new JButton("Backup Now");
		btnBackupNow.setBounds(384, 367, 390, 30);
		panel.add(btnBackupNow);
		btnBackupNow.setFont(new Font("Verdana", Font.PLAIN, 13));
		btnBackupNow.setBackground(new Color(71, 160, 165));
		btnBackupNow.setForeground(new Color(255, 255, 255));
		btnBackupNow.setBorderPainted(false);
		btnBackupNow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				backup();
			}
		});
		btnBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				browsePath();
			}
		});

	}
	
	String path = null;
	String fileName;
	
	public void browsePath() {
		JFileChooser fc = new JFileChooser();
		fc.showOpenDialog(this);
		String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		
		try {
			File f = fc.getSelectedFile();
			path = f.getAbsolutePath();
			path = path.replace('\\', '/');
			path = path + "_" + date + ".sql";
			txtPath.setText(path);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	    
    }
	    

	
	public void backup() {
		Process p = null;
		try {
			Runtime runtime = Runtime.getRuntime();
			p = runtime.exec("C:/Program Files/MySQL/MySQL Server 8.0/bin/mysqldump.exe -uroot -pranielle25 --add-drop-database -B booksdb -r" + path);
			
			int processComplete = p.waitFor();
			
			if(processComplete == 0) {
				JOptionPane.showMessageDialog(getRootPane(), "Backup Created Succesfully");
			}
			else {
				JOptionPane.showMessageDialog(getRootPane(), "Can't Create Backup");
			}
		}
		catch(Exception e) {
			
		}
	}
}
