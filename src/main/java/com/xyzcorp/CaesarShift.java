package com.xyzcorp;

import java.util.Objects;
import java.util.stream.Collectors;

public class CaesarShift {
    private final int shift;

    public CaesarShift(int shift) {
        this.shift = shift;
    }

    private char encodeChar(char c, int charShift) {
        if (!Character.isAlphabetic(c)) return c;
        char capitalA = 'A';
        char smallA = 'a';
        char anchorA = Character.isUpperCase(c) ? capitalA : smallA;
        int actualShift = charShift - (26 * (charShift/26)); //Keep the number small
        return (char)((c - anchorA + actualShift + 26) % 26 + anchorA);
    }

    private String encodeString(String s, int charShift) {
        Objects.requireNonNull(s, "String is null");
        if (s.isEmpty()) return "";
        return s.chars()
                .boxed()
                .map(c -> encodeChar((char) c.intValue(), charShift))
                .map(String::valueOf)
                .collect(Collectors.joining());
    }

    public String encode(String s) {
        return encodeString(s, shift);
    }

    public String decode(String s) {
        return encodeString(s, -shift);
    }
}
