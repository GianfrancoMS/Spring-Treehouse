package com.gianfranco.vending;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AlphaNumericChooserTest {
    AbstractChooser chooser;

    @Before
    public void setUp() throws Exception {
        chooser = new AlphaNumericChooser(26, 10);
    }

    @Test
    public void validInputReturnsProperLocation() throws Exception {
        AbstractChooser.Location location = chooser.locationFromInput("B4");

        assertEquals("proper row", 1, location.getRow());
        assertEquals("proper column", 3, location.getColumn());
    }

    @Test(expected = InvalidLocationException.class)
    public void choosingWrongInputIsNotAllowed() throws Exception {
        chooser.locationFromInput("WRONG");
    }

    @Test(expected = InvalidLocationException.class)
    public void choosingLargerThanMaxIsNotAllowed() throws Exception {
        chooser.locationFromInput("B52");
    }

    @Test
    public void constructingLargerThanAlphabetNotAllowed() throws Exception {
        new AlphaNumericChooser(27, 10);
    }
}