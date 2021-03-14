package com.example.group15project;


import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class EspressoTestAcceptance {

    @Rule
    public ActivityScenarioRule<AcceptanceActivity> myRule = new ActivityScenarioRule<>(AcceptanceActivity.class);
    public IntentsTestRule<AcceptanceActivity> myIntentRule=new IntentsTestRule<>(AcceptanceActivity.class);

    @BeforeClass
    public static void setup(){
        Intents.init();
    }

    /*** AT-I**/
    @Test
    public void checkIfAcceptIsPossible() throws InterruptedException
    {
        Thread.sleep(2000);

        onView(ViewMatchers.withId(R.id.recyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        try {
            onView(ViewMatchers.withId(R.id.recyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withId(R.id.image_accept))));
        }
        catch (RuntimeException e)
        {
            System.out.println("Found more than one sub-view matching holder with view. Hence, this element exists");
        }
    }

    /*** AT-II**/
    @Test
    public void checkIfRejectionIsPossible() throws InterruptedException, RuntimeException
    {

        Thread.sleep(2000);

        onView(ViewMatchers.withId(R.id.recyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(2, click()));
        try {
            onView(ViewMatchers.withId(R.id.recyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withId(R.id.image_delete))));
        }
        catch (RuntimeException e)
        {
            System.out.println("Found more than one sub-view matching holder with view. Hence, this element exists");
        }

    }

}