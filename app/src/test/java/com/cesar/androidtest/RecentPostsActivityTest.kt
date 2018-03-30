package com.cesar.androidtest

import com.cesar.androidtest.recentposts.RecentPostsActivity
import com.cesar.androidtest.recentposts.di.RecentPostsTestActivityMock
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(RobolectricTestRunner::class)
class RecentPostsActivityTest {

    private lateinit var activity : RecentPostsActivity

    @Before
    fun setUp() {
        activity = Robolectric.buildActivity(RecentPostsTestActivityMock::class.java).create().get()
    }

    @Test
    fun whenLoadActivityThenRequestPostsList() {
        verify(activity.recentPostsPresenter, times(1)).onLoad()
    }
}
