



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
public class JUnitTestEditDescription {
    static TestEditDescriptionActivity edActivity;

    @BeforeClass
    public static void setup() {
        edActivity = new TestEditDescriptionActivity();
    }

    @Test
    public void checkIfChangeIsEmpty() {
        assertTrue(edActivity.isEmptyChange(""));

    }

    @Test
    public void checkIfChangeIsNotEmpty(){
        assertFalse(edActivity.isEmptyChange("bla bla"));

    }

    @AfterClass
    public static void tearDown(){
        System.gc();
    }
}
