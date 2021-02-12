/**
 * @authour: Aeriana MV Narbonne
 * @description: espresso tests for login to app
 * @source adapted from
 * -> Assignment 2
 * -> https://github.com/azygous13/Espresso-Login/blob/master/app/src/androidTest/java/com/bananacoding/expressologin/LoginActivityTest.java
 */

//package com.bananacoding.expressologin;


package com.example.group15project;

import com.example.group15project.LoginActivity;
import com.example.group15project.PostActivity;
import com.example.group15project.R;



import androidx.test.runner.AndroidJUnit4; // important!

import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

/**
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
 **/

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.platform.app.InstrumentationRegistry;


import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;


@RunWith(AndroidJUnit4.class)
public class EspressoTestLogin {
    @Rule
    public ActivityScenarioRule<LoginActivity> myRule = new ActivityScenarioRule<>(LoginActivity.class);
    public IntentsTestRule<LoginActivity> myIntentRule=new IntentsTestRule<>(LoginActivity.class);

    @BeforeClass
    public static void setup(){
        Intents.init();
    }


    @Test
    public void emailIsEmpty() {
        onView(withId(R.id.email)).perform(ViewActions.typeText(""));
    }

    @Test
    public void usernameIsEmpty() {
        onView(withId(R.id.username)).perform(ViewActions.typeText(""));
    }

    @Test
    public void checkIfEmailIsInvalid() {
        onView(withId(R.id.email)).perform(typeText("abc123.dal.ca"));
        onView(withId(R.id.login)).perform(click());
        onView(withId(R.id.statusLabel)).check(matches(withText(R.string.INVALID_EMAIL_ADDRESS)));
    }


    @Test
    public void checkIusernameIsInvalid() {
        onView(withId(R.id.username)).perform(typeText("abc123.dal.ca"));
        onView(withId(R.id.login)).perform(click());
        onView(withId(R.id.statusLabel)).check(matches(withText(R.string.INVALID_EMAIL_ADDRESS)));
    }


}