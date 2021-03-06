



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
    static EditDescriptionActivity edActivity;

    @BeforeClass
    public static void setup() {
        edActivity = new EditDescriptionActivity();
    }

    @Test
    public void changeIsEmpty() {
        assertTrue(edActivity.isEmptyDescription(""));
    }

    @Test
    public void changeIsNotEmpty(){
        assertFalse(edActivity.isEmptyDescription("bla bla"));

    }
/*
    @Test
    public void descriptionIsUpdated(){
        assertFalse(edActivity.("Typing, typing, ..."));

    }
 */

    @AfterClass
    public static void tearDown(){
        System.gc();
    }
}
