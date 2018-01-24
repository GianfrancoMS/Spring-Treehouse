package com.gianfranco.vending;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class VendingMachineTest {
    private VendingMachine vendingMachine;

    public class NotifierSub implements Notifier {
        @Override
        public void onSale(Item item) {
            return;
        }
    }

    @Before
    public void setUp() throws Exception {
        Notifier notifier = new NotifierSub();
        vendingMachine = new VendingMachine(notifier, 10, 10, 10);
        vendingMachine.restock("A1", "Twinkies", 10, 30, 75);
    }

    @Test
    public void vendingWhenStockedReturnsItem() throws Exception {
        vendingMachine.addMoney(75);

        Item item = vendingMachine.vend("A1");

        assertEquals("Twinkies", item.getName());
    }
}