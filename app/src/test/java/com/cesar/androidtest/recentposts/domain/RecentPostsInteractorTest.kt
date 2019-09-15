package com.cesar.androidtest.recentposts.domain

import com.cesar.androidtest.recentposts.RecentPostsContract
import com.cesar.androidtest.recentposts.model.RecentPostModel
import com.cesar.androidtest.recentposts.model.RecentPostsRepository
import com.nhaarman.mockito_kotlin.isNull
import org.junit.Before
import org.junit.Test
import org.mockito.*
import org.mockito.Mockito.same
import org.mockito.Mockito.verify

class RecentPostsInteractorTest {

    // Helper function for Mockito with Kotlin
    private fun <T> any(): T {Mockito.any<T>();return uninitialized()}
    @Suppress("UNCHECKED_CAST")
    private fun <T> uninitialized(): T = null as T

    private lateinit var interactor: RecentPostsInteractor
    @Mock
    private lateinit var mockRepository: RecentPostsRepository
    @Mock
    private lateinit var mockPresenter: RecentPostsContract.Presenter
    @Captor
    private lateinit var resultListenerArgumentCaptor: ArgumentCaptor<RecentPostsRepository.ResultListener>

    private val lastItemSample: String = "lastItemSample"

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        interactor = RecentPostsInteractor(mockRepository)
        interactor.presenter = mockPresenter
    }

    @Test
    fun whenRequestListThenCallApiList() {
        interactor.requestList(lastItemSample)
        verify(mockRepository).list(same(lastItemSample), callback = any())
    }

    @Test
    fun givenListRequestedWithLastViewedWhenResponseSuccessfulThenCallPresenterAddToListResponseSuccessful() {
        val anyList = listOf(RecentPostModel())
        interactor.requestList(lastItemSample)

        verify(mockRepository).list(same(lastItemSample), resultListenerArgumentCaptor.capture())
        resultListenerArgumentCaptor.value.onResponseSuccessful(anyList)

        verify(mockPresenter).onAddToListResponseSuccessful(anyList)
    }

    @Test
    fun givenListRequestedWithoutLastViewedWhenResponseSuccessfulThenCallPresenterReplaceListResponseSuccessful() {
        val anyList = listOf(RecentPostModel())
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

}