package de.ollie.baselib.util.upn.exception;

import de.ollie.baselib.util.upn.impl.model.Command;

public class WrongArgumentTypeExpressionEvaluationException extends ExpressionEvaluationException {

	public static final String MESSAGE_WRONG_ARGUMENT_TYPE =
		"wrong type of argument {0} for {1} evaluation! Should be '{2}' but was '{3}'.";

	public WrongArgumentTypeExpressionEvaluationException(
		int argumentNumber,
		Command command,
		Class<?> expectedType,
		Class<?> foundType
	) {
		super(
			MESSAGE_WRONG_ARGUMENT_TYPE
				.replace("{0}", "" + argumentNumber)
				.replace("{1}", command.getToken())
				.replace("{2}", expectedType.getName())
				.replace("{3}", foundType.getName())
		);
	}
}
