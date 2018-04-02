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

    override fun onReplaceListResponseSuccessful(response: List<RecentPostModel>) {
        view.hideLoading()
        view.onListLoadingComplete(response)
    }

    override fun onAddToListResponseSuccessful(response: List<RecentPostModel>) {
        view.hideLoading()
        view.onListAddingComplete(response)
    }

    override fun onRequestListResponseNotSuccessful() {
        view.hideLoading()
        view.onRequestListResponseNotSuccessful()
    }

    override fun onRequestListFailure() {
        view.hideLoading()
        view.onRequestListFailure()
    }

    override fun onPostsListItemClicked() {
        view.showPostDetails()
    }
}
