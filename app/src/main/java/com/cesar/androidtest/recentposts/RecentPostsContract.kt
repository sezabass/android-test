package com.cesar.androidtest.recentposts

interface RecentPostsContract {
    interface View {

    }

    interface Presenter {
        fun onLoad()
        fun onRequestListSuccess()
    }

    interface Model {
        fun requestList()
    }
}