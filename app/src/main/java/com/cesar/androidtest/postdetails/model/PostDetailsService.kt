package com.cesar.androidtest.postdetails.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PostDetailsService {

    @GET("r/Android/comments/{id}/.json")
    fun commentsList(
            @Path("id") id: String
    ): Call<CommentModel>
}