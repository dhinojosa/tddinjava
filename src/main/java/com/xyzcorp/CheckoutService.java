package com.xyzcorp;

public class CheckoutService {
    private final CheckoutDAO checkoutDAO;

    public CheckoutService(CheckoutDAO checkoutDAO) {
       this.checkoutDAO = checkoutDAO;
    }

    public void store(Checkout checkout) {

        if (!checkoutDAO.persist(checkout)) throw new IllegalStateException("Cannot do it");
    }
}
