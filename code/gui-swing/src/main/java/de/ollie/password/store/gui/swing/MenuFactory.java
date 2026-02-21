package de.ollie.password.store.gui.swing;

import jakarta.inject.Named;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

@Named
class MenuFactory {

	interface MenuObserver {
		void menuItemSelected(Identifier identifier);
	}

	enum Identifier {
		NEW,
		QUIT,
		SAVE,
	}

	JMenuBar create(MenuObserver observer) {
		JMenuBar menuBar = new JMenuBar();
		menuBar.add(createFileMenu(observer));
		menuBar.add(createPasswordEntriesMenu(observer));
		return menuBar;
	}

	JMenu createFileMenu(MenuObserver observer) {
		JMenu menu = new JMenu("File");
		menu.add(createMenuItemSave(observer));
		menu.add(new JSeparator());
		menu.add(createMenuItemQuit(observer));
		return menu;
	}

	JMenuItem createMenuItemQuit(MenuObserver observer) {
		JMenuItem menuItem = new JMenuItem("Quit");
		menuItem.addActionListener(e -> menuItemSelected(observer, Identifier.QUIT));
		return menuItem;
	}

	void menuItemSelected(MenuObserver observer, Identifier identifier) {
		observer.menuItemSelected(identifier);
	}

	JMenuItem createMenuItemSave(MenuObserver observer) {
		JMenuItem menuItem = new JMenuItem("Save");
		menuItem.addActionListener(e -> menuItemSelected(observer, Identifier.SAVE));
		return menuItem;
	}

	JMenu createPasswordEntriesMenu(MenuObserver observer) {
		JMenu menu = new JMenu("Password Entries");
		menu.add(createMenuItemNew(observer));
		return menu;
	}

	JMenuItem createMenuItemNew(MenuObserver observer) {
		JMenuItem menuItem = new JMenuItem("New");
		menuItem.addActionListener(e -> menuItemSelected(observer, Identifier.NEW));
		return menuItem;
	}
}
