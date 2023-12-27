package color;

import java.awt.Color;
import java.awt.Component;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class AlternateColorRender extends DefaultTableCellRenderer{

	private static final Color AVAILABLE_COLOR = Color.GREEN;
    private static final Color BORROWED_COLOR = Color.RED;

    private String[][] bookStatusArray; // Assuming a 2D array for book status

    public void setBookStatusArray(String[][] bookStatusArray) {
        this.bookStatusArray = bookStatusArray;
    }

    @Override
    public Component getTableCellRendererComponent(
            JTable table, Object value, boolean isSelected,
            boolean hasFocus, int row, int column) {

        Component component = super.getTableCellRendererComponent(
                table, value, isSelected, hasFocus, row, column);

        // Check if book status information is available
        if (bookStatusArray != null && row < bookStatusArray.length) {
            String bookStatus = bookStatusArray[row][column];

            // Set row color based on book status
            if ("available".equalsIgnoreCase(bookStatus)) {
                component.setBackground(AVAILABLE_COLOR);
            } else if ("borrowed".equalsIgnoreCase(bookStatus)) {
                component.setBackground(BORROWED_COLOR);
            } else {
                // Default color if status is not recognized
                component.setBackground(Color.WHITE);
            }
        } else {
            // Default color if book status information is not available
            component.setBackground(Color.WHITE);
        }

        return component;
    }
	    

}


