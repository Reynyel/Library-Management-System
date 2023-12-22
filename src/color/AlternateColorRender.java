package color;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class AlternateColorRender extends DefaultTableCellRenderer{

	 	private static final Color ODD_ROW_COLOR = Color.WHITE;
	    private static final Color EVEN_ROW_COLOR = Color.cyan;
	    
	    @Override
	    public Component getTableCellRendererComponent(
	            JTable table, Object value, boolean isSelected,
	            boolean hasFocus, int row, int column) {

	        Component component = super.getTableCellRendererComponent(
	                table, value, isSelected, hasFocus, row, column);

	        // Set alternating row colors
	        Color rowColor = (row % 2 == 0) ? EVEN_ROW_COLOR : ODD_ROW_COLOR;
	        component.setBackground(rowColor);

	        return component;
	    }
	    
	    

}


