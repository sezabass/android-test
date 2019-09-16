package com.cesar.androidtest.recentposts

import com.cesar.androidtest.recentposts.model.RecentPost
import com.cesar.androidtest.recentposts.model.RecentPostsCallback

interface RecentPostsContract {
    interface View {
        fun onListLoadingComplete(postsListResult: List<RecentPost>)
        fun onListAddingComplete(response: List<RecentPost>)
        fun hideLoading()
        fun onPostsListItemClicked(listItem: android.view.View, post: RecentPost?)
        fun showPostDetails(post: RecentPost?)
        fun onRequestListResponseNotSuccessful()
        fun onRequestListFailure()
    }

    interface Presenter {
        var loadItemsResultListener: RecentPostsCallback
        fun onLoad()
        fun requestMoreItems(lastName: String?)
        fun onSwipeToRefresh()
        fun onReplaceListResponseSuccessful(response: List<RecentPost>)
        fun onAddToListResponseSuccessful(response: List<RecentPost>)
        fun onRequestListResponseNotSuccessful()
        fun onRequestListFailure()
        fun onPostsListItemClicked(post: RecentPost?)
    }

    interface Interactor {
        fun requestList(callback: RecentPostsCallback)
    }
}