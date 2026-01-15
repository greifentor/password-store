package de.ollie.baselib.util.upn;

import de.ollie.baselib.util.upn.impl.model.Command;
import de.ollie.baselib.util.upn.impl.model.Value;

public interface WordFactory {
	Command createCommand(String token);

	Value createValue(Object value);

	boolean isCommand(String token);
}
