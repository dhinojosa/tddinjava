package com.xyzcorp.junit_samples;

import org.junit.jupiter.api.*;

public class JUnit5DisplayNameTest {

    @Test
    @DisplayName("Custom test name containing spaces")
    public void testWithDisplayNameContainingSpaces() {
    }

    @Test
    @DisplayName("╯°□°）╯")
    public void testWithDisplayNameContainingSpecialCharacters() {
    }

    @Test
    @DisplayName("😱")
    public void testWithDisplayNameContainingEmoji() {
    }
}
