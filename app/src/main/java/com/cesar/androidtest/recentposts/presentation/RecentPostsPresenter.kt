package com.cesar.androidtest.recentposts.presentation

import com.cesar.androidtest.recentposts.RecentPostsContract
import com.cesar.androidtest.recentposts.model.RecentPostModel
import javax.inject.Inject

class RecentPostsPresenter @Inject constructor(
        val view: RecentPostsContract.View, val interactor: RecentPostsContract.Interactor) :
        RecentPostsContract.Presenter {

    override fun onLoad() {
        interactor.requestList(null)
    }

    override fun requestMoreItems(lastName: String?) {
        interactor.requestList(lastName)
    }

    override fun onSwipeToRefresh() {
        interactor.requestList(null)
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

    override fun onPostsListItemClicked(post: RecentPostModel?) {
        view.showPostDetails(post)
    }
}
