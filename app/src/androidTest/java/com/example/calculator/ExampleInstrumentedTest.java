package com.example.calculator;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import com.example.calculator.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void testAddition() {
        onView(withId(R.id.button_five)).perform(click());
        onView(withId(R.id.button_plus)).perform(click());
        onView(withId(R.id.button_three)).perform(click());
        onView(withId(R.id.button_equals)).perform(click());

        // verifies the result is displayed as "8"
        onView(withId(R.id.textView_result)).check(matches(withText("8")));
    }

    @Test
    public void testDivisionByZero() {
        onView(withId(R.id.button_five)).perform(click());
        onView(withId(R.id.button_division)).perform(click());
        onView(withId(R.id.button_zero)).perform(click());
        onView(withId(R.id.button_equals)).perform(click());

        // verifies the result displays an error message "Error: Division by Zero"
        onView(withId(R.id.textView_result)).check(matches(withText("Error: Division by Zero")));
    }

    @Test
    public void testSquareRootOfNegative() {
        onView(withId(R.id.button_minus)).perform(click());
        onView(withId(R.id.button_nine)).perform(click());
        onView(withId(R.id.button_root)).perform(click());

        // verifies the result displays an error message "Invalid Input"
        onView(withId(R.id.textView_result)).check(matches(withText("Invalid Input")));
    }

    @Test
    public void testClearButton() {
        onView(withId(R.id.button_five)).perform(click());
        onView(withId(R.id.button_plus)).perform(click());
        onView(withId(R.id.button_three)).perform(click());
        onView(withId(R.id.button_clear)).perform(click());

        // verifies the result is cleared
        onView(withId(R.id.textView_result)).check(matches(withText("")));
    }
}
