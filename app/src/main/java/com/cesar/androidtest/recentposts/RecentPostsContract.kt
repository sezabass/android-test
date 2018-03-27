package com.cesar.androidtest.recentposts

interface RecentPostsContract {
    interface View {
        fun onListLoadingComplete()
    }

    interface Presenter {
        fun onLoad()
        fun onRequestListSuccess()
    }

    interface Model {
        fun requestList()
    }
}