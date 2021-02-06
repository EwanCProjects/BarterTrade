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
public class JUnitTestPost {
    static PostActivity postActivity;

    @BeforeClass
    public static void setup() {
        postActivity = new PostActivity();
    }

    /*** AT-II**/
    @Test
    public void checkIfTitleIsEmpty() {
        assertTrue(postActivity.isEmptyTitle(""));
        assertFalse(postActivity.isEmptyTitle("xyz$56"));
    }

    /*** AT-III**/
    @Test
    public void checkIfCategoryIsEmpty() {
        assertTrue(postActivity.isEmptyCategory(""));
        assertFalse(postActivity.isEmptyCategory("xyz$56"));
    }

    /*** AT-IV**/
    @Test
    public void checkIfDescriptionIsEmpty() {
        assertTrue(postActivity.isEmptyDescription(""));
        assertFalse(postActivity.isEmptyDescription("xyz$56"));
    }

    @AfterClass
    public static void tearDown(){
        System.gc();
    }
}
