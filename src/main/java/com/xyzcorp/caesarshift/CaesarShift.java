package com.xyzcorp.caesarshift;

import java.util.Objects;

public class CaesarShift {

	private final int shift;

	public CaesarShift(int shift) {
      this.shift = shift;
    }

	public String encode(String string) {
		Objects.requireNonNull(string,"Original string cannot be null");
		if (string.isEmpty()) return string;
		return String.valueOf((char)(string.charAt(0) + shift));
	}
}
