package com.brunobaiano;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Locale;

public record Meeting(String startAt, String endAt, String day, String description) {
    public String getFullDay() {
        try {
            DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth().getValue(), Integer.parseInt(day)).toString();
        } catch (NumberFormatException e) {
            DateTimeFormatterBuilder builder = new DateTimeFormatterBuilder();
            DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                    .parseCaseInsensitive()
                    .appendPattern("MMMM d")
                    .parseDefaulting(ChronoField.YEAR, LocalDate.now().getYear()) // Provide a default year if needed
                    .optionalStart()
                    .appendLiteral("st").optionalEnd()
                    .optionalStart()
                    .appendLiteral("nd").optionalEnd()
                    .optionalStart()
                    .appendLiteral("rd").optionalEnd()
                    .optionalStart()
                    .appendLiteral("th").optionalEnd()
                    .toFormatter(Locale.ENGLISH);

            return LocalDate.parse(day, formatter).toString();
        }
    }
}
