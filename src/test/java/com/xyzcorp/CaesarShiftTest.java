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
		assertThat(cs.encode("p")).isEqualTo("i"); //i?
	}

	//Homework!
	//123 problem
	//Add another character
	//Capital Letters should work: A + 5 = F, Z + 4 = D
	//Add other characters to make complete sentences
	//Spaces, Numbers, Strange Characters (Leave them alone)
	//Decode (subtraction)
}
