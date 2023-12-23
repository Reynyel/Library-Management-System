package notification;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Path2D;
import java.awt.geom.RoundRectangle2D;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.GroupLayout.Alignment;

import net.miginfocom.swing.MigLayout;

public class Notification extends JPanel {
	private JPanel panel;
	/**
	 * Create the panel.
	 */
	public Notification() {
		setBackground(new Color(255, 255, 255));

		setOpaque(false);
		
		JLabel lblNewLabel = new JLabel("Notifications");
		lblNewLabel.setForeground(new Color(124, 124, 124));
		
		JScrollPane scroll = new JScrollPane();
		scroll.getViewport().setOpaque(false);
		scroll.setViewportBorder(null);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);;
		
		panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setPreferredSize(getPreferredSize());
		scroll.setViewportView(panel);
		panel.setOpaque(false);
		panel.setLayout(null);
		
		JButton btnShowAll = new JButton("Show All");
		btnShowAll.setForeground(new Color(255, 255, 255));
		btnShowAll.setBackground(new Color(116, 177, 228));
		
		panel.setLayout(new MigLayout("inset 0, fillx, wrap", "[fill]", "[][][][][][][][][]"));
		panel.setLayout(new MigLayout("inset 0, fillx, wrap", "[fill]"));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(10)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnShowAll, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
						.addComponent(scroll, GroupLayout.PREFERRED_SIZE, 459, GroupLayout.PREFERRED_SIZE))
					.addGap(25))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(22)
					.addComponent(lblNewLabel)
					.addGap(11)
					.addComponent(scroll, GroupLayout.PREFERRED_SIZE, 292, GroupLayout.PREFERRED_SIZE)
					.addGap(16)
					.addComponent(btnShowAll))
		);
		setLayout(groupLayout);

		loadNotif();
	}
	
	private void loadNotif() {
		
		item item_ = new item("Ranielle Cruz", " Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua");
		item_.setSize(753, 39);
		panel.add(item_, "cell 0 0");
	}
	
	public void paintComponent(Graphics graphics) {
		Graphics2D g2 = (Graphics2D)graphics.create();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(getBackground());
		int header = 10;
		AffineTransform tran = new AffineTransform();
		tran.translate(getWidth()-25, 5);
		tran.rotate(Math.toRadians(45));
		Path2D p = new Path2D.Double(new RoundRectangle2D.Double(0,0,20,20,5,5), tran);
		
		Area area = new Area(p);
		area.add(new Area(new RoundRectangle2D.Double(0, header, getWidth(), getHeight()-header, 10, 10)));
		g2.fill(area);
		g2.dispose();
		super.paintComponent(graphics);
	}
	
	public Dimension getPreferredSize() {
        return new Dimension(250, 400);
    }

}
