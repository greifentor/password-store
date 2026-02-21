package de.ollie.password.store.service.core.port.impl;

import static de.ollie.baselib.util.Check.ensure;

import de.ollie.password.store.service.code.model.PasswordEntry;
import de.ollie.password.store.service.core.exception.FileSystemException;
import de.ollie.password.store.service.core.port.FileSystemPort;
import jakarta.inject.Named;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

@Named
class DefaultFileSystemAdapter implements FileSystemPort {

	static final String FIELD_SEPARATOR = "|";

	@Override
	public boolean isPasswordEntryListFileExisting(String fileName) {
		return Files.exists(Path.of(fileName));
	}

	@Override
	public List<PasswordEntry> readPasswordEntryList(String fileName) {
		ensure(fileName != null, "file name cannot be null!");
		try {
			return new ArrayList<>(Files.readAllLines(Path.of(fileName)).stream().map(this::mapToModel).toList());
		} catch (Exception e) {
			throw new FileSystemException("some thing went wrong while reading file with name: " + fileName, e);
		}
	}

	private PasswordEntry mapToModel(String line) {
		StringTokenizer st = new StringTokenizer(line, FIELD_SEPARATOR);
		return new PasswordEntry().setLabel(st.nextToken()).setPassword(st.nextToken());
	}

	@Override
	public void writePasswordEntryList(String fileName, List<PasswordEntry> passwordEntryList) {
		ensure(fileName != null, "file name cannot be null!");
		ensure(passwordEntryList != null, "password entry list cannot be null!");
		try {
			Files.writeString(
				Path.of(fileName),
				mapToString(passwordEntryList),
				StandardOpenOption.CREATE,
				StandardOpenOption.TRUNCATE_EXISTING
			);
		} catch (Exception e) {
			throw new FileSystemException("some thing went wrong while reading file with name: " + fileName, e);
		}
	}

	private String mapToString(List<PasswordEntry> passwordEntryList) {
		return passwordEntryList.stream().map(this::mapToString).reduce((s0, s1) -> s0 + "\n" + s1).orElse("");
	}

	private String mapToString(PasswordEntry passwordEntry) {
		return passwordEntry.getLabel() + FIELD_SEPARATOR + passwordEntry.getPassword();
	}
}
