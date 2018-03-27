package com.cesar.androidtest.recentposts

import com.cesar.androidtest.recentposts.model.RecentPostsApi
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class RecentPostsModelTest {

    // Helper function for Mockito with Kotlin
    private fun <T> any(): T {Mockito.any<T>();return uninitialized()}
    private fun <T> uninitialized(): T = null as T

    lateinit var model: RecentPostsModel
    lateinit var mockApi: RecentPostsApi
    lateinit var mockPresenter: RecentPostsPresenter

    @Before
    fun setUp() {
        mockApi = mock(RecentPostsApi::class.java)
        mockPresenter = mock(RecentPostsPresenter::class.java)
        MockitoAnnotations.initMocks(this)

        model = RecentPostsModel(mockApi)
        model.presenter = mockPresenter
    }

    @Test
    fun whenRequestListThenCallServiceList() {
        model.requestList()
        verify(mockApi, times(1)).list(callback = any())
    }

    @Test
    fun givenRequestListWhenSuccessThenCallPresenterOnSuccess() {
        model.requestList()
        verify(mockPresenter, times(1)).
                onRequestListResponseSuccessful(any())
    }

}