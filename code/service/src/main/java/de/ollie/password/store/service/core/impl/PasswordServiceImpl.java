package de.ollie.password.store.service.core.impl;

import static de.ollie.baselib.util.Check.ensure;

import de.ollie.password.store.service.code.model.PasswordEntry;
import de.ollie.password.store.service.core.PasswordService;
import de.ollie.password.store.service.core.configuration.PasswordServiceConfiguration;
import de.ollie.password.store.service.core.port.FileSystemPort;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Named;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
class PasswordServiceImpl implements PasswordService {

	private final FileSystemPort fileSystemPort;
	private final PasswordServiceConfiguration passwordServiceConfiguration;

	private List<PasswordEntry> passwordEntries = new ArrayList<>();

	@PostConstruct
	void postConstruct() {
		String fileName = passwordServiceConfiguration.getPasswordEntryFileName();
		if (fileSystemPort.isPasswordEntryListFileExisting(fileName)) {
			passwordEntries.addAll(fileSystemPort.readPasswordEntryList(fileName));
		}
	}

	@Override
	public void addNewPasswordEntry(PasswordEntry passwordEntry) {
		ensure(passwordEntry != null, "password entry cannot be null!");
		passwordEntries.add(passwordEntry);
	}

	@Override
	public void deletePasswordEntry(PasswordEntry passwordEntry) {
		ensure(passwordEntry != null, "password entry cannot be null!");
		passwordEntries.remove(passwordEntry);
	}

	@Override
	public List<PasswordEntry> findAllEntriesOrderedByLabel() {
		return passwordEntries.stream().sorted((pe0, pe1) -> pe0.getLabel().compareTo(pe1.getLabel())).toList();
	}

	@Override
	public void persistAllEntries() {
		fileSystemPort.writePasswordEntryList(passwordServiceConfiguration.getPasswordEntryFileName(), passwordEntries);
	}
}
