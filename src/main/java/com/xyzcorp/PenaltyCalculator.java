package com.xyzcorp;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.function.Supplier;

public class PenaltyCalculator {

    private final Supplier<LocalDate> dateSupplier;
    private final int penaltyPerMonth;

    public PenaltyCalculator
	      (Supplier<LocalDate> dateSupplier, int penaltyPerMonth) {
	    this.dateSupplier = dateSupplier;
	    this.penaltyPerMonth = penaltyPerMonth;
	}

	public int calculate(LocalDate checkoutDate) {
		LocalDate todaysDate = dateSupplier.get();
        if (checkoutDate.isAfter(todaysDate))
        	throw new IllegalArgumentException("Checkout date cannot be after today's date");
		return (int) ChronoUnit.MONTHS.between(checkoutDate.plusDays(1), todaysDate) * penaltyPerMonth;
	}
}
