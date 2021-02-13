package com.example.group15project;

import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

/**
 * import org.junit.BeforeClass;
 * import org.junit.Rule;
 * import org.junit.Test;
 * import org.junit.runner.RunWith;
 **/


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
        onView(withId(R.id.email)).perform(ViewActions.typeText(""));
    }
    @Test
    public void checkIfEmailIsInvalid() {
        onView(withId(R.id.email)).perform(typeText("abc123.dal.ca"));
        onView(withId(R.id.loginB)).perform(click());
        onView(withId(R.id.statusLabel)).check(matches(withText(R.string.invalid_email)));
    }

    @Test
    public void checkIfUsernameIsInvalid() {
        onView(withId(R.id.email)).perform(typeText("abc123.dal.ca"));
        onView(withId(R.id.loginB)).perform(click());
        onView(withId(R.id.statusLabel)).check(matches(withText(R.string.invalid_email)));
    }

}