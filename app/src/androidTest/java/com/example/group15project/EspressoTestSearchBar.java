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

    @Rule
    public ActivityScenarioRule<PostActivity> myRule = new ActivityScenarioRule<>(searchBar.class);
    public IntentsTestRule<PostActivity> myIntentRule=new IntentsTestRule<>(searchBar.class);

    @BeforeClass
    public static void setup(){
        Intents.init();
    }


    /*** AT-I**/
    @Test
    public void checkIfSearchPageIsShown() {
        onView(withId(R.id.editTextSearchBar)).check(matches(withText(R.string.empty_string)));
        onView(withId(R.id.searchButton)).check(matches(withText("Search")));
    }

    /*** AT-II**/
    @Test
    public void checkIfSearchBarEmpty() {
        onView(withId(R.id.editTextSearchBar)).perform(typeText(""));
        closeSoftKeyboard();
        onView(withId(R.id.searchButton)).perform(click());
        onView(withId(R.id.statusLabel)).check(matches(withText("Empty input")));
    }


    /*** AT-III**/
    @Test
    public void checkIfResultPageIsEmpty() {
        onView(withId(R.id.editTextSearchBar)).perform(typeText("PS6"));
        closeSoftKeyboard();
        onView(withId(R.id.searchButton)).perform(click());
        onView(withId(R.id.statusLabel)).check(matches(withText("No results")));    }


    /*** AT-IV**/
    @Test
    public void checkIfResultPageIsNotEmpty() {
        onView(withId(R.id.editTextSearchBar)).perform(typeText("PS2"));
        closeSoftKeyboard();
        onView(withId(R.id.searchButton)).perform(click());
        onView(withId(R.id.statusLabel)).check(matches(withText("")));
    }

}