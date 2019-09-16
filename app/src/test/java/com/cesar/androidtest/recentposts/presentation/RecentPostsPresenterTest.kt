package com.cesar.androidtest.recentposts.presentation

import com.cesar.androidtest.recentposts.RecentPostsContract
import com.cesar.androidtest.recentposts.model.RecentPost
import com.cesar.androidtest.recentposts.model.RecentPostsRepository
import com.nhaarman.mockito_kotlin.same
import com.nhaarman.mockito_kotlin.verify
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RecentPostsPresenterTest {

    // Helper function for Mockito with Kotlin
    private fun <T> any(): T {
        Mockito.any<T>();return uninitialized()
    }

    @Suppress("UNCHECKED_CAST")
    private fun <T> uninitialized(): T = null as T

    lateinit var mockView: RecentPostsContract.View
    lateinit var presenter: RecentPostsContract.Presenter

    lateinit var mockRepository: RecentPostsRepository

    private val lastItemSample: String = "lastItemSample"

    @Before
    fun setUp() {
        mockView = Mockito.mock(RecentPostsContract.View::class.java)
        mockRepository = Mockito.mock(RecentPostsRepository::class.java)

        presenter = RecentPostsPresenter(mockRepository).apply {
            view = mockView
        }
    }

    @Test
    fun whenOnLoadThenRequestListToRepository() {
        presenter.onLoad()
        verify(mockRepository).list(any())
    }

    @Test
    fun whenOnSwipeToRefreshThenRequestListToRepository() {
        presenter.onSwipeToRefresh()
        verify(mockRepository).list(any())
    }

    @Test
    fun givenListRequestedWhenRepositoryReturnsReplaceSuccessThenCallViewSuccess() {
        presenter.onReplaceListResponseSuccessful(listOf(RecentPost()))
        verify(mockView).hideLoading()
        verify(mockView).onListLoadingComplete(any())
    }

    @Test
    fun givenListRequestedWhenRepositoryReturnsAddSuccessThenCallViewSuccess() {
        presenter.onAddToListResponseSuccessful(listOf(RecentPost()))
        verify(mockView).hideLoading()
        verify(mockView).onListAddingComplete(any())
    }

    @Test
    fun whenOnPostsListItemClickedThenCallViewShowPostDetails() {
        val somePost = RecentPost()
        presenter.onPostsListItemClicked(somePost)
        verify(mockView).showPostDetails(same(somePost))
    }

    @Test
    fun whenRequestMoreItemsThenCallRepositoryList() {
        presenter.requestMoreItems(lastItemSample)
        verify(mockRepository).list(any())
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

    /*
    Interactor Tests
    // TODO: add them back when architecture is MVVM

    @Test
    fun givenListRequestedWithLastViewedWhenResponseSuccessfulThenCallOnAddToListResponseSuccessful() {
        val anyList = listOf(RecentPost())

        verify(mockRepository).list(resultListenerArgumentCaptor.capture())
        resultListenerArgumentCaptor.value.onResponseSuccessful(anyList)

        verify(presenter).onAddToListResponseSuccessful(anyList)
    }

    @Test
    fun givenListRequestedWithoutLastViewedWhenResponseSuccessfulThenCallPresenterReplaceListResponseSuccessful() {
        val anyList = listOf(RecentPost())
        interactor.requestList(null)

        verify(mockRepository).list(isNull(), resultListenerArgumentCaptor.capture())
        resultListenerArgumentCaptor.value.onResponseSuccessful(anyList)

        verify(mockPresenter).onReplaceListResponseSuccessful(anyList)
    }

    @Test
    fun givenListRequestedWhenResponseNotSuccessfulThenCallPresenterRequestListResponseNotSuccessful() {
        interactor.requestList(lastItemSample)

        verify(mockRepository).list(same(lastItemSample), resultListenerArgumentCaptor.capture())
        resultListenerArgumentCaptor.value.onResponseNotSuccessful()

        verify(mockPresenter).onRequestListResponseNotSuccessful()
    }

    @Test
    fun givenListRequestedWhenFailureThenCallPresenterRequestListFailure() {
        val anyString = "Some error message"
        interactor.requestList(lastItemSample)

        verify(mockRepository).list(same(lastItemSample), resultListenerArgumentCaptor.capture())
        resultListenerArgumentCaptor.value.onFailure(anyString)

        verify(mockPresenter).onRequestListFailure()
    }
     */
}