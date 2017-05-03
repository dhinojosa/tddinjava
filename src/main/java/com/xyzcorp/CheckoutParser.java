package com.xyzcorp;

import java.time.LocalDate;

public class CheckoutParser {
    private final String delimeter;

    public CheckoutParser(String delimiter) {
       this.delimeter = delimiter;
    }

    public Checkout parse(String s) {
        String[] tokens = s.split(delimeter);
        if (tokens.length != 3) throw new IllegalArgumentException("IllegalFormat");
        return new Checkout(tokens[0], LocalDate.parse(tokens[2]));
    }
}
