package de.ollie.baselib.util.upn.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ValueConverterTest {

	private static final String STRING = "string";

	@InjectMocks
	private ValueConverter unitUnderTest;

	@Nested
	class getValue_String {

		@Test
		void throwsAnException_passingANullValue_asToken() {
			assertThrows(IllegalArgumentException.class, () -> unitUnderTest.getValue(null));
		}

		@ParameterizedTest
		@CsvSource({ "TRUE,true", "True,true", "true,true", "FALSE,false", "False,false", "false,false" })
		void returnsACorrectValue_forABooleanArgument(String token, boolean expected) {
			assertEquals(expected, unitUnderTest.getValue(token));
		}

		@ParameterizedTest
		@CsvSource(
			{
				"-1,-1",
				"0,0",
				"1,1",
				"42,42",
				Integer.MIN_VALUE + "," + Integer.MIN_VALUE,
				Integer.MAX_VALUE + "," + Integer.MAX_VALUE,
			}
		)
		void returnsACorrectValue_forAnIntegerArgument(String token, Integer expected) {
			assertEquals(expected, unitUnderTest.getValue(token));
		}

		@Test
		void returnsACorrectValue_forAnStringArgument() {
			assertEquals(STRING, unitUnderTest.getValue(STRING));
		}
	}
}
