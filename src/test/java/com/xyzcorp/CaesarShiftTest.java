package com.xyzcorp;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

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

    public void testEmptyStringWithAShiftOne() {}
    public void testStringOfSpace() {}
    public void testCaesarShiftOfNull() {}
    public void testNegativeShift() {}   
}
