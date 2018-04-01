package com.cesar.androidtest.recentposts

import com.cesar.androidtest.recentposts.model.RecentPostModel
import com.cesar.androidtest.recentposts.model.RecentPostsApi


class RecentPostsModel(val api: RecentPostsApi) : RecentPostsContract.Model {

    var presenter: RecentPostsContract.Presenter? = null

    override fun requestList(lastViewed: String?) {

        api.list(lastViewed, object : RecentPostsApi.ResultListener {
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