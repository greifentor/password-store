package de.ollie.password.store.gui.swing;

import static de.ollie.password.store.gui.swing.Constants.HGAP;
import static de.ollie.password.store.gui.swing.Constants.VGAP;

import de.ollie.password.store.gui.swing.MenuFactory.Identifier;
import de.ollie.password.store.gui.swing.MenuFactory.MenuObserver;
import de.ollie.password.store.gui.swing.PasswordListPanel.ListActionObserver;
import de.ollie.password.store.service.code.model.PasswordEntry;
import de.ollie.password.store.service.core.CryptoService;
import de.ollie.password.store.service.core.PasswordService;
import jakarta.inject.Named;
import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
class PasswordStoreMainFrame extends JFrame implements ListActionObserver, MenuObserver {

	private final ApplicationConfiguration configuration;
	private final PasswordService passwordService;
	private final CryptoService cryptoService;
	private final MenuFactory menuFactory;

	private String password;

	public void showFrame() {
		password = JOptionPane.showInputDialog("Enter your password!");
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
			SwingUtilities.updateComponentTreeUI(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		setTitle("Password Store (" + configuration.getVersion() + ")");
		setVisible(true);
		setBounds(20, 20, 640, 480);
		setContentPane(createMainPanel());
		setJMenuBar(menuFactory.create(this));
	}

	private JPanel createMainPanel() {
		JPanel panel = new JPanel(new BorderLayout(HGAP, VGAP));
		panel.add(new PasswordListPanel(this, passwordService), BorderLayout.CENTER);
		return panel;
	}

	@Override
	public void menuItemSelected(Identifier identifier) {
		if (identifier == Identifier.QUIT) {
			System.exit(0);
		}
	}

	@Override
	public void changeRequested(PasswordEntry passwordEntry) {
		new PasswordEntryDialog(passwordEntry, cryptoService, password, this);
	}

	@Override
	public void rightMouseActionDetected(PasswordEntry passwordEntry) {
		try {
			Toolkit
				.getDefaultToolkit()
				.getSystemClipboard()
				.setContents(new StringSelection(cryptoService.decrypt(passwordEntry.getPassword(), password)), null);
		} catch (Exception ex) {
			JOptionPane.showConfirmDialog(
				this,
				"Error while decrypting password!",
				"Error",
				JOptionPane.DEFAULT_OPTION,
				JOptionPane.ERROR_MESSAGE
			);
		}
	}
}
