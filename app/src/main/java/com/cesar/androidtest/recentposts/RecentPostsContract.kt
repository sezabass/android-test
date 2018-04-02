package com.cesar.androidtest.recentposts

import com.cesar.androidtest.recentposts.model.RecentPostModel

interface RecentPostsContract {
    interface View {
        fun onListLoadingComplete(postsListResult: List<RecentPostModel>)
        fun onListAddingComplete(response: List<RecentPostModel>)
        fun hideLoading()
        fun onPostsListItemClicked(listItem: android.view.View)
        fun showPostDetails()
        fun onRequestListResponseNotSuccessful()
        fun onRequestListFailure()
    }

    interface Presenter {
        fun onLoad()
        fun requestMoreItems(lastName: String?)
        fun onSwipeToRefresh()
        fun onReplaceListResponseSuccessful(response: List<RecentPostModel>)
        fun onAddToListResponseSuccessful(response: List<RecentPostModel>)
        fun onRequestListResponseNotSuccessful()
        fun onRequestListFailure()
        fun onPostsListItemClicked()
    }

    interface Model {
        fun requestList(lastViewed: String?)
    }
}