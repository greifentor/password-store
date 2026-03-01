package de.ollie.password.store.service.code.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import de.ollie.password.store.service.code.model.ValidationResult.ValidationFailureCause;
import de.ollie.password.store.service.code.model.ValidationResult.ValidationFailures;
import java.util.List;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ValidationResultTest {

	private static final ValidationFailureCause CAUSE = ValidationFailureCause.IS_EMPTY;
	private static final String FIELD_NAME = "field-name";

	@InjectMocks
	private ValidationResult unitUnderTest;

	@Nested
	class add_ValidationFailureCause_String {

		@Test
		void throwsAnException_passingANullValue_asCause() {
			assertThrows(IllegalArgumentException.class, () -> unitUnderTest.addFailure(null, FIELD_NAME));
		}

		@Test
		void throwsAnException_passingAnEmptyString_asFieldName() {
			assertThrows(IllegalArgumentException.class, () -> unitUnderTest.addFailure(CAUSE, ""));
		}

		@Test
		void throwsAnException_passingANullValue_asFieldName() {
			assertThrows(IllegalArgumentException.class, () -> unitUnderTest.addFailure(CAUSE, null));
		}

		@Test
		void addANewFailure_withThePassedData() {
			// Prepare
			List<ValidationFailures> expected = List.of(new ValidationFailures(CAUSE, FIELD_NAME));
			// Run
			unitUnderTest.addFailure(CAUSE, FIELD_NAME);
			// Check
			assertEquals(expected, unitUnderTest.getFailures());
		}
	}

	@Nested
	class isValid {

		@Test
		void returnsFalse_whenFailuresAdded() {
			// Prepare
			unitUnderTest.addFailure(CAUSE, FIELD_NAME);
			// Run & Check
			assertFalse(unitUnderTest.isValid());
		}

		@Test
		void returnsTrue_whenNoFailuresAdded() {
			assertTrue(unitUnderTest.isValid());
		}
	}
}
