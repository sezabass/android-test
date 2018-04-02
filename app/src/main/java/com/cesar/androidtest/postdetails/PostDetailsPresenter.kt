package com.cesar.androidtest.postdetails

import com.cesar.androidtest.postdetails.model.Comment
import javax.inject.Inject

class PostDetailsPresenter @Inject constructor(
        val view: PostDetailsContract.View, val model: PostDetailsContract.Model) :
        PostDetailsContract.Presenter {

    override fun onLoad(postId: String) {
        model.loadComments(postId)
    }

    override fun onRequestListResponseSuccessful(response: List<Comment>) {
        view.onLoadCommentsSuccess(response)
    }

    override fun onRequestListFailure() {
        view.onLoadCommentsFailure()
    }
}
