package de.ollie.password.store.gui.swing;

import de.ollie.password.store.gui.swing.MenuFactory.Identifier;
import de.ollie.password.store.gui.swing.MenuFactory.Observer;
import jakarta.inject.Named;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
class PasswordStoreMainFrame extends JFrame implements Observer {

	private static final int HGAP = 3;
	private static final int VGAP = 3;

	private final ApplicationConfiguration configuration;
	private final MenuFactory menuFactory;

	public void showFrame() {
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
		panel.add(new JLabel(";ob"));
		return panel;
	}

	@Override
	public void menuItemSelected(Identifier identifier) {
		if (identifier == Identifier.QUIT) {
			System.exit(0);
		}
	}
}
