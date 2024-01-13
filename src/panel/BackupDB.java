package panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
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
import javax.swing.JComboBox;
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
		lblNewLabel_1.setBounds(270, 116, 133, 24);
		panel.add(lblNewLabel_1);
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBackground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		txtPath = new JTextField();
		txtPath.setBounds(270, 151, 610, 30);
		panel.add(txtPath);
		txtPath.setForeground(new Color(255, 255, 255));
		txtPath.setBackground(new Color(0, 153, 204));
		txtPath.setColumns(10);
		
		JButton btnBrowse = new JButton("Browse Path");
		btnBrowse.setBounds(890, 151, 133, 30);
		panel.add(btnBrowse);
		btnBrowse.setFont(new Font("Verdana", Font.PLAIN, 13));
		btnBrowse.setForeground(new Color(255, 255, 255));
		btnBrowse.setBackground(new Color(60, 172, 196));
		btnBrowse.setBorderPainted(false);
		
		JButton btnBackupNow = new JButton("Backup Now");
		btnBackupNow.setBounds(390, 192, 390, 30);
		panel.add(btnBackupNow);
		btnBackupNow.setFont(new Font("Verdana", Font.BOLD, 15));
		btnBackupNow.setBackground(new Color(71, 160, 165));
		btnBackupNow.setForeground(new Color(255, 255, 255));
		btnBackupNow.setBorderPainted(false);
		
		JButton btnImportNow = new JButton("Import Now");
		btnImportNow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				importDatabase();
			}
		});
		btnImportNow.setForeground(Color.WHITE);
		btnImportNow.setFont(new Font("Verdana", Font.BOLD, 15));
		btnImportNow.setBorderPainted(false);
		btnImportNow.setBackground(new Color(0, 153, 0));
		btnImportNow.setBounds(390, 476, 390, 30);
		panel.add(btnImportNow);
		
		textField = new JTextField();
		textField.setForeground(Color.WHITE);
		textField.setColumns(10);
		textField.setBackground(new Color(0, 153, 204));
		textField.setBounds(270, 433, 610, 30);
		panel.add(textField);
		
		JLabel lblNewLabel_1_1 = new JLabel("Import Database");
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_1.setBackground(Color.WHITE);
		lblNewLabel_1_1.setBounds(270, 398, 133, 24);
		panel.add(lblNewLabel_1_1);
		
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
	private JTextField textField;
	
	//select file path before exporting
	public void browsePath() {
	    JFileChooser fc = new JFileChooser();
	    fc.showOpenDialog(this);
	    String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

	    try {
	        File f = fc.getSelectedFile();

	        // Check if the selected file is null
	        if (f == null) {
	            return; // User canceled the file selection
	        }

	        path = f.getAbsolutePath();
	        path = path.replace('\\', '/');
	        path = path + "_" + date + ".sql";
	        txtPath.setText(path);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	//import mysql dump
	public void importDatabase() {
	    JFileChooser fc = new JFileChooser();
	    fc.showOpenDialog(this);

	    try {
	        File selectedFile = fc.getSelectedFile();
	        String filePath = selectedFile.getAbsolutePath();

	        // Modify the MySQL connection details accordingly
	        String command = "C:/Program Files/MySQL/MySQL Server 8.0/bin/mysql.exe";
	        String user = "-uroot";
	        String password = "-pranielle25";
	        String database = "-Dbooksdb";

	        // Run the command in a separate thread
	        Thread importThread = new Thread(() -> {
	            try {
	                ProcessBuilder processBuilder = new ProcessBuilder(command, user, password, database);
	                processBuilder.redirectInput(new File(filePath)); // Redirect input from the file

	                Process process = processBuilder.start();

	                // Wait for the process to complete
	                int processComplete = process.waitFor();

	                if (processComplete == 0) {
	                    System.out.println("Import Successful");
	                    JOptionPane.showMessageDialog(getRootPane(), "Import Successful");
	                } else {
	                    System.out.println("Import Failed");
	                    JOptionPane.showMessageDialog(getRootPane(), "Import Failed");
	                }
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        });

	        importThread.start();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	//export mysql dump
	public void backup() {
		Process p = null;
		try {
			Runtime runtime = Runtime.getRuntime();
			p = runtime.exec("C:/Program Files/MySQL/MySQL Server 8.0/bin/mysqldump.exe -uroot -pranielle25 --routines --add-drop-database -B booksdb -r" + path);
			
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
