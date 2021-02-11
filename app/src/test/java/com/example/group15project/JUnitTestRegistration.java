package com.example.group15project;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import android.util.Patterns;
import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class JUnitTestRegistration {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    static MainActivity mainActivity;

    @BeforeClass
    public static void setup() {
        mainActivity = new MainActivity();
    }


/*      NEW TESTS
        ==========

        @Test
    public void checkIfFirstNameIsEmpty() {
        assertTrue(mainActivity.isEmptyFirstName(""));
        assertFalse(mainActivity.isEmptyFirstName("John"));
    }

        @Test
    public void checkIfLastNameIsEmpty() {
        assertTrue(mainActivity.isEmptyLastName(""));
        assertFalse(mainActivity.isEmptyLastName("Smith"));
    }

    @Test
    public void checkIfEmailIsEmpty() {
        assertTrue(mainActivity.isEmptyEmail(""));
        assertFalse(mainActivity.isEmptyLastName("abc.123@dal.ca"));
    }

    @Test
    public void checkIfPasswordIsEmpty() {
        assertTrue(mainActivity.isEmptyLastName(""));
        assertFalse(mainActivity.isEmptyLastName("abc123"));
    }

    @Test
    public void checkIfPasswordsMatch() {
        assertEquals(mainActivity.passwordsMatch(),true);
        assertEquals(mainActivity.passwordsNotMatch(),false);
    }

    @Test
    public void checkIfPasswordsNotMatch() {
        assertEquals(mainActivity.passwordsNotMatch(),true);
        assertEquals(mainActivity.passwordsMatch(),false);
    }

 */

    @Test
    public void checkIfEmailIsValid(){
        assertTrue(mainActivity.isValidEmailAddress("abc123@dal.ca"));
    }

    @Test
    public void checkIfEmailIsInValid(){
        assertFalse(mainActivity.isValidEmailAddress("abc.123dal.ca"));
    }

    @AfterClass
    public static void tearDown(){
        System.gc();
    }
}