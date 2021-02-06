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
public class EspressoTestPost {

    @Rule
    public ActivityScenarioRule<PostActivity> myRule = new ActivityScenarioRule<>(PostActivity.class);
    public IntentsTestRule<PostActivity> myIntentRule=new IntentsTestRule<>(PostActivity.class);

    @BeforeClass
    public static void setup(){
        Intents.init();
    }


    /*** AT-I**/
    @Test
    public void checkIfRegistrationPageIsShown() {
        //this doesn't work right now, we need to add a way to get to the post page and update the test here
        //to do this we need to add a "post" button for the main design
        onView(withId(R.id.titleTextField)).check(matches(withText(R.string.empty_string)));
        onView(withId(R.id.categoryTextField)).check(matches(withText(R.string.empty_string)));
        onView(withId(R.id.postButton)).check(matches(withText("Register")));
    }
}