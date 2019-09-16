package com.cesar.androidtest.recentposts.model

interface RecentPostsCallback {
    var lastViewed: String?
    fun onResponseSuccessful(response: List<RecentPost>)
    fun onResponseNotSuccessful()
    fun onFailure(errorMessage: String)
}