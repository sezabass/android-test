package com.cesar.androidtest.postdetails

import com.cesar.androidtest.postdetails.model.Comment

interface PostDetailsContract {
    interface View {
        fun onLoadCommentsSuccess(response: List<Comment>)
        fun onLoadCommentsFailure()
    }

    interface Presenter {
        fun onLoad(postId: String)
        fun onRequestListResponseSuccessful(response: List<Comment>)
        fun onRequestListFailure()
    }

    interface Model {
        fun loadComments(postId: String)
    }
}