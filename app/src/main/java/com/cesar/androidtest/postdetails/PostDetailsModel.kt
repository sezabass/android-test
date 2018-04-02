package com.cesar.androidtest.postdetails

import com.cesar.androidtest.postdetails.model.Comment
import com.cesar.androidtest.postdetails.model.PostDetailsApi


class PostDetailsModel(val api: PostDetailsApi) : PostDetailsContract.Model {

    var presenter: PostDetailsContract.Presenter? = null

    override fun loadComments(postId: String) {
        api.list(postId, object : PostDetailsApi.ResultListener {
            override fun onResponseSuccessful(response: List<Comment>) {
                presenter?.onRequestListResponseSuccessful(response)
            }

            override fun onFailure(errorMessage: String) {
                presenter?.onRequestListFailure()
            }
        })
    }
}