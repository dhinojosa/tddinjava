package com.xyzcorp;

public class CaesarShift {

	private int shift;
	
	public CaesarShift(int shift) {
		this.shift = shift;
	}

	public String encrypt(String string) {
		if(string.isEmpty()) return "";
		return "" + (char)(string.charAt(0) + shift);
	}
}
