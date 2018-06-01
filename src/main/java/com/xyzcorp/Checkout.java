package com.xyzcorp;

import java.time.LocalDate;

public class Checkout {
    private final LocalDate date;
    private final String name;
    private final String title;

    public Checkout(String name, String title, LocalDate date) {
        this.title = title;
        this.name = name;
        this.date = date;
    }

    public String getName() {

       return name;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Checkout(");
        sb.append(name).append(", ");
        sb.append(title).append(", ");
        sb.append(date);
        sb.append(')');
        return sb.toString();
    }
}
