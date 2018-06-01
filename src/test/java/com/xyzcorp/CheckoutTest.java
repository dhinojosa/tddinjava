package com.xyzcorp;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class CheckoutTest {

    @Test
    void testToString() {
        Checkout checkout = new Checkout("Bob Marley",
                "All about Raggae",
                LocalDate.of(2015,3,10));

        assertThat(checkout.toString())
                .isEqualTo("Checkout(Bob Marley, All about Raggae, 2015-03-10)");
    }
}
