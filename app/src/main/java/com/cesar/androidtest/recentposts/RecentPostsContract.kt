package com.cesar.androidtest.recentposts

import com.cesar.androidtest.recentposts.model.RecentPostModel

interface RecentPostsContract {
    interface View {
        fun onListLoadingComplete(postsListResult: List<RecentPostModel>)
        fun hideLoading()
        fun onPostsListItemClicked(listItem: android.view.View)
        fun showPostDetails()
    }

    interface Presenter {
        fun onLoad()
        fun onSwipeToRefresh()
        fun onRequestListResponseSuccessful(response: List<RecentPostModel>)
        fun onRequestListResponseNotSuccessful()
        fun onRequestListFailure()
        fun onPostsListItemClicked()
    }

    interface Model {
        fun requestList()
    }
}