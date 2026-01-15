package de.ollie.baselib.util;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Locale;

public class DateTimeUtil {

	DateTimeUtil() {
		throw new UnsupportedOperationException("Can not be instantiated!");
	}

	public static final DateTimeFormatter DE_DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.GERMAN);
	public static final DateTimeFormatter DE_TIME_FORMAT = new DateTimeFormatterBuilder()
		.appendValue(ChronoField.HOUR_OF_DAY, 1, 2, java.time.format.SignStyle.NOT_NEGATIVE)
		.appendLiteral(':')
		.appendValue(ChronoField.MINUTE_OF_HOUR, 2)
		.toFormatter();

	public static LocalDate dateFromString(String dateStr) {
		return LocalDate.parse(dateStr, DE_DATE_FORMAT);
	}

	public static LocalTime timeFromString(String timeStr) {
		return LocalTime.parse(timeStr, DE_TIME_FORMAT);
	}
}
