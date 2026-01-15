package de.ollie.baselib.util.upn.impl;

import jakarta.inject.Named;
import java.util.Stack;

@Named
class StackFactory {

	Stack<Object> create() {
		return new Stack<>();
	}
}
