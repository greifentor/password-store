package de.ollie.baselib.util.upn.impl;

import static de.ollie.baselib.util.Check.ensure;

import jakarta.inject.Named;

@Named
class ValueConverter {

	Object getValue(String token) {
		ensure(token != null, "token cannot be null!");
		if (token.toUpperCase().equals("FALSE") || token.toUpperCase().equals("TRUE")) {
			return Boolean.valueOf(token.toUpperCase());
		}
		try {
			return Integer.valueOf(token);
		} catch (NumberFormatException nfe) {
			// NOP
		}
		return token;
	}
}
