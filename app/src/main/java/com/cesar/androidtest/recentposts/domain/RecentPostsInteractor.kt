package com.cesar.androidtest.recentposts.domain

import com.cesar.androidtest.recentposts.RecentPostsContract
import com.cesar.androidtest.recentposts.model.RecentPost
import com.cesar.androidtest.recentposts.model.RecentPostsRepository
import javax.inject.Inject


class RecentPostsInteractor @Inject constructor(
        private val repository: RecentPostsRepository
) : RecentPostsContract.Interactor {

    var presenter: RecentPostsContract.Presenter? = null

    override fun requestList(lastViewed: String?) {

        repository.list(lastViewed, object : RecentPostsRepository.ResultListener {
            override fun onResponseSuccessful(response: List<RecentPost>) {
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