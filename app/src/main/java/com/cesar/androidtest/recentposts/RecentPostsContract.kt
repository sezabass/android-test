package com.cesar.androidtest.recentposts

import com.cesar.androidtest.recentposts.model.RecentPostModel

interface RecentPostsContract {
    interface View {
        fun onListLoadingComplete(postsListResult: List<RecentPostModel>)
    }

    interface Presenter {
        fun onLoad()
        fun onRequestListResponseSuccessful(response: List<RecentPostModel>)
        fun onRequestListResponseNotSuccessful()
        fun onRequestListFailure()
    }

    interface Model {
        fun requestList()
    }
}