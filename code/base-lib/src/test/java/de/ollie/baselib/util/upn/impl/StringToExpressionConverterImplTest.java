package de.ollie.baselib.util.upn.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import de.ollie.baselib.util.upn.WordFactory;
import de.ollie.baselib.util.upn.impl.model.Command;
import de.ollie.baselib.util.upn.impl.model.Expression;
import de.ollie.baselib.util.upn.impl.model.Value;
import java.util.List;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class StringToExpressionConverterImplTest {

	private static final String ARGUMENT = "argument";
	private static final String TOKEN = "token";

	@Mock
	private WordFactory wordFactory;

	@Mock
	private ValueConverter valueConverter;

	@InjectMocks
	private StringToExpressionConverterImpl unitUnderTest;

	@Nested
	class convert_String {

		@Test
		void throwsAnException_passingANullValue_asExpressionString() {
			assertThrows(IllegalArgumentException.class, () -> unitUnderTest.convert(null));
		}

		@Test
		void returnsAnEmptyExpression_passingAnEmptyString() {
			assertEquals(Expression.of(List.of()), unitUnderTest.convert(""));
		}

		@Test
		void returnsACorrectExpression_passingAString_withArgumentAndToken() {
			// Prepare
			Command command = mock(Command.class);
			Value value = Value.of(ARGUMENT);
			Expression expected = Expression.of(List.of(value, command));
			when(valueConverter.getValue(ARGUMENT)).thenReturn(ARGUMENT);
			when(wordFactory.createCommand(TOKEN)).thenReturn(command);
			when(wordFactory.isCommand(ARGUMENT)).thenReturn(false);
			when(wordFactory.isCommand(TOKEN)).thenReturn(true);
			// Run & Check
			assertEquals(expected, unitUnderTest.convert(ARGUMENT + " " + TOKEN));
		}
	}
}
