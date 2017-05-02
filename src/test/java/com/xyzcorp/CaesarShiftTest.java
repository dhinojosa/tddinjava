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

    //NotRedBar
    @Test
    public void testEmptyStringWithAShiftOne() {
    	    CaesarShift caesarShift = new CaesarShift(1);
        String result = caesarShift.encrypt("");
        assertThat(result).isEqualTo("");
    }
    
    //NotRedBar
    @Test
    public void testEmptyStringWithAShiftTen() {
    	    CaesarShift caesarShift = new CaesarShift(10);
        String result = caesarShift.encrypt("");
        assertThat(result).isEqualTo("");
    }
    
    @Test
    public void testStringOfOneAlphaCharAndShiftOf1(){
    	    CaesarShift caesarShift = new CaesarShift(1);
        String result = caesarShift.encrypt("a");
        assertThat(result).isEqualTo("b");
    }
    
    
    public void testStringOfSpace() {}
    public void testStringOfSpecialChars() {}
    
    public void testCaesarShiftOfNull() {}
    public void testDoWeShiftNumbers() {}
    public void testNegativeShift() {}   
}
