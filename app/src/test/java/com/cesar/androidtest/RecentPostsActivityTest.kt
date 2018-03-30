package com.cesar.androidtest

import com.cesar.androidtest.recentposts.RecentPostsActivity
import com.cesar.androidtest.recentposts.di.RecentPostsTestActivityMock
import com.cesar.androidtest.recentposts.model.Data
import com.cesar.androidtest.recentposts.model.RecentPostModel
import junit.framework.Assert.assertFalse
import kotlinx.android.synthetic.main.activity_recentposts.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class, sdk = intArrayOf(27))
class RecentPostsActivityTest {

    private lateinit var activity: RecentPostsActivity

    private val sampleList = ArrayList<RecentPostModel>()

    @Before
    fun setUp() {
        var samplePost = RecentPostModel()
        var sampleData = Data()
        sampleData.author = "Sample Author"
        sampleData.title = "Sample Title"
        sampleData.url = "http://www.pudim.com.br"
        samplePost.data = sampleData
        sampleList.add(samplePost)

        activity = Robolectric.buildActivity(RecentPostsTestActivityMock::class.java).create().get()
    }

    @Test
    fun whenLoadActivityThenRequestPostsList() {
        verify(activity.recentPostsPresenter, times(1)).onLoad()
    }

    @Test
    fun whenSwipeToRefreshThenRequestPresenterOnSwipeToRefresh() {
        activity.onRefresh()
        verify(activity.recentPostsPresenter, times(1)).onSwipeToRefresh()
    }

    @Test
    fun whenHideLoadingThenRecentPostsSwipeRefreshLayoutIsLoadingShouldBeFalse() {
        activity.hideLoading()
        assertFalse(activity.recentPostsSwipeRefreshLayout.isRefreshing)
    }

    @Test
    @Throws(Exception::class)
    fun whenOnListItemClickedThenCallPresenter() {
        activity.onListLoadingComplete(sampleList)

        val recyclerView = activity.recyclerView
        // workaround robolectric recyclerView issue
        recyclerView.measure(0, 0)
        recyclerView.layout(0, 0, 100, 1000)
        recyclerView.findViewHolderForAdapterPosition(0).itemView.performClick()

        verify(activity.recentPostsPresenter).onPostsListItemClicked()
    }
}
