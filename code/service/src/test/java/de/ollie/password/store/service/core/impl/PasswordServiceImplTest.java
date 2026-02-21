package de.ollie.password.store.service.core.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import de.ollie.password.store.service.code.model.PasswordEntry;
import de.ollie.password.store.service.core.configuration.PasswordServiceConfiguration;
import de.ollie.password.store.service.core.port.FileSystemPort;
import java.util.List;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

@ExtendWith(MockitoExtension.class)
class PasswordServiceImplTest {

	private static final String FILE_NAME = "file-name";

	@Mock
	private FileSystemPort fileSystemPort;

	@Mock
	private PasswordEntry passwordEntry;

	@Mock
	private PasswordServiceConfiguration passwordServiceConfiguration;

	@InjectMocks
	private PasswordServiceImpl unitUnderTest;

	@Nested
	class findAllEntries {

		@Test
		void returnsAnEmptyList() {
			assertTrue(unitUnderTest.findAllEntries().isEmpty());
		}
	}

	@Nested
	class addNewPasswordEntry_PasswordEntry {

		@Test
		void throwsAnException_passingANullValue_asPasswordEntry() {
			assertThrows(IllegalArgumentException.class, () -> unitUnderTest.addNewPasswordEntry(null));
		}

		@Test
		void addsThePassedEntry_toList() {
			// Run
			unitUnderTest.addNewPasswordEntry(passwordEntry);
			// Check
			assertSame(passwordEntry, unitUnderTest.findAllEntries().get(0));
		}
	}

	@Nested
	class persistAllEntries {

		@Test
		void transfersAllEntriesToPersistence() {
			// Prepare
			ReflectionTestUtils.setField(unitUnderTest, "passwordEntries", List.of(passwordEntry));
			when(passwordServiceConfiguration.getPasswordEntryFileName()).thenReturn(FILE_NAME);
			// Run
			unitUnderTest.persistAllEntries();
			// Check
			verify(fileSystemPort, times(1)).writePasswordEntryList(FILE_NAME, List.of(passwordEntry));
		}
	}

	@Nested
	class postConstruct {

		@Test
		void doesNotLoadAnything_whenNotFileIsPresent() {
			// Prepare
			when(fileSystemPort.isPasswordEntryListFileExisting(FILE_NAME)).thenReturn(false);
			when(passwordServiceConfiguration.getPasswordEntryFileName()).thenReturn(FILE_NAME);
			// Run
			unitUnderTest.postConstruct();
			// Check
			verify(fileSystemPort, never()).readPasswordEntryList(FILE_NAME);
		}

		@Test
		void loadsPasswordListFromPersistence() {
			// Prepare
			when(fileSystemPort.isPasswordEntryListFileExisting(FILE_NAME)).thenReturn(true);
			when(fileSystemPort.readPasswordEntryList(FILE_NAME)).thenReturn(List.of(passwordEntry));
			when(passwordServiceConfiguration.getPasswordEntryFileName()).thenReturn(FILE_NAME);
			// Run
			unitUnderTest.postConstruct();
			// Check
			assertEquals(List.of(passwordEntry), unitUnderTest.findAllEntries());
		}
	}
}
