package com.gianfranco.vending;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CreditorTest {
    Creditor creditor;

    @Before
    public void setUp() throws Exception {
        creditor = new Creditor();
    }

    @Test
    public void addingFundsIncrementsAvailableFunds() throws Exception {
        creditor.addFunds(25);
        creditor.addFunds(25);

        assertEquals(50, creditor.getAvailableFunds());
    }

    @Test
    public void refundingReturnsAllAvailableFunds() throws Exception {
        creditor.addFunds(15);

        int refund = creditor.refund();

        assertEquals(15, refund);
    }

    @Test
    public void refundingResetAllAvailableFundsToZero() throws Exception {
        creditor.addFunds(15);

        creditor.refund();

        assertEquals(0, creditor.getAvailableFunds());
    }

}