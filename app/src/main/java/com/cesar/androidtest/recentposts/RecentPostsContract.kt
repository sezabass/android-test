package com.cesar.androidtest.recentposts

interface RecentPostsContract {
    interface View {
        fun onListLoadingComplete()
    }

    interface Presenter {
        fun onLoad()
        fun onRequestListResponseSuccessful()
        fun onRequestListResponseNotSuccessful()
        fun onRequestListFailure()
    }

    interface Model {
        fun requestList()
    }
}