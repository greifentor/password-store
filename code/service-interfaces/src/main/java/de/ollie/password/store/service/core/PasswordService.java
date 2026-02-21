package de.ollie.password.store.service.core;

import de.ollie.password.store.service.code.model.PasswordEntry;
import java.util.List;

public interface PasswordService {
	void addNewPasswordEntry(PasswordEntry passwordEntry);

	List<PasswordEntry> findAllEntries();

	void persistAllEntries();
}
