package tablemodel;
import java.util.Set;

import javax.swing.table.DefaultTableModel;

public class NonEditTableModel extends DefaultTableModel{
	private final Set<Integer> editableColumns;
	
	public NonEditTableModel(Object[][] data, Object[] columnNames, Set<Integer> editableColumns) {
		super(data, columnNames);
		this.editableColumns = editableColumns;
	}
	public boolean isCellEditable(int row, int column) {
		return false;
	}
}
