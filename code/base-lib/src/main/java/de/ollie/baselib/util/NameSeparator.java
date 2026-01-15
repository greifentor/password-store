package de.ollie.baselib.util;

import static de.ollie.baselib.util.Check.ensure;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class NameSeparator {

	private static final String MSG_NAME_IS_NULL = "name cannot be null!";
	private static final String MSG_SEPARATOR_IS_NULL = "separator cannot be null!";

	public static final NameSeparator INSTANCE = new NameSeparator();

	public String getNameSeparated(String name, String separator) {
		ensure(name != null, MSG_NAME_IS_NULL);
		ensure(separator != null, MSG_SEPARATOR_IS_NULL);
		String s = "" + (name.length() > 0 ? name.charAt(0) : "");
		for (int i = 1; i < name.length(); i++) {
			s += (((name.charAt(i) >= 'A') && (name.charAt(i) <= 'Z')) ? separator : "") + name.charAt(i);
		}
		return s;
	}
}
