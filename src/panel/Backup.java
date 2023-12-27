package panel;

import javax.swing.JPanel;
import javax.swing.JTextField;

import jnafilechooser.api.JnaFileChooser;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;

public class Backup extends JPanel {
	private JTextField txtPath;

	/**
	 * Create the panel.
	 */
	public Backup() {
		setLayout(null);
		
		txtPath = new JTextField();
		txtPath.setForeground(new Color(255, 255, 255));
		txtPath.setBackground(new Color(0, 153, 204));
		txtPath.setBounds(308, 320, 610, 30);
		add(txtPath);
		txtPath.setColumns(10);
		
		JButton btnBrowse = new JButton("Browse Path");
		btnBrowse.setFont(new Font("Verdana", Font.PLAIN, 13));
		btnBrowse.setForeground(new Color(255, 255, 255));
		btnBrowse.setBackground(new Color(153, 51, 51));
		btnBrowse.setBorderPainted(false);
		btnBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				browsePath();
			}
		});
		btnBrowse.setBounds(928, 320, 133, 30);
		add(btnBrowse);
		
		JButton btnBackupNow = new JButton("Backup Now");
		btnBackupNow.setFont(new Font("Verdana", Font.PLAIN, 13));
		btnBackupNow.setBackground(new Color(204, 153, 102));
		btnBackupNow.setForeground(new Color(102, 0, 0));
		btnBackupNow.setBorderPainted(false);
		btnBackupNow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				backup();
			}
		});
		btnBackupNow.setBounds(428, 363, 390, 30);
		add(btnBackupNow);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\pc\\Library-Management-System\\res\\Backup Records.png"));
		lblNewLabel.setBounds(0, 0, 1256, 686);
		add(lblNewLabel);

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
