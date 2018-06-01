package com.xyzcorp;

import java.util.Objects;
import java.util.stream.Collectors;

public class CaesarShift {

    public static String encode(String input, int shift) {
        Objects.requireNonNull(input, "Input cannot be null");
        if (input.isEmpty()) return input;
        return input.chars()
                    .boxed()
                    .map(i -> String.valueOf((char) shiftChar(shift, i)))
                    .collect(Collectors.joining());
    }

    private static int shiftChar(int shift, int c) {
        if (!Character.isAlphabetic(c)) return c;
        char a = Character.isUpperCase(c) ? 'A' : 'a';
        return ((c + (shift % 26 + 26) % 26 - a) % 26 + a);
    }

    public static String decode(String input, int shift) {
        return encode(input, -shift);
    }
}
