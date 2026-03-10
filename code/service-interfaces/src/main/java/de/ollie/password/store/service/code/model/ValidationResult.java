package de.ollie.password.store.service.code.model;

import static de.ollie.baselib.util.Check.ensure;

import java.util.ArrayList;
import java.util.List;

public class ValidationResult {

	public enum ValidationFailureCause {
		IS_EMPTY,
	}

	public static record ValidationFailures(ValidationFailureCause cause, String fieldName) {}

	private List<ValidationFailures> failures = new ArrayList<>();

	public void addFailure(ValidationFailureCause cause, String fieldName) {
		ensure(cause != null, "cause cannot be null!");
		ensure(fieldName != null, "field name cannot be null!");
		ensure(!fieldName.isEmpty(), "field name cannot be empty!");
		failures.add(new ValidationFailures(cause, fieldName));
	}

	public List<ValidationFailures> getFailures() {
		return List.copyOf(failures);
	}

	public boolean isValid() {
		return failures.isEmpty();
	}
}
