package de.ollie.baselib.util.upn;

import de.ollie.baselib.util.upn.impl.model.Expression;

public interface StringToExpressionConverter {
	Expression convert(String expression);
}
