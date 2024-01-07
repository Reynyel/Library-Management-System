package color;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class BlockedColorRender extends DefaultTableCellRenderer {
	 public Component getTableCellRendererComponent(
	            JTable table, Object value, boolean isSelected,
	            boolean hasFocus, int row, int column) {
        Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        String status = table.getValueAt(row, column).toString();
	
	        // Check the status and set the background color accordingly
	    if ("Lost".equals(status) || "Damaged".equals(status)) {
	        component.setBackground(Color.RED);
	        component.setForeground(Color.WHITE);
	    } else {
	        // Reset the background color for other statuses
	            component.setBackground(table.getBackground());
	            component.setForeground(table.getForeground());
	        }	
	        return component;
	    }
}
