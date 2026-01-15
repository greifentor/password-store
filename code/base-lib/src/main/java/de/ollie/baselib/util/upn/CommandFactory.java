package de.ollie.baselib.util.upn;

import de.ollie.baselib.util.upn.impl.model.Command;

public interface CommandFactory<T extends Command> {
	T create();
}
