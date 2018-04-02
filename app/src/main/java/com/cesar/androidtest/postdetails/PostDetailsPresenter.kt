package com.cesar.androidtest.postdetails

import javax.inject.Inject

class PostDetailsPresenter @Inject constructor(
        val view: PostDetailsContract.View, val model: PostDetailsContract.Model) :
        PostDetailsContract.Presenter {
}
