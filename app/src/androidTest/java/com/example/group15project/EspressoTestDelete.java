package com.example.group15project;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class EspressoTestDelete {

    public ActivityScenarioRule<DeletePostActivity> myRule = new ActivityScenarioRule<>(DeletePostActivity.class);
    public IntentsTestRule<DeletePostActivity> intentsTestRule = new IntentsTestRule<>(DeletePostActivity.class);

    @BeforeClass
    public static void setup() {
        Intents.init();
    }

    @Test
    public void checkAlertDisplay() {
        //onView(withId(R.id.deleteButton)).perform(click());
        //onView(withText("Are you sure you want to delete this post?")).check(matches(isDisplayed()));
    }

    @Test
    public void clickPositiveButton() {
        //onView(withId(R.id.deleteButton)).perform(click());
        //onView(withId(android.R.id.button1)).perform(click());

    }

    @Test
    public void clickNegativeButton() {
        //onView(withId(R.id.deleteButton)).perform(click());
        //onView(withId(android.R.id.button2)).perform(click());
    }

}
