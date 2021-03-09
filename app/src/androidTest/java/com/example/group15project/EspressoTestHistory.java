package com.example.group15project;

import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class EspressoTestHistory {

    @Rule
    public ActivityScenarioRule<HistoryActivity> myRule = new ActivityScenarioRule<>(HistoryActivity.class);

    @BeforeClass
    public static void setup(){
        Intents.init();
    }

    @Test
    public void checkIfHistoryPageIsShown() {
        onView(withId(R.id.historyHeader)).check(matches(withText(R.string.history)));
        onView(withId(R.id.postHeader)).check(matches(withText(R.string.posts)));
        onView(withId(R.id.tradeHeader)).check(matches(withText(R.string.trades)));
        onView(withId(R.id.closeButton)).check(matches(withText(R.string.close)));
        onView(withId(R.id.refreshButton)).check(matches(withText(R.string.refresh)));
    }

}
