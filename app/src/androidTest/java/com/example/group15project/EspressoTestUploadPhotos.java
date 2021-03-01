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
    public ActivityScenarioRule<UploadPhotosActivity> myRule = new ActivityScenarioRule<>(UploadPhotosActivity.class);
    public IntentsTestRule<UploadPhotosActivity> myIntentRule=new IntentsTestRule<>(UploadPhotosActivity.class);

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
        onView(withId(R.id.titleTextField)).check(matches(withText(R.string.empty_string)));
        onView(withId(R.id.categoryTextField)).check(matches(withText(R.string.empty_string)));
        onView(withId(R.id.descriptionTextField)).check(matches(withText(R.string.empty_string)));
        onView(withId(R.id.postButton)).check(matches(withText("POST!")));
    }

    /*** AT-II**/
    /**
     * Click add/remove button photos and move to the new screen, with empty photos
     */
    @Test
    public void movedToUploadPhotosPage() {
        onView(withId(R.id.editPost)).perform(click());
        onView(withId(R.id.addOrRemovePhotos)).perform(click());
        onView(withId(R.id.photoSpot1)).check(matches(withText(R.string.empty_photo)));
        onView(withId(R.id.photoSpot2)).check(matches(withText(R.string.empty_photo)));
        onView(withId(R.id.photoSpot3)).check(matches(withText(R.string.empty_photo)));

    }


    /*** AT-III**/
    /**
     * click '+' on the empty photo to add, '+' -> 'x'
     */
    @Test
    public void addedPhotoFromPhone() {
        onView(withId(R.id.photoSpot1)).check(matches(withText(R.string.empty_photo)));
        onView(withId(R.id.addPhoto)).perform(click());

    }

    /*** AT-IV**/
    /**
     * click 'x' on the empty photo to remove
     */
    @Test
    public void photoIsRemoved() {
        onView(withId(R.id.photoSpot1)).check(matches(withText(R.string.empty_photo)));
        onView(withId(R.id.removePhoto)).perform(click());

    }


}