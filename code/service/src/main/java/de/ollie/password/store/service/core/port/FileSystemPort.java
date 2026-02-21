package de.ollie.password.store.service.core.port;

import de.ollie.password.store.service.code.model.PasswordEntry;
import java.util.List;

public interface FileSystemPort {
	boolean isPasswordEntryListFileExisting(String fileName);

	List<PasswordEntry> readPasswordEntryList(String fileName);

	void writePasswordEntryList(String fileName, List<PasswordEntry> passwordEntryList);
}
