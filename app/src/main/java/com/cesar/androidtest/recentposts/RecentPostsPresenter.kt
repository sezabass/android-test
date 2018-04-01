package com.cesar.androidtest.recentposts

import com.cesar.androidtest.recentposts.model.RecentPostModel
import javax.inject.Inject

class RecentPostsPresenter @Inject constructor(
        val view: RecentPostsContract.View, val model: RecentPostsContract.Model) :
        RecentPostsContract.Presenter {

    override fun onLoad() {
        model.requestList(null)
    }

    override fun requestMoreItems(lastName: String?) {
        model.requestList(lastName)
    }

    override fun onSwipeToRefresh() {
        model.requestList(null)
    }

    override fun onRequestListResponseSuccessful(response: List<RecentPostModel>) {
        view.hideLoading()
        view.onListLoadingComplete(response)
    }

    override fun onRequestListResponseNotSuccessful() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onRequestListFailure() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onPostsListItemClicked() {
        view.showPostDetails()
    }
}
