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


// addPhoto = +
// removePhoto = x

@RunWith(AndroidJUnit4.class)
public class EspressoTestUploadPhotos {

    @Rule
    public ActivityScenarioRule<PostImageActivity> myRule = new ActivityScenarioRule<>(PostImageActivity.class);
    public IntentsTestRule<PostImageActivity> myIntentRule=new IntentsTestRule<>(PostImageActivity.class);

    @BeforeClass
    public static void setup(){
        Intents.init();
    }


    /*** AT-I**/
    /**
     * Need to check that we are on the Posting Page. This will need to be updated as
     * the posting page is updated
     */
    @Test
    public void postPageIsShown() {
        //We also need to add a way to access the post page by adding a post button
        onView(withId(R.id.btnChoose)).check(matches(withText("Choose")));
        onView(withId(R.id.btnUpload)).check(matches(withText("Upload")));
        onView(withId(R.id.btnreturnpost)).check(matches(withText("done")));
    }

    /*** AT-II**/
    /**
     * Check that an image is chosen
     */
    @Test
    public void noChosenImages() {
        onView(withId(R.id.btnUpload)).perform(click());
        onView(withId(R.id.statusLabel)).check(matches(withText("please choose an image")));
    }




}