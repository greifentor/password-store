package de.ollie.baselib.util.upn.impl.model;

import java.util.Map;
import java.util.Stack;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Generated;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
@Generated
@Getter
@ToString
public class Value implements Word {

	public static Value of(Object value) {
		return new Value(value);
	}

	private Object value;

	@Override
	public Stack<Object> evaluate(Stack<Object> stack, Map<String, Object> valueStore) {
		stack.push(getValue());
		return stack;
	}
}
