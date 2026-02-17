package de.ollie.password.store.gui.swing;

import static de.ollie.password.store.gui.swing.Constants.HGAP;
import static de.ollie.password.store.gui.swing.Constants.VGAP;

import de.ollie.password.store.service.code.model.PasswordEntry;
import de.ollie.password.store.service.core.PasswordService;
import java.awt.BorderLayout;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

class PasswordListPanel extends JPanel {

	private final PasswordService passwordService;

	PasswordListPanel(PasswordService passwordService) {
		super(new BorderLayout(HGAP, VGAP));
		this.passwordService = passwordService;
		List<PasswordEntry> passwordEntries = passwordService.findAllEntries();
		JTable table = new JTable(new PasswordEntryTableModel(passwordEntries));
		add(new JScrollPane(table), BorderLayout.CENTER);
	}
}
