package com.app.collegeadmissionapp

import com.app.collegeadmissionapp.R
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.* // REQUIRED: For click(), typeText(), closeSoftKeyboard()
import androidx.test.espresso.matcher.ViewMatchers.* // REQUIRED: For withId(), withText()
import androidx.test.espresso.assertion.ViewAssertions.matches // REQUIRED: For check(matches())
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.app.collegeadmissionapp.ui.LoginActivity
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(LoginActivity::class.java)

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.app.collegeadmissionapp", appContext.packageName)
    }

    @Test
    fun validateLoginSuccess() {
        // 2. Locate the username field and perform a typeText action
        onView(withId(R.id.emailEditText))
            .perform(typeText("testuser"), closeSoftKeyboard())

        // 3. Locate the password field and perform a typeText action
        onView(withId(R.id.passwordEditText))
            .perform(typeText("password123"), closeSoftKeyboard())

        // 4. Locate the login button and perform a click action
        onView(withId(R.id.loginButton))
            .perform(click())

        // 5. Verify the result: check if a success message is displayed
        onView(withText("Welcome, testuser!"))
            .check(matches(isDisplayed()))
    }
}