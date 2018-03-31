package com.cesar.androidtest.recentposts

import com.cesar.androidtest.recentposts.model.RecentPostsApi
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class RecentPostsModelTest {

    // Helper function for Mockito with Kotlin
    private fun <T> any(): T {Mockito.any<T>();return uninitialized()}
    private fun <T> uninitialized(): T = null as T

    private lateinit var model: RecentPostsModel
    @Mock
    private lateinit var mockApi: RecentPostsApi
    @Mock
    private lateinit var mockPresenter: RecentPostsPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        model = RecentPostsModel(mockApi)
        model.presenter = mockPresenter
    }

    @Test
    fun whenRequestListThenCallApiList() {
        model.requestList()
        verify(mockApi, times(1)).list(callback = any())
    }

}