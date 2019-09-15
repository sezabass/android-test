package com.cesar.androidtest

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import android.view.View
import com.cesar.androidtest.recentposts.presentation.RecentPostsActivity
import org.hamcrest.Matcher
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class RecentPostsActivityAndroidTest {

    @get:Rule
    var activityRule = ActivityTestRule<RecentPostsActivity>(RecentPostsActivity::class.java)

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.cesar.androidtest", appContext.packageName)
    }

    @Test
    fun ensureSwipeDownWillRefresh() {
        onView(withId(R.id.recentPostsSwipeRefreshLayout))
                .perform(swipeDownCustomConstraints(
                        ViewActions.swipeDown(), isDisplayingAtLeast(85)
                ))
    }

    private fun swipeDownCustomConstraints(action: ViewAction, constraints: Matcher<View>): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> = constraints
            override fun getDescription(): String = action.description
            override fun perform(uiController: UiController, view: View) =
                    action.perform(uiController, view)
        }
    }

    @Test
    fun ensureRecyclerViewIsVisible() {
        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()))
    }

    //TODO: fun ensureRecyclerViewItemsAreProperlyLoaded() {} --> via MockWebServer
    //TODO: fun ensureRecyclerViewItemsAreClickable() {}
    //TODO: fun ensureRecyclerViewItemsClickWouldTakeToDetailsActivity() {}

}
