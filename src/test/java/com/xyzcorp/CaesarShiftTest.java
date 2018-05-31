package com.xyzcorp;

import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class CaesarShiftTest {

    //Hello -> +5 -> Mjqqt
    //Striving for red bar tests!

    //Start with Simplest & Dumbest Input As the first test

    private static Stream<Arguments> stringIntAndStringProvider() {
        return Stream.of(
                Arguments.of("", 0, ""),
                Arguments.of("a", 0, "a"),
                Arguments.of("", 1, ""),
                Arguments.of("a", 1, "b"),
                Arguments.of("z", 1, "a"),
                Arguments.of("ab", 1, "bc")
        );
    }

    @ParameterizedTest(name= "{index}: input={0}, shift={1}, expected={2}")
    @MethodSource("stringIntAndStringProvider")
    void testEncode(String input, int shift, String output) {
        String result = CaesarShift.encode(input, shift);
        assertThat(result).isEqualTo(output);
    }

    @Test
    void testEncodeNullStringWith0ClassicWay() {
        try {
            CaesarShift.encode(null, 0);
            fail("This line never have run");
        } catch (NullPointerException e) {
            assertThat(e).hasMessage("Input cannot be null");
        }
    }

    @Test
    void testEncodeNullStringWith5ShiftTheAssertJWay() {
        assertThatThrownBy(() -> CaesarShift.encode(null, 5))
                .hasMessage("Input cannot be null")
                .isInstanceOf(NullPointerException.class);
    }

    void testAAWith1() { }
    void testCapitalAWith1() { }
    void testAWithNegative1() { }
    void testAWithStrangeCharShiftOf1() { }
    void testAWithShiftOfMaxValue() { }
}
