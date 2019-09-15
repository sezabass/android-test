package com.cesar.androidtest.postdetails

import com.cesar.androidtest.postdetails.model.Comment
import com.cesar.androidtest.postdetails.model.PostDetailsApi
import com.nhaarman.mockito_kotlin.same
import org.junit.Before
import org.junit.Test
import org.mockito.*
import org.mockito.Mockito.verify

class PostDetailsInteractorTest {// Helper function for Mockito with Kotlin
private fun <T> any(): T {
    Mockito.any<T>();return uninitialized()}
    @Suppress("UNCHECKED_CAST")
    private fun <T> uninitialized(): T = null as T

    private lateinit var model: PostDetailsModel
    @Mock
    private lateinit var mockApi: PostDetailsApi
    @Mock
    private lateinit var mockPresenter: PostDetailsContract.Presenter
    @Captor
    private lateinit var resultListenerArgumentCaptor: ArgumentCaptor<PostDetailsApi.ResultListener>

    private val idSample: String = "idSample"

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        model = PostDetailsModel(mockApi)
        model.presenter = mockPresenter
    }


    @Test
    fun whenRequestListThenCallApiList() {
        model.loadComments(idSample)
        verify(mockApi).list(same(idSample), callback = any())
    }

    @Test
    fun givenCommentsRequestedWhenResponseSuccessfulThenCallPresenterOnRequestListResponseSuccessful() {
        val anyList = listOf(Comment())
        model.loadComments(idSample)

        verify(mockApi).list(same(idSample), resultListenerArgumentCaptor.capture())
        resultListenerArgumentCaptor.value.onResponseSuccessful(anyList)

        verify(mockPresenter).onRequestListResponseSuccessful(anyList)
    }


    @Test
    fun givenCommentsRequestedWhenFailureThenCallPresenterOnRequestListFailure() {
        val anyMessage = "Any message"
        model.loadComments(idSample)

        verify(mockApi).list(same(idSample), resultListenerArgumentCaptor.capture())
        resultListenerArgumentCaptor.value.onFailure(anyMessage)

        verify(mockPresenter).onRequestListFailure()
    }

}