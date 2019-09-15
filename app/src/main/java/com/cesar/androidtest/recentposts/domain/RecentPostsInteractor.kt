package com.cesar.androidtest.recentposts.domain

import com.cesar.androidtest.recentposts.RecentPostsContract
import com.cesar.androidtest.recentposts.model.RecentPostModel
import com.cesar.androidtest.recentposts.model.RecentPostsRepository


class RecentPostsInteractor(val repository: RecentPostsRepository) : RecentPostsContract.Interactor {

    var presenter: RecentPostsContract.Presenter? = null

    override fun requestList(lastViewed: String?) {

        repository.list(lastViewed, object : RecentPostsRepository.ResultListener {
            override fun onResponseSuccessful(response: List<RecentPostModel>) {
                if (lastViewed == null) {
                    presenter?.onReplaceListResponseSuccessful(response)
                } else {
                    presenter?.onAddToListResponseSuccessful(response)
                }
            }

            override fun onResponseNotSuccessful() {
                presenter?.onRequestListResponseNotSuccessful()
            }

            override fun onFailure(errorMessage: String) {
                presenter?.onRequestListFailure()
            }
        })
    }

}