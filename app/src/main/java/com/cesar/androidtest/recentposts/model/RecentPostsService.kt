package com.cesar.androidtest.recentposts.model

import retrofit2.Call
import retrofit2.http.GET

interface RecentPostsService {

    @GET("r/Android/new/.json")
    fun list(): Call<RecentPostModel>
}