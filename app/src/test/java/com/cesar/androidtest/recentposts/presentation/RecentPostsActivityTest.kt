package com.cesar.androidtest.recentposts.presentation

import com.cesar.androidtest.recentposts.di.RecentPostsTestActivityMock
import com.cesar.androidtest.recentposts.model.Data
import com.cesar.androidtest.recentposts.model.RecentPostModel
import junit.framework.TestCase.assertFalse
import kotlinx.android.synthetic.main.activity_recentposts.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.*
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class RecentPostsActivityTest {

    private lateinit var activity: RecentPostsActivity

    private val sampleList = ArrayList<RecentPostModel>()

    @Before
    fun setUp() {
        val samplePost = RecentPostModel()
        val sampleData = Data()
        sampleData.author = "Sample Author"
        sampleData.title = "Sample Title"
        sampleData.url = "http://www.pudim.com.br"
        samplePost.data = sampleData
        sampleList.add(samplePost)

        activity = Robolectric.buildActivity(RecentPostsTestActivityMock::class.java).create().get()
    }

    @Test
    fun whenLoadActivityThenRequestPostsList() {
        verify(activity.presenter).onLoad()
    }

    @Test
    fun whenSwipeToRefreshThenRequestPresenterOnSwipeToRefresh() {
        activity.onRefresh()
        verify(activity.presenter).onSwipeToRefresh()
    }

    @Test
    fun whenHideLoadingThenRecentPostsSwipeRefreshLayoutIsLoadingShouldBeFalse() {
        activity.hideLoading()
        assertFalse(activity.recentPostsSwipeRefreshLayout.isRefreshing)
    }

    @Test
    fun givenListLoadingCompleteWhenItemClickedThenCallPresenter() {
        activity.onListLoadingComplete(sampleList)

        val recyclerView = activity.recyclerView
        // workaround robolectric recyclerView issue
        recyclerView.measure(0, 0)
        recyclerView.layout(0, 0, 100, 1000)
        recyclerView.findViewHolderForAdapterPosition(0)?.itemView?.performClick()

        verify(activity.presenter).onPostsListItemClicked(any())
    }

    @Test
    fun givenListAddingCompleteWhenItemClickedThenCallPresenter() {
        activity.onListAddingComplete(sampleList)

        val recyclerView = activity.recyclerView
        // workaround robolectric recyclerView issue
        recyclerView.measure(0, 0)
        recyclerView.layout(0, 0, 100, 1000)
        recyclerView.findViewHolderForAdapterPosition(0)?.itemView?.performClick()

        verify(activity.presenter).onPostsListItemClicked(any())
    }

    @Test
    fun whenRequestNextDataFromApiThenPresenterRequestMoreItems() {
        activity.loadNextDataFromApi()
        verify(activity.presenter).requestMoreItems(null)
    }

}
