package com.cesar.androidtest.recentposts.presentation

import com.cesar.androidtest.recentposts.RecentPostsContract
import com.cesar.androidtest.recentposts.model.RecentPostModel
import com.nhaarman.mockito_kotlin.same
import com.nhaarman.mockito_kotlin.verify
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class RecentPostsPresenterTest {

    // Helper function for Mockito with Kotlin
    private fun <T> any(): T {Mockito.any<T>();return uninitialized()}
    @Suppress("UNCHECKED_CAST")
    private fun <T> uninitialized(): T = null as T

    lateinit var mockView: RecentPostsContract.View
    lateinit var mockInteractor: RecentPostsContract.Interactor
    lateinit var presenter: RecentPostsContract.Presenter

    private val lastItemSample: String = "lastItemSample"

    @Before
    fun setUp() {
        mockView = Mockito.mock(RecentPostsContract.View::class.java)
        mockInteractor = Mockito.mock(RecentPostsContract.Interactor::class.java)

        presenter = RecentPostsPresenter(mockInteractor).apply {
            view = mockView
        }
    }

    @Test
    fun whenOnLoadThenRequestListToModel() {
        presenter.onLoad()
        verify(mockInteractor).requestList(null)
    }

    @Test
    fun whenOnSwipeToRefreshThenRequestListToModel() {
        presenter.onSwipeToRefresh()
        verify(mockInteractor).requestList(null)
    }

    @Test
    fun givenListRequestedWhenModelReturnsReplaceSuccessThenCallViewSuccess() {
        presenter.onReplaceListResponseSuccessful(listOf(RecentPostModel()))
        verify(mockView).hideLoading()
        verify(mockView).onListLoadingComplete(any())
    }

    @Test
    fun givenListRequestedWhenModelReturnsAddSuccessThenCallViewSuccess() {
        presenter.onAddToListResponseSuccessful(listOf(RecentPostModel()))
        verify(mockView).hideLoading()
        verify(mockView).onListAddingComplete(any())
    }

    @Test
    fun whenOnPostsListItemClickedThenCallViewShowPostDetails() {
        val somePost = RecentPostModel()
        presenter.onPostsListItemClicked(somePost)
        verify(mockView).showPostDetails(same(somePost))
    }

    @Test
    fun whenRequestMoreItemsThenCallModelRequestList() {
        presenter.requestMoreItems(lastItemSample)
        verify(mockInteractor).requestList(lastItemSample)
    }

    @Test
    fun whenOnRequestListResponseNotSuccessfulThenCallViewOnRequestListResponseNotSuccessful() {
        presenter.onRequestListResponseNotSuccessful()
        verify(mockView).hideLoading()
        verify(mockView).onRequestListResponseNotSuccessful()
    }
    
    @Test
    fun whenOnRequestListFailureThenCallViewOnRequestListFailure() {
        presenter.onRequestListFailure()
        verify(mockView).hideLoading()
        verify(mockView).onRequestListFailure()
    }
}