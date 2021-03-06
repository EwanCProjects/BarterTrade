package com.example.group15project;

import org.junit.Test;

import testActivities.TestLoginActivity;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class JUnitTestLogin {

    //AT-2
    //check email is in a valid format
    @Test
    public void validEmail(){
        assertTrue(TestLoginActivity.isValidEmailAddress("abc123@dal.ca"));
    }

    //AT-3
    //check email in an invalid format
    @Test
    public void invalidEmail(){
        assertFalse(TestLoginActivity.isValidEmailAddress("abc123dal.ca"));
        assertFalse(TestLoginActivity.isValidEmailAddress(""));
        assertFalse(TestLoginActivity.isValidEmailAddress("abc123dal@ca"));
    }


    //AT-3
    //check password
    @Test
    public void emptyPassword(){
        assertTrue(TestLoginActivity.isEmptyUserName(""));

    }

    //AT-3
    //check password
    @Test
    public void nonEmptyPassword(){
        assertFalse(TestLoginActivity.isEmptyUserName("ededede"));
        assertFalse(TestLoginActivity.isEmptyUserName("A"));
    }


}


