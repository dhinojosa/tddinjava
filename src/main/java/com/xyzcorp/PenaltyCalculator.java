package com.xyzcorp;

import static java.time.temporal.ChronoUnit.MONTHS;
import java.time.LocalDate;
import java.util.Objects;

public class PenaltyCalculator {
    private final int penaltyPerMonth;

    public PenaltyCalculator(int penaltyPerMonth) {
       this.penaltyPerMonth = penaltyPerMonth;
    }

    public long calculate(LocalDate todaysDate, LocalDate checkoutDate) {
        Objects.requireNonNull(checkoutDate, "Checkout date cannot be null");
        Objects.requireNonNull(todaysDate, "Today's date cannot be null");
        if (checkoutDate.isAfter(todaysDate)) throw new IllegalArgumentException("Checkout date cannot be greater than today's date");
        return MONTHS.between(checkoutDate.plusDays(1), todaysDate) * penaltyPerMonth;
    }
}
