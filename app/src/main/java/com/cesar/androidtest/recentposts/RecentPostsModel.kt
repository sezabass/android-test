package com.cesar.androidtest.recentposts

import com.cesar.androidtest.recentposts.model.RecentPostModel
import com.cesar.androidtest.recentposts.model.RecentPostsApi


class RecentPostsModel(val api: RecentPostsApi) : RecentPostsContract.Model {

    var presenter: RecentPostsContract.Presenter? = null

    override fun requestList(lastItem : String?) {

        api.list(lastItem, object : RecentPostsApi.ResultListener {
            override fun onResponseSuccessful(response: List<RecentPostModel>) {
                presenter?.onRequestListResponseSuccessful(response)
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