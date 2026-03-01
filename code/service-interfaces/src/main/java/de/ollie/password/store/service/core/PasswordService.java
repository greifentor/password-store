package de.ollie.password.store.service.core;

import de.ollie.password.store.service.code.model.PasswordEntry;
import de.ollie.password.store.service.code.model.ValidationResult;
import java.util.List;

public interface PasswordService {
	void addNewPasswordEntry(PasswordEntry passwordEntry);

	void deletePasswordEntry(PasswordEntry passwordEntry);

	List<PasswordEntry> findAllEntriesOrderedByLabel();

	void persistAllEntries();

	ValidationResult validatePasswordEntry(PasswordEntry passwordEntry);
}
