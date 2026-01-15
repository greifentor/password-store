package de.ollie.baselib.util.upn.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import de.ollie.baselib.util.upn.impl.model.Command;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MissingArgumentExpressionEvaluationExceptionTest {

	private static final int ARGUMENT_NUMBER = 42;
	private static final String TOKEN = "token";

	@Mock
	private Command command;

	@Nested
	class constructor_String {

		@Test
		void setsTheMessageCorrectly() {
			// Prepare
			when(command.getToken()).thenReturn(TOKEN);
			// Run & Check
			assertEquals(
				"missing argument " + ARGUMENT_NUMBER + " for " + TOKEN + " evaluation",
				new MissingArgumentExpressionEvaluationException(ARGUMENT_NUMBER, command).getMessage()
			);
		}
	}
}
