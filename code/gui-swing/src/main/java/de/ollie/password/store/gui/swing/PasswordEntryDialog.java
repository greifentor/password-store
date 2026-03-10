package de.ollie.password.store.gui.swing;

import static de.ollie.password.store.gui.swing.Constants.HGAP;
import static de.ollie.password.store.gui.swing.Constants.VGAP;

import de.ollie.password.store.service.code.model.PasswordEntry;
import de.ollie.password.store.service.core.CryptoService;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class PasswordEntryDialog extends JDialog {

	public interface Observer {
		void canceled();
		void okPressedAndDataTransfered();
	}

	private final CryptoService cryptoService;
	private final Observer observer;
	private final PasswordEntry passwordEntry;
	private final String secret;

	private JTextField labelField;
	private JPasswordField passwordField;

	public PasswordEntryDialog(
		PasswordEntry passwordEntry,
		CryptoService cryptoService,
		String secret,
		JFrame parent,
		Observer observer
	) {
		super(parent, "Account");
		this.cryptoService = cryptoService;
		this.observer = observer;
		this.passwordEntry = passwordEntry;
		this.secret = secret;
		createMainPanel();
		pack();
		setVisible(true);
		setModal(true);
	}

	private void createMainPanel() {
		JPanel mainPanel = new JPanel(new BorderLayout(HGAP, VGAP));
		mainPanel.setBorder(BorderFactory.createEmptyBorder(HGAP, VGAP, HGAP, VGAP));
		mainPanel.add(createLabelPanel(), BorderLayout.WEST);
		mainPanel.add(createInputPanel(), BorderLayout.CENTER);
		mainPanel.add(createButtonPanel(), BorderLayout.SOUTH);
		add(mainPanel);
	}

	private JPanel createInputPanel() {
		createTextFieldLabel();
		createTextFieldPassword();
		JPanel panel = new JPanel(new GridLayout(2, 1, HGAP, VGAP));
		panel.add(labelField);
		panel.add(passwordField);
		return panel;
	}

	private void createTextFieldLabel() {
		labelField = new JTextField(40);
		labelField.setText(passwordEntry.getLabel());
	}

	private void createTextFieldPassword() {
		passwordField = new JPasswordField(40);
		passwordField.setText(
			passwordEntry.getPassword().isEmpty() ? "" : cryptoService.decrypt(passwordEntry.getPassword(), secret)
		);
	}

	private JPanel createLabelPanel() {
		JPanel panel = new JPanel(new GridLayout(2, 1, HGAP, VGAP));
		JLabel labelLabel = new JLabel("Label:");
		JLabel passwordLabel = new JLabel("Password:");
		panel.add(labelLabel);
		panel.add(passwordLabel);
		return panel;
	}

	private JPanel createButtonPanel() {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		panel.add(createButtonOk());
		panel.add(createButtonCancel());
		return panel;
	}

	private JButton createButtonOk() {
		JButton button = new JButton("Ok");
		button.addActionListener(e -> saveFieldsToObjectAndCloseDialog());
		return button;
	}

	private void saveFieldsToObjectAndCloseDialog() {
		if (!isEntryValid()) {
			showMessageValidationError();
			return;
		}
		transferData();
		if (observer != null) {
			observer.okPressedAndDataTransfered();
		}
		dispose();
	}

	private boolean isEntryValid() {
		return getValidationErrors().isEmpty();
	}

	private List<String> getValidationErrors() {
		List<String> validationErrors = new ArrayList<>();
		if (labelField.getText().isBlank()) {
			validationErrors.add("- Label cannot be empty");
		}
		if (passwordField.getPassword().length == 0) {
			validationErrors.add("- Password cannot be empty");
		}
		return validationErrors;
	}

	private void showMessageValidationError() {
		JOptionPane.showMessageDialog(
			this,
			"There are validation errors:\n" +
			getValidationErrors().stream().reduce((s0, s1) -> s0 + "\n" + s1).orElse("n/a"),
			"Validation Error",
			JOptionPane.ERROR_MESSAGE
		);
	}

	private void transferData() {
		String password = new String(passwordField.getPassword());
		passwordEntry.setLabel(labelField.getText());
		passwordEntry.setPassword(password.isEmpty() ? "" : cryptoService.encrypt(password, secret));
	}

	private JButton createButtonCancel() {
		JButton button = new JButton("Abbruch");
		button.addActionListener(e -> canceled());
		return button;
	}

	private void canceled() {
		if (observer != null) {
			observer.canceled();
		}
		dispose();
	}
}
