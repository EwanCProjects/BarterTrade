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

    static RegistrationActivity regActivity;

    @BeforeClass
    public static void setup() {
        regActivity = new RegistrationActivity();
    }


/*      NEW TESTS
        ==========*/

        @Test
    public void checkIfFirstNameIsEmpty() {
        assertTrue(regActivity.isEmptyFirstName(""));
        assertFalse(regActivity.isEmptyFirstName("John"));
    }

        @Test
    public void checkIfLastNameIsEmpty() {
        assertTrue(regActivity.isEmptyLastName(""));
        assertFalse(regActivity.isEmptyLastName("Smith"));
    }

    @Test
    public void checkIfEmailIsEmpty() {
        assertTrue(regActivity.isEmptyEmail(""));
        assertFalse(regActivity.isEmptyLastName("abc.123@dal.ca"));
    }

    @Test
    public void checkIfPasswordIsEmpty() {
        assertTrue(regActivity.isEmptyLastName(""));
        assertFalse(regActivity.isEmptyLastName("abc123"));
    }

    @Test
    public void checkIfPasswordsMatch() {
        assertEquals(true, regActivity.passwordsMatch("abc123","abc123"));
        //assertEquals(regActivity.passwordsNotMatch(),false);
    }

    @Test
    public void checkIfPasswordsNotMatch() {
        assertEquals(false, regActivity.passwordsMatch("abc123","abc223"));
        //assertEquals(regActivity.passwordsMatch(),false);
    }


    @Test
    public void checkIfEmailIsValid(){
        assertTrue(regActivity.isValidEmailAddress("abc123@dal.ca"));
    }

    @Test
    public void checkIfEmailIsInValid(){
        assertFalse(regActivity.isValidEmailAddress("abc.123dal.ca"));
    }

    @AfterClass
    public static void tearDown(){
        System.gc();
    }
}