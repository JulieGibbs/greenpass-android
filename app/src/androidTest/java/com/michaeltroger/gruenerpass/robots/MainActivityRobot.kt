package com.michaeltroger.gruenerpass.robots

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withTagValue
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.michaeltroger.gruenerpass.R
import com.michaeltroger.gruenerpass.utils.waitUntilIdle
import com.michaeltroger.gruenerpass.utils.waitUntilNoException
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.`is`

class MainActivityRobot {

    fun verifyEmptyState() = apply {
        waitUntilIdle()
        waitUntilNoException {
            onView(withId(R.id.add)).check(matches(isDisplayed()))
        }
    }

    fun verifyPdfDocumentLoaded(docName: String) = apply {
        waitUntilNoException {
            onView(
                allOf(
                    withId(R.id.certificate),
                    withTagValue(`is`("loaded"))
                )
            ).check(matches(isDisplayed()))

            onView(withId(R.id.deleteIcon)).check(matches(isDisplayed()))
            onView(withText(docName)).check(matches(isDisplayed()))
        }
    }

    fun selectAddDocument(): AndroidFileAppRobot {
        waitUntilNoException {
            onView(withId(R.id.add)).perform(click())
        }
        return AndroidFileAppRobot()
    }
}