package com.cesar.androidtest.recentposts

import com.cesar.androidtest.recentposts.model.RecentPostModel
import com.cesar.androidtest.recentposts.model.RecentPostsApi
import org.junit.Before
import org.junit.Test
import org.mockito.*
import org.mockito.Mockito.*

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

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        model = RecentPostsModel(mockApi)
        model.presenter = mockPresenter
    }

    @Test
    fun whenRequestListThenCallApiList() {
        model.requestList()
        verify(mockApi).list(callback = any())
    }

    @Test
    fun givenListRequestedWhenResponseSuccessfulThenCallPresenterRequestListResponseSuccessful() {
        val anyList = listOf(RecentPostModel())
        model.requestList()

        verify(mockApi).list(resultListenerArgumentCaptor.capture())
        resultListenerArgumentCaptor.value.onResponseSuccessful(anyList)

        verify(mockPresenter).onRequestListResponseSuccessful(anyList)
    }

}