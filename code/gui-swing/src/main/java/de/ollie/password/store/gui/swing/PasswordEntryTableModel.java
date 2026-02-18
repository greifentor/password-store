package de.ollie.password.store.gui.swing;

import static de.ollie.baselib.util.Check.ensure;

import de.ollie.password.store.service.code.model.PasswordEntry;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class PasswordEntryTableModel extends AbstractTableModel {

	static final int COLUMN_COUNT = 1;

	private static final String[] COLUMN_NAME = { "Account", "" };

	private final List<PasswordEntry> passwordEntries;

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		ensureValidColumnIndex(columnIndex);
		return String.class;
	}

	private void ensureValidColumnIndex(int columnIndex) {
		ensure((columnIndex >= 0) && (columnIndex < COLUMN_COUNT), "column index cannot be less than 0 or greater than 2!");
	}

	@Override
	public int getColumnCount() {
		return COLUMN_COUNT;
	}

	@Override
	public String getColumnName(int columnIndex) {
		ensureValidColumnIndex(columnIndex);
		return COLUMN_NAME[columnIndex];
	}

	@Override
	public int getRowCount() {
		return passwordEntries.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		ensureValidColumnIndex(columnIndex);
		ensureValidRowIndex(rowIndex);
		PasswordEntry pe = passwordEntries.get(rowIndex);
		return pe.getLabel();
	}

	private void ensureValidRowIndex(int rowIndex) {
		ensure(
			(rowIndex >= 0) && (rowIndex < passwordEntries.size()),
			"row index cannot be less than 0 or greater than " + (passwordEntries.size() - 1) + "!"
		);
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		ensureValidColumnIndex(columnIndex);
		ensureValidRowIndex(rowIndex);
		return columnIndex == 1;
	}
}
