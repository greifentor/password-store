package de.ollie.password.store.service.core.exception;

import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class FileSystemExceptionTest {

	@Nested
	class constructor_String_Exception {

		private static final Exception EXCEPTION = new Exception();
		private static final String MESSAGE = "message";

		@Test
		void setsTheExceptionCorrectlyAsCause() {
			assertSame(EXCEPTION, new FileSystemException(MESSAGE, EXCEPTION).getCause());
		}

		@Test
		void setsTheMessageCorrectly() {
			assertSame(MESSAGE, new FileSystemException(MESSAGE, EXCEPTION).getMessage());
		}
	}
}
