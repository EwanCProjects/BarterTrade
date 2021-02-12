package com.example.group15project;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.content.Context;
import android.util.Patterns;
import static org.junit.Assert.*;




public class JUnitTestLogin {
/**
    static LoginActivity LoginActivity;

    @BeforeClass
    public static void setup() {
        LoginActivity = new LoginActivity();
    }

/*

    //AT-1
    //check if password empty
    @Test
    public void passwordEmpty() {
        assertFalse(LoginActivity.isPasswordValid(""));

    }

*/


    //AT-2
    //check email is in a valid format
    @Test
    public void validEmail(){
        assertTrue(LoginActivity.isValidEmailAddress("abc123@dal.ca"));
    }

    //AT-3
    //check email in an invalid format
    @Test
    public void invalidEmail(){
        assertFalse(LoginActivity.isValidEmailAddress("abc123dal.ca"));
        assertFalse(LoginActivity.isValidEmailAddress(""));
        assertFalse(LoginActivity.isValidEmailAddress("abc123dal@ca"));
    }


    //AT-3
    //check password
    @Test
    public void emptyPassword(){
        assertTrue(LoginActivity.isEmptyUserName(""));

    }

    //AT-3
    //check password
    @Test
    public void nonEmptyPassword(){
        assertFalse(LoginActivity.isEmptyUserName("ededede"));
        assertFalse(LoginActivity.isEmptyUserName("A"));
    }


    /*
    //AT-4
    //check if email is contained in the database
    @Test
    public void containsEmail() {
        //add this email to the DB
        assertTrue(LoginActivity.isEmailInDB("abc@de.fg"));

    }


    //AT-5
    //check if email is not contained in database
    @Test
    public void DoesntContainsEmail() {
        assertFalse(LoginActivity.isEmailInDB("zzz@de.fg"));

    }

*/

    //AT-6
    //check if the inputed password matches the password found to correspond to the email


  //  @Test
    //public void PasswordMatchesDBPassword() {
      //  assertTrue(LoginActivity.isPasswordMatching("dog123", "dog123"));
    //}

    //AT-7
    //check if the inputed password doesnt matches the password found to correspond to the email
    //@Test
    //public void PasswordDoesntMatchDBPassword() {
      //  assertFalse(LoginActivity.isPasswordMatching("dog321", "dog123"));
    //}


}


