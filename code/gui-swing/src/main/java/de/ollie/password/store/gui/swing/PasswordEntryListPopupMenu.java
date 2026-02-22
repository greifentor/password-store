package de.ollie.password.store.gui.swing;

import java.awt.Component;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class PasswordEntryListPopupMenu {

	public interface Observer {
		void onNew();
		void onDelete();
		void onCopyToClipboard();
	}

	private final JPopupMenu popupMenu = new JPopupMenu();

	public PasswordEntryListPopupMenu(Observer observer) {
		JMenuItem copyToClipboardItem = new JMenuItem("Copy to Clipboard");
		JMenuItem deleteItem = new JMenuItem("Delete");
		JMenuItem newItem = new JMenuItem("New");
		copyToClipboardItem.addActionListener(e -> observer.onCopyToClipboard());
		deleteItem.addActionListener(e -> observer.onDelete());
		newItem.addActionListener(e -> observer.onNew());
		popupMenu.add(copyToClipboardItem);
		popupMenu.add(newItem);
		popupMenu.add(deleteItem);
	}

	public void show(Component parent, int x, int y) {
		popupMenu.show(parent, x, y);
	}

	public JPopupMenu getPopupMenu() {
		return popupMenu;
	}
}
