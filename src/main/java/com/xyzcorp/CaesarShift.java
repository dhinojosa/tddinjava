package com.xyzcorp;

public class CaesarShift {

	private static final int NUMBER_LETTERS = 'z' - 'a' + 1;
	private int shift;
	
	public CaesarShift(int shift) {
		this.shift = shift;
	}

	public String encrypt(String string) {
		//handle the exception
		if(string.isEmpty()) return "";
		char c = string.charAt(0);
		char result = c;
		if (Character.isAlphabetic(c)) {
		   char offset = Character.isUpperCase(c) ? 'A' : 'a';
		   result = (char)((c + shift - offset) % NUMBER_LETTERS + offset);
		}
		return "" + result;
	}
}
