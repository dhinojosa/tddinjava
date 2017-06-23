package com.xyzcorp;

import java.util.Objects;
import java.util.stream.Collectors;

public class CaesarShift {
    private final int shift;

    public CaesarShift(int shift) {
        this.shift = shift;
    }

    public char encodeChar(char c, int charShift) {
        if (!Character.isAlphabetic(c)) return c;
        char capitalA = 'A';
        char smallA = 'a';
        char anchorA = Character.isUpperCase(c) ? capitalA : smallA;
        int actualShift = charShift - (26 * (charShift/26)); //Keep the number small
        return (char)((c - anchorA + actualShift + 26) % 26 + anchorA);
    }

    public String encode(String s) {
        Objects.requireNonNull(s, "String is null");
        if (s.isEmpty()) return "";
        return s.chars()
                .boxed()
                .map(c -> encodeChar((char) c.intValue(), shift))
                .map(String::valueOf)
                .collect(Collectors.joining());
    }

    public String decode(String s) {
        Objects.requireNonNull(s, "String is null");
        if (s.isEmpty()) return "";
        return s.chars()
                .boxed()
                .map(c -> encodeChar((char) c.intValue(), -shift))
                .map(String::valueOf)
                .collect(Collectors.joining());
    }
}
