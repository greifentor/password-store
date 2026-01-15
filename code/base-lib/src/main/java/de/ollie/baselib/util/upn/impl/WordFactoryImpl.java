package de.ollie.baselib.util.upn.impl;

import static de.ollie.baselib.util.Check.ensure;

import de.ollie.baselib.util.upn.CommandFactory;
import de.ollie.baselib.util.upn.WordFactory;
import de.ollie.baselib.util.upn.impl.model.Command;
import de.ollie.baselib.util.upn.impl.model.Value;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Named;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;

@Named
public class WordFactoryImpl implements WordFactory {

	@Autowired(required = false)
	private List<CommandFactory<? extends Command>> commandFactories;

	private Map<String, CommandFactory<? extends Command>> mapCommandFactories = new HashMap<>();

	@PostConstruct
	void postConstruct() {
		commandFactories.forEach(factory -> mapCommandFactories.put(factory.create().getToken(), factory));
	}

	@Override
	public Command createCommand(String token) {
		ensure(token != null, "token cannot be null!");
		if (isCommand(token)) {
			return mapCommandFactories.get(token).create();
		}
		throw new IllegalArgumentException("there is no command for token: " + token);
	}

	@Override
	public Value createValue(Object value) {
		ensure(value != null, "value cannot be null!");
		return Value.of(value);
	}

	@Override
	public boolean isCommand(String token) {
		ensure(token != null, "token cannot be null!");
		return mapCommandFactories.containsKey(token);
	}
}
