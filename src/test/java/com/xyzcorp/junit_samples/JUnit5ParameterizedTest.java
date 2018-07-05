package com.xyzcorp.junit_samples;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.EnumSource.Mode.EXCLUDE;
import static org.junit.jupiter.params.provider.EnumSource.Mode.MATCH_ALL;

public class JUnit5ParameterizedTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void testWithValueSource(int argument) {
        assertNotNull(argument);
    }


    @ParameterizedTest
    @MethodSource("range")
    void testWithRangeMethodSource(int argument) {
        assertNotEquals(9, argument);
    }

    private static IntStream range() {
        return IntStream.range(0, 20).skip(10);
    }

    @ParameterizedTest
    @EnumSource(TimeUnit.class)
    void testWithEnumSource(TimeUnit timeUnit) {
        assertNotNull(timeUnit);
    }

    //We can knock out certain tests
    @ParameterizedTest
    @EnumSource(value = TimeUnit.class, names = {"DAYS", "HOURS"})
    void testWithEnumSourceInclude(TimeUnit timeUnit) {
        assertTrue(EnumSet.of(TimeUnit.DAYS, TimeUnit.HOURS).contains(timeUnit));
    }

    /**
     * optional mode parameter that enables fine-grained control over which constants are
     * passed to the test method. For example, you can exclude names from the enum constant pool
     *
     * mode includes EXCLUDE, INCLUDE, MATCH_ALL, MATCH_ANY
     *
     * @param timeUnit the time unit parameter
     */
    @ParameterizedTest
    @EnumSource(value = TimeUnit.class, mode = EXCLUDE, names = {"DAYS", "HOURS"})
    void testWithEnumSourceExclude(TimeUnit timeUnit) {
        assertFalse(EnumSet.of(TimeUnit.DAYS, TimeUnit.HOURS).contains(timeUnit));
        assertTrue(timeUnit.name().length() > 5);
    }

    /**
     * optional mode parameter that enables fine-grained control over which constants are
     * passed to the test method. For example, you can specify regular expressions
     *
     * mode includes EXCLUDE, INCLUDE, MATCH_ALL, MATCH_ANY
     *
     * @param timeUnit the time unit parameter
     */
    @ParameterizedTest
    @EnumSource(value = TimeUnit.class, mode = MATCH_ALL, names = "^(M|N).+SECONDS$")
    void testWithEnumSourceRegex(TimeUnit timeUnit) {
        String name = timeUnit.name();
        assertTrue(name.startsWith("M") || name.startsWith("N"));
        assertTrue(name.endsWith("SECONDS"));
    }

    /* If a test method declares multiple parameters, you need to return
     * a collection or stream of Arguments instances as shown below.
     * Note that Arguments.of(Object…​) is a static factory method defined
     * in the Arguments interface.
     */
    private static Stream<Arguments> stringIntAndListProvider() {
        return Stream.of(
                Arguments.of("foo", 1, Arrays.asList("a", "b")),
                Arguments.of("bar", 2, Arrays.asList("x", "y"))
        );
    }

    @ParameterizedTest(name= "{index}: first={0}, second={1}, third={2}")
    @MethodSource("stringIntAndListProvider")
    void testWithMultiArgMethodSource(String str, int num, List<String> list) {
        assertEquals(3, str.length());
        assertTrue(num >=1 && num <=2);
        assertEquals(2, list.size());
    }

    @ParameterizedTest(name= "{index} ==> first=''{0}'', second={1}")
    @CsvSource({ "foo, 1", "bar, 2", "'baz, qux', 3" })
    void testWithCsvSource(String first, int second) {
        assertNotNull(first);
        assertNotEquals(0, second);
    }
}
