package com.cesar.androidtest.recentposts

import com.nhaarman.mockito_kotlin.verify
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.times

class RecentPostsPresenterTest {

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
        presenter.onRequestListResponseSuccessful()
        verify(mockView, times(1)).onListLoadingComplete()
    }

}