package com.cesar.androidtest.recentposts

import com.cesar.androidtest.recentposts.model.RecentPostModel
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
    lateinit var mockModel: RecentPostsContract.Model
    lateinit var presenter: RecentPostsContract.Presenter

    private val lastItemSample: String = "lastItemSample"

    @Before
    fun setUp() {
        mockView = Mockito.mock(RecentPostsContract.View::class.java)
        mockModel = Mockito.mock(RecentPostsContract.Model::class.java)

        presenter = RecentPostsPresenter(mockView, mockModel)
    }

    @Test
    fun whenOnLoadThenRequestListToModel() {
        presenter.onLoad()
        verify(mockModel).requestList(null)
    }

    @Test
    fun whenOnSwipeToRefreshThenRequestListToModel() {
        presenter.onSwipeToRefresh()
        verify(mockModel).requestList(null)
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
        presenter.onPostsListItemClicked()
        verify(mockView).showPostDetails()
    }

    @Test
    fun whenRequestMoreItemsThenCallModelRequestList() {
        presenter.requestMoreItems(lastItemSample)
        verify(mockModel).requestList(lastItemSample)
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