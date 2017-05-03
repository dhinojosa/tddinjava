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

	@Test
	public void testStringOfSpaceAndShiftOf5() {
		CaesarShift caesarShift = new CaesarShift(5);
		String result = caesarShift.encrypt(" ");
		assertThat(result).isEqualTo(" ");
	}

	@Test
	public void testStringOfSpecialChar() {
		CaesarShift caesarShift = new CaesarShift(5);
		String result = caesarShift.encrypt("~");
		assertThat(result).isEqualTo("~");
	}

	@Test
	public void testCaesarShift0WithANullString() {
		CaesarShift caesarShift = new CaesarShift(0);
		try {
			caesarShift.encrypt(null); // Exception!
			fail("The line should not reach this point");
		} catch (NullPointerException iae) {
			assertThat(iae).hasMessage("String cannot be null");
		}
	}

	@Test
	public void testStringOfANumber() {
		CaesarShift caesarShift = new CaesarShift(1);
		String result = caesarShift.encrypt("3");
		assertThat(result).isEqualTo("3");
	}

	@Test
	public void testAStringOfMoreThanOneLetter() {
		CaesarShift caesarShift = new CaesarShift(3);
		String result = caesarShift.encrypt("Apple");
		assertThat(result).isEqualTo("Dssoh");
	}


	@Test
	public void testCaesarShiftDecryptOfEmptyStringAnd0() {
		CaesarShift caesarShift = new CaesarShift(0);
		String result = caesarShift.decrypt("");
		assertEquals("", result);
	}

	@Test
	public void testDecryptStringWithOneCharAndShiftOfZero() {
		CaesarShift caesarShift = new CaesarShift(0);
		String result = caesarShift.decrypt("a");
		assertThat(result).isEqualTo("a");
	}

	@Test
	public void testDecryptStringOfOneAlphaCharAndShiftOf1() {
		CaesarShift caesarShift = new CaesarShift(1);
		String result = caesarShift.decrypt("a");
		assertThat(result).isEqualTo("z");
	}

	@Test
	public void testDecryptStringOfzAndShiftOf1() {
		CaesarShift caesarShift = new CaesarShift(1);
		String result = caesarShift.decrypt("z");
		assertThat(result).isEqualTo("y");
	}

	@Test
	public void testDecryptStringOfBAndShiftOf1() {
		CaesarShift caesarShift = new CaesarShift(1);
		String result = caesarShift.decrypt("G");
		assertThat(result).isEqualTo("F");
	}

	@Test
	public void testDecryptStringOfSpaceAndShiftOf1() {
		CaesarShift caesarShift = new CaesarShift(1);
		String result = caesarShift.decrypt(" ");
		assertThat(result).isEqualTo(" ");
	}

	@Test
	public void testDecryptStringOfSpecialChar() {
		CaesarShift caesarShift = new CaesarShift(5);
		String result = caesarShift.decrypt("~");
		assertThat(result).isEqualTo("~");
	}

	@Test
	public void testDecryptCaesarShift0WithANullString() {
		CaesarShift caesarShift = new CaesarShift(0);
		try {
			caesarShift.decrypt(null); // Exception!
			fail("The line should not reach this point");
		} catch (NullPointerException iae) {
			assertThat(iae).hasMessage("String cannot be null");
		}
	}

}
