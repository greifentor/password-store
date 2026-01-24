package de.ollie.password.store.gui.swing;

import jakarta.inject.Named;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

@Named
class MenuFactory {

	interface Observer {
		void menuItemSelected(Identifier identifier);
	}

	enum Identifier {
		QUIT,
	}

	JMenuBar create(Observer observer) {
		JMenuBar menuBar = new JMenuBar();
		menuBar.add(createFileMenu(observer));
		return menuBar;
	}

	JMenu createFileMenu(Observer observer) {
		JMenu menu = new JMenu("File");
		menu.add(createMenuItemQuit(observer));
		return menu;
	}

	JMenuItem createMenuItemQuit(Observer observer) {
		JMenuItem menuItem = new JMenuItem("Quit");
		menuItem.addActionListener(e -> menuItemSelected(observer, Identifier.QUIT));
		return menuItem;
	}

	void menuItemSelected(Observer observer, Identifier identifier) {
		observer.menuItemSelected(identifier);
	}
}
