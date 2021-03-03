package com.example.group15project;

import android.app.Activity;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class JUnitTestSearchBar {
    static TestSearchBarActivity sbActivity;

    @BeforeClass
    public static void setup() {
        sbActivity = new TestSearchBarActivity();
    }

    @Test
    public void checkIfSBIsEmpty() {
        assertTrue(sbActivity.isEmptyInput(""));
        assertFalse(sbActivity.isEmptyInput("ps5"));
    }

    @Test
    public void checkIfOutputExists(){
        assertFalse(sbActivity.checkOutput("ps6"));
        assertTrue(sbActivity.checkOutput("ps2"));
    }

    @AfterClass
    public static void tearDown(){
        System.gc();
    }
}

