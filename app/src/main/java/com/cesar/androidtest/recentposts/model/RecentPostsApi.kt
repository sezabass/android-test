package com.cesar.androidtest.recentposts.model

interface RecentPostsApi {
    fun list(callback: ResultListener)

    interface ResultListener {
        fun onResponseSuccessful(response: List<RecentPostModel>)
        fun onResponseNotSuccessful()
        fun onFailure(errorMessage: String)
    }
}