package com.xyzcorp;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.time.LocalDate;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CheckoutDAOServiceTest {

    @Test
    public void testCheckoutPersist() throws Exception {
        Checkout checkout = new Checkout
                ("Dan Hinojosa", LocalDate.of(2017, 3, 4));
        CheckoutDAO checkoutDAO = mock(CheckoutDAO.class);

        when(checkoutDAO.persist(checkout)).thenReturn(true);

        CheckoutService checkoutService = new CheckoutService(checkoutDAO);
        checkoutService.store(checkout);
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testCheckoutPersistFalse() throws Exception {
        Checkout checkout = new Checkout
                ("Dan Hinojosa", LocalDate.of(2017, 3, 4));
        CheckoutDAO checkoutDAO = mock(CheckoutDAO.class);

        when(checkoutDAO.persist(checkout)).thenReturn(false);

        thrown.expect(IllegalStateException.class);
        CheckoutService checkoutService = new CheckoutService(checkoutDAO);
        checkoutService.store(checkout);
    }
}
