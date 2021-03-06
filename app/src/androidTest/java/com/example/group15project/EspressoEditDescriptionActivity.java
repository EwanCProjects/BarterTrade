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
public class EspressoEditDescriptionActivity {

    @Rule
    public ActivityScenarioRule<EditPostActivity> myRule
            = new ActivityScenarioRule<>(EditPostActivity.class);

    public IntentsTestRule<EditPostActivity> myIntentRule
            = new IntentsTestRule<>(EditPostActivity.class);

    @BeforeClass
    public static void setup(){
        Intents.init();
    }


    // HELP 2
    /*** AT-I**/
    @Test
    public void checkIfEditPageIsShown() {
        onView(withId(R.id.editDescBtn)).check(matches(withText("update")));

    }
}
