package de.ollie.baselib.util.upn.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import de.ollie.baselib.util.upn.CommandFactory;
import de.ollie.baselib.util.upn.impl.model.Command;
import de.ollie.baselib.util.upn.impl.model.Value;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

@ExtendWith(MockitoExtension.class)
class WordFactoryImplTest {

	private static final Integer INTEGER = 42;
	private static final String STRING = "string";
	private static final String TOKEN = "token";

	private List<CommandFactory<?>> commandFactories;

	@Mock
	private Command command;

	@Mock
	private CommandFactory<Command> commandFactory;

	@InjectMocks
	private WordFactoryImpl unitUnderTest;

	@BeforeEach
	void beforeEach() {
		commandFactories = new ArrayList<>();
		commandFactories.add(commandFactory);
		when(command.getToken()).thenReturn(TOKEN);
		when(commandFactory.create()).thenReturn(command);
		ReflectionTestUtils.setField(unitUnderTest, "commandFactories", commandFactories);
		unitUnderTest.postConstruct();
	}

	@Nested
	class createCommand_String {

		@Test
		void throwsAnException_passingANullValue_asToken() {
			assertThrows(IllegalArgumentException.class, () -> unitUnderTest.createCommand(null));
		}

		@Test
		void throwsAnException_passingAnUnknownToken() {
			assertThrows(IllegalArgumentException.class, () -> unitUnderTest.createCommand(TOKEN + 1));
		}

		@Test
		void returnsACommand_passingAKnownToken() {
			assertSame(command, unitUnderTest.createCommand(TOKEN));
		}
	}

	@Nested
	class getValue_String {

		@Test
		void throwsAnException_passingANullValue() {
			assertThrows(IllegalArgumentException.class, () -> unitUnderTest.createValue(null));
		}

		@Test
		void returnsACorrectValue_passingANotNullObject_asInteger() {
			assertEquals(Value.of(INTEGER), unitUnderTest.createValue(INTEGER));
		}

		@Test
		void returnsACorrectValue_passingANotNullObject_asString() {
			assertEquals(Value.of(STRING), unitUnderTest.createValue(STRING));
		}
	}

	@Nested
	class isCommand_String {

		@Test
		void throwsAnException_passingANullValue() {
			assertThrows(IllegalArgumentException.class, () -> unitUnderTest.isCommand(null));
		}
	}
}
