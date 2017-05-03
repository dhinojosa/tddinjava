package com.xyzcorp;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class CheckoutParserTest {

    @Test
    public void testParserWithOneLine() {
        CheckoutParser parser = new CheckoutParser("~");
        Checkout checkout = parser.parse("Timothy Cheng~Hamlet~2015-12-15");
        assertThat(checkout.getName()).isEqualTo("Timothy Cheng");
        assertThat(checkout.getCheckoutDate()).isEqualTo(LocalDate.of(2015, 12, 15));
    }

    @Test
    public void testParserWithOneLineAmit() {
        CheckoutParser parser = new CheckoutParser("~");
        Checkout checkout = parser.parse("Amit Sharma~Effective Java~2016-05-31");
        assertThat(checkout.getName()).isEqualTo("Amit Sharma");
        assertThat(checkout.getCheckoutDate()).isEqualTo(LocalDate.of(2016, 5, 31));
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();
    
    @Test
    public void testParserWithLessThan3Fields() {
        CheckoutParser parser = new CheckoutParser("~");
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("IllegalFormat");
        parser.parse("Amit Sharma~2016-05-31");
    }

    //TODO: Be more aggressive!

}
