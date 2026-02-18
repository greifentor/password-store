package de.ollie.password.store.gui.swing;

import static de.ollie.password.store.gui.swing.Constants.HGAP;
import static de.ollie.password.store.gui.swing.Constants.VGAP;

import de.ollie.password.store.service.code.model.PasswordEntry;
import de.ollie.password.store.service.core.PasswordService;
import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

class PasswordListPanel extends JPanel implements MouseListener {

	public interface ListActionObserver {
		void changeRequested(PasswordEntry passwordEntry);
		void rightMouseActionDetected(PasswordEntry passwordEntry);
	}

	private final ListActionObserver changeObserver;
	private final PasswordService passwordService;

	private JTable table;

	PasswordListPanel(ListActionObserver changeObserver, PasswordService passwordService) {
		super(new BorderLayout(HGAP, VGAP));
		this.changeObserver = changeObserver;
		this.passwordService = passwordService;
		List<PasswordEntry> passwordEntries = passwordService.findAllEntries();
		table = new JTable(new PasswordEntryTableModel(passwordEntries));
		table.addMouseListener(this);
		add(new JScrollPane(table), BorderLayout.CENTER);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (isRightButton(e)) {
			if (isPasswordEntrySelected()) {
				fireListActionEventActionSelected();
			} else {
				showMessageNoAccountSelected();
			}
		} else if (isLeftButton(e) && isDoubleClick(e) && isPasswordEntrySelected()) {
			openDialogToChangePasswordEntry();
		}
	}

	private boolean isRightButton(MouseEvent e) {
		return e.getButton() == MouseEvent.BUTTON3;
	}

	private boolean isPasswordEntrySelected() {
		return table.getSelectedColumn() > -1;
	}

	private void fireListActionEventActionSelected() {
		if (isChangeObserverDefined()) {
			PasswordEntry pe = passwordService.findAllEntries().get(table.getSelectedColumn());
			changeObserver.rightMouseActionDetected(pe);
		}
	}

	private boolean isChangeObserverDefined() {
		return changeObserver != null;
	}

	private void showMessageNoAccountSelected() {
		JOptionPane.showMessageDialog(this, "No account selected!");
	}

	private boolean isLeftButton(MouseEvent e) {
		return e.getButton() == MouseEvent.BUTTON1;
	}

	private boolean isDoubleClick(MouseEvent e) {
		return e.getClickCount() == 2;
	}

	private void openDialogToChangePasswordEntry() {
		if (isChangeObserverDefined() && isPasswordEntrySelected()) {
			PasswordEntry pe = passwordService.findAllEntries().get(table.getSelectedColumn());
			changeObserver.changeRequested(pe);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
}
