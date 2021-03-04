package com.example.group15project;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class JUnitTestTradeRequest {

    static TradeRequestActivity tradeRequestActivity;

    @BeforeClass
    public static void setup(){ tradeRequestActivity = new TradeRequestActivity();}

    @Test
    public void isTitleEmpty() {
        assertFalse(tradeRequestActivity.isTitleEmpty("Xbox 360"));
        assertTrue(tradeRequestActivity.isTitleEmpty(""));
    }

    @Test
    public void isDescriptionEmpty() {
        assertFalse(tradeRequestActivity.isDescriptionEmpty("1 year old Xbox 360 with one controller"));
        assertTrue(tradeRequestActivity.isDescriptionEmpty(""));
    }

    @AfterClass
    public static void tearDown(){ System.gc(); }
}
