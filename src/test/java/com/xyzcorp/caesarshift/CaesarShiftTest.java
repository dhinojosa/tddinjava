package com.xyzcorp.caesarshift;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;


public class CaesarShiftTest {

    //Inputs: "Foo", 5
    //Outputs: "Ktt"

    //Simplest (Dumbest) Inputs and Outputs?
    //Inputs:  "",0
    //Outputs: ""

    //Other tests
    //null, ('a',0), ('a', 1), ('a', 26), ("", -1), ("aaa", 0), (z, "3") = c
    //("~", -1), ('a', Integer.MAX_VALUE), ("ab", 0), ("ab", 1), ("AB", 1),
    //("a", -1), ("a", -4),
    //
    //
    // decode?
    // 1. Stay simple
    // 2. Focus on that
    // 3. Refactor only if you have green bar tests
    // 4. Check code coverage, mvn package, open/start target/site/jacoco/index.html


    @Test
    void testEncodeWithEmptyStringAnd0Shift() {
        //How do we create it?

        //Votes: 8
        //1. CaesarShift cs = new CaesarShift(0);
        //   cs.encode("");
        //   cs.decode("");

        //Votes: 2
        //2. CaesarShift cs = new CaesarShift();
        //   cs.encode("", 0);
        //   cs.decode("", 0);

        //Votes: 6
        //3. Caesar.encode("", 0);
        //   Caesar.decode("", 0);

        //Votes: 2
        //4. CaesarShift cs = new CaesarShift("", 0);
        //   cs.encode();
        //   cs.decode();

        CaesarShift caesarShift = new CaesarShift(0);
        assertEquals("", caesarShift.encode(""));
    }

    @Test
    void testEncodeASingleCharacterWithAShiftOf0() {
        CaesarShift caesarShift = new CaesarShift(0);
        assertEquals("a", caesarShift.encode("a"));
    }

    private static Stream<Arguments> caesarShiftEncodeProvider() {
        return Stream.of(
                Arguments.of("", 0, ""),
                Arguments.of("a", 0, "a"),
                Arguments.of("", 1010, ""), //Green bar!
                Arguments.of("a", 1, "b"),
                Arguments.of("ab", 1, "bc"),
                Arguments.of("z", 1, "a"),
                Arguments.of("a", -1, "z"),
                Arguments.of("a", -4, "w"),
                Arguments.of("a", 260, "a"),
                Arguments.of("a", -260, "a"),
                Arguments.of("~", 10, "~"),
                Arguments.of(" ", 10, " "),
                Arguments.of("A", 1, "B"),
                Arguments.of("A", 0, "A"),
                Arguments.of("Z", 1, "A")
        );
    }

    private static Stream<Arguments> caesarShiftDecodeProvider() {
        return Stream.of(
                Arguments.of("", 0, ""),
                Arguments.of("a", 0, "a"),
                Arguments.of("", 1010, ""),
                Arguments.of("b", 1, "a"),
                Arguments.of("z", 1, "y"),
                Arguments.of("a", 1, "z"),
                Arguments.of("a", 260, "a"),
                Arguments.of("a", -260, "a"),
                Arguments.of("~", 10, "~"),
                Arguments.of(" ", 10, " "),
                Arguments.of("A", 1, "Z")
        );
    }

    @ParameterizedTest(name = "{index}, originalString: {0}, shift: {1}, result:{2}")
    @MethodSource(value = {"caesarShiftEncodeProvider"})
    public void testCaesarShiftEncode(String original, int shift, String result) {
        CaesarShift caesarShift = new CaesarShift(shift);
        assertThat(caesarShift.encode(original)).isEqualTo(result);
    }


    @ParameterizedTest(name = "{index}, originalString: {0}, shift: {1}, result:{2}")
    @MethodSource(value = {"caesarShiftDecodeProvider"})
    public void testCaesarShiftDecode(String original, int shift, String result) {
        CaesarShift caesarShift = new CaesarShift(shift);
        assertThat(caesarShift.decode(original)).isEqualTo(result);
    }

    @Test
    void testEncodeWithANullAsOriginalStringClassicWay() {
        try {
            CaesarShift caesarShift = new CaesarShift(5);
            caesarShift.encode(null);
            fail("This line should never be called");
        } catch (NullPointerException npe) {
            assertThat(npe.getMessage())
                    .isEqualTo("Original string cannot be null");
        }
    }

    @Test
    void testEncodeWithANullAsOriginalStringJUnit5() {
        CaesarShift caesarShift = new CaesarShift(5);

        NullPointerException nullPointerException = assertThrows
                (NullPointerException.class, () -> caesarShift.encode(null));

        assertThat(nullPointerException).hasMessage("Original string cannot be null");
    }

    @Test
    void testEncodeWithANullAsOriginalStringAssertJ() {
        CaesarShift caesarShift = new CaesarShift(5);
        assertThatThrownBy(() -> caesarShift.encode(null))
                .hasMessage("Original string cannot be null");
    }
}






