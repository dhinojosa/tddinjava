package com.xyzcorp.caesarshift;

import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.lang.Character.isAlphabetic;
import static java.lang.Character.isUpperCase;

public class CaesarShift {

	private static final int ALPHA_SIZE = 26;
	private final int shift;

	public CaesarShift(int i) {
		this.shift = i % ALPHA_SIZE;
	}

	public String encode(String s) {
		return shiftString(s, shift);
	}

	public String decode(String s) {
		return shiftString(s, -shift);
	}

	private String shiftString(String s, int actualShift) {
		Objects.requireNonNull(s, "Original string cannot be null");
		IntStream chars = s.chars();
		Stream<Integer> boxed = chars.boxed();
		Stream<String> stringStream = boxed.map(i ->
				shiftCharacter((char) i.intValue(), actualShift));
		return stringStream.collect(Collectors.joining());
	}

	private String shiftCharacter(char c, int actualShift) {
		char preferredA = isUpperCase(c) ? 'A' : 'a';
		if (!isAlphabetic(c)) return String.valueOf(c);
		return String.valueOf((char)
				((c - preferredA + actualShift + ALPHA_SIZE) %
						ALPHA_SIZE + preferredA));
	}
}
