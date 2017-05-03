package com.xyzcorp;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class PenaltyCalculatorTest {

    //GO SIMPLE, todays date, checkoutDate,
    @Test
    public void testPenaltyTodaysDateIsCheckoutDate() {
        LocalDate todaysDate = LocalDate.of(2017, 10, 12);
        LocalDate checkoutDate = LocalDate.of(2017, 10, 12);
        PenaltyCalculator penaltyCalculator = new PenaltyCalculator(() -> todaysDate, 5);
        int penalty = penaltyCalculator.calculate(checkoutDate);
        assertThat(penalty).isEqualTo(0);
    }

    @Test
    public void testPenaltyTodaysDateIsOneMonthFromCheckoutDate() {
        LocalDate todaysDate = LocalDate.of(2017, 11, 12);
        LocalDate checkoutDate = LocalDate.of(2017, 10, 12);
        PenaltyCalculator penaltyCalculator = new PenaltyCalculator(() -> todaysDate, 5);
        int penalty = penaltyCalculator.calculate(checkoutDate);
        assertThat(penalty).isEqualTo(0);
    }

    @Test
    public void testPenaltyTodaysDateIsOneMonthOneDayFromCheckoutDate() {
        LocalDate todaysDate = LocalDate.of(2017, 11, 13);
        LocalDate checkoutDate = LocalDate.of(2017, 10, 12);
        PenaltyCalculator penaltyCalculator = new PenaltyCalculator(() -> todaysDate, 5);
        int penalty = penaltyCalculator.calculate(checkoutDate);
        assertThat(penalty).isEqualTo(5);
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testCheckoutDateAfterTodaysDate() {
        LocalDate todaysDate = LocalDate.of(2017, 11, 13);
        LocalDate checkoutDate = LocalDate.of(2018, 1, 9);
        PenaltyCalculator penaltyCalculator = new PenaltyCalculator(() -> todaysDate, 5);
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Checkout date cannot be after today's date");
        penaltyCalculator.calculate(checkoutDate);
    }

    //We SHOULD definitely do a lot more test, be aggressive.
}
