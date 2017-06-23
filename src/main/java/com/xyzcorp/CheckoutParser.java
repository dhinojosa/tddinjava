package com.xyzcorp;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.stream.Stream;

public class CheckoutParser {

    public static final String STRING_CANNOT_BE_EMPTY = "String cannot be empty";
    public static final java.lang.String INVALID_DATE = "Date is invalid";

    private final String regex;

    public CheckoutParser(String regex) {
       this.regex = regex;
    }

    protected Checkout parseLine(String str) {
        if (str.isEmpty()) throw new IllegalArgumentException(STRING_CANNOT_BE_EMPTY);
        String[] split = str.split(regex);

        try {
            LocalDate.parse(split[2]);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Date is invalid", e);
        }

        return new Checkout(split[0], LocalDate.parse(split[2]));
    }

    public Stream<Checkout> parseStream(Stream<String> stream) {
       return stream.map(str -> parseLine(str));
    }
}
