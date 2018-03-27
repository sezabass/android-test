package com.cesar.androidtest.recentposts

import com.cesar.androidtest.recentposts.model.RecentPostModel
import com.nhaarman.mockito_kotlin.verify
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.times

class RecentPostsPresenterTest {

    // Helper function for Mockito with Kotlin
    private fun <T> any(): T {Mockito.any<T>();return uninitialized()}
    private fun <T> uninitialized(): T = null as T

    lateinit var mockView: RecentPostsContract.View
    lateinit var mockModel: RecentPostsContract.Model
    lateinit var presenter: RecentPostsContract.Presenter

    @Before
    fun setUp() {
        mockView = Mockito.mock(RecentPostsContract.View::class.java)
        mockModel = Mockito.mock(RecentPostsContract.Model::class.java)

        presenter = RecentPostsPresenter(mockView, mockModel)
    }

    @Test
    fun whenOnLoadThenRequestListToModel() {
        presenter.onLoad()
        verify(mockModel, times(1)).requestList()
    }

    @Test
    fun givenListRequestedWhenModelReturnsSuccessThenCallViewSuccess() {
        presenter.onRequestListResponseSuccessful(ArrayList())
        verify(mockView, times(1)).onListLoadingComplete(any())
    }

}