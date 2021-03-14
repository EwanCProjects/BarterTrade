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
    static SearchBarActivity sbActivity;

    @BeforeClass
    public static void setup() {
        sbActivity = new SearchBarActivity();// ??
    }

    @Test
    public void checkIfSBIsEmpty() {
        assertTrue(sbActivity.isEmptyInput(""));
        assertFalse(sbActivity.isEmptyInput("ps5"));
    }

    @Test
    public void checkIfPostingExistsIsEmpty(){
        assertFalse(sbActivity.checkPostExistence(null, null, null));
        //assertTrue(sbActivity.checkPostExistence("chair", "furniture")); // this needs to pass

    }

    @AfterClass
    public static void tearDown(){
        System.gc();
    }
}

