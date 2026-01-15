package de.ollie.baselib.util.upn.impl;

import static de.ollie.baselib.util.Check.ensure;

import de.ollie.baselib.util.upn.StringToExpressionConverter;
import de.ollie.baselib.util.upn.WordFactory;
import de.ollie.baselib.util.upn.impl.model.Expression;
import de.ollie.baselib.util.upn.impl.model.Value;
import de.ollie.baselib.util.upn.impl.model.Word;
import jakarta.inject.Named;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.apache.commons.text.StringTokenizer;
import org.apache.commons.text.matcher.StringMatcher;
import org.apache.commons.text.matcher.StringMatcherFactory;

@Named
@RequiredArgsConstructor
public class StringToExpressionConverterImpl implements StringToExpressionConverter {

	private final ValueConverter valueConverter;
	private final WordFactory wordFactory;

	@Override
	public Expression convert(String expression) {
		ensure(expression != null, "expression to convert cannot be null!");
		List<Word> words = new ArrayList<Word>();
		StringTokenizer tokenizer = new StringTokenizer(expression, " ");
		StringMatcher sm = StringMatcherFactory.INSTANCE.quoteMatcher();
		tokenizer.setQuoteMatcher(sm);
		while (tokenizer.hasNext()) {
			words.add(getWord(tokenizer.nextToken()));
		}
		return Expression.of(words);
	}

	private Word getWord(String token) {
		if (wordFactory.isCommand(token)) {
			return wordFactory.createCommand(token);
		}
		return Value.of(valueConverter.getValue(token));
	}
}
