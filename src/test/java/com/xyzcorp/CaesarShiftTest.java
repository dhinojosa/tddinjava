package com.xyzcorp;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

public class CaesarShiftTest {
	@Test
	public void testCaesarShiftOfEmptyStringAnd0() {
		CaesarShift caesarShift = new CaesarShift(0);
		String result = caesarShift.encrypt("");
		assertEquals("", result);
	}

	@Test
	public void testAStringWithOneCharAndShiftOfZero() {
		CaesarShift caesarShift = new CaesarShift(0);
		String result = caesarShift.encrypt("a");
		assertThat(result).isEqualTo("a");
	}

	// NotRedBar
	@Test
	public void testEmptyStringWithAShiftOne() {
		CaesarShift caesarShift = new CaesarShift(1);
		String result = caesarShift.encrypt("");
		assertThat(result).isEqualTo("");
	}

	// NotRedBar
	@Test
	public void testEmptyStringWithAShiftTen() {
		CaesarShift caesarShift = new CaesarShift(10);
		String result = caesarShift.encrypt("");
		assertThat(result).isEqualTo("");
	}

	@Test
	public void testStringOfOneAlphaCharAndShiftOf1() {
		CaesarShift caesarShift = new CaesarShift(1);
		String result = caesarShift.encrypt("a");
		assertThat(result).isEqualTo("b");
	}

	@Test
	public void testStringOfzAndShiftOf1() {
		CaesarShift caesarShift = new CaesarShift(1);
		String result = caesarShift.encrypt("z");
		assertThat(result).isEqualTo("a");
	}

	@Test
	public void testStringOfBAndShiftOf1() {
		CaesarShift caesarShift = new CaesarShift(1);
		String result = caesarShift.encrypt("B");
		assertThat(result).isEqualTo("C");
	}

	@Test
	public void testStringOfSpaceAndShiftOf1() {
		CaesarShift caesarShift = new CaesarShift(1);
		String result = caesarShift.encrypt(" ");
		assertThat(result).isEqualTo(" ");
	}

	public void testStringOfSpace() {
	}

	public void testStringOfSpecialChars() {
	} // ~'$#@

	@Test
	public void testCaesarShift0WithANullString() {
		CaesarShift caesarShift = new CaesarShift(0);
		try {
			caesarShift.encrypt(""); // Exception!
			fail("The line should not reach this point");
		} catch (IllegalArgumentException iae) {
			assertThat(iae).hasMessage("String cannot be null");
		}
	}

	public void testDoWeShiftNumbers() {
	}

	public void testNegativeShift() {
	}
}
