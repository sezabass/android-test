package com.cesar.androidtest.postdetails

import com.cesar.androidtest.postdetails.model.Comment
import com.nhaarman.mockito_kotlin.same
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mockito
import org.mockito.Mockito.anyList
import org.mockito.Mockito.verify

class PostDetailsPresenterTest {

    // Helper function for Mockito with Kotlin
    private fun <T> any(): T {
        Mockito.any<T>();return uninitialized()}
    @Suppress("UNCHECKED_CAST")
    private fun <T> uninitialized(): T = null as T

    lateinit var mockView: PostDetailsContract.View
    lateinit var mockModel: PostDetailsContract.Model
    lateinit var presenter: PostDetailsContract.Presenter

    private val lastItemSample: String = "lastItemSample"

    @Before
    fun setUp() {
        mockView = Mockito.mock(PostDetailsContract.View::class.java)
        mockModel = Mockito.mock(PostDetailsContract.Model::class.java)

        presenter = PostDetailsPresenter(mockView, mockModel)
    }

    @Test
    fun whenOnLoadThenCallModelLoadComments() {
        val postId = "samplePostId"
        presenter.onLoad(postId)
        verify(mockModel).loadComments(same(postId))
    }

    @Test
    fun onRequestListResponseSuccessful() {
        val response: List<Comment> = listOf(Comment())
        presenter.onRequestListResponseSuccessful(response)
        verify(mockView).onLoadCommentsSuccess(anyList<Comment>())
    }

    @Test
    fun onRequestListFailure() {
        presenter.onRequestListFailure()
        verify(mockView).onLoadCommentsFailure()
    }
}