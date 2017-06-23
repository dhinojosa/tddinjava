package com.xyzcorp;

import java.time.LocalDate;

public class Checkout {
    private String name;
    private LocalDate checkoutDate;

    public Checkout(String name, LocalDate checkoutDate) {
        this.name = name;
        this.checkoutDate = checkoutDate;
    }

    public String getName() {
        return name;
    }

    public LocalDate getCheckoutDate() {
        return checkoutDate;
    }
}
