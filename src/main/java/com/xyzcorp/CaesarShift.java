package com.xyzcorp;

import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CaesarShift {

	public static String encode(String input, int shift) {
		Objects.requireNonNull(input, "Input cannot be null");
		if (input.isEmpty()) return input;
		return input.chars()
		     .boxed()
		     .map(i -> shiftChar(shift, (char)(i.intValue())))
		     .collect(Collectors.joining());
	}

	private static String shiftChar(int shift, char c) {
		int afterShift = c + shift;
		if (afterShift > 122) {
			return String.valueOf((char) ((afterShift % 122) + 96));
		} else {
			return String.valueOf((char) (afterShift));
		}
		
		//return String.valueOf((char)(((input.charAt(0) + shift) - 97) % 26 + 97));
	}
}
