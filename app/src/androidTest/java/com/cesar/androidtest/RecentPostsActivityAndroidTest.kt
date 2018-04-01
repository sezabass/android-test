package com.cesar.androidtest

import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.UiController
import android.support.test.espresso.ViewAction
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.view.View
import com.cesar.androidtest.recentposts.RecentPostsActivity
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
        val appContext = InstrumentationRegistry.getTargetContext()
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
