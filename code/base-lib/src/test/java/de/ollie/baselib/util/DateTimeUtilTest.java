package de.ollie.baselib.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.LocalTime;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DateTimeUtilTest {

	@Nested
	class constructor {

		@Test
		void throwsAnException_whenCalled() {
			assertThrows(UnsupportedOperationException.class, () -> new DateTimeUtil());
		}
	}

	@Nested
	class dateFromString_String {

		@Test
		void throwsAnException_passingANullValueAsDateStr() {
			assertThrows(NullPointerException.class, () -> DateTimeUtil.dateFromString(null));
		}

		@Test
		void returnsACorrectLocalDate_passingACorrectFormattedString() {
			assertEquals(LocalDate.of(2025, 10, 4), DateTimeUtil.dateFromString("04.10.2025"));
		}
	}

	@Nested
	class timeFromString_String {

		@Test
		void throwsAnException_passingANullValueAsDateStr() {
			assertThrows(NullPointerException.class, () -> DateTimeUtil.timeFromString(null));
		}

		@Test
		void returnsACorrectLocalDate_passingACorrectFormattedString() {
			assertEquals(LocalTime.of(23, 59), DateTimeUtil.timeFromString("23:59"));
		}
	}
}
