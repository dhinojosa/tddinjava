package com.xyzcorp;

import static org.junit.Assert.*;

import org.junit.Test;

public class CaesarShiftTest {
    @Test
    public void testCaesarShiftOfEmptyStringAnd0() {
         CaesarShift caesarShift = new CaesarShift(0);
         String result = caesarShift.encrypt("");
         assertEquals("", result);
    }
}
