package de.ollie.password.store.gui.swing;

import static de.ollie.password.store.gui.swing.PasswordEntryTableModel.COLUMN_COUNT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import de.ollie.password.store.service.code.model.PasswordEntry;
import java.util.List;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PasswordEntryTableModelTest {

	@Mock
	private List<PasswordEntry> passwordEntries;

	@InjectMocks
	private PasswordEntryTableModel unitUnderTest;

	@Nested
	class getColumnClass_int {

		@Test
		void throwsAnException_passingAColumnIndex_lesserZero() {
			assertThrows(IllegalArgumentException.class, () -> unitUnderTest.getColumnClass(-1));
		}

		@Test
		void throwsAnException_passingAColumnIndex_greaterThanOne() {
			assertThrows(IllegalArgumentException.class, () -> unitUnderTest.getColumnClass(COLUMN_COUNT));
		}

		@Test
		void returnsTheCorrectColumnClass_forColumn0() {
			assertEquals(String.class, unitUnderTest.getColumnClass(0));
		}
	}

	@Nested
	class getColumnCount {

		@Test
		void returns1() {
			assertEquals(1, unitUnderTest.getColumnCount());
		}
	}

	@Nested
	class getColumnName_int {

		@Test
		void throwsAnException_passingAColumnIndex_lesserZero() {
			assertThrows(IllegalArgumentException.class, () -> unitUnderTest.getColumnName(-1));
		}

		@Test
		void throwsAnException_passingAColumnIndex_greaterThanOne() {
			assertThrows(IllegalArgumentException.class, () -> unitUnderTest.getColumnName(COLUMN_COUNT));
		}

		@ParameterizedTest
		@CsvSource({ "0,Account" })
		void returnsTheCorrectColumnName_passingAValidColumn(int columnIndex, String expectedColumnName) {
			assertEquals(expectedColumnName, unitUnderTest.getColumnName(columnIndex));
		}
	}

	@Nested
	class getRowCount {

		@Test
		void returnsTheSizeOfThePasswordEntryList() {
			// Prepare
			int expected = 42;
			when(passwordEntries.size()).thenReturn(expected);
			// Run & Check
			assertEquals(expected, unitUnderTest.getRowCount());
		}
	}

	@Nested
	class getValueAt_int_int {

		@Mock
		private PasswordEntry passwordEntry;

		@Test
		void throwsAnException_passingAColumnIndex_lesserZero() {
			assertThrows(IllegalArgumentException.class, () -> unitUnderTest.getValueAt(0, -1));
		}

		@Test
		void throwsAnException_passingAColumnIndex_greaterThanOne() {
			assertThrows(IllegalArgumentException.class, () -> unitUnderTest.getValueAt(0, COLUMN_COUNT));
		}

		@Test
		void throwsAnException_passingARowIndex_lesserZero() {
			assertThrows(IllegalArgumentException.class, () -> unitUnderTest.getValueAt(-1, 0));
		}

		@Test
		void throwsAnException_passingARowIndex_greaterOrEqualThanSizeOfPasswordEntries() {
			// Prepare
			int size = 42;
			when(passwordEntries.size()).thenReturn(size);
			// Run & Check
			assertThrows(IllegalArgumentException.class, () -> unitUnderTest.getValueAt(size, 0));
		}

		@Test
		void returnsTheLabelOfThePasswordEntry_passing0_asColumn() {
			// Prepare
			String expectedLabel = "expected-label";
			when(passwordEntries.size()).thenReturn(1);
			when(passwordEntries.get(0)).thenReturn(passwordEntry);
			when(passwordEntry.getLabel()).thenReturn(expectedLabel);
			// Run & Check
			assertEquals(expectedLabel, unitUnderTest.getValueAt(0, 0));
		}
	}

	@Nested
	class isCellEditable_int_int {

		@Test
		void throwsAnException_passingAColumnIndex_lesserZero() {
			assertThrows(IllegalArgumentException.class, () -> unitUnderTest.isCellEditable(0, -1));
		}

		@Test
		void throwsAnException_passingAColumnIndex_greaterThanOne() {
			assertThrows(IllegalArgumentException.class, () -> unitUnderTest.isCellEditable(0, COLUMN_COUNT));
		}

		@Test
		void throwsAnException_passingARowIndex_lesserZero() {
			assertThrows(IllegalArgumentException.class, () -> unitUnderTest.isCellEditable(-1, 0));
		}

		@Test
		void throwsAnException_passingARowIndex_greaterOrEqualThanSizeOfPasswordEntries() {
			// Prepare
			int size = 42;
			when(passwordEntries.size()).thenReturn(size);
			// Run & Check
			assertThrows(IllegalArgumentException.class, () -> unitUnderTest.isCellEditable(size, 0));
		}

		@ParameterizedTest
		@ValueSource(ints = { 0, 1, 2, 3, 4, 5, 20, 21, 22, 38, 39, 40, 41 })
		void returnsFalse_forColumn0(int row) { // Prepare
			int size = 42;
			when(passwordEntries.size()).thenReturn(size);
			// Run & Check
			assertFalse(unitUnderTest.isCellEditable(row, 0));
		}
	}
}
