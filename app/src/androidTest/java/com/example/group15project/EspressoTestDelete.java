package com.example.group15project;

import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class EspressoTestDelete {

    @Rule
    //public ActivityScenarioRule<DeletePostActivity> myRule = new ActivityScenarioRule<>(DeletePostActivity.class);
    //public IntentsTestRule<DeletePostActivity> myIntentRule = new IntentsTestRule<>(DeletePostActivity.class);

    @BeforeClass
    public static void setup(){
        Intents.init();
    }

}
