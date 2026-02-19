package de.ollie.password.store.gui.swing;

import static de.ollie.password.store.gui.swing.Constants.HGAP;
import static de.ollie.password.store.gui.swing.Constants.VGAP;

import de.ollie.password.store.service.code.model.PasswordEntry;
import de.ollie.password.store.service.core.CryptoService;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class PasswordEntryDialog extends JDialog {

	private final CryptoService cryptoService;
	private final PasswordEntry passwordEntry;
	private final String secret;

	private JTextField labelField;
	private JPasswordField passwordField;
	private JButton okButton;
	private JButton cancelButton;

	public PasswordEntryDialog(PasswordEntry passwordEntry, CryptoService cryptoService, String secret, JFrame parent) {
		super(parent, "Account");
		this.cryptoService = cryptoService;
		this.passwordEntry = passwordEntry;
		this.secret = secret;
		createMainPanel(passwordEntry);
		pack();
		setVisible(true);
		setModal(true);
	}

	private void createMainPanel(PasswordEntry passwordEntry) {
		JPanel mainPanel = new JPanel(new BorderLayout(HGAP, VGAP));
		mainPanel.setBorder(BorderFactory.createEmptyBorder(HGAP, VGAP, HGAP, VGAP));
		JPanel labelPanel = new JPanel(new GridLayout(2, 1, HGAP, VGAP));
		JPanel inputPanel = new JPanel(new GridLayout(2, 1, HGAP, VGAP));
		JLabel labelLabel = new JLabel("Label:");
		labelField = new JTextField(40);
		labelField.setText(passwordEntry.getLabel());
		JLabel passwordLabel = new JLabel("Password:");
		passwordField = new JPasswordField(40);
		passwordField.setText(cryptoService.decrypt(passwordEntry.getPassword(), secret));

		labelPanel.add(labelLabel);
		labelPanel.add(passwordLabel);
		inputPanel.add(labelField);
		inputPanel.add(passwordField);

		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		okButton = new JButton("Ok");
		cancelButton = new JButton("Abbruch");

		okButton.addActionListener(e -> saveFieldsToData());
		cancelButton.addActionListener(e -> dispose());

		buttonPanel.add(okButton);
		buttonPanel.add(cancelButton);

		mainPanel.add(labelPanel, BorderLayout.WEST);
		mainPanel.add(inputPanel, BorderLayout.CENTER);
		mainPanel.add(buttonPanel, BorderLayout.SOUTH);

		add(mainPanel);
	}

	private void saveFieldsToData() {
		passwordEntry.setLabel(labelField.getText());
		passwordEntry.setPassword(cryptoService.encrypt(new String(passwordField.getPassword()), secret));
		dispose();
	}
}
