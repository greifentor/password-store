package de.ollie.baselib.util.upn.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ExpressionEvaluationExceptionTest {

	private static final String MESSAGE = "message";

	@Nested
	class constructor_String {

		@Test
		void setsTheMessageCorrectly() {
			assertEquals(MESSAGE, new ExpressionEvaluationException(MESSAGE).getMessage());
		}
	}
}
