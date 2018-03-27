package com.cesar.androidtest.recentposts

import javax.inject.Inject

class RecentPostsPresenter @Inject constructor(
        val view: RecentPostsContract.View, val model: RecentPostsContract.Model) :
        RecentPostsContract.Presenter {

    override fun onLoad() {
        model.requestList()
    }

    override fun onRequestListResponseSuccessful() {
        view.onListLoadingComplete()
    }

    override fun onRequestListResponseNotSuccessful() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onRequestListFailure() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
