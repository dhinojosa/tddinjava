package com.xyzcorp;

import java.util.Objects;

public class CaesarShift {
    private final int shift;

    public CaesarShift(int shift) {
        this.shift = shift;
    }

    public String encode(String s) {
        Objects.requireNonNull(s, "String is null");
        if (s.isEmpty()) return "";
        //int actualShift = s.charAt(0) + shift;
        //if (actualShift > 122) actualShift = actualShift - 122 + 96;
        return Character.toString((char) ((s.charAt(0) - 97 + shift) % 26 + 97));
        //123
        //
    }
}
