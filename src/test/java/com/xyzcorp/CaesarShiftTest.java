package com.xyzcorp;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.fail;

public class CaesarShiftTest {

    //Hello -> +5 -> Mjqqt
    //Striving for red bar tests!

    //Start with Simplest & Dumbest Input As the first test


    //MIN_VALUE
    private static Stream<Arguments> encodeProvider() {
        return Stream.of(
                Arguments.of("", 0, ""),
                Arguments.of("a", 0, "a"),
                Arguments.of("", 1, ""),
                Arguments.of("a", 1, "b"),
                Arguments.of("z", 1, "a"),
                Arguments.of("ab", 1, "bc"),
                Arguments.of("ab", 26, "ab"), //Green
                Arguments.of("ab", 260, "ab"), //Red
                Arguments.of("ab", 26000, "ab"), //Red
                Arguments.of("b", Integer.MAX_VALUE, "y"), //Red
                Arguments.of("ab", Integer.MAX_VALUE, "xy"), //Red
                Arguments.of("z", -1, "y"), //Green
                Arguments.of("a", -1, "z"), //Red
                Arguments.of("a", -2600, "a"), //Green
                Arguments.of("~", 20, "~"), //Red
                Arguments.of("A", 1, "B"), //Red
                Arguments.of("Z", 1, "A"), //Green
                Arguments.of("a", Integer.MIN_VALUE, "c") //Green
        );
    }

    @ParameterizedTest(name= "{index}: input={0}, shift={1}, expected={2}")
    @MethodSource("encodeProvider")
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

    private static Stream<Arguments> decodeProvider() {
        return Stream.of(
                Arguments.of("", 0, ""), //Red
                Arguments.of("a", 0, "a"), //Green
                Arguments.of("", 1, ""),  //Green
                Arguments.of("a", 1, "z"), //Red
                Arguments.of("z", 1, "y"), //Red
                Arguments.of("ab", 1, "za"), //Green
                Arguments.of("ab", 26, "ab"), //Green
                Arguments.of("ab", 260, "ab"), //Green
                Arguments.of("ab", 26000, "ab"), //Green
                Arguments.of("b", Integer.MAX_VALUE, "e"), //Green
                Arguments.of("ab", Integer.MAX_VALUE, "de"), //Green
                Arguments.of("z", -1, "a"), //Green
                Arguments.of("a", -1, "b"), //Green
                Arguments.of("a", -2600, "a"), //Green
                Arguments.of("~", 20, "~"), //Green
                Arguments.of("A", 1, "Z"), //Green
                Arguments.of("Z", 1, "Y"), //Green
                Arguments.of("z", Integer.MIN_VALUE, "b") //Green
        );
    }

    @ParameterizedTest(name= "{index}: input={0}, shift={1}, expected={2}")
    @MethodSource("decodeProvider")
    void testDecode(String input, int shift, String output) {
        String result = CaesarShift.decode(input, shift);
        assertThat(result).isEqualTo(output);
    }

    @Test
    void testEncodeNullStringWith5ShiftTheAssertJWay() {
        assertThatThrownBy(() -> CaesarShift.encode(null, 5))
                .hasMessage("Input cannot be null")
                .isInstanceOf(NullPointerException.class);
    }
}
