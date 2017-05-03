package com.xyzcorp;

import java.util.Objects;
import java.util.stream.Collectors;

public class CaesarShift {

	private static final int NUMBER_LETTERS = 'z' - 'a' + 1;
	private int defaultShift;

	public CaesarShift(int shift) {
		this.defaultShift = shift;
	}

	private char shiftChar(char c, int otherShift) {
		char result = c;
		if (Character.isAlphabetic(c)) {
            char offset = Character.isUpperCase(c) ? 'A' : 'a';
			result = (char) (((c + otherShift - offset) % NUMBER_LETTERS + NUMBER_LETTERS) % NUMBER_LETTERS + offset);
        }
		return result;
	}

	private String process(String string, int shift) {
		Objects.requireNonNull(string, "String cannot be null");
		if(string.isEmpty()) return "";
	    return string.chars().boxed().map(integer -> (char) integer.intValue())
			  .map(character -> "" + shiftChar(character, shift)).collect(Collectors.joining());
	}

	public String encrypt(String string) {
		return process(string, defaultShift);
	}

	public String decrypt(String string) {
		return process(string, -defaultShift);
	}
}
