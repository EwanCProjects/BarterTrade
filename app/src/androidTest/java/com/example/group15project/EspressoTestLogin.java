package com.example.group15project;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
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

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class EspressoTestLogin {

    @Rule
    public ActivityScenarioRule<PostActivity> myRule = new ActivityScenarioRule<>(PostActivity.class);
    public IntentsTestRule<PostActivity> myIntentRule=new IntentsTestRule<>(PostActivity.class);

    @BeforeClass
    public static void setup(){
        Intents.init();
    }


    /*** AT-I**/
    @Test
    public void checkIfPostPageIsShown() {
        //We also need to add a way to access the post page by adding a post button
        onView(withId(R.id.titleTextField)).check(matches(withText(R.string.empty_string)));
        onView(withId(R.id.categoryTextField)).check(matches(withText(R.string.empty_string)));
        onView(withId(R.id.postButton)).check(matches(withText("POST!")));
    }

    /*** AT-II**/
    @Test
    public void checkIfTitleIsEmpty() {
        onView(withId(R.id.titleTextField)).perform(typeText(""));
        onView(withId(R.id.postButton)).perform(click());
        onView(withId(R.id.statusLabel)).check(matches(withText(R.string.empty_title)));
    }

    /*** AT-III**/
    @Test
    public void checkIfCategoryIsEmpty() {
        onView(withId(R.id.categoryTextField)).perform(typeText(""));
        onView(withId(R.id.postButton)).perform(click());
        onView(withId(R.id.statusLabel)).check(matches(withText(R.string.empty_category)));
    }

    /*** AT-IV**/
    @Test
    public void checkIfDescriptionIsEmpty() {
        onView(withId(R.id.descriptionTextField)).perform(typeText(""));
        onView(withId(R.id.postButton)).perform(click());
        onView(withId(R.id.statusLabel)).check(matches(withText(R.string.empty_description)));
    }
}