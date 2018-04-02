package com.cesar.androidtest.postdetails

import com.cesar.androidtest.postdetails.model.PostDetailsApi


class PostDetailsModel(val api: PostDetailsApi) : PostDetailsContract.Model {

    var presenter: PostDetailsContract.Presenter? = null

}