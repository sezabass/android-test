package com.cesar.androidtest.recentposts.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RecentPostsService {

    @GET("r/Android/new/.json")
    fun list(
            @Query("limit") limit: Int,
            @Query("count") count: Int,
            @Query("after") after: String?
    ): Call<RecentPost>
}