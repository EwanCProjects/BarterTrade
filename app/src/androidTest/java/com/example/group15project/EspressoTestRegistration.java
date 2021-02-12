package com.example.group15project;

import android.content.Context;

import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

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
import static org.junit.Assert.assertEquals;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class EspressoTestRegistration {

    @Rule
    public ActivityScenarioRule<TestRegistrationActivity> myRule = new ActivityScenarioRule<>(TestRegistrationActivity.class);
    public IntentsTestRule<TestRegistrationActivity> myIntentRule=new IntentsTestRule<>(TestRegistrationActivity.class);

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
    public void checkIfRegistrationPageIsShown() {
        onView(withId(R.id.editTextFirstName)).check(matches(withText(R.string.empty_string)));
        onView(withId(R.id.editTextLastName)).check(matches(withText(R.string.empty_string)));
        onView(withId(R.id.editTextEmailAddress)).check(matches(withText(R.string.empty_string)));
        onView(withId(R.id.editTextPassword)).check(matches(withText(R.string.empty_string)));
        onView(withId(R.id.editTextConfirmPassword)).check(matches(withText(R.string.empty_string)));
        onView(withId(R.id.registerHeader)).check(matches(withText(R.string.register)));
    }


/*      NEW TESTS
        ==========*/
        @Test
    public void checkIfFirstNameIsEmpty() {
        onView(withId(R.id.editTextEmailAddress)).perform(typeText("abc.123@dal.ca"));
        onView(withId(R.id.editTextFirstName)).perform(typeText(""));
        onView(withId(R.id.editTextLastName)).perform(typeText("Smith"));
        onView(withId(R.id.editTextPassword)).perform(typeText("abc123"));
        onView(withId(R.id.editTextConfirmPassword)).perform(typeText("abc123"));
        closeSoftKeyboard();
        onView(withId(R.id.signUpButton)).perform(click());
        onView(withId(R.id.statusLabel)).check(matches(withText("First name is empty!")));
    }

        @Test
    public void checkIfLastNameIsEmpty() {
        onView(withId(R.id.editTextEmailAddress)).perform(typeText("abc.123@dal.ca"));
        onView(withId(R.id.editTextFirstName)).perform(typeText("John"));
        onView(withId(R.id.editTextLastName)).perform(typeText(""));
        onView(withId(R.id.editTextPassword)).perform(typeText("abc123"));
        onView(withId(R.id.editTextConfirmPassword)).perform(typeText("abc123"));
        closeSoftKeyboard();
        onView(withId(R.id.signUpButton)).perform(click());
        onView(withId(R.id.statusLabel)).check(matches(withText("Last name is empty!")));
    }

        @Test
    public void checkIfEmailIsEmpty() {
        onView(withId(R.id.editTextEmailAddress)).perform(typeText(""));
        onView(withId(R.id.editTextFirstName)).perform(typeText("John"));
        onView(withId(R.id.editTextLastName)).perform(typeText("Smith"));
        onView(withId(R.id.editTextPassword)).perform(typeText("abc123"));
        onView(withId(R.id.editTextConfirmPassword)).perform(typeText("abc123"));
        closeSoftKeyboard();
        onView(withId(R.id.signUpButton)).perform(click());
        onView(withId(R.id.statusLabel)).check(matches(withText("Email is empty!")));
    }

        @Test
    public void checkIfPasswordsMatch() {
        onView(withId(R.id.editTextEmailAddress)).perform(typeText("abc.123@dal.ca"));
        onView(withId(R.id.editTextFirstName)).perform(typeText("John"));
        onView(withId(R.id.editTextLastName)).perform(typeText("Smith"));
        onView(withId(R.id.editTextPassword)).perform(typeText("abc123"));
        onView(withId(R.id.editTextConfirmPassword)).perform(typeText("abc123"));
        closeSoftKeyboard();
        onView(withId(R.id.signUpButton)).perform(click());
        onView(withId(R.id.statusLabel)).check(matches(withText("")));
    }

        /*@Test
    public void checkIfPasswordsNotMatch() {
        onView(withId(R.id.editTextEmailAddress)).perform(typeText("abc.123@dal.ca"));
        onView(withId(R.id.editTextFirstName)).perform(typeText("John"));
        onView(withId(R.id.editTextLastName)).perform(typeText("Smith"));
        onView(withId(R.id.editTextPassword)).perform(typeText("abc123"));
        onView(withId(R.id.editTextConfirmPassword)).perform(typeText("abc223"));
        onView(withId(R.id.signUpButton)).perform(click());
        onView(withId(R.id.statusLabel)).check(matches(withText("Passwords do not match")));
    }*/

    @Test
    public void checkIfPasswordEmpty() {
        onView(withId(R.id.editTextEmailAddress)).perform(typeText("abc.123@dal.ca"));
        onView(withId(R.id.editTextFirstName)).perform(typeText("John"));
        onView(withId(R.id.editTextLastName)).perform(typeText("Smith"));
        onView(withId(R.id.editTextPassword)).perform(typeText(""));
        onView(withId(R.id.editTextConfirmPassword)).perform(typeText(""));
        closeSoftKeyboard();
        onView(withId(R.id.signUpButton)).perform(click());
        onView(withId(R.id.statusLabel)).check(matches(withText("Password field empty!")));
    }



    @Test
    public void checkIfEmailIsValid() {
        onView(withId(R.id.editTextEmailAddress)).perform(typeText("abc123@dal.ca"));
        onView(withId(R.id.editTextFirstName)).perform(typeText("John"));
        onView(withId(R.id.editTextLastName)).perform(typeText("Smith"));
        onView(withId(R.id.editTextPassword)).perform(typeText("p"));
        onView(withId(R.id.editTextConfirmPassword)).perform(typeText("p"));
        closeSoftKeyboard();
        onView(withId(R.id.signUpButton)).perform(click());
        onView(withId(R.id.statusLabel)).check(matches(withText("")));
    }


    @Test
    public void checkIfEmailIsInvalid() {
        onView(withId(R.id.editTextEmailAddress)).perform(typeText("abc123.dal.ca"));
        onView(withId(R.id.editTextFirstName)).perform(typeText("John"));
        onView(withId(R.id.editTextLastName)).perform(typeText("Smith"));
        onView(withId(R.id.editTextPassword)).perform(typeText("p"));
        onView(withId(R.id.editTextConfirmPassword)).perform(typeText("p"));
        closeSoftKeyboard();
        onView(withId(R.id.signUpButton)).perform(click());
        onView(withId(R.id.statusLabel)).check(matches(withText("Email is invalid!")));
    }

    /*
    @Test
    public void checkIfMoved2WelcomePage(){

        onView(withId(R.id.editTextEmailAddress)).perform(typeText("abc123@dal.ca"));
        onView(withId(R.id.registerButton)).perform(click());
        intended(hasComponent(LoginActivity.class.getName()));
    }
     */
}