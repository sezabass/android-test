package com.cesar.androidtest.recentposts

import com.cesar.androidtest.recentposts.model.RecentPostsService
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class RecentPostsModelTest {

    lateinit var model: RecentPostsModel
    lateinit var mockService: RecentPostsService
    lateinit var mockPresenter: RecentPostsPresenter

    @Before
    fun setUp() {
        mockService = mock(RecentPostsService::class.java)
        mockPresenter = mock(RecentPostsPresenter::class.java)
        MockitoAnnotations.initMocks(this)

        model = RecentPostsModel(mockService)
        model.presenter = mockPresenter
    }

    @Test
    fun whenRequestListThenCallServiceList() {
        model.requestList()
        verify(mockService, times(1)).list()
    }

    @Test
    fun givenRequestListWhenSuccessThenCallPresenterOnSuccess() {
        model.requestList()
        verify(mockPresenter, times(1)).onRequestListSuccess()
    }

}