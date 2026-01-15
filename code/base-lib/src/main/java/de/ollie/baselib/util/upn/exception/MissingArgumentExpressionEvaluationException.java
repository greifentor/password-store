package de.ollie.baselib.util.upn.exception;

import de.ollie.baselib.util.upn.impl.model.Command;

public class MissingArgumentExpressionEvaluationException extends ExpressionEvaluationException {

	static final String MESSAGE_MISSING_ARGUMENT = "missing argument {0} for {1} evaluation";

	public MissingArgumentExpressionEvaluationException(int argumentNumber, Command command) {
		super(MESSAGE_MISSING_ARGUMENT.replace("{0}", "" + argumentNumber).replace("{1}", command.getToken()));
	}
}
