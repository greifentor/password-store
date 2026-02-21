package de.ollie.password.store.service.core.port.impl;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import de.ollie.password.store.service.code.model.PasswordEntry;
import de.ollie.password.store.service.core.exception.FileSystemException;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DefaultFileSystemAdapterTest {

	private static final String FILE_NAME = "test-file.tf";
	private static final String LABEL_ACCOUNT0 = "Account0";
	private static final String PASSWORD_ACCOUNT0 =
		"oXFcOPCT6NzJb/QLXLuf3F2B6RNGzoGa/wz8GvfLrzFZCb8tqW74HXRTs6z3vsxhAzg71Us=";
	private static final String LABEL_ACCOUNT1 = "Account1";
	private static final String PASSWORD_ACCOUNT1 =
		"cwzGh5rRDhW3GCil+N18HByKpsaGswqtsOl0tO5/qgcYyEhoGracGmyIc+X2c5U379c9YOU=";

	@TempDir
	private File tempDir;

	@InjectMocks
	private DefaultFileSystemAdapter unitUnderTest;

	@Nested
	class readPasswordEntryList_String {

		@Test
		void throwsAnException_passingANullValue_asFileName() {
			assertThrows(IllegalArgumentException.class, () -> unitUnderTest.readPasswordEntryList(null));
		}

		@Test
		void throwsAFileSystemException_whenSomeThingGoesWrong_whileAccessingTheFileSystem() throws Exception {
			// Prepare
			String pathName = Path.of(tempDir.getAbsolutePath(), FILE_NAME).toString();
			// Run & Check
			assertThrows(FileSystemException.class, () -> unitUnderTest.readPasswordEntryList(pathName));
		}

		@Test
		void returnsACorrectListOfPasswordEntries() throws Exception {
			// Prepare
			Files.writeString(
				Path.of(tempDir.getAbsolutePath(), FILE_NAME),
				LABEL_ACCOUNT0 + "|" + PASSWORD_ACCOUNT0 + "\n" + LABEL_ACCOUNT1 + "|" + PASSWORD_ACCOUNT1,
				StandardOpenOption.CREATE_NEW
			);
			// Run
			List<PasswordEntry> returned = unitUnderTest.readPasswordEntryList(
				Path.of(tempDir.getAbsolutePath(), FILE_NAME).toString()
			);
			// Check
			assertEquals(LABEL_ACCOUNT0, returned.get(0).getLabel());
			assertEquals(PASSWORD_ACCOUNT0, returned.get(0).getPassword());
			assertEquals(LABEL_ACCOUNT1, returned.get(1).getLabel());
			assertEquals(PASSWORD_ACCOUNT1, returned.get(1).getPassword());
		}
	}

	@Nested
	class writePasswordEntryList_String_ListPasswordEntry {

		private List<PasswordEntry> passwordEntries;

		@BeforeEach
		void beforeEach() {
			passwordEntries = new ArrayList<>();
		}

		@Test
		void throwsAnException_passingANullValue_asFileName() {
			assertThrows(IllegalArgumentException.class, () -> unitUnderTest.writePasswordEntryList(null, passwordEntries));
		}

		@Test
		void throwsAnException_passingANullValue_asListPasswordEntry() {
			assertThrows(IllegalArgumentException.class, () -> unitUnderTest.writePasswordEntryList(FILE_NAME, null));
		}

		@Test
		void throwsAFileSystemException_whenSomeThingGoesWrong_whileAccessingTheFileSystem() {
			String pathName = "does/not/exists.file";
			assertThrows(FileSystemException.class, () -> unitUnderTest.writePasswordEntryList(pathName, passwordEntries));
		}

		@Test
		void returnsACorrectListOfPasswordEntries() {
			// Prepare
			String pathName = Path.of(tempDir.getAbsolutePath(), FILE_NAME).toString();
			passwordEntries =
				List.of(
					new PasswordEntry().setLabel(LABEL_ACCOUNT0).setPassword(PASSWORD_ACCOUNT0),
					new PasswordEntry().setLabel(LABEL_ACCOUNT1).setPassword(PASSWORD_ACCOUNT1)
				);
			// Run
			unitUnderTest.writePasswordEntryList(pathName, passwordEntries);
			// Check
			List<PasswordEntry> returned = unitUnderTest.readPasswordEntryList(pathName);
			assertEquals(LABEL_ACCOUNT0, returned.get(0).getLabel());
			assertEquals(PASSWORD_ACCOUNT0, returned.get(0).getPassword());
			assertEquals(LABEL_ACCOUNT1, returned.get(1).getLabel());
			assertEquals(PASSWORD_ACCOUNT1, returned.get(1).getPassword());
		}

		@Test
		void worksAlsoWhenFileIsWrittenTwice() {
			// Prepare
			String pathName = Path.of(tempDir.getAbsolutePath(), FILE_NAME).toString();
			passwordEntries =
				List.of(
					new PasswordEntry().setLabel(LABEL_ACCOUNT0).setPassword(PASSWORD_ACCOUNT0),
					new PasswordEntry().setLabel(LABEL_ACCOUNT1).setPassword(PASSWORD_ACCOUNT1)
				);
			// Run
			assertDoesNotThrow(() -> {
				unitUnderTest.writePasswordEntryList(pathName, passwordEntries);
				unitUnderTest.writePasswordEntryList(pathName, passwordEntries);
			});
		}
	}
}
