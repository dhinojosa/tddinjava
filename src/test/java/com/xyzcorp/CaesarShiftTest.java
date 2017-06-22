package com.xyzcorp;


import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class CaesarShiftTest {
    
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
			assertTrue(true);
		}
	}

	public void testSingleLetterShiftOf0() {}
}
