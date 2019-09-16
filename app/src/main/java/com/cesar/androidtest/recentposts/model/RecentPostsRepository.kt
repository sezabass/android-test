package com.cesar.androidtest.recentposts.model

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

interface RecentPostsRepository {
    fun list(callback: RecentPostsCallback)
}

class RecentPostsRepositoryImpl @Inject constructor(
        private val service: RecentPostsService) : RecentPostsRepository {

    override fun list(callback: RecentPostsCallback) {

        val listCall: Call<RecentPost>? = service.list(
                limit = pageSize,
                count = pageSize,
                after = callback.lastViewed
        )

        listCall?.enqueue(object : Callback<RecentPost> {
            override fun onResponse(call: Call<RecentPost>?, response: Response<RecentPost>?) {
                if (response!!.isSuccessful) {
                    response.body()?.data?.children?.let {
                        callback.onResponseSuccessful(it.toMutableList())
                        return
                    }
                    callback.onResponseNotSuccessful()
                } else {
                    callback.onResponseNotSuccessful()
                }
            }

            override fun onFailure(call: Call<RecentPost>?, t: Throwable?) {
                callback.onFailure(t?.message.toString())
            }
        })
    }

    companion object {
        const val TAG = "API-RecentPosts"
        const val pageSize = 10
    }

}