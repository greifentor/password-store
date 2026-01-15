package de.ollie.baselib.util.upn.impl;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import de.ollie.baselib.util.upn.impl.model.Expression;
import de.ollie.baselib.util.upn.impl.model.Word;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ExpressionEvaluatorImplTest {

	private static final Stack<Object> STACK = new Stack<>();
	private static final Map<String, Object> VALUE_STORE = Map.of();

	@Mock
	private Expression expression;

	@Mock
	private StackFactory stackFactory;

	@InjectMocks
	private ExpressionEvaluatorImpl unitUnderTest;

	@Nested
	class evaluate_Expression_MapStringObject {

		@Test
		void throwsAnException_passingANullValue_asExpression() {
			assertThrows(IllegalArgumentException.class, () -> unitUnderTest.evaluate(null, VALUE_STORE));
		}

		@Test
		void throwsAnException_passingANullValue_asValueStore() {
			assertThrows(IllegalArgumentException.class, () -> unitUnderTest.evaluate(expression, null));
		}

		@Test
		void doesNotContinueEvaluatingWords_whenFirstWordEvaluationFails() {
			// Prepare
			RuntimeException exception = new RuntimeException();
			Word word0 = mock(Word.class);
			Word word1 = mock(Word.class);
			List<Word> words = List.of(word0, word1);
			when(expression.getWords()).thenReturn(words);
			when(stackFactory.create()).thenReturn(STACK);
			when(word0.evaluate(STACK, VALUE_STORE)).thenThrow(exception);
			// Run & Check
			assertThrows(RuntimeException.class, () -> unitUnderTest.evaluate(expression, VALUE_STORE));
			verifyNoInteractions(word1);
		}

		@Nested
		class cleanRun {

			@Test
			void eachWordIsEvaluated() {
				// Prepare
				List<Word> words = trainMock_forACleanRun_andReturnWordList();
				// Run
				unitUnderTest.evaluate(expression, VALUE_STORE);
				// Check
				words.forEach(word -> verify(word, times(1)).evaluate(STACK, VALUE_STORE));
			}

			private List<Word> trainMock_forACleanRun_andReturnWordList() {
				Word word0 = mock(Word.class);
				Word word1 = mock(Word.class);
				List<Word> words = List.of(word0, word1);
				when(expression.getWords()).thenReturn(words);
				when(stackFactory.create()).thenReturn(STACK);
				return words;
			}

			@Test
			void stackIsReturned() {
				// Prepare
				trainMock_forACleanRun_andReturnWordList();
				// Run & Check
				assertSame(STACK, unitUnderTest.evaluate(expression, VALUE_STORE));
			}
		}
	}
}
