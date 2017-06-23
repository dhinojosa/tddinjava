package com.xyzcorp;


import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Rule;

public class CaesarShiftTest {
    
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void testShiftOf0OnAnEmptyString() {
		//I always want the simplest thing first.
		//Encoding
		//Shift 0
		//Empty String
		
		//Question: How do I want to design the API
		
		CaesarShift cs = new CaesarShift(0); //same shift
		assertEquals("", cs.encode(""));
	}

	//Use our favorite assertion language (Hamcrest, AssertJ)
	@Test
	public void testNullStringClassicWay() {
		CaesarShift cs = new CaesarShift(0);
		try {
			cs.encode(null);
			fail("This line should never be executed");
		} catch (NullPointerException e) {
			assertThat(e).hasMessage("String is null");
		}
	}

	@Test
	public void testNullStringExpectedException() {
		thrown.expect(NullPointerException.class);
		thrown.expectMessage("String is null");
		CaesarShift cs = new CaesarShift(0);
		cs.encode(null);
	}

	@Test
	public void testNullStringAssertJTrick() {
		CaesarShift cs = new CaesarShift(0);
		assertThatThrownBy(() -> cs.encode(null))
		.isInstanceOf(NullPointerException.class)
		.hasMessage("String is null");
	}

	@Test
	public void testSingleLetterShiftOf0() {
		CaesarShift cs = new CaesarShift(0);
		assertThat(cs.encode("a")).isEqualTo("a");
	}

	@Test
	public void testSingleLetterShiftOf1() {
		CaesarShift cs = new CaesarShift(1);
		assertThat(cs.encode("a")).isEqualTo("b");
	}

	//NoRed
	@Test
	public void testSingleLetterShiftOf5FromCTo() {
		CaesarShift cs = new CaesarShift(5);
		assertThat(cs.encode("c")).isEqualTo("h");
	}

	/// a = 97 ... z = 122
	/// a..z = 26

	// 122 + 1 % 2

	@Test
	public void testSingleLetterShiftOf1FromZ() {
		CaesarShift cs = new CaesarShift(1);
		assertThat(cs.encode("z")).isEqualTo("a");
	}

	@Test
	public void testSingleLetterShiftOf5FromX() {
		CaesarShift cs = new CaesarShift(5);
		assertThat(cs.encode("x")).isEqualTo("c");
	}

	@Test
	public void testSingleLetterShiftOf26AndShouldBeTheSameLetter() {
		CaesarShift cs = new CaesarShift(26);
		assertThat(cs.encode("p")).isEqualTo("p");
	}

	@Test
	public void testSingleLetterShiftOf27AndShouldBeTheSameAsAShiftOf1() {
		CaesarShift cs = new CaesarShift(27);
		assertThat(cs.encode("p")).isEqualTo("q");
	}

	@Test
	public void testSingleLetterShiftOf104AndShouldBeUnshifted() {
		CaesarShift cs = new CaesarShift(26 * 4);
		assertThat(cs.encode("p")).isEqualTo("p");
	}

	@Test
	public void testSingleLetterShiftOf123AndShouldBeShiftedT() {
		CaesarShift cs = new CaesarShift(123);
		assertThat(cs.encode("p")).isEqualTo("i");
	}

	//Add another character
	@Test
	public void testDoubleLetterOfABShiftOf2() {
		CaesarShift cs = new CaesarShift(1);
		assertThat(cs.encode("ab")).isEqualTo("bc");
	}

	//Capital Letters should work: A + 5 = F, Z + 4 = D
	@Test
	public void testSingleLetterShiftOf1FromCapitalM() {
		CaesarShift cs = new CaesarShift(1);
		assertThat(cs.encode("M")).isEqualTo("N");
	}

	//Capital Letters should work: A + 5 = F, Z + 4 = D
	@Test
	public void testSingleLetterShiftOf1FromCapitalZ() {
		CaesarShift cs = new CaesarShift(1);
		assertThat(cs.encode("Z")).isEqualTo("A");
	}

	//Spaces, Numbers, Strange Characters (Leave them alone)
	@Test
	public void testStrangeCharacters() {
		CaesarShift cs = new CaesarShift(4);
		assertThat(cs.encode("~")).isEqualTo("~");
	}

	//Add other characters to make complete sentences
	@Test
	public void testHelloWorld() {
		CaesarShift cs = new CaesarShift(1);
		assertThat(cs.encode("Hello, World.")).isEqualTo("Ifmmp, Xpsme.");
	}

	//Decode (subtraction)
	@Test
	public void testDecodeOfAnEmptyStringWithShiftOf1() {
		CaesarShift cs = new CaesarShift(1);
		assertThat(cs.decode("")).isEqualTo("");
	}

	@Test
	public void testDecodeOfAnSingleStringWithShiftOf0() {
		CaesarShift cs = new CaesarShift(0);
		assertThat(cs.decode("b")).isEqualTo("b");
	}

	@Test
	public void testDecodeOfAnSingleStringWithShiftOf1() {
		CaesarShift cs = new CaesarShift(1);
		assertThat(cs.decode("b")).isEqualTo("a");
	}

	//NoRedBar
	@Test
	public void testDecodeOfAnSingleStringWithShiftOf5() {
		CaesarShift cs = new CaesarShift(5);
		assertThat(cs.decode("f")).isEqualTo("a");
	}

	@Test
	public void testDecodeNullString() {
		CaesarShift cs = new CaesarShift(0);
		assertThatThrownBy(() -> cs.decode(null))
				.isInstanceOf(NullPointerException.class)
				.hasMessage("String is null");
	}

	//Very difficult
	@Test
	public void testDecodeOfAnAWithShiftOf1() {
		CaesarShift cs = new CaesarShift(1);
		assertThat(cs.decode("a")).isEqualTo("z");
	}

	//NoRedBar
	//Very difficult
	@Test
	public void testDecodeOfAnAWithCapitalShiftOf1() {
		CaesarShift cs = new CaesarShift(1);
		assertThat(cs.decode("A")).isEqualTo("Z");
	}

    //NoRedBar
	@Test
	public void testDecodeOfAnAWithCapitalShiftOfAHighMultipleOf26() {
		CaesarShift cs = new CaesarShift(26 * 10);
		assertThat(cs.decode("A")).isEqualTo("A");
	}

	@Test
	public void testDecodeOfAnAWithCapitalShiftOfAHighMultipleOf26And1() {
		CaesarShift cs = new CaesarShift((26 * 10) + 1);
		assertThat(cs.decode("a")).isEqualTo("z");
	}

	//Add other characters to make complete sentences
	@Test
	public void testDecodeHelloWorld() {
		CaesarShift cs = new CaesarShift(1);
		assertThat(cs.decode("Ifmmp, Xpsme.")).isEqualTo("Hello, World.");
	}

}
