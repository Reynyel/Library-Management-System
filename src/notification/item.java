package notification;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

public class item extends JPanel {

	/**
	 * Create the panel.
	 */
	public item(String name, String desc) {
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setText(name);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setForeground(new Color(192, 192, 192));
		lblDescription.setText(desc);
		
		
		JLabel lblADayAgo = new JLabel("a day ago");
		lblADayAgo.setForeground(Color.LIGHT_GRAY);
		setLayout(new MigLayout("", "[55px][55px]", "[14px][14px]"));
		add(lblNewLabel, "cell 0 0,alignx left,aligny top");
		add(lblDescription, "cell 1 0,alignx left,aligny top");
		add(lblADayAgo, "cell 0 1,alignx left,aligny top");
		
		setLayout(new MigLayout("wrap 1", "[grow]", "[grow]"));
	}

}
