package com.cesar.androidtest.recentposts.presentation

import com.cesar.androidtest.recentposts.RecentPostsContract
import com.cesar.androidtest.recentposts.model.RecentPost
import javax.inject.Inject

class RecentPostsPresenter @Inject constructor(
        private val interactor: RecentPostsContract.Interactor) :
        RecentPostsContract.Presenter {

    lateinit var view: RecentPostsContract.View

    override fun onLoad() {
        interactor.requestList(null)
    }

    override fun requestMoreItems(lastName: String?) {
        interactor.requestList(lastName)
    }

    override fun onSwipeToRefresh() {
        interactor.requestList(null)
    }

    override fun onReplaceListResponseSuccessful(response: List<RecentPost>) {
        view.hideLoading()
        view.onListLoadingComplete(response)
    }

    override fun onAddToListResponseSuccessful(response: List<RecentPost>) {
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

    override fun onPostsListItemClicked(post: RecentPost?) {
        view.showPostDetails(post)
    }
}
