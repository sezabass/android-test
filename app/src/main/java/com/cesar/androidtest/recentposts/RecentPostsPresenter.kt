package com.cesar.androidtest.recentposts

import javax.inject.Inject

class RecentPostsPresenter @Inject constructor(
        val view: RecentPostsContract.View, val model: RecentPostsContract.Model) :
        RecentPostsContract.Presenter {

    override fun onLoad() {
        model.requestList()
    }

    override fun onRequestListSuccess() {
        view.onListLoadingComplete()
    }

}