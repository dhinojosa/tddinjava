package com.xyzcorp;

import java.util.Objects;

public class CaesarShift {

	private static final int NUMBER_LETTERS = 'z' - 'a' + 1;
	private int shift;

	public CaesarShift(int shift) {
		this.shift = shift;
	}

	public String encrypt(String string) {
		Objects.requireNonNull(string, "String cannot be null");
		if(string.isEmpty()) return "";
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < string.length(); i++) {
			char c = string.charAt(i);
			char result = c;
			if (Character.isAlphabetic(c)) {
				char offset = Character.isUpperCase(c) ? 'A' : 'a';
				result = (char) ((c + shift - offset) % NUMBER_LETTERS + offset);
			}
			builder.append(result);
		}
		return builder.toString();
	}

	public String decrypt(String string) {
		Objects.requireNonNull(string, "String cannot be null");
		if(string.isEmpty()) return "";
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < string.length(); i++) {
			char c = string.charAt(i);
			char result = c;
			if (Character.isAlphabetic(c)) {
				char offset = Character.isUpperCase(c) ? 'A' : 'a';
				result = (char) ((((c - shift - offset) % NUMBER_LETTERS + NUMBER_LETTERS) % NUMBER_LETTERS + offset));
			}
			builder.append(result);
		}
		return builder.toString();
	}
}
