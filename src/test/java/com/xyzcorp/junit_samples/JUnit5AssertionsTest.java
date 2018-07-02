package com.xyzcorp.junit_samples;

import org.junit.jupiter.api.*;

import static java.time.Duration.*;
import static org.junit.jupiter.api.Assertions.*;

public class JUnit5AssertionsTest {

    @Test
    @DisplayName("Standard Assertion Testing")
    public void standardAssertions() {
        assertEquals(2, 2);
        assertEquals(4, 4, "The optional assertion message is now the last parameter.");
        assertTrue(2 == 2, () -> "Assertion messages can be lazily evaluated -- "
                + "to avoid constructing complex messages unnecessarily.");
    }

    @Test
    public void groupedAssertions() {
        // In a grouped assertion all assertions are executed, and any
        // failures will be reported together.

        Employee e = new Employee("Douglas", "Adams");
        assertAll("person",
                () -> assertEquals("Douglas", e.getFirstName()),
                () -> assertEquals("Adams", e.getLastName())
        );
    }

    @Test
    public void dependentAssertions() {
        // Within a code block, if an assertion fails the
        // subsequent code in the same block will be skipped.
        Employee employee = new Employee("Douglas", "Adams");
        assertAll("Employee Properties",
                () -> {
                    String firstName = employee.getFirstName();
                    assertNotNull(firstName);

                    // Executed only if the previous assertion is valid.
                    assertAll("first name",
                            () -> assertTrue(firstName.startsWith("D")),
                            () -> assertTrue(firstName.endsWith("s"))
                    );
                },
                () -> {
                    // Grouped assertion, so processed independently
                    // of results of first name assertions.
                    String lastName = employee.getLastName();
                    assertNotNull(lastName);

                    // Executed only if the previous assertion is valid.
                    assertAll("last name",
                            () -> assertTrue(lastName.startsWith("A")),
                            () -> assertTrue(lastName.endsWith("s"))
                    );
                }
        );
    }

    @Test
    void exceptionTesting() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            throw new IllegalArgumentException("a message");
        });

        assertEquals("a message", exception.getMessage());
    }

    @Test
    void timeoutNotExceeded() {
        // The following assertion succeeds.
        assertTimeout(ofSeconds(5), () -> {
            Thread.sleep(4000);
        });
    }

    @Test
    void timeoutNotExceededWithResult() {
        // The following assertion succeeds, and returns the supplied object.
        String actualResult = assertTimeout(ofSeconds(2), () -> {
            return "a result";
        });
        assertEquals("a result", actualResult);
    }

    @Test
    void timeoutNotExceededWithMethod() {
        Employee employee = new Employee("Carl", "Sagan");
        // The following assertion invokes a method reference and returns an object.
        String lastName = assertTimeout(ofSeconds(2), employee::getLastName);
        assertEquals("Sagan", lastName);
    }
}
