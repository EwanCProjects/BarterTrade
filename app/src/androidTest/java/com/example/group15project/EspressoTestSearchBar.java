package com.example.group15project;

import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class EspressoTestSearchBar {

    /*
    @Rule
    public ActivityScenarioRule<SearchBarActivity> myRule = new ActivityScenarioRule<SearchBarActivity>(searchBarBtn.class);
    public IntentsTestRule<SearchBarActivity> myIntentRule = new IntentsTestRule<SearchBarActivity>(searchBarBtn.class);
     */
    @BeforeClass
    public static void setup(){
        Intents.init();
    }


    /*** AT-I**/
    @Test
    public void checkIfSearchPageIsShown() {
        onView(withId(R.id.hashTagSearch)).check(matches(withText(R.string.empty_string)));
        onView(withId(R.id.titleSearch)).check(matches(withText(R.string.empty_string)));
        onView(withId(R.id.searchBarBtn)).check(matches(withText("Search")));
    }

    /*** AT-II**/
    @Test
    public void checkIfSearchBarEmpty() {
        onView(withId(R.id.hashTagSearch)).perform(typeText(""));

        onView(withId(R.id.hashTagSearch)).check(matches(withText(R.string.empty_string)));
        onView(withId(R.id.titleSearch)).check(matches(withText(R.string.empty_string)));
        closeSoftKeyboard();

        onView(withId(R.id.searchBarBtn)).perform(click());
        onView(withId(R.id.statusLabel)).check(matches(withText("Empty input")));
    }


    /*** AT-III**/
    @Test
    public void checkIfResultPageIsEmpty() {
        onView(withId(R.id.hashTagSearch)).perform(typeText("Electronics"));
        onView(withId(R.id.titleSearch)).perform(typeText("PS6"));
        closeSoftKeyboard();

        onView(withId(R.id.searchBarBtn)).perform(click());
        onView(withId(R.id.statusLabel)).check(matches(withText("No results")));
    }


    /*** AT-IV**/
    @Test
    public void checkIfResultPageIsNotEmpty() {
        onView(withId(R.id.hashTagSearch)).perform(typeText("Electronics"));
        onView(withId(R.id.titleSearch)).perform(typeText("PS3"));
        closeSoftKeyboard();

        onView(withId(R.id.searchBarBtn)).perform(click());
        onView(withId(R.id.statusLabel)).check(matches(withText("")));
    }

}