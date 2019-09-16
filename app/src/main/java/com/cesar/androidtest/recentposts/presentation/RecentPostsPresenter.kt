package com.cesar.androidtest.recentposts.presentation

import com.cesar.androidtest.recentposts.RecentPostsContract
import com.cesar.androidtest.recentposts.model.RecentPost
import com.cesar.androidtest.recentposts.model.RecentPostsRepository
import com.cesar.androidtest.recentposts.model.RecentPostsCallback
import javax.inject.Inject

class RecentPostsPresenter @Inject constructor(
        private val repository: RecentPostsRepository) :
        RecentPostsContract.Presenter {

    lateinit var view: RecentPostsContract.View

    override var loadItemsResultListener: RecentPostsCallback = object: RecentPostsCallback {
        override var lastViewed: String? = null

        override fun onResponseSuccessful(response: List<RecentPost>) {
            if (lastViewed == null) {
                onReplaceListResponseSuccessful(response)
            } else {
                onAddToListResponseSuccessful(response)
            }
        }

        override fun onResponseNotSuccessful() {
            onRequestListResponseNotSuccessful()
        }

        override fun onFailure(errorMessage: String) {
            onRequestListFailure()
        }
    }

    override fun onLoad() {
        repository.list(loadItemsResultListener.apply {
            lastViewed = null
        })
    }

    override fun requestMoreItems(lastName: String?) {
        repository.list(loadItemsResultListener.apply {
            lastViewed = lastName
        })
    }

    override fun onSwipeToRefresh() {
        repository.list(loadItemsResultListener.apply {
            lastViewed = null
        })
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
