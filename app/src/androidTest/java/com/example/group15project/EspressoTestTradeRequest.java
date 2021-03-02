package com.example.group15project;

import android.content.Context;

import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class EspressoTestTradeRequest {

    @BeforeClass
    public static void setup(){
        Intents.init();
    }

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.group15project", appContext.getPackageName());
    }

    @Test
    public void checkIfTradeRequestPageIsShown() {
        onView(withId(R.id.tradeRequestHeader)).check(matches(withText(R.string.trade_request)));
        onView(withId(R.id.titleHeader)).check(matches(withText(R.string.title_of_item)));
        onView(withId(R.id.itemTitle)).check(matches(withText(R.string.empty_string)));
        onView(withId(R.id.descriptionHeader)).check(matches(withText(R.string.description_of_item)));
        onView(withId(R.id.itemDescription)).check(matches(withText(R.string.empty_string)));
    }

    @Test
    public void isTitleEmpty(){
        onView(withId(R.id.itemTitle)).perform(typeText(""));
        onView(withId(R.id.itemDescription)).perform(typeText("Good condition and working Xbox 360 with one controller"));
        closeSoftKeyboard();
        onView(withId(R.id.sendRequestButton)).perform(click());
        onView(withId(R.id.tradeRequestStatusLabel)).check(matches(withText("Title is Empty")));
    }

    @Test
    public void isDescriptionEmpty(){
        onView(withId(R.id.itemTitle)).perform(typeText("Xbox 360"));
        onView(withId(R.id.itemDescription)).perform(typeText(""));
        closeSoftKeyboard();
        onView(withId(R.id.sendRequestButton)).perform(click());
        onView(withId(R.id.tradeRequestStatusLabel)).check(matches(withText("Description is Empty")));
    }
}
