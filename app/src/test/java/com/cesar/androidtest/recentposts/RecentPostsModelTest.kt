package com.cesar.androidtest.recentposts

import com.cesar.androidtest.recentposts.model.RecentPostModel
import com.cesar.androidtest.recentposts.model.RecentPostsApi
import com.nhaarman.mockito_kotlin.isNull
import org.junit.Before
import org.junit.Test
import org.mockito.*
import org.mockito.Mockito.same
import org.mockito.Mockito.verify

class RecentPostsModelTest {

    // Helper function for Mockito with Kotlin
    private fun <T> any(): T {Mockito.any<T>();return uninitialized()}
    @Suppress("UNCHECKED_CAST")
    private fun <T> uninitialized(): T = null as T

    private lateinit var model: RecentPostsModel
    @Mock
    private lateinit var mockApi: RecentPostsApi
    @Mock
    private lateinit var mockPresenter: RecentPostsPresenter
    @Captor
    private lateinit var resultListenerArgumentCaptor: ArgumentCaptor<RecentPostsApi.ResultListener>

    private val lastItemSample: String = "lastItemSample"

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        model = RecentPostsModel(mockApi)
        model.presenter = mockPresenter
    }

    @Test
    fun whenRequestListThenCallApiList() {
        model.requestList(lastItemSample)
        verify(mockApi).list(same(lastItemSample), callback = any())
    }

    @Test
    fun givenListRequestedWithLastViewedWhenResponseSuccessfulThenCallPresenterAddToListResponseSuccessful() {
        val anyList = listOf(RecentPostModel())
        model.requestList(lastItemSample)

        verify(mockApi).list(same(lastItemSample), resultListenerArgumentCaptor.capture())
        resultListenerArgumentCaptor.value.onResponseSuccessful(anyList)

        verify(mockPresenter).onAddToListResponseSuccessful(anyList)
    }

    @Test
    fun givenListRequestedWithoutLastViewedWhenResponseSuccessfulThenCallPresenterReplaceListResponseSuccessful() {
        val anyList = listOf(RecentPostModel())
        model.requestList(null)

        verify(mockApi).list(isNull(), resultListenerArgumentCaptor.capture())
        resultListenerArgumentCaptor.value.onResponseSuccessful(anyList)

        verify(mockPresenter).onReplaceListResponseSuccessful(anyList)
    }

    @Test
    fun givenListRequestedWhenResponseNotSuccessfulThenCallPresenterRequestListResponseNotSuccessful() {
        model.requestList(lastItemSample)

        verify(mockApi).list(same(lastItemSample), resultListenerArgumentCaptor.capture())
        resultListenerArgumentCaptor.value.onResponseNotSuccessful()

        verify(mockPresenter).onRequestListResponseNotSuccessful()
    }

    @Test
    fun givenListRequestedWhenFailureThenCallPresenterRequestListFailure() {
        val anyString = "Some error message"
        model.requestList(lastItemSample)

        verify(mockApi).list(same(lastItemSample), resultListenerArgumentCaptor.capture())
        resultListenerArgumentCaptor.value.onFailure(anyString)

        verify(mockPresenter).onRequestListFailure()
    }

}