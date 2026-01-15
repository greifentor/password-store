package de.ollie.baselib.util.upn.impl.model;

import java.util.List;
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
public class Expression {

	public static Expression of(List<Word> words) {
		return new Expression(words);
	}

	private List<Word> words;
}
