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

public class Backup extends JPanel {
	private JTextField txtPath;

	/**
	 * Create the panel.
	 */
	public Backup() {
		setLayout(null);
		
		txtPath = new JTextField();
		txtPath.setBounds(165, 165, 292, 20);
		add(txtPath);
		txtPath.setColumns(10);
		
		JButton btnBrowse = new JButton("Browse Path");
		btnBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				browsePath();
			}
		});
		btnBrowse.setBounds(487, 164, 133, 23);
		add(btnBrowse);
		
		JButton btnBackupNow = new JButton("Backup Now");
		btnBackupNow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				backup();
			}
		});
		btnBackupNow.setBounds(165, 236, 112, 23);
		add(btnBackupNow);

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
