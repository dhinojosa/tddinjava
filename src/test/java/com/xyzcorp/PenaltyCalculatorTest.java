package com.xyzcorp;

import org.junit.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PenaltyCalculatorTest {

    @Test
    public void testTodaysDateIsTheSameAsTheCheckoutDate() {
        PenaltyCalculator penaltyCalculator = new PenaltyCalculator(10);
        long penalty = penaltyCalculator.calculate(LocalDate.of(2016, 1, 4),
                LocalDate.of(2016, 1, 4));
        assertThat(penalty).isEqualTo(0);
    }

    @Test
    public void testTodaysDateIsOneMonthFromTheCheckoutDate() {
        PenaltyCalculator penaltyCalculator = new PenaltyCalculator(10);
        long penalty = penaltyCalculator.calculate(
                LocalDate.of(2016, 5, 4),
                LocalDate.of(2016, 4, 4));
        assertThat(penalty).isEqualTo(0);
    }

    @Test
    public void testTodaysDateIsOneMonthOneDayFromTheCheckoutDate() {
        PenaltyCalculator penaltyCalculator = new PenaltyCalculator(10);
        long penalty = penaltyCalculator.calculate(
                LocalDate.of(2016, 5, 5),
                LocalDate.of(2016, 4, 4));
        assertThat(penalty).isEqualTo(10);
    }

    @Test
    public void testLeapYearSituation() {
        PenaltyCalculator penaltyCalculator = new PenaltyCalculator(10);
        long penalty = penaltyCalculator.calculate(
                LocalDate.of(2016, 3, 15), //todays
                LocalDate.of(2016, 2, 14)); //checkoutDate
        assertThat(penalty).isEqualTo(10);
    }

    @Test
    public void testCheckoutDateIsGreaterThanTodaysDate() {
        PenaltyCalculator penaltyCalculator = new PenaltyCalculator(10);
        assertThatThrownBy(() -> penaltyCalculator.calculate(
                LocalDate.of(2016, 1, 15), //todays
                LocalDate.of(2016, 5, 14))) //checkoutDate
                                            .isInstanceOf(IllegalArgumentException.class)
                                            .hasMessage("Checkout date cannot be greater than today's date");
    }

    @Test
    public void testCheckoutDateNullForCheckoutDate() {
        PenaltyCalculator penaltyCalculator = new PenaltyCalculator(10);
        assertThatThrownBy(() ->
                penaltyCalculator.calculate(
                        LocalDate.of(2016, 1, 15), //todays
                        null)) //checkoutDate
                               .isInstanceOf(NullPointerException.class)
                               .hasMessage("Checkout date cannot be null");
    }

    @Test
    public void testCheckoutDateNullForTodaysDate() {
        PenaltyCalculator penaltyCalculator = new PenaltyCalculator(10);
        assertThatThrownBy(() ->
                penaltyCalculator.calculate(null,                 //todays
                        LocalDate.of(2016, 1, 15))) //checkoutDate
                               .isInstanceOf(NullPointerException.class)
                               .hasMessage("Today's date cannot be null");
    }
}
